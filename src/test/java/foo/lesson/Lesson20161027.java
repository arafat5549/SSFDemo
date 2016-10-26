package foo.lesson;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * Java并发与同步机制<p>
 * 
 * 1.Java有几种实现多线程的方式?<br>
 * 2.Java中的锁机制?<br>
 * 
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
		
		
		new MyThread().start();
		new Thread(new MyRunnable()).start();
		//
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
	 * 2.Java中的锁机制?
	 * 
	 * #1.内置锁synchronized
	 * #2.显式锁Lock
	 */
	@Test
	public void LockTest()
	{
		//1.synchronized 互斥锁
		class Counter
		{
			private int count = 0;
			public int inc(){
				synchronized(this){//对象锁
					return ++count;
				}
			}
		}
		//2.ReentrantLock   重进入的互斥锁
	}
}
