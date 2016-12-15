package foo.lesson;


/**
 * 主流框架技术<p>
 * 
 * 三种MVC框架的对比<br>
 * 两种ORM框架的对比<br>
 * Spring框架回顾<br>
 * 
 * SSH框架搭建,(Spring-SpringMVC-Mybaits)框架搭建,(Spring-SpringMVC-Hibernate)框架搭建<br>
 * 推荐：Spring-SpringMVC-Hibernate or Mybatis<br>
 * 
 * @author wyy
 * 2016年11月3日
 *
 */
public class Lesson20161103 {
	
	//信息管理 ：pocket(read Later以后再看) 印象笔记(比较好的)
	
	//1.WEB
	//#1.web服务器(动态资源服务器)：
	  //Tomcat - 
	  	//server.xml  reload="true"
	  //JSP清除页面缓存 
		//<meta http-equiv="pragma" content="no-cache">
		//<meta http-equiv="cache-control" content="no-cache">
		//<meta http-equiv="expires" content="0">
	//#2.hTTP服务器(静态资源)：ngnix(可选)
	//#3.数据库：
		//Navicat(可视化操作)
	    //Mysql:
		//1.修改url，用户名和密码
	    //2.配置 数据库乱码utf-8  my.ini
	    //3.设置数据库引擎为InnoDB（MYIASM）
	    //4.数据库优化：
	      //#1.设置fetch_size一次获取数据的大小(50-100),减少数据库操作
	   	  //#2.数据库缓存，减少与数据库交互
	      //#3.数据库连接池(dbCP,C3PO,DRUID):利用PS(preparedstatement)缓存
	      
	//#4.3大框架
	    //ORM框架
	    //WEB框架
	    //Spring框架
	//#5.前台相关- css(Bootstrap) html js(jQuery)
	//#6.IDE
	     //Eclipse / MyEclipse
	     //常用的快捷键: ctl+sft+r : ctl+o  : ctl+左键 : CTL+f :alt+/
	     //设置:自动补全
	//#7.专业文本编辑器 EditPlus / NotePad++ /
	     //文件管理
	//#8.前台IDE(可选)
	//#9.打包工具Maven
		//编译JDK版本(默认1.6) / test方法报错
	    //mvn install：1.mvn install 2.mvn build 3.mvn test
	    //mvn eclipse:eclipse
	    //mvn tomcat7:run  #  插件<plugin>
	//#10.项目管理工具SVN/GIT
	    //多人协作
	    //diff - 直接在svn上面修改
	//#11.网站/blog/开发者社区
	    //importnew.com
	    //APP:开发者头条
	    //出问题：百度-google-知乎-Quora-StackOverFlow-CSDN-GitHub
		
	
	//#11.第三方插件
	  //JSON工具包：-Gson
	  //XML工具包：  -dom4j - xcercesJ
	  //ApacheCommons:
	  //commons-io / commons-codec / commons-util / commons-fileupload
	  //Guava工具包
	  //日期JodaTime
	//#12.中间件(可选)
	
	
	
	
	
	//Davlik # JVM HotSpot # IBM
	
	//gui机制 - 并发机制
	//1:MFC : while(遍历消息) 阻塞型IO，大数据量会影响你的主线程
	//2:现代GUI:事件分发机制() onClick / html()   NIO（阻塞忙轮询）
	//3:qt/COCOA:  singal/slot  --> 异步IO(注册，轮询,
	//##1.主管  2.worker == cpu数量
	//#1.channel连接建立 2.channel读取IO流 3.channel空闲 4.channel读取结束)        
	
	
	//1.分页算法
	//#1.offset 起始索引
	//#2.limit  一个页面要显示几条数据
	//#3.total  总数据 select * from table
	//#4.List<Obj> 根据offset和limit来获取当前页面要显示对象的List集合
	     //.setMaxResults(limit) .setSatrtIndex(offset)
	//#5.totalPage      total%limit==0?total/limit:total/limit+1
	//#6.pageNum        1<= (total/limit)+1 <= totalPage
	
	
	//2.WEB开发简易流程:
	//#1.创建JavaBean(POJO) 定义字段和属性
	//#2.根据JavaBean来建表  (模型层 entity/bean/model)
	//#3.DAO(DataAcccessObject):屏蔽上层业务逻辑,只负责跟数据库操作(ORM框架)
	     //Hierbate XML配置:1.映射文件-类名.hbm.xml，跟类放在同一个文件夹下面
	     //                2.注册映射文件(app-base.xml，你的Hibernate由谁来创建就由谁负责注册)
	//#4.Service(业务层)：业务逻辑 - (开启事务,业务有可能涉及多个数据库操作)
	//#5.(web/servlet/action/controller)web层,可以跟前台页面交互(JSP) (WEB框架)
		//strus.xml 配置页面对转和对应的Action : struts负责参数的注入IOC/AOP
	//#6.Spring框架整合以上
	    //把DAO/Service/Action都定义为一个Bean来共同管理他们,IOC AOP
	    //在Application-bean.xml里面配置：
	
	
	
	//ORM框架是什么 为什么要有ORM?
	//ORM(ObjectRelativeMapping：对象关系映射) ->因为程序语言的数据类型和数据库
	//的数据类不匹配，ORM就他们之间的桥梁，将数据库类型映射为程序语言能使用的对象。
	//Entity实体对象在流动
	//ORM框架:Mybaits,Hibernate
	
	//WEB框架是什么 为什么要有WEB框架?说出3个WEB框架的特点
	//他帮我们实现了MVC，统一代码，简化操作
	//WEB框架:struts1.x struts2.x springmvc
	//#1.Servlet(request,response)  
	//1.request.getParameter("") 
	//2.request.setAttribute("")
	//3.request.getDispatcher().forward(request,response)
	//#2.struts1.x
	//Action(request,response)
	//1.request.getParameter("") 
	//2.request.setAttribute("")
	//统一来管理这些页面跳转
	//(一个Servlet有多个Action,利用隐藏input框实现 <INPUT HIDDEN="" NAME="方法名")
	//#3.struts2.x  == xwork2
	//String action()
	//1.管理参数和返回值
	//2.统一来管理这些页面跳转
	//#4.SpringMVC
	//1.泛化参数和返回值 (封装对象)
	//2.统一管理页面跳转(可设定为dispatch和redirect重定向)
	//3.注解方式-可以跟Spring框架无缝整合
	
	//Spring框架
	//IOC/DI:
	//IOC(InverseOfControl)/DI(DependcyInject):
	//Action层要依赖Service,在运行时动态绑定
		//ioc优点
		//封装(WEB开发的4个分层，4个封装)
		//(抽象-继承)面向接口编程-动态代理
		//多态-动态绑定
	//BeanFactory/ApplicationContext (WebApplicationContext)
	//1.把第三方服务读成一个Bean
	//2.把我们具体的实现类也读成一个Bean
	//3.由工厂来控制他们的生命周期，负责他们之间的交互
	
	//AOP:
	//aspect:   类
	//joint:    对应类的方法
	//pointcut: 切点-哪些类的哪些方法需要被包含进来
	//advice:   before，after，around执行额外操作
	//weave :   
	
	//事务：
	//声明式事务（推荐）和编程式事务
	//事务的传播属性PROPAGATION:
	//PROPAGATION_REQUIRED:	1.如果没有事务的话，强制开启一个事务给他。2.如果有当前事务，把当前事务挂起，创建新事务执行
	//2.强制不开事务
	//3.1.如果没有事务的话，强制开启一个事务给他。2.如果有当前事务，就是用当前事务
	
	
	//Hibernat相关：
	//三种状态(瞬时transient 持久persisitant 游离detached)/移除removed
	
	//关联
	//             -saveOrUpdate()
	//瞬时transient -SAVE()       
	//			   -PERSIST()
	//             -MERGE()
	//
	//       -get()
	//       -load()                              -evict()
	//new -->-Query.list()         持久persisitant -close()
	//       -Query.iterator()                    -clear()
	//       -Query.unique()
	//
	//            -update()
	//游离detached -saveOrUpdate()
	//            -mere()
	//
	//new -get - 持久 -close()-游离态-update()
	//new -> 游离 -save -持久
	
	//Hibernate一级缓存，二级缓存，查询缓存
	//SESSION自带缓存(一级缓存，默认开启): 减少与数据库交互
	//二级缓存全局缓存：指定哪个JavaBean需要缓存，
	//查询缓存：(查询sql+值 作为一个key值，重复查某一块)
	
	//#多对多映射和
	//学生跟课程
	//多对多映射 转成两个 多对一映射
	//JPA(Java Persist API)
	//其他----------------------------------------------------------
	//SpringBoot->轻量化WEB框架ROR(RubyOnRails)#
	//APPClassLoader你自己本身的APP
	//EXTClassLoder
	//BootstrapClassLoader -（不可见）-
	
	
	//JAVA_HOME-环境变量
	//PATH：bin目录(可执行文件)jdk jre
	//CLASSPATH  rt.jar 通知BootstrapClassLoader来加载
	//mvn install
	//1.MVN BUILD（关联）
	//2.mvn test
	//3.mvn install（下载）
	
	
	//String, StringBuilder, StringBuffer的区别是什么？
	//#1.final类 ： 线程安全
	//#2.String s="1"; String s2=new String("3");:Java内存模型(JMM)
	//#3.IMMUTABLE不可变类:
	//equles怎么相等X
	//1.如果两个对象equals相等，那么这两个对象的HashCode一定也相同
	//2.如果对象的equals方法被重写，那么对象的HashCode方法也尽量重写
	//3.如果两个对象的HashCode相同，不代表两个对象就相同，只能说明这两个对象在散列存储结构中，存放于同一个位置
	//不可变类会产生很多额外对象（StringBuilder, StringBuffer）
	
	//HashMap (碰撞检测)
	//Hapstable concunrrentHashmap
	//LinkedHashmap , Treemap（自定义树的顺序）
	//KEY --> BUCKET(Entry(key,value)) -> BUCKET(Entry(key,value))  .equals()
	//如果两个对象的HashCode相同-
	
	//一致性Hash-单点登录
	
	public static void main(String[] args) {
		float f = 3.4f;
		long u = 2L;//Long尽量用大写
//		short s1 =1;
//		s1=(short)(s1+1);
//		short s2 =1;
//		s2+=1;
		
		//为什么要是int  int是32位 与操作系统一样
		//
		//String string = new String("string");
		//string.equals(anObject)
		//1.string  - 引用放在栈里面
		//2.new 对象 在堆里面
		//3."string"   (静态区/方法区)常量池
		
		//switch  "STRING"
		
		String string  ="ss";
		String string2 ="ss";
		String string3 =new String("ss");
		System.out.println(string == string2);
		System.out.println(string.equals(string2));
		
		System.out.println(string == string3);
		System.out.println(string.equals(string3));
		//string.hashCode()
		
		//(Key - String)
		
		String str = "abcde";
		StringBuffer sb =new StringBuffer(str);
		sb.reverse();
		System.out.println(sb.toString());
	}
	
/*
* 
68、Java中如何实现序列化，有什么意义？
答：序列化就是一种用来处理对象流的机制，所谓对象流也就是将对象的内容进行流化。可以对流化
后的对象进行读写操作，也可将流化后的对象传输于网络之间。序列化是为了解决对象流读写操作时
可能引发的问题（如果不进行序列化可能会存在数据乱序的问题）。要实现序列化，需要让一个类实
现Serializable接口，该接口是一个标识性接口，标注该类对象是可被序列化的，然后使用一个
输出流来构造一个对象输出流并通过writeObject(Object)方法就可以将实现对象写出（即保存
其状态）；如果需要反序列化则可以用一个输入流建立对象输入流，然后通过readObject方法从流
中读取对象。序列化除了能够实现对象的持久化之外，还能够用于对象的深度克隆
*/
}
