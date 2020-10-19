///******************************************************************************
//* CREATETIME : 2020年9月15日 下午11:58:23
//******************************************************************************/
//package test.webeye.code;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.alibaba.fastjson.JSONArray;
//
//import ins.webeye.WebEyeApplication;
//import ins.webeye.common.constant.RedisConstants;
//import ins.webeye.common.utils.RedisFileUtil;
//
///**
// * <pre></pre>
// * @author ★LiuPing
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = WebEyeApplication.class)
//public class RedisTest {
//
//	@Autowired
//	private RedisFileUtil redisUtil;
//	private String listKey = "putListTestKey";
//	@Test
//	public void putListTest1() {
//		redisUtil.boundList(listKey,RedisConstants.ExpireEnum.UNSAVE_FILE);
//		for(int i = 1; i<=9; i++ ){
//			redisUtil.addToListRight(listKey,"value_"+i);
//		}
//	}
//
//	@Test
//	public void getListTest1() {
//		List<String> valueList = redisUtil.proListFromLeft(listKey);
//		System.out.println("==getListTest1=="+JSONArray.toJSONString(valueList));
//
//	}
//
//}
