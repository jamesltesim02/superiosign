ALTER TABLE `T_CUSTOMER` DROP FOREIGN KEY `fk_T_CUSTOMER`;
ALTER TABLE `T_ACCOUNT_CUSTOMER_REF` DROP FOREIGN KEY `fk_customer_account_ref`;
ALTER TABLE `T_ACCOUNT_CUSTOMER_REF` DROP FOREIGN KEY `fk_account_customer_ref`;
ALTER TABLE `T_APPLE_ACCOUNT` DROP FOREIGN KEY `fk_T_APPLE_ACCOUNT`;
ALTER TABLE `T_DOWNLOAD_LOG` DROP FOREIGN KEY `fk_T_DOWNLOAD_LOG`;
ALTER TABLE `T_LOGIN_LOG` DROP FOREIGN KEY `fk_T_LOGIN_LOG`;
ALTER TABLE `T_OPERSION_LOG` DROP FOREIGN KEY `fk_T_OPERSION_LOG`;

DROP TABLE `T_UERS`;
DROP TABLE `T_CUSTOMER`;
DROP TABLE `T_DOWNLOAD_LOG`;
DROP TABLE `T_APPLE_ACCOUNT`;
DROP TABLE `T_ACCOUNT_CUSTOMER_REF`;
DROP TABLE `T_LOGIN_LOG`;
DROP TABLE `T_OPERSION_LOG`;

CREATE TABLE `T_UERS` (
`id` varchar(32) NOT NULL COMMENT '主键 uuid',
`username` varchar(50) NOT NULL COMMENT '账号',
`password` varchar(100) NOT NULL COMMENT '密码',
`pass_sault` varchar(50) NOT NULL COMMENT '密码加密因子',
`create_time` datetime NOT NULL COMMENT '创建时间',
`create_user` varchar(20) NULL COMMENT '创建账号ip',
`role_type` int(8) NOT NULL COMMENT '用户类型 1: 超级管理员 2: 普通管理员 3: 普通用户
',
`last_login_ip` varchar(20) NOT NULL COMMENT '最后登录ip',
`last_login_time` datetime NOT NULL,
`login_fail_count` int(8) NULL DEFAULT 0 COMMENT '连续登录失败次数',
`login_fail_time` datetime NULL COMMENT '登录失败时间',
`login_fail_ip` varchar(20) NULL COMMENT '登录失败ip',
`locked` int(4) NULL DEFAULT 0 COMMENT '是否已锁定 0: 否, 1: 是',
`deleted` int(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0: 否, 1: 是',
PRIMARY KEY (`id`) 
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
COMMENT = '付费客户信息';
CREATE TABLE `T_DOWNLOAD_LOG` (
`id` varchar(32) NOT NULL COMMENT '主键',
`customer_account_id` varchar(32) NOT NULL COMMENT '客户及账号关联id',
`download_time` datetime NOT NULL COMMENT '下载时间',
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
);
CREATE TABLE `T_ACCOUNT_CUSTOMER_REF` (
`id` varchar(32) NOT NULL,
`account_id` varchar(32) NOT NULL COMMENT '苹果账号id',
`customer_id` varchar(32) NOT NULL COMMENT '客户id',
`udid` varchar(30) NOT NULL COMMENT '用户设备udid',
`create_time` datetime NOT NULL COMMENT '创建时间',
`deleted` int(2) NULL DEFAULT 0 COMMENT '是否已删除',
`remark` varchar(255) NULL COMMENT '描述',
PRIMARY KEY (`id`) 
);
CREATE TABLE `T_LOGIN_LOG` (
`id` varchar(32) NOT NULL COMMENT '主键',
`user_id` varchar(32) NOT NULL COMMENT '登录用户id',
`login_time` datetime NULL COMMENT '登录时间',
`login_ip` varchar(20) NULL COMMENT '登录ip',
PRIMARY KEY (`id`) 
);
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
);

ALTER TABLE `T_CUSTOMER` ADD CONSTRAINT `fk_T_CUSTOMER` FOREIGN KEY (`user_id`) REFERENCES `T_UERS` (`id`);
ALTER TABLE `T_ACCOUNT_CUSTOMER_REF` ADD CONSTRAINT `fk_customer_account_ref` FOREIGN KEY (`account_id`) REFERENCES `T_APPLE_ACCOUNT` (`id`);
ALTER TABLE `T_ACCOUNT_CUSTOMER_REF` ADD CONSTRAINT `fk_account_customer_ref` FOREIGN KEY (`customer_id`) REFERENCES `T_CUSTOMER` (`id`);
ALTER TABLE `T_APPLE_ACCOUNT` ADD CONSTRAINT `fk_T_APPLE_ACCOUNT` FOREIGN KEY (`create_user`) REFERENCES `T_UERS` (`id`);
ALTER TABLE `T_DOWNLOAD_LOG` ADD CONSTRAINT `fk_T_DOWNLOAD_LOG` FOREIGN KEY (`customer_account_id`) REFERENCES `T_ACCOUNT_CUSTOMER_REF` (`id`);
ALTER TABLE `T_LOGIN_LOG` ADD CONSTRAINT `fk_T_LOGIN_LOG` FOREIGN KEY (`user_id`) REFERENCES `T_UERS` (`id`);
ALTER TABLE `T_OPERSION_LOG` ADD CONSTRAINT `fk_T_OPERSION_LOG` FOREIGN KEY (`operate_user`) REFERENCES `T_UERS` (`id`);

