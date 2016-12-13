package foo.lesson.summary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 1.什么是线程安全?
 * 2.JAVA是怎么实现线程安全的?
 * 3.JAVA的类加载机制?
 * 4.java的异常处理?Error和Exception的区别?
 * 5.谈谈你所了解的设计模式?
 * 6.什么是UML?类图和时序图？
 * 
 * <b>Java虚拟机与GC</b><p>
 * 1.java内存模型(JMM)?它分为哪几个区域?
 * 2.什么是GC(GarbageCollect)?他是怎么工作的?【JAVA的内存管理机制】
 * 3.什么是内存泄漏?  怎么避免内存泄漏? 说说一个你遇到内存泄漏的例子?
 * 
 * @author wyy
 * 2016年12月12日
 *
 */
public class LessonJavaSE 
{
	/**
	 * 什么是线程安全?
	 */
	
	
	/**
	 * 3.JAVA的类加载机制ClassLoader?什么是双亲委托模型?
	 */
	//#java程序怎么运行起来的?
	//1.编译过程： 。java编译成。class（字节码）
	//2.JVM加载class文件来运行程序.
	
	//classpath: rt.jar
	//
	//类的加载是由类加载器完成的，类加载器包括：
	//根加载器（BootStrap）[对程序不可见]、
	//扩展加载器（Extension）、系统加载器（System）和用户自定义类加载器（java.lang.ClassLoader的子类）
	
	//双亲委托机制：避免用户覆盖父类的类，是一种类加载相关的安全机制
	//先尝试调用父类的类加载器,只有父类类加载器调用不到这个类，才会启动子类的类加载器。
	
	//用户自定义类加载器
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
	
	//GC：垃圾清理。JAVA管理内存的机制。JAVA程序员不需要手动管理内存，而由垃圾收集器
	//来管理。
	
	//GC算法：标记和清除 / 引用计数
	//Java平台对堆内存回收和再利用的基本算法被称为标记和清除，但是Java对其进行了改进，
	//采用“分代式垃圾收集”。这种方法会跟Java对象的生命周期将堆内存划分为不同的区域，
	//在垃圾收集过程中，可能会将对象移动到不同区域：
	//- 伊甸园（Eden）：这是对象最初诞生的区域，并且对大多数对象来说，
	//这里是它们唯一存在过的区域。
	//- 幸存者乐园（Survivor）：从伊甸园幸存下来的对象会被挪到这里。
	//- 终身颐养园（Tenured）：这是足够老的幸存对象的归宿。年轻代收集（Minor-GC）
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
	
	//#怎么避免内存泄漏? 
	//1.集合中存放JAVA对象需要特别小心
	//2.IO连接 数据库连接没有释放
	
	//#说说一个你遇到内存泄漏的例子?
	//
	
	//JAVA面试题: http://www.importnew.com/22083.html 内存相关
	
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
