package ins.webeye.common.utils.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件处理工具类
 * 
 * @author webeye
 */
public class FileUtils
{
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    /**
	 * 输出指定文件的byte数组
	 * @param fileData 文件
	 * @param os 输出流
	 * @return
	 * @throws IOException
	 */
	public static void writeBytes(byte[] fileData,OutputStream os) throws IOException {
		try{
			os.write(fileData);
		}catch(IOException e){
			throw e;
		}
		finally{
			if(os!=null) os.close();
		}
	}

	/**
	 * 输出指定文件的byte数组
	 * @param filePath 文件路径
	 * @param os 输出流
	 * @return
	 */
    public static void writeBytes(String filePath, OutputStream os) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
	 * 删除文件
	 * @param filePath 文件
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		// 路径为文件且不为空则进行删除
		if(file.isFile()&&file.exists()){
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 文件名称验证
	 * @param filename 文件名称
	 * @return true 正常 false 非法
	 */
    public static boolean isValidFilename(String filename)
    {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * 下载文件名重新编码
     * 
     * @param request 请求对象
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        else if (agent.contains("Firefox"))
        {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }


    /**
     * 获取文件字节
     * @param file
     * @return
     */
    public static byte[] readFileAsByteArray(File file) {
		InputStream is = null;
		byte[] result = null;
		try{
			is = new FileInputStream(file);
			result = null;
			if(is==null||is.available()<1){
				result = new byte[0];
			}else{
				result = readStreamAsByteArray(is);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		finally{
			safeClose(is);
		}
		return result;
	}
    
    
    public static byte[] readStreamAsByteArray(InputStream inputStream) {
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
		}finally{
			try{
				inputStream.close();
				byteOut.close();
			}catch(IOException e){}
		}

		return data;
	}

	public static StringBuffer readStreamAsBuffer(InputStream inputStream) {
		StringBuffer buffer = new StringBuffer();
		try{
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
			String s = null;
			while(( s = bf.readLine() )!=null){// 使用readLine方法，一次读一行
				buffer.append(s.trim());
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		finally{
			safeClose(inputStream);
		}
		return buffer;
	}

    public static void safeClose(InputStream inputStream) {
		if(inputStream!=null){
			try{
				inputStream.close();
			}catch(IOException e){}
		}
	}

	/**
	 * 根据文件路径得到最后的文件名
	 * @param filePath
	 * @return
	 * @modified:
	 */
	public static String getFileNameByPath(String filePath) {
		String fileName = "";
		if(filePath.indexOf("/")<0){
			filePath = filePath.replace('\\','/');
		}
		fileName = filePath.substring(filePath.lastIndexOf('/')+1);
		return fileName;
	}

	public static String getFileExt(String fileFullName) {
		int idx = fileFullName.lastIndexOf(".");
		if(idx<0) return null;
		return fileFullName.substring(idx).toLowerCase();
	}

	public static String getContentType(String fileName) {
		String ext = getFileExt(fileName);
		String strContentType = "binary/octet-stream";
		if(ext==null) return strContentType;
		ext = ext.toLowerCase();
		if(ext.indexOf(".j")> -1) strContentType = "image/jpeg";
		if(ext.indexOf(".png")> -1) strContentType = "image/x-png";
		if(ext.indexOf(".g")> -1) strContentType = "image/gif";
		if(ext.indexOf(".tif")> -1) strContentType = "image/tiff";
		if(ext.indexOf(".w")> -1) strContentType = "application/msword";
		if(ext.indexOf(".xls")> -1) strContentType = "application/vnd.ms-excel";
		if(ext.indexOf(".pdf")> -1) strContentType = "application/pdf";

		return strContentType;
	}


}
