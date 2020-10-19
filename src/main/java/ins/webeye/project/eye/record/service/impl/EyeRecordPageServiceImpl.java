package ins.webeye.project.eye.record.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.text.Convert;
import ins.webeye.framework.config.WebEyeConfig;
import ins.webeye.project.eye.record.domain.EyeRecordClearReport;
import ins.webeye.project.eye.record.domain.EyeRecordPage;
import ins.webeye.project.eye.record.domain.EyeRecordPageHis;
import ins.webeye.project.eye.record.mapper.EyeRecordPageMapper;
import ins.webeye.project.eye.record.service.IEyeRecordClearReportService;
import ins.webeye.project.eye.record.service.IEyeRecordPageHisService;
import ins.webeye.project.eye.record.service.IEyeRecordPageService;
import ins.webeye.project.storage.FileStreamService;
import lombok.extern.slf4j.Slf4j;

/**
 * 页面录制记录Service业务层处理
 * 
 * @author xiaoquan
 * @date 2020-07-15
 */
@Service
@Slf4j
public class EyeRecordPageServiceImpl implements IEyeRecordPageService 
{
    @Autowired
    private EyeRecordPageMapper eyeRecordPageMapper;
    @Autowired
    private IEyeRecordClearReportService eyeRecordClearReportService;
    @Autowired
    private IEyeRecordPageHisService eyeRecordPageHisService;
	@Autowired
	private FileStreamService fileStreamService;
	@Autowired
	private WebEyeConfig webEyeConfig;
    /**
     * 查询页面录制记录
     * 
     * @param id 页面录制记录ID
     * @return 页面录制记录
     */
    @Override
    public EyeRecordPage selectEyeRecordPageById(Long id)
    {
        return eyeRecordPageMapper.selectEyeRecordPageById(id);
    }

    /**
     * 查询页面录制记录列表
     * 
     * @param eyeRecordPage 页面录制记录
     * @return 页面录制记录
     */
    @Override
    public List<EyeRecordPage> selectEyeRecordPageList(EyeRecordPage eyeRecordPage)
    {
        return eyeRecordPageMapper.selectEyeRecordPageList(eyeRecordPage);
    }

    /**
     * 新增页面录制记录
     * 
     * @param eyeRecordPage 页面录制记录
     * @return 结果
     */
    @Override
    public int insertEyeRecordPage(EyeRecordPage eyeRecordPage)
    {
        eyeRecordPage.setCreateTime(DateUtils.getNowDate());
        //eyeRecordPage.setCreateUser(ShiroUtils.getLoginName());
        return eyeRecordPageMapper.insertEyeRecordPage(eyeRecordPage);
    }

    /**
     * 修改页面录制记录
     * 
     * @param eyeRecordPage 页面录制记录
     * @return 结果
     */
    @Override
    public int updateEyeRecordPage(EyeRecordPage eyeRecordPage)
    {
        eyeRecordPage.setUpdateTime(DateUtils.getNowDate());

		if(eyeRecordPage.getId()!=null){
			return eyeRecordPageMapper.updateEyeRecordPage(eyeRecordPage);
		}else if(StringUtils.isNotBlank(eyeRecordPage.getOrderId())){
			return eyeRecordPageMapper.updateEyeRecordPageByOrderId(eyeRecordPage);
		}
		return 0;
	}

    @Override
    public int updateEyeRecordPageByToken(EyeRecordPage eyeRecordPage)
    {
    	return eyeRecordPageMapper.updateEyeRecordPageByToken(eyeRecordPage);
    }
    /**
     * 删除页面录制记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEyeRecordPageByIds(String ids)
    {
        return eyeRecordPageMapper.deleteEyeRecordPageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除页面录制记录信息
     * 
     * @param id 页面录制记录ID
     * @return 结果
     */
    @Override
    public int deleteEyeRecordPageById(Long id)
    {
        return eyeRecordPageMapper.deleteEyeRecordPageById(id);
    }

    
	/* 
	 * @param clearAll    清理clearAll天前的所有数据，包含已生成订单的
	 * @param clearNoOrder  清理clearNoOrder天前，没有生成订单号的数据
	 */
    @Override
    public void clearRecordPages(Integer clearAll,Integer clearNoOrder)
    {
    	//查询需要清理的数据
    	List<String> clearTokenList = new ArrayList<String>();
    	//先处理七天前的
    	Map<String, Object> map = new HashMap<>();
    	Date clearAllTime = DateUtil.offsetDay(new Date(),-clearAll);//7天前
    	map.put("endTime", clearAllTime); 
    	clearTokenList = eyeRecordPageMapper.getClearTokenList(map);
    	if(clearTokenList!=null&&clearTokenList.size()>0){
			log.info("清理{}之前的所有数据（含已生成订单的)，共{}行",DateUtils.dateToStr(clearAllTime,DateUtils.YYYY_MM_DD),clearTokenList.size());
			clearRecordPages(clearTokenList);
		}else{
			log.info("清理{}之前的所有数据（含已生成订单的)，共0行",DateUtils.dateToStr(clearAllTime,DateUtils.YYYY_MM_DD));
    	}
    	//再处理1~7天
    	Date clearNoOrderTime = DateUtil.offsetDay(new Date(),-clearNoOrder);//1天前
        map.put("startTime", clearAllTime); 	 // 开始时间
        map.put("endTime", clearNoOrderTime);	 // 结束时间
		map.put("NoOrder","true"); // 这个是排除到有订单号的条件
        clearTokenList = eyeRecordPageMapper.getClearTokenList(map);

        if(clearTokenList!=null&&clearTokenList.size()>0){
			log.info("清理{}~{}天未生成订单号的数据 ，共{}行",clearNoOrder,clearAll,clearTokenList.size());
			clearRecordPages(clearTokenList);
		}else{
			log.info("清理{}~{}天未生成订单号的数据 ，共{}行",clearNoOrder,clearAll,0);
    	}
    }
    
    /**
     * 通过tokenList进行清理并记录录制轨迹
     * @param clearTokenList
     * @modified:
     * ☆XiaoQuan(2020年7月20日 ): <br>
     */
    private void clearRecordPages(List<String> clearTokenList){
    	//清理数据---清理文件--记录数据踪迹
		log.debug("清理文件--记录数据踪迹 clearRecordPages.size:{}",clearTokenList.size());
    	EyeRecordPage eyeRecordPage = new EyeRecordPage();
    	for(String token:clearTokenList){//根据token依次进行清理
			EyeRecordClearReport eyeRecordClearReport = new EyeRecordClearReport();
        	String actionPath = "";		 //录制轨迹
			String orderId = null; // 订单号
    		eyeRecordPage.setToken(token);
    		List<EyeRecordPage> eyeRecordPageList = eyeRecordPageMapper.selectClearEyeRecordPageList(eyeRecordPage);
    		EyeRecordPage recordPage = eyeRecordPageList.get(0);
    		eyeRecordClearReport.setProductCode(recordPage.getProductCode());
			eyeRecordClearReport.setProductName(recordPage.getProductName());
			eyeRecordClearReport.setUserId(recordPage.getUserId());
			eyeRecordClearReport.setSysCode(recordPage.getSysCode());
    		for(EyeRecordPage clearRecord:eyeRecordPageList){
    			int recordTime = clearRecord.getRecordTime()==null?0:clearRecord.getRecordTime();
				if(actionPath.length()<1000){// 控制长度
					actionPath = actionPath+clearRecord.getNodeName()+"("+recordTime+"s)→";
				}
    			if(StringUtils.isNotBlank(clearRecord.getOrderId())&&StringUtils.isBlank(orderId))orderId = clearRecord.getOrderId();

    			eyeRecordClearReport.setRecordTime(clearRecord.getBeginTime());//最后录制时间

                //获取配置文件  个性化设置删除后是否需要存储
				if(webEyeConfig.isClearToHis()){// 要将数据转移到备份表，不能删除文件
                    EyeRecordPageHis eyeRecordPageHis = new EyeRecordPageHis();
                    BeanUtil.copyProperties(clearRecord,eyeRecordPageHis);
                    eyeRecordPageHisService.copyToEyeRecordPageHis(eyeRecordPageHis);
				}else{
					if(StringUtils.isNotBlank(clearRecord.getFilePath())){
						fileStreamService.deleteFiles(clearRecord.getFilePath());// 删除文件
						log.debug("clearRecordPages.deleteFile:{}",clearRecord.getFilePath());
					}
                }
				deleteEyeRecordPageById(clearRecord.getId());// 删除录制的数据
    		}
			if(StringUtils.isNotBlank(actionPath))actionPath=actionPath.substring(0,actionPath.length()-1);
			eyeRecordClearReport.setToken(token);
			eyeRecordClearReport.setOrderId(orderId);
			eyeRecordClearReport.setActionPath(actionPath);
			log.debug("clearRecordPages.insertEyeRecordClearReport:token={}",token);
			eyeRecordClearReportService.insertEyeRecordClearReport(eyeRecordClearReport);//插入记录表
    	}
    }
    
}
