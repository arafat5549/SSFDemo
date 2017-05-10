-- 单行注释
/* 多行注释 */

-- DDL语句(建库建表)

-- 创建数据库
DROP DATABASE IF EXISTS `myshop`;          -- 如果存在我就删除
CREATE DATABASE IF NOT EXISTS  `myshop`;   -- 加入双斜引号 避免你的数据库名和表名等跟数据库的关键字重名
USE `myshop`;   -- 选择数据库

-- 建表语句 
-- 主键 #(唯一而且不为空的)  (要有个唯一的标识) , 数据类型是什么？ int/bigint(long)型，自动增长

-- 用户表
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`(
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',    
	`username` varchar(100) NOT NULL COMMENT '用户登录名称',
	`password` varchar(100) NOT NULL COMMENT '用户登录密码',
	`describe` varchar(255) DEFAULT "" COMMENT '用户描述',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	primary key(`id`)  -- 实体表一定要设置主键  -- 最后一行没有逗号
)ENGINE=InnoDB DEFAULT CHARSET=utf8;   -- 两个数据库引擎 InnoDB Myisam


-- SQL结构化查询语句(重点)
--  插入测试数据
INSERT INTO `sys_user` VALUES(1,"wang","123456","",now(),now());
INSERT INTO `sys_user` VALUES(99,"lisi","45678","des",now(),now()); -- 如果不写指定列名 必须每一列都设置而且必须按照你生成的顺序
-- 查询
-- SELECT * FROM sys_user;

-- 更新
/*
    INSERT INTO sys_user(`username`,`password`) VALUES ("wamhwu","222222");
	UPDATE sys_user SET `password`="" WHERE id=1;
	DElETE FROM sys_user WHERE id =1;
	SELECT * FROM sys_user;
*/

/*
=== 数据库字段命名规范
. 后缀与类型：
.. 主键、外键等使用 id 后缀，为整型。
.. 以 status、type 为后缀的字段名明确为整型，如枚举，按位存储等。
.. 后缀 time 为时间型、后缀 date 为日期型，譬如不要使用 start_date 作为一个时间型字段。
.. 以 key、title、label、name、description、uri、url 等为后缀的字段名明确为字符型。
. 不要使用对象名作为**简单类型**的字段名，为其增加可识别其类型的后缀，如：uri、key 等。
.. 头像不要使用 `avatar` 作为字段名，使用 `avatar_key` 或者 `avatar_url`。
.. 字段名 `user` 不知道是 `user_id` 还是 `user_name`。 
*/

-- 员工表
DROP TABLE IF EXISTS `sys_employee`;
CREATE TABLE `sys_employee` (
	`id`	    INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
	`name`		VARCHAR(50) NOT NULL COMMENT '员工姓名',
	`dept_id`	INT(11) NOT NULL  COMMENT '外键 所在的部门ID',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_employee` VALUES(1,"公司总部文员",1,now(),now());
INSERT INTO `sys_employee` VALUES(2,"研发部文员",11,now(),now());
INSERT INTO `sys_employee` VALUES(4,"技术员",111,now(),now());
INSERT INTO `sys_employee` VALUES(5,"技术员",1111,now(),now());

-- 部门表
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
	`id`	    INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
	`name`		VARCHAR(50) NOT NULL COMMENT '部门名称',
	`parent_id`	INT(11) NOT NULL  COMMENT '外键 父部门ID',
	`parent_ids` VARCHAR(100) NOT NULL COMMENT '记录所有父部门的ID',
	`leader_id`  INT(11) DEFAULT 0  COMMENT '外键 领导ID(员工) 0代表暂时没有领导',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sys_department` VALUES(1,"公司",0,'0,1',1,now(),now()); 
INSERT INTO `sys_department` VALUES(11,"研发部 ",1,'0,1,11',2,now(),now());
INSERT INTO `sys_department` VALUES(21,"市场部 ",1,'0,1,21',0,now(),now());
INSERT INTO `sys_department` VALUES(201,"福建市场部 ",21,'0,1,21,201',0,now(),now());
INSERT INTO `sys_department` VALUES(111,"客户端研发部",11,'0,1,11,111',0,now(),now());
INSERT INTO `sys_department` VALUES(1111,"Android研发部",111,'0,1,11,111,1111',0,now(),now());

-- 资源表
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`(
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_resource VALUES(1,"图像资源");
INSERT INTO sys_resource VALUES(2,"人力资源");
INSERT INTO sys_resource VALUES(3,"市场资源");
INSERT INTO sys_resource VALUES(4,"影视资源");
INSERT INTO sys_resource VALUES(5,"后备资源");

-- 权限表
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
	`id`	    INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
	`resource_id`	INT(11) NOT NULL COMMENT '外键 资源ID',
	`action`        VARCHAR(50) NOT NULL COMMENT '权限操作或者说类型',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_permission VALUES(1,1,"add",now(),now());	
INSERT INTO sys_permission VALUES(2,1,"upd",now(),now());
INSERT INTO sys_permission VALUES(3,1,"del",now(),now());
INSERT INTO sys_permission VALUES(4,1,"query",now(),now());

INSERT INTO sys_permission VALUES(11,2,"add",now(),now());
INSERT INTO sys_permission VALUES(12,2,"upd",now(),now());
INSERT INTO sys_permission VALUES(13,2,"del",now(),now());
INSERT INTO sys_permission VALUES(14,2,"query",now(),now());

INSERT INTO sys_permission VALUES(111,4,"add",now(),now());
INSERT INTO sys_permission VALUES(112,4,"upd",now(),now());
INSERT INTO sys_permission VALUES(113,4,"del",now(),now());
INSERT INTO sys_permission VALUES(114,4,"query",now(),now());

INSERT INTO sys_permission VALUES(1111,5,"add",now(),now());
INSERT INTO sys_permission VALUES(1112,5,"upd",now(),now());
INSERT INTO sys_permission VALUES(1113,5,"del",now(),now());
INSERT INTO sys_permission VALUES(1114,5,"query",now(),now());

-- 部门权限 关联表（不是实体表 没有主键）
DROP TABLE IF EXISTS `sys_dept_permission`;
CREATE TABLE `sys_dept_permission` (
	`dept_id`	    INT(11) NOT NULL COMMENT '外键 部门ID',
	`permission_id`	INT(11) NOT NULL COMMENT '外键 权限ID'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_dept_permission(dept_id,permission_id) VALUES(11,1); 

INSERT INTO sys_dept_permission(dept_id,permission_id) VALUES(1,1); 
INSERT INTO sys_dept_permission(dept_id,permission_id) VALUES(1,2);
INSERT INTO sys_dept_permission(dept_id,permission_id) VALUES(1,3);
INSERT INTO sys_dept_permission(dept_id,permission_id) VALUES(1,4);

INSERT INTO sys_dept_permission(dept_id,permission_id) VALUES(1,11); 
INSERT INTO sys_dept_permission(dept_id,permission_id) VALUES(1,12);
INSERT INTO sys_dept_permission(dept_id,permission_id) VALUES(1,13);
INSERT INTO sys_dept_permission(dept_id,permission_id) VALUES(1,14);

INSERT INTO sys_dept_permission(dept_id,permission_id) VALUES(1,111);

-- 员工权限 关联表
DROP TABLE IF EXISTS `sys_emp_permission`;
CREATE TABLE `sys_emp_permission` (
	`emp_id`	    INT(11) NOT NULL COMMENT '外键 员工ID',
	`permission_id`	INT(11) NOT NULL COMMENT '外键 权限ID',
  	UNIQUE KEY `unq(emp,permission)` (`emp_id`,`permission_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_emp_permission VALUES(1,1);
INSERT INTO sys_emp_permission VALUES(1,2);
INSERT INTO sys_emp_permission VALUES(1,3);

-- 分类表
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category` (
	`id`	    INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
	`name`		VARCHAR(50) NOT NULL COMMENT '分类名称',
	`parent_id`	INT(11) NOT NULL  COMMENT '外键 父分类ID',
	`parent_ids` VARCHAR(100) NOT NULL COMMENT '记录所有父分类的ID',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 商品表
DROP TABLE IF EXISTS `sys_product`;
CREATE TABLE `sys_product` (
	`id`	    INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
	`name`		VARCHAR(50) NOT NULL COMMENT '商品名称',
	`subTitle`  VARCHAR(150) NOT NULL COMMENT '商品描述',
	`originPrice`  decimal(10,2) NOT NULL COMMENT '商品初始价格',
	`promotoPrice` decimal(10,2) NOT NULL COMMENT '商品初始价格',
	`stock`       INT(11) DEFAULT 0 COMMENT '库存', 
	`category_id` INT(11) NOT NULL COMMENT '外键 关联商品',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime DEFAULT NULL COMMENT '更新时间',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 购物车
DROP TABLE IF EXISTS `sys_cart`;
CREATE TABLE `sys_cart` (
	`id`	    INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
	`user_id`	INT(11) NOT NULL  COMMENT '外键 所关联的用户',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 购物车项
DROP TABLE IF EXISTS `sys_cart_item`;
CREATE TABLE `sys_cart_item` (
	`id`	    INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
	`cart_id`	INT(11) NOT NULL  COMMENT '外键 所关联的购物车',
	`product_id`	INT(11) NOT NULL  COMMENT '外键 所关联的商品',
	`count`	INT(11) NOT NULL  COMMENT '所关联的商品 购买数量',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 订单表
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order` (
	`id`	    INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',

   `ordercode` varchar(255) NOT NULL COMMENT '唯一ID 订单号',
   `address` varchar(255) NOT NULL  COMMENT '收获地址',
   `post` varchar(50) DEFAULT NULL  COMMENT '邮编区号',
   `receiver` varchar(50) NOT NULL COMMENT '收件人',
   `mobile` varchar(50) NOT NULL COMMENT '手机',
    `message` varchar(255) DEFAULT NULL  COMMENT '用户信息-备注信息',
   `user_id` int(11) NOT NULL COMMENT '外键 用户ID',
   `status` varchar(50) DEFAULT NULL COMMENT '订单状态',
   `pay_date` datetime DEFAULT NULL   COMMENT '订单付款时间',
   `delivery_date` datetime DEFAULT NULL COMMENT '订单发货时间',
   `confirm_date` datetime DEFAULT NULL  COMMENT '订单确认到货时间',

	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 订单项目表
DROP TABLE IF EXISTS `sys_order_item`;
CREATE TABLE `sys_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL COMMENT '外键 商品id',
  `order_id` int(11) NOT NULL COMMENT '外键 订单id',
  `user_id` int(11) NOT NULL COMMENT '外键 用户id',
  `count` int(11) NOT NULL  COMMENT '订单商品数量',
  `create_time`  datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

