package foo.lesson;

/**
 * SpringMVC的基本用法<p>
 * 
 * 1.怎么整合SpringMVC？
 * 2.SpringMVC常用的注解？
 * 
 * @author wyy
 * 2016年12月2日
 *
 */
public class Lesson20161202 
{
	/*
	 * 1.怎么整合SpringMVC
	 */
	//1.本质上是个DispatcherServlet，既然是Servlet在web.xml里面配置
	//2.springmvc本身的配置文件：springmvc-servlet.xml
	
	/*
	 * SpringMVC常用的注解？
	 */
	//扫描注解：@Controller
	
	//springmvc注解
	
	//@RequestMapping   
	//该方法或者类所代表的URL路径
	
	//@RequestBody  // 手机端传输json数据 -> User
	//该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上 ,再把HttpMessageConverter返回的对象数据绑定到 controller中方法的参数上

	//@ResponseBody
	//把我们的对象转成JSON（需要的数据类型）
	
    //@PathVariable  ：实现RESTFUL风格
	//绑定 URL占位符到入参
	
	//@ModelAttribute
	//在方法定义上使用 @ModelAttribute 注解：Spring MVC 在调用目标处理方法前，会先逐个调用在方法级上标注了@ModelAttribute 的方法
	//在方法的入参前使用 @ModelAttribute 注解：可以从隐含对象中获取隐含的模型数据中获取对象，再将请求参数 –绑定到对象中，再传入入参将方法入参对象添加到模型中

	//@RequestParam　
	//在处理方法入参处使用 @RequestParam 可以把请求参 数传递给请求方法
	
	//Spring默认是请求转发   重定向需要添加前缀："redirect:"

	//设置一个自定义拦截器
	//
}
