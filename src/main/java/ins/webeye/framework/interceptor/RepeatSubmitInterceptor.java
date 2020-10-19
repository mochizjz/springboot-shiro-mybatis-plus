package ins.webeye.framework.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

import ins.webeye.common.utils.ServletUtils;
import ins.webeye.framework.interceptor.annotation.RepeatSubmit;
import ins.webeye.framework.web.domain.AjaxResult;

/**
 * 防止重复提交拦截器
 * 
 * @author webeye
 */
@Component
public abstract class RepeatSubmitInterceptor extends HandlerInterceptorAdapter
{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {

		/*if(!this.isSqlAttack(request)){
		    AjaxResult ajaxResult = AjaxResult.error("参数含有非法攻击字符,已禁止继续访问！");
		    ServletUtils.renderString(response, JSONObject.toJSONString(ajaxResult));
		    return false;
		}*/

        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null)
            {
                if (this.isRepeatSubmit(request))
                {
                    AjaxResult ajaxResult = AjaxResult.error("不允许重复提交，请稍后再试");
                    ServletUtils.renderString(response, JSONObject.toJSONString(ajaxResult));
                    return false;
                }
            }
            return true;
        }
        else
        {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request);


    /**
     * 验证是否sql攻击的规则
     *
     * @param request
     * @return
     * @throws Exception
     */
    public abstract Boolean isSqlAttack(HttpServletRequest request);

}
