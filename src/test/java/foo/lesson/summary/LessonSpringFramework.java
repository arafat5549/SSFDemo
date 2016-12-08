package foo.lesson.summary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Spring框架<p>
 * 
 * 1.什么是Spring框架？为什么要引入Spring框架?<br>
 * 2.什么是IOC/DI?Spring是怎么实现DI(依赖注入)的?<br>
 * 3.讲讲扫描注解？讲讲注入注解？<br>
 * 4.什么是AOP(AspectOrientProgram)面向切面编程?<br>
 * 5.pointcut切点,advice增强,Aspect切面,JoinPoint连接点是什么?
 * 6.Spring是怎么实现AOP的?
 * 7.什么是事务?为什么要使用事务?
 * 8.讲讲事务的传播机制？讲讲事务的数据库隔离级别？
 * 9.Spring怎么实现事务?
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 0.搭建Spring框架
 * 1.IOC(InterverseOfControll)控制反转
 *  #我们把对象创建和管理的控制权移交给Spring容器
 *  #Spring容器来统一管理和生成Bean对象
 * 2.DI(DependInject)依赖注入
 *  #我们由spring容器来控制你需要注入的对象
 *  如何实现依赖注入:
 *  1.XML配置方式：setter配置
 *     <bean name="x" class="x.x">
 *        <property name="你要注入的字段名称" ref="bean的ID或者Name"/>
 *     </bean>
 *  2.注解扫描的方式(Spring3.0以后):
 *     #注意注解扫描需要引入 <context:annotation-config/>的定义
 *     <context:component-scan  base-package="com.ssf.bookshop.*"/>
 *     在要注册的类上面添加四个标签@Componnet,@Service,@Controller,@Repository
 *     在要注入的字段上添加@AutoWired , @Resource
 *  
 *  
 * 优点：
 * #跟你依赖的对象解耦合
 * 
 * AOP(AspectOrientProgram)面向切面编程：
 * -
 * 
 * 1.怎么实现面向切面
 * 
 * 
 * pointcut切点:   你要切入的哪个类哪个方法
 * advice增强:     你要你所切面的方法增强 （事务） 
 * 
 * Aspect切面：-advice+pointcut
 * advisor：  advice+pointcut
 * 
 * 什么是事务?为什么我们要使用事务？
 * 事务的传播机制？事务的数据库隔离级别？
 * Spring怎么实现事务?
 * @author wyy
 * 2016年11月24日
 *
 */
public class LessonSpringFramework 
{
	/**
	 * 0.搭建Spring框架
	 */
	//1.导入包
	//2.log4j-slf4j这两个日记包，log4j.properties
	//3.spring的配置:springcontext.xml 放在classpath
	//4.springcontext.xml生成Spring容器BeanFactory
	//5.注册你的Bean对象：两种配置方式#1XML配置#2注解扫描的方式
	
   /**
    * 1.Spring框架包的简单说明
    */
   //#1.Spring核心包：springframework-core.jar
   //Beanfactory#Spring容器
	
   //#2.Spring上下文包 ： springframework-context.jar
	  //扩展了core包的内容
	//ApplicationContext
	//ClassPathXmlApplicationContext
	/**
	 * Spring常用的注解:
	 */
	//四个扫描标签：
	//@Component
	//@Repository
	//@Service
	//@Controller
	//两个注入标签：
	//@AutoWired  
	//@Resource
	
	/**
	 * 1.什么是Spring框架？为什么要引入Spring框架?<br>
	 */
	
	/**
	 * 6.Spring是怎么实现AOP的?
	 */
	//aop靠动态代理来实现?  JDK动态代理（默认方式）-CGLIB动态代理
	//JDK动态代理-传入的是接口（所以我们WEB的分层一般要面向接口编程）
	
	//为什么动态代理可以实现AOP?
	//
}
