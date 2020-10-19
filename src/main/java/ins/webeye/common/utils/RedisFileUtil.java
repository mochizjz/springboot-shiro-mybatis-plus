/******************************************************************************
* CREATETIME : 2020年9月16日 上午12:06:33
******************************************************************************/
package ins.webeye.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import ins.webeye.common.constant.RedisConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre></pre>
 * @author ★LiuPing
 */
@Component
@Slf4j
public class RedisFileUtil {

	private static final String _PREFIX = "eye:filecontent:";
	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	// =========BoundListOperations 用法 start============

	public BoundListOperations<String,String> boundList(String listKey,RedisConstants.ExpireEnum expireEnum) {
		// 绑定操作
		BoundListOperations<String,String> boundValueOperations = redisTemplate.boundListOps(_PREFIX+listKey);
		// 设置过期时间
		boundValueOperations.expire(expireEnum.getTime(),expireEnum.getTimeUnit());
		return boundValueOperations;
	}

	/**
	 * 将数据添加到Redis的list中（从右边添加）
	 * @param listKey
	 * @param expireEnum 有效期的枚举类
	 * @param values 待添加的数据
	 */
	public void addToListRight(String listKey,String value) {
		// 插入数据
		if(StringUtils.isNotBlank(value)){
			redisTemplate.boundListOps(_PREFIX+listKey).rightPushAll(value);
		}
	}

	/**
	 * 取出当前的数据并清空
	 * @param listKey
	 * @return
	 * @modified:
	 */
	public List<String> proListFromLeft(String listKey) {
		BoundListOperations<String,String> boundValueOperations = redisTemplate.boundListOps(_PREFIX+listKey);
		List<String> valueList = new ArrayList<String>();
		if(boundValueOperations==null) return valueList;
		String value = null;
		while(( value = boundValueOperations.leftPop() )!=null){
			valueList.add(value);
		}
		return valueList;
	}
	/**
	 * 根据起始结束序号遍历Redis中的list
	 * @param listKey
	 * @param start 起始序号
	 * @param end 结束序号
	 * @return
	 */
	public List<String> rangeList(String listKey,long start,long end) {
		// 绑定操作
		BoundListOperations<String,String> boundValueOperations = redisTemplate.boundListOps(_PREFIX+listKey);
		// 查询数据
		return boundValueOperations.range(start,end);
	}

	/**
	 * 弹出右边的值 --- 并且移除这个值
	 * @param listKey
	 */
	public Object rifhtPop(String listKey) {
		// 绑定操作
		BoundListOperations<String,String> boundValueOperations = redisTemplate.boundListOps(_PREFIX+listKey);
		return boundValueOperations.rightPop();
	}
}
