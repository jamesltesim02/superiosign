ALTER TABLE `T_CUSTOMER` DROP FOREIGN KEY `fk_T_CUSTOMER`;
ALTER TABLE `T_ACCOUNT_CUSTOMER_REF` DROP FOREIGN KEY `fk_customer_account_ref`;
ALTER TABLE `T_ACCOUNT_CUSTOMER_REF` DROP FOREIGN KEY `fk_account_customer_ref`;
ALTER TABLE `T_APPLE_ACCOUNT` DROP FOREIGN KEY `fk_T_APPLE_ACCOUNT`;
ALTER TABLE `T_DOWNLOAD_LOG` DROP FOREIGN KEY `fk_T_DOWNLOAD_LOG`;
ALTER TABLE `T_LOGIN_LOG` DROP FOREIGN KEY `fk_T_LOGIN_LOG`;
ALTER TABLE `T_OPERSION_LOG` DROP FOREIGN KEY `fk_T_OPERSION_LOG`;
ALTER TABLE `T_IPA` DROP FOREIGN KEY `fk_T_IPA`;
ALTER TABLE `T_DOWNLOAD_LOG` DROP FOREIGN KEY `fk_T_DOWNLOAD_LOG_1`;

DROP INDEX `username_unique` ON `T_USERS`;

DROP TABLE `T_USERS`;
DROP TABLE `T_CUSTOMER`;
DROP TABLE `T_DOWNLOAD_LOG`;
DROP TABLE `T_APPLE_ACCOUNT`;
DROP TABLE `T_ACCOUNT_CUSTOMER_REF`;
DROP TABLE `T_LOGIN_LOG`;
DROP TABLE `T_OPERSION_LOG`;
DROP TABLE `T_IPA`;

CREATE TABLE `T_USERS` (
`id` varchar(32) NOT NULL COMMENT '主键 uuid',
`username` varchar(50) NOT NULL COMMENT '账号',
`password` varchar(100) NOT NULL COMMENT '密码',
`pass_sault` varchar(50) NOT NULL COMMENT '密码加密因子',
`create_time` datetime NOT NULL COMMENT '创建时间',
`create_user` varchar(20) NULL COMMENT '创建账号ip',
`role_type` int(8) NOT NULL COMMENT '用户类型 1: 超级管理员 2: 普通管理员 3: 普通用户',
`last_login_ip` varchar(20) NULL COMMENT '最后登录ip',
`last_login_time` datetime NULL,
`login_fail_count` int(8) NULL DEFAULT 0 COMMENT '连续登录失败次数',
`login_fail_time` datetime NULL COMMENT '登录失败时间',
`login_fail_ip` varchar(20) NULL COMMENT '登录失败ip',
`locked` int(4) NULL DEFAULT 0 COMMENT '是否已锁定 0: 否, 1: 是',
`deleted` int(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0: 否, 1: 是',
PRIMARY KEY (`id`) ,
UNIQUE INDEX `username_unique` (`username` ASC)
)
COMMENT = '用户表';
CREATE TABLE `T_CUSTOMER` (
`id` varchar(32) NOT NULL,
`user_id` varchar(32) NOT NULL COMMENT '用户id(外键)',
`total_count` int(10) NOT NULL DEFAULT 0 COMMENT '剩余总下载次数',
`customer_key` varchar(100) NULL COMMENT '客户标识key,当客户需要下载时传入,用以区分客户',
`name` varchar(255) NULL COMMENT '用户名称',
`check_api_url` varchar(500) NULL COMMENT '下载校验地址',
`need_check` int(4) NOT NULL DEFAULT 0 COMMENT '下载前是否需要校验 0 不需要, 1 需要, 默认为 0',
PRIMARY KEY (`id`) 
)
COMMENT = '付费客户信息表';
CREATE TABLE `T_DOWNLOAD_LOG` (
`id` varchar(32) NOT NULL COMMENT '主键',
`customer_account_id` varchar(32) NOT NULL COMMENT '客户及账号关联id',
`ipa_id` varchar(32) NOT NULL COMMENT '下载对应的ipa包',
`download_time` datetime NOT NULL COMMENT '下载时间',
`download_ip` varchar(20) NOT NULL COMMENT '下载ip',
`remark` varchar(255) NULL COMMENT '描述',
PRIMARY KEY (`id`) 
)
COMMENT = '下载日志';
CREATE TABLE `T_APPLE_ACCOUNT` (
`id` varchar(32) NOT NULL COMMENT '主键',
`account` varchar(255) NOT NULL COMMENT '账号',
`password` varchar(255) NOT NULL COMMENT '密码',
`email` varchar(255) NULL COMMENT '绑定的邮箱',
`email_password` varchar(255) NULL COMMENT '邮箱密码',
`phone` varchar(20) NULL COMMENT '绑定的手机号',
`device_count` int(4) NOT NULL DEFAULT 0 COMMENT '已绑定设备数量',
`cookie` varchar(1000) NULL COMMENT '最新登录cookie',
`state` int(2) NULL COMMENT '登录状态 0: 未登录, 1: 已登录, 2: 登录已失效, 4: 密码错误, 5: 登录未知错误',
`remark` varchar(255) NULL COMMENT '描述',
`create_user` varchar(32) NOT NULL COMMENT '创建者id',
`create_time` datetime NOT NULL COMMENT '创建时间',
PRIMARY KEY (`id`) 
)
COMMENT = '苹果开发账号表';
CREATE TABLE `T_ACCOUNT_CUSTOMER_REF` (
`id` varchar(32) NOT NULL,
`account_id` varchar(32) NOT NULL COMMENT '苹果账号id',
`customer_id` varchar(32) NOT NULL COMMENT '客户id',
`udid` varchar(30) NOT NULL COMMENT '用户设备udid',
`create_time` datetime NOT NULL COMMENT '创建时间',
`deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
`remark` varchar(255) NULL COMMENT '描述',
PRIMARY KEY (`id`) 
)
COMMENT = '客户udid绑定关系表';
CREATE TABLE `T_LOGIN_LOG` (
`id` varchar(32) NOT NULL COMMENT '主键',
`user_id` varchar(32) NOT NULL COMMENT '登录用户id',
`login_time` datetime NULL COMMENT '登录时间',
`login_ip` varchar(20) NULL COMMENT '登录ip',
PRIMARY KEY (`id`) 
)
COMMENT = '登录日志表';
CREATE TABLE `T_OPERSION_LOG` (
`id` varchar(32) NOT NULL,
`table_name` varchar(50) NOT NULL,
`operate_type` int(2) NOT NULL COMMENT '操作类型, 1: 新增, 2: 修改, 3: 删除',
`column_name` varchar(50) NOT NULL DEFAULT 'all' COMMENT '修改的列名修改单列时记录列名,其他为 all',
`value_before` varchar(4000) NULL,
`value_after` varchar(4000) NULL,
`operate_user` varchar(32) NOT NULL,
`operate_time` datetime NOT NULL,
PRIMARY KEY (`id`) 
)
COMMENT = '业务修改表';
CREATE TABLE `T_IPA` (
`id` varchar(32) NOT NULL COMMENT '主键',
`customer_id` varchar(32) NOT NULL COMMENT '对应客户',
`ipa_path` varchar(500) NOT NULL COMMENT 'ipa路径',
`ipa_token` varchar(100) NOT NULL COMMENT '用于给客户下载时传入的区分token',
`remark` varchar(1000) NULL COMMENT '描述',
`create_time` datetime NOT NULL COMMENT '创建时间',
`create_ip` varchar(20) NOT NULL COMMENT '创建ip',
`create_user` varchar(32) NOT NULL COMMENT '创建用户,当客户上传失败时,管理员可以帮助上传',
PRIMARY KEY (`id`) 
)
COMMENT = '客户ipa信息表';

ALTER TABLE `T_CUSTOMER` ADD CONSTRAINT `fk_T_CUSTOMER` FOREIGN KEY (`user_id`) REFERENCES `T_USERS` (`id`);
ALTER TABLE `T_ACCOUNT_CUSTOMER_REF` ADD CONSTRAINT `fk_customer_account_ref` FOREIGN KEY (`account_id`) REFERENCES `T_APPLE_ACCOUNT` (`id`);
ALTER TABLE `T_ACCOUNT_CUSTOMER_REF` ADD CONSTRAINT `fk_account_customer_ref` FOREIGN KEY (`customer_id`) REFERENCES `T_CUSTOMER` (`id`);
ALTER TABLE `T_APPLE_ACCOUNT` ADD CONSTRAINT `fk_T_APPLE_ACCOUNT` FOREIGN KEY (`create_user`) REFERENCES `T_USERS` (`id`);
ALTER TABLE `T_DOWNLOAD_LOG` ADD CONSTRAINT `fk_T_DOWNLOAD_LOG` FOREIGN KEY (`customer_account_id`) REFERENCES `T_ACCOUNT_CUSTOMER_REF` (`id`);
ALTER TABLE `T_LOGIN_LOG` ADD CONSTRAINT `fk_T_LOGIN_LOG` FOREIGN KEY (`user_id`) REFERENCES `T_USERS` (`id`);
ALTER TABLE `T_OPERSION_LOG` ADD CONSTRAINT `fk_T_OPERSION_LOG` FOREIGN KEY (`operate_user`) REFERENCES `T_USERS` (`id`);
ALTER TABLE `T_IPA` ADD CONSTRAINT `fk_T_IPA` FOREIGN KEY (`customer_id`) REFERENCES `T_CUSTOMER` (`id`);
ALTER TABLE `T_DOWNLOAD_LOG` ADD CONSTRAINT `fk_T_DOWNLOAD_LOG_1` FOREIGN KEY (`ipa_id`) REFERENCES `T_IPA` (`id`);

