package foo.lesson;

/**
 * 数据库相关<p>
 * 
 * 1.什么是mysql?为什么要选择mysql?mysql的优点和缺点?<br>
 * 2.如何安装mysql?<br>
 * 3.mysql的基本配置?<br>
 * 4.
 * 
 * 1.根据需求创建model层的JavaBean
 * 2.创建数据库表
 * 3.service-业务层
 * 4.单元测试
 * 5.创建Servlet类，注册
 * 
 * 6.如果一些方法比如update两种，
 * #1.进入到update.jsp（一般是不是get方法）
 * #2.form表单修改，（POST）
 * 
 * 
 * 1.book/list.jsp
 * 2.BookServlet -》 跳转到  book/list.jsp
 * 3. 
 * 
 * 5.book/update.jsp里面有form表单 (post方法)
 * 
 * model-  实体层
 * service-业务层
 * action- web层
 * 
 * 
 * 作业:
 * 自己编写一个程序，从建库，建表到连接数据库，进行crud增删改查操作.
 * 
 * 
 * 实体类名:
 * Book 
 *  - int id；主键自增长方式
 *  - string bookname；书名 字符串长度50 不为空
 *  - string category； 类别  字符串长度20
 *  - double price；  价格 默认为0.0；
 * 
 * 页面  book/add.jsp    添加图书
 * 页面  book/update.jsp 修改图书
 * 页面  book/list.jsp 查询遍历数据库中的book集合 ， 删除/修改/添加图书功能
 * 
 * @author wyy
 * 2016年11月17日
 *
 */
public class Lesson20161117 
{
   /**
    * 回顾昨天的内容
    * 
    * 一.Maven来组织工程?
    * #1无法开启run on server选项？
    *  -1. maven-war-plugin 默认版本太低
    *      显示指定他的JDK编译版本 显式指定为1.7
    *  -2  eclipse默认java的太低，这样无法自动生成，web结构
    *       解决方法:右键 properties -> project facets
    * 
    * #2引入新的包，需要配置dependency标签，重新mvn install
    *  -去mvnreposity查找dependency标签定义，自己选择版本
    * 
    * 
    * 二.mysql数据库
    * 1.mysqld install [服务名称默认为mysql] 修改mysql.ini的basedir和datadir
    *  -如果mysql服务的地址不对，修改注册表 命令行启动regedit
    * 2.net start mysql
    * 
    * 3.配置中文乱码
    * 进入mysql命令行
    * show variables like '%char%'; //查看配置的对不对
    * 
    * 4.解压NaviCat
    * -1.创建数据库
    * -2.创建表：  string-varchar # boolean-tinyint # int-int # long-bigint
    * -3.生成表结构
    * 
    * 为什么要设置主键？
    * -主键查询更快，数据库默认对主键做索引
    * -主键唯一的
    * 
    *  mysql-connector-java:版本5.1.21
    * 
    * 
    * 
    */
	
	
	//一些常见的错误
	//数据库相关：
	//Access denied for user 'root'@'localhost' (using password: YES)
	//1.数据库用户名和密码错误
	//You have an error in your SQL syntax;
	//2.sql语句语法错误
	//com.mysql.jdbc.Driver 找不到
	//3.你没有导入mysql连接库
	//no database bookshop / no table t_user
	//4.没有建立数据库bookshop / 没有数据库t_user
	
	//JSP
	//1.定义WEB应用路径 <%=contextPath>
	//2.jstl C标签：<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	//如果你使用的是非Maven组织的方式 记得将引入的jar包复制一份到WEB-INF/lib文件夹下面
	
	//1.web开发的流程？
	//2.GET和POST的区别？
}
