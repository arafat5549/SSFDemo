package foo.lesson.summary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 1.什么是线程安全?
 * 2.JAVA是怎么实现线程安全的?
 * 2-1.线程池的用法与优势?
 * 2-2.描述ArrayBlockingQueue, CountDownLatch , Semaphore ,Executors类的作用
 * 
 * 3.JAVA的类加载机制?
 * 4.java的异常处理?Error和Exception的区别?
 * 4-0.常见的运行时异常有哪些?你们会编写一个来产生OOM或者SOF错误?
 * 4-1.运行时异常和检察时异常有啥区别?
 * 4-2阐述final、finally、finalize的区别?
 * 5.谈谈你所了解的设计模式?
 * 6.什么是UML?类图和时序图?
 * 7.什么是动态代理?
 * 8.什么是反射?
 * 9.什么是注解?
 * 10.什么是泛型?
 * 11.Java中的四种引用及其应用场景是什么？
 * 
 * <b>Java虚拟机与GC</b><p>
 * 1.java内存模型(JMM)?它分为哪几个区域?
 * 2.什么是GC(GarbageCollect)?他是怎么工作的?【JAVA的内存管理机制】
 * 3.什么是内存泄漏?  怎么避免内存泄漏? 说说一个你遇到内存泄漏的例子?
 * 
 * 看懂UML：
 * http://design-patterns.readthedocs.io/zh_CN/latest/read_uml.html
 * JAVA面试题: 内存相关
 * http://www.importnew.com/22083.html 
 * @author wyy
 * 2016年12月12日
 *
 */
public class LessonJavaSE 
{
	
	/**
	 * 1.什么是线程安全?
	 */
	//1.什么时候产生：多个线程同时访问同一个资源()
	/**
	 * 2.JAVA是怎么实现线程安全的?
	 */
	//2.保证线程安全需要实现三个特性：
	//1.原子性
	//2.有序性
	//3.可见性
	//java使用锁机制来实现线程安全
	//1.synchorinzed
	//synchorinzed本质上是互斥锁，悲观锁，非公平锁.
	//synchorinzed保证了有序性和可见性
	
	//2.原子变量Atomic*
	//保证了原子性
	
	//3.Lock Lock和synchorinzed的区别？
	//LOCK可以实现乐观锁和公平锁
	//LOCK默认是非公平锁
	//使用LOCK的时候一定要在finally里面释放锁
	//乐观锁和公平锁可以最大程度避免死锁
	/**
	 * 3.JAVA的类加载机制ClassLoader?什么是双亲委托模型?
	 */
	//#java程序怎么运行起来的?
	//1.编译过程： 。java编译成。class（字节码）
	//2.JVM加载class文件来运行程序.
	
	//类的加载是由类加载器完成的，JAVA有三个类加载器包括：
	//根加载器（BootStrap）[对程序不可见,加载启动JVM相关的类]、
	//扩展加载器（Extension）[从java.ext.dirs系统属性所指定的目录中加载类库，一些扩展类库]、
	//系统加载器（System）[加载我们自己编写的类和第三方Class，应用最广泛的类加载器]
	//#用户自定义类加载器（java.lang.ClassLoader的子类）
	
	//双亲委托机制：
	//加载规则：先尝试调用父类的类加载器,只有父类类加载器调用不到这个类，才会启动子类的类加载器。
	//优点：避免用户覆盖父类的类，是一种类加载相关的安全机制
	//缺点：如果你使用用户自定义类加载器的话需要执行一些特殊的操作。
	
	//用户自定义类加载器应该怎么做：
	//1.把你的父类加载器设置为null
	//2.把自定义加载器放置到你的加载器序列里面 由它来加载。
	
	//Tomcat的自定义类加载器的例子。
	
	//<b>Java虚拟机与GC</b><p>
	/**
	 * 1.java内存模型(JMM)?它分为哪几个区域?什么时候会产生OOM错误/SOF错误?
	 * 你能写出一个OOM或者SOF错误？
	 */
	//JAVA运行时怎么分配这个内存区域:
	//1.堆内存(Heap):
	//所有对象的创建new 都是分配到堆内存里面
	//OOM(OutOfMemory)内存溢出：当超过指定的最大堆内存时就会产生.
	
	//2.栈内存(JavaVirtualStack虚拟栈):
	//对象的引用,基本数据类型
	//StackOverFlow(SOF):分配的地址超过你栈的最大值就会出现这个错误
	//OOM：存放的引用超过你的内存大小就会引发
	
	//3.本地方法栈(): JNI
	//JNI调用其他语言时，存储的公用区域
	
	//4.方法区/静态区：
	//静态方法，静态变量，方法实例都存放在这里(也包含了常量池)，编译时产生
	
	//5.程序计数器
	
	//举个例子：
	//String str = new String("hello");
	//1.堆内存：  new出来的对象
	//2.常量池："hello" 放在常量池
	//3.虚拟栈： str引用放在栈里面
	
	/**
	 * 2.什么是GC(GarbageCollect)?他是怎么工作的?【JAVA的内存管理机制】
	 */
	//手动调用GC，Systema.gc();手动GC会打乱垃圾收集本身的工作，并不一定能达到
	//提升性能的效果，不推荐手动调用。
	
	//#什么是GC
	//GC：垃圾收集。是JAVA管理内存的机制。JAVA程序员不需要手动管理内存，
	//而由垃圾收集器来管理，让我们可以专注于业务代码
	
	//2.GC是什么工作的? GC算法：标记和清除 / 引用计数
	//Java平台对堆内存回收和再利用的基本算法被称为标记和清除，但是Java对其进行了改进，
	//采用“分代式垃圾收集”。这种方法会跟Java对象的生命周期将堆内存划分为不同的区域，
	//在垃圾收集过程中，可能会将对象移动到不同区域：
	//- 伊甸园（Eden）/新生代：这是对象最初诞生的区域，并且对大多数对象来说，
	//这里是它们唯一存在过的区域。
	//- 幸存者乐园（Survivor）/新生代：从伊甸园幸存下来的对象会被挪到这里。
	//- 终身颐养园（Tenured）/老年代：这是足够老的幸存对象的归宿。年轻代收集（Minor-GC）
	//过程是不会触及这个地方的。当年轻代收集不能把对象放进终身颐养园时，就会触发一次
	//完全收集（Major-GC），这里可能还会牵扯到压缩，以便为大对象腾出足够的空间。
	
	//Jconsole:来查看你的应用的内存分配
	
//	与垃圾回收相关的JVM参数：
//	-Xms / -Xmx — 堆的初始大小 / 堆的最大大小
// 	-XXMaxPermSize   - 设置静态区的大小
//	-Xss             - 设置虚拟栈的大小
//	-Xmn — 堆中年轻代的大小
//	-XX:-DisableExplicitGC — 让System.gc()不产生任何作用
//	-XX:+PrintGCDetails — 打印GC的细节
//	-XX:+PrintGCDateStamps — 打印GC操作的时间戳
//	-XX:NewSize / XX:MaxNewSize — 设置新生代大小/新生代最大大小
//	-XX:NewRatio — 可以设置老生代和新生代的比例
//	-XX:PrintTenuringDistribution — 设置每次新生代GC后输出幸存者乐园中对象年龄的分布
//	-XX:InitialTenuringThreshold / -XX:MaxTenuringThreshold：设置老年代阀值的初始值和最大值
//	-XX:TargetSurvivorRatio：设置幸存区的目标使用率
	/**
	 * 3.什么是内存泄漏?  怎么避免内存泄漏? 说说一个你遇到内存泄漏的例子?
	 */
	//#什么是内存泄漏?
	//我们以前写C的时候，我们需要手动分配和释放内存。
	//当你分配了内存，但没有释放的时候就会产生内存泄漏问题.
	//JAVA中的内存泄漏问题一般是内存得不到释放,(比如数据库连接一定要close)
	
	//#怎么避免内存泄漏?#说说一个你遇到内存泄漏的例子?
	//1.集合中存放JAVA对象需要特别小心
	//2.IO连接 数据库连接没有释放
	
	//#说说一个你遇到内存泄漏的例子?
	
	/**
	 * 4.java的异常处理?Error和Exception的区别?
	 */
	//-----Error和Exception的区别?
	//Error和Exception都是Throwable的子类
	//Error:更严重的，更致命的错误，导致程序崩溃 无法运行.
	//1-OutOfMemory    内存溢出（JVM会分配一块指定大小的内存给它，如果你创建的对象超过它的大小就会OOM）
	//2-StackOverFlow  栈溢出（你分配的地址大于你分配给它的虚拟栈的大小）
	
	//Exception:
	//异常表示程序运行过程中可能出现的非正常状态，运行时异常表示虚拟机的通常操作中可能遇到的异常
	//1-RuntimeException: 运行时异常
	//#1.NullPointerException 空指针
	//#2.ArrayIndexOut  下标越界
	//#3.ClassNotFount  找不到类
	//#4.ClassCast      类型转换异常
	//#5.Arithmetrci    算术异常
	//#6.IlegalArgument 非法参数
	//2-IOException:
	//FileNotFound 找不到文件
	
	//-----java的异常处理?
	//异常处理相关的5个关键字:try/catch/finally /throw / throws
	
	//#try用来指定一块预防所有异常的程序；
	//#catch子句紧跟在try块后面，用来指定你想要捕获的异常的类型；
	//#finally为确保一段代码不管发生什么异常状况都要被执行；
	//try语句可以嵌套，每当遇到一个try语句，异常的结构就会被放入异常栈中，
	//直到所有的try语句都完成。如果下一级的try语句没有对某种异常进行处理，
	//异常栈就会执行出栈操作，直到遇到有处理这种异常的try语句或者最终将异常抛给JVM。
	
	//#throw语句用来明确地抛出一个异常；
	//#throws用来声明一个方法可能抛出的各种异常（当然声明异常时允许无病呻吟）；
	//异常处理的原则之一谁调用谁处理.(异常更好定位)
	//1.不要将异常处理用于正常的控制流(不要用异常实现类似于if else的功能)
	/**
	 * 4-0.常见的运行时异常有哪些?你们会编写一个来产生OOM或者SOF错误?
	 */
	//OOM:
	//1.设定你最大堆内存的大小
	//2.在一个无限循环中，一直new创建对象。
	
	//设定堆内存大小，输出错误信息：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
	//设定方法区大小：         -XX:PermSize=10M -XX:MaxPermSize=10M
	//设定虚拟栈内存大小: -Xss128k
	/**
	 * 4-1.运行时异常和检察时异常有啥区别?
	 */
	//异常表示程序运行过程中可能出现的非正常状态，运行时异常表示虚拟机的通常操作中可能遇到的异常
	//异常使用的原则：
	//1.不要将异常处理用于正常的控制流(不要用异常实现类似于if else的功能)
	
	/**
	 * 4-2阐述final、finally、finalize的区别。
	 */
	//#final修饰符：
	//final修饰类的时候代表这个类不可被继承
	//final字段和方法的时候，他们是不可变的(字段不可变(常量),方法不可被重写)
	
	//#finally
	//为确保一段代码不管发生什么异常状况都要被执行；
	//在finally代码块中的代码一定会被执行，除非整个程序崩溃
	
	//#finalize:
	//Object类中定义的方法，Java中允许使用finalize()方法在垃圾收集器将对象从
	//内存中清除出去之前做必要的清理工作。这个方法是由垃圾收集器在销毁对象时调用的，
	//通过重写finalize()方法可以整理系统资源或者执行其他清理工作。
	
	/**
	 * 5.谈谈你所了解的设计模式?
	 */
	//什么是设计模式？
	//设计模式（Design pattern）代表了最佳的实践，通常被有经验的面向对象的软件开发人员所采用。设计模式是软件开发人员在软件开发过程中面临的一般问题的解决方案。
	//这些解决方案是众多软件开发人员经过相当长的一段时间的试验和错误总结出来的。
	
	//创建者模式：
	//1.简单工厂模式：
	//2.工厂方法模式：
	//3.抽象工厂：
	//4.单例模型:
	
	//结构型模式：
	//1.装饰模式：
	//2.代理模式：
	
	//行为型模式：
	//1.策略模式：
	//2.命令模式：
	//3.观察者模式:
	/**
	 * 6.什么是UML?类图和时序图？
	 */
	//UML统一建模语言，是软件工程范畴中的一种。用于大型软件设计中团队协作
	//利用类图和时序图等让需求更加明确。
	
	//类图
	//#泛化关系 代表类之间的练习
	//1.实现关系    图例：虚线的空心箭头
	//2.基础关系    图例：实线的空心箭头
	//#很多时候是数据表之间关系
	//1.聚合关系    图例：一条带空心菱形箭头的直线
	//2.组合关系    图例：一条带实心菱形箭头直线
	//3.关联关系    图例：一条实线
	
	//聚合关系(一对多/多对一)和组合关系区别？
	//班级和学生就是聚合关系，班级对象不在了 学生对象还是存在
	//发动机、轮胎和汽车就是组合关系，汽车对象不在了 发动机对象也就不存在了
	//组合关系是一种强依赖的特殊聚合关系，如果整体不存在了，则部分也不存在了；
	//关联关系
	//学生和身份证 一般以成员变量的方式来实现，代表二者有关联
	
	//时序图
	//时序图（Sequence Diagram）是显示对象之间交互的图，这些对象是按时间顺序排列的。
	//顺序图中显示的是参与交互的对象及其对象之间消息交互的顺序。
	//时序图中包括的建模元素主要有：
	//#对象（Actor）、
	//系统角色，可以是人、及其甚至其他的系统或者子系统。
	//生命线（Lifeline）、
	//
	//控制焦点（Focus of control）、
	//#消息（Message）
	//同步消息
	//异步消息
	//返回消息
	
	/**
	 * 7.什么是动态代理?
	 */
	/**
	 * 8.什么是反射?
	 */
	//反射的作用概括地说是运行时获取类的各种定义信息，
	//比如定义了哪些属性与方法。原理是通过类的class对象来获取它的各种信息。
	/**
	 * 9.什么是注解? 
	 */
//	注解可以看作是“增强版的注释”，它可以向编译器、虚拟机说明一些事情。
//	注解是描述Java代码的代码，它能够被编译器解析，注解处理工具在运行时也能够解析注解。注解本身是“被动”的信息，只有主动解析它才有意义。
//	除了向编译器/虚拟机传递信息，我们也可以使用注解来生成一些“模板化”的代码。
	/**
	 * 10.什么是泛型?
	 */
	
	public static void main(String[] args) {
		ArrayList<Object> list = new ArrayList<Object>();
		Object testObject = new Object();
		list.add(testObject);
		
		testObject = null;
		
		//ClassLoader
	}
}

//当我调用pop时候不会发生垃圾回收

class MyStack<T> {
	 private T[] elements;
	 private int size = 0;
	 
	    private static final int INIT_CAPACITY = 16;
	    
	    public MyStack() {
	        elements = (T[]) new Object[INIT_CAPACITY];
	    }
	 
	    public void push(T elem) {
	        ensureCapacity();
	        elements[size++] = elem;
	    }
	 
	    public T pop() {
	        if(size == 0) 
	            throw new EmptyStackException();
	        return elements[--size];
	    }
	 
	    private void ensureCapacity() {
	        if(elements.length == size) {
	            elements = Arrays.copyOf(elements, 2 * size + 1);
	        }
	    }
}
