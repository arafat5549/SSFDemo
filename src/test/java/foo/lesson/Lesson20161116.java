package foo.lesson;

public class Lesson20161116 {
   //1.后台怎么给前台穿对象
	
	//#1.String
	//#2.User
	//#3.List<User>
	
	//0.导入jstl包 用maven pom组织的方式
		//0-1.创建实体对象User      包名com.ssf.model
	//1.Servlet 继承 HttpServlet   包名com.ssf.action/controller
	//2.jsp页面 放在webapp底下    /文件夹名/xx.jsp
	//3.jsp-FOR each循环用的是什么标签
	//4.写完Servlet，web.xml注册它
	
	//知识点：
	//1.GET POST的区别
	//2.怎么编写和配置Servlet
		//2-1.请求转发和重定向的区别
		//2-2.作用域 request的作用域
	//3.怎么写JSP
		//3-1.c标签 <c:forEach
		//3-2.怎么写table标签
	
	
	
	//四大作用域
	//1.Page页面作用域：页面跳转 属性就会消失
	//2.request请求作用域：根据请求走，请求没有改变 属性就会一直存在
	//3.session会话作用域：浏览器没有关闭（一次会话浏览器开启到关闭的过程） # （存用户名）
	//4.application程序作用域：WEB应用都会起效   #（统计网站的访问量）
	//5.cookie存在的本地的一个缓存数据，跨越浏览器 # （购物车）
	//底层作用域会覆盖掉高层的作用域
	
	
	//数据库
	
	//mysql（默认用户名root 默认没有或者root  端口号默认是3306）
	//sql语句
	//JDBC(JavaDataBaseConnector):API 为了让java程序访问数据库
	
	//driverclass驱动类：不同数据和不同的编程语言交互要用到不同的驱动类
	    //
	//url：  jdbc:mysql://127.0.0.1:3306/bookstore
	//username用户名:root
	//password密码：
	
	
	//怎么使用mysql数据库：
	
	//1.把mysql的bin目标添加到Path路径
	//2.添加mysql的服务：   mysqld install [服务的名字默认mysql]
	    //#删除mysql服务：mysqld remove mysql
	//3.启动mysql服务：      net start mysql
	    //关闭mysql服务： net stop mysql
	
	//win8和win10的操作系统 要用管理员启动cmd
	
	//避免数据库中文乱码
	//my.ini-添加以下配置
	/*
		[client]
		default-character-set=utf8
		[mysql]  
		default-character-set=utf8 
		[mysqld]
		default-storage-engine=INNODB
		character-set-server=utf8
		collation-server=utf8_general_ci
	*/
	
}
