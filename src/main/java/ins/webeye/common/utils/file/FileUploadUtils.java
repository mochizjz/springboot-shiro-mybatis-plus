package ins.webeye.common.utils.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import ins.webeye.common.constant.Constants;
import ins.webeye.common.exception.file.FileNameLengthLimitExceededException;
import ins.webeye.common.exception.file.FileSizeLimitExceededException;
import ins.webeye.common.exception.file.InvalidExtensionException;
import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.Md5Utils;
import ins.webeye.common.utils.StringUtils;
import ins.webeye.framework.config.WebEyeConfig;

/**
 * 文件上传工具类
 * 
 * @author webeye
 */
public class FileUploadUtils
{
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = WebEyeConfig.getProfile();

    private static int counter = 0;

    
	private static final String PathDay_FORMAT = "yyyyMMdd";
    private static final String PathDate_FORMAT = "yyyy/MM/dd";
 	private static final String FILE_SPLT="/";
 	
 	/**
 	 * 图片类型后缀名集合
 	 */
 	public static final String IMAGE_EXTS = "*.jpg;*.jpeg;*.png;*.gif";
 	
    public static void setDefaultBaseDir(String defaultBaseDir)
    {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir()
    {
        return defaultBaseDir;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException
    {
        try
        {
            return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException
    {
        try
        {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException 比如读写文件出错时
     * @throws InvalidExtensionException 文件校验异常
     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException
    {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension);

        String fileName = extractFilename(file);

        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        String pathFileName = getPathFileName(baseDir, fileName);
        return pathFileName;
    }

    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file)
    {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        fileName = DateUtils.datePath() + "/" + encodingFilename(fileName) + "." + extension;
        return fileName;
    }

    private static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException
    {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists())
        {
            desc.createNewFile();
        }
        return desc;
    }

    private static final String getPathFileName(String uploadDir, String fileName) throws IOException
    {
        int dirLastIndex = WebEyeConfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        String pathFileName = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
        return pathFileName;
    }

    /**
     * 编码文件名
     */
    private static final String encodingFilename(String fileName)
    {
        fileName = fileName.replace("_", " ");
        fileName = Md5Utils.hash(fileName + System.nanoTime() + counter++);
        return fileName;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws InvalidExtensionException
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException
    {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE)
        {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }

        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension))
        {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName);
            }
            else
            {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }

    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension)
    {
        for (String str : allowedExtension)
        {
            if (str.equalsIgnoreCase(extension))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     * 
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file)
    {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension))
        {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }
    
    /**
     * 通过fileName获取文件后缀
     * @param fileFullName
     * @return
     * @modified:
     * ☆XiaoQuan(2020年7月22日 ): <br>
     */
    public static String getFileExt(String fileFullName) {
		int idx = fileFullName.lastIndexOf(".");
		if(idx<0) return null;
		return fileFullName.substring(idx).toLowerCase();
	}
    
	/**
	 * 获取录制文件的上传路径，录制文件按 系统/产品/YYYYMMDD/ 的目录保存,这个目录一般只有15个，其他日期的会被清理
	 * @param productCode
	 * @param nodeCode
	 * @param token
	 * @param fileName
	 * @return
	 * @modified: ☆XiaoQuan(2020年7月22日 ): <br>
	 */
	public static final String getRecordFilePath(String sysCode,String productCode)
    {
    	StringBuffer pathBuf=new StringBuffer();
		pathBuf.append(defaultBaseDir);
		pathBuf.append(FILE_SPLT);
		pathBuf.append("recordFile");
		pathBuf.append(FILE_SPLT);
		pathBuf.append(sysCode);
		pathBuf.append(FILE_SPLT);
		pathBuf.append(productCode);
		pathBuf.append(FILE_SPLT);
		pathBuf.append(DateUtils.dateToStr(new Date(),PathDay_FORMAT));
		pathBuf.append(FILE_SPLT);
        return pathBuf.toString();
    }
    
	/**
	 * 获取已完成订单文件的上传路径，录制文件按 系统/产品/YYYY/MM/DD/token 的目录保存
	 * @param productCode
	 * @param nodeCode
	 * @param token
	 * @param fileName
	 * @return
	 * @modified: ☆XiaoQuan(2020年7月22日 ): <br>
	 */
	public static final String getOrderFilePath(String sysCode,String productCode,String token) {
		StringBuffer pathBuf = new StringBuffer();
		pathBuf.append(defaultBaseDir);
		pathBuf.append(FILE_SPLT);
		pathBuf.append("OrderFile");
		pathBuf.append(FILE_SPLT);
		pathBuf.append(sysCode);
		pathBuf.append(FILE_SPLT);
		pathBuf.append(productCode);
		pathBuf.append(FILE_SPLT);
		pathBuf.append(DateUtils.dateToStr(new Date(),PathDate_FORMAT));
		pathBuf.append(FILE_SPLT);
		pathBuf.append(token);
		pathBuf.append(FILE_SPLT);
		return pathBuf.toString();
	}
    /**
     * 版本文件管理路径
     * @param productCode
     * @param nodeCode
     * @param pageId
     * @param fileName
     * @return
     * @modified:
     * ☆XiaoQuan(2020年7月22日 ): <br>
     */
    public static final String getPathForRecordVersion(String productCode,String nodeCode,String pageId,String fileName)
    {
    	StringBuffer pathBuf=new StringBuffer();
		pathBuf.append(defaultBaseDir);
		pathBuf.append(FILE_SPLT);
		pathBuf.append("versionFile");
		pathBuf.append(FILE_SPLT);
		pathBuf.append(productCode);
		pathBuf.append(FILE_SPLT);
		pathBuf.append(nodeCode);
		pathBuf.append(FILE_SPLT);
		if(StringUtils.isNotBlank(pageId)){
			pathBuf.append(pageId);
			pathBuf.append(FILE_SPLT);
		}
		pathBuf.append(fileName);
        return pathBuf.toString();
    }
}