package foo.lesson.summary;



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
 * 10.ApplicationContext与BeanFactory有什么区别？
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
	//1.实现程序之间的解耦,IOC就相当于类之间的解耦,AOP相当于与第三方服务之间的解耦
	//Spring提供了IOC容器，提供了AOP功能
	//为了解决EJB复杂应用而引入
	/**
	 * 2.什么是IOC/DI?Spring是怎么实现DI(依赖注入)的?<br>
	 */
	//#1.ioc容器：控制反转，
	//什么是IOC控制反转？
	//你的javabean的生命周期维护交给IOC容器来管理，你不再手动创建Javabean而是从IOC容器里面去获取
	//#2.什么是DI依赖注入？
	//我们的一个JavaBean的功能一般要依赖另外一个Javabean来实现，就需要将这个依赖的JavaBean给注入进来。
	//#3.Spring是怎么实现DI(依赖注入)的?
	//注入有两种注入方式 setter和构造器注入，默认是setter注入方式
	//XML配置的方式：
	//1-首先你的JavaBean交给IOC容器来管理 相当于你要两个Bean标签
	//2-在你要依赖的那个bean下面添加Property标签实现依赖注入                // <constructor-arg>
	//注解的方式：
	//1-扫描某个路径 <context:component-scan  base-package="*.*"/>
	//2-四个扫描注解@Componnent @Reposity @Service @Contoller(四个注解是等价的) 
	//相当于IOC容器注册的过程（在你的IOC容器里面创建了一个bean标签）
	//3-两个注入注解 
	//@Autowired： Spring注解 ， 按类别注入
	//@resource：   JDK注解              按名称注入
	
	//ioc容器/BeanFactory：
	//BeanFactory工厂类默认生成的Bean是单例模式，延迟加载/懒加载
	/**
	 * 4.什么是AOP(AspectOrientProgram)面向切面编程?<br>
	 */
	//弥补了计算机程序线性执行的弱点，利用AOP避免代码散落在程序的各个部分，
	//而来统一管理他们
	//AOP常用的地方（事务，缓存，日记等）
	//#tips：要详细的描述AOP可以引入Spring事务的部分和Spring是如何实现AOP的
	/**
	 * 6.Spring是怎么实现AOP的?
	 */
	//aop靠动态代理和反射来实现?  JDK动态代理（默认方式）-CGLIB动态代理
	//JDK动态代理-传入的是接口（所以我们WEB的分层一般要面向接口编程）
	//为什么动态代理可以实现AOP?
	//1.把你要代理的类（我们自己实现的类）传给动态代理对象（AOP框架）
	//2.把你要实现的功能写到你的动态代理类【提供我们需要功能的那个类如事务的提供类】里面去（DynamicProxyProvider）
	/*
	 //#1 JDK创建动态代理的过程
	Class<IFontProvider> targetClass = IFontProvider.class;
    return (IFontProvider) Proxy.newProxyInstance(
  		  targetClass.getClassLoader(),
        new Class[] { targetClass },
        new DynamicProxyProvider(new NetFontProvider()));
     //#2  
     IFontProvider provider =  new NetFontProvider();  
    */
	/**
	 * 7.什么是事务?为什么要使用事务?
	 */
	//#1.把多个sql操作封装成一个原子操作，来一起提交或者回退，从而保证数据的一致性和安全性。
	//#2.acid特性：
	//事务应该具有4个属性：原子性、一致性、隔离性、持续性。这四个属性通常称为ACID特性

	//#3.为什么要使用事务
	//保证数据的一致性和安全性。
	//讲那个转账的例子。
	//怎么保证数据的一致性和安全性?
	
	//#4.怎么样合理的使用事务?
	//每一条sql语句默认会开启事务 
	//当大量sql一起执行时，我们需要执行批处理Batch，我们可以手动关闭事务setAutocommit(false),再手动提交commit
	
    //事务的传播机制？为什么我们的传播机制默认是requried 默认开启且只开启一个事务
	/**
	 * 9.Spring怎么实现事务?
	 */
	//Spring利用AOP来实现事务。
}
