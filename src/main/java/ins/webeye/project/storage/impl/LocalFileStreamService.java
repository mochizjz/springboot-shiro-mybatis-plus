/******************************************************************************
* CREATETIME : 2020年9月9日 下午11:21:32
******************************************************************************/
package ins.webeye.project.storage.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import cn.hutool.core.io.FileUtil;
import ins.webeye.common.constant.Constants;
import ins.webeye.common.utils.file.FileUtils;
import ins.webeye.project.storage.FileStreamService;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre></pre>
 * @author ★LiuPing
 */
@Slf4j
@Service
@ConditionalOnProperty(value = "webeye.storageType", havingValue = "LOCAL")
public class LocalFileStreamService implements FileStreamService {

	/* 
	 * @see ins.webeye.project.storage.FileStreamService#getFileStream(java.lang.String, java.lang.String)
	 * @param filePath
	 * @param bucket
	 * @return
	 */
	@Override
	public byte[] readFileBytes(String filePath) {
		File recordFile = new File(filePath);
		byte[] bytes = FileUtils.readFileAsByteArray(recordFile);
		return bytes;
	}

	@Override
	public String readFileContent(String filePath) {
	    File file = new File(filePath);
	    if(!file.exists())return null;
	    BufferedReader reader = null;
	    StringBuffer sbf = new StringBuffer();
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempStr;
	        while ((tempStr = reader.readLine()) != null) {
	            sbf.append(tempStr);
	        }
	        reader.close();
	        return sbf.toString();
	    } catch (IOException e) {
			log.error(filePath+" readLocalFileContent Error:"+e.getMessage(),e);
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	    return sbf.toString();
	}


	/* 
	 * @see ins.webeye.project.storage.FileStreamService#uploadFileData(java.lang.String, java.lang.String, byte[])
	 * @param bucket
	 * @param filePath
	 * @param fileData
	 * @return
	 * @throws IOException
	 */
	@Override
	public long uploadFileData(String filePath,byte[] fileData) throws IOException {
		// 保存文件
		File file = new File(filePath);
		if( !file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		FileOutputStream outputStream = null;
		outputStream = new FileOutputStream(file);
		outputStream.write(fileData);
		outputStream.close();
		return 0;
	}

	/* 
	 * @see ins.webeye.project.storage.FileStreamService#appendData(java.lang.String, java.lang.String, byte[])
	 * @param bucket
	 * @param filePath
	 * @param fileData
	 * @return
	 * @throws IOException
	 */
	@Override
	public long appendData(String filePath,long position,String fileContent) throws IOException {
		File file = new File(filePath);
		if( !file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		FileOutputStream outputStream = null;

		try{
			if(file.exists()){// 追加文件信息
				outputStream = new FileOutputStream(file,true);// 不覆盖进行追加
			}else{
				outputStream = new FileOutputStream(file);
			}

			outputStream.write(fileContent.getBytes(Constants.UTF8));

			outputStream.close();
		}
		finally{
			if(outputStream!=null) outputStream.close();
		}

		return 9999;
	}

	@Override
	public void saveFileContent(String filePath,String fileContent) {
		File orderFile = new File(filePath);
		FileUtil.writeString(fileContent,orderFile,Constants.UTF8);
	}

	@Override
	public String moveFile(String srcFilePath,String targetFilePath) {
		log.debug(" move file from {} to {}",srcFilePath,targetFilePath);
		File targetFile = new File(targetFilePath);
		if( !targetFile.getParentFile().exists()){
			targetFile.getParentFile().mkdirs();
		}
		FileUtil.move(new File(srcFilePath),targetFile,true);
		return targetFile.getAbsolutePath();
	}

	@Override
	public boolean deleteFiles(String... deleteFiles) {
		boolean flag = false;
		for(String filePath:deleteFiles){
			File file = new File(filePath);
			if(file.isFile()&&file.exists()){
				file.delete();
				flag = true;
			}
		}

		return flag;
	}

	/*@Override
	public long replaceFileData(String filePath,String fileContent) throws IOException {
		saveFileContent(filePath,fileContent);
		return 1;
	}*/

}
