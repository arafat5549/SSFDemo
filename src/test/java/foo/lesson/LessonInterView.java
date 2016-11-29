package foo.lesson;

/**
 * 面试过程就是展示你自己的过程(适度包装)<p>
 * 自我介绍<p>
 * 
 * 1.介绍你的基本情况(准备工作：衣着 发型 整体形象)【对hr来说是扫描你的简历和观察你的过程】
 * 2.了解他们公司的基本情况，应聘岗位的基本情况(准备工作：面试前上网搜寻)
 * 3.介绍你的经历和能力 (
 *  1.社团活动 代表你整体比较积极向上；自信
 * 		能够融入这个团队[1.不要自卑 2.不要太自大]，
 *  2.你自学或者做过的项目
 *  	你的技术实力复合（1.你想从事这个行业  2.你已经有一定的基础）
 *  3.一些荣誉(英语好,奖学金,) (扬长避短有的话就多讲)
 * )
 * 
 * 
 * 面试之前的准备:1.简历(
 *  #简历名词的格式为名字+应聘岗位
 *  #准备多分简历，你的简历的内容尽量包含应聘岗位的技术词汇(HR是用搜索来筛选)
 *  
 *  #突出你要准备讲的项目(在项目经验第一条，内容最多)
 *   -技术框架选择
 *   -为什么要选择这些
 *   -你遇到了哪些问题，如何解决的？
 * )
 * 
 * 
 * JAVAWEB 技术选型整理：
 * #Servlet：Servlet的工作原理？为什么我们要引入Servlet
 * #JSP: JSP的工作原理？为什么我们要引入JSP? JSP的页面域的对象
 * #JSTL(JavaStandardTabLib)Java标准标签库 / EL表达式: ${user}:他们的常用标签和用法
 *   JSTL常用标签: <c:foreach>  <c:if>  <c:set> <c:url>
 *   
 * #WEB服务器:Tomcat7,热更新配置，基本工作原理?为什么我们要使用Tomcat
 * 		 学会怎么使用:web.xml 和 server.xml 里面的基本配置
 * 
 * #后端技术框架：
 *   #SpringFrameWork框架：为什么我们要引入Spring? 
 *   IOC/DI是什么，Spring怎么实现它们的?
 *      学会怎么使用:1.怎么跟你的项目整合(引入你的jar包，编写配置文件)
 *      配置文件推荐名称：spring-context.xml
 *   #SpringMvc（WEB框架）：为什么我们要引入SpringMvc?SpringMvc的工作原理?
 *   跟其他WEB框架的比较(Struts1,Struts2)
 *      学会怎么使用:1.怎么跟你的项目整合 2.怎么跟Spring整合
 *      配置文件推荐名称：springmvc-servlet.xml
 *   #Mybatis(ORM持久层框架)：为什么我们要引入Mybatis?Mybatis的工作原理?
 *   跟其他ORM框架的比较(Hibernate)
 *   	学会怎么使用:1.怎么跟你的项目整合 2.怎么跟Spring整合
 *      配置文件推荐名称：默认可以不要(mybatis-config.xml)
 *      
 *   #数据库连接池Druid:为什么我们要引入Druid?Druid的工作原理?
 *   跟其他数据库连接池的比较(DBCP,C3P0)
 *   	学会怎么使用:1.怎么跟你的项目整合 2.怎么跟Spring整合
 *      配置：在spring配置文件里面配置
 *   #缓存Ehcache:为什么我们要引入Ehcache?Ehcache的工作原理?
 *   跟其他缓存的比较(OSCache,MemCache)
 *   	学会怎么使用:1.怎么跟你的项目整合 2.怎么跟Spring整合
 *   	配置文件推荐名称：ehcahce-local.xml
 */

import java.util.regex.Pattern;

import javassist.expr.NewArray;

import org.junit.Test;

import freemarker.core.Node;

/**
 * 经典面试题解析<p>
 * 1.String,StringBuffer,StringBuilder的区别<br>
 * 2.String的split操作的优化<br>
 * 
 * 
 * 3.ArrayList, LinkedList, Vector的区别是什么？<br>
 * 4.Map, Set, List, Queue、Stack的特点及用法。
 * 5.HashMap和HashTable的区别
 * 6.TreeMap, LinkedHashMap, HashMap的区别是什么？
 * 
 * 7.HashMap的实现原理
 * 
 * @author wyy
 * 2016年11月23日
 *
 */
public class LessonInterView 
{
	private static Pattern pattern = Pattern.compile("$\\#");
	//1.String相关
	@Test
	public void StringDemo(){
		String s1 = "hello";//常量池里
		String s2 = "world";
		String s3 = new String("hello");//堆里
		//#引用对象s3在stack
		//#"hello"常量池
		//new String()堆里
		
		System.out.println(s1 == s3); 
		System.out.println(s1.equals(s3)); 
		String s4 = s1 + s2;
		String s5 = s3 + s2;
		System.out.println(s4==s5); 
		System.out.println(s4.equals(s5)); 
		
		String s6 = "helloworld";
		System.out.println(s4 == s6);
		
		String demo = "1"+","+new String("2"); //1,2,4
		//字符串产生大量的临时string数据
		
		//大量字符串拼接
		StringBuffer sb = new StringBuffer();   //线程安全    -
		StringBuilder sbt = new StringBuilder();//线程不安全-
		sb.append("1");
		sb.append(",");
		sb.append("2");
		
//		//#2equals
//		//1.HASHCODE相同
//		//2.值相同
//		Object object = new Object();
//		object.hashCode();
//		s3.hashCode();
//		Apple apple   = new Apple("hello");
//		Orange orange = new Orange("hello"); 
//		System.out.println(apple.hashCode()+","+orange.hashCode());
//		System.out.println(apple.equals(orange));
		
		//split应用到正则表达式 大量调用效率很低
		String baesp = "araf#arrr#4444";
		String sp ="";
		long begin = System.currentTimeMillis();
		for(int i=0;i<1000000;i++){
			pattern.split(sp);//调用正则表达式 ， 需要compile
		}
		long end = System.currentTimeMillis();
		System.out.println((end-begin)+"ms"); 
		
		sp = "";
		begin = System.currentTimeMillis();
		for(int i=0;i<1000000;i++){
			sp.split("$\\#");
		}
		end = System.currentTimeMillis();
		System.out.println((end-begin)+"ms"); 
	}
	//2.数据结构相关
	//读(查询)和写(增删改)
	@Test
	public void StructDemo(){
		//3.ArrayList, LinkedList, Vector的区别是什么？
		//ArrayList扩容：一般扩容到当前容量的两倍
		
		//#ArrayList本质上是个可变数组，
			//支持随机访问(下标访问)，查询非常高
		    //数据量大的时候写的效率非常非常低
		//#LinkedList本质上是个链表（Node节点来连接起来的）
		    //查询效率低，遍历查询特别慢
		    //写操作高效(增删)
		//Vector基本跟ArrayList一样 他是线程安全 效率低一点。
		
		
		//4.Map, Set, List, Queue、Stack的特点及用法
		//Map本质上是数组的链表（结合了ArrayList和LinkedList的优点，比较适合大数据），键值对存储
	    //Set不重复的集合
		//List线性表
		//Queue先进先出，进程调度
		//Stack先进后出，引用指针就放在栈里面，最后一个引用是最新在用的对象
		
		//5.HashMap和HashTable的区别
		//#1.HashMap线程不安全 ， HashTable线程安全 效率低
		//#2.只有HashMap可以让你将空值作为一个表的条目的key或value
		//#3.Hashtable是基于陈旧的Dictionary类的，HashMap是Java 1.2引进的Map接口的一个实现。
		
		//6.HashMap,LinkedMap,TreeMap
		//HashMap是无序,LinkedMap是有序,TreeMap树结构 如果你的树结构是有序的他就是有序的
		
		//7.HashMap的实现原理
		//把key值做个hash算法，分布到16个bucket桶{数组},桶里面存的就是链表
		//KEY存在数组桶里面，链表存的是并不是Value值吗，Entry(key,value)
		//如果你的hash码相同的话，需要进入判断 需要根据Equals
		//相当于整合ArrayList和LinkedList的优点，比较适合大数据
	}
	
}

class Fruit{
	String string;
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof Fruit)
		{
			return string.equals(((Fruit)obj).string);
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		int hash = string.hashCode();
		return hash;
	}
}
class Apple extends Fruit{
	
	public Apple(String s){string =s;}
	
	@Override
	public int hashCode() {
		//System.out.println("---");
		return 1;
	}
	
}
class Orange extends Fruit{
	public Orange(String s){string =s;}
	
}
