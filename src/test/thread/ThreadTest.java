package test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class ThreadTest {

	
	
	
	/**
	 * java如何创建线程
	 */
	@Test
	public void test1(){
		T1 t = new T1();
		t.start();
	}
	
	//需求1  开启5个线程分别统计 最后统计相加所所有的结果
	static void t1() throws InterruptedException{ 
		   for(int i=0;i<10;i++){
			   Thread t = new MyThread ();
			   t.start();
		   }
		   Thread.sleep(2000);//让上面的线程运行完成
		   R r = new R();
		   for (int i = 0;i<10;i++){
			   Thread t = new Thread(r);
			   t.start(); 
		   }
	}
	/**
	 * 测试我们的计数器
	 * @throws InterruptedException 
	 */
	static void countterTest() throws InterruptedException{
		int SUM = 10000;
//		Counter c = new Counter();
//		for (int i = 0;i<SUM;i++){
//			   Thread t = new Thread(c);
//			   t.start(); 
//			//c.run();
//		 } 
//		Thread.sleep(1000);//等待线程运行完毕
//		System.out.println("总计:"+c.getCount());
		
		SynCounter c2 = new SynCounter();
		for (int i = 0;i<SUM;i++){
			   Thread t = new Thread(c2);
			   t.start(); 
		 } 
		Thread.sleep(1000);//等待线程运行完毕
		System.out.println("2#总计:"+c2.getCount());
		
		AtmoicCounter c3 = new AtmoicCounter();
		for (int i = 0;i<SUM;i++){
			   Thread t = new Thread(c3);
			   t.start(); 
		 } 
		Thread.sleep(1000);
		System.out.println("3#总计:"+c3.getCount());
	}
	
	public static void main(String[] args) throws Exception {
		//T1 t = new T1();
		//t.start();
		
		
		//T2 t2 = new T2();
		//t2.run();//以类的方式来处理
		
		//这个才是开线程处理的方式
		//new Thread(new T2()).start();;
		
		
//		T3 t3 = new T3();
//		Integer c = t3.call();
//		System.out.println(c);
		
		//t1();
		countterTest();
		
	}
}


class MyThread extends Thread{
	public int x = 0;
	public void run(){
		System.out.println(++x);
	}
}
class R implements Runnable{
	private int x = 0;
	public void run(){
		System.out.println(toString()+"Runnable:"+(++x));
	}
}



//1.继承Thread类（重写run方法）
class T1 extends Thread{
	boolean isRun = true;
	@Override
	public void run() {
		//如何维持
		while(isRun)
		{
			System.out.println("这是一个线程");
		}
	}
}

//2.实现Runnable接口
class T2 implements Runnable{
	boolean isRun = true;
	@Override
	public void run() {
		//while(isRun)
		{
			System.out.println("这是一个线程222");
		}
	}
	
}

//3.回调函数 - callabel - 返回结果
class T3 implements Callable<Integer>{
    int count = 0;
	@Override
	public Integer call() throws Exception {
		if(count<100)
			count = count+1;
		//else
		return count;
	}
	
}


/**
 * 区分哪些代码是线程安全 哪些代码是线程不安全
 * 
 * 怎么判读一个类是线程安全
 * 
 * 原子性
 * 有序性
 * 可见性
 * 
 * 三种实现线程安全的方式：如何实现线程安全
 * synchrinzed  (互斥锁[我没有释放我锁定的资源之前 别人不能访问资源]，非公平锁[只要我请求就一定是我的]) 推荐方式
 * Atomic*
 * Lock         (隐式锁)
 * 
 * 并发包的内容：
 * 
 * 1.生产者和消费者问题
 * 
 */

class Counter implements Runnable{
	private int count = 0;
	@Override
	public void run() {
		count++;  //1.相加 2.赋值
	}
	public int getCount(){
		return count;
	}
}

//使用synchronized来实现线程安全  本质上互斥锁  原子性和有序性 
//JAVA默认都是最保守的方式
//servlet每次请求都是一个线程 10万人访问如何正确计算
//推荐方式 只有synchronized达不到你的要求 再考虑其他方式
class SynCounter implements Runnable{
	private volatile int count = 0;
	@Override
	public void run() {
		 inc();  //1.相加 2.赋值
	}
	public synchronized int getCount(){
		return count;
	}
	
	private synchronized void inc(){
		count++; //非原子操作- count+1 count=count+1
	}
}
//2.原子变量  并发包的类型 # 保证了原子性
class AtmoicCounter implements Runnable
{
	private AtomicInteger count = new AtomicInteger(0);
	public  int getCount(){return count.get();}
	@Override
	public void run() {
		inc();
	}
	private  void inc(){
		count.getAndIncrement();//先获取再相加
	}
}
//重入锁 - 并发包自定义的锁机制
class LockCounter implements Runnable
{
	private  int count = 0;
	Lock lock = new ReentrantLock();
	@Override
	public void run() {
		lock.lock();
		try{
			count++;
		}
		finally{
			lock.unlock();//
		}
	}
	public  int getCount(){return count;}
}