package foo.lesson;

/**
 * SpringMVC<p>
 * 
 * 0.什么是MVC？为什么要引入MVC？<br>
 * 1.什么是SpringMVC？为什么要引入SpringMVC?<br>
 * 2.SpringMVC和其他WEBMVC框架有啥区别(Struts1,Struts2)?<br>
 * 3.怎么使用SpringMVC？<br>
 * 4.SpringMVC的工作流程？<br>
 * 
 * @author wyy
 * 2016年12月1日
 *
 */
public class Lesson20161201 
{
	/*
	 * 0.什么是MVC？为什么要引入MVC？
	 */
	//WEB开发历史：静态网页->servlet->JSP->MVC(Model-View-Controller)
	//MVC是一种编程思想或者编程规范，遵循这个规范让逻辑与显示分离
	
	//大的MVC(一般非特指的MVC说的就是他)：Spring(C)SpringMVC(V)H(M)
	//小的MVC：SpringMVC等WEBMVC框架
	
	//JSP的缺点：JSP相当于HTML+JAVA代码，引入MVC让逻辑与显示分离
	
	/*
	 * 1.什么是SpringMVC？为什么要引入SpringMVC?
	 */
	//SpringMVC是一种WEBMVC框架，强制要求你们写的代码遵循MVC规范。
	
	/*
	 * 2.SpringMVC和其他WEBMVC框架有啥区别(Struts1,Struts2)?
	 */
	//#直接使用Servlet
	//1.request.getParameter
	//2.执行业务代码
	//3.request.setAttribute
	//4.页面跳转request.getRequestDispatcher("/index.jsp").forward()
	//web.xml->指定到类那一级别
	
	//#Struts2
	//1.第一部省略  直接将参数注入
	//2.执行业务代码
	//3.直接将参数返回
	//4.简化  返回SUCESS
	//struts.xml-> 指定到你的方法那一级别 <action name="login" class="xxx" method="xxx">
	
	//#SpringMVC
	//#1.第一部省略  直接将参数注入（直接将参数注入到JavaBean中[推荐方式]）
	//#2.执行业务代码
	//#3.直接将参数返回[只有封装到JAVABean才会直接返回]
	//#4.简化 页面跳转[不需要带后缀]
	
	/*
	 * 怎么使用SpringMVC？
	 */
	//#1.SpringMVC本质上是一个Servlet,在web.xml里面配置
	//DispatcherServlet默认跳转方式是页面转发
	
//    <!-- SpringMVC配置 -->
//    <servlet>
//        <servlet-name>Spring</servlet-name>
//        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
//        <init-param>
//            <param-name>contextConfigLocation</param-name>
//            <param-value>classpath:springmvc-servlet.xml</param-value>
//        </init-param>
//        <load-on-startup>1</load-on-startup>
//    </servlet>
//    <servlet-mapping>
//        <servlet-name>Spring</servlet-name>
//        <url-pattern>/</url-pattern>
//    </servlet-mapping>
	
	//#2.SpringMVC的配置文件:springmvc-servlet.xml
	
	//1.注解扫描 哪些类你需要注入进来()
	//2.请求和POJO注解打开
	//3.View解析器 - 视图
	
	
	
	/**
	 * 尝试自己搭建一个SpringSpringMVC框架
	 */
	//1.创建Maven工程
	//3.修改pom.xml (Maven Install运行这个工程)
	//4.单元测试(注意你的WEB架构分层，修改你的springcontext.xml配置文件)
	//4.添加ORM框架(单元测试)
	
	//5-1.修改web.xml (至少2.5以上的的版本)
	//5-2.添加SpringMVC框架
	
	//X.慢慢调试-注意自己调试
}
