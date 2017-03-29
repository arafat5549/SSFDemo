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


-- 以下为测试数据
-- 测试学生表(Student)
DROP TABLE IF EXISTS `test_student`;
CREATE TABLE `test_student` (
	`id`	    INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
	`code`      CHAR(6) NOT NULL COMMENT '学生编号',
	`name`		VARCHAR(50) NOT NULL COMMENT '学生姓名',
	`age`		INT(2) DEFAULT NULL COMMENT '学生年龄',
	`gender`	VARCHAR(10) DEFAULT NULL COMMENT '学生性别',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `test_student` VALUES(1,'S_1001', 'liuYi', 35, 'male');
INSERT INTO `test_student` VALUES(2,'S_1002', 'chenEr', 15, 'female');
INSERT INTO `test_student` VALUES(3,'S_1003', 'zhangSan', 95, 'male');
INSERT INTO `test_student` VALUES(4,'S_1004', 'liSi', 65, 'female');
INSERT INTO `test_student` VALUES(5,'S_1005', 'wangWu', 55, 'male');
INSERT INTO `test_student` VALUES(6,'S_1006', 'zhaoLiu', 75, 'female');
INSERT INTO `test_student` VALUES(7,'S_1007', 'sunQi', 25, 'male');
INSERT INTO `test_student` VALUES(8,'S_1008', 'zhouBa', 45, 'female');
INSERT INTO `test_student` VALUES(9,'S_1009', 'wuJiu', 85, 'male');
INSERT INTO `test_student` VALUES(10,'S_1010', 'zhengShi', 5, 'female');
INSERT INTO `test_student` VALUES(11,'S_1011', 'xxx', NULL, NULL);

-- 测试员工表(Employeee)
DROP TABLE IF EXISTS `test_employee`;
CREATE TABLE `test_employee`(
	`id`		 INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
	`name`		 VARCHAR(50) NOT NULL COMMENT '员工姓名',
	`job`		 VARCHAR(50) NOT NULL COMMENT '职位',
	`manager_id` INT(11) DEFAULT NULL COMMENT '外键 关联自身 经理id',
	`hire_date`	 DATE NOT NULL COMMENT '雇佣日期',
	`salary`	 DECIMAL(7,2) NOT NULL COMMENT '薪水',
	`bonus`		 decimal(7,2) DEFAULT NULL COMMENT '奖金',
	`dept_id`	 INT(11) NOT NULL COMMENT '外键 部门id',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `test_employee` VALUES(7369,'SMITH','CLERK',7902,'1980-12-17',800,NULL,20);
INSERT INTO `test_employee` VALUES(7499,'ALLEN','SALESMAN',7698,'1981-02-20',1600,300,30);
INSERT INTO `test_employee` VALUES(7521,'WARD','SALESMAN',7698,'1981-02-22',1250,500,30);
INSERT INTO `test_employee` VALUES(7566,'JONES','MANAGER',7839,'1981-04-02',2975,NULL,20);
INSERT INTO `test_employee` VALUES(7654,'MARTIN','SALESMAN',7698,'1981-09-28',1250,1400,30);
INSERT INTO `test_employee` VALUES(7698,'BLAKE','MANAGER',7839,'1981-05-01',2850,NULL,30);
INSERT INTO `test_employee` VALUES(7782,'CLARK','MANAGER',7839,'1981-06-09',2450,NULL,10);
INSERT INTO `test_employee` VALUES(7788,'SCOTT','ANALYST',7566,'1987-04-19',3000,NULL,20);
INSERT INTO `test_employee` VALUES(7839,'KING','PRESIDENT',NULL,'1981-11-17',5000,NULL,10);
INSERT INTO `test_employee` VALUES(7844,'TURNER','SALESMAN',7698,'1981-09-08',1500,0,30);
INSERT INTO `test_employee` VALUES(7876,'ADAMS','CLERK',7788,'1987-05-23',1100,NULL,20);
INSERT INTO `test_employee` VALUES(7900,'JAMES','CLERK',7698,'1981-12-03',950,NULL,30);
INSERT INTO `test_employee` VALUES(7902,'FORD','ANALYST',7566,'1981-12-03',3000,NULL,20);
INSERT INTO `test_employee` VALUES(7934,'MILLER','CLERK',7782,'1982-01-23',1300,NULL,10);

-- 部门表
DROP TABLE IF EXISTS `test_department`;
CREATE TABLE `test_department`(
	`id`		INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
	`name`		varchar(14) COMMENT '部门名称',
	`location`	varchar(13) COMMENT '部门所在地',
	primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `test_department` VALUES(10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO `test_department` VALUES(20, 'RESEARCH', 'DALLAS');
INSERT INTO `test_department` VALUES(30, 'SALES', 'CHICAGO');
INSERT INTO `test_department` VALUES(40, 'OPERATIONS', 'BOSTON');

/*一.基础查询*/
-- 1.1查询所有列
-- 1.2查询指定列

/*二.条件查询*/
-- 2.2　查询性别为女，并且年龄50的记录
-- 2.3  查询学号为S_1001，或者姓名为liSi的记录
-- 2.4　查询学号为S_1001，S_1002，S_1003的记录
-- 2.5　查询学号不是S_1001，S_1002，S_1003的记录
-- 2.6  查询年龄为null的记录
-- 2.7　查询年龄在20到40之间的学生记录
-- 2.8　查询性别非男的学生记录
-- 2.9　查询姓名不为null的学生记录

/*三.模糊查询*/
-- 3.1　查询姓名由5个字母构成的学生记录
-- 3.2　查询姓名由5个字母构成，并且第5个字母为“i”的学生记录
-- 3.3　查询姓名以“z”开头的学生记录
-- 3.4　查询姓名中第2个字母为“i”的学生记录
-- 3.5　查询姓名中包含“a”字母的学生记录

/*四.字段控制查询*/
/*五.排序*/
/*六.聚集函数*/
/*七.分组*/
/*八.Limit
	LIMIT用来限定查询结果的起始行，以及总行数。
*/