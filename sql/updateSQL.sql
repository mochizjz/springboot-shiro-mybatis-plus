
## 创建索引

ALTER TABLE  `eye_record_page` 
ADD INDEX `idx_record_token`(`token`),
ADD INDEX `idx_record_orderid`(`order_id`);

ALTER TABLE `eye_order` 
ADD INDEX `idx_order_token`(`token`),
ADD INDEX `idx_order_orderid`(`order_id`);

ALTER TABLE  `eye_page_version` 
ADD INDEX `idx_page_pageid`(`page_id`);


-- 字段调整得到150 再创建索引
ALTER TABLE eye_record_page  MODIFY COLUMN page_id varchar(150)  ;
ALTER TABLE eye_order_page  MODIFY COLUMN page_id varchar(150)  ;
ALTER TABLE eye_page_version  MODIFY COLUMN page_id varchar(150) ;

ALTER TABLE  `eye_page_version`  ADD INDEX `idx_page_pageid`(`page_id`);

--  文件路径字段长度增大
ALTER TABLE eye_record_page  MODIFY COLUMN file_path varchar(1000)  ;
ALTER TABLE eye_order_page  MODIFY COLUMN file_path varchar(1000)  ;
ALTER TABLE eye_page_version  MODIFY COLUMN file_path varchar(1000) ;
ALTER TABLE eye_record_page  MODIFY COLUMN client_ip varchar(100)  ;
ALTER TABLE eye_order_page  MODIFY COLUMN client_ip varchar(100)  ;

## 添加索引 2020年9月19日
ALTER TABLE `eye_order` ADD INDEX `idx_order_time`(`create_time`);
ALTER TABLE `eye_order_page` ADD INDEX `idx_orderpage_token`(`token`);


ALTER TABLE `eye_order_check` RENAME INDEX `redis_key` TO `idx_ordercheck_orderid`;
-- 如果上面的语句执行失败，使用创建语句 ALTER TABLE `eye_order_check` ADD UNIQUE  INDEX `idx_ordercheck_orderid`(`order_id`) USING BTREE;

ALTER TABLE `eye_order_check` ADD INDEX `idx_ordercheck_time`(`create_time`);
 
ALTER TABLE `eye_record_clear_report` ADD INDEX `idx_recordrep_orderid`(`order_id`);
ALTER TABLE `eye_record_clear_report` ADD INDEX `idx_recordrep_token`(`token`);
ALTER TABLE `eye_record_clear_report` ADD INDEX `idx_recordrep_time`(`create_time`);

ALTER TABLE `eye_check_log` ADD INDEX `idx_checklog_orderid`(`order_id`);
ALTER TABLE `eye_check_log` ADD INDEX `idx_checklog_time`(`check_time`);

ALTER TABLE `eye_page_version` ADD INDEX `idx_pageversion_prodcode`(`product_code`);
ALTER TABLE `eye_page_version` ADD INDEX `idx_pageversion_time`(`create_time`);
ALTER TABLE `eye_page_version` ADD INDEX `idx_pageversion_md5`(`file_md5`);

## 字段长度调整 2020年9月21日

ALTER TABLE qrtz_calendars  MODIFY COLUMN calendar_name varchar(150);
ALTER TABLE qrtz_paused_trigger_grps  MODIFY COLUMN trigger_group varchar(150);
ALTER TABLE qrtz_scheduler_state  MODIFY COLUMN instance_name varchar(150);


-- 20200921  解决回溯签名查询问题
update sys_menu set perms ='eye:recallmanage:list' where url='/eye/recallmanage/recallOfTruth';
INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2058, '签名查询', 2015, 1, '#', 'menuItem', 'F', '0', 'eye:recallmanage:list', '#', 'admin', '2020-09-21 21:01:49', '', NULL, '');