package foo.concurrent;

import foo.concurrent.annotations.NotThreadSafe;
import foo.concurrent.annotations.ThreadSafe;

/**
 * 两种初始化的线程安全演示
 * 
 * @author wyy
 *
 */
public class InitializaDemo {

}

/**
 * 懒加载LazyInit的方式
 * <p>
 * 可能在getInstance存在条件竞争RaceCondition
 * 是线程不安全的
 * @author wyy
 *
 */
@NotThreadSafe
class LazyInitRace {
    private ExpensiveObject instance = null;
    public ExpensiveObject getInstance() {
        if (instance == null)
            instance = new ExpensiveObject();
        return instance;
    }
}
class ExpensiveObject {}

/**
 * 线程不安全的懒加载机制
 * @author wyy
 *
 */
@NotThreadSafe
class UnsafeLazyInitialization {
    private static Resource resource;
    public static Resource getInstance() {
        if (resource == null)
            resource = new Resource(); // unsafe publication
        return resource;
    }
    static class Resource {}
}

/**
 * 主动加载EagerInit
 * <p>
 * 线程安全
 * @author wyy
 *
 */
@ThreadSafe
class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getResource() {
        return resource;
    }

    static class Resource {}
}



