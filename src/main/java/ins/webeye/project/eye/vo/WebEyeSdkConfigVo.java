/******************************************************************************
* CREATETIME : 2020年8月30日 下午11:05:36
******************************************************************************/
package ins.webeye.project.eye.vo;

import lombok.Data;

/**
 * 前端录制sdk的配置文件
 * @author ★LiuPing
 */
@Data
public class WebEyeSdkConfigVo {

	/** 系统或渠道编码 */
	private String sysCode;
	/** 服务器端地址 */
	private String serviceURL;
	/** SDK类型 */
	private String sdkType;
	/** 是否自动开始录制 */
	private boolean autoStart;
	/** JS 里绑定录制的token */
	private String token;

	private boolean gzip;

}
