/******************************************************************************
* Copyright 2010-2011 the original author or authors.
* CREATETIME : 2014-1-10 上午11:56:49
******************************************************************************/
package ins.webeye.project.storage;

import java.io.IOException;


/**
 * 文件流操作接口
 * @Copyright Copyright (c) 2014
 * @Company www.sinosoft.com.cn
 * @author ★<a href="mailto:liuping-gz@sinosoft.com.cn">LiuPing</a>
 * @since 2014-1-10 上午11:56:49
 */
public interface FileStreamService {



	/**
	 * 上传影像文件
	 * @param filePath 文件保存路径
	 * @param imgID 文件id
	 * @param uploadFileData 文件数据
	 * @return 上传成功返回上传用时间,-1上传失败
	 * @modified: ☆LiuPing(2014-1-13 下午01:25:15): <br>
	 */
	public long uploadFileData(String filePath,byte[] fileData) throws IOException;
	
	/**
	 * 追加文件内容
	 * @param bucket
	 * @param filePath
	 * @param fileContent
	 * @return
	 * @throws IOException
	 * @modified: ☆LiuPing(2020年9月9日 下午11:20:03): <br>
	 */
	public long appendData(String filePath,long position,String fileContent) throws IOException;

	/**
	 * 读取文件内容
	 * @param filePath
	 * @return
	 * @modified:
	 */
	public String readFileContent(String filePath);

	/**
	 * 读取文件字节
	 * @param path
	 * @return
	 * @modified:
	 */
	public byte[] readFileBytes(String filePath);

	/**
	 * 保存一个文本文件
	 * @param filePath
	 * @param fileContent
	 */
	public void saveFileContent(String filePath,String fileContent);

	/**
	 * 文件转移
	 * @param srcFilePath
	 * @param targetFilePath
	 * @return
	 * @modified: ☆LiuPing(2020年9月13日 下午5:03:12): <br>
	 */
	public String moveFile(String srcFilePath,String targetFilePath);

	/**
	 * 删除多个文件
	 * @param deleteFiles
	 * @modified:
	 */
	public boolean deleteFiles(String... deleteFiles);

	/**
	 * URL 被替换后，重写文件
	 * @param path
	 * @param bytes
	 * @modified:
	 */
	// public long replaceFileData(String filePath,String fileContent) throws IOException;

}
