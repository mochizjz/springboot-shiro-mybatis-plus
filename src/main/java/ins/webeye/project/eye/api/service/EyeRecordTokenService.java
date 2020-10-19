/******************************************************************************
* CREATETIME : 2020年8月2日 下午6:17:11
******************************************************************************/
package ins.webeye.project.eye.api.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.ObjectId;
import ins.webeye.common.utils.RedisUtil;
import ins.webeye.project.eye.order.domain.EyeOrder;
import ins.webeye.project.eye.order.service.IEyeOrderService;
import ins.webeye.project.eye.record.domain.EyeRecordPage;
import ins.webeye.project.eye.record.service.IEyeRecordPageService;
import ins.webeye.project.eye.vo.TokenRequestVo;


/**
 * <pre></pre>
 * @author ★XiaoQuan
 * @CreateTime 2020年8月2日
 */
@Service
public class EyeRecordTokenService {

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
    private IEyeOrderService eyeOrderService;

	@Autowired
	private IEyeRecordPageService eyeRecordPageService;

	
    
	 /**
	  * 新创建token
	  * @param eyeRecordPage
	  * @return
	  */
	public EyeRecordPage createToken(TokenRequestVo requestVo) {
    	String token ="";
		String orderId = requestVo.getOrderId();
		EyeRecordPage recordPageVo = new EyeRecordPage();
		BeanUtil.copyProperties(requestVo,recordPageVo,"consumerID","consumerPWD");
    	if(StringUtils.isNotBlank(orderId)){//先查节点表-再查订单表  
			EyeRecordPage recordSelect = new EyeRecordPage();
			recordSelect.setOrderId(orderId);
			recordSelect.setProductCode(requestVo.getProductCode());
			List<EyeRecordPage> list = eyeRecordPageService.selectEyeRecordPageList(recordSelect);
        	if(list!=null&&list.size()>0){
        		token=list.get(0).getToken();

        	}else{
        		EyeOrder order = new EyeOrder();
				order.setOrderId(orderId);
				order.setProductCode(requestVo.getProductCode());
    	        List<EyeOrder> orderList = eyeOrderService.selectEyeOrderList(order);
    	        if(orderList!=null&&orderList.size()>0){
    	        	token = orderList.get(0).getToken();
    	        }
        	}
    	}
    	if(StringUtils.isBlank(token)){
			token = ObjectId.next();
    	}
		recordPageVo.setCreateTime(new Date());// 记录一个token的产生时间，用于计算play_set_time
		recordPageVo.setToken(token);
		return recordPageVo;
    }

	public Boolean saveToken(EyeRecordPage eyeRecordPage) {
		String cacheKey = eyeRecordPage.getToken()+eyeRecordPage.getProductCode();
		boolean cacheSuccess = redisUtil.set(cacheKey,eyeRecordPage);
		redisUtil.expire(cacheKey,3600);
		return cacheSuccess;
	}
	
	public void updateToken(EyeRecordPage eyeRecordPage) {
		String token = eyeRecordPage.getToken();
		String productCode = eyeRecordPage.getProductCode();
		String orderId = eyeRecordPage.getOrderId();
		String cacheKey=eyeRecordPage.getToken()+eyeRecordPage.getProductCode();
		redisUtil.set(cacheKey, eyeRecordPage);//更新缓存
		redisUtil.expire(cacheKey,3600);
		//如果三个值都不为空时,将缓存给orderId一份，防止支付时只有订单的情况
		if(StringUtils.isNotBlank(token)&&StringUtils.isNotBlank(productCode)&&StringUtils.isNotBlank(orderId)){
			if(redisUtil.hasKey(orderId)){
				redisUtil.set(orderId, eyeRecordPage);
			}else{
				redisUtil.set(orderId,eyeRecordPage);
				redisUtil.expire(orderId,3600);
			}
		}
		
	}
	
	public boolean checkToken(String token,String productCode,String orderId) {
		String cacheKey=token+productCode;
		if(redisUtil.hasKey(cacheKey)){
			return true;
		}
		if(StringUtils.isNotBlank(orderId)&&redisUtil.hasKey(orderId)){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据 token或订单号 获取缓存
	 * @param token
	 * @param productCode
	 * @param orderId
	 * @return
	 * @modified: ☆LiuPing(2020年8月31日 上午1:07:19): <br>
	 */
	public EyeRecordPage getTokenCache(String token,String productCode,String orderId) {

		EyeRecordPage recordPageCache = null;
		if(StringUtils.isNotBlank(orderId)){// 优先订单号获取缓存
			recordPageCache = (EyeRecordPage)redisUtil.get(orderId);
		}
		// 如果根据token没有得到，再根据订单号获取一次
		if(recordPageCache==null&&StringUtils.isNotBlank(token)){
			String cacheKey = token+productCode;
			recordPageCache = (EyeRecordPage)redisUtil.get(cacheKey);
		}

		// token 过期后的补救
		if(recordPageCache==null&&StringUtils.isNotBlank(orderId)){
			EyeRecordPage recordSelect = new EyeRecordPage();
			recordSelect.setOrderId(orderId);
			List<EyeRecordPage> list = eyeRecordPageService.selectEyeRecordPageList(recordSelect);
			if(list!=null&&list.size()>0){
				recordPageCache = list.get(0);
				redisUtil.set(orderId,recordPageCache);
				redisUtil.expire(orderId,3600);
			}
		}

		return  recordPageCache;
	}
	
	
	public void clearTokenCache(String token,String productCode,String orderId) {

		if(StringUtils.isNotBlank(orderId)){// 优先订单号获取缓存
			redisUtil.del(orderId);
		}
		// 如果根据token没有得到，再根据订单号获取一次
		if(StringUtils.isNotBlank(token)){
			String cacheKey = token+productCode;
			redisUtil.del(cacheKey);
		}
	}
	
}
