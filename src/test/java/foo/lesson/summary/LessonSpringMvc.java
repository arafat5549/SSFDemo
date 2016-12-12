package foo.lesson.summary;

/**
 * SpringMVC<p>
 * 0.什么是MVC？为什么要引入MVC？<br>
 * 1.什么是SpringMVC？为什么要引入SpringMVC?<br>
 * 2.SpringMVC和其他WEBMVC框架有啥区别(Struts1,Struts2)?<br>
 * 3.怎么使用SpringMVC？<br>
 * 4.SpringMVC的工作流程？<br>
 * 5.SpringMVC的常用注解？<br>
 * 6.SpringMVC怎么访问静态资源?<br>
 * 
 * 
 * 基础概念：
 * 1.web.xml配置的DispatcherServlet
 * 2.需要用到的Jar包
 * Spring基础包:
 * 	spring-core.jar
 * 	spring-context.jar
 *  [spring-expression]
 *  [spring-beans]
 *  [spring-aop]
 * SprinfMVC相关的包
 * 	spring-web.jar
 *  spring-webmvc.jar 
 *  spring-context-support.jar
 *  
 * Spring测试包与Junit整合:
 * 	spring-test.jar
 * 
 *  3.SpringMVC的基本流程
 *  //1.通过DispatchServlet转发
 *  //2.解析你的URL路径 /user/index  
 *  //3.根据返回 返回你的视图
 * @author wyy
 * 2016年11月25日
 *
 */
public class LessonSpringMvc {
  //无法解析EL表达式 确保你的JSP版本在2.0以上
	//1.web.xml web-app
	/*
	 *  <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
      version="3.1">
	 */
	
     /**
      * 5.SpringMVC常用注解
      */
//	　　@Controller
//	　　负责注册一个bean 到spring 上下文中
//
//	　　@RequestMapping
//	　　注解为控制器指定可以处理哪些 URL 请求
//
//	　　@RequestBody
//	　　该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上 ,再把HttpMessageConverter返回的对象数据绑定到 controller中方法的参数上
//
//	　　@ResponseBody
//	　　该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区
//
//	　　@ModelAttribute 
//	　　在方法定义上使用 @ModelAttribute 注解：Spring MVC 在调用目标处理方法前，会先逐个调用在方法级上标注了@ModelAttribute 的方法
//	　　在方法的入参前使用 @ModelAttribute 注解：可以从隐含对象中获取隐含的模型数据中获取对象，再将请求参数 –绑定到对象中，再传入入参将方法入参对象添加到模型中 
//
//	　　@RequestParam　
//	　　在处理方法入参处使用 @RequestParam 可以把请求参 数传递给请求方法
//
//	　　@PathVariable
//	　　绑定 URL 占位符到入参
//
//	　　@ExceptionHandler
//	　　注解到方法上，出现异常时会执行该方法
//
//	　　@ControllerAdvice
//	　　使一个Contoller成为全局的异常处理类，类中用@ExceptionHandler方法注解的方法可以处理所有Controller发生的异常
	
	   //不用框架写WEB前后台交互   你需要
	   //#1.request.getParameter("")
	   //#2.request.getDispacther("").forward()
	
	   //struts1.简化了哪一步
	   //简化了第二部（页面跳转-页面转发）
	
	   //struts2
	   //POJO(JavaBean)参数注入
	   //页面跳转-页面转发
	
	   //SpringmVC
	   //简化了POJO(JavaBean)参数注入
	   //简化了页面跳转-页面转发
	   //直接接收JSON-显示你的JavaBean对象为JSon数据
	   //RESTful web网站的架构

//json:
//	{
//		{"username":"1234","password":"2222"},
//		 "email":"WSSS",
//	}

//xml:
/**
 * <? xml =>
 *  <user> 
 *     <username>12345</username>
 *     <password>2222</password>
 *  </user>
 */
	
	  //JSON   优点：文件结构简单，数据量小（数据传输用JSON）
	  //xml文件  优点：数据结构严谨，可读性强（配置文件本地读取）
}
