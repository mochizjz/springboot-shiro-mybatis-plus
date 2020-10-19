/******************************************************************************
* CREATETIME : 2020年8月31日 上午8:45:56
******************************************************************************/
package ins.webeye.project.eye.vo;

import java.util.Date;

import lombok.Data;

/**
 * 控制文件写入的vo,
 * 
 * <pre>
 * 控制逻辑：
 * startRecord创建的时候：
 * 	 1、初始化了一个EyeRecordFileVo，设置saveFileTime为当前时间10秒后，放到缓存，key为filePath，缓存时间30分钟;
 * 	 2、启动了一个定时任务，10秒后执行文件保存；
 * 前端发起保存文件的请求saveFile时：
 * 	 1、从缓存中得到EyeRecordFileVo，将文件写入内容追加到fileContenth，
 *   2、判断saveFileTime是否小于当前时间，如果小于， 保存现在的文件内容，update 录制结束时间，同时要重新发起一个10秒后执行保存文件
 * </pre>
 * 
 * @author ★LiuPing
 */
@Data
public class EyeRecordFileVo {

	/** 编号 */
	private Long id;

	/** token */
	private String token;

	/** 订单id */
	private String orderId;

	/** 产品代码 */
	private String productCode;

	/** 最后一次请求的时间 */
	// private Date lastRequestTime;

	/** 文件地址 */
	private String fileRealPath;

	/** 保存的次数 */
	private int saveCount = 0;

	/** oss AppendObject 的位置 */
	private long position = 0L;

	private Date beginTime;
}
