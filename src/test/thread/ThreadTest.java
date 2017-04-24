package test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

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
 * 1.生产者和消费者问题
 * 2.Java中的锁机制？JAVA怎么线程安全? synchonrized
 *  - synchonrized 互斥锁(悲观锁（无法被抢占），非公平锁（我请求我占领）)
 *  - 怎么保证线程安全? 原子性，有序性和可见性
 *       多个线程访问同一个资源(临界区)就由可能出现线程安全问题。
 *    synchonrized(保证 原子性，有序性)
 *    volatile(保证 可见性)
 * 3.wait和sleep有什么区别
 * wait会释放锁，sleep不会释放锁
 * notify就是释放锁的方法  wait和notify一起出现
 * 4.什么是死锁(deadlock)？
 *	两个进程都在等待对方执行完毕才能继续往下执行的时候就发生了死锁。结果就是两个进程都陷入了无限的等待中。
 */


/**
 * 并发Concurrent包
 * 
 * 连接池 - 线程池 Executor
 * 阻塞队列 BlockingQueue- 生产者和消费者问题()
 * CountDownLacth 闸门# 让线程阻塞在某个时间点 一起运行
 * Semaphore  信号量 # 控制固定线程的并发
 * 
 * ConcurrentHashMap：        线程安全/锁分离（每一把锁只会锁Map某一块区域） #对比数据库 行级锁InnoDB和表级锁MyISAM
 * CopyOnWriteArrayList: 线程安全
 * 
 * 多线程相关部分：
 * 数据库表级锁和行级锁,事务,每一次request请求都是一次线程进来
*/
public class ThreadTest {
    //dxzt2yc吊销枝头二月春
	private List list = new ArrayList();//能不能作为多线程的容器
	
	//1.Executor 线程池
	public static void excutorTest(){
		//newFixedThreadPool 开一个容量为1的线程池
		ExecutorService es = Executors.newFixedThreadPool(1);
		
//		//匿名内部类
//		es.execute(new Runnable() {
//			@Override
//			public void run() {
//				for(int i=0;i<10;i++)
//				{
//					System.out.println("匿名内部类");
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
//		
//		es.execute(new Runnable() {
//			@Override
//			public void run() {
//				for(int i=0;i<10;i++)
//					System.out.println("匿名内部类2");
//			}
//		});
		
		
		//2.自动增长
		ExecutorService es2 = Executors.newCachedThreadPool();
		
		//3.schdule #自定义执行的间隔时间
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		long initialDelay1 = 1;
		long period1 = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
		service.scheduleAtFixedRate(
		        new Runnable() {
					@Override
					public void run() {
						System.out.println("匿名内部类");
					}
				}, initialDelay1,
				period1, TimeUnit.SECONDS);
//
//		long initialDelay2 = 1;
//		long delay2 = 1;
//        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2
//		service.scheduleWithFixedDelay(
//		        new ScheduledExecutorTest("job2"), initialDelay2,
//				delay2, TimeUnit.SECONDS);
	}
	
	
	
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
		//countterTest();
		
		excutorTest();
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



