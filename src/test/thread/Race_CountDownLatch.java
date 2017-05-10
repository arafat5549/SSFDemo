package test.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 马儿赛跑的机制
 * @author wyy
 * 2017年4月24日
 *
 */
public class Race_CountDownLatch {
	private Random rand = new Random();
	private int distance = rand.nextInt(100);
	private ArrayList<String> horses = new ArrayList<String>();
	
	public Race_CountDownLatch(String ...names){
		horses.addAll(Arrays.asList(names));
		//System.out.println(horses.size());
	}
	//比赛
	public void runRace() throws InterruptedException
	{
		final CountDownLatch start  = new CountDownLatch(1);
		final CountDownLatch finish = new CountDownLatch(horses.size());	
		//ExecutorService es = Executors.newFixedThreadPool(10);		
		for(final String horse:horses){
			
			new Thread(new Runnable() {//匿名内部类
				int traveld = 0;//每次随机跑多远
				@Override
				public void run() 
				{
					try {
						System.out.println(horse+"..horse awaits.");
						start.await();
						
						System.out.println(horse+"出发");
						while (traveld < distance) {
							 Thread.sleep(rand.nextInt(3) * 1000);//随机等待多久
							 traveld += rand.nextInt(15);
							 System.out.println(horse+" traveld "+traveld);
						}
						finish.countDown();//一个马跑完 数值减1
						System.out.println(horse+"冲过了终点"); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		// 
		Thread.sleep(1000L);
		start.countDown(); 
		
		System.out.println("--------------------------");
		
		finish.await();
		
		System.out.println("全都跑完啦@"); 
	}
	
	public static void main(String[] args) throws InterruptedException {
		Race_CountDownLatch race0 = new Race_CountDownLatch(
				 "[#1孙悟空]",
		         "[#2马大哈]",
		         "[#3美女]",
		         "[#4闪电]",
		         "[#5科比]",
		         "[#6呆呆]",
		         "[#7詹姆斯]"
		);
		
//		try {
//			race0.runRace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		//等N个线程都执行完后再执行最后的方法
		
		final CountDownLatch start  = new CountDownLatch(6);
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("----");
				start.countDown();//计数器减1 减到0继续执行await后面的代码
			}
		};
		
		
		for (int i = 0; i <6; i++) {
			new Thread(r).start();
		}
		start.await();//阻塞线程
		System.out.println("线程执行完之后执行这句话");
	}
}
