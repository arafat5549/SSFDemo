
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

-- 1基础查询
-- 1.1　查询所有列
-- 1.2　查询指定列

-- 2条件查询介绍
-- 2.2　查询性别为女，并且年龄50的记录
-- 2.3　查询学号为S_1001，或者姓名为liSi的记录
-- 2.4　查询学号为S_1001，S_1002，S_1003的记录
-- 2.5　查询学号不是S_1001，S_1002，S_1003的记录
-- 2.6　查询年龄为null的记录
-- 2.7　查询年龄在20到40之间的学生记录
-- 2.8　查询性别非男的学生记录
-- 2.9　查询姓名不为null的学生记录

-- 3模糊查询
-- 3.1　查询姓名由5个字母构成的学生记录
-- 3.2　查询姓名由5个字母构成，并且第5个字母为“i”的学生记录
-- 3.3　查询姓名以“z”开头的学生记录
-- 3.4　查询姓名中第2个字母为“i”的学生记录
-- 3.5　查询姓名中包含“a”字母的学生记录

-- 4字段控制
-- 4.1　去除重复记录
-- 4.2　查看雇员的月薪与佣金之和
-- 4.3　给列名添加别名

-- 5排序
-- 5.1　查询所有学生记录，按年龄升序排序
-- 5.2　查询所有学生记录，按年龄降序排序
-- 5.3　查询所有雇员，按月薪降序排序，如果月薪相同时，按编号升序排序

-- 6聚集函数
-- 6.1　COUNT
-- 	查询emp表中记录数：
-- 	查询emp表中有佣金的人数：
-- 	查询emp表中月薪大于2500的人数：
-- 	查询有佣金的人数，以及有领导的人数：
-- 6.2　SUM和AVG
-- 	查询所有雇员月薪和：
-- 	查询所有雇员月薪和，以及所有雇员佣金和：
-- 	查询所有雇员月薪+佣金和：
-- 6.3　MAX和MIN
-- 	查询最高工资和最低工资：

-- 7分组查询
-- 7.1　分组查询
-- 	查询每个部门的部门编号和每个部门的工资和：
-- 	查询每个部门的部门编号以及每个部门的人数：
-- 	查询每个部门的部门编号以及每个部门工资大于1500的人数：
-- 7.2　HAVING子句
-- 	查询工资总和大于9000的部门编号以及工资和：

-- 8Limit
-- 8.1　查询5行记录，起始行从0开始
-- 8.2　查询10行记录，起始行从3开始