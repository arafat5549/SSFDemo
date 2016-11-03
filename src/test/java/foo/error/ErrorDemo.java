package foo.error;

import org.junit.Test;

/**
 * 自己编写各种常见的Error<p>
 * 
 * 1.HeapOutOfMemory<br>
 * 2.Young OutOfMemory<br>
 * 3.MethodArea OutOfMemory<br>
 * 4.ConstantPool OutOfMemory<br>
 * 5.DirectMemory OutOfMemory<br>
 * 6.Stack OutOfMemory Stack OverFlow<br>
 * @author wyy
 * 2016年10月25日
 *
 */
public class ErrorDemo 
{
	//运行会卡死
	//1.java.lang.OutOfMemoryError: Requested array size exceeds VM limit
    @Test
    public void HeapOutOfMemoryTest1(){
//    	int len = Integer.MAX_VALUE;
//    	int largArray[] = new int[len];
//    	System.out.print(len);
    }
    
    //2.java.lang.OutOfMemoryError: Java heap space
    //原因：Heap内存溢出，意味着Young和Old generation的内存不够。
    //解决：调整java启动参数 -Xms -Xmx 来增加Heap内存
    @Test
    public void HeapOutOfMemoryTest_heapSpace()
    {
//    	int len = Integer.MAX_VALUE;
//    	int largArray[] = new int[len - 4];
//    	System.out.print(len);
    }
    //3.java.lang.OutOfMemoryError: unable to create new native thread
    //原因：Stack空间不足以创建额外的线程，要么是创建的线程过多，要么是Stack空间确实小了。
    @Test
    public void HeapOutOfMemoryTest_nativeThread()
    {
    	/*
    	class HoldThread extends Thread 
    	{  
    	    CountDownLatch cdl = new CountDownLatch(1);  
    	    public HoldThread() {  this.setDaemon(true);  }  
    	    public void run() {  
    	        try {  
    	            cdl.await();  
    	        } catch (InterruptedException e) {  }  
    	    }  
    	} 
    	for (int i = 0;; i++) {  
            System.out.println("i = " + i);  
            new Thread(new HoldThread()).start();  
        }  
        */
    }
    //4.java.lang.OutOfMemoryError: PermGen space
    //原因：Permanent Generation空间不足，不能加载额外的类。
    //解决：调整-XX:PermSize= -XX:MaxPermSize= 两个参数来增大PermGen内存。一般情况下，这两个参数不要手动设置，只要设置-Xmx足够大即可，JVM会自行选择合适的PermGen大小。
    @Test
    public void OutOfMemoryError_PermGen(){
    	//PermGen space即永久代，是非堆内存的一个区域。主要存放的数据是类结构及调用了intern()的字符串。
//    	List<Class<?>> classes = new ArrayList<Class<?>>();
//    	while(true){
//    	    MyClassLoader cl = new MyClassLoader();
//    	    try{
//    	        classes.add(cl.loadClass("Dummy"));
//    	    }catch (ClassNotFoundException e) {
//    	        e.printStackTrace();
//    	    }
//    	}
    }
    //6.java.lang.StackOverflowError
    //原因：这也内存溢出错误的一种，即线程栈的溢出，要么是方法调用层次过多（比如存在无限递归调用），要么是线程栈太小。
    //解决：优化程序设计，减少方法调用层次；调整-Xss参数增加线程栈大小。
    
    //9.java.lang.OutOfMemoryError: Direct buffer memory
    //即从Direct Memory分配内存失败，Direct Buffer对象不是分配在堆上，是在Direct Memory分配，且不被GC直接管理的空间（但Direct Buffer的Java对象是归GC管理
    //的，只要GC回收了它的Java对象，操作系统才会释放Direct Buffer所申请的空间）。通过-XX:MaxDirectMemorySize=可以设置Direct内存的大小。
    @Test
    public void OutOfMemoryError_DirectMem(){
    	//List<ByteBuffer> list = new ArrayList<ByteBuffer>();
    	// while(true) list.add(ByteBuffer.allocateDirect(10000000));
    }
  
}


