package foo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 使用BlockingQueue实现生产者和消费者
 * 
 * @author wyy
 *
 */ 
public class BlockingQueueDemo {
	  private int size = 20;
	  private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(size);
	  public static void main(String[] args) {
	    BlockingQueueDemo test = new BlockingQueueDemo(); 
	    Producer producer = test.new Producer(); 
	    Consumer consumer = test.new Consumer(); 
	    producer.start(); consumer.start(); 
	  } 

	  class Consumer extends Thread{ 
	    @Override 
	    public void run() { 
	      while(true){ 
	        try { 
	          //从阻塞队列中取出一个元素 
	          queue.take(); 
	          System.out.println("队列剩余" + queue.size() + "个元素"); 
	        } catch (InterruptedException e) { 
	        } 
	      } 
	    } 
}
	  
	  
	  class Producer extends Thread{ 
		    @Override 
		    public void run() { 
		      while (true) { 
		        try { 
		          //向阻塞队列中插入一个元素 
		          queue.put(1); 
		          System.out.println("队列剩余空间：" + (size - queue.size())); 
		        } catch (InterruptedException e) { } 
		      } 
		    } 
		  }
	}
