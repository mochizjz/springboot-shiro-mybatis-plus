/*
 Navicat Premium Data Transfer

 Source Server         : local_mysql_h5img
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : web_eye_db

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 31/08/2020 08:40:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for eye_check_log
-- ----------------------------
DROP TABLE IF EXISTS `eye_check_log`;
CREATE TABLE `eye_check_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `eye_order_id` bigint(20) NOT NULL COMMENT '订单表主键',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `policy_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保单号',
  `check_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '质检结果，0-质检通过，1-质检未通过',
  `check_message` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '质检结果描述',
  `check_time` datetime(0) NULL DEFAULT NULL COMMENT '质检时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常）',
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '质检轨迹表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eye_check_log
-- ----------------------------

-- ----------------------------
-- Table structure for eye_client_config
-- ----------------------------
DROP TABLE IF EXISTS `eye_client_config`;
CREATE TABLE `eye_client_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统/渠道代码',
  `client_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统/渠道名称',
  `client_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终端类型',
  `client_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端域名，多个用逗号或者回车隔开，用于跨域验证',
  `sdk_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用的SDK类型',
  `auto_start` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否自动开始录制，Y-是，N-否 字典sys_yes_no',
  `contact_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_mail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人邮箱，出现未知异常时会发邮件给联系人，多个人逗号隔开',
  `https_only` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SDK强制HTTPS',
  `authorize_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口授权ID',
  `authorize_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口授权密钥',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常，1-停用）字典sys_normal_disable',
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 380 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '对接的系统或渠道配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eye_client_config
-- ----------------------------
INSERT INTO `eye_client_config` VALUES (364, 'dhic-pc', '鼎和保险网销', 'PC', '*', 'G', 'N', '鼎和对接测试', 'dhic-test@126.com', 'N', 'dhic-test', '9a1a3f44c139267d9af04d8050f84f15', '0', 'admin', '2020-07-19 21:30:41', '', '2020-07-20 12:56:49', '2ad267', NULL);
INSERT INTO `eye_client_config` VALUES (366, 'TSWX', '泰山微信', 'WX_H5', '*', 'S', 'Y', '泰山保险测试', 'taishang@126.com', 'N', 'taishan-test', '4083e100fada791f7332709937ac9d1d', '0', 'admin', '2020-07-20 15:28:53', 'admin', '2020-08-31 00:21:22', 'ba9b32', '');
INSERT INTO `eye_client_config` VALUES (368, 'huatai-test', '华泰测试', 'WX_H5', '*', 'G', 'N', '华泰测试', 'huatai@126.com', 'N', 'huatai-test', '94d320d0b4d53eda60305555e0e0450e', '0', 'admin', '2020-07-22 10:56:31', '', '2020-08-11 20:38:46', '46d2a3', NULL);
INSERT INTO `eye_client_config` VALUES (369, 'xiandai-h5', '现代保险微信', 'WX_H5', '*', 'G', 'N', '现代保险', '1234567@sinosoft.co', 'N', 'xiandai-test', '32ea8a19bc56abd927ee37dc19d4081b', '0', 'admin', '2020-07-22 15:37:33', '', '2020-08-29 15:46:49', '4503da', NULL);
INSERT INTO `eye_client_config` VALUES (371, 'chengtai-pc', '诚泰保险网销', 'PC', '*', 'G', 'N', '诚泰保险', '12345678@sinosoft.co', 'N', 'chengtai-test', '5d97c48302df96d8eecde8ccb8840648', '0', 'admin', '2020-07-31 09:43:16', '', '2020-08-28 22:37:34', '6ed8cf', NULL);
INSERT INTO `eye_client_config` VALUES (372, 'QSCWX', '轻松筹微信', 'WX_H5', '*', 'S', 'N', '轻松筹测试', 'qschou@vip.com', 'N', 'qschou', '1082b97f7dd85a8097d93aee05b63ae7', '0', 'admin', '2020-08-02 22:33:00', 'admin', '2020-08-30 11:45:04', 'c92d8d', '');
INSERT INTO `eye_client_config` VALUES (375, 'huatai', '华泰测试', 'WX_H5', '*', 'G', 'N', '华泰测试', '9123@qq.com', 'N', 'huatai', '2aef5f1a483d13f6592326d744eee5de', '0', 'admin', '2020-08-05 09:40:39', '', '2020-08-05 09:48:01', '59999e', NULL);
INSERT INTO `eye_client_config` VALUES (377, 'anda-wx', '安达保险微信', 'WX_H5', '*', 'G', 'N', '安达测试', '19331234@qq.com', 'N', 'anda-test', '6d1e6da27ff84b75a875e6d5452a449a', '0', 'admin', '2020-08-17 16:19:54', 'admin', '2020-08-26 10:26:10', 'fbba8f', '');
INSERT INTO `eye_client_config` VALUES (378, 'xinan-test', '鑫安保险', 'WX_H5', '*', 'G', 'N', '鑫安保险测试', 'xinan@vip.com', 'N', 'xinan-test', '5194a9644b5550a2889edb5baf64ca8d', '0', 'admin', '2020-08-19 14:34:12', '', '2020-08-26 10:27:21', 'f94675', NULL);
INSERT INTO `eye_client_config` VALUES (379, 'TPWX', '太平微信', 'WX_H5', '*', 'S', 'Y', '太平测试', 'taiping@126.com', 'N', 'taiping', '4b60bd09e90595f5d7758e21cb10e285', '0', 'admin', '2020-08-06 08:31:37', '', '2020-08-31 00:23:45', '8583b0', NULL);

-- ----------------------------
-- Table structure for eye_faceapi_log
-- ----------------------------
DROP TABLE IF EXISTS `eye_faceapi_log`;
CREATE TABLE `eye_faceapi_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调用方代码',
  `face_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口类型：活体人脸核身，身份信息核验，活体检测，活体人脸对比',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `user_idcard` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `recognition_data` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '识别结果',
  `recognition_date` datetime(0) NULL DEFAULT NULL COMMENT '识别日期',
  `face_picture` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人脸图片路径',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常）',
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 363 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '实名验证接口日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eye_faceapi_log
-- ----------------------------

-- ----------------------------
-- Table structure for eye_node_config
-- ----------------------------
DROP TABLE IF EXISTS `eye_node_config`;
CREATE TABLE `eye_node_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `node_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点代码',
  `node_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `relation_product_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联产品代码：为空则为全部产品,多个用,隔开',
  `node_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点类型(监管节点、业务节点、事件监控节点)',
  `oper_min_time` int(11) NULL DEFAULT NULL COMMENT '操作最少用时(单位秒)',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常）',
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 377 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '节点配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eye_node_config
-- ----------------------------
INSERT INTO `eye_node_config` VALUES (364, 'ProductBrowse', '产品浏览', 'ALL', 'regulatory', 10, '0', 'admin', '2020-07-17 14:18:58', 'admin', '2020-07-19 15:16:41', NULL, '');
INSERT INTO `eye_node_config` VALUES (365, 'InsurePrompt', '进入投保提示', 'ALL', 'regulatory', 10, '0', 'admin', '2020-07-17 14:19:19', 'admin', '2020-07-19 18:54:49', NULL, '');
INSERT INTO `eye_node_config` VALUES (366, 'HealthInform', '健康告知', 'ALL', 'regulatory', 10, '0', 'admin', '2020-07-17 14:19:39', 'admin', '2020-07-19 15:16:48', NULL, '');
INSERT INTO `eye_node_config` VALUES (367, 'InsureInput', '投保录入', 'ALL', 'regulatory', 10, '0', 'admin', '2020-07-17 14:19:52', 'admin', '2020-07-19 15:16:51', NULL, '');
INSERT INTO `eye_node_config` VALUES (368, 'ClauseBrowse', '条款浏览', 'ALL', 'regulatory', 10, '0', 'admin', '2020-07-17 14:20:09', 'admin', '2020-07-19 18:55:05', NULL, '');
INSERT INTO `eye_node_config` VALUES (370, 'OrderSubmit', '订单提交', 'ALL', 'regulatory', 10, '0', 'admin', '2020-07-17 14:20:42', 'admin', '2020-07-19 15:17:01', NULL, '');
INSERT INTO `eye_node_config` VALUES (373, 'OrderPayWay', '订单开始支付', 'ALL', 'regulatory', 10, '0', 'admin', '2020-07-17 14:45:28', 'admin', '2020-07-19 18:55:32', NULL, '');
INSERT INTO `eye_node_config` VALUES (374, 'OrderPaySuccess', '订单支付成功', 'ALL', 'regulatory', 10, '0', 'admin', '2020-07-17 14:45:55', 'admin', '2020-07-19 15:17:09', NULL, '');
INSERT INTO `eye_node_config` VALUES (375, 'FaceCheck', '实名验证', 'ALL', 'regulatory', 1, '0', 'admin', '2020-07-19 18:55:59', NULL, NULL, NULL, '');
INSERT INTO `eye_node_config` VALUES (376, 'InsureConfirm', '投保确认', 'ALL', 'regulatory', 10, '0', 'admin', '2020-08-02 16:33:20', NULL, NULL, NULL, '');

-- ----------------------------
-- Table structure for eye_order
-- ----------------------------
DROP TABLE IF EXISTS `eye_order`;
CREATE TABLE `eye_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
  `sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接方系统编码,字典client_code',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `product_code` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品代码',
  `product_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `policy_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保单号',
  `policy_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投保人姓名',
  `policy_idcard` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投保人证件',
  `check_status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '质检结果，0-质检通过，1-质检未通过',
  `check_message` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '质检结果描述',
  `check_time` datetime(0) NULL DEFAULT NULL COMMENT '检查时间',
  `verify_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回溯验真码',
  `verify_time` datetime(0) NULL DEFAULT NULL COMMENT '验真检查时间',
  `verify_third_party` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方验真：0-未发送，1-已发送,2-发送失败',
  `file_size` double(8, 2) NULL DEFAULT NULL COMMENT '文件大小',
  `is_litigations` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否涉及诉讼：0-否；1-是',
  `arch_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '归档状态：0-未归档；1-已归档',
  `arch_time` datetime(0) NULL DEFAULT NULL COMMENT '归档时间',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常）',
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eye_order
-- ----------------------------

-- ----------------------------
-- Table structure for eye_order_page
-- ----------------------------
DROP TABLE IF EXISTS `eye_order_page`;
CREATE TABLE `eye_order_page`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
  `sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接方系统编码,字典client_code',
  `sdk_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '返回sdk版本',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `page_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前页面id',
  `node_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人员id',
  `begin_time` datetime(0) NULL DEFAULT NULL COMMENT '开始录制时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束录制时间',
  `record_time` int(11) NULL DEFAULT NULL COMMENT 'X秒',
  `file_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件地址',
  `file_size` double(8, 2) NULL DEFAULT NULL COMMENT '文件大小',
  `client_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终端类型',
  `client_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `client_city_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端城市编码',
  `client_city_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端城市名称',
  `client_browser` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端浏览器类型',
  `policy_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保单号',
  `policy_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投保人姓名',
  `policy_idcard` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投保人证件',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常）',
  `oper_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作类型:子节点操作-0，录制-1，截屏-2',
  `node_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前页面url',
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '页面录制记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eye_order_page
-- ----------------------------

-- ----------------------------
-- Table structure for eye_page_config
-- ----------------------------
DROP TABLE IF EXISTS `eye_page_config`;
CREATE TABLE `eye_page_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统代码',
  `page_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面url',
  `page_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面名称',
  `node_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点代码',
  `exclude_content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排除计算内容',
  `add_js_function` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附加js方法',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常）',
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 376 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '页面管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eye_page_config
-- ----------------------------
INSERT INTO `eye_page_config` VALUES (365, 'TPWX', 'http://eye.h5img.com:8866/demo/eye/productBrowse', '产品浏览测试页面001', 'ProductBrowse', '', '', '0', 'admin', '2020-08-02 17:02:56', NULL, NULL, NULL, '');
INSERT INTO `eye_page_config` VALUES (366, 'TPWX', 'http://eye.h5img.com:8866/demo/eye/healthInform', '健康告知测试页面001', 'HealthInform', '', '', '0', 'admin', '2020-08-02 17:03:42', NULL, NULL, NULL, '');
INSERT INTO `eye_page_config` VALUES (367, 'TPWX', 'http://eye.h5img.com:8866/demo/eye/insureInput', '投保录入测试页面001', 'ProductBrowse', '', '', '0', 'admin', '2020-08-02 17:04:22', NULL, NULL, NULL, '');
INSERT INTO `eye_page_config` VALUES (368, 'TPWX', 'http://eye.h5img.com:8866/demo/eye/paymentSit', '订单开始支付测试页面001', 'OrderPayWay', '', '', '0', 'admin', '2020-08-02 17:19:59', NULL, NULL, NULL, '');
INSERT INTO `eye_page_config` VALUES (369, 'TPWX', 'http://eye.h5img.com:8866/demo/eye/paymentEnd', '订单支付成功测试页面001', 'OrderPaySuccess', '', '', '0', 'admin', '2020-08-02 17:23:03', NULL, NULL, NULL, '');
INSERT INTO `eye_page_config` VALUES (370, 'TPWX', 'http://eye.h5img.com:8866/demo/eye/insureInputSave', '订单提交测试页面001', 'OrderSubmit', '', '', '0', 'admin', '2020-08-02 17:23:34', NULL, NULL, NULL, '');
INSERT INTO `eye_page_config` VALUES (371, 'CCIC_WX', '/ebiz/view/propertyMobile*', '微信端产品浏览', 'ProductBrowse', '', '$(\".buy-button\").click(function(){\r\n	    	WebEye.start(\"HealthInform\");\r\n		})\r\n		$(\".agree\").click(function(){\r\n		    WebEye.end(\"HealthInform\");\r\n		})\r\n		$(\".disagree\").click(function(){\r\n		    WebEye.end(\"HealthInform\");\r\n		})', '0', 'admin', '2020-08-04 09:32:51', NULL, NULL, NULL, '');
INSERT INTO `eye_page_config` VALUES (372, 'CCIC_WX', '/ebiz/view/propertyMobile/insuranceInform*', '微信端投保录入', 'InsureInput', '', '', '0', 'admin', '2020-08-04 09:34:20', NULL, NULL, NULL, '');
INSERT INTO `eye_page_config` VALUES (373, 'CCIC_WX', '/ebiz/view/propertyMobile/insuranceConfirm*', '微信端投保确认', 'InsureConfirm', '', '', '0', 'admin', '2020-08-04 09:34:42', 'admin', '2020-08-04 09:37:13', NULL, '');
INSERT INTO `eye_page_config` VALUES (374, 'CCIC_WX', '/ebiz/view/propertyInsuranceMobile*', '微信端投保提交成功', 'OrderSubmit', '', '', '0', 'admin', '2020-08-04 09:35:05', 'admin', '2020-08-04 09:35:16', NULL, '');
INSERT INTO `eye_page_config` VALUES (375, 'CCIC_WX', '/mnpon/#/product/introduce*', '微信端产品浏览VUE', 'ProductBrowse', '', 'setTimeout(function(){\r\n		$(\".toubao\").click(function(){\r\n			console.log(\"--starHealthInform-\")\r\n			starHealthInform();\r\n		});\r\n	},1000);\r\n\r\n	function starHealthInform(){\r\n		WebEye.start(\"HealthInform\");		\r\n	}', '0', 'admin', '2020-08-04 09:37:04', NULL, NULL, NULL, '单页应用');

-- ----------------------------
-- Table structure for eye_page_version
-- ----------------------------
DROP TABLE IF EXISTS `eye_page_version`;
CREATE TABLE `eye_page_version`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统编码',
  `product_code` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品代码',
  `product_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `node_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点id',
  `page_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前页面id',
  `file_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `file_md5` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件MD5',
  `file_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型:录制文件:recordFile;抓取文件:urlFile;当前版本文件:orgHtml',
  `node_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采集版本时的页面url',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常）',
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '页面版本记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eye_page_version
-- ----------------------------
INSERT INTO `eye_page_version` VALUES (1, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/6102_v3_ProductBrowse_ProductBrowse_6102_v3.txt', '6102_v3_ProductBrowse_ProductBrowse_6102_v3', 'orgHtml', NULL, '0', 'admin', '2020-08-29 21:37:47', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (2, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/6e04649da4cb456211e27c515ab880f5.css', '6e04649da4cb456211e27c515ab880f5', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:52', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (3, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/4330b5a0b5dbaa63959340ca2cf2f36c.css', '4330b5a0b5dbaa63959340ca2cf2f36c', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:53', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (4, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/3559da567ecf83fc778bd93f51db31ea.css', '3559da567ecf83fc778bd93f51db31ea', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:53', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (5, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/d99ed30a1ae98049e3c8c580c0503ed5.css', 'd99ed30a1ae98049e3c8c580c0503ed5', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:53', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (6, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/4100a8cf302141d64bcb69a6c1385064.png', '4100a8cf302141d64bcb69a6c1385064', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:53', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (7, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/af4e7ccd4f8a2d78ac7a02e81dee85ec.jpg', 'af4e7ccd4f8a2d78ac7a02e81dee85ec', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:53', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (8, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/878a98153f54fa4fe1a223a33dff1442.jpg', '878a98153f54fa4fe1a223a33dff1442', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:54', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (9, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/84816085ec0da4049f06d7fa76cd8f86.jpg', '84816085ec0da4049f06d7fa76cd8f86', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:54', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (10, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/d9fe5261b99a6cad6bfe4bc064a36b63.jpg', 'd9fe5261b99a6cad6bfe4bc064a36b63', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:54', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (11, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/3ebb2ff57ee38639048fcb4dc19f361b.jpg', '3ebb2ff57ee38639048fcb4dc19f361b', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:54', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (12, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/78e5a9fd65770a5958275c47cc9b55a9.png', '78e5a9fd65770a5958275c47cc9b55a9', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:55', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (13, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/58063fdb997e8cfe1eba5397497de619.png', '58063fdb997e8cfe1eba5397497de619', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:55', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (14, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/a8707b15aad2886416906f98734536df.png', 'a8707b15aad2886416906f98734536df', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:55', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (15, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/6b6f6a3246df99af4eb99d1741b1d7e1.png', '6b6f6a3246df99af4eb99d1741b1d7e1', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:55', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (16, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/fd3f669031ce72d7b2386a436c677c87.png', 'fd3f669031ce72d7b2386a436c677c87', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:55', NULL, NULL, NULL, NULL);
INSERT INTO `eye_page_version` VALUES (17, NULL, '6102_v3', '安康福医疗险', 'ProductBrowse', 'ProductBrowse_6102_v3', 'D:/webeye/uploadPath/versionFile/6102_v3/ProductBrowse/ProductBrowse_6102_v3/bccbdb768ab5a3c620e0c01b1beaa3b1.jpg', 'bccbdb768ab5a3c620e0c01b1beaa3b1', 'urlFile', NULL, '0', 'admin', '2020-08-29 21:37:55', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for eye_recallmanage
-- ----------------------------
DROP TABLE IF EXISTS `eye_recallmanage`;
CREATE TABLE `eye_recallmanage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `recallNum` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '回溯编码',
  `projectName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '产品名称',
  `orderNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单号',
  `policyNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '保单号',
  `policyName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '投保人姓名',
  `policyIdNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '证件',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'ip地址',
  `recordingTime` datetime(0) NULL DEFAULT NULL COMMENT '录制时间',
  `videoNum` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频/图片编码',
  `fileName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文件名称',
  `filePath` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文件路径',
  `existPlatform` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '第三方存证平台',
  `existTiem` datetime(0) NULL DEFAULT NULL COMMENT '送存时间',
  `existenceState` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `truthState` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环节/类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 383 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回溯管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eye_recallmanage
-- ----------------------------
INSERT INTO `eye_recallmanage` VALUES (222, 'b035d9a424fb917b5ad4c596bc173119', '安康福医疗险', '1594277664847', 'T2020441000045612', '', '', '192.168.0.75', '2020-07-08 15:54:25', NULL, 'cpll.txt', 'E:/uploadPath/upload/cpll.txt', '未发送', '2020-07-09 14:54:25', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (223, 'd3c885caf80c53d1fdaf67196e8f169f', '安康福医疗险', '1594277664847', 'T2020441000045612', '', '', '192.168.0.75', '2020-07-08 15:54:33', NULL, 'ckxz.txt', 'E:/uploadPath/upload/ckxz.txt', '未发送', '2020-07-09 14:54:33', '0', '0', 'ckxz');
INSERT INTO `eye_recallmanage` VALUES (224, '2984df26d866c6f2fdd176586674bf93', '安康福医疗险', '1594277664847', 'T2020441000045612', '张三', '430124197902031811', '192.168.0.75', '2020-07-08 15:55:19', NULL, 'xxlr.txt', 'E:/uploadPath/upload/xxlr.txt', '未发送', '2020-07-09 14:55:19', '0', '0', 'xxlr');
INSERT INTO `eye_recallmanage` VALUES (347, '1ec0491b1ef5990e1c69fb880faddcd5', '安康福医疗险', '1594604797262', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-13 09:46:38', NULL, '1594604797262cpll.txt', 'D:/ruoyi/uploadPath/upload/1594604797262cpll.txt', '未发送', '2020-07-13 09:46:38', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (348, '647c976c054e0a16e0f00ed3a89b5248', '安康福医疗险', '1594604797262', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-13 09:46:44', NULL, '1594604797262ckxz.txt', 'D:/ruoyi/uploadPath/upload/1594604797262ckxz.txt', '未发送', '2020-07-13 09:46:44', '0', '0', 'ckxz');
INSERT INTO `eye_recallmanage` VALUES (349, '090d13b9f6ae901790ff1275242ff3bc', '安康福医疗险', '1594604797262', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-13 09:46:45', NULL, '1594604797262cktk.txt', 'D:/ruoyi/uploadPath/upload/1594604797262cktk.txt', '未发送', '2020-07-13 09:46:45', '0', '0', 'cktk');
INSERT INTO `eye_recallmanage` VALUES (350, '822e7340005265b98798d00d263780cb', '安康福医疗险', '1594615113131', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-13 12:38:34', NULL, '1594615113131cpll.txt', 'D:/ruoyi/uploadPath/upload/1594615113131cpll.txt', '未发送', '2020-07-13 12:38:34', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (351, 'bfb01bd043cf10b1ff24c3e59e9392ce', '安康福医疗险', '1594604797262', 'T2020441000045612', 'hello', '430626100090909090', '127.0.0.1', '2020-07-13 12:54:30', NULL, '1594604797262xxlr.txt', 'D:/ruoyi/uploadPath/upload/1594604797262xxlr.txt', '未发送', '2020-07-13 12:54:30', '0', '0', 'xxlr');
INSERT INTO `eye_recallmanage` VALUES (352, 'ff09d1ac346189a63854fc0b26fa4a0f', '安康福医疗险', '1594633149045', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-13 17:39:09', NULL, '1594633149045cpll.txt', 'D:/ruoyi/uploadPath/upload/1594633149045cpll.txt', '未发送', '2020-07-13 17:39:09', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (353, '683373963127ba3b5deec7c2374ced49', '安康福医疗险', '1594634689327', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-13 18:04:50', NULL, '1594634689327cpll.txt', 'E:/uploadPath/upload/1594634689327cpll.txt', '未发送', '2020-07-13 18:04:50', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (354, '493999ac63866b3a109eeb18c61e4a96', '安康福医疗险', '1594690388149', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 09:33:09', NULL, '1594690388149cpll.txt', 'D:/ruoyi/uploadPath/upload/1594690388149cpll.txt', '未发送', '2020-07-14 09:33:09', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (355, '49a43f51dee1a13ce464a0d4d328c943', '安康福医疗险', '1594690488861', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 09:34:50', NULL, '1594690488861cpll.txt', 'D:/ruoyi/uploadPath/upload/1594690488861cpll.txt', '未发送', '2020-07-14 09:34:50', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (356, 'a523d89b32d20be46a2f4ad3f06ba0e5', '安康福医疗险', '1594690488861', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 09:34:56', NULL, '1594690488861ckxz.txt', 'D:/ruoyi/uploadPath/upload/1594690488861ckxz.txt', '未发送', '2020-07-14 09:34:56', '0', '0', 'ckxz');
INSERT INTO `eye_recallmanage` VALUES (357, '983108cbb4455ae59b9bf161bd773749', '安康福医疗险', '1594691232801', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 09:47:13', NULL, '1594691232801cpll.txt', 'D:/ruoyi/uploadPath/upload/1594691232801cpll.txt', '未发送', '2020-07-14 09:47:13', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (358, '5618da8d3d8a8d04806fedca29fe0710', '安康福医疗险', '1594691288702', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 09:48:09', NULL, '1594691288702cpll.txt', 'D:/ruoyi/uploadPath/upload/1594691288702cpll.txt', '未发送', '2020-07-14 09:48:09', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (359, '5e7781494cebad76dcef4b613879dee6', '安康福医疗险', '1594693248415', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 10:20:49', NULL, '1594693248415cpll.txt', 'D:/ruoyi/uploadPath/upload/1594693248415cpll.txt', '未发送', '2020-07-14 10:20:49', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (360, '39653b7114315e2c33ac016abcf85d9a', '安康福医疗险', '1594693452975', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 10:24:13', NULL, '1594693452975cpll.txt', 'D:/ruoyi/uploadPath/upload/1594693452975cpll.txt', '未发送', '2020-07-14 10:24:13', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (361, 'b56edf3a6c3ece95c183963d8074b324', '安康福医疗险', '1234', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 10:24:19', NULL, '1234ckxz.txt', 'D:/ruoyi/uploadPath/upload/1234ckxz.txt', '未发送', '2020-07-14 10:24:19', '0', '0', 'ckxz');
INSERT INTO `eye_recallmanage` VALUES (362, 'a32662ed090b66edeb05da979a0d1985', '安康福医疗险', '1594697021302', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 11:23:41', NULL, '1594697021302cpll.txt', 'D:/ruoyi/uploadPath/upload/1594697021302cpll.txt', '未发送', '2020-07-14 11:23:41', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (363, 'ab4540815253931c8f387500f7b3851c', '安康福医疗险', '1594710908872', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 15:15:09', NULL, '1594710908872cpll.txt', 'D:/ruoyi/uploadPath/upload/1594710908872cpll.txt', '未发送', '2020-07-14 15:15:09', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (364, 'bd4a170d811068c03b73451aeee0be28', '安康福医疗险', '1594713930660', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 16:05:31', NULL, '1594713930660cpll.txt', 'D:/ruoyi/uploadPath/upload/1594713930660cpll.txt', '未发送', '2020-07-14 16:05:31', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (365, '578ab41f3869d7b693a8b976882b4e3c', '安康福医疗险', '1594714270699', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 16:11:11', NULL, '1594714270699cpll.txt', 'D:/ruoyi/uploadPath/upload/1594714270699cpll.txt', '未发送', '2020-07-14 16:11:11', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (366, '9ea2308b06a6522ce5fc0ba642ca8a9e', '安康福医疗险', '1594714309257', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 16:11:49', NULL, '1594714309257cpll.txt', 'D:/ruoyi/uploadPath/upload/1594714309257cpll.txt', '未发送', '2020-07-14 16:11:49', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (367, '6feab8764d5ae91e56cd427036e6b57e', '安康福医疗险', '1594714373267', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 16:12:53', NULL, '1594714373267cpll.txt', 'D:/ruoyi/uploadPath/upload/1594714373267cpll.txt', '未发送', '2020-07-14 16:12:53', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (368, '4ea38bee095e78d4cccc6c7dc61c6f18', '安康福医疗险', '1594714394422', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 16:13:14', NULL, '1594714394422cpll.txt', 'D:/ruoyi/uploadPath/upload/1594714394422cpll.txt', '未发送', '2020-07-14 16:13:14', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (369, '3dce8ec0fde90b95818e9f85ce519e5a', '安康福医疗险', '1594714417851', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 16:13:38', NULL, '1594714417851cpll.txt', 'D:/ruoyi/uploadPath/upload/1594714417851cpll.txt', '未发送', '2020-07-14 16:13:38', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (370, '6b5be1c2c10ee3128f57047a074c56cc', '安康福医疗险', '1594714516389', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 16:15:17', NULL, '1594714516389cpll.txt', 'D:/ruoyi/uploadPath/upload/1594714516389cpll.txt', '未发送', '2020-07-14 16:15:17', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (371, 'd38b15dadf560df8f9b19dbc6e85ae78', '安康福医疗险', '1594714516389', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 16:15:22', NULL, '1594714516389ckxz.txt', 'D:/ruoyi/uploadPath/upload/1594714516389ckxz.txt', '未发送', '2020-07-14 16:15:22', '0', '0', 'ckxz');
INSERT INTO `eye_recallmanage` VALUES (372, 'c26f2c9efb80b0c8be0dbbb40c6bec48', '安康福医疗险', '1594714516389', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 16:15:51', NULL, '1594714516389xxlr.txt', 'D:/ruoyi/uploadPath/upload/1594714516389xxlr.txt', '未发送', '2020-07-14 16:15:51', '0', '0', 'xxlr');
INSERT INTO `eye_recallmanage` VALUES (373, 'f0e33c0d81104cab2848c122c25c6d74', '安康福医疗险', '1594716122109', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 16:42:02', NULL, '1594716122109cpll.txt', 'D:/ruoyi/uploadPath/upload/1594716122109cpll.txt', '未发送', '2020-07-14 16:42:02', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (374, '816bc331658287b4a0161dde8e767a3b', '安康福医疗险', '1594716152567', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 16:42:33', NULL, '1594716152567cpll.txt', 'D:/ruoyi/uploadPath/upload/1594716152567cpll.txt', '未发送', '2020-07-14 16:42:33', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (375, '722b727e77f9731b72a8e6a3ffff15cc', '安康福医疗险', '1594717216372', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 17:00:17', NULL, '1594717216372cpll.txt', 'D:/ruoyi/uploadPath/upload/1594717216372cpll.txt', '未发送', '2020-07-14 17:00:17', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (376, '0e923ed1d200ca4b8a915f0186de04de', '安康福医疗险', '1594720636299', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 17:57:17', NULL, '1594720636299cpll.txt', 'D:/ruoyi/uploadPath/upload/1594720636299cpll.txt', '未发送', '2020-07-14 17:57:17', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (377, '7675cf687a1fa7d6f8e999c227590e5e', '安康福医疗险', '1594720636299', 'T2020441000045612', '12344567', 'ADMIN1231234556', '127.0.0.1', '2020-07-14 17:58:49', NULL, '1594720636299xxlr.txt', 'D:/ruoyi/uploadPath/upload/1594720636299xxlr.txt', '未发送', '2020-07-14 17:58:49', '0', '0', 'xxlr');
INSERT INTO `eye_recallmanage` VALUES (378, '51f23248069590767e0d02a964d9b802', '安康福医疗险', '1594727052572', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 19:44:13', NULL, '1594727052572cpll.txt', 'D:/ruoyi/uploadPath/upload/1594727052572cpll.txt', '未发送', '2020-07-14 19:44:13', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (379, '9a18a771cf231c9824d95b7d0ba492f1', '安康福医疗险', '1594737489737', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-14 22:38:10', NULL, '1594737489737cpll.txt', 'D:/ruoyi/uploadPath/upload/1594737489737cpll.txt', '未发送', '2020-07-14 22:38:10', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (380, '74cf865c5a8b05f5bcb8b35ad8c19e56', '安康福医疗险', '1594776192531', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-15 09:23:15', NULL, '1594776192531cpll.txt', 'D:/ruoyi/uploadPath/upload/1594776192531cpll.txt', '未发送', '2020-07-15 09:23:15', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (381, '153dcc0c1dfd0d5631f8a83fcf379aef', '安康福医疗险', '1594777748401', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-15 09:49:09', NULL, '1594777748401cpll.txt', 'D:/ruoyi/uploadPath/upload/1594777748401cpll.txt', '未发送', '2020-07-15 09:49:09', '0', '0', 'cpll');
INSERT INTO `eye_recallmanage` VALUES (382, '4cc03426cfd0912c02e56c3329546ae5', '安康福医疗险', '1594783027626', 'T2020441000045612', '', '', '127.0.0.1', '2020-07-15 11:17:09', NULL, '1594783027626cpll.txt', 'D:/ruoyi/uploadPath/upload/1594783027626cpll.txt', '未发送', '2020-07-15 11:17:09', '0', '0', 'cpll');

-- ----------------------------
-- Table structure for eye_record_clear_report
-- ----------------------------
DROP TABLE IF EXISTS `eye_record_clear_report`;
CREATE TABLE `eye_record_clear_report`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
  `sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接方系统编码,字典client_code',
  `sdk_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接所用的sdk版本',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `page_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前页面id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人员id',
  `product_code` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品代码',
  `product_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `begin_time` datetime(0) NULL DEFAULT NULL COMMENT '开始录制时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束录制时间',
  `sum_record_time` int(11) NULL DEFAULT NULL COMMENT '总共录制了X秒',
  `node_count` int(3) NULL DEFAULT NULL COMMENT '总共录制了多少个节点',
  `action_path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作的路径过程',
  `client_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终端类型',
  `policy_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保单号',
  `policy_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投保人姓名',
  `policy_idcard` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投保人证件',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '录制清理记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eye_record_clear_report
-- ----------------------------

-- ----------------------------
-- Table structure for eye_record_page
-- ----------------------------
DROP TABLE IF EXISTS `eye_record_page`;
CREATE TABLE `eye_record_page`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
  `sys_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接方系统编码,字典client_code',
  `sdk_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对接所用的sdk版本',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `page_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前页面id',
  `node_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人员id',
  `product_code` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品代码',
  `product_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `begin_time` datetime(0) NULL DEFAULT NULL COMMENT '开始录制时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束录制时间',
  `record_time` int(11) NULL DEFAULT NULL COMMENT '录制了X秒',
  `file_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件地址',
  `client_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终端类型',
  `client_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `client_city_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端城市编码',
  `client_city_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端城市名称',
  `client_browser` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端浏览器类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常）',
  `oper_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作类型:子节点操作-0，录制-1，截屏-2',
  `node_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前页面url',
  `policy_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保单号',
  `policy_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投保人姓名',
  `policy_idcard` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投保人证件',
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `flag` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5542 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '页面录制记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eye_record_page
-- ----------------------------
INSERT INTO `eye_record_page` VALUES (5534, '5f31fdd0f4b1e30a56de885a', NULL, NULL, NULL, 'ProductBrowse_6102_v3', 'ProductBrowse', 'testuser', '6102_v3', '安康福医疗险', '2020-08-11 10:09:27', '2020-08-11 10:09:52', 25, 'D:/webeye/uploadPath/recordFile/6102_v3/ProductBrowse/2020/08/11/5f31fdd0f4b1e30a56de885a/5f31fdd0f4b1e30a56de885a_ProductBrowse_5534.txt', NULL, '127.0.0.1', NULL, '内网', 'Chrome', '0', '1', NULL, NULL, NULL, NULL, 'admin', '2020-08-11 10:09:27', NULL, '2020-08-11 10:09:52', NULL, NULL);
INSERT INTO `eye_record_page` VALUES (5535, '5f31fdd0f4b1e30a56de885a', NULL, NULL, NULL, 'HealthInform_6102_v3', 'HealthInform', 'testuser', '6102_v3', '安康福医疗险', '2020-08-11 10:09:52', NULL, 0, 'D:/webeye/uploadPath/recordFile/6102_v3/HealthInform/2020/08/11/5f31fdd0f4b1e30a56de885a/5f31fdd0f4b1e30a56de885a_HealthInform_5535.txt', NULL, '127.0.0.1', NULL, '内网', 'Chrome', '0', '1', NULL, NULL, NULL, NULL, 'admin', '2020-08-11 10:09:52', NULL, '2020-08-11 10:09:52', NULL, NULL);
INSERT INTO `eye_record_page` VALUES (5536, '5f3f98f6f4b17bc1bbce64b2', NULL, NULL, NULL, 'ProductBrowse_6102_v3', 'ProductBrowse', 'testuser', '6102_v3', '安康福医疗险', '2020-08-21 17:50:51', '2020-08-21 17:51:01', 10, 'D:/webeye/uploadPath/recordFile/6102_v3/ProductBrowse/2020/08/21/5f3f98f6f4b17bc1bbce64b2/5f3f98f6f4b17bc1bbce64b2_ProductBrowse_5536.txt', NULL, '127.0.0.1', NULL, '内网', 'Chrome', '0', '1', NULL, NULL, NULL, NULL, 'admin', '2020-08-21 17:50:51', NULL, '2020-08-21 17:51:01', NULL, NULL);
INSERT INTO `eye_record_page` VALUES (5537, '5f3f98f6f4b17bc1bbce64b2', NULL, NULL, NULL, 'HealthInform_6102_v3', 'HealthInform', 'testuser', '6102_v3', '安康福医疗险', '2020-08-21 17:51:01', NULL, 0, 'D:/webeye/uploadPath/recordFile/6102_v3/HealthInform/2020/08/21/5f3f98f6f4b17bc1bbce64b2/5f3f98f6f4b17bc1bbce64b2_HealthInform_5537.txt', NULL, '127.0.0.1', NULL, '内网', 'Chrome', '0', '1', NULL, NULL, NULL, NULL, 'admin', '2020-08-21 17:51:01', NULL, '2020-08-21 17:51:01', NULL, NULL);
INSERT INTO `eye_record_page` VALUES (5538, '5f448085f4b1a70bc5a7840a', 'QSCWX', 'S', NULL, 'pingan-health-2020-two', 'ProductBrowse', '05ce89b4-ba0c-11ea-a428-0242ac114068', 'pingan-health-2020-two-pingan-health-2020-two-land-page-A', 'pingan-health-2020-two-pingan-health-2020-two-land-page-A', '2020-08-25 11:07:50', NULL, 0, 'D:/webeye/uploadPath/recordFile/pingan-health-2020-two-pingan-health-2020-two-land-page-A/ProductBrowse/2020/08/25/5f448085f4b1a70bc5a7840a/5f448085f4b1a70bc5a7840a_ProductBrowse_5538.txt', NULL, '127.0.0.1', NULL, '内网', 'Unknown', '0', '1', NULL, NULL, NULL, NULL, 'admin', '2020-08-25 11:07:50', NULL, '2020-08-25 11:07:50', NULL, NULL);
INSERT INTO `eye_record_page` VALUES (5539, '5f44808cf4b1a70bc5a7840b', 'QSCWX', 'S', NULL, 'pingan-health-2020-two', 'ProductBrowse', '05ce89b4-ba0c-11ea-a428-0242ac114068', 'pingan-health-2020-two-pingan-health-2020-two-land-page-A', 'pingan-health-2020-two-pingan-health-2020-two-land-page-A', '2020-08-25 11:07:57', NULL, 0, 'D:/webeye/uploadPath/recordFile/pingan-health-2020-two-pingan-health-2020-two-land-page-A/ProductBrowse/2020/08/25/5f44808cf4b1a70bc5a7840b/5f44808cf4b1a70bc5a7840b_ProductBrowse_5539.txt', NULL, '127.0.0.1', NULL, '内网', 'Unknown', '0', '1', NULL, NULL, NULL, NULL, 'admin', '2020-08-25 11:07:57', NULL, '2020-08-25 11:07:57', NULL, NULL);
INSERT INTO `eye_record_page` VALUES (5540, '5f4480a6f4b1a70bc5a7840c', 'QSCWX', 'S', NULL, 'pingan-health-2020-two', 'ProductBrowse', '05ce89b4-ba0c-11ea-a428-0242ac114068', 'pingan-health-2020-two-pingan-health-2020-two-land-page-A', 'pingan-health-2020-two-pingan-health-2020-two-land-page-A', '2020-08-25 11:08:23', NULL, 0, 'D:/webeye/uploadPath/recordFile/pingan-health-2020-two-pingan-health-2020-two-land-page-A/ProductBrowse/2020/08/25/5f4480a6f4b1a70bc5a7840c/5f4480a6f4b1a70bc5a7840c_ProductBrowse_5540.txt', NULL, '127.0.0.1', NULL, '内网', 'Unknown', '0', '1', NULL, NULL, NULL, NULL, 'admin', '2020-08-25 11:08:23', NULL, '2020-08-25 11:08:23', NULL, NULL);
INSERT INTO `eye_record_page` VALUES (5541, '5f4a5a26f4b1edd41f98e158', NULL, NULL, NULL, 'ProductBrowse_6102_v3', 'ProductBrowse', 'testuser', '6102_v3', '安康福医疗险', '2020-08-29 21:37:47', NULL, 0, 'D:/webeye/uploadPath/recordFile/6102_v3/ProductBrowse/2020/08/29/5f4a5a26f4b1edd41f98e158/5f4a5a26f4b1edd41f98e158_ProductBrowse_5541.txt', NULL, '127.0.0.1', NULL, '内网', 'Chrome', '0', '1', 'http://127.0.0.1/demo/eye/productBrowse', NULL, NULL, NULL, 'admin', '2020-08-29 21:37:47', NULL, '2020-08-29 21:37:47', NULL, NULL);

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
  `package_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `options` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (15, 'eye_client_config', '对接的系统或渠道配置', NULL, NULL, 'EyeClientConfig', 'crud', 'ins.webeye.project.eye', 'eye', 'clientconfig', '系统/渠道配置', 'webeye', '{\"treeName\":\"\",\"treeParentCode\":\"\",\"treeCode\":\"\"}', 'admin', '2020-08-30 01:02:49', '', '2020-08-30 10:09:37', '');

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 306 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (287, '15', 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (288, '15', 'client_code', '系统/渠道代码', 'varchar(32)', 'String', 'clientCode', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (289, '15', 'client_name', '系统/渠道名称', 'varchar(64)', 'String', 'clientName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (290, '15', 'client_type', '终端类型', 'varchar(32)', 'String', 'clientType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'client_type', 4, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (291, '15', 'client_url', '系统域名', 'varchar(255)', 'String', 'clientUrl', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', '', 5, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (292, '15', 'sdk_type', 'SDK类型', 'varchar(10)', 'String', 'sdkType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', 'sdk_type', 6, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (293, '15', 'auto_start', '自动录制', 'varchar(1)', 'String', 'autoStart', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'select', 'sys_yes_no', 7, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (294, '15', 'contact_name', '联系人', 'varchar(64)', 'String', 'contactName', '0', '0', NULL, '1', '1', NULL, NULL, 'LIKE', 'input', '', 8, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (295, '15', 'contact_mail', '联系人邮箱', 'varchar(200)', 'String', 'contactMail', '0', '0', NULL, '1', '1', NULL, NULL, 'LIKE', 'input', '', 9, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (296, '15', 'authorize_id', '接口授权ID', 'varchar(64)', 'String', 'authorizeId', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (297, '15', 'authorize_key', '接口授权密钥', 'varchar(64)', 'String', 'authorizeKey', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (298, '15', 'status', '状态', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', 'sys_normal_disable', 12, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (299, '15', 'create_user', '创建者', 'varchar(64)', 'String', 'createUser', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (300, '15', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, '1', '1', 'BETWEEN', 'datetime', '', 14, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (301, '15', 'update_user', '更新者', 'varchar(64)', 'String', 'updateUser', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (302, '15', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (303, '15', 'flag', '标记', 'varchar(10)', 'String', 'flag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 17, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (304, '15', 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'textarea', '', 18, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');
INSERT INTO `gen_table_column` VALUES (305, '15', 'https_only', '状态', 'varchar(1)', 'String', 'httpsOnly', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'radio', 'sys_yes_no', 12, 'admin', '2020-08-30 01:02:49', NULL, '2020-08-30 10:09:37');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME100', 'SYSTEM', '0 0 1 * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME101', 'DEFAULT', '0 0 1 * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME102', 'DEFAULT', '0 0 1 * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/15 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME100', 'SYSTEM', '0 0 1 * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME101', 'DEFAULT', '0 0 1 * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME102', 'DEFAULT', '0 0 1 * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/15 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fired_time` bigint(20) NOT NULL,
  `sched_time` bigint(20) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3130202A202A202A202A203F74001172795461736B2E72794E6F506172616D7374000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME100', 'SYSTEM', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707400007372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001736B8E1F187870740045E58F82E695B0E8AFB4E6988EEFBC9AE6B885E79086203720E5A4A9E5898DE68980E69C89202B20E6B885E790862031207E3720E5A4A9E697A0E8AEA2E58D95E695B0E68DAE7070707400013174000B3020302031202A202A203F74001D6579655461736B2E636C6561725265636F7264506167657328372C312974000653595354454D7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000064740018E6B885E79086E5BD95E588B6E4BFA1E681AFE4BBBBE58AA174000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME101', 'DEFAULT', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707400007372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001737B3717F878707400007070707400013174000B3020302031202A202A203F7400226579655461736B2E636C6561724361636865282766696C6555524C4361636865272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000006574001EE6B885E79086E5BE85E69BBFE68DA275726CE7BC93E5AD98E4BBBBE58AA174000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME102', 'DEFAULT', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707400007372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001738F3B54A878707400007070707400013174000B3020302031202A202A203F7400236579655461736B2E636C6561724361636865282766696C6548746D6C4361636865272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000066740027E6B885E79086E5B7B2E4BF9DE5AD98E78988E69CACE7BD91E9A1B5E7BC93E5AD98E4BBBBE58AA174000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3135202A202A202A202A203F74001572795461736B2E7279506172616D7328277279272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000002740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3230202A202A202A202A203F74003872795461736B2E72794D756C7469706C65506172616D7328277279272C20747275652C20323030304C2C203331362E3530442C203130302974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000003740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E5A49AE58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3130202A202A202A202A203F74001172795461736B2E72794E6F506172616D7374000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME100', 'SYSTEM', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707400007372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001736B8E1F187870740045E58F82E695B0E8AFB4E6988EEFBC9AE6B885E79086203720E5A4A9E5898DE68980E69C89202B20E6B885E790862031207E3720E5A4A9E697A0E8AEA2E58D95E695B0E68DAE7070707400013174000B3020302031202A202A203F74001D6579655461736B2E636C6561725265636F7264506167657328372C312974000653595354454D7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000064740018E6B885E79086E5BD95E588B6E4BFA1E681AFE4BBBBE58AA174000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME101', 'DEFAULT', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707400007372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001737B3717F878707400007070707400013174000B3020302031202A202A203F7400226579655461736B2E636C6561724361636865282766696C6555524C4361636865272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000006574001EE6B885E79086E5BE85E69BBFE68DA275726CE7BC93E5AD98E4BBBBE58AA174000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME102', 'DEFAULT', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C78707400007372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001738F3B54A878707400007070707400013174000B3020302031202A202A203F7400236579655461736B2E636C6561724361636865282766696C6548746D6C4361636865272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000066740027E6B885E79086E5B7B2E4BF9DE5AD98E78988E69CACE7BD91E9A1B5E7BC93E5AD98E4BBBBE58AA174000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3135202A202A202A202A203F74001572795461736B2E7279506172616D7328277279272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000002740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC8974000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 'ins.webeye.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720029696E732E7765626579652E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200084C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E00097872002A696E732E7765626579652E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3230202A202A202A202A203F74003872795461736B2E72794D756C7469706C65506172616D7328277279272C20747275652C20323030304C2C203331362E3530442C203130302974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000003740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E5A49AE58F82EFBC8974000133740001317800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('WebeyeScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('WebeyeScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_checkin_time` bigint(20) NOT NULL,
  `checkin_interval` bigint(20) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RuoyiScheduler', 'Think-LiuPing1597560306063', 1597600656370, 15000);
INSERT INTO `qrtz_scheduler_state` VALUES ('WebeyeScheduler', 'Think-LiuPing1598811759436', 1598834450066, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `repeat_count` bigint(20) NOT NULL,
  `repeat_interval` bigint(20) NOT NULL,
  `times_triggered` bigint(20) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `int_prop_1` int(11) NULL DEFAULT NULL,
  `int_prop_2` int(11) NULL DEFAULT NULL,
  `long_prop_1` bigint(20) NULL DEFAULT NULL,
  `long_prop_2` bigint(20) NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_fire_time` bigint(20) NULL DEFAULT NULL,
  `prev_fire_time` bigint(20) NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `end_time` bigint(20) NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `misfire_instr` smallint(6) NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 1597560310000, -1, 5, 'PAUSED', 'CRON', 1597560306000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME100', 'SYSTEM', 'TASK_CLASS_NAME100', 'SYSTEM', NULL, 1597597200000, -1, 5, 'PAUSED', 'CRON', 1597560306000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME101', 'DEFAULT', 'TASK_CLASS_NAME101', 'DEFAULT', NULL, 1597597200000, -1, 5, 'PAUSED', 'CRON', 1597560306000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME102', 'DEFAULT', 'TASK_CLASS_NAME102', 'DEFAULT', NULL, 1597597200000, -1, 5, 'PAUSED', 'CRON', 1597560306000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 1597560315000, -1, 5, 'PAUSED', 'CRON', 1597560306000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 1597560320000, -1, 5, 'PAUSED', 'CRON', 1597560306000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 1598811760000, -1, 5, 'PAUSED', 'CRON', 1598811759000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME100', 'SYSTEM', 'TASK_CLASS_NAME100', 'SYSTEM', NULL, 1598893200000, -1, 5, 'PAUSED', 'CRON', 1598811759000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME101', 'DEFAULT', 'TASK_CLASS_NAME101', 'DEFAULT', NULL, 1598893200000, -1, 5, 'PAUSED', 'CRON', 1598811759000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME102', 'DEFAULT', 'TASK_CLASS_NAME102', 'DEFAULT', NULL, 1598893200000, -1, 5, 'PAUSED', 'CRON', 1598811759000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 1598811765000, -1, 5, 'PAUSED', 'CRON', 1598811759000, 0, NULL, 2, '');
INSERT INTO `qrtz_triggers` VALUES ('WebeyeScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 1598811760000, -1, 5, 'PAUSED', 'CRON', 1598811759000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');
INSERT INTO `sys_config` VALUES (4, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '是否开启注册用户功能');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 217 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '总公司', 0, '管理员', '15888888888', 'admin@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-08-17 16:17:11');
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '中科软深圳分公司', 1, '管理员', '15888888888', 'admin@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-07-19 21:23:46');
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '中科软北京分公司', 2, '管理员', '15888888888', 'admin@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-07-19 21:23:58');
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '管理员', '15888888888', 'admin@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '管理员', '15888888888', 'admin@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '管理员', '15888888888', 'admin@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '管理员', '15888888888', 'admin@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '管理员', '15888888888', 'admin@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '管理员', '15888888888', 'admin@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '管理员', '15888888888', 'admin@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES (200, 100, '0,100', '保险机构', 5, NULL, NULL, 'javalp@126.com', '0', '0', 'admin', '2020-07-19 21:25:14', 'admin', '2020-08-17 16:17:11');
INSERT INTO `sys_dept` VALUES (201, 200, '0,100,200', '鼎和保险', 1, NULL, NULL, NULL, '0', '0', 'admin', '2020-07-19 21:26:48', '', NULL);
INSERT INTO `sys_dept` VALUES (202, 200, '0,100,200', '中铁保险', 2, NULL, NULL, NULL, '0', '0', 'admin', '2020-07-19 21:26:59', '', NULL);
INSERT INTO `sys_dept` VALUES (203, 200, '0,100,200', '泰山保险', 3, NULL, NULL, NULL, '0', '0', 'admin', '2020-07-19 21:27:10', '', NULL);
INSERT INTO `sys_dept` VALUES (204, 200, '0,100,200', 'PICC总公司', 1, NULL, NULL, NULL, '0', '0', 'admin', '2020-07-20 10:52:14', '', NULL);
INSERT INTO `sys_dept` VALUES (205, 200, '0,100,200', '保险公司', 9, NULL, NULL, NULL, '0', '2', 'admin', '2020-07-20 15:28:00', '', NULL);
INSERT INTO `sys_dept` VALUES (206, 200, '0,100,200', '众惠保险', 4, NULL, NULL, NULL, '0', '0', 'admin', '2020-07-20 15:28:53', '', NULL);
INSERT INTO `sys_dept` VALUES (207, 200, '0,100,200', '华泰保险', 5, NULL, NULL, NULL, '0', '0', 'admin', '2020-07-22 10:55:43', '', NULL);
INSERT INTO `sys_dept` VALUES (208, 200, '0,100,200', '现代保险', 6, NULL, NULL, NULL, '0', '0', 'admin', '2020-07-22 15:36:11', '', NULL);
INSERT INTO `sys_dept` VALUES (209, 200, '0,100,200', '诚泰保险', 7, NULL, NULL, NULL, '0', '0', 'admin', '2020-07-31 09:41:46', '', NULL);
INSERT INTO `sys_dept` VALUES (210, 100, '0,100', '保险中介', 6, NULL, NULL, NULL, '0', '0', 'admin', '2020-08-02 22:31:56', '', NULL);
INSERT INTO `sys_dept` VALUES (211, 210, '0,100,210', '轻松筹', 1, NULL, NULL, NULL, '0', '0', 'admin', '2020-08-02 22:32:19', '', NULL);
INSERT INTO `sys_dept` VALUES (212, 200, '0,100,200', '大地保险', 12, NULL, NULL, NULL, '0', '0', 'admin', '2020-08-03 10:16:01', '', NULL);
INSERT INTO `sys_dept` VALUES (213, 200, '0,100,200', '太平财', 13, NULL, NULL, NULL, '0', '0', 'admin', '2020-08-06 08:30:13', '', NULL);
INSERT INTO `sys_dept` VALUES (214, 100, '0,100', '安达保险', 11, '无', NULL, NULL, '0', '2', 'admin', '2020-08-17 16:16:21', '', NULL);
INSERT INTO `sys_dept` VALUES (215, 200, '0,100,200', '安达保险', 15, '无', '', '', '0', '0', 'admin', '2020-08-17 16:16:56', 'admin', '2020-08-17 16:17:11');
INSERT INTO `sys_dept` VALUES (216, 200, '0,100,200', '鑫安保险', 15, NULL, NULL, NULL, '0', '0', 'admin', '2020-08-19 14:23:23', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(11) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 203 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES (100, 1, '已存证', '1', 'existenceState', '', 'info', 'Y', '0', 'admin', '2020-07-08 22:18:55', 'admin', '2020-07-08 22:22:59', '');
INSERT INTO `sys_dict_data` VALUES (101, 2, '未存证', '0', 'existenceState', '', 'warning', 'Y', '0', 'admin', '2020-07-08 22:19:24', 'admin', '2020-07-08 22:19:47', '');
INSERT INTO `sys_dict_data` VALUES (102, 1, '通过', '1', 'truthState', '', 'primary', 'Y', '0', 'admin', '2020-07-08 22:24:55', 'admin', '2020-07-18 11:48:50', '');
INSERT INTO `sys_dict_data` VALUES (103, 2, '不通过', '0', 'truthState', '', 'danger', 'Y', '0', 'admin', '2020-07-08 22:25:13', 'admin', '2020-07-18 11:49:41', '');
INSERT INTO `sys_dict_data` VALUES (110, 1, '监管节点', 'regulatory', 'eye_node_type', NULL, NULL, 'Y', '0', 'admin', '2020-07-16 11:31:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (111, 2, '业务节点', 'service', 'eye_node_type', NULL, NULL, 'Y', '0', 'admin', '2020-07-16 11:33:23', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (112, 3, '事件节点', 'event', 'eye_node_type', '', '', 'Y', '0', 'admin', '2020-07-16 11:34:22', 'admin', '2020-07-18 11:46:09', '');
INSERT INTO `sys_dict_data` VALUES (115, 2, '进入投保提示', 'InsurePrompt', 'eye_node_config', '', '', 'Y', '0', 'admin', '2020-07-17 14:19:19', 'admin', '2020-07-19 18:58:59', '');
INSERT INTO `sys_dict_data` VALUES (116, 3, '健康告知', 'HealthInform', 'eye_node_config', '', '', 'Y', '0', 'admin', '2020-07-17 14:19:39', 'admin', '2020-07-19 18:59:05', '');
INSERT INTO `sys_dict_data` VALUES (117, 4, '投保录入', 'InsureInput', 'eye_node_config', '', '', 'Y', '0', 'admin', '2020-07-17 14:19:52', 'admin', '2020-07-19 18:59:11', '');
INSERT INTO `sys_dict_data` VALUES (118, 5, '条款浏览', 'ClauseBrowse', 'eye_node_config', '', '', 'Y', '0', 'admin', '2020-07-17 14:20:09', 'admin', '2020-07-19 18:59:16', '');
INSERT INTO `sys_dict_data` VALUES (120, 5, '订单提交', 'OrderSubmit', 'eye_node_config', '', '', 'Y', '0', 'admin', '2020-07-17 14:20:43', 'admin', '2020-07-19 18:59:20', '');
INSERT INTO `sys_dict_data` VALUES (123, 6, '订单开始支付', 'OrderPayWay', 'eye_node_config', '', '', 'Y', '0', 'admin', '2020-07-17 14:45:28', 'admin', '2020-07-19 18:59:25', '');
INSERT INTO `sys_dict_data` VALUES (124, 7, '订单支付成功', 'OrderPaySuccess', 'eye_node_config', '', '', 'Y', '0', 'admin', '2020-07-17 14:45:55', 'admin', '2020-07-19 18:59:29', '');
INSERT INTO `sys_dict_data` VALUES (125, 2, '子事件', '0', 'oper_type', '', 'default', 'Y', '0', 'admin', '2020-07-17 16:02:07', 'admin', '2020-07-18 11:45:33', '子节点录制操作-0（也属于录制）');
INSERT INTO `sys_dict_data` VALUES (126, 1, '事件录制', '1', 'oper_type', '', 'default', 'Y', '0', 'admin', '2020-07-17 16:03:45', 'admin', '2020-07-18 11:42:33', '');
INSERT INTO `sys_dict_data` VALUES (127, 3, '页面截屏', '2', 'oper_type', '', 'default', 'Y', '0', 'admin', '2020-07-17 16:04:32', 'admin', '2020-07-18 11:45:14', '');
INSERT INTO `sys_dict_data` VALUES (128, 5, '关键操作', '4', 'oper_type', NULL, NULL, 'Y', '0', 'admin', '2020-07-18 11:44:50', '', NULL, '页面关键操作标记');
INSERT INTO `sys_dict_data` VALUES (129, 1, '质检通过', '0', 'check_status', NULL, 'default', 'Y', '0', 'admin', '2020-07-19 14:50:38', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (130, 2, '质检未通过', '1', 'check_status', NULL, 'default', 'Y', '0', 'admin', '2020-07-19 14:51:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (131, 1, '未发送', '0', 'verify_third_party', NULL, 'default', 'Y', '0', 'admin', '2020-07-19 14:52:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (132, 2, '已发送', '1', 'verify_third_party', NULL, 'default', 'Y', '0', 'admin', '2020-07-19 14:52:26', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (133, 3, '发送失败', '2', 'verify_third_party', NULL, 'default', 'Y', '0', 'admin', '2020-07-19 14:52:39', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (134, 8, '实名验证', 'FaceCheck', 'eye_node_config', '', '', 'Y', '0', 'admin', '2020-07-19 18:56:00', 'admin', '2020-07-19 18:59:36', '');
INSERT INTO `sys_dict_data` VALUES (135, 1, '产品浏览', 'ProductBrowse', 'eye_node_config', NULL, NULL, 'Y', '0', 'admin', '2020-07-19 18:58:49', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (138, 1, '未归档', '0', 'arch_status', '', '', 'Y', '0', 'admin', '2020-07-30 11:03:10', 'admin', '2020-07-30 11:29:01', '');
INSERT INTO `sys_dict_data` VALUES (139, 2, '已归档', '1', 'arch_status', '', '', 'Y', '0', 'admin', '2020-07-30 11:03:19', 'admin', '2020-07-30 11:29:08', '');
INSERT INTO `sys_dict_data` VALUES (140, 1, '否', '0', 'is_litigations', NULL, NULL, 'Y', '0', 'admin', '2020-07-30 11:04:49', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (141, 2, '是', '1', 'is_litigations', NULL, NULL, 'Y', '0', 'admin', '2020-07-30 11:04:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (142, 9, '投保确认', 'InsureConfirm', 'eye_node_config', '', '', 'Y', '0', 'admin', '2020-08-02 16:33:20', 'admin', '2020-08-02 16:33:57', '');
INSERT INTO `sys_dict_data` VALUES (145, 1, 'PC浏览器', 'PC', 'client_type', NULL, NULL, 'Y', '0', 'admin', '2020-08-29 20:55:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (146, 2, '微信H5', 'WX_H5', 'client_type', '', '', 'Y', '0', 'admin', '2020-08-29 20:55:38', 'admin', '2020-08-29 20:56:29', '微信公众号');
INSERT INTO `sys_dict_data` VALUES (147, 3, '微信小程序', 'WX_XCX', 'client_type', NULL, NULL, 'Y', '0', 'admin', '2020-08-29 20:55:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (148, 4, '微信小程序H5', 'WX_XCX_H5', 'client_type', NULL, NULL, 'Y', '0', 'admin', '2020-08-29 20:57:01', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (149, 5, '移动端H5', 'APP_H5', 'client_type', NULL, NULL, 'Y', '0', 'admin', '2020-08-29 20:57:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (150, 6, '纯原生APP', 'APP', 'client_type', '', '', 'Y', '0', 'admin', '2020-08-29 20:57:44', 'admin', '2020-08-29 20:58:19', '');
INSERT INTO `sys_dict_data` VALUES (151, 1, '标准SDK', 'G', 'sdk_type', '', 'success', 'Y', '0', 'admin', '2020-08-29 21:10:19', 'admin', '2020-08-29 21:11:50', '标准的SDK，参照标准版对接文档');
INSERT INTO `sys_dict_data` VALUES (152, 2, '简版SDK', 'S', 'sdk_type', '', 'primary', 'Y', '0', 'admin', '2020-08-29 21:11:33', 'admin', '2020-08-29 21:11:44', '简版的SDK，产品浏览自动生成token，通过样式抓取参数');
INSERT INTO `sys_dict_data` VALUES (153, 1, '活体人脸核身', '1', 'face_type', NULL, NULL, 'Y', '0', 'admin', '2020-08-29 21:26:04', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (154, 2, '活体检测', '2', 'face_type', NULL, NULL, 'Y', '0', 'admin', '2020-08-29 21:26:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (155, 3, '活体人脸对比', '3', 'face_type', NULL, NULL, 'Y', '0', 'admin', '2020-08-29 21:26:24', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (156, 4, '身份信息核验', '4', 'face_type', NULL, NULL, 'Y', '0', 'admin', '2020-08-29 21:26:35', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (188, 1, '鼎和保险网销', 'dhic-pc', 'client_code', NULL, NULL, 'Y', '0', 'admin', '2020-08-30 11:45:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (189, 1, '泰山微信', 'TSWX', 'client_code', NULL, NULL, 'Y', '0', 'admin', '2020-08-30 11:45:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (190, 1, '华泰测试', 'huatai-test', 'client_code', NULL, NULL, 'Y', '0', 'admin', '2020-08-30 11:45:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (191, 1, '现代保险微信', 'xiandai-h5', 'client_code', NULL, NULL, 'Y', '0', 'admin', '2020-08-30 11:45:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (192, 1, '诚泰保险网销', 'chengtai-pc', 'client_code', NULL, NULL, 'Y', '0', 'admin', '2020-08-30 11:45:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (193, 1, '轻松筹微信', 'QSCWX', 'client_code', NULL, NULL, 'Y', '0', 'admin', '2020-08-30 11:45:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (194, 1, '华泰测试', 'huatai', 'client_code', NULL, NULL, 'Y', '0', 'admin', '2020-08-30 11:45:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (195, 1, '安达保险微信', 'anda-wx', 'client_code', NULL, NULL, 'Y', '0', 'admin', '2020-08-30 11:45:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (196, 1, '鑫安保险', 'xinan-test', 'client_code', NULL, NULL, 'Y', '0', 'admin', '2020-08-30 11:45:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (197, 1, '太平微信', 'TPWX', 'client_code', NULL, NULL, 'Y', '0', 'admin', '2020-08-30 11:45:52', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100, '存证状态', 'existenceState', '0', 'admin', '2020-07-08 22:17:50', 'admin', '2020-07-14 12:05:32', '存证状态');
INSERT INTO `sys_dict_type` VALUES (101, '验真状态', 'truthState', '0', 'admin', '2020-07-08 22:23:50', 'admin', '2020-07-14 12:05:22', '验真状态');
INSERT INTO `sys_dict_type` VALUES (104, '节点配置', 'eye_node_config', '0', 'admin', '2020-07-14 12:02:07', 'admin', '2020-07-16 11:37:31', '节点翻译字典');
INSERT INTO `sys_dict_type` VALUES (105, '节点类型', 'eye_node_type', '0', 'admin', '2020-07-16 11:28:07', '', NULL, '节点类型');
INSERT INTO `sys_dict_type` VALUES (106, '操作类型', 'oper_type', '0', 'admin', '2020-07-17 16:01:21', '', NULL, '页面操作类型');
INSERT INTO `sys_dict_type` VALUES (107, '质检状态', 'check_status', '0', 'admin', '2020-07-19 14:50:04', '', NULL, '质检结果');
INSERT INTO `sys_dict_type` VALUES (108, '第三方存证状态', 'verify_third_party', '0', 'admin', '2020-07-19 14:51:44', 'admin', '2020-08-29 20:48:31', '第三方存证状态');
INSERT INTO `sys_dict_type` VALUES (110, '归档状态', 'arch_status', '0', 'admin', '2020-07-30 11:02:43', 'admin', '2020-07-30 11:02:51', '归档状态');
INSERT INTO `sys_dict_type` VALUES (111, '涉及诉讼', 'is_litigations', '0', 'admin', '2020-07-30 11:03:59', '', NULL, '涉及诉讼');
INSERT INTO `sys_dict_type` VALUES (114, '终端类型', 'client_type', '0', 'admin', '2020-08-29 20:54:19', 'admin', '2020-08-29 20:54:31', '渠道或浏览器的终端类型');
INSERT INTO `sys_dict_type` VALUES (115, 'SDK类型', 'sdk_type', '0', 'admin', '2020-08-29 21:08:56', '', NULL, '终端集成的SDK类型');
INSERT INTO `sys_dict_type` VALUES (116, '实名验证类型', 'face_type', '0', 'admin', '2020-08-29 21:25:13', 'admin', '2020-08-29 21:25:25', '调用实名验证的接口类型');
INSERT INTO `sys_dict_type` VALUES (117, '对接方系统/渠道', 'client_code', '0', 'admin', '2020-08-30 11:34:43', '', NULL, '系统/渠道配置时自动写入，不允许修改');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_job` VALUES (100, '清理录制信息任务', 'SYSTEM', 'eyeTask.clearRecordPages(7,1)', '0 0 1 * * ?', '3', '1', '1', '', '2020-07-20 17:29:03', '', '2020-07-21 15:26:02', '参数说明：清理 7 天前所有 + 清理 1 ~7 天无订单数据');
INSERT INTO `sys_job` VALUES (101, '清理待替换url缓存任务', 'DEFAULT', 'eyeTask.clearCache(\'fileURLCache\')', '0 0 1 * * ?', '3', '1', '1', '', '2020-07-23 18:27:55', '', '2020-07-23 18:35:25', '');
INSERT INTO `sys_job` VALUES (102, '清理已保存版本网页缓存任务', 'DEFAULT', 'eyeTask.clearCache(\'fileHtmlCache\')', '0 0 1 * * ?', '3', '1', '1', '', '2020-07-27 15:44:57', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 132 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
INSERT INTO `sys_job_log` VALUES (1, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：10毫秒', '0', '', '2020-07-15 09:21:31');
INSERT INTO `sys_job_log` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:21:46');
INSERT INTO `sys_job_log` VALUES (3, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:22:01');
INSERT INTO `sys_job_log` VALUES (4, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:22:16');
INSERT INTO `sys_job_log` VALUES (5, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:22:31');
INSERT INTO `sys_job_log` VALUES (6, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:22:46');
INSERT INTO `sys_job_log` VALUES (7, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:23:01');
INSERT INTO `sys_job_log` VALUES (8, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:23:16');
INSERT INTO `sys_job_log` VALUES (9, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:23:31');
INSERT INTO `sys_job_log` VALUES (10, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:23:46');
INSERT INTO `sys_job_log` VALUES (11, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:24:01');
INSERT INTO `sys_job_log` VALUES (12, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:24:16');
INSERT INTO `sys_job_log` VALUES (13, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:24:31');
INSERT INTO `sys_job_log` VALUES (14, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:24:46');
INSERT INTO `sys_job_log` VALUES (15, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:25:01');
INSERT INTO `sys_job_log` VALUES (16, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:25:16');
INSERT INTO `sys_job_log` VALUES (17, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:25:31');
INSERT INTO `sys_job_log` VALUES (18, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:25:46');
INSERT INTO `sys_job_log` VALUES (19, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:26:01');
INSERT INTO `sys_job_log` VALUES (20, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:26:16');
INSERT INTO `sys_job_log` VALUES (21, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:26:31');
INSERT INTO `sys_job_log` VALUES (22, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:26:46');
INSERT INTO `sys_job_log` VALUES (23, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:27:01');
INSERT INTO `sys_job_log` VALUES (24, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:27:16');
INSERT INTO `sys_job_log` VALUES (25, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:27:31');
INSERT INTO `sys_job_log` VALUES (26, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:27:46');
INSERT INTO `sys_job_log` VALUES (27, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:28:01');
INSERT INTO `sys_job_log` VALUES (28, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:28:16');
INSERT INTO `sys_job_log` VALUES (29, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:28:31');
INSERT INTO `sys_job_log` VALUES (30, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:28:46');
INSERT INTO `sys_job_log` VALUES (31, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:29:01');
INSERT INTO `sys_job_log` VALUES (32, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:29:16');
INSERT INTO `sys_job_log` VALUES (33, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:29:31');
INSERT INTO `sys_job_log` VALUES (34, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:29:46');
INSERT INTO `sys_job_log` VALUES (35, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:30:01');
INSERT INTO `sys_job_log` VALUES (36, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:30:16');
INSERT INTO `sys_job_log` VALUES (37, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:30:32');
INSERT INTO `sys_job_log` VALUES (38, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:30:46');
INSERT INTO `sys_job_log` VALUES (39, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:31:01');
INSERT INTO `sys_job_log` VALUES (40, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:31:16');
INSERT INTO `sys_job_log` VALUES (41, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:31:31');
INSERT INTO `sys_job_log` VALUES (42, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:31:46');
INSERT INTO `sys_job_log` VALUES (43, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:32:01');
INSERT INTO `sys_job_log` VALUES (44, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:32:16');
INSERT INTO `sys_job_log` VALUES (45, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:32:31');
INSERT INTO `sys_job_log` VALUES (46, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:32:46');
INSERT INTO `sys_job_log` VALUES (47, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:33:01');
INSERT INTO `sys_job_log` VALUES (48, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:33:16');
INSERT INTO `sys_job_log` VALUES (49, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:33:31');
INSERT INTO `sys_job_log` VALUES (50, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:33:46');
INSERT INTO `sys_job_log` VALUES (51, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:34:01');
INSERT INTO `sys_job_log` VALUES (52, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:34:16');
INSERT INTO `sys_job_log` VALUES (53, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:34:31');
INSERT INTO `sys_job_log` VALUES (54, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:34:46');
INSERT INTO `sys_job_log` VALUES (55, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:35:01');
INSERT INTO `sys_job_log` VALUES (56, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:35:16');
INSERT INTO `sys_job_log` VALUES (57, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:35:31');
INSERT INTO `sys_job_log` VALUES (58, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:35:46');
INSERT INTO `sys_job_log` VALUES (59, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:36:01');
INSERT INTO `sys_job_log` VALUES (60, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:36:16');
INSERT INTO `sys_job_log` VALUES (61, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:36:31');
INSERT INTO `sys_job_log` VALUES (62, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:36:46');
INSERT INTO `sys_job_log` VALUES (63, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:37:01');
INSERT INTO `sys_job_log` VALUES (64, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:37:16');
INSERT INTO `sys_job_log` VALUES (65, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:37:31');
INSERT INTO `sys_job_log` VALUES (66, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:37:46');
INSERT INTO `sys_job_log` VALUES (67, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:38:01');
INSERT INTO `sys_job_log` VALUES (68, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:38:16');
INSERT INTO `sys_job_log` VALUES (69, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:38:31');
INSERT INTO `sys_job_log` VALUES (70, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:38:46');
INSERT INTO `sys_job_log` VALUES (71, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:39:01');
INSERT INTO `sys_job_log` VALUES (72, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:39:16');
INSERT INTO `sys_job_log` VALUES (73, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:39:31');
INSERT INTO `sys_job_log` VALUES (74, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:39:46');
INSERT INTO `sys_job_log` VALUES (75, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:40:01');
INSERT INTO `sys_job_log` VALUES (76, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:40:16');
INSERT INTO `sys_job_log` VALUES (77, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:40:31');
INSERT INTO `sys_job_log` VALUES (78, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:40:46');
INSERT INTO `sys_job_log` VALUES (79, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:41:01');
INSERT INTO `sys_job_log` VALUES (80, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:41:16');
INSERT INTO `sys_job_log` VALUES (81, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:41:31');
INSERT INTO `sys_job_log` VALUES (82, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:41:46');
INSERT INTO `sys_job_log` VALUES (83, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:42:01');
INSERT INTO `sys_job_log` VALUES (84, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:42:16');
INSERT INTO `sys_job_log` VALUES (85, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:42:31');
INSERT INTO `sys_job_log` VALUES (86, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:42:46');
INSERT INTO `sys_job_log` VALUES (87, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:43:01');
INSERT INTO `sys_job_log` VALUES (88, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:43:16');
INSERT INTO `sys_job_log` VALUES (89, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:43:31');
INSERT INTO `sys_job_log` VALUES (90, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:43:46');
INSERT INTO `sys_job_log` VALUES (91, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:44:01');
INSERT INTO `sys_job_log` VALUES (92, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:44:16');
INSERT INTO `sys_job_log` VALUES (93, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:44:31');
INSERT INTO `sys_job_log` VALUES (94, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:44:46');
INSERT INTO `sys_job_log` VALUES (95, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:45:01');
INSERT INTO `sys_job_log` VALUES (96, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:45:16');
INSERT INTO `sys_job_log` VALUES (97, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:45:31');
INSERT INTO `sys_job_log` VALUES (98, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:45:46');
INSERT INTO `sys_job_log` VALUES (99, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:46:01');
INSERT INTO `sys_job_log` VALUES (100, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:46:16');
INSERT INTO `sys_job_log` VALUES (101, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:46:31');
INSERT INTO `sys_job_log` VALUES (102, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:46:46');
INSERT INTO `sys_job_log` VALUES (103, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:47:01');
INSERT INTO `sys_job_log` VALUES (104, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:47:16');
INSERT INTO `sys_job_log` VALUES (105, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:47:31');
INSERT INTO `sys_job_log` VALUES (106, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:47:46');
INSERT INTO `sys_job_log` VALUES (107, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:48:01');
INSERT INTO `sys_job_log` VALUES (108, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:48:16');
INSERT INTO `sys_job_log` VALUES (109, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：6毫秒', '0', '', '2020-07-15 09:48:31');
INSERT INTO `sys_job_log` VALUES (110, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:48:46');
INSERT INTO `sys_job_log` VALUES (111, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:49:01');
INSERT INTO `sys_job_log` VALUES (112, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:49:16');
INSERT INTO `sys_job_log` VALUES (113, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:49:31');
INSERT INTO `sys_job_log` VALUES (114, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:49:46');
INSERT INTO `sys_job_log` VALUES (115, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:50:01');
INSERT INTO `sys_job_log` VALUES (116, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:50:16');
INSERT INTO `sys_job_log` VALUES (117, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:50:31');
INSERT INTO `sys_job_log` VALUES (118, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:50:46');
INSERT INTO `sys_job_log` VALUES (119, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:51:01');
INSERT INTO `sys_job_log` VALUES (120, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:51:16');
INSERT INTO `sys_job_log` VALUES (121, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:51:31');
INSERT INTO `sys_job_log` VALUES (122, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:51:46');
INSERT INTO `sys_job_log` VALUES (123, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:52:01');
INSERT INTO `sys_job_log` VALUES (124, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：1毫秒', '0', '', '2020-07-15 09:52:16');
INSERT INTO `sys_job_log` VALUES (125, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:52:31');
INSERT INTO `sys_job_log` VALUES (126, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:52:46');
INSERT INTO `sys_job_log` VALUES (127, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:53:01');
INSERT INTO `sys_job_log` VALUES (128, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:53:16');
INSERT INTO `sys_job_log` VALUES (129, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:53:31');
INSERT INTO `sys_job_log` VALUES (130, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：99毫秒', '0', '', '2020-07-15 09:53:51');
INSERT INTO `sys_job_log` VALUES (131, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '系统默认（有参） 总共耗时：0毫秒', '0', '', '2020-07-15 09:54:00');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (1, 'admin', '127.0.0.1', '内网', 'Chrome', 'Windows 10', '1', '密码输入错误1次', '2020-08-29 20:32:42');
INSERT INTO `sys_logininfor` VALUES (2, 'admin', '127.0.0.1', '内网', 'Chrome', 'Windows 10', '0', '登录成功', '2020-08-29 20:32:48');
INSERT INTO `sys_logininfor` VALUES (3, 'admin', '127.0.0.1', '内网', 'Chrome', 'Windows 10', '0', '登录成功', '2020-08-29 22:47:29');
INSERT INTO `sys_logininfor` VALUES (4, 'admin', '127.0.0.1', '内网', 'Chrome', 'Windows 10', '1', '验证码错误', '2020-08-29 23:13:07');
INSERT INTO `sys_logininfor` VALUES (5, 'admin', '127.0.0.1', '内网', 'Chrome', 'Windows 10', '0', '登录成功', '2020-08-29 23:13:12');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2058 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统配置', 0, 5, '#', 'menuItem', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'admin', '2020-07-08 00:12:33', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 6, '#', 'menuItem', 'M', '0', '', 'fa fa-heartbeat', 'admin', '2018-03-16 11:33:00', 'admin', '2020-07-08 00:32:41', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 7, '#', 'menuItem', 'M', '0', '', 'fa fa-wrench', 'admin', '2018-03-16 11:33:00', 'admin', '2020-07-08 00:16:26', '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 2001, 1, '/system/user', 'menuItem', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2020-07-08 00:14:19', '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 2001, 2, '/system/role', 'menuItem', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2020-07-08 00:14:32', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', '', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 2001, 4, '/system/dept', 'menuItem', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2020-07-08 00:14:44', '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 2001, 5, '/system/post', 'menuItem', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2020-07-08 00:14:57', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', '', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', '', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, '/system/notice', '', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 2, 9, '#', 'menuItem', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2020-07-20 16:16:57', '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, '/monitor/online', '', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 1, 2, '/monitor/job', 'menuItem', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'admin', '2020-08-29 21:37:22', '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, '/monitor/data', '', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 3, '/monitor/server', '', 'C', '0', 'monitor:server:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '表单构建', 3, 1, '/tool/build', '', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES (114, '代码生成', 3, 2, '/tool/gen', '', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES (115, '系统接口', 3, 3, '/tool/swagger', '', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, '/monitor/operlog', '', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, '/monitor/logininfor', '', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', '', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', '', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', '', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', '', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', '', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '#', '', 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '#', '', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '#', '', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '#', '', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '#', '', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '#', '', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '#', '', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '#', '', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '#', '', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '#', '', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '#', '', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '#', '', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '#', '', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '#', '', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '#', '', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '#', '', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '#', '', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '#', '', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '#', '', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '#', '', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1041, '详细信息', 500, 3, '#', '', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1046, '账户解锁', 501, 4, '#', '', 'F', '0', 'monitor:logininfor:unlock', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1047, '在线查询', 109, 1, '#', '', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1048, '批量强退', 109, 2, '#', '', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1049, '单条强退', 109, 3, '#', '', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1050, '任务查询', 110, 1, '#', '', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1051, '任务新增', 110, 2, '#', '', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1052, '任务修改', 110, 3, '#', '', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1053, '任务删除', 110, 4, '#', '', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1054, '状态修改', 110, 5, '#', '', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1055, '任务详细', 110, 6, '#', '', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1056, '任务导出', 110, 7, '#', '', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1057, '生成查询', 114, 1, '#', '', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1058, '生成修改', 114, 2, '#', '', 'F', '0', 'tool:gen:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1059, '生成删除', 114, 3, '#', '', 'F', '0', 'tool:gen:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1060, '预览代码', 114, 4, '#', '', 'F', '0', 'tool:gen:preview', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (1061, '生成代码', 114, 5, '#', '', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES (2000, '回溯管理', 0, 1, '#', 'menuItem', 'M', '0', '', 'fa fa-desktop', 'admin', '2020-07-07 23:49:30', 'admin', '2020-08-29 21:38:26', '');
INSERT INTO `sys_menu` VALUES (2001, '权限管理', 0, 4, '#', 'menuItem', 'M', '0', '', 'fa fa-address-book-o', 'admin', '2020-07-08 00:14:06', 'admin', '2020-07-08 00:15:10', '');
INSERT INTO `sys_menu` VALUES (2007, '验真管理', 0, 3, '#', 'menuItem', 'M', '0', '', 'fa fa-check', 'admin', '2020-07-08 20:20:49', 'admin', '2020-07-16 23:11:21', '');
INSERT INTO `sys_menu` VALUES (2011, '未完成订单查询', 2000, 1, '/eye/recordpage', 'menuItem', 'C', '0', 'eye:recordpage:view', '#', 'admin', '2020-07-16 22:55:19', 'admin', '2020-08-29 21:38:41', '');
INSERT INTO `sys_menu` VALUES (2012, '已完成订单查询', 2000, 2, '/eye/order', 'menuItem', 'C', '0', 'eye:order:view', '#', 'admin', '2020-07-16 22:55:52', 'admin', '2020-08-29 21:38:48', '');
INSERT INTO `sys_menu` VALUES (2013, '质检预警报告', 2000, 4, '/eye/order/orderQualitylist', 'menuItem', 'C', '0', 'eye:order:view', '#', 'admin', '2020-07-16 23:03:00', 'admin', '2020-07-19 21:44:31', '');
INSERT INTO `sys_menu` VALUES (2014, '行为统计报告', 2000, 6, '/eye/recordclearreport', 'menuItem', 'C', '0', 'eye:recordpage:view', '#', 'admin', '2020-07-16 23:06:23', 'admin', '2020-07-31 10:43:28', '');
INSERT INTO `sys_menu` VALUES (2015, '回溯签名查询', 2007, 1, '/eye/recallmanage/recallOfTruth', 'menuItem', 'C', '0', 'eye:order:view', '#', 'admin', '2020-07-16 23:09:10', 'admin', '2020-07-18 21:44:10', '');
INSERT INTO `sys_menu` VALUES (2017, '回溯节点配置', 2050, 2, '/eye/nodeconfig', 'menuItem', 'C', '0', 'eye:nodeconfig:view', '#', 'admin', '2020-07-17 09:56:29', 'admin', '2020-08-29 21:40:17', '');
INSERT INTO `sys_menu` VALUES (2018, '查询按钮', 2011, 1, '#', 'menuItem', 'F', '0', 'eye:recordpage:list', '#', 'admin', '2020-07-18 17:12:28', 'admin', '2020-07-18 17:13:38', '');
INSERT INTO `sys_menu` VALUES (2020, '查询浏览', 2012, 1, '#', 'menuItem', 'F', '0', 'eye:order:list', '#', 'admin', '2020-07-18 17:15:43', 'admin', '2020-07-18 17:22:29', '');
INSERT INTO `sys_menu` VALUES (2021, '节点查询', 2017, 1, '#', 'menuItem', 'F', '0', 'eye:nodeconfig:list', '#', 'admin', '2020-07-19 21:36:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2022, '节点增加', 2017, 2, '#', 'menuItem', 'F', '0', 'eye:nodeconfig:add', '#', 'admin', '2020-07-19 21:37:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2023, '节点修改', 2017, 5, '#', 'menuItem', 'F', '0', 'eye:nodeconfig:edit', '#', 'admin', '2020-07-19 21:38:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2025, '页面版本查询', 2000, 9, '/eye/pageversion', 'menuItem', 'C', '0', 'eye:pageversion:view', '#', 'admin', '2020-07-23 14:44:44', 'admin', '2020-08-29 21:31:58', '');
INSERT INTO `sys_menu` VALUES (2027, '查询浏览', 2014, 1, '#', 'menuItem', 'F', '0', 'eye:recordclearreport:list', '#', 'admin', '2020-07-31 10:47:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2028, '查询浏览', 2025, 1, '#', 'menuItem', 'F', '0', 'eye:pageversion:list', '#', 'admin', '2020-07-31 10:48:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2029, '查看版本', 2025, 2, '#', 'menuItem', 'F', '0', 'eye:pageversion:edit', '#', 'admin', '2020-07-31 10:57:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2030, '查询浏览', 2013, 1, '#', 'menuItem', 'F', '0', 'eye:order:list', '#', 'admin', '2020-07-31 11:00:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2031, '回放', 2013, 2, '#', 'menuItem', 'F', '0', 'eye:order:edit', '#', 'admin', '2020-07-31 11:01:45', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2041, '页面对接配置', 2050, 3, '/eye/pageconfig', 'menuItem', 'C', '0', 'eye:pageconfig:view', '#', 'admin', '2020-08-02 15:50:02', 'admin', '2020-08-29 21:35:04', '');
INSERT INTO `sys_menu` VALUES (2043, '页面节点查询', 2041, 1, '#', 'menuItem', 'F', '0', 'eye:pageconfig:list', '#', 'admin', '2020-08-03 09:35:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2045, '实名验证日志', 2007, 1, '/eye/faceapilog', 'menuItem', 'C', '0', 'eye:faceapilog:view', '#', 'admin', '2020-08-16 15:55:54', 'admin', '2020-08-29 21:34:00', '');
INSERT INTO `sys_menu` VALUES (2046, '实名验证接口查询', 2045, 1, '#', 'menuItem', 'F', '0', 'eye:faceapilog:list', '#', 'admin', '2020-08-16 16:28:14', 'admin', '2020-08-16 17:11:09', '');
INSERT INTO `sys_menu` VALUES (2048, '对接系统/渠道配置', 2050, 1, '/eye/clientconfig', 'menuItem', 'C', '0', 'eye:clientconfig:view', '#', 'admin', '2020-08-16 17:12:25', 'admin', '2020-08-30 10:35:05', '');
INSERT INTO `sys_menu` VALUES (2050, '回溯配置', 0, 2, '#', 'menuItem', 'M', '0', '', 'fa fa-gears', 'admin', '2018-03-01 00:00:00', 'admin', '2020-08-29 21:30:31', '');
INSERT INTO `sys_menu` VALUES (2051, '人工质检轨迹', 2000, 8, '/eye/checklog', 'menuItem', 'C', '0', 'system:checklog:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2020-08-29 21:31:20', '');
INSERT INTO `sys_menu` VALUES (2052, '系统/渠道配置查询', 2048, 1, '#', '', 'F', '0', 'eye:clientconfig:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2053, '系统/渠道配置新增', 2048, 2, '#', '', 'F', '0', 'eye:clientconfig:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2054, '系统/渠道配置修改', 2048, 3, '#', '', 'F', '0', 'eye:clientconfig:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2055, '系统/渠道配置删除', 2048, 4, '#', '', 'F', '0', 'eye:clientconfig:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2056, '系统/渠道配置导出', 2048, 5, '#', '', 'F', '0', 'eye:clientconfig:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES (2057, '查看对接密钥', 2048, 7, '#', 'menuItem', 'F', '0', 'eye:clientconfig:viewKey', '#', 'admin', '2020-08-30 11:27:07', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 WebEye新版本发布啦', '2', '新版本内容', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 WebEye系统凌晨维护', '1', '维护内容', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(11) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(11) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(11) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (1, '渠道管理', 1, 'ins.webeye.project.system.eye.controller.EyeAgentConfigController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/agentconfig/add', '127.0.0.1', '内网', '{\"agentCode\":[\"test\"],\"agentName\":[\"set\"],\"clientType\":[\"set\"],\"contact\":[\"stet\"],\"authorizeId\":[\"xxxx\"],\"authorizeKey\":[\"xxxxx\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:38:10');
INSERT INTO `sys_oper_log` VALUES (2, '字典类型', 2, 'ins.webeye.project.system.dict.controller.DictTypeController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/edit', '127.0.0.1', '内网', '{\"dictId\":[\"108\"],\"dictName\":[\"第三方存证状态\"],\"dictType\":[\"verify_third_party\"],\"status\":[\"0\"],\"remark\":[\"第三方存证状态\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:48:31');
INSERT INTO `sys_oper_log` VALUES (3, '字典类型', 1, 'ins.webeye.project.system.dict.controller.DictTypeController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/add', '127.0.0.1', '内网', '{\"dictName\":[\"终端类型\"],\"dictType\":[\"client_type\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:54:19');
INSERT INTO `sys_oper_log` VALUES (4, '字典类型', 2, 'ins.webeye.project.system.dict.controller.DictTypeController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/edit', '127.0.0.1', '内网', '{\"dictId\":[\"114\"],\"dictName\":[\"终端类型\"],\"dictType\":[\"client_type\"],\"status\":[\"0\"],\"remark\":[\"渠道或浏览器的终端类型\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:54:31');
INSERT INTO `sys_oper_log` VALUES (5, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"PC浏览器\"],\"dictValue\":[\"PC\"],\"dictType\":[\"client_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:55:00');
INSERT INTO `sys_oper_log` VALUES (6, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"微信公众号\"],\"dictValue\":[\"WX_H5\"],\"dictType\":[\"client_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"2\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:55:38');
INSERT INTO `sys_oper_log` VALUES (7, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"微信小程序\"],\"dictValue\":[\"WX_XCX\\t\"],\"dictType\":[\"client_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"3\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:55:51');
INSERT INTO `sys_oper_log` VALUES (8, '字典数据', 2, 'ins.webeye.project.system.dict.controller.DictDataController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/edit', '127.0.0.1', '内网', '{\"dictCode\":[\"146\"],\"dictLabel\":[\"微信H5\"],\"dictValue\":[\"WX_H5\"],\"dictType\":[\"client_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"2\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"微信公众号\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:56:29');
INSERT INTO `sys_oper_log` VALUES (9, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"微信小程序H5\"],\"dictValue\":[\"WX_XCX_H5\"],\"dictType\":[\"client_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"4\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:57:01');
INSERT INTO `sys_oper_log` VALUES (10, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"移动端H5\"],\"dictValue\":[\"APP_H5\"],\"dictType\":[\"client_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"5\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:57:18');
INSERT INTO `sys_oper_log` VALUES (11, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"原生APP\"],\"dictValue\":[\"APP\"],\"dictType\":[\"client_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"6\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:57:44');
INSERT INTO `sys_oper_log` VALUES (12, '字典数据', 2, 'ins.webeye.project.system.dict.controller.DictDataController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/edit', '127.0.0.1', '内网', '{\"dictCode\":[\"150\"],\"dictLabel\":[\"纯原生APP\"],\"dictValue\":[\"APP\"],\"dictType\":[\"client_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"6\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 20:58:19');
INSERT INTO `sys_oper_log` VALUES (13, '字典类型', 1, 'ins.webeye.project.system.dict.controller.DictTypeController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/add', '127.0.0.1', '内网', '{\"dictName\":[\"SDK类型\"],\"dictType\":[\"sdk_type\"],\"status\":[\"0\"],\"remark\":[\"终端集成的SDK类型\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:08:56');
INSERT INTO `sys_oper_log` VALUES (14, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"标准SDK\"],\"dictValue\":[\"G\"],\"dictType\":[\"sdk_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"标准的SDK，参照标准版对接文档\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:10:19');
INSERT INTO `sys_oper_log` VALUES (15, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"简版SDK\"],\"dictValue\":[\"S\"],\"dictType\":[\"sdk_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"2\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"简版的SDK，产品浏览自动生成token，通过样式抓取参数\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:11:33');
INSERT INTO `sys_oper_log` VALUES (16, '字典数据', 2, 'ins.webeye.project.system.dict.controller.DictDataController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/edit', '127.0.0.1', '内网', '{\"dictCode\":[\"152\"],\"dictLabel\":[\"简版SDK\"],\"dictValue\":[\"S\"],\"dictType\":[\"sdk_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"2\"],\"listClass\":[\"primary\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"简版的SDK，产品浏览自动生成token，通过样式抓取参数\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:11:44');
INSERT INTO `sys_oper_log` VALUES (17, '字典数据', 2, 'ins.webeye.project.system.dict.controller.DictDataController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/edit', '127.0.0.1', '内网', '{\"dictCode\":[\"151\"],\"dictLabel\":[\"标准SDK\"],\"dictValue\":[\"G\"],\"dictType\":[\"sdk_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"success\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"标准的SDK，参照标准版对接文档\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:11:50');
INSERT INTO `sys_oper_log` VALUES (18, '字典数据', 3, 'ins.webeye.project.system.dict.controller.DictDataController.remove()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/remove', '127.0.0.1', '内网', '{\"ids\":[\"143,144\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:15:21');
INSERT INTO `sys_oper_log` VALUES (19, '字典类型', 3, 'ins.webeye.project.system.dict.controller.DictTypeController.remove()', 'POST', 1, 'admin', '研发部门', '/system/dict/remove', '127.0.0.1', '内网', '{\"ids\":[\"113\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:15:32');
INSERT INTO `sys_oper_log` VALUES (20, '字典类型', 1, 'ins.webeye.project.system.dict.controller.DictTypeController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/add', '127.0.0.1', '内网', '{\"dictName\":[\"实名验证类型\"],\"dictType\":[\"face_type\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:25:13');
INSERT INTO `sys_oper_log` VALUES (21, '字典类型', 2, 'ins.webeye.project.system.dict.controller.DictTypeController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/edit', '127.0.0.1', '内网', '{\"dictId\":[\"116\"],\"dictName\":[\"实名验证类型\"],\"dictType\":[\"face_type\"],\"status\":[\"0\"],\"remark\":[\"调用实名验证的接口类型\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:25:25');
INSERT INTO `sys_oper_log` VALUES (22, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"活体人脸核身\"],\"dictValue\":[\"1\"],\"dictType\":[\"face_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"1\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:26:04');
INSERT INTO `sys_oper_log` VALUES (23, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"活体检测\"],\"dictValue\":[\"2\"],\"dictType\":[\"face_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"2\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:26:14');
INSERT INTO `sys_oper_log` VALUES (24, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"活体人脸对比\"],\"dictValue\":[\"3\"],\"dictType\":[\"face_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"3\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:26:24');
INSERT INTO `sys_oper_log` VALUES (25, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"身份信息核验\"],\"dictValue\":[\"4\"],\"dictType\":[\"face_type\"],\"cssClass\":[\"\"],\"dictSort\":[\"4\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:26:35');
INSERT INTO `sys_oper_log` VALUES (26, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2050\"],\"parentId\":[\"0\"],\"menuType\":[\"M\"],\"menuName\":[\"回溯配置\"],\"url\":[\"#\"],\"target\":[\"menuItem\"],\"perms\":[\"\"],\"orderNum\":[\"2\"],\"icon\":[\"fa fa-gears\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:30:31');
INSERT INTO `sys_oper_log` VALUES (27, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2051\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"人工质检轨迹\"],\"url\":[\"/system/checklog\"],\"target\":[\"menuItem\"],\"perms\":[\"system:checklog:view\"],\"orderNum\":[\"8\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:31:20');
INSERT INTO `sys_oper_log` VALUES (28, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2025\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"页面版本查询\"],\"url\":[\"/system/pageversion\"],\"target\":[\"menuItem\"],\"perms\":[\"system:pageversion:view\"],\"orderNum\":[\"9\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:31:58');
INSERT INTO `sys_oper_log` VALUES (29, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2041\"],\"parentId\":[\"2050\"],\"menuType\":[\"C\"],\"menuName\":[\"页面节点配置\"],\"url\":[\"/system/pagemanage\"],\"target\":[\"menuItem\"],\"perms\":[\"eye:pagemanage:view\"],\"orderNum\":[\"3\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:32:20');
INSERT INTO `sys_oper_log` VALUES (30, '菜单管理', 3, 'ins.webeye.project.system.menu.controller.MenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/2016', '127.0.0.1', '内网', '{}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:33:03');
INSERT INTO `sys_oper_log` VALUES (31, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2045\"],\"parentId\":[\"2007\"],\"menuType\":[\"C\"],\"menuName\":[\"实名验证日志\"],\"url\":[\"/system/faceapilog\"],\"target\":[\"menuItem\"],\"perms\":[\"eye:faceapilog:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:34:00');
INSERT INTO `sys_oper_log` VALUES (32, '菜单管理', 3, 'ins.webeye.project.system.menu.controller.MenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/2044', '127.0.0.1', '内网', '{}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:34:08');
INSERT INTO `sys_oper_log` VALUES (33, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2048\"],\"parentId\":[\"2050\"],\"menuType\":[\"C\"],\"menuName\":[\"渠道配置\"],\"url\":[\"/system/agentconfig\"],\"target\":[\"menuItem\"],\"perms\":[\"eye:agentconfig:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:34:22');
INSERT INTO `sys_oper_log` VALUES (34, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2017\"],\"parentId\":[\"2050\"],\"menuType\":[\"C\"],\"menuName\":[\"节点配置\"],\"url\":[\"/system/nodeconfig\"],\"target\":[\"menuItem\"],\"perms\":[\"system:nodeconfig:view\"],\"orderNum\":[\"2\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:34:34');
INSERT INTO `sys_oper_log` VALUES (35, '菜单管理', 3, 'ins.webeye.project.system.menu.controller.MenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/2047', '127.0.0.1', '内网', '{}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:34:44');
INSERT INTO `sys_oper_log` VALUES (36, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2041\"],\"parentId\":[\"2050\"],\"menuType\":[\"C\"],\"menuName\":[\"页面对接配置\"],\"url\":[\"/system/pagemanage\"],\"target\":[\"menuItem\"],\"perms\":[\"eye:pagemanage:view\"],\"orderNum\":[\"3\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:35:04');
INSERT INTO `sys_oper_log` VALUES (37, '菜单管理', 3, 'ins.webeye.project.system.menu.controller.MenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/2042', '127.0.0.1', '内网', '{}', '{\"code\":301,\"msg\":\"菜单已分配,不允许删除\"}', 0, NULL, '2020-08-29 21:35:11');
INSERT INTO `sys_oper_log` VALUES (38, '菜单管理', 3, 'ins.webeye.project.system.menu.controller.MenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/2042', '127.0.0.1', '内网', '{}', '{\"code\":301,\"msg\":\"菜单已分配,不允许删除\"}', 0, NULL, '2020-08-29 21:35:19');
INSERT INTO `sys_oper_log` VALUES (39, '菜单管理', 3, 'ins.webeye.project.system.menu.controller.MenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/2042', '127.0.0.1', '内网', '{}', '{\"code\":301,\"msg\":\"菜单已分配,不允许删除\"}', 0, NULL, '2020-08-29 21:35:35');
INSERT INTO `sys_oper_log` VALUES (40, '角色管理', 2, 'ins.webeye.project.system.role.controller.RoleController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网', '{\"roleId\":[\"100\"],\"roleName\":[\"对接测试人员\"],\"roleKey\":[\"eyetest\"],\"roleSort\":[\"3\"],\"status\":[\"0\"],\"remark\":[\"提供给回溯测试人员使用\"],\"menuIds\":[\"2000,2011,2018,2012,2020,2013,2030,2031,2014,2027,2025,2028,2029,2050,2048,2049,2017,2021,2022,2023,2041,2043,1,2,109,1047,1048,1049,110,1050,1051,1052,1053,1054,1055,1056,111,112,3,113,115\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:36:02');
INSERT INTO `sys_oper_log` VALUES (41, '菜单管理', 3, 'ins.webeye.project.system.menu.controller.MenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/2042', '127.0.0.1', '内网', '{}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:36:08');
INSERT INTO `sys_oper_log` VALUES (42, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"110\"],\"parentId\":[\"1\"],\"menuType\":[\"C\"],\"menuName\":[\"定时任务\"],\"url\":[\"/monitor/job\"],\"target\":[\"menuItem\"],\"perms\":[\"monitor:job:view\"],\"orderNum\":[\"6\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:37:00');
INSERT INTO `sys_oper_log` VALUES (43, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"110\"],\"parentId\":[\"1\"],\"menuType\":[\"C\"],\"menuName\":[\"定时任务\"],\"url\":[\"/monitor/job\"],\"target\":[\"menuItem\"],\"perms\":[\"monitor:job:view\"],\"orderNum\":[\"2\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:37:22');
INSERT INTO `sys_oper_log` VALUES (44, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2000\"],\"parentId\":[\"0\"],\"menuType\":[\"M\"],\"menuName\":[\"回溯管理\"],\"url\":[\"#\"],\"target\":[\"menuItem\"],\"perms\":[\"\"],\"orderNum\":[\"1\"],\"icon\":[\"fa fa-desktop\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:38:26');
INSERT INTO `sys_oper_log` VALUES (45, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2011\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"未完成订单查询\"],\"url\":[\"/system/recordpage\"],\"target\":[\"menuItem\"],\"perms\":[\"eye:recordpage:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:38:41');
INSERT INTO `sys_oper_log` VALUES (46, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2012\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"已完成订单查询\"],\"url\":[\"/system/order\"],\"target\":[\"menuItem\"],\"perms\":[\"eye:order:view\"],\"orderNum\":[\"2\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:38:48');
INSERT INTO `sys_oper_log` VALUES (47, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2048\"],\"parentId\":[\"2050\"],\"menuType\":[\"C\"],\"menuName\":[\"系统/渠道配置\"],\"url\":[\"/system/agentconfig\"],\"target\":[\"menuItem\"],\"perms\":[\"eye:agentconfig:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:40:03');
INSERT INTO `sys_oper_log` VALUES (48, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2017\"],\"parentId\":[\"2050\"],\"menuType\":[\"C\"],\"menuName\":[\"回溯节点配置\"],\"url\":[\"/system/nodeconfig\"],\"target\":[\"menuItem\"],\"perms\":[\"system:nodeconfig:view\"],\"orderNum\":[\"2\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-29 21:40:17');
INSERT INTO `sys_oper_log` VALUES (49, '代码生成', 3, 'ins.webeye.project.tool.gen.controller.GenController.remove()', 'POST', 1, 'admin', '研发部门', '/tool/gen/remove', '127.0.0.1', '内网', '{\"ids\":[\"13,14,10,2\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 01:02:41');
INSERT INTO `sys_oper_log` VALUES (50, '代码生成', 6, 'ins.webeye.project.tool.gen.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网', '{\"tables\":[\"eye_client_config\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 01:02:49');
INSERT INTO `sys_oper_log` VALUES (51, '代码生成', 2, 'ins.webeye.project.tool.gen.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网', '{\"tableId\":[\"15\"],\"tableName\":[\"eye_client_config\"],\"tableComment\":[\"对接的系统或渠道配置\"],\"className\":[\"EyeClientConfig\"],\"functionAuthor\":[\"webeye\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"287\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"288\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"系统/渠道代码\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"clientCode\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"289\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"系统/渠道名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"clientName\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"290\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"终端类型\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"clientType\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"select\"],\"columns[3].dictType\":[\"client_type\"],\"columns[4].columnId\":[\"291\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"系统域名\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"clientUrl\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"292\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"SDK类型\"],\"', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 01:08:48');
INSERT INTO `sys_oper_log` VALUES (52, '代码生成', 2, 'ins.webeye.project.tool.gen.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网', '{\"tableId\":[\"15\"],\"tableName\":[\"eye_client_config\"],\"tableComment\":[\"对接的系统或渠道配置\"],\"className\":[\"EyeClientConfig\"],\"functionAuthor\":[\"webeye\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"287\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"288\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"系统/渠道代码\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"clientCode\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"289\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"系统/渠道名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"clientName\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"290\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"终端类型\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"clientType\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"select\"],\"columns[3].dictType\":[\"client_type\"],\"columns[4].columnId\":[\"291\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"系统域名\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"clientUrl\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"292\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"SDK类型\"],\"', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 01:10:10');
INSERT INTO `sys_oper_log` VALUES (53, '代码生成', 2, 'ins.webeye.project.tool.gen.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网', '{\"tableId\":[\"15\"],\"tableName\":[\"eye_client_config\"],\"tableComment\":[\"对接的系统或渠道配置\"],\"className\":[\"EyeClientConfig\"],\"functionAuthor\":[\"webeye\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"287\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"288\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"系统/渠道代码\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"clientCode\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"289\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"系统/渠道名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"clientName\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"290\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"终端类型\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"clientType\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"select\"],\"columns[3].dictType\":[\"client_type\"],\"columns[4].columnId\":[\"291\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"系统域名\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"clientUrl\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"292\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"SDK类型\"],\"', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 01:11:41');
INSERT INTO `sys_oper_log` VALUES (54, '代码生成', 8, 'ins.webeye.project.tool.gen.controller.GenController.genCode()', 'GET', 1, 'admin', '研发部门', '/tool/gen/genCode/eye_client_config', '127.0.0.1', '内网', '{}', 'null', 0, NULL, '2020-08-30 01:12:50');
INSERT INTO `sys_oper_log` VALUES (55, '代码生成', 2, 'ins.webeye.project.tool.gen.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网', '{\"tableId\":[\"15\"],\"tableName\":[\"eye_client_config\"],\"tableComment\":[\"对接的系统或渠道配置\"],\"className\":[\"EyeClientConfig\"],\"functionAuthor\":[\"webeye\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"287\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"288\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"系统/渠道代码\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"clientCode\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"289\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"系统/渠道名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"clientName\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"290\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"终端类型\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"clientType\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"select\"],\"columns[3].dictType\":[\"client_type\"],\"columns[4].columnId\":[\"291\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"系统域名\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"clientUrl\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"292\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"SDK类型\"],\"', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 01:15:36');
INSERT INTO `sys_oper_log` VALUES (56, '代码生成', 2, 'ins.webeye.project.tool.gen.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网', '{\"tableId\":[\"15\"],\"tableName\":[\"eye_client_config\"],\"tableComment\":[\"对接的系统或渠道配置\"],\"className\":[\"EyeClientConfig\"],\"functionAuthor\":[\"webeye\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"287\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"288\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"系统/渠道代码\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"clientCode\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"289\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"系统/渠道名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"clientName\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"290\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"终端类型\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"clientType\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"select\"],\"columns[3].dictType\":[\"client_type\"],\"columns[4].columnId\":[\"291\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"系统域名\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"clientUrl\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"292\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"SDK类型\"],\"', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 01:16:04');
INSERT INTO `sys_oper_log` VALUES (57, '代码生成', 8, 'ins.webeye.project.tool.gen.controller.GenController.genCode()', 'GET', 1, 'admin', '研发部门', '/tool/gen/genCode/eye_client_config', '127.0.0.1', '内网', '{}', 'null', 0, NULL, '2020-08-30 01:16:25');
INSERT INTO `sys_oper_log` VALUES (58, '代码生成', 2, 'ins.webeye.project.tool.gen.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网', '{\"tableId\":[\"15\"],\"tableName\":[\"eye_client_config\"],\"tableComment\":[\"对接的系统或渠道配置\"],\"className\":[\"EyeClientConfig\"],\"functionAuthor\":[\"webeye\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"287\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"288\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"系统/渠道代码\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"clientCode\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"289\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"系统/渠道名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"clientName\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"290\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"终端类型\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"clientType\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"select\"],\"columns[3].dictType\":[\"client_type\"],\"columns[4].columnId\":[\"291\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"系统域名\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"clientUrl\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"292\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"SDK类型\"],\"', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 01:55:15');
INSERT INTO `sys_oper_log` VALUES (59, '代码生成', 8, 'ins.webeye.project.tool.gen.controller.GenController.genCode()', 'GET', 1, 'admin', '研发部门', '/tool/gen/genCode/eye_client_config', '127.0.0.1', '内网', '{}', 'null', 0, NULL, '2020-08-30 01:55:20');
INSERT INTO `sys_oper_log` VALUES (60, '系统/渠道配置', 2, 'ins.webeye.project.eye.clientconfig.controller.EyeClientConfigController.editSave()', 'POST', 1, 'admin', '研发部门', '/eye/clientconfig/edit', '127.0.0.1', '内网', '{\"id\":[\"363\"],\"clientCode\":[\"test\"],\"clientName\":[\"set\"],\"clientType\":[\"PC\"],\"clientUrl\":[\"\"],\"sdkType\":[\"G\"],\"autoStart\":[\"Y\"],\"contactName\":[\"stet\"],\"contactMail\":[\"\"],\"authorizeId\":[\"xxxx\"],\"authorizeKey\":[\"xxxxx\"],\"status\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 01:59:17');
INSERT INTO `sys_oper_log` VALUES (61, '角色管理', 2, 'ins.webeye.project.system.role.controller.RoleController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网', '{\"roleId\":[\"100\"],\"roleName\":[\"对接测试人员\"],\"roleKey\":[\"eyetest\"],\"roleSort\":[\"3\"],\"status\":[\"0\"],\"remark\":[\"提供给回溯测试人员使用\"],\"menuIds\":[\"2000,2011,2018,2012,2020,2013,2030,2031,2014,2027,2025,2028,2029,2050,2048,2052,2017,2021,2041,2043,2007,2015,2045,2046,1,110,1050,1051,1052,1053,1054,1055,1056,105,1025,107,1035,2,109,1047,1048,1049,111,112,3,113,115\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 02:04:50');
INSERT INTO `sys_oper_log` VALUES (62, '代码生成', 2, 'ins.webeye.project.tool.gen.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网', '{\"tableId\":[\"15\"],\"tableName\":[\"eye_client_config\"],\"tableComment\":[\"对接的系统或渠道配置\"],\"className\":[\"EyeClientConfig\"],\"functionAuthor\":[\"webeye\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"287\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"主键\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"288\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"系统/渠道代码\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"clientCode\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"289\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"系统/渠道名称\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"clientName\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].isRequired\":[\"1\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"290\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"终端类型\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"clientType\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].isRequired\":[\"1\"],\"columns[3].htmlType\":[\"select\"],\"columns[3].dictType\":[\"client_type\"],\"columns[4].columnId\":[\"291\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"系统域名\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"clientUrl\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"292\"],\"columns[5].sort\":[\"6\"],\"columns[5]', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 10:09:37');
INSERT INTO `sys_oper_log` VALUES (63, '代码生成', 8, 'ins.webeye.project.tool.gen.controller.GenController.genCode()', 'GET', 1, 'admin', '研发部门', '/tool/gen/genCode/eye_client_config', '127.0.0.1', '内网', '{}', 'null', 0, NULL, '2020-08-30 10:09:48');
INSERT INTO `sys_oper_log` VALUES (64, '菜单管理', 2, 'ins.webeye.project.system.menu.controller.MenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网', '{\"menuId\":[\"2048\"],\"parentId\":[\"2050\"],\"menuType\":[\"C\"],\"menuName\":[\"对接系统/渠道配置\"],\"url\":[\"/eye/clientconfig\"],\"target\":[\"menuItem\"],\"perms\":[\"eye:clientconfig:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 10:35:06');
INSERT INTO `sys_oper_log` VALUES (65, '系统/渠道配置', 2, 'ins.webeye.project.eye.clientconfig.controller.EyeClientConfigController.editSave()', 'POST', 1, 'admin', '研发部门', '/eye/clientconfig/edit', '127.0.0.1', '内网', '{\"id\":[\"363\"],\"clientCode\":[\"test\"],\"clientName\":[\"测试渠道\"],\"clientType\":[\"PC\"],\"clientUrl\":[\"\"],\"sdkType\":[\"G\"],\"autoStart\":[\"Y\"],\"contactName\":[\"stet\"],\"contactMail\":[\"\"],\"authorizeId\":[\"xxxx\"],\"authorizeKey\":[\"xxxxx\"],\"status\":[\"0\"],\"httpsOnly\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 10:36:34');
INSERT INTO `sys_oper_log` VALUES (66, '系统/渠道配置', 2, 'ins.webeye.project.eye.clientconfig.controller.EyeClientConfigController.editSave()', 'POST', 1, 'admin', '研发部门', '/eye/clientconfig/edit', '127.0.0.1', '内网', '{\"id\":[\"363\"],\"clientCode\":[\"test\"],\"clientName\":[\"测试渠道\"],\"clientType\":[\"PC\"],\"clientUrl\":[\"*\"],\"sdkType\":[\"G\"],\"autoStart\":[\"Y\"],\"contactName\":[\"stet\"],\"contactMail\":[\"\"],\"authorizeId\":[\"xxxx\"],\"authorizeKey\":[\"xxxxx\"],\"status\":[\"0\"],\"httpsOnly\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 10:36:45');
INSERT INTO `sys_oper_log` VALUES (67, '菜单管理', 1, 'ins.webeye.project.system.menu.controller.MenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网', '{\"parentId\":[\"2048\"],\"menuType\":[\"F\"],\"menuName\":[\"查看对接密钥\"],\"url\":[\"\"],\"target\":[\"menuItem\"],\"perms\":[\"eye:clientconfig:viewKey\"],\"orderNum\":[\"7\"],\"icon\":[\"\"],\"visible\":[\"0\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 11:27:09');
INSERT INTO `sys_oper_log` VALUES (68, '字典类型', 1, 'ins.webeye.project.system.dict.controller.DictTypeController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/add', '127.0.0.1', '内网', '{\"dictName\":[\"对接方系统/渠道\"],\"dictType\":[\"client_code\"],\"status\":[\"0\"],\"remark\":[\"系统/渠道配置时自动写入，不允许修改\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 11:34:43');
INSERT INTO `sys_oper_log` VALUES (69, '系统/渠道配置', 2, 'ins.webeye.project.eye.clientconfig.controller.EyeClientConfigController.editSave()', 'POST', 1, 'admin', '研发部门', '/eye/clientconfig/edit', '127.0.0.1', '内网', '{\"id\":[\"372\"],\"clientCode\":[\"qschou\"],\"clientName\":[\"轻松筹测试\"],\"clientType\":[\"WX_H5\"],\"clientUrl\":[\"*\"],\"sdkType\":[\"S\"],\"autoStart\":[\"N\"],\"authorizeId\":[\"qschou\"],\"authorizeKey\":[\"1082b97f7dd85a8097d93aee05b63ae7\"],\"httpsOnly\":[\"N\"],\"status\":[\"0\"],\"contactName\":[\"轻松筹测试\"],\"contactMail\":[\"qschou@vip.com\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 11:41:32');
INSERT INTO `sys_oper_log` VALUES (70, '系统/渠道配置', 2, 'ins.webeye.project.eye.clientconfig.controller.EyeClientConfigController.editSave()', 'POST', 1, 'admin', '研发部门', '/eye/clientconfig/edit', '127.0.0.1', '内网', '{\"id\":[\"372\"],\"clientCode\":[\"QSCWX\"],\"clientName\":[\"轻松筹测试\"],\"clientType\":[\"WX_H5\"],\"clientUrl\":[\"*\"],\"sdkType\":[\"S\"],\"autoStart\":[\"N\"],\"authorizeId\":[\"qschou\"],\"authorizeKey\":[\"1082b97f7dd85a8097d93aee05b63ae7\"],\"httpsOnly\":[\"N\"],\"status\":[\"0\"],\"contactName\":[\"轻松筹测试\"],\"contactMail\":[\"qschou@vip.com\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 11:44:24');
INSERT INTO `sys_oper_log` VALUES (71, '系统/渠道配置', 2, 'ins.webeye.project.eye.clientconfig.controller.EyeClientConfigController.editSave()', 'POST', 1, 'admin', '研发部门', '/eye/clientconfig/edit', '127.0.0.1', '内网', '{\"id\":[\"372\"],\"clientCode\":[\"QSCWX\"],\"clientName\":[\"轻松筹微信\"],\"clientType\":[\"WX_H5\"],\"clientUrl\":[\"*\"],\"sdkType\":[\"S\"],\"autoStart\":[\"N\"],\"authorizeId\":[\"qschou\"],\"authorizeKey\":[\"1082b97f7dd85a8097d93aee05b63ae7\"],\"httpsOnly\":[\"N\"],\"status\":[\"0\"],\"contactName\":[\"轻松筹测试\"],\"contactMail\":[\"qschou@vip.com\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 11:45:03');
INSERT INTO `sys_oper_log` VALUES (72, '字典数据', 1, 'ins.webeye.project.system.dict.controller.DictDataController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/data/add', '127.0.0.1', '内网', '{\"dictLabel\":[\"测试系统\"],\"dictValue\":[\"test\"],\"dictType\":[\"client_code\"],\"cssClass\":[\"\"],\"dictSort\":[\"99\"],\"listClass\":[\"\"],\"isDefault\":[\"Y\"],\"status\":[\"0\"],\"remark\":[\"\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-30 11:45:52');
INSERT INTO `sys_oper_log` VALUES (73, '系统/渠道配置', 2, 'ins.webeye.project.eye.clientconfig.controller.EyeClientConfigController.editSave()', 'POST', 1, 'admin', '研发部门', '/eye/clientconfig/edit', '127.0.0.1', '内网', '{\"id\":[\"366\"],\"clientCode\":[\"TSWX\"],\"clientName\":[\"泰山微信\"],\"clientType\":[\"WX_H5\"],\"clientUrl\":[\"*\"],\"sdkType\":[\"S\"],\"autoStart\":[\"Y\"],\"authorizeId\":[\"taishan-test\"],\"authorizeKey\":[\"4083e100fada791f7332709937ac9d1d\"],\"httpsOnly\":[\"N\"],\"status\":[\"0\"],\"contactName\":[\"泰山保险测试\"],\"contactMail\":[\"taishang@126.com\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-31 00:21:22');
INSERT INTO `sys_oper_log` VALUES (74, '系统/渠道配置', 2, 'ins.webeye.project.eye.clientconfig.controller.EyeClientConfigController.editSave()', 'POST', 1, 'admin', '研发部门', '/eye/clientconfig/edit', '127.0.0.1', '内网', '{\"id\":[\"379\"],\"clientCode\":[\"TPWX\"],\"clientName\":[\"太平微信\"],\"clientType\":[\"WX_H5\"],\"clientUrl\":[\"*\"],\"sdkType\":[\"S\"],\"autoStart\":[\"Y\"],\"authorizeId\":[\"taiping\"],\"authorizeKey\":[\"4b60bd09e90595f5d7758e21cb10e285\"],\"httpsOnly\":[\"N\"],\"status\":[\"0\"],\"contactName\":[\"太平测试\"],\"contactMail\":[\"taiping@126.com\"]}', '{\"code\":0,\"msg\":\"操作成功\"}', 0, NULL, '2020-08-31 00:23:45');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(11) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(11) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-07-07 22:20:12', '普通角色');
INSERT INTO `sys_role` VALUES (100, '对接测试人员', 'eyetest', 3, '1', '0', '0', 'admin', '2020-07-18 17:06:26', 'admin', '2020-08-30 02:04:49', '提供给回溯测试人员使用');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);
INSERT INTO `sys_role_menu` VALUES (2, 1061);
INSERT INTO `sys_role_menu` VALUES (100, 1);
INSERT INTO `sys_role_menu` VALUES (100, 2);
INSERT INTO `sys_role_menu` VALUES (100, 3);
INSERT INTO `sys_role_menu` VALUES (100, 105);
INSERT INTO `sys_role_menu` VALUES (100, 107);
INSERT INTO `sys_role_menu` VALUES (100, 109);
INSERT INTO `sys_role_menu` VALUES (100, 110);
INSERT INTO `sys_role_menu` VALUES (100, 111);
INSERT INTO `sys_role_menu` VALUES (100, 112);
INSERT INTO `sys_role_menu` VALUES (100, 113);
INSERT INTO `sys_role_menu` VALUES (100, 115);
INSERT INTO `sys_role_menu` VALUES (100, 1025);
INSERT INTO `sys_role_menu` VALUES (100, 1035);
INSERT INTO `sys_role_menu` VALUES (100, 1047);
INSERT INTO `sys_role_menu` VALUES (100, 1048);
INSERT INTO `sys_role_menu` VALUES (100, 1049);
INSERT INTO `sys_role_menu` VALUES (100, 1050);
INSERT INTO `sys_role_menu` VALUES (100, 1051);
INSERT INTO `sys_role_menu` VALUES (100, 1052);
INSERT INTO `sys_role_menu` VALUES (100, 1053);
INSERT INTO `sys_role_menu` VALUES (100, 1054);
INSERT INTO `sys_role_menu` VALUES (100, 1055);
INSERT INTO `sys_role_menu` VALUES (100, 1056);
INSERT INTO `sys_role_menu` VALUES (100, 2000);
INSERT INTO `sys_role_menu` VALUES (100, 2007);
INSERT INTO `sys_role_menu` VALUES (100, 2011);
INSERT INTO `sys_role_menu` VALUES (100, 2012);
INSERT INTO `sys_role_menu` VALUES (100, 2013);
INSERT INTO `sys_role_menu` VALUES (100, 2014);
INSERT INTO `sys_role_menu` VALUES (100, 2015);
INSERT INTO `sys_role_menu` VALUES (100, 2017);
INSERT INTO `sys_role_menu` VALUES (100, 2018);
INSERT INTO `sys_role_menu` VALUES (100, 2020);
INSERT INTO `sys_role_menu` VALUES (100, 2021);
INSERT INTO `sys_role_menu` VALUES (100, 2025);
INSERT INTO `sys_role_menu` VALUES (100, 2027);
INSERT INTO `sys_role_menu` VALUES (100, 2028);
INSERT INTO `sys_role_menu` VALUES (100, 2029);
INSERT INTO `sys_role_menu` VALUES (100, 2030);
INSERT INTO `sys_role_menu` VALUES (100, 2031);
INSERT INTO `sys_role_menu` VALUES (100, 2041);
INSERT INTO `sys_role_menu` VALUES (100, 2043);
INSERT INTO `sys_role_menu` VALUES (100, 2045);
INSERT INTO `sys_role_menu` VALUES (100, 2046);
INSERT INTO `sys_role_menu` VALUES (100, 2048);
INSERT INTO `sys_role_menu` VALUES (100, 2050);
INSERT INTO `sys_role_menu` VALUES (100, 2052);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '管理员', '00', 'admin@163.com', '15888888888', '1', '', '4ab7aa0d71f160b1bdffdffe7e0082fc', '1eb045', '0', '0', '115.200.255.200', '2020-08-30 09:19:02', 'admin', '2018-03-16 11:33:00', 'ry', '2020-08-30 09:19:02', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '测试人员', '00', 'admin@qq.com', '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '2', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '测试员');
INSERT INTO `sys_user` VALUES (100, 103, 'eyetest', '测试用户1', '00', 'javalp@126.com', '13111111111', '0', '', '46f6b7c83ba164a5f7afcc886edf19e7', '9bd0dc', '0', '0', '', NULL, 'admin', '2020-07-19 10:25:37', '', '2020-07-20 15:38:44', NULL);
INSERT INTO `sys_user` VALUES (101, 201, 'dhic-test', '鼎和对接测试', '00', 'dhic-test@126.com', '13111111112', '0', '', '9a1a3f44c139267d9af04d8050f84f15', '2ad267', '0', '0', '101.71.245.110', '2020-07-20 12:56:50', 'admin', '2020-07-19 21:30:41', '', '2020-07-20 12:56:49', NULL);
INSERT INTO `sys_user` VALUES (102, 204, 'picc-test', 'PICC对接测试', '00', 'picc@126.com', '15888888889', '0', '', 'fd4bd49909969b178ca2b5828f9e1638', '2fe58a', '0', '0', '', NULL, 'admin', '2020-07-20 10:54:34', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (103, 203, 'taishan-test', '泰山保险测试', '00', 'taishang@126.com', '15888888800', '0', '', '4083e100fada791f7332709937ac9d1d', 'ba9b32', '0', '0', '36.113.129.169', '2020-08-20 17:59:33', 'admin', '2020-07-20 15:28:53', 'admin', '2020-08-20 17:59:32', '');
INSERT INTO `sys_user` VALUES (104, 206, 'zh-test', '众惠对接测试', '00', '123456@sinosoft.co', '13899990000', '0', '', '97c92cb17094dfded4b4e565ed1d3de9', '9bf2a3', '0', '0', '182.50.120.136', '2020-07-21 15:35:25', 'admin', '2020-07-20 15:30:36', '', '2020-07-21 15:35:25', NULL);
INSERT INTO `sys_user` VALUES (105, 207, 'huatai-test', '华泰测试', '00', 'huatai@126.com', '15888888885', '0', '', '94d320d0b4d53eda60305555e0e0450e', '46d2a3', '0', '0', '202.108.103.249', '2020-08-11 20:38:47', 'admin', '2020-07-22 10:56:31', '', '2020-08-11 20:38:46', NULL);
INSERT INTO `sys_user` VALUES (106, 208, 'xiandai-test', '现代保险', '00', '1234567@sinosoft.co', '13312346666', '0', '', '32ea8a19bc56abd927ee37dc19d4081b', '4503da', '0', '0', '122.200.121.157', '2020-08-29 15:46:50', 'admin', '2020-07-22 15:37:33', '', '2020-08-29 15:46:49', NULL);
INSERT INTO `sys_user` VALUES (107, 201, 'dinghe-test', '鼎和对接测试', '00', '12345666@sinosoft.co', '13344456666', '0', '', 'c93a006b046f34be9e3c86c4cd16c29e', 'd409c2', '0', '0', '10.156.45.71', '2020-08-05 11:11:04', 'admin', '2020-07-27 09:07:47', '', '2020-08-05 11:11:03', NULL);
INSERT INTO `sys_user` VALUES (108, 209, 'chengtai-test', '诚泰保险', '00', '12345678@sinosoft.co', '13344459999', '0', '', '5d97c48302df96d8eecde8ccb8840648', '6ed8cf', '0', '0', '222.221.193.194', '2020-08-28 22:37:35', 'admin', '2020-07-31 09:43:16', '', '2020-08-28 22:37:34', NULL);
INSERT INTO `sys_user` VALUES (109, 211, 'qschou', '轻松筹测试', '00', 'qschou@vip.com', '15888888881', '0', '', '1082b97f7dd85a8097d93aee05b63ae7', 'c92d8d', '0', '0', '182.50.125.2', '2020-08-14 17:43:48', 'admin', '2020-08-02 22:33:00', 'admin', '2020-08-14 17:43:48', '');
INSERT INTO `sys_user` VALUES (110, 212, 'ccictest@2020', 'ccictest', '00', 'ccic@vip.com', '13866668888', '0', '', '4b6e226f03e69ff2ad41b5088bb6c33c', '6c897b', '0', '2', '', NULL, 'admin', '2020-08-03 10:17:13', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (111, 212, 'ccictest', '大地测试', '00', 'ccictest@vip.com', '13866668889', '0', '', '567bac04c88ef7ee0b6499b4cc1c29ee', 'c4301a', '0', '0', '119.123.77.192', '2020-08-03 10:28:13', 'admin', '2020-08-03 10:27:16', 'admin', '2020-08-03 10:28:13', '');
INSERT INTO `sys_user` VALUES (112, 207, 'huatai', '华泰测试', '00', '9123@qq.com', '13344456621', '0', '', '2aef5f1a483d13f6592326d744eee5de', '59999e', '0', '0', '202.108.103.249', '2020-08-05 09:48:01', 'admin', '2020-08-05 09:40:39', '', '2020-08-05 09:48:01', NULL);
INSERT INTO `sys_user` VALUES (113, 213, 'taiping', '太平测试', '00', 'taiping@126.com', '13812345676', '0', '', '4b60bd09e90595f5d7758e21cb10e285', '8583b0', '0', '0', '183.192.237.18', '2020-08-24 14:15:54', 'admin', '2020-08-06 08:31:37', '', '2020-08-24 14:15:54', NULL);
INSERT INTO `sys_user` VALUES (114, 215, 'anda-test', '安达测试', '00', '19331234@qq.com', '13619331234', '0', '', '6d1e6da27ff84b75a875e6d5452a449a', 'fbba8f', '0', '0', '101.71.245.110', '2020-08-26 10:26:10', 'admin', '2020-08-17 16:19:54', 'admin', '2020-08-26 10:26:10', '');
INSERT INTO `sys_user` VALUES (115, 216, 'xinan-test', '鑫安保险测试', '00', 'xinan@vip.com', '15888888859', '0', '', '5194a9644b5550a2889edb5baf64ca8d', 'f94675', '0', '0', '101.71.245.110', '2020-08-26 10:27:21', 'admin', '2020-08-19 14:34:12', '', '2020-08-26 10:27:21', NULL);

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online`  (
  `sessionId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime(0) NULL DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime(0) NULL DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(11) NULL DEFAULT 0 COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '在线用户记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (100, 2);
INSERT INTO `sys_user_role` VALUES (101, 100);
INSERT INTO `sys_user_role` VALUES (102, 100);
INSERT INTO `sys_user_role` VALUES (103, 100);
INSERT INTO `sys_user_role` VALUES (104, 100);
INSERT INTO `sys_user_role` VALUES (105, 100);
INSERT INTO `sys_user_role` VALUES (106, 100);
INSERT INTO `sys_user_role` VALUES (107, 100);
INSERT INTO `sys_user_role` VALUES (108, 100);
INSERT INTO `sys_user_role` VALUES (109, 100);
INSERT INTO `sys_user_role` VALUES (110, 100);
INSERT INTO `sys_user_role` VALUES (111, 100);
INSERT INTO `sys_user_role` VALUES (112, 100);
INSERT INTO `sys_user_role` VALUES (113, 100);

SET FOREIGN_KEY_CHECKS = 1;
 


ALTER TABLE eye_record_page  MODIFY COLUMN node_url varchar(1600)  ;
ALTER TABLE eye_orderd_page  MODIFY COLUMN node_url varchar(1600)  ;


ALTER TABLE  `eye_order_page` 
ADD COLUMN `play_set_time` int(10) NULL COMMENT '播放偏移时间' AFTER `record_time`;

 