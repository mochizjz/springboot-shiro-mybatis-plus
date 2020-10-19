package ins.webeye.project.monitor.job.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ins.webeye.common.utils.RedisUtil;
import ins.webeye.project.eye.record.service.IEyeRecordPageService;
import lombok.extern.slf4j.Slf4j;


/**
 * 定时任务调度
 * @author XiaoQuan
 */
@Component("eyeTask")
@Slf4j
public class EyeTask
{
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
    private IEyeRecordPageService eyeRecordPageService;

	/**
	 * 清理录制信息
	 * @modified:
	 * ☆XiaoQuan(2020年7月20日 ): <br>
	 */
    public void clearRecordPages(Integer clearAll,Integer clearNoOrder)
    {
		log.info("EyeTask.clearRecordPages:正在执行清理录制信息数据...");
    	try{
    		eyeRecordPageService.clearRecordPages(clearAll,clearNoOrder);
			log.info("EyeTask.clearRecordPages:执行清理录数据执行完成,日期{}~{}内无订单数据,{}天前所有数据",clearNoOrder,clearAll,clearAll);
    	}catch(Exception e){
    		log.error("EyeTask.clearRecordPages Error:",e);
			throw e;// 任务执行异常要抛出个任务管理器捕获
    	}
    }
    
    /**
     * 清除指定缓存
     * @param type
     * @modified:
     * ☆XiaoQuan(2020年7月23日 ): <br>
     */
    public void clearCache(String type){
    	redisUtil.del(type);//删除指定缓存
    	log.debug("EyeTask.clearCache:清除 {} 缓存成功...",type);
    }
}
