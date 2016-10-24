package foo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.ietf.jgss.Oid;

import scala.collection.parallel.ParIterableLike.Max;

/**
 * 生产者和消费者模型
 * 并发经典的案例
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
public class MyBlockingQueueDemo 
{
	private static int size =20;
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(size);
	public static void main(String[] args) 
	{
		MyBlockingQueueDemo rrr = new MyBlockingQueueDemo();
//		Producer p = rrr.new Producer();
//		Consumer c = rrr.new Consumer();
//		p.start();
//		c.start();
		
		
		//synchronized - 锁-互斥锁（满足1和2），3的满足需要我们自己来实现
		//volatile     - 满足2
		//
		
		//AtomicInteger只能保证原子性
		Thread t1 = rrr.new DeCounter();
		Thread t2 = rrr.new Counter();
		t2.start();
		t1.start();
			
	}
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
	//
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
	//
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
}



