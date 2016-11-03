package foo.test.netty.stickyPacket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 解决TCP粘包/分包问题
 * @author wyy
 *
 */
public class TimeServer 
{
	public void bind(int port)
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		ServerBootstrap b = new ServerBootstrap();
		
		b.group(bossGroup, workerGroup)
		.channel(NioServerSocketChannel.class)
		.option(ChannelOption.SO_BACKLOG, 1024)
		.childHandler(new ChildChannelHandler());
		
		ChannelFuture future = null;
		try {
			//绑定端口,同步等待成功
			future = b.bind(port).sync();
			//等待服务端监听端口关闭
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			//释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
		}
		
		
	}
	
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected  void initChannel(SocketChannel channel)throws Exception{
            //增加 LineBasedFrameDecoder 和StringDecoder编码器
            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            channel.pipeline().addLast(new StringDecoder());
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
