package foo.concurrent.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import foo.concurrent.annotations.NotThreadSafe;
import foo.concurrent.annotations.ThreadSafe;

/**
 * Java的锁机制<p>
 * 
 * 1.Java有几种实现多线程的方式?<br>
 * 2.Java中的锁机制?<br>
 * 3.sleep和wait - wait和notify<br>
 * 4.死锁Deadlock<br>
 * 5.ConcurrentHashMap?<br>
 * 6.线程池?<br>
 * 7.CountDownLatch?<br>
 * #awaits,CountDown,
 * @author wyy
 * 2016年10月26日
 *
 */
public class LockDemo 
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
		//executor.execute(command);
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
}
