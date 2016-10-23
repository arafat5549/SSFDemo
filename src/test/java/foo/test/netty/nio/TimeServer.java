package foo.test.netty.nio;

/**
 * 三、Java原生NIO方式  同步非阻塞
 * 使用selector轮询功能，轮询注册在其上面的channel，如果有TCP连接，读写事件
 * 则把该channel设为就绪，最后利用selectKey把所有就绪的channel遍历出来，执行后续IO操作
 * 
 * @author wyy
 *
 */
public class TimeServer 
{
	
}
