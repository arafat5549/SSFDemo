package foo.concurrent;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import foo.concurrent.annotations.GuardedBy;
import foo.concurrent.annotations.NotThreadSafe;
import foo.concurrent.annotations.ThreadSafe;

/**
 * 因式分解
 * <p>
 * 有状态Servlet如何实现线程安全
 * @author wyy
 *
 */
public class FactorizerDemo 
{
}

/**
 * 多个Servlet访问次数相加
 * <p>
 * 如何实现线程安全
 * 使用原子对象
 * @author wyy
 *
 */
@ThreadSafe
@SuppressWarnings("serial")
class CountingFactorizer extends GenericServlet implements Servlet 
{
	private final AtomicLong count = new AtomicLong(0);
    public long getCount() {
        return count.get();
    }
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        //count++;//NotThreadSafe
        encodeIntoResponse(resp, factors);
    }
    
    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {}
    BigInteger extractFromRequest(ServletRequest req) {
        return null;
    }
    BigInteger[] factor(BigInteger i) {
        return null;
    }
}
/**
 * 非线程安全的
 * <p>
 * 原子引用AtomicReference是线程安全，但UnSafeCachedFactorizer存在竞争条件
 * @author wyy
 *
 */
@SuppressWarnings("serial")
@NotThreadSafe
class UnSafeCachedFactorizer extends GenericServlet implements Servlet
{
	private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();
	private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<BigInteger[]>();
	
	@Override
	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
		BigInteger i = extractFromRequest(req);
		if(i.equals(lastNumber.get())){
			 encodeIntoResponse(resp, lastFactors.get());
		}
		else{
			BigInteger[] factors = factor(i);
			lastNumber.set(i);
			lastFactors.set(factors);
			encodeIntoResponse(resp, factors);
		}
	}
	
    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {}
    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }
    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}


/**
 * 我们的Servlet需要缓存最新的计算结果
 * 以应对连续两个新的客户端请求相同的数据进行分解，如何实现？
 * <p>
 * 锁机制
 * @author wyy
 *
 */
@ThreadSafe
@SuppressWarnings("serial")
class CachedFactorizer extends GenericServlet implements Servlet {
    @GuardedBy("this")
    private BigInteger lastNumber;
    @GuardedBy("this")
    private BigInteger[] lastFactors;
    @GuardedBy("this")
    private long hits;
    @GuardedBy("this")
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    public /*synchronized 极度影响性能*/ void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {}

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}

