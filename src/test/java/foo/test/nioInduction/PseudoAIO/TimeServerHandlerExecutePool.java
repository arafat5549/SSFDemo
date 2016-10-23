package foo.test.nioInduction.PseudoAIO;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {
	// Thread Pool
    private ExecutorService executor;

    public TimeServerHandlerExecutePool(int maxPoolSize,int queueSize){
        executor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(), /* //获取当前系统的CPU 数目 */
                maxPoolSize,
                120L,             // 心跳时间频率
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void exceute(Runnable task){
        executor.execute(task);
    }
}
