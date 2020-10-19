package ins.webeye.project.eye.app.service;

import java.util.TimerTask;

import ins.webeye.project.eye.record.domain.EyeRecordPage;
import ins.webeye.project.eye.vo.OrderInfoVo;
import ins.webeye.project.eye.vo.UrlHanledVo;
import ins.webeye.project.eye.vo.WebEyeSdkConfigVo;



/**
 * app Service接口
 * @author xiaoquan
 * @date 2020-07-15
 */
public interface IEyeAppService 
{
	
	 /**
	  * 开始录制-保存录制信息
	  * @param eyeRecordPage
	  * @return
	  */
	 public EyeRecordPage startRecord(EyeRecordPage eyeRecordPage) throws Exception;
	 
	 
	 /**
	  * 结束录制-修改录制信息、转移订单
	  * @param eyeRecordPage
	  * @return
	  */
	 public EyeRecordPage endRecord(EyeRecordPage eyeRecordPage)throws Exception;
	 
	/**
     * 完成一个支付流程
     * @param eyeRecord
     * @modified:
     * ☆LiuPing(2020年7月19日 下午1:47:52): <br>
     */
	public void endPaySuccess(EyeRecordPage eyeRecord) throws Exception;
	
	/**
	 * 订单信息回写
	 * @param orderVo
	 * @return
	 * @throws Exception 
	 */
	public int syncOrderInfo(OrderInfoVo orderVo) throws Exception;

	/**
	 * 异步执行方法（endPay ：支付成功后数据转移 ； urlHandle ：获取页面url转换 ；fileHandle ：文件内容替换任务）
	 * @param eyeRecord
	 * @param urlHanledVo
	 * @param filePath
	 * @param type
	 * @return
	 * @modified:
	 * ☆XiaoQuan(2020年7月22日 ): <br>
	 */
	public TimerTask asynHandleTask(EyeRecordPage eyeRecord,UrlHanledVo urlHanledVo,String filePath,String type);

	/**
	 * 获取SDK的配置
	 * @param sysCode
	 * @return
	 * @modified: ☆LiuPing(2020年8月30日 下午11:15:27): <br>
	 */
	public WebEyeSdkConfigVo getEyeSdkConfigVo(String sysCode);
	
	/**
	 * 根据IP获取城市名称
	 * @param ip
	 * @return
	 */
	public String getCityName(String ip);
	
}
