/**
 * Date: 2019/11/13
 * Author: Zhengja
 */
package ins.webeye.common.constant;

import java.util.concurrent.TimeUnit;

public final class RedisConstants {

	static final String _PREFIX = "eye_";

    //缓存组
	public static final class EntityCacheKey {

		public static final String QUERY = _PREFIX+"query";
		public static final String MANAGE = _PREFIX+"manage";
		public static final String OPINION = _PREFIX+"opinion";
		public static final String POINT = _PREFIX+"point";
		public static final String REPORTBIZ = _PREFIX+"reportbiz";
		public static final String REPORTDATA = _PREFIX+"reportdata";
		public static final String RESOURCE = _PREFIX+"resource";
		public static final String TASK = _PREFIX+"task";
	}
	
	/** 首页报表的缓存 */
	public static final String INDEX_REPORT_CACHE = "sys:Index_Report";
	
	/** 首页报表的缓存 */
	public static final String PAGE_NODE_CACHE = "sys:PAGE_NODE_CONFIG";

	/** IP 对应中文地址的缓存，减少调用次数 */
	public static final String IP_NAME_CACHE = "sys:IP_Name";
	// 需要替换的文件 缓存
	public static final String FILE_PATH_CACHE = "File_Path:";
	// 等待替换的url
	public static final String FILE_URL_CACHE = "sys:File_URL";
	// 已经进行替换的html原文件
	public static final String FILE_HTML_CACHE = "sys:File_Html";
	// 文件录制过程中Token的环境
	// public static final String ReCode_Token_CACHE = "RecordTokenCache";

    //临时缓存 key
    //public static final String TEMP_REGION_IMPORT_KEY = _PREFIX+"temp:region:import";

	/**
     * 过期时间相关枚举
     */
    public static enum ExpireEnum{

		/** 未读消息的有效期为30天 */
		UNREAD_MSG(30L,TimeUnit.DAYS),
		/** 前端前的未保存文件,1分钟 */
		UNSAVE_FILE(1L,TimeUnit.MINUTES)
        ;

        /**
         * 过期时间
         */
        private Long time;
        /**
         * 时间单位
         */
        private TimeUnit timeUnit;

        ExpireEnum(Long time, TimeUnit timeUnit) {
            this.time = time;
            this.timeUnit = timeUnit;
        }

        public Long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
	}
}