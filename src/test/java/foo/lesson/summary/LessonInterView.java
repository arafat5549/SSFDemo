package foo.lesson.summary;

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
 *   AOP是什么，Spring是怎么实现他们的？
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
 *   
 *  
 *  其他个人素质：
 *  1.你项目中遇到哪些问题？你是如何解决的？
 *  2.你平时经常上哪些网站，社区？最近你看过什么书？
 *  
 *  其他技能-常用软件的使用：
 *  1.Eclipse（Eclipse for javaEE）
 *   #Eclispe插件的安装
 *  2.EditPlus/NotePad++/SublimeText
 *   #专业文本编辑器-为什么要使用专业文本编辑器？
 *  3.PPT/Excel
 *  4.技术相关的软件
 *   4-1.
 *  
 *  
 *  
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * <b>经典面试题解析</b><p>
 * <b>JAVA基础部分</b> <br>
 * 
 * 1.String,StringBuffer,StringBuilder的区别?<br>
 * 2.String的split操作的优化?<br>
 * 
 * 3.ArrayList, LinkedList, Vector的区别是什么？<br>
 * 3-1.ArrayList和LinkedList 怎么实现栈结构Stack和队列结构Queue<br>
 * -Stack:特点FILO先进后出  pop(移除最后一位),push()
 * -Queue:特点FIFO先进先出  pop(移除第一位),push()<br>
 * 4.Map, Set, List, Queue、Stack的特点及用法。<br>
 * 5.HashMap和HashTable的区别？<br>
 * 6.TreeMap, LinkedHashMap, HashMap的区别是什么？<br>
 * 7.HashMap的实现原理<br>
 * 8.什么是JAVA序列化?有几种比较常见的序列化方式?<br>
 * 9.JSON解析-XML解析？<br>
 * 10.什么是线程安全?<br>
 * 11.JAVA是如何实现线程安全的?<br>
 * 12.描述一下的JAVA的内存模型(JMM),
 * 
 * 20.描述下常用的设计模式
 * <br>
 * 
 * 
 * <b>WEB基础部分</b> <p>
 * 1.什么是Servlet?为什么我们要引入Servlet?<br>
 * 2.什么是JSP(JavaServletPage)?为什么我们要引入JSP?<br>
 * 3.什么是MVC?为什么要引入MVC？<br>
 * 4.什么是webMVC框架？为什么要引入webMVC框架?<br>
 * 5.SpringMVC的优点?<br>
 * 6.JSTL(JavaStandardTagLib)标签库,EL表达式(${})的简单用法?<br>
 * 7.主流的WEB服务器有哪些?<br>
 * 8.WEB服务器和HTTP服务器的区别?<br>
 * 9.什么是反向代理?什么是负载均衡?
 * 10.什么是AJAX?如何实现AJAX?
 * 11.什么是RESTFUL?如何实现RESTFUL?
 * 12.什么是缓存？什么是分布式缓存?EhCache和MemCache/Redis的区别?
 * 13.Session和Cookie是什么?他们的用法?
 * 
 * <br>
 * <b>Java并发与同步机制,多线程</b><p>
 * 1.Java有几种实现多线程的方式?<br>
 * 2.Java中的锁机制?<br>
 * 3.sleep和wait - wait和notify<br>
 * 4.死锁Deadlock<br>
 * 5.ConcurrentHashMap<br>
 * 6.JAVA并发包：BlockingQueue,CountDownLatch,Semaphore ,Executors
 * ConcurrentHashMap,CopyOnWriteArrayList<br>
 * 7.JAVA怎么实现线程安全<br>
 * 
 * <br>
 * <b>网络编程</b><p>
 * 1.什么是TCP/IP?<br>
 * 2.网络架构一共有几层?OSI模型?<br>
 * 3.什么是Socket?会简单的Socket编程?Socket编程的几种方式?<br>
 * 4.讲讲TCP协议？
 * 5.讲讲UDP协议？
 * 6.讲讲HTTP协议？
 * (请求头 1.请求类型(GET/POST) 2.请求地址(URL) 3.请求参数 4. 请求约束（哪个浏览器过来的，传输的数据结构）)
 * (响应头 1.状态码 2.Content-Type：内容的类别 3.内容编码gzip)
 * 7.JAVA通信框架Mina和Netty
 * 8.GET和POST的区别？
 * 
 * <br>
 * <b>操作系统</b><p>
 * //1.常用的命令
 * 1-1.ipconfig 
 * 1-2.netstat
 * 1-3.ping  
 * 1-4.
 * <br>
 * <b>Java虚拟机与GC</b><p>
 * 1.java内存模型(JMM)?它分为哪几个区域?
 * <br>
 * <b>设计模式</b><p>
 * 1.UML的类图和时序图?<br>
 * 2.常用的设计模式?在哪些地方见过?<br>
 * 3.什么是设计模式？为什么要有设计模式?<br>
 * 
 * <b>并发与多线程</b><p>
 * 1.Java有几种实现多线程的方式?<br>
 * 2.什么是线程安全问题?Java中怎么实现线程安全?<br>
 * 3.sleep和wait - wait和notify<br>
 * 4.死锁Deadlock<br>
 * 5.ConcurrentHashMap<br>
 * 6.线程池<br>
 * 7.CountDownLatch<br>
 * 
 * 看懂UML：
 * http://design-patterns.readthedocs.io/zh_CN/latest/read_uml.html
 * JAVA面试题: 内存相关
 * http://www.importnew.com/22083.html 
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
		/**
		 * 1.
		 */
		
		int i1 =1;
		Integer i2 = 1;
		Integer i3 = new Integer(1);
		
		System.out.println(i1 == i2); 
		System.out.println(i1 == i3); 
		System.out.println(i2 == i3); 
		
		String s1 = "hello";//常量池里
		String s2 = "world";
		String s3 = new String("hello");//堆里 Heap
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
	//3.WEB基础部分
	/**
	 * 1.什么是Servlet?为什么我们要引入Servlet?
	 */
	//#Servlet的用法
	//1.创建类 Servlet extends HttpServlet 继承HttpServlet（HttpServlet直接封装HTTP请求 ， HttpServletRequest）
	//2.在web.xml里面配置它 映射的URL
	//3.实现具体方法
	//#什么是Servlet?
	//web网站的发展过程：静态网页HTML->动态网页
	//要引入动态网页，就要引入能处理动态WEB资源的技术  Servlet
	//Servlet能够处理动态WEB资源->(引入为什么要引入JSP)
	//#3.Servlet怎么处理动态WEB资源
	//Servlet重新输出了HTML的结构 在其中插入可变的部分 达到动态网页的效果
	//优点：可以处理动态WEB资源   （因为优点我们为什么要引入它）
	//缺点：处理过于繁琐                   （因为缺点我们需要引入新的技术）
	/**
	 * 什么是JSP(JavaServletPage)?为什么我们要引入JSP?
	 */
	//#
	//JSP本质上是个Servlet，有jasper解析器解析
	//优点：JSP相当于HTML+Servlet，我们只需要编写可变的JAVA代码
	//缺点：逻辑（JAVA代码）和显示（HTML页面显示）耦合在一起，在页面比较复杂的时候，修改和维护会很困难
	//<%= %>  <% %>   <%@ page %>  
	/**
	 * 什么是MVC?为什么要引入MVC？
	 */
	//MVC并不是一种技术，一种编程规范和编程思想。(逻辑和显示分离)
	//最原始的MVC：逻辑由Servlet处理，显示由JSP处理 
	//缺点：要求程序员自觉遵守规范
	/**
	 * 什么是webMVC框架？为什么要引入webMVC框架?
	 */
	//优点：webMVC框架 强制要求你遵守MVC规范，按他的编码规范走一定是MVC的
	
	//#常见的webMVC框架有哪些？
	//SpringMVC的优点：
	//1.整合JSON解析模块， 接收和返回JSON数据
	//2./user/1 # /user?id=1  :  根据URL路径传参数的方式：很容易实现RESTFUL风格
	//3.天然跟Spring框架整合
	//4.注解方式，定义URL路径更加简洁
	
	//Struts2的优点：
	//1.简化了获取参数
	//2.
	//3.简化了返回参数
	//4.简化了页面跳转
	//5.指定到方法那一级别
	
	//不使用WEBMVC框架怎么处理请求?
	//1.获取参数  request.getParameter("")
	//2.业务方法
	//3.返回参数  request.setAttribute()
	//4.页面跳转  请求转发：request.getDispatcher("").forward() / 重定向：sendRedirect()
	//5.默认的Servlet配置的粒度到哪一个级别：类
	//如果指定到方法那一级别需要特殊处理：如带个参数来区分
	
	/**
	 * 什么是线程Thread？什么是进程？Process/Program程序
	 */
	//单任务系统:单核CPU ，电脑同一时间只能处理一个程序
	//多任务系统单核CPU ，进程调度，来处理多个程序
	//多线程：一个程序有多个线程，每个CPU负责一个线程。（一般你开的线程数量跟你CPU的核心数相同）
	
	/**
	 * 1.Java有几种实现多线程的方式?
	 */
	//1.继承Thread
	//2.实现Runnable接口
	//3.实现Callable接口 - 回调函数
	/**
	 * 3.sleep和wait - wait和notify
	 */
	//sleep 休眠不会不释放锁 ； wait 会释放锁。
	//wait 释放锁 和 notify 获得锁
	/**
	 * 2.Java中的锁机制?
	 */
	//#什么是线程安全？
	//锁机制：多个线程访问同一个资源(临界区) 就有可能产生数据不同步的问题。
	//怎么判断是不是线程安全
	//1.原子性
	//2.有序性
	//3.可见性
	
	//#JAVA里面怎么实现实现线程安全(锁机制)？
	//1.synchronized  有序性和可见性
	//2.Atmoic*       原子变量：保证我的变量是原子操作 / 原子性
	//3.Lock/ReentrantLock: 显式锁,基本跟synchronized一致（由你自己控制的锁的加锁和释放）
	
	//4.volatile:可见性,只保证了可见性 无法保证线程安全
	
	//显式锁,隐式锁并不是锁的类别（synchronized本质上是靠锁机制来实现）：
	//* 锁的种类？
	//* #互斥锁：同一时间只能有一个线程访问
	
	//* #乐观锁：线程可以被抢占有一套抢占规则
	//* #悲观锁：线程无法被抢占
	//* #公平锁：线程可以被抢占的时候才有公平锁,每个锁每一次请求,计数加1
	//*         轮询计数最高的锁来抢占线程
	//* #非公平锁：只要我这个锁请求了，线程就一定是我的。
	
	//synchronized锁的类别
	//不可重入的互斥锁 ， 非公平锁 ,悲观锁
	//优点：安全性更高，
	//缺点：更容易引起死锁，性能较低
	//ReentrantLock
	//可重入的互斥锁：（可自己配置是不是公平锁）乐观锁
	
	//指同一个线程，外层函数获得锁之后，内层递归函数仍有获得该锁的代码，但是不受影响。
	//优点：可重入锁的最大作用可以避免死锁
	//缺点：安全性要低一些，使用的粒度要更灵活。
	
	/**
	 * 6.JAVA并发包：BlockingQueue,CountDownLatch,ExecutorService
	 */
	
	/**
	 * CountDownLatch
	 * #awaits -> Thread.CurrentThread();调用了这个方法的线程都会被阻塞
	 * #CountDown的时候统一释放这些线程
	 * 闸门（所有线程会等待闸门开启的时候一起启动）
	 */
	
	
	/**
	 * <b>网络编程</b><p>
	 * 1.什么是TCP/IP?<br>
	 * //1.tcp/IP是一个协议簇.-(1.先讲一句话定义)
	 * //2.ip位于网络层，决定了你的计算机在网络传输中的唯一节点(怎么找到你这个计算机).-(2.具体含义)
	 * //3.tcp位于传输层，代表我能够进行网络通信，信息传输
	 * //4.TCP/iP布置包含TCP协议和IP协议，还包含UDP协议等.-(3.需要注意的部分)
	 * //5.什么是TCP协议?什么是UDP协议?
	 * 
	 * 2.网络架构一共有几层?<br>
	 * //1.物理层
	 * //2.数字链路层（把电流转化为逻辑电路）
	 * //#3.网络层(IP协议)
	 * //#4.传输层(TCP / UDP)
	 * //5.会话层
	 * //6.表示层
	 * //#7.应用层(HTTP协议)
	 * 
	 * 3.什么是Socket?会简单的Socket编程?Socket编程的几种方式?<br>
	 *	//1.Socket不是一个网络协议，是一套API.
	 *  //2.Socket是对网络各层的抽象，简化了网络传输的步骤。
	 *  //3.BIO（BlockingIO阻塞IO）/伪异步IO（线程池）/NIO（Non——BlockingIO非阻塞IO）
	 *  
	 * 4.讲讲TCP协议？
	 *  //tcp位于传输层，代表我能够进行网络通信，信息传输
	 *  //TCP协议是面向连接的协议，通过三次握手来保证连接的正确
	 *  //什么是三次握手
	 *  //1.客户端向服务端发送请求syn包
	 *  //2.服务端收到syn包，向客户端发送syn+ack包，服务端建立连接(可以接受数据了)
	 *  //3.客户端收到syn+ack包,向服务端发送ack包， 客户端建立连接(可以接受数据了)
	 * 5.讲讲UDP协议？
	 *  //udp协议是面向无连接的协议，类似于发电报，只负责发送，不管对方有没有接收到
	 *  
	 * 6.讲讲HTTP协议？
	 * //1.HTTP位于应用层，是一种超文本传输协议.(TCP决定了程序可以进行网络传输，HTTP决定了你传输的数据类型是什么)
	 * //2.http是对TCP协议的扩展(本质上也是个TCP协议)
	 * //3.为什么http协议很流行? 1.简单易用  2.很容易进行扩展
	 * 
	 * //hTTP协议的结构:
	 * (请求头 1.请求类型(GET/POST) 2.请求地址(URL) 3.请求参数 4. 请求约束（哪个浏览器过来的，传输的数据结构）)
	 * (响应头 1.状态码 2.Content-Type：内容的类别 3.内容编码gzip)
	 * 
	 * 7.JAVA通信框架Mina和Netty
	 *  //1.netty网络通信模块，大部分开源框架用它来实现高并发 高IO的网络通信服务器(JAVA本身的NIO模块过于复杂)
	 * 8.GET和POST的区别？
	 *   //1.GET明文传输 , 安全性较差 , 传输数据量小 , 数据可以被缓存
	 *   //2.POST表单传输,安全性较高,传输数据量大
	 */
	
	//Main方法
	public static void main(String[] args) {
		
//		for (int i = 0; i < 5; i++) {
//			MyThread thread = new MyThread();
//			thread.start();
//		}
//		//多次调用
//		MyThread2 m2 = new MyThread2();
//		for (int i = 0; i < 5; i++) {
//			m2.run();
//		}
//		//启动一个线程执行一些操作，并将结果返回。
//		MyCallable m3 = new MyCallable();
//		try {
//			int result = m3.call();
//			System.out.println("result="+result);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		ExecutorService es = Executors.newFixedThreadPool(1);
//		Counter counter = new Counter();
//		SynCounter syncounter = new SynCounter();
//		AtmoicCounter atmoicCounter = new AtmoicCounter();
//		LockCounter lockCounter = new LockCounter();
//		for (int i = 0; i < 10000; i++) 
//		{
//			es.execute(counter);
//			es.execute(syncounter);
//			//new Thread(counter).start();
//			//new Thread(syncounter).start();
//			//new Thread(atmoicCounter).start();
//			//new Thread(lockCounter).start();
//		}
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("counter="+counter.getCount());
//		System.out.println("syncounter="+syncounter.getCount());
		//System.out.println("atmoicCounter="+syncounter.getCount());
		//System.out.println("lockCounter="+syncounter.getCount());
		
//		Race0 race0 = new Race0(
//				 "[#1孙悟空]",
//		          "[#2马大哈]",
//		          "[#3美女]",
//		          "[#4闪电]",
//		          "[#5科比]",
//		          "[#6呆呆]",
//		          "[#7詹姆斯]"
//		);
//		
//		try {
//			race0.runRace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		SemaPhoreDemo demo = new SemaPhoreDemo();
		demo.runDemo();
	}
}
//.计数器-线程不安全
class Counter implements Runnable
{
	private int count;
	public int getCount(){return count;}
	@Override
	public void run() {
		inc();
	}
	private void inc(){
		count++;//count+1 count=count+1
	}
	
}

/**
 * 信号量Semaphore
 * 控制n个线程访问，N为固定数目
 *
 */
class SemaPhoreDemo {
    public void runDemo()
    {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟50个客户端访问
        for (int index = 0; index < 50; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 6000));
                        // 访问完后，释放
                        semp.release();
                        //availablePermits()指的是当前信号灯库中有多少个可以被使用
                        System.out.println("-----------------" + semp.availablePermits()); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }
}

//1.Race
class Race0{
	private Random rand = new Random();
	private int distance = rand.nextInt(100);
	private ArrayList<String> horses = new ArrayList<String>();
	
	public Race0(String ...names){
		horses.addAll(Arrays.asList(names));
		//System.out.println(horses.size());
	}
	//比赛
	public void runRace() throws InterruptedException
	{
		final CountDownLatch start  = new CountDownLatch(1);
		final CountDownLatch finish = new CountDownLatch(horses.size());	
		//ExecutorService es = Executors.newFixedThreadPool(10);		
		for(final String horse:horses){
			
			new Thread(new Runnable() {//匿名内部类
				int traveld = 0;
				@Override
				public void run() 
				{
					try {
						System.out.println(horse+"..horse awaits.");
						start.await();
						
						System.out.println(horse+"出发");
						while (traveld < distance) {
							 Thread.sleep(rand.nextInt(3) * 1000);
							 traveld += rand.nextInt(15);
							 System.out.println(horse+" traveld "+traveld);
						}
						finish.countDown();//一个马跑完 数值减1
						System.out.println(horse+"冲过了终点"); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		// 
		Thread.sleep(1000L);
		start.countDown(); 
		System.out.println("--------------------------");
		
		finish.await();
		
		System.out.println("全都跑完啦@"); 
	}
}


//1.synchronized (本质是锁,互斥锁，悲观锁，非公平锁)
class SynCounter implements Runnable
{
	private int count;
	public synchronized int getCount(){return count;}
	@Override
	public void run() {
		inc();
	}
	private synchronized void inc(){
//		synchronized(this)
//		{
//			count++;
//		}
		count++; //非原子操作- count+1 count=count+1
		
	}
}
//2.Atomic - CAS(Compare and set)先比对再赋值
class AtmoicCounter implements Runnable
{
	private AtomicInteger count = new AtomicInteger(0);
	public  int getCount(){return count.get();}
	@Override
	public void run() {
		inc();
	}
	private  void inc(){
		count.getAndIncrement();
	}
}
//3.Lock
class LockCounter implements Runnable{
	private int count;
	private Lock lock =new ReentrantLock();
	
	public int getCount(){return count;}
	@Override
	public void run() {
		inc();
	}
	private void inc(){
		lock.lock();
		try {
			count++;
		} finally{
			lock.unlock();//finally
		}
		
	}
}

//1.继承Thread类
class MyThread extends Thread{
	private  int  count = 0;//static
	@Override
	public void run() {
		//while(true)
		{
			System.out.println((count++)+",进程打印中---------");
		}
	}
}
//2.实现Runnable接口
class MyThread2 implements Runnable
{
	private  int  count = 0;//static
	@Override
	public void run() {
		System.out.println((count++)+",Runnable进程打印中---------");
	}
	
}
//3.回调
class MyCallable implements Callable<Integer>
{
	private int count = 0;
	@Override
	public Integer call() throws Exception {
		while(count<10)
		{
			count++;
		}
		return count;
	}
	
}

//
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
