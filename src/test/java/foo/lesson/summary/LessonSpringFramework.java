package foo.lesson.summary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring框架<p>
 * 
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
 * @author wyy
 * 2016年11月24日
 *
 */
public class LessonSpringFramework 
{
   /**
    * 1.Spring框架包的简单说明
    */
   //#1.Spring核心包：springframework-core.jar
   //Beanfactory#Spring容器
	
   //#2.Spring上下文包 ： springframework-context.jar
	  //扩展了core包的内容
	//ApplicationContext
	//ClassPathXmlApplicationContext
	
}
