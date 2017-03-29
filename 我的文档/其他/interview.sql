/*
Navicat MySQL Data Transfer

Source Server         : demo
Source Server Version : 50635
Source Host           : 127.0.0.1:3306
Source Database       : interview

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-03-17 10:23:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
  `name` varchar(50) NOT NULL,
  `parent_id` int(10) DEFAULT '0' COMMENT '父类',
  `parent_ids` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_employee
-- ----------------------------
DROP TABLE IF EXISTS `sys_employee`;
CREATE TABLE `sys_employee` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
  `name` varchar(50) NOT NULL,
  `dept_id` int(10) NOT NULL COMMENT '外键 部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
