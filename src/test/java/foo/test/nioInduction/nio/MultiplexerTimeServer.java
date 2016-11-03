package foo.test.nioInduction.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;


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
            //3.创建Reactor线程，创建多路复用器selector (Reactive/EventDrivenModel),epoll
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
		//5.selector轮询channel
		try
		{
		selector.select(1000);
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey>it = selectedKeys.iterator();
        SelectionKey key = null;
        while (it.hasNext()){
            key = it.next();
            it.remove();
            try {
                handleInput(key);
            }catch (Exception e){
                if(key!=null)
                    key.cancel();
                if(key.channel()!=null){
                    key.channel().close();
                }
            }
        }
		} catch (Throwable T){
            T.printStackTrace();
        }
		
        // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动注册并关闭，所以不需要重新释放资源
        if(selector!=null){
            try {
                selector.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
	}
	//
    private void handleInput(SelectionKey key)throws IOException{
        if(key.isValid()){
            //6.监听到客户端接入，处理新接入的请求消息
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                //7.连接客户端socket，设为非阻塞
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //8.将新接入的客户端socket注册到selector上监听读写
                sc.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){
            	//9.异步读取数据到缓冲区
                SocketChannel sc = (SocketChannel)key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                //10.对bytebuf编码解码，如果有半包消息 重新读取
                if(readBytes>0){
                    readBuffer.flip();
                    byte[] bytes=new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("recvive order : " + body);

                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(
                            System.currentTimeMillis()).toString()
                            :"BAD ORDER";
                     //11.将读好的数据发送给客户端
                    doWrite(sc,currentTime);
                }
                else if(readBytes<0){
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                }
                else ; // 读到0字节，忽略
            }
        }
    }
    //
    private void doWrite(SocketChannel channel,String response)throws IOException{
        if(response!=null&&response.trim().length()>0){
            byte[]bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }

}
