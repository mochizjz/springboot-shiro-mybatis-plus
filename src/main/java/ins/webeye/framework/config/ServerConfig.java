package ins.webeye.framework.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ins.webeye.common.utils.ServletUtils;

/**
 * 服务相关配置
 * 
 * @author webeye
 *
 */
@Component
public class ServerConfig
{

	@Autowired
	private WebEyeConfig webEyeConfig;

	/**
	 * 获取接口的URL保存路径，有个时候请求路径和api路径不同
	 * @return
	 */
	public String getApiUrl() {
		if(StringUtils.isNotBlank(webEyeConfig.getApiServiceUrl())){
			return webEyeConfig.getApiServiceUrl();// 有配置，直接取配置的
		}
		HttpServletRequest request = ServletUtils.getRequest();
		return getDomain(request);
	}
    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     * 
     * @return 服务地址
     */
    public String getUrl()
    {
		HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request);
    }

    public static String getDomain(HttpServletRequest request)
    {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
