//package test.webeye.code;
//
//import org.junit.Test;
//
//import cn.hutool.core.util.RandomUtil;
//import ins.webeye.common.utils.file.FileUtils;
//
//
//public class ToolTest {
//
//	@Test
//	public void test() {
//		System.out.println(RandomUtil.randomString(16));
//
//		System.out.println(FileUtils.getFileNameByPath("\\xx\\yy.jpg"));
//		System.out.println(FileUtils.getFileNameByPath("/xx/yy.jpg"));
//		String relatProdCode = "123*";
//		relatProdCode = relatProdCode.substring(0,relatProdCode.length()-1);
//		System.out.println(relatProdCode);
//		String ip = "117.136.40.176,12111";
//		if(ip!=null&&ip.indexOf(',')>0){
//			// 得到了多个IP，取第一个
//			ip = ip.substring(0,ip.indexOf(','));
//		}
//		System.out.println(ip);
//	}
//
//}
