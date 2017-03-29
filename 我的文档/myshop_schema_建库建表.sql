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
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sys_department` VALUES(1,"公司",0,'0,1',now(),now());
INSERT INTO `sys_department` VALUES(11,"研发部 ",1,'0,1,11',now(),now());
INSERT INTO `sys_department` VALUES(21,"市场部 ",1,'0,1,21',now(),now());
INSERT INTO `sys_department` VALUES(201,"福建市场部 ",21,'0,1,21,201',now(),now());
INSERT INTO `sys_department` VALUES(111,"客户端研发部",11,'0,1,11,111',now(),now());
INSERT INTO `sys_department` VALUES(1111,"Android研发部",111,'0,1,11,111,1111',now(),now());

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
	`originPrice`  decimal(10,2) NOT NULL COMMENT '商品初始价格',
	`promotoPrice` decimal(10,2) NOT NULL COMMENT '商品初始价格',
	`stock`       INT(11) DEFAULT 0 COMMENT '库存', 
	`category_id` INT(11) NOT NULL COMMENT '外键 关联商品',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;