package foo.lesson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public class Lesson20161107 {
 /**
  * 1.登录跟注册模块
  * 
  * #确认你说使用的技术框架(你要不要使用技术框架，你使用哪些技术框架)
  * - 不使用框架 逻辑比较简单，工程慢慢扩大以后，没有框架会特别不方便。
  *   框架有助于统一团队的编码格式
  * - SSH（）Struts2:1.控制页面跳转 
  * 				2.将Action封装成JAVABEAN(POJO)对象极大简化参数传递操作
  * 				3.拦截器实现I18N，统一编码格式
  *         Spring: 1.IOC:#1BeanFactory把所有的对象都配置为spring的Bean统一有Factory管理
  *                       #2不用创建依赖对象也可以使用它的方法 【 注入:setter注入  构造器注入】
  *                       #3（
  *                       	反射：
  *                          1.为什么要引入反射？获得一些动态语言的特性
  *                          2.在类还没被创建的时候就知道类的各个属性和方法
  *                          3.setter注入
  *                          
  *                          动态代理：
  *                             #1.静态代理
  *                                 //IHandler
  *                                 //Handler Handler2
  *                               //new Proxy(new Handler());
  *                               Proxy{
  *                                  Handler hanlder;
  *                                  //
  *                                  //Beforemethod;;
  *                                  hanlder.ddood
  *                                  //
  *                                  //Aftermethod;
  *                               }
  *                               没法动态生成代理类
  *                             #2.JDK动态代理
  *                                 IHandler强制面向接口编程
  *                                 //jdk动态代理的方式
									//	public Object bind(Object target){
									//		this.target = target;
									//		//jdk动态代理相关联的类 一定实现接口   cglib
									//		return Proxy.newProxyInstance(target.getClass().getClassLoader()
									//				, target.getClass().getInterfaces()
									//				, this);
									//	}
									//	@Override
									//	public Object invoke(Object proxy, Method method, Object[] args)
									//			throws Throwable {
									//		Object ret = null;
									//		System.out.println("Test1=====缓存功能");
									//		ret = method.invoke(target, args);
									//		System.out.println("TestEnd=====");
									//		return ret;
									//	}
  *                             #3.CGLIB代理
  *                              //CGLIB动态代理的方式
								@Override
								public Object intercept(Object obj, Method method, Object[] args,
										MethodProxy proxy) throws Throwable {
									Object ret = null;
									Annotation a = method.getAnnotation(AnnoNotCache.class);
									if(a==null)
									  System.out.println("开始执行缓存功能");
									
									ret = proxy.invokeSuper(obj, args);
									System.out.println("-------------调用方法结束-----------");
									return ret;
									//proxy.invoke(target, args);
								}
								
								//不用实现接口
								public Object bindCglib(Object target){
									this.target = target;
									//CglibProxy
									Enhancer enhancer = new Enhancer();
									enhancer.setSuperclass(target.getClass());
									enhancer.setCallback(this);
									//enhancer.setClassLoader(classLoader);
									return enhancer.create();
								}
  *                          ）
  *                       #4解耦合，依赖注入
  *                       
  *                 2.AOP:#1为什么要使用他？->优点和缺点->他的使用范围->他怎么实现这些东西?
  *                       #1解耦合跟第三方依赖库(比如事务和日记)
  *                       #2利于Spring整合各种第三方插件
  *                       #3JDK动态代理 cglib代理
  *                         动态代理效率偏低 (ASM汇编增强动态代理效率)
  *                         
  *                 3.事务: 全局事务和本地事务
  *                        编程式事务和声明式事务   -> AOP(声明式事务 )
  *                        1.事务分哪些?2.为什么要使用事务？3.怎么使用事务？4.事务的传播属性？
  *                        #1
  *                        #2保证数据的安全性和一致性，
  *                          怎么保证?事务的使用原理
  *                        #把几个SQL整合起来 ，中间有一个错误就会回滚回初始点。
  *                        
  *                        #3
  *                        
  *           Hibernate:1.什么是Hibernate?  ORM框架 -》什么是ORM框架？
  *                     2.为什么使用他？             屏蔽底层细节，不用直接写SQL语句
  *                     3.怎么使用他？ 
  *                        1.在Spring配置文件里面配置他   -》 dataSource 数据库连接池   
  *                        2.创建Mapper.hbm.xml -> 在Spring配置文件注册
  *                        3.HibernateSupportDAO来处理DAO操作
  *                     4.（屏蔽底层细节，不用直接写SQL语句）
  *                     
  *                     
  *                    Hibernate 一级缓存？二级缓存？查询缓存？
  *                    Hibernate 三种状态，怎么互相转化
  *                        
  *                        
  *     
  *         1.怎么使用Maven组织工程：
  *         1.src/main/java
  *         2.src/test/java
  *         3.src/main/resources
  *         4.src/main/webapp
  *         
  *         <packaging>war</packaging>
  *         war包是可以直接放在WEB服务器下面 可以被WEB服务器读取
  *         
  *         2.POM文件组织依赖Jar包：http://mvnrepository.com/  【Mavaen中央仓库】
  *         
  *         <dependency> <!-- 一个Dependency就是一个依赖子项目 -->
				<groupId>junit</groupId> <!-- .m2 找groupId文件夹 -->
				<artifactId>junit</artifactId> <!-- .m2 找groupId文件夹下面的artifactId子文件夹 -->
				<version>4.12</version> <!-- 版本号 -->
				<scope>test</scope> <!-- jar包的作用域不出测试src/test/java文件夹路径 -->
			</dependency>
			
			
			3.介绍大概需要哪些Jar包
			  #junit测试包和log4j slf4j日记包
			4.框架搭建完成后
			  #entity
			  #dao
			  #service
			   单元测试  --> 数据库
			     spring.xml：spring的配置管理ORM框架
			     mapper/*mapper.xml:在spring的配置注册
			  跟WEB对接
			   web.xml:控制框架的配置
			   struts.xml:struts2的配置文件
			   spring.xml：sping的配置
			   
			  属性properties:jdbc.properties / log4j.properties /
  */
}
