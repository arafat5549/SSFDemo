package foo.lesson.summary;

/**
 * JavaWEB总结?<p>
 * 
 * 0.java的优点？
 * 1.WEB的简单历程?<br>
 * 2.简单WEB开发流程？<br>
 * 3.常用的第三方工具类有哪些?<br>
 * 4.
 * 
 * 
 * 
 * 
 * @author wyy
 * 2016年12月1日
 *
 */
public class LessonJavaWeb {
	/**
	 * 0.java的优点？
	 */
	//1.跨平台 - JVM（代理-跟操作系统的代理）
	//2.容易多人协作 - 语言特性,固定的语法,（默认你不会），高手跟新手的代码比较容易统一
	//3.行业规范 - C#（开源框架多且大多数经过商业实战）
	//4.易于扩展
	/**
	 * WEB的简单历程
	 */
	//静态页面->Servlet->JSP->MVC->WEBMVC框架
	
	//静态页面HTML     优点：执行效率高     缺点：无法处理动态内容
	//Servlet 优点：处理动态内容   缺点：处理动态内容太过繁琐，得输出整个HTML页面
	//JSP     优点：HTML+Servlet  缺点：静态内容和动态内容耦合在一起(逻辑和显示耦合)
	  //Servlet做后台逻辑  ， JSP做显示
	//MVC： 编程思想或者编程规范(逻辑和显示分离)
	
	  //mvc框架： S(逻辑Contolller)S(显示层View)H(Model模型层)
	  //webmvc框架:SpringMVC/STRUTS2
	
	//webmvc框架：
	//#常用的WEBMVC框架： SpringMVC/STRUTS2 ，
	//#为什么要引入他：强制要求你遵守MVC规范()
	
	/**
	 * 简单WEB开发流程：
	 */
	
	//原始Servlet和JSP
	//1.request.getParameter()
	//2.业务处理
	//3.request.setAttibute
	//4.页面跳转  request.getDispacther("/index.jsp").forward();
	//页面跳转的两种方式： 请求转发 和 重定向
	
	//#Struts2
	//1.第一部省略  直接将参数注入
	//2.执行业务代码
	//3.直接将参数返回
	//4.简化  返回SUCESS
	//struts.xml-> 指定到你的方法那一级别 <action name="login" class="xxx" method="xxx">
	
	//SpringMVC
	//1.简化参数传递  参数注入,可以注入你的JavaBean（参数的名称得跟你的字段名称相同）
	//2.业务处理
	//3.往前台传参数    要用JAVABEAN传递的方式
	//4.页面跳转   
	
	//5.很容易实现RESTFUL风格(利用URL路径来传参数)
	//6.json解析：（对象和数据结构的映射 JSON）
	//7.url映射到方法那一级别（粒度更细）
	//8.注解的方式，简洁方便
	
	//导入jackson处理包  
//    <!-- springmvc注解 处理JSON数据的注解 -->
//    <mvc:annotation-driven>
//		<mvc:message-converters>
//			<bean class="org.springframework.http.converter.StringHttpMessageConverter">
//				<property name="supportedMediaTypes">
//					<list>
//						<value>text/plain;charset=UTF-8</value>
//						<value>text/html;charset=UTF-8</value>
//					</list>
//				</property>
//			</bean>
//			<bean
//				class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
//				<property name="supportedMediaTypes">
//					<list>
//						<value>application/json; charset=UTF-8</value>
//						<value>application/x-www-form-urlencoded; charset=UTF-8</value>
//					</list>
//				</property>
//			</bean>
//		</mvc:message-converters>
//	</mvc:annotation-driven>
	
	
	/**
	 * Spring框架
	 */
	//spring最大的优点：解耦合
	//1.ioc/DI：跟类的解耦合
	//ioc:把你所有的类都注册到BeanFactory里面
	      //#1.控制反转（你失去对象的控制权），对象的生命周期由IOC容器来控制
	      //#2.默认单例
	//di:你需要什么类 我才给你注入（对接口编程）
	     //我们不需要改变源代码，就可以替换你的实现类
	
	//2.aop（面向切面编程）:跟第三方服务的解耦合
	//aspect切面： 切点+增强方法
	//pointcut切点：  你要在哪些类哪些方法
	//advice增强方法：你要添加的服务或者功能
	//JoinPoint:切点和增强方法 交互的地方
	
	//3.事务模块
	//靠AOP实现事务 (编程式事务，声明式编程【推荐】)
	//事务是什么？为什么我们要使用事务？
	//事务的传播机制 ：propagation ： REQUIRED（默认）如果你没有事务 就帮你创建一个，如果你已经有事务，就是用你当前事务
	//事务的隔离级别
	
	//ioc/DI模块：
	//AOP模块：
	//事务模块：
	
	/**
	 * 数据库相关
	 */
	//1.主流的数据库有哪些？
	//#1.开源    Mysql       （1.免费 2.社区活跃，出现问题更好解决  3.深度定制）
	//#2.微软    SqlServer  （全家桶比较好用 你要使用尽量使用微软完整的解决方案）
	//#3.商业    Oracle       （1.笨重 2.功能性特别强大 3.贵）
	
	//￥1-2.关系型数据库   非关系型数据库Nosql？
	
	//2.主流的WEB服务器？
	//#1.开源  Tomcat
	//#2.微软  IIS
	//#3.商业  WebLogic
	
	
	//3.jdbc操作
	//4.SQL语句
	//5.orm框架：Hibernate和Mybatis
	//6.数据库连接池   druid / DBCP /C3PO
	//7.缓存                   EHCACACHE / REDIS
	
	//验证架构
	//后台验证Hibernate-Validation：保证你入库的数据的正确
	//前台框架Jquery-Validation：       防止用户重复提交，无效提交
	
	//前台架构
	//
}
