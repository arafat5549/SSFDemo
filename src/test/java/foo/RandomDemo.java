package foo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.rmi.Cardable;

/**
 * 多线程环境下生成随机树<p>
 * 1.原生Random<br>
 * 2.Random数组<br>
 * 3.ThreadLocal<Random><br>
 * 4.ThreadLocalRandom:1.7新特性 DougLea大神新作 你懂得！！！<br>
 * 总结：
 * 任何情况下都不要在多个线程间共享一个java.util.Random实例，而该把它放入ThreadLocal之中。
   Java7在所有情形下都更推荐使用java.util.concurrent.ThreadLocalRandom——它向下兼容已有的代码且运营成本更低。
       对于较低的JDK版本可以自己实现（代码开源）<p>
   
        随机经典应用：洗牌Shuffle<br>
 * @author wyy
 *
 */
public class RandomDemo 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final long COUNT = 10000000l;
	private static final int  THREADS = 6;
	
	public static void main(String[] args) {
		ThreadLocalRandomTest();
		BaeRandomTest();
		RandomArrayTest(1);
		RandomArrayTest(2);
		TL_RandomTest();
	}
	
	
	/**
	 * ThreadLocalRandom 的主要实现细节：<br>
		它使用一个普通的 long 而不是使用 Random 中的 AtomicLong 作为seed。
		你不能自己创建ThreadLocalRandom实例，因为它的构造函数没有设置为public。可以使用它的静态工厂
		ThreadLocalRandom.current()，这个工厂方法调用了内置的ThreadLocal<ThreadLocalRandom>。
		它是CPU缓存感知式的，使用8个 long 虚拟域来填充64位L1高速缓存行。
	 */
	public static void ThreadLocalRandomTest()
	{
		
		final int  threads = THREADS;
		final long cnt = COUNT;
		//1.7版本新加-多线程环境下的随机数
		//ThreadLocalRandom tlr = ThreadLocalRandom.current();
		//logger.info(tlr.nextInt(32)+"");
	    final CountDownLatch latch = new CountDownLatch( threads );
	    for (int i = 0; i < threads; ++i )
	    {
	        final Thread thread = new Thread(new RandomTask(null, i, cnt, latch,"ThreadLocalRandomTest:") {
	            @Override
	            protected Random getRandom() {
	                return ThreadLocalRandom.current();
	            }
	        } );
	        thread.start();
	    }
	   
	}
	/**
	 * 原生Random 线程安全的 多线程下性能很差，阻塞IO
	 */
	public static void BaeRandomTest()
	{
		final int  threads = THREADS;
		final long cnt = COUNT;
		
	    final CountDownLatch latch = new CountDownLatch( threads );
	    final Random r = new Random(100);
	    for ( int i = 0; i < threads; ++i )
	    {
	        final Thread thread = new Thread(new RandomTask(r, i, cnt, latch,"BaeRandomTest:") );
	        thread.start();
	    }
	}
	/**
	 * 随机数数组
	 */
	public static void RandomArrayTest(int padding)
	{
		final int  threads = THREADS;
		final long cnt = COUNT;
		
	    final CountDownLatch latch = new CountDownLatch(threads);
	    final Random[] rnd = new Random[threads * padding];
	    for (int i = 0; i < threads * padding; ++i ) //allocate together
	        rnd[i] = new Random(100);
	    
	    for (int i = 0; i < threads; ++i )
	    {
	        final Thread thread = new Thread(new RandomTask(rnd[ i * padding ], i, cnt, latch,"RandomArrayTest["+padding+"]:") );
	        thread.start();
	    }
	}
	/**
	 * ThreadLocal<Random>方式共享
	 */
	public static void TL_RandomTest()
	{
		final int  threads = THREADS;
		final long cnt = COUNT;
		
	    final CountDownLatch latch = new CountDownLatch( threads );
	    final ThreadLocal<Random> rnd = new ThreadLocal<Random>() {
	        @Override
	        protected Random initialValue() {
	            return new Random(100);
	        }
	    };
	    
	    for ( int i = 0; i < threads; ++i )
	    {
	        final Thread thread = new Thread(new RandomTask( null, i, cnt, latch,"TL_RandomTest:") {
	            @Override
	            protected Random getRandom() {
	                return rnd.get();
	            }
	        } );
	        thread.start();
	    }
	}

	/**
	 * 产生一个随机数多线程Task
	 */
	private static class RandomTask implements Runnable
	{
	    private final Random rnd;
	    protected final int id;
	    private final long cnt;
	    private final CountDownLatch latch;
	    private final String prefix;
	  
	    private RandomTask(Random rnd, int id, long cnt, CountDownLatch latch,String prefix) {
	        this.rnd = rnd;
	        this.id = id;
	        this.cnt = cnt;
	        this.latch = latch;  
	        this.prefix=prefix;
	    }
	  
	    protected Random getRandom()
	    {
	        return rnd;
	    }
		@Override
		public void run() 
		{
	        try {
	            final Random r = getRandom();
	            latch.countDown();
	            latch.await();
	            final long start = System.currentTimeMillis();
	            int sum = 0;
	            for ( long j = 0; j < cnt; ++j )
	            {
	                sum += r.nextInt();
	            }
	            final long time = System.currentTimeMillis() - start;
	            System.out.println( prefix+"Thread #" + id + " Time = " + time / 1000.0 + " sec, sum = " + sum );
	        } catch (InterruptedException e) {
	        }
		}
		
	}
}

//扑克
class Poker{
	private static final String suits[]={"梅花","方块","黑桃","红心"};
	private static final int faces[]={1,2,3,4,5,6,7,8,9,10,11,12,13};
	private Card cards[];
	
	public Poker(){
		cards = new Card[52];
		for(int i=0;i<suits.length;i++){
			for(int j=0;j<faces.length;j++){
				cards[i*faces.length + j] =new Card(suits[i],faces[j]);
				//System.out.println(cards[i*faces.length + j]);
			}
		}
	}
	//洗牌
	public void shuffle(){
		int n = cards.length;
		int count = 0;
		//1.最简单的洗牌
		for (int i = 0; i < n; i++) {
			int index = (int)(Math.random()* n);
			System.out.println(index+","+i);
			Card temp = cards[index];
			cards[index] = cards[i];
			cards[i] = temp;
		}
		//2.简易改进版
//		for (int i = 0; i < n; i++) {
//			int index = (int)(i+Math.random()* (n-i));
//			if(i!=index){
//				System.out.println(index+","+i);
//				count++;
//				Card temp = cards[index];
//				cards[index] = cards[i];
//				cards[i] = temp;
//			}
//		}
		
		
		
		System.out.println("count="+count);
		
//		for (int i = 0; i < cards.length; i++) {
//			System.out.println(i+","+cards[i]);
//		}
	}
	
	public static void main(String[] args) {
		Poker p = new Poker();
		p.shuffle();
	}
	
}
class Card{
	public String suit;
	public int face;
	
	public Card(String suit, int face) {
		super();
		this.suit = suit;
		this.face = face;
	}

	@Override
	public String toString() {
		String nface = String.valueOf(face);
		if (face == 11) {
			nface ="J";
		}
		else if (face == 12) {
			nface ="Q";
		}
		else if (face == 13) {
			nface ="K";
		}
		else if (face == 1) {
			nface ="A";
		}
		return "Card [suit=" + suit + " " +nface + "]";
	}
}
