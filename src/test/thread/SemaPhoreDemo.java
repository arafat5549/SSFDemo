package test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量Semaphore=1
 * 控制n个线程访问，N为固定数目
 *
 */
public class SemaPhoreDemo {
    public void runDemo()
    {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //Executors.newFixedThreadPool(10);
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟50个客户端访问
        for (int index = 0; index < 50; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 6000));
                        // 访问完后，释放
                        semp.release();
                        //availablePermits()指的是当前信号灯库中有多少个可以被使用
                        System.out.println(NO+"------relase-----------" + semp.availablePermits()); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }
    
    public static void main(String[] args) {
    	SemaPhoreDemo demo = new SemaPhoreDemo();
		demo.runDemo();
	}
    
}
