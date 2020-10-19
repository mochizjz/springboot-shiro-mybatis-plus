/******************************************************************************
* CREATETIME : 2020年9月14日 上午12:40:01
******************************************************************************/
package ins.webeye.project.eye.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.constant.RedisConstants;
import ins.webeye.common.utils.RedisUtil;
import ins.webeye.project.eye.order.mapper.EyeOrderMapper;
import ins.webeye.project.eye.order.mapper.EyeOrderPageMapper;
import ins.webeye.project.eye.record.mapper.EyeRecordPageMapper;
import ins.webeye.project.eye.report.vo.IndexReportVo;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre></pre>
 * @author ★LiuPing
 */
@Service
@Slf4j
public class EyeReportService {

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private EyeOrderMapper eyeOrderMapper;
	@Autowired
	private EyeRecordPageMapper eyeRecordPageMapper;
	@Autowired
	private EyeOrderPageMapper eyeOrderPageMapper;

	public IndexReportVo getIndexReport() {

		IndexReportVo reportVo = (IndexReportVo)redisUtil.get(RedisConstants.INDEX_REPORT_CACHE);
		if(reportVo==null){
			reportVo = new IndexReportVo();
			// 当日已完成订单数量
			int orderDayNum = eyeOrderMapper.selectOrderDayNum();
			// 当日录制总数量
			int recordNum = eyeRecordPageMapper.selectRecordNum();

			int orderPageNum = eyeOrderPageMapper.selectOrderPageNum();
			recordNum = orderPageNum+recordNum;
			// 当月已完成订单数量
			int orderMonthNum = eyeOrderMapper.selectOrderMonthNum();
			// 当天质检不通过的订单
			int orderDayFailNum = eyeOrderMapper.selectOrderDayFailNum();

			reportVo.setRecordNum(recordNum);
			reportVo.setOrderDayNum(orderDayNum);
			reportVo.setOrderMonthNum(orderMonthNum);
			reportVo.setOrderDayFailNum(orderDayFailNum);
			// 缓存10分钟
			redisUtil.set(RedisConstants.INDEX_REPORT_CACHE,reportVo);
			redisUtil.expire(RedisConstants.INDEX_REPORT_CACHE,60*10);
		}
		return reportVo;
	}

}
