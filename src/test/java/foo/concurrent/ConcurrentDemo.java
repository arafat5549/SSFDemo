package foo.concurrent;

/**
 * Java中的并发Concurrent和多线程MultiThread
 * 1.Thread线程：
 * 2.Process进程：
 * 3.Coroutine协程：
 * 4.
 * 
 * 名词：
 * 竞争条件RaceCondition:
 * 互斥锁Mutex:synchronized：就是互斥锁/监视器锁
 * Java内置锁机制synchronized：
 * 重排序reorder:
 * volatile变量:
 * @author wyy
 *
 */
public class ConcurrentDemo 
{
	/**
	 * 1.多线程并发的历史
	 * #1.[SingleTask单任务系统]:单cpu计算机，一次只能执行一个程序(program)
	 * #2.[MultiTask多任务系统]: 单cpu计算机，一次能执行多个程序(并不是真正的同时需要CPU调度)
	 * #3.[MultiThreading多线程系统]:多CPU计算机，在一个程序program里可以有多个线程Thread，
	 * 多个线程在同一个程序里执行，相当于多个CPU同时处理同一个程序
	 * 
	 * 程序Program看成[主线程]的话:多任务系统相当于每个程序只有一个线程
	 * 引入多线程 为了利用多个CPU的计算能力。
	 * 
	 * 当不同线程同时争夺同一个内存区域，比如一个线程要写数据 另一个线程要读数据就可能出现脏数据。
	 * 
	 * 
	 * 多线程并发的优点：
	 * #1.资源利用更加高效
	 * #2.
	 * 缺点：
	 * #1.程序的设计更加复杂
	 * #2.线程频繁切换会降低效率(当CPU从一个线程调度到另一个时，需要保存本地数据，程序指针等等)
	 * #3.资源消耗增大(每开启一个线程，CPU都要消耗一定的资源来运行它)
	 * 
	 * 多线程并发模型非常类似于分布式系统架构(把每一个架构上的服务器看成一个线程)
	 * 并发模型ConcurrentModel:
	 * 模型#1.Parallel Workers(大部分Java并发包(java.util.concurrent)的实现策略都是)
	 * 
	 *            --> Worker
	 * delegator  --> Worker
	 *            --> Worker
	 * 优点：
	 * 易懂容易理解
	 * 缺点：
	 * -共享状态非常复杂难懂complex
	 * -Job Ordering is Nondeterministic
	 * 
	 * 
	 * persistent data structures (immutable数据类型)
	 * 
	 * 模型#2.Assembly Line流水线   (e.g. Reactive Systems, event driven systems)
	 * 大部分是为了Non-BlockingIO 非阻塞IO
	 * delegator  --> Worker --> Worker --> Worker
	 * 
	 * Each worker only performs a part of the full job. When that part is finished 
	 * the worker forwards the job to the next worker.
	 * 每个工人只完成整个Job的一部分，当完成的时候将Job传给下一个工人。
	 * Each worker is running in its own thread, and shares no state with other workers. 
	 * This is also sometimes referred to as a shared nothing concurrency model.
	 * 每个工人在自己的线程跑，并且不跟其他工人共享状态。也叫作 状态不共享模型
	 */
	
	
	
	
	
	
	
    //1.现代GUI架构 (EventDispatchThread事件派发线程EDT)取代了主线程循环
	//2.
	//编写线程安全的代码，本质上是管理对状态state的访问，特别是共享，可变的状态。
	//一个对象的状态就是他的数据，存储在状态变量中
	
	//同步机制
	//1.synchronized  //同步 ，独占锁-互斥锁
	//2.volatile      //弱同步，编译器运行时会监视这个变量：他是共享的，并且不会和其他内存操作重排序,访问volatile变量不会加锁
	//3.显示锁
	//4.原子变量
	
	//锁不但代表原子性，还代表可见性，对各个操作对象都是内存可见的。锁机制能保证原子性和可见性，volatile只能保证可见性。
	//volatile变量的使用原则：
	//#1.写入变量并不依赖变量的当前值，或者确保只有单一的线程能修改变量的值
	//#2.变量不需要和其他状态变量共同参与不变约束
	//#3.访问变量时，没有其他原因需要加锁
	
	//避免脏数据-修复
	//1.不要跨线程共享变量
	//2.设置变量数据为不可变的
	//3.在任何访问变量的时候使用同步
	
	
	
}
