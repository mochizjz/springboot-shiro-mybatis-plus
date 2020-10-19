/******************************************************************************
* CREATETIME : 2020年8月30日 下午2:52:09
******************************************************************************/
package ins.webeye.project.eye.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.framework.shiro.service.PasswordService;
import ins.webeye.framework.web.domain.AjaxResult;
import ins.webeye.project.eye.clientconfig.domain.EyeClientConfig;
import ins.webeye.project.eye.clientconfig.service.IEyeClientConfigService;
import ins.webeye.project.system.user.domain.User;

/**
 * <pre></pre>
 * @author ★LiuPing
 */
@Service
public class EyeApiService {

	@Autowired
	private IEyeClientConfigService clientConfigService;
	@Autowired
	private PasswordService passwordService;

	/**
	 * 检查客户端调用的密钥是否匹配，匹配返回null
	 * @param requestVo
	 * @return
	 */
	public AjaxResult checkClient(String systemCode,String consumerID,String consumerPWD) {
		// 验证字段是否为空//订单号+产品id+user_id//消费方密码


		EyeClientConfig clientConfig = clientConfigService.selectClientConfig(systemCode,consumerID);

		if(clientConfig==null){
			return AjaxResult.error("系统["+systemCode+"]或消费方ID["+consumerID+"]不存在,请检查参数");
		}
		if( !clientConfig.getAuthorizeKey().equals(consumerPWD)){
			// 密码不匹配，可能传了明文密码，下面代码是兼容旧的密码验证
			User user = new User();
			user.setLoginName(consumerID);
			user.setSalt(clientConfig.getFlag());
			user.setPassword(clientConfig.getAuthorizeKey());
			boolean matches = passwordService.matches(user,consumerPWD);
			if(matches==false){
				return AjaxResult.error("系统["+systemCode+"]消费方ID["+consumerID+"]密钥不匹配,请检查参数");
			}
		}
		return null;
	}
}
