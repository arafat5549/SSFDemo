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
 * 互斥锁Mutex:synchronized就是互斥锁/监视器锁
 * Java内置锁机制synchronized：
 * 重排序reorder:
 * @author wyy
 *
 */
public class ConcurrentDemo 
{
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
