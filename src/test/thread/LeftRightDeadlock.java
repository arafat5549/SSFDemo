package test.thread;

/**
 * 左右互搏
 * 死锁
 * 
	多线程产生死锁的四个必要条件： 

   	#互斥条件：一个资源每次只能被一个进程使用。
    #保持和请求条件：一个进程因请求资源而阻塞时，对已获得资源保持不放。
    #不可剥夺调教：进程已获得资源，在未使用完成前，不能被剥夺。
    #循环等待条件：若干进程之间形成一种头尾相接的循环等待资源关系。

  	只要破坏其中任意一个条件，就可以避免死锁，其中最简单的就是破环循环等待条件。按同一顺序访问对象，加载锁，释放锁。其中最简单的方法就是线程都是以同样的顺序加锁和释放锁，也就是破坏了第四个条件。

 * 
 */
public class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                doSomethingElse();
            }
        }
    }

    void doSomething() {
    }

    void doSomethingElse() {
    }
}
