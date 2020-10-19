package ins.webeye.project.storage.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

public class OssClientUtils {

	private static OSS ossClient = null;
	// 以下参数放到了静态变量，系统配置要重启才能生效
	private static String bucket = null;
	private static String endpoint = null;
	private static String accessKeyID = null;
	private static String accessKeySecret = null;

	public static OSS initOssClient(String endpoint,String accessKeyID,String accessKeySecret,String bucket2) {
		bucket = bucket2;
		ossClient = new OSSClientBuilder().build(endpoint,accessKeyID,accessKeySecret);
		return ossClient;
	}

	public static OSS getOssClient() {
		if(ossClient==null&&accessKeyID!=null){
			// 静态变量中有值，自己初始化一次
			initOssClient(endpoint,accessKeyID,accessKeySecret,bucket);
		}
		return ossClient;
	}

	public static String getBucket() {
		return bucket;
	}

	public static void colseOssClient() {
		if(ossClient!=null){
			ossClient.shutdown();
			ossClient = null;
		}
	}
}
