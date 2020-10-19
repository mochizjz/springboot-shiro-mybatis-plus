/******************************************************************************
* CREATETIME : 2020年9月9日 下午11:22:21
******************************************************************************/
package ins.webeye.project.storage.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.AppendObjectRequest;
import com.aliyun.oss.model.AppendObjectResult;
import com.aliyun.oss.model.CopyObjectResult;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;

import ins.webeye.project.storage.FileStreamService;
import ins.webeye.project.storage.utils.OssClientUtils;
import ins.webeye.project.system.config.service.IConfigService;
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云文件存储服务
 * @author ★LiuPing
 */
@Slf4j
@Service
@ConditionalOnProperty(value = "webeye.storageType", havingValue = "OSS")
public class OssFileStreamService implements FileStreamService {

	@Autowired
	private IConfigService configService;


	/**
	 * 获取Client
	 * @return
	 * @throws Exception
	 */
	private OSS getClient() {

		OSS client = OssClientUtils.getOssClient();
		if(client==null){
			/** 绑定的域名 */
			String endpoint = configService.selectConfigByKey("oss.endpoint");
			/** 访问密钥之ID */
			String accessKeyID = configService.selectConfigByKey("oss.AccessKeyID");
			/** 访问密钥之Sercret */
			String accessKeySecret = configService.selectConfigByKey("oss.AccessKeySecret");
			String bucket = configService.selectConfigByKey("oss.bucket");
			client = OssClientUtils.initOssClient(endpoint,accessKeyID,accessKeySecret,bucket);
		}
		return client;

	}

	private void colseOssClient() {
		// OssClientUtils.colseOssClient(); 静态的oss客户端全局调用，感觉不需要关闭
	}

	@Override
	public long uploadFileData(String filePath,byte[] fileData) throws IOException {
		ByteArrayInputStream fileStream = null;
		try{
			log.debug("OSS.uploadFileData:>>>>>>fileKey={},length={}",filePath,fileData.length);
			fileStream = new ByteArrayInputStream(fileData);
			getClient().putObject(OssClientUtils.getBucket(),filePath,fileStream);
		}
		finally{
			fileStream.close();
			colseOssClient();
		}
		return 1;
	}

	@Override
	public long appendData(String filePath,long position,String fileContent) throws IOException {
		OSS client = getClient();

		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType("text/plain");
		byte[] fileData = fileContent.getBytes();
		AppendObjectRequest appendObjectRequest = new AppendObjectRequest(
				OssClientUtils.getBucket(),filePath,new ByteArrayInputStream(fileData),meta);
		appendObjectRequest.setPosition(position);
		log.debug("OSS.appendData:START>>>>>>fileKey={},position={},length={}",filePath,position,fileData.length);
		AppendObjectResult appendObjectResult = client.appendObject(appendObjectRequest);
		long newposition = appendObjectResult.getNextPosition();
		log.debug("OSS.appendData:END>>>>>>>>fileKey={},endPosition={}",filePath,newposition);
		this.colseOssClient();
		return newposition;
	}

	@Override
	public String readFileContent(String filePath) {
		OSSObject ossObject = getClient().getObject(OssClientUtils.getBucket(),filePath);
		BufferedReader reader = null;

		StringBuffer sbf = new StringBuffer();
		try{
			reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
			String line;
			while(( line = reader.readLine() )!=null){
				sbf.append(line);
			}
			reader.close();
			return sbf.toString();
		}catch(IOException e){
			log.error(filePath+"readOSSFileContent Error:"+e.getMessage(),e);
		}
		finally{
			try{
				if(reader!=null) reader.close();
				this.colseOssClient();
			}catch(IOException e1){
				e1.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public byte[] readFileBytes(String filePath) {
		OSSObject ossObject = getClient().getObject(OssClientUtils.getBucket(),filePath);
		InputStream inputStream = ossObject.getObjectContent();
		byte[] data = null;
		if(inputStream==null){
			return data;
		}
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		byte[] b = new byte[4096];
		try{
			int read = 0;
			while(( read = inputStream.read(b) )>0){
				byteOut.write(b,0,read);
			}
			data = byteOut.toByteArray();

		}catch(IOException e){
			throw new RuntimeException(e);
		}
		finally{
			try{
				inputStream.close();
				byteOut.close();
				this.colseOssClient();
			}catch(IOException e){}
		}

		return data;
	}

	@Override
	public void saveFileContent(String filePath,String fileContent) {
		log.debug("OSS.saveFileContent:>>>>>>fileKey={},length={}",filePath,fileContent.length());
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType("text/plain");
		PutObjectRequest putObjectRequest = new PutObjectRequest(
				OssClientUtils.getBucket(),filePath,new ByteArrayInputStream(fileContent.getBytes()),meta);
		getClient().putObject(putObjectRequest);
		this.colseOssClient();
	}

	@Override
	public String moveFile(String srcFilePath,String targetFilePath) {
		CopyObjectResult result = getClient().copyObject(OssClientUtils.getBucket(),srcFilePath,OssClientUtils.getBucket(),targetFilePath);
		log.debug(" Copy Object ETag:{}, LastModified:{}",result.getETag(),result.getLastModified());
		getClient().deleteObject(OssClientUtils.getBucket(),srcFilePath);
		this.colseOssClient();
		return targetFilePath;
	}

	@Override
	public boolean deleteFiles(String... deleteFiles) {
		boolean flag = false;
		OSS client = getClient();
		for(String filePath:deleteFiles){
			client.deleteObject(OssClientUtils.getBucket(),filePath);
			flag = true;
		}

		return flag;

	}

	/*@Override
	public long replaceFileData(String filePath,String fileContent) throws IOException {
		log.debug("OSS.replaceFileData:>>>>>>fileKey={},length={}",filePath,fileContent.length());
		getClient().deleteObject(OssClientUtils.getBucket(),filePath);
		return appendData(filePath,0L,fileContent);
	}*/

}
