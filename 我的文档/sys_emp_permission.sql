/*
Navicat MySQL Data Transfer

Source Server         : demo
Source Server Version : 50635
Source Host           : 127.0.0.1:3306
Source Database       : myshop

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-04-11 10:38:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_emp_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_emp_permission`;
CREATE TABLE `sys_emp_permission` (
  `emp_id` int(11) NOT NULL COMMENT '外键 员工ID',
  `permission_id` int(11) NOT NULL COMMENT '外键 权限ID',
  UNIQUE KEY `unq(emp,permission)` (`emp_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
