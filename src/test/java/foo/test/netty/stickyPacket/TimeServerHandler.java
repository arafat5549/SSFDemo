package foo.test.netty.stickyPacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class TimeServerHandler extends ChannelInboundHandlerAdapter
{
	private int counter;

    // 用于网络的读写操作
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
       //粘包
//       ByteBuf buf = (ByteBuf)msg;
//       byte[]req = new byte[buf.readableBytes()];
//       buf.readBytes(req);
//       String body = new String(req,"UTF-8").substring(0,
//        		 req.length - System.getProperty("line.separator").length());
    	
    	String body = (String)msg;
        System.out.println("the time server order : " + body+";the counter is :"+ (++counter));

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?
        		new Date(System.currentTimeMillis()).toString():"BAD ORDER";
        currentTime +=System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);

        // 当客户端和服务端建立tcp成功之后，Netty的NIO线程会调用channelActive
        // 发送查询时间的指令给服务端。
        // 调用ChannelHandlerContext的writeAndFlush方法，将请求消息发送给服务端
        // 当服务端应答时，channelRead方法被调用
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        ctx.close();
    }
}
