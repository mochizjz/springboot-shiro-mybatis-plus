package ins.webeye.common.utils.file;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * gzip 压缩,解压缩工具类(自己改写)
 */
public class GZIPUtil {

	public static final String DEFAULT_GZIP_CHARSET = "UTF-8";

	/**
	 * 压缩字符串为byte数组 默认使用 utf-8 编码
	 */
	public static byte[] compress(String str) {
		return compress(str, DEFAULT_GZIP_CHARSET);
	}

	/**
	 * 压缩字符串为byte数组
	 * @param str 要进行压缩的字符串
	 * @param charset 字符集
	 */
	public static byte[] compress(String str, String charset) {
		if (str == null || str.trim().length() == 0) {
			return null;
		}
		ByteArrayOutputStream byteArrayOutputStream = null;
		GZIPOutputStream gzipOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
			IOUtils.write(str.getBytes(charset), gzipOutputStream);
			gzipOutputStream.close();//重要 否则byteArrayOutputStream返回的数据不完整 将剩余压缩数据写入输出流并关闭底层流。继续自DeflaterOutputStream
			return byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(byteArrayOutputStream);
		}
		return null;
	}

	/**
	 * 解压gzip格式的byte数组 默认使用 utf-8 编码
	 */
	public static String uncompress(byte[] bytes) {
		return uncompress(bytes, DEFAULT_GZIP_CHARSET);
	}

	/**
	 * 解压gzip格式的byte数组
	 * @param bytes gzip格式byte数组
	 * @param charset 字符集
	 */
	public static String uncompress(byte[] bytes, String charset) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		ByteArrayOutputStream byteArrayOutputStream = null;
		ByteArrayInputStream byteArrayInputStream = null;
		GZIPInputStream gzipInputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			byteArrayInputStream = new ByteArrayInputStream(bytes);
			gzipInputStream = new GZIPInputStream(byteArrayInputStream);
			//使用org.apache.commons.io.IOUtils 简化流的操作
			IOUtils.copy(gzipInputStream, byteArrayOutputStream);
			return byteArrayOutputStream.toString(charset);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//释放流资源
			IOUtils.closeQuietly(gzipInputStream);
			IOUtils.closeQuietly(byteArrayInputStream);
			IOUtils.closeQuietly(byteArrayOutputStream);
		}
		return null;
	}

	public static String getZipStream(HttpServletRequest request){
		String params = "";
		try {
			//获取输入流
			BufferedReader reader = request.getReader();
			//将输入流中的请求实体转换为byte数组,进行gzip解压
			byte[] bytes = IOUtils.toByteArray(reader, "iso-8859-1");
			//对 bytes 数组进行解压
			params = GZIPUtil.uncompress(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return params;
	}
}
