///******************************************************************************
//* CREATETIME : 2020年7月19日 上午10:33:17
//******************************************************************************/
//package test.webeye.api;
//
//import org.junit.Test;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//
//import ins.webeye.common.utils.http.HttpUtils;
//import ins.webeye.project.eye.vo.OrderInfoVo;
//import ins.webeye.project.eye.vo.TokenRequestVo;
//
///**
// * <pre></pre>
// * @author ★LiuPing
// */
//public class EyeApiTest {
//
//	@Test
//	public void testGetToken() {
//		String webeyeUrl = "http://127.0.0.1/webeye/api/getToken";
//		TokenRequestVo requestVo = new TokenRequestVo();
//		requestVo.setSystemCode("esales-pc");
//		requestVo.setSystemName("网销PC端");
//		requestVo.setConsumerID("eyetest");
//		requestVo.setConsumerPWD("test1234");
//		requestVo.setProductCode("6102_v3");
//		requestVo.setProductName("轻松筹压力测试");
//		JSONObject result = HttpUtils.sendPostJson(webeyeUrl,requestVo);
//		System.out.println(result.toString(SerializerFeature.PrettyFormat));
//	}
//
//	@Test
//	public void testGetTokenQSC() {
//		String webeyeUrl = "https://backtracking.qsebao.com/webeye/api/getToken";
//		TokenRequestVo requestVo = new TokenRequestVo();
//		requestVo.setSystemCode("QSCWX");
//		requestVo.setConsumerID("qschou");
//		requestVo.setConsumerPWD("1082b97f7dd85a8097d93aee05b63ae7");
//		requestVo.setProductCode("6102_v3");
//		requestVo.setProductName("轻松筹压力测试");
//		JSONObject result = HttpUtils.sendPostJson(webeyeUrl,requestVo);
//		System.out.println(result.toString(SerializerFeature.PrettyFormat));
//	}
//
//	@Test
//	public void testSyncOrderInfo() {
//		// String webeyeUrl = "http://127.0.0.1/webeye/api/syncOrderInfo";
//		String webeyeUrl = "http://eye.h5img.com:8866/webeye/api/syncOrderInfo";
//		OrderInfoVo requestVo = new OrderInfoVo();
//		requestVo.setOrderId("240120202010032932904");
//		requestVo.setPayStatus("1");
//		requestVo.setConsumerID("eyetest");
//		requestVo.setConsumerPWD("test1234");
//		requestVo.setProductCode("6102_v3");
//		requestVo.setPolicyNo("PDAATEST2020-11212333");
//		requestVo.setPolicyName("投保人test2");
//		requestVo.setPolicyIdcard("4312281986082153");
//		JSONObject result = HttpUtils.sendPostJson(webeyeUrl,requestVo);
//		System.out.println(result.toString(SerializerFeature.PrettyFormat));
//	}
//
//	@Test
//	public void testSyncOrderInfo2() {
//		String webeyeUrl = "http://127.0.0.1/webeye/api/syncOrderInfo";
//		// String webeyeUrl = "http://eye.h5img.com:8866/webeye/api/syncOrderInfo";
//		OrderInfoVo requestVo = new OrderInfoVo();
//		requestVo.setOrderId("240120202312120357295");
//		requestVo.setPayStatus("1");
//		requestVo.setSystemCode("xiandai-h5");
//		requestVo.setConsumerID("xiandai-test");
//		requestVo.setConsumerPWD("32ea8a19bc56abd927ee37dc19d4081b");
//		requestVo.setProductCode("6103_vue");
//		requestVo.setPolicyNo("PDAATEST2020-112125723");
//		requestVo.setPolicyName("投保人test56");
//		requestVo.setPolicyIdcard("4312281986082122571");
//		JSONObject result = HttpUtils.sendPostJson(webeyeUrl,requestVo);
//		System.out.println(result.toString(SerializerFeature.PrettyFormat));
//	}
//
//	@Test
//	public void testSyncOrderInfoQSC() {
//		String webeyeUrl = "https://backtracking-admin.qingsongchou.net/";
//		// String webeyeUrl = "http://eye.h5img.com:8866/webeye/api/syncOrderInfo";
//		OrderInfoVo requestVo = new OrderInfoVo();
//		requestVo.setOrderId("qa202009010001881662758009_t");
//		requestVo.setPayStatus("0");
//		requestVo.setSystemCode("QSCWX");
//		requestVo.setConsumerID("qschou");
//		requestVo.setConsumerPWD("1082b97f7dd85a8097d93aee05b63ae7");
//		requestVo.setProductCode("taikang-health-201901-123activityB_taikang-health-201901-123land-pageB");
//		requestVo.setPolicyNo("PDAATEST2020-1121255");
//		requestVo.setPolicyName("投保人test55");
//		requestVo.setPolicyIdcard("431228198608212255");
//		JSONObject result = HttpUtils.sendPostJson(webeyeUrl,requestVo);
//		System.out.println(result.toString(SerializerFeature.PrettyFormat));
//	}
//
//
//}
