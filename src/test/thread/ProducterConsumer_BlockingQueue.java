package test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 生产者和消费者模型
 * 并发经典的案例
 * 
 * ArrayBlockingQueue阻塞队列
 * 
 * 双方都可以访问的一个状态变量
 * queue:类本身是线程安全的
 * 
 * 线程安全的三个原则
 * 1.原子性
 * 2.可见性
 * 3.有序性
 * @author wyy
 * 2016年10月24日
 *
 */
public class ProducterConsumer_BlockingQueue 
{
	private static int size =20;
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(size);
	
	
	public static void main(String[] args) 
	{
		ProducterConsumer_BlockingQueue rrr = new ProducterConsumer_BlockingQueue();
		Producer p = rrr.new Producer();
		Consumer c = rrr.new Consumer();
		p.start();
		c.start();
		
//		//synchronized - 锁-互斥锁（满足1和2），3的满足需要我们自己来实现
//		//volatile     - 满足2
//		
//		//AtomicInteger只能保证原子性
//		Thread t1 = rrr.new DeCounter();
//		Thread t2 = rrr.new Counter();
//		t2.start();
//		t1.start();
			
	}
	
	/*
	private volatile int max = 0;
	AtomicInteger max3 = new AtomicInteger();
	AtomicReference<Integer> max2= new AtomicReference<Integer>();
	
	public  void addAndDecree(int value){
		//max3.addAndGet(value);
		max2.getAndSet(value);
		max3.getAndSet(value);
		max+=value;
	}
	
	class DeCounter extends Thread{
		@Override
		public  void run() {
			while (true) {
				//addAndDecree(1);
				max3.getAndSet(-1);
				//int now = max.decrementAndGet();
				addAndDecree(-1);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("smax:"+max3);
			}
			
		}
	}
	class Counter extends Thread{
		@Override
		public  void run() {
			//addAndDecree(1);
			max3.getAndSet(1);
			//int now = max.incrementAndGet();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("max:"+max3);
		}
		
	}
	*/
	
	/**
	 * 往队列里面加东西
	 * @author wyy
	 * 2017年4月24日
	 *
	 */
	class Producer extends Thread{

		@Override
		public void run() {
			while (true) {
				try {
					queue.put(1);
					Thread.sleep(1000);
					System.out.println("添加一个，剩余:"+queue.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	/**
	 * 往队列里面减东西
	 * @author wyy
	 * 2017年4月24日
	 *
	 */
	class Consumer extends Thread{

		@Override
		public void run() {
			while (true) {
				try {
					queue.take(); 
					Thread.sleep(1500);
					System.out.println("取一个，剩余:"+queue.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	//实现文档爬虫
	
	
}



