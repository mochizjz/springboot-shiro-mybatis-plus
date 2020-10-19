package ins.webeye.project.system.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ins.webeye.common.utils.IpUtils;
import ins.webeye.common.utils.ServletUtils;
import ins.webeye.common.utils.StringUtils;
import ins.webeye.common.utils.security.AesEncryptUtils;
import ins.webeye.common.utils.security.RSAUtils;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录验证
 * 
 * @author webeye
 */
@Slf4j
@Controller
public class LoginController extends BaseController
{
    @Autowired
    private Environment env;

    @GetMapping("/")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap mmap)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
		if(canLogin(request)==false){
			// return redirect("http://www.baidu.com");
			return "blank";
		}

        mmap.put("rsaPublicKey",env.getProperty("RSA.public.key"));
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
	public AjaxResult ajaxLogin(HttpServletRequest request,String username,String pdmd5,Boolean rememberMe)
    {
		if(canLogin(request)==false){
			return error("当前环境不允许登录");
		}
        String encrypted = request.getParameter("encrypted");
        String aseKey = RSAUtils.decryptDataOnJava(encrypted,env.getProperty("RSA.private.key"));
        String password = pdmd5;
        try {
            password = AesEncryptUtils.decrypt(password, aseKey);
			// log.debug("==decrypt password=="+password);
        } catch (Exception e) {
			log.error(e.getMessage(),e);
			return error(e.getMessage());
        }


        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }

	private boolean canLogin(HttpServletRequest request) {
		/*
		 * 环境变量配置：
		-Deye.login=192.168.*  表示这个IP段可以登录后台；
		-Deye.login=false 表示关闭登录
		
		被拒绝登录的跳转到 系统配置的 
		sys.portal.url(门户网站)
		 */
		String loginEnabled = System.getProperty("eye.login");
		if(StringUtils.isBlank(loginEnabled)||loginEnabled.equals("true")){
			return true;
		}
		if(loginEnabled.equals("false")){
			return false;
		}
		if(loginEnabled.indexOf('.')>0){// 带有.的是IP形式
			loginEnabled = loginEnabled.replace('*','\0');// 去掉*号
			String ip = IpUtils.getIpAddr(request);
			if(ip.startsWith(loginEnabled)){
				return true;
			}
		}
		return false;
	}
}
