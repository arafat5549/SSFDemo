package foo.test.netty.nettyNIO;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 使用Netty开发的TimeServer
 * @author wyy
 *
 */
public class TimeServer 
{
	public void bind(int port){
		/* 配置服务端的NIO线程组 */
        // NioEventLoopGroup类 是个线程组，包含一组NIO线程，用于网络事件的处理
        // （实际上它就是Reactor线程组）。
        // 创建的2个线程组，1个是服务端接收客户端的连接，另一个是进行SocketChannel的
        // 网络读写
		EventLoopGroup bossGroup   = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		 // ServerBootstrap 类，是启动NIO服务器的辅助启动类
		try {
			ServerBootstrap b =new ServerBootstrap();
			b.group(bossGroup, workerGroup)        //1.启动线程服务组
			.channel(NioServerSocketChannel.class) //2.开启SocketChannel
			.option(ChannelOption.SO_BACKLOG, 1024)//3.配置TCP参数
			.childHandler(new ChildChannelHandler());//4.设置读取事件处理器   Reactor模式里的Handler
			//绑定端口,同步等待成功
			ChannelFuture future = b.bind(port).sync();
			//等待服务端监听端口关闭
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
		}
	}
	//IO事件的处理类
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected  void initChannel(SocketChannel channel)throws Exception{
        	channel.pipeline().addLast(new TimeServerHandler());
        }
    }
    public static void main(String[]args)throws Exception{
        int port = 8080;
        if(args!=null && args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            }
            catch (NumberFormatException ex){}
        }
        new TimeServer().bind(port);
    }
}
