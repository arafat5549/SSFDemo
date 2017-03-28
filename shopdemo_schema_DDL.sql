DROP DATABASE IF EXISTS `shopdemo`;
CREATE DATABASE `shopdemo`;
USE `shopdemo`;

/**
-- 用户表
**/
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`(
  `id` int(10) NOT NULL AUTO_INCREMENT  COMMENT '主键自增长' ,
  -- `gender` tinyint(2) DEFAULT 0,
  `username` varchar(50)  NOT NULL  COMMENT '用户名' ,
  `password` varchar(50)  NOT NULL  COMMENT '密码' ,
  `avatar_url`   varchar(50)  DEFAULT NULL  COMMENT '头像图片索引时间片唯一值' ,
  `create_time` datetime   NOT NULL  COMMENT '创建时间' ,
  UNIQUE KEY `uq_username` (`username`) USING BTREE,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_user`(username,password,create_time) VALUES ('arafat','123456',now());
INSERT INTO `sys_user`(username,password,create_time) VALUES ('admin','123456',now());

/**
-- 商品表
**/
DROP TABLE IF EXISTS `sys_product`;
CREATE TABLE `sys_product`(
	`id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
    `name`  varchar(100) NOT NULL,
    `sub_title`  varchar(100) NOT NULL COMMENT '用于查询的Title',
    `price` decimal(10,2) DEFAULT 0.00,
    `promote_price` decimal(10,2) DEFAULT 0.00 COMMENT '促销价格' ,
    `stock` int(11) DEFAULT NULL COMMENT '库存',
    `category_id`   int(10) DEFAULT NULL  COMMENT '外键 分类ID' ,
    `create_time`   datetime NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
-- 分类表 （主键不自动增长，需要手动指定）
**/
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category`(
	`id` int(10) NOT NULL COMMENT '必须自己设定分类ID',
    `name` varchar(100) NOT NULL,
    `parent_id` int(10) NOT NULL  COMMENT '外键 关联自身 父类ID' ,
    `parent_ids` varchar(100) NOT NULL  COMMENT '所有的父类id' ,
    `create_time`   datetime NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
-- 购物车
**/
DROP TABLE IF EXISTS `sys_cart`;
CREATE TABLE `sys_cart`(
	`id` int(10) NOT NULL AUTO_INCREMENT,
    `user_id` int(10) NOT NULL      COMMENT '外键 关联用户ID',
    -- `cookie_id` int(10) DEFAULT 0   ,
    -- `ip_url`    varchar(100) DEFAULT NULL,
    `create_time`   datetime NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
/**
-- 购物车Item项
**/
DROP TABLE IF EXISTS `sys_cart_item`;
CREATE TABLE `sys_cart_item`(
	`id` int(10) NOT NULL AUTO_INCREMENT,
    `cart_id` int(10) NOT NULL      COMMENT '外键 关联购物车ID',
    `product_id` int(10) NOT NULL   COMMENT '外键 关联商品ID',
    `count` int(10)  NOT NULL COMMENT '购物车商品数量',
    -- `user_id` int(10) DEFAULT 0      COMMENT '外键 关联用户ID',
    -- `cookie_id` int(10) DEFAULT 0   ,
    `create_time`   datetime NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;  

/**
-- 订单
**/
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ordercode` varchar(255) NOT NULL COMMENT '唯一ID 订单号',
  `address` varchar(255) NOT NULL  COMMENT '收获地址',
  `post` varchar(50) DEFAULT NULL  COMMENT '邮编区号',
  `receiver` varchar(50) NOT NULL COMMENT '收件人',
  `mobile` varchar(50) NOT NULL COMMENT '手机',
  `message` varchar(255) DEFAULT NULL  COMMENT '用户信息',
  `user_id` int(11) NOT NULL COMMENT '外键 用户ID',
  `status` varchar(50) DEFAULT NULL COMMENT '订单状态',
  `pay_date` datetime DEFAULT NULL   COMMENT '订单付款时间',
  `delivery_date` datetime DEFAULT NULL COMMENT '订单发货时间',
  `confirm_date` datetime DEFAULT NULL  COMMENT '订单确认到货时间',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/**
-- 订单Item项
**/
DROP TABLE IF EXISTS `sys_order_item`;
CREATE TABLE `sys_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '外键 商品id',
  `order_id` int(11) NOT NULL COMMENT '外键 订单id',
  `user_id` int(11) NOT NULL COMMENT '外键 用户id',
  `count` int(11) NOT NULL  COMMENT '订单商品数量',
  `create_time`   datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/**
-- 购物车和用户关联表 sys_cart_user
   一对一 一个用户只能拥有一个购物车  [User表里面存cart_id , Cart表里面存user_id]
   一对多 一个用户拥有多个购物车
   多对多 拆成两个 一对多

   1 - cartid =1
   1 - cartid =2
   1 - cartid =3
**/
-- DROP TABLE IF EXISTS `sys_user_cart`;
-- CREATE TABLE `sys_user_cart`(
--     `cart_id` int(10) NOT NULL   ,  
--     `cart_item_id` int(10) NOT NULL   ,
--     `create_time`   datetime NOT NULL
-- )ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
-- 用户账户表
**/
-- DROP TABLE IF EXISTS `sys_account`; 
-- CREATE TABLE `sys_account`(
-- 	`id` int(10) NOT NULL AUTO_INCREMENT,
-- 	`aname` varchar(100) NOT NULL COMMENT '账户名称' ,
-- 	`balance` decimal DEFAULT 0           COMMENT '账户余额' ,
-- 	`createTime` datetime DEFAULT NULL  ,
-- 	PRIMARY KEY (`id`)
--  )ENGINE=InnoDB DEFAULT CHARSET=utf8;  -- MyISAM
/**
-- 用户 账户明细（交易） 表
**/
-- DROP TABLE IF EXISTS `sys_account_trade`;     -- 
-- CREATE TABLE `sys_account_trade`(
--  	`id` int(10) NOT NULL AUTO_INCREMENT,
--  	`from` int(10) NOT NULL COMMENT '转账信息 转账账户id' ,
--  	`to`   int(10) NOT NULL COMMENT '转账信息 收款账户id' ,     
--     `amount` decimal DEFAULT 0           COMMENT '转账信息金额' ,
--     `note` varchar(100) NOT NULL        COMMENT '转账信息备注' ,     
--     `createTime` datetime DEFAULT NULL  COMMENT '转账信息创建时间' ,
--  	PRIMARY KEY (`id`)
--  )ENGINE=InnoDB DEFAULT CHARSET=utf8;



/**
-- 测试日期数据表
**/
-- DROP TABLE IF EXISTS `test_date`;
-- CREATE TABLE `test_date`(
--  	`id` int(10) NOT NULL AUTO_INCREMENT,
--      `s_date` date DEFAULT NULL,
--      `s_time` time DEFAULT NULL,
--      `s_timestamp` timestamp DEFAULT 0,
--      `s_datetime`  datetime DEFAULT NULL,
--  	PRIMARY KEY (`id`)
--  )ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
-- 为数据表添加一列
**/
-- ALTER TABLE `sys_user` ADD (`createTime` datetime DEFAULT NULL);
-- ALTER TABLE `sys_product` ADD (`createTime` datetime DEFAULT NULL);

