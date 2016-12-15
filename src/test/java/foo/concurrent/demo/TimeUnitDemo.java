package foo.concurrent.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 
 * Concurrent包探秘<p>
 * Callable :
 * Executor ：具体Runnable任务的执行者。<br>
 * ExecutorService ：一个线程池管理者，其实现类有多种，我会介绍一部分。我们能把Runnable,Callable提交到池中让其调度<br>
 * Semaphore ：一个计数信号量<br>
 * ReentrantLock ：一个可重入的互斥锁定 Lock，功能类似synchronized，但要强大的多。<br>
 * Future ：是与Runnable,Callable进行交互的接口，比如一个线程执行结束后取返回的结果等等，还提供了cancel终止线程。<br>
 * BlockingQueue ：阻塞队列。<br>
 * CompletionService : ExecutorService的扩展，可以获得线程执行结果的<br>
 * CountDownLatch ：一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。<br>
 * CyclicBarrier ：一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点<br>
 * Future ：Future 表示异步计算的结果。<br>
 * ScheduledExecutorService ：一个ExecutorService，可安排在给定的延迟后运行或定期执行的命令。<br>
 * 
 * @author wyy
 * 2016年10月25日
 *
 */
public class TimeUnitDemo {
   public static void main(String[] args)  {
	  //TimeUnit.NANOSECONDS
	   
	   //1.CopyOnWriteArrayList
	   //读取频繁，但很少有写操作的集合，例如JavaBean事件的 Listeners。
	   //2.BlockingQueue:阻塞队列（生产者和消费者模型）
//	   BlockingQueue<String> drop = new ArrayBlockingQueue<String>(1, true);
//	   BlockingQueue<String> drop = new SynchronousQueue<String>();
//       (new Thread(new Producer(drop))).start();
//       (new Thread(new Consumer(drop))).start();
       
//	     //Semaphore
//	     LimitedCall call = new LimitedCall();
//	     for (int i=0; i<10; i++)
//	         new Thread(call).start();
	     
	   //CountDownLatch
       System.out.println("准备开始...");
       Race r = new Race(
           "[#1孙悟空]",
           "[#2马大哈]",
           "[#3美女]",
           "[#4闪电]",
           "[#5科比]",
           "[#6呆呆]",
           "[#7詹姆斯]"
       );
       System.out.println("本场比赛的长度为 " + r.getDistance());
       System.out.println("输入Enter开始比赛....");
       try {
		 System.in.read();
		 r.run();
       } 
       catch (IOException e) {}
       catch(InterruptedException e){}

	   //ScheduledExecutorService：固定时间，周期的线程池调用
//	   ScheduledExecutorService ses =
//               Executors.newScheduledThreadPool(1);
//           Runnable pinger = new Runnable() {
//               public void run() {
//                   System.out.println("PING!");
//               }
//           };
//           ses.scheduleAtFixedRate(pinger, 2, 2, TimeUnit.SECONDS);
           
   }
   
   
}


/**
 * <b>Semaphore(信号量)</b>是允许一次进入一个线程的并发性类
 *
 */
class LimitedCall implements Runnable
{
	
    final Random rand = new Random();
    final Semaphore available = new Semaphore(2);
    int count = 0;
    @Override
    public void run()
    {
        int time = rand.nextInt(15);
        int num = count++;
        try
        {
            available.acquire();
            System.out.printf("%d 秒后执行[#%d]\n",time,num);
        
            Thread.sleep(time * 1000);
            System.out.println("#[" + num + "]执行完毕!");
            available.release();
        }
        catch (InterruptedException intEx)
        {
            intEx.printStackTrace();
        }
    }
};

/**
 * CountDownLatch /赛马场的起跑门栅
 * 此类持有所有空闲线程，直到满足特定条件，这时它将会一次释放所有这些线程
 * 
 */
class Race
{
    private Random rand = new Random();
    
    private int distance = rand.nextInt(250);
    private CountDownLatch start;
    private CountDownLatch finish;
    
    private List<String> horses = new ArrayList<String>();
    
    public Race(String... names)
    {
        this.horses.addAll(Arrays.asList(names));
    }
    public int getDistance(){
    	return distance;
    }
    
    public void run() throws InterruptedException
    {
        System.out.println("马儿准备撒欢跑啦...");
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch finish = new CountDownLatch(horses.size());
        final List<String> places = 
            Collections.synchronizedList(new ArrayList<String>());
        
        for (final String h : horses)
        {
            new Thread(new Runnable() {
            	@Override
                public void run() {
                    try
                    {
                        System.out.println(h + " stepping up to the gate...");
                        start.await();
                        
                        int traveled = 0;
                        while (traveled < distance)
                        {
                            Thread.sleep(rand.nextInt(3) * 1000);
                            traveled += rand.nextInt(15);
                            System.out.println(h + " 已经跑了 " + traveled + "!");
                        }
                        finish.countDown();
                        System.out.println(h + " 冲过了终点!");
                        places.add(h);
                    }
                    catch (InterruptedException intEx)
                    {
                        System.out.println("比赛异常退出!!!");
                        intEx.printStackTrace();
                    }
                }
            }).start();
        }
        
        Thread.sleep(1000L);//
        System.out.println("And... they're off!");
        start.countDown();        
        
        
        finish.await();
        System.out.println("WINNER IS!");
        System.out.println(places.get(0) + " 获得金牌...");
        System.out.println(places.get(1) + " 获得银牌...");
        System.out.println(places.get(2) + " 获得铜牌...");
    }
}

/**
 * Executor(线程池)
 * 使您不必亲自对 Thread对象执行new就能够创建新线程
 */

/**
 * ReentrantLock
 * 
 */

/**
 * CyclicBarrier
 */
class CyclicBarrierDemo
{
	
}

/**
 * BlockingQueue
 * 生产者和消费者模型
 * @author wyy
 * 2016年10月25日
 *
 */
class Producer implements Runnable
{
    private BlockingQueue<String> drop;
    List<String> messages = Arrays.asList(
        "1.Mares eat oats",
        "2.Does eat oats",
        "3.Little lambs eat ivy",
        "4.Wouldn't you eat ivy too?",
        "5.Mares eat oats22",
        "6.Mares eat oats33"
    );
        
    public Producer(BlockingQueue<String> d) { this.drop = d; }
    @Override
    public void run()
    {
        try
        {
        	while(true)
        	{
        		for (String s : messages)
                    drop.put(s);
                drop.put("DONE");
        	}
            
        }
        catch (InterruptedException intEx)
        {
            System.out.println("Interrupted! " + 
                "Last one out, turn out the lights!");
        }
    }    
}

class Consumer implements Runnable
{
    private BlockingQueue<String> drop;
    public Consumer(BlockingQueue<String> d) { this.drop = d; }
    
    @Override
    public void run()
    {
        try
        {
        	while(true)
        	{
        		String msg = null;
                while (!((msg = drop.take()).equals("DONE")))
                    System.out.println(msg);
        	}
        }
        catch (InterruptedException intEx)
        {
            System.out.println("Interrupted! " + 
                "Last one out, turn out the lights!");
        }
    }
}

