package foo.lesson;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import foo.concurrent.annotations.NotThreadSafe;
import foo.concurrent.annotations.ThreadSafe;

/**
 * Java并发与同步机制<p>
 * 
 * 1.Java有几种实现多线程的方式?<br>
 * 2.Java中的锁机制?<br>
 * 3.sleep和wait - wait和notify<br>
 * 4.死锁Deadlock<br>
 * 5.ConcurrentHashMap<br>
 * 
 * 6.线程池
 * 7.CountDownLatch
 * @author wyy
 * 2016年10月26日
 *
 */
public class Lesson20161027 
{
	/**
	 * 1.Java有几种实现多线程的方式?
	 * #Thread , Runnable , Callable 
	 */
	@Test
	public void MultiThreadTest()
	{
		
		//#1继承Thread
		class MyThread extends Thread
		{
			@Override
			public void run(){
				//while (true) 
				{
					System.out.println("MyThread Running!");
				}
				
			}
		}
		//#2.实现Runnable接口
		class MyRunnable implements Runnable{
			@Override
			public void run() {
				System.out.println("MyRunnable Running!");
			}
			
		}
		//#3.实现Callable - 在执行完任务之后可以获取执行结果
		class MyCallable implements Callable<Integer>{

			@Override
			public Integer call() throws Exception 
			{
				System.out.println("子线程在进行计算");
		        Thread.sleep(3000);
		        int sum = 0;
		        for(int i=0;i<100;i++)
		            sum += i;
		        return sum;
			}
		}
		
		//#所有高级特性都有一个原则，低级特性能解决，就不用上高级特性。
		new MyThread().start();
		new Thread(new MyRunnable()).start();
		
		//回调-Callback
		ExecutorService executor = Executors.newCachedThreadPool();
		MyCallable call = new MyCallable();
		Future<Integer> result= executor.submit(call);
        try {
            System.out.println("task运行结果"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
	}
	/**
	 * synchronized
	 * 颗粒度越低越好,你syn的代码块越少越好
	 * 线程安全的三原则：
	 * 1.原子性  2.有序性  3.可见性    (锁的都是大家都能访问的内存)
	 * 
	 * synchronized的使用方式：
	 * #//对象锁-死锁
	 * #synchronized(this){ dosometing().....} 
	 * #//方法锁
	 * #public synchronized void dosometing();
	 * 
	 * synchronized是什么？
	 * #内置锁/[互斥锁]/监视锁,([悲观锁] -[确保代码块不会被线程抢占]),([非公平锁])
	 * 
	 * synchronized的机制？
	 * #1.JVM监视你所syn的代码块(可见性)
	 * #2.确保只有一个线程访问       (原子性)
	 * #3.自动释放锁
	 * 
	 * synchronized的问题：
	 * #更容易产生死锁(确保只有一个线程访问,无法被抢占)
	 * #无法实现乐观锁和公平锁
	 * 
	 * 锁的种类？
	 * #互斥锁：同一时间只能有一个线程访问
	 * #乐观锁：线程可以被抢占有一套抢占规则
	 * #悲观锁：线程无法被抢占
	 * #公平锁：线程可以被抢占的时候才有公平锁,每个锁每一次请求,计数加1
	 *         轮询计数最高的锁来抢占线程
	 * #非公平锁：只要我这个锁请求了，线程就一定是我的。
	 */
	/**
	 * 2.Java中的锁机制?
	 * #0.
	 * #1.内置锁synchronized(原子性和可见性)  [互斥锁]
	 * #2.显式锁Lock
	 * #3.原子变量Atomic*
	 * #4.volatile变量(可见性)-尽量不要使用它来保证线程安全
	 * 
	 * synchronized和Lock的区别？
	 * 
	 * #1.lock在获取锁的过程可以被中断。
	 * #2.lock可以尝试获取锁，如果锁被其他线程持有，则返回 false，不会使当前线程休眠。
	 * #3.lock在尝试获取锁的时候，传入一个时间参数，如果在这个时间范围内，没有获得锁，那么就是终止请求。
	 * #4.synchronized 会自动释放锁，lock则不会自动释放锁。
	 * 
	 * ReentrantLock：
	 * 默认非公平锁
	 * 可重入的互斥锁：指同一个线程，外层函数获得锁之后，内层递归函数仍有获得该锁的代码，但是不受影响。
	 * 可重入锁的最大作用可以避免死锁
	 * 
	 * ReentrantLock有什么新的特性？
	 * #可定时TimeOut，可轮询与可中断的获取锁请求，公平队列，
	 * !!并且当且仅当你确定你需要这些新特性而synchronized无法满足时你才使用新的特性。
	 */
	@Test
	public void TempTest(){
		//SYN-首选
		class Counter implements Runnable{
			private int mycount = 0;
			public synchronized int getCount(){return mycount;}
			@Override
			public void run() 
			{
				inc();
			}
			private synchronized void inc(){
				//synchronized (this) {
					++mycount;
				//}
			}
		}
		//Atomic原子-单字段 — CAS(compare and set)
		class AtomicCounter implements Runnable{
			private AtomicInteger mycount = new AtomicInteger(0);
			public int getCount(){return mycount.get();}
			@Override
			public void run() 
			{
				inc();
			}
			private  void inc(){
				//synchronized (this) {
					mycount.getAndIncrement();
				//}
			}
		}
		//Lock显式锁-更灵活
		class LockCounter implements Runnable{
			private int mycount = 0;
			Lock lock = new ReentrantLock();
			public int getCount(){return mycount;}
			@Override
			public void run() 
			{
				inc();
			}
			private  void inc()
			{
				lock.lock();
				try {
					++mycount;
				} finally{//一定要在finally释放锁
					lock.unlock();
				}
			}
		}
		//什么时候会出现线程安全问题?
		//1.破坏原子性
		//2.破坏有序性
		//3.可见性
		//怎么去修复他？
		//
		
		//
		Counter counter = new Counter();//Servlet单例
		for(int i=0;i<10000;i++){
			new Thread(counter).start();//
		}
		
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("counter="+counter.getCount());
	}
	
	@Test
	public void LockTest()
	{
		
		
		//1.synchronized 互斥锁
		@ThreadSafe
		final class SynCounter implements Runnable 
		{
			private int mycount = 0;
			public synchronized int getCount(){return mycount;}//syn需要对每一个调用mycount的地方加锁
			@Override
			public void run() {
				increment();
			}
			private synchronized void increment(){//1.syn方法锁
				++mycount;
//				synchronized(this){//2.syn对象锁
//					++mycount;
//				}
			}
		}
		//2.ReentrantLock   重进入的互斥锁
		@ThreadSafe
		final class LockCounter implements Runnable 
		{
			private int mycount = 0;
			public  int getCount(){return mycount;}
			private ReentrantLock  lock = new ReentrantLock();
			private void increment() {
				lock.lock();
			    try {
			    	++mycount;
			    } finally {//将unlock放入finally避免异常情况
			        lock.unlock();
			    }
			}
			@Override
			public void run() {
				increment();
			}
		}
		
		//3.不加锁
		@NotThreadSafe
		class BaseCounter implements Runnable 
		{
			private int mycount = 0;
			public int getCount(){return mycount;}
			private void increment() {
				++mycount;
			}
			@Override
			public void run() {
				increment();
			}
		}
		//4.Atmoic***
		@ThreadSafe
		class AtomicCounter implements Runnable{
			private AtomicInteger mycount = new AtomicInteger(0);
			public int getCount(){return mycount.get();}
			private void increment() {
				mycount.getAndIncrement();
			}
			@Override
			public void run() {
				increment();
			}
		}
		
		//测试代码
		BaseCounter baseCounter = new BaseCounter();
		SynCounter  synCounter = new SynCounter();
		LockCounter lockCounter = new LockCounter();
		AtomicCounter atomicCounter = new AtomicCounter();
		ExecutorService es = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 10000; i++) {
			//new Thread(baseCounter).start();
			//new Thread(synCounter).start();
			//new Thread(lockCounter).start();
			es.execute(atomicCounter);
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("baseCounter:"+baseCounter.getCount());  
		System.out.println("synCounter:"+synCounter.getCount());  
		System.out.println("lockCounter:"+lockCounter.getCount()); 
		System.out.println("atomicCounter:"+atomicCounter.getCount()); 
	}
	/**
	 * DeadLock
	 * 1.会写一个简单的死锁程序?
	 * #
	 * 2.如何解决或避免死锁?
	 * #1.定时释放 Timeout
	 * #2.可中断
	 * #3.可以被抢占
	 * 
	 */
	@Test
	public void DeadLockDemo()
	{
		//最典型的死锁-左右互搏
		 class LeftRightDeadlock {
		    private final Object left = new Object();
		    private final Object right = new Object();
		    public void leftRight() {
		        synchronized (left) {
		            synchronized (right) {
		                doSomething();
		            }
		        }
		    }
		    public void rightLeft() {
		        synchronized (right) {
		            synchronized (left) {
		                doSomethingElse();
		            }
		        }
		    }
		    void doSomething() {System.out.println("doSomething");}
		    void doSomethingElse() {System.out.println("doSomethingElse");}
		}
		 
//		LeftRightDeadlock d1 = new LeftRightDeadlock();
//		new Thread(){
//			
//			@Override
//			public void run(){
//				d1.leftRight();
//			}
//		}.start();
//		//
//		new Thread(){
//			@Override
//			public void run(){
//				d1.rightLeft();
//			}
//		}.start();
	}
	/**
	 * 线程安全的集合
	 * ConcurrentHashMap?
	 * CopyOnWriteArrayList
	 */
	@Test
	public void ConcurrentHashMapTest(){
		//ConcurrentHashMap，HashMap的区别？
		//HashMap的底层实现  链表的数组
		//hashcode分配到一个个数组（Bucket桶），每个桶里面都是一个个链表
		//数组的快速访问+链表增加删除的高性能
		
		//HashTable线程安全   synchronized
		//ConcurrentHashMap线程安全
		//1.分锁机制来实现安全 
		//2.什么是分锁机制？
		//#他把所有的键值对Entry分为16个segment段
		//#读取不加锁,写的时候加锁,在你锁写的那个segment那个段加锁
		@NotThreadSafe
		class MapCounter implements Runnable
		{
			//0.Lock 自己实现锁
			//1.synchronized 自己实现锁
			//2.HashTable 原子变量
			//3.ConcurrentHashMap 原子变量
            HashMap<String, String> map = new  HashMap<String, String>();
			@Override
			public void run() {
				AddMap();
			}
			private void AddMap(){
				map.put("1", "1");
			}
		}
	} 
	/**
	 * 线程池
	 */
	@Test
	public void ThreadPoolTest()
	{
		//1.遇到并发问题优先使用线程池
		ExecutorService es = Executors.newSingleThreadExecutor();//单线程
		ExecutorService es2 = Executors.newFixedThreadPool(1);//指定线程池大小
		ExecutorService es3 = Executors.newCachedThreadPool();//自增长
		ScheduledExecutorService ses =
	               Executors.newScheduledThreadPool(1);//固定周期执行
	    
	}
	
	
	/**
	 * sleep和wait
	 * #sleep不会释放锁
	 * #wait会释放对象锁
	 * wait和notify
	 * #wait：对象等待，会释放对象锁
	 * #notify：唤醒对象，你的锁可以被获取的时候才有意义
	 */
//	@Test
//	public void JDKThreadDemo()
//	{
		
	    static class Thread1 implements Runnable{
	        @Override
	        public void run(){
	           synchronized (Lesson20161027.class) 
	           {
	            System.out.println("enter thread1...");    
	            System.out.println("thread1 is waiting...");
	            try {
	                //调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
	            	Lesson20161027.class.wait();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            System.out.println("thread1 is going on ....");
	            System.out.println("thread1 is over!!!");
	            }
	        }
	    }
	    //
	    static class Thread2 implements Runnable{
	        @Override
	        public void run(){
	            synchronized (Lesson20161027.class) {
	                System.out.println("enter thread2....");
	                System.out.println("thread2 is sleep....");
	                //只有针对此对象调用notify()方法后本线程才进入对象锁定池准备获取对象锁进入运行状态。
	                Lesson20161027.class.notify();
	                //==================
	                //区别
	                //如果我们把代码：TestD.class.notify();给注释掉，即TestD.class调用了wait()方法，但是没有调用notify()
	                //方法，则线程永远处于挂起状态。
	                try {
	                    //sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，
	                    //但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
	                    //在调用sleep()方法的过程中，线程不会释放对象锁。
	                    Thread.sleep(2000);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                System.out.println("thread2 is going on....");
	                System.out.println("thread2 is over!!!");
	            }
	        }
	    }

	//}
	
	public static void main(String[] args) {
		//System.currentTimeMillis()
		
	    //
//        new Thread(new Thread1()).start();
//        try {
//            Thread.sleep(2000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        new Thread(new Thread2()).start();

		
	  //CountDownLatch
      System.out.println("准备开始...");
      Race race = new Race(
          "[#1孙悟空]",
          "[#2马大哈]",
          "[#3美女]",
          "[#4闪电]",
          "[#5科比]",
          "[#6呆呆]",
          "[#7詹姆斯]"
      );
      System.out.println("本场比赛的长度为 " + race.getDistance());
      System.out.println("输入Enter开始比赛....");
      try {
		 System.in.read(); 
		 race.runRace();
      } 
      catch (IOException e) {}
      catch(InterruptedException e){}
	}
	
	
}

/**
 * CountDownLatch
 * #awaits -> Thread.CurrentThread();调用了这个方法的线程都会被阻塞
 * #CountDown的时候统一释放这些线程
 * 闸门（所有线程会等待闸门开启的时候一起启动）
 */
class Race
{
	//1.有多少匹马  List<String> horses
	//2.比赛长度 - 随机数Random.nextInt(200)
	//3.每匹马一个线程   (随机距离-随机时间)
	//4.CountDownLatch start; //发令枪
	//5.CountDownLatch finish;//等所有人跑完才举行颁奖典型
	private Random rand= new Random();
	private final int distance = rand.nextInt(200);
	public int getDistance(){return distance;}
	private List<String> horses = new ArrayList<String>();
	public Race(String...names){//可变参数
		this.horses.addAll(Arrays.asList(names));
	}
	
	ExecutorService es = Executors.newFixedThreadPool(10);
	public void runRace() throws InterruptedException
	{
		//Runtime.getRuntime().availableProcessors();
		final CountDownLatch start  = new CountDownLatch(1);
		final CountDownLatch finish = new CountDownLatch(horses.size());
		final List<String> places = 
	            Collections.synchronizedList(new ArrayList<String>());
	    for(final String s:horses){
		   es.execute(
		   new Runnable() //匿名内部类 来实现Runnable线程
		   {
			    int traveld = 0;
				@Override
				public void run() 
				{
					try {
						System.out.println(s+"..horse awaits.");
						start.await();
						while(traveld < distance)
						{
						   Thread.sleep(rand.nextInt(3) * 1000);
						   traveld += rand.nextInt(15);
						   System.out.println(s+" traveld "+traveld);
						}
						finish.countDown();
						System.out.println(s+"冲过了终点");
						places.add(s);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		   });
		   //.start();
	   }
	   //System.out.println(Thread.currentThread());
	   es.shutdown();
	   Thread.sleep(1000L);
	   System.out.println("And... they're off!");
	   start.countDown(); 
	   
       finish.await();
       System.out.println(places.get(0)+"获得金牌");
       System.out.println(places.get(1)+"获得银牌");
       System.out.println(places.get(2)+"获得铜牌");
       
	}
}
