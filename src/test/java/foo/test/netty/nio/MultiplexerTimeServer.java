package foo.test.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;


public class MultiplexerTimeServer implements Runnable
{

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;  //线程在每次使用volatile变量的时候，都会读取变量修改后的最的值
    
    public MultiplexerTimeServer(int port){
        try {
            
            //1.打开ServerSocketChannel通道，用来监听所有客户端连接，他是所有客户端连接的父管道
            serverSocketChannel = ServerSocketChannel.open();
            //2.绑定socket监听端口，设定为非阻塞模式
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            serverSocketChannel.configureBlocking(false);
            //3.创建Reactor线程，创建多路复用器selector
            selector = Selector.open();
            //4.将channel注册到selector上，并监听Accpet事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The Server start in port : " + port);
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    
	@Override
	public void run() {
		
	}

}
