package foo.lesson;

/**
 * 20161129回顾<br>
 * 
 * 什么是Mybatis？我们为什么要使用它？
 * 参数验证框架：
 * 
 * 什么是参数验证框架？一般我们会怎么使用参数验证框架？
 * HibernateValidation验证框架：
 * 
 * 什么是事务？为什么我们要使用它？Spring是怎么配置声明式事务的?
 * 
 * SpringAOP模块<br>
 * 
 * 
 * 
 * @author wyy
 * 2016年11月30日
 *
 */
public class Lesson20161130 
{
	//1.什么是Mybatis？
	//#ORM框架或者持久层框架。
	//2.我们为什么要使用它？
	//#1简化我们的数据库操作。#2很容易跟其他的数据库第三方服务整合(数据库连接池和缓存)	
	//#3要写原生sql语句，更容易做数据库优化（也有利于sql语句的练习）
	
	//3.怎么简化我们的数据库操作？
	//如果你使用ORM框架，我们是不是要实现一个JDBCUtils的JDBC连接工具类来实现数据库crud操作
	//并且你要实现与每个数据库连接的细节
	
	
	//1.什么是参数验证框架？
	//简化我们的参数验证操作，
	//2.我们为什么要使用它？
	//参数验证在web开发中特别普遍，任意涉及到入库的操作基本都会碰到参数验证
	//通过参数验证来保证你数据的正确性(确保你入库的数据与你的要求相符合)
	//3.一般我们会怎么使用参数验证框架？
	//分前端验证[Jquery-validation](作用：防止恶意提交和意外提交 例子：比如你用户的浏览器卡住了，他可能不会不停的提交 导致服务器资源的极大浪费)
	  //用户的行为我们无法控制，我们就利用参数验证来组织用户的无效提交，就能提高服务器的性能
	//和服务端验证[Hibernate-validation](作用：保证你数据的正确性)，意涉及到入库的操作基本都会碰到参数验证
		
	
	//1.什么是事务？
	//多个sql操作的集合，以便于统一的回滚和提交。
	//2.我们为什么要使用它？
	//保证数据的安全性和一致性(账户转账的例子：)
	//3.怎么在Spring里面使用事务？
	//编程式事务和声明式事务(推荐)
	//编程式事务：硬编码在程序里面，在每一个需要事务的业务方法中，手动开启事务和手动提交事务
	//声明式事务：利用spring的配置来声明哪些类的哪些方法需要开启事务
	//#1.定义事务的管理类transactionManager，把数据源dataSource注入进入
//	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
//	<property name="dataSource" ref="dataSource" />
//	</bean>
	//#2.(pointcut切点：指定了哪些类和哪些方法)：在哪些类哪些方法你需要开启事务 
//	<aop:config>
//    <aop:pointcut
//            expression="execution(* com.ssf.bookshop.service.impl.*.*(..))"
//            id="txpointcut"/>
//    <aop:advisor advice-ref="txadvice" pointcut-ref="txpointcut"/>
//    </aop:config>
	//#3.Advice增强方法(事务，日记等你需要实现的额外功能)，把transactionManager注入
    //
//<tx:advice id="txadvice" transaction-manager="transactionManager">
//    <tx:attributes>
//        <tx:method name="save*"     propagation="REQUIRED"/>
//        <tx:method name="delete*"   propagation="REQUIRED"/>
//        <tx:method name="update*"   propagation="REQUIRED"/>
//        <tx:method name="transfer*" propagation="REQUIRED"/>
//        <tx:method name="insert*"   propagation="REQUIRED"/>
//        <tx:method name="change*"   propagation="REQUIRED"/>
//        <tx:method name="do*"       propagation="REQUIRED"/>
//        <!--  
//        <tx:method name="*"         propagation="REQUIRED" read-only="true"/>
//        -->
//    </tx:attributes>
//</tx:advice>
	//#4.aop是由Advice和Pointcut组合而成的
	//<aop:advisor advice-ref="txadvice" pointcut-ref="txpointcut"/>
	
	//propagation事务的传播类型和事务的隔离级别
	//propagation事务
	//默认的REQUIRED行为是如果没有事务就新建来执行如果已经有事务就是用当前事务。
	
	//4.Spring怎么实现声明式事务的？
	//AOP（面向切面编程）:弥补计算机程序线性执行的不足 （事务，日记，）
	
	/**
	 * Spring中aop的基础概念
	 */
	//JoinPoint连接点：切点和增强方法的连接处
	//Pointcut切点：指定了哪些类和哪些方法
	//Advice增强方法：你要执行哪些额外功能
	//Aspect切面：切点+增强方法
	
//	在AOP中有几个概念： 
//	— 方面（Aspect）：一个关注点的模块化，这个关注点实现可能另外横切多个对象。事务管理是J2EE应用中一个很好的横切关注点例子。方面用Spring的Advisor或拦截器实现。 
//	— 连接点（Joinpoint）：程序执行过程中明确的点，如方法的调用或特定的异常被抛出。 
//	— 通知（Advice）：在特定的连接点，AOP框架执行的动作。各种类型的通知包括“around”、“before”和“throws”通知。 
//	— 切入点（Pointcut）：指定一个通知将被引发的一系列连接点的集合。AOP框架必须允许开发者指定切入点，例如，使用正则表达式。 
//
//	所以“<aop:aspect>”实际上是定义横切逻辑，就是在连接点上做什么，
//	“<aop:advisor>”则定义了在哪些连接点应用什么<aop:aspect>。
//	Spring这样做的好处就是可以让多个横切逻辑（即<aop:aspect>定义的）多次使用，提供可重用性。 
//
//	你后面的两个类实际上就是实现横切逻辑的不同方式，一种需要实现特定接口，
//	一种以POJO + Annotation ， 在功能上没有太大差别，只是方式不同。
	
	/**
	 * Spring怎么实现AOP
	 */
	//1.Spring实现AOP功能需要哪些类
	//#要实现AOP、需不需要IOC模块-IOC模块需要的那些包都要有
	//#spring-aop.jar   aopalliance.jar（不同AOP模块的接口包）
	//#aspectj第三方AOP实现;
	//#cglib.jar
	//#asm.jar (优化你的字节码,提高你反射的性能)
	
	//动态代理  静态代理-JDK动态代理-CGLIB的动态代理
	//什么是代理模式？
	//
	
}
