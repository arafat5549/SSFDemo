package foo.lesson;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * <b>JAVA网络编程</b><p>
 * 
 * 计算机架构分层：<br>
 * TCP/IP:<br>
 * TCP协议:<br>
 * UDP协议:<br>
 * HTTP协议:<br>
 * Socket编程(4种方式):<br>
 * #BIO Java原生Socket编程  同步阻塞IO 一(Server)对一(请求)<br>
 * #PIO 伪NIO利用线程池           同步阻塞IO M(Server):N(请求) <br>
 * [可选]#NIO(NewIO - Non-BlcokingIO) 同步非阻塞IO（异步）<br>
 * [可选]#AIO 异步非阻塞IO<br>
 * [可选](Netty)<br>
 * 
 * @author wyy
 * 2016年10月26日
 *
 */
public class Lesson20161026 {
    /**
     * 一、计算机分层架构   从低到高
	//1.物理层 - 机械,电子原始比特流传输
	//2.数据链路层 - 物理寻址，将比特流转化为逻辑传输线路
	
	//3.网络层[IP协议]  - 控制子网运行，逻辑寻址，路由选择
	//4.传输层[TCP/UDP协议]  - 接收上一层数据（必要时进行分割），将这些数据交给网络层，
			//保证这些数据有效到达对端
	
	//5.会话层 - 不同机器用户建立会话
	//6.表示层 - 信息语义解析，如加密解密，压缩解压缩等
	//7.应用层 - 各自应用层协议（HTTP,FTP,SMTP,POP3）{遵守TCP协议}
	 * 
	 * TCP/IP代表你能够进行网络通信，应用层协议HTTP代表的是你传输的数据类型
	 * 
	 * Q：0.什么是TCP/IP协议？
	 * TCP/IP协议提供了网络通信的功能
	 * TCP/IP协议并不单指（TCP和IP这两个协议，也包含UDP协议），包含一些协议簇
	 * 由这些协议共同提供了一系列网络通信功能。
	 * IP网路层 -  TCP/UDP传输层 - HTTP[超文本传输协议]应用层
	 * Q：1.TCP协议和UDP协议的区别？
	 * TCP协议(电话)：面向连接,数据传输前先建立通信连接（利用三次握手），极大保证数据能够正确传输
	 * UDP协议(短信)：面向无接连，在数据传输前不需要建立连接(通信报文)
	 * Q：2.什么是Socket？
	 * #1.套接字,方便调用TCP/IP协议，他本是并不是协议，他是一套调用接口API
	 * #.简单易用方便
	 * #2
	 *  - 服务端:1.服务器监听new ServerSockt
	 *  -       2.客户端请求new Socket
	 *          3.连接确认  服务端ServerSockt.accept()
	 *          4.双方都就绪就可以网络通信
	 * Q：3.什么是HTTP协议？
	 * #超文本传输协议,传输各种超文本，在应用层
	 * #为什么他用的最多?
	 * #优点：简单易用，灵活-可以自定义很多文件格式MIME,短连接，
	 * #TCP/IP协议提供了网络通信的功能，HTTP协议确认了你传输数据的类型
	 * HTTP的两种请求方式：GET-POST
	 * GET：
	 * 优点：快，简单，请求可以被缓存
	 * 缺点：明文传输，内容不会放到表单里面(安全性较差)，数据限定大小，
	 * POST：
	 * 优点：【安全性相对更高(内容封装到表单里面)，适合表单传输，】，传输数据大小比较大
	 * 缺点：不可以被缓存，效率稍低
	 * HTTP属于短连接！长连接：保持跟服务器的连接 ;短连接：一次请求一次连接
	 * HTTP也属于TCP协议
	 * 
	 * 常用的HTTP服务器： ApacheHTTP, Nginx
	 * 
	 * HTTP1.1/HTTP1.0:
	 * 
	 * HTTP协议的大概的格式
	 * 请求头：
	 * GET HTTP1.1
	 * user-agent：浏览器
	 * encoding：编码
	 * 响应头：
	 * 
	 * HTTP协议常见的返回码：
	 * 200:请求成功
	 * 404:请求失败
	 * 500:找不到资源
	*/
	
	/**
	 * 二、Socket(套接字) 对网络通信过程端点的抽象，调用接口API
	 * Socket本身不是协议而是API，他的出现只是为了更方便调用TCP/IP协议（包含了UDP）
	   #连接过程:1.服务器监听某个端口2.客户端请求3.连接确认  
	   
	   #【连接确认 】建立通信连接(三次握手)：syn请求包 - ack确认就绪状态包
	   #1客户端发送syn包，等待状态，等待服务器确认  new Socket
	   #2服务器收到syn包，给服务端发syn+ack包，服务端进入就绪状态 accpet -->
	   #3客户端收到服务端发来的syn+ack包，向服务器发送ack确认包,客户端进入就绪状态，双方进入连接状态establsih
	   #断开连接(四次握手)：
	   
	   TCP/IP协议只负责通信传输，至于通信数据的内容需要由其他的应用层协议(HTTP)确认
	   Socket是发动机，提供了网络通信的能力。
	   HTTP协议是轿车，提供了封装和显示数据的能力。
	 */
	
	/**
	 * NIO
	 * #Worker ->IO操作等到 -> Worker
	 *   -  Worker[channel](IO操作)
	 * # -  Worker[channel].....
	 *   -  Worker[channel]....
	 */
	
	public static void main(String[] args) 
	{
		int port = 8080;
		String HostAddr = "127.0.0.1";
		
		new BIOServer(port).start();
		
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		BIOClient tc = new BIOClient(HostAddr, port);
        Thread tt = new Thread(tc,"线程"+1);
        tt.start();
	}
}

class BIOServer extends Thread
{
	private int port = 8080;
	public BIOServer(int port){
		this.port = port;
	}
	public void run(){
		ServerSocket server = null;
		Socket socket = null;
		try {
			//1.指定端口户-监听端口号看有没人人连接
			server = new ServerSocket(port);
			System.out.println("Server start in port:"+port);
			//2.确认连接
			socket = server.accept();
			//3.处理二进制流
			new Thread(new ServerHandler(socket)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	//处理二进制流
	class ServerHandler implements Runnable
	{
		private Socket socket;
		public ServerHandler(Socket socket){
			this.socket = socket;
		}
		@Override
		public void run() {
			BufferedReader in = null;
			PrintWriter out = null;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String string = in.readLine();
				System.out.println("[Server:]"+string);
				out = new PrintWriter(socket.getOutputStream(),true);
				out.println("后台消息22222");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
//
class BIOClient implements Runnable
{
	//1.客户端需要端口号和服务器IP地址
	private int port = 8080;
	private String hostAddr = "127.0.0.1";
	
	public BIOClient(String hostAddr,int port){
		this.port = port;
		this.hostAddr = hostAddr;
	}
	@Override
	public void run() 
	{
	      Socket socket = null;
	      BufferedReader in =null;
	      PrintWriter out = null;
	      try{
	          socket = new Socket(hostAddr,port);
	          in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	          out = new PrintWriter(socket.getOutputStream(),true);
	          out.println("前台消息11111");
	          System.out.println("[Client]:Send order 2 server succeed.");
	          String resp = in.readLine();
	          System.out.println("Now is : " + resp);
	      }
	      catch(Exception e){}
	}
	
}


//class BIOServer extends Thread
//{
//	private int port = 8080;
//	//1.指定端口号
//	public BIOServer(int port) {
//		this.port = port;
//	}
//	@Override
//	public void run()
//	{
//		ServerSocket server = null;
//		try {
//			server = new ServerSocket(port);
//			System.out.println("Server Start in port:"+port);
//			Socket socket = null;
//			socket = server.accept();
//			//new Thread(new ServerHandler(socket)).start();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			if(server!=null){
//				System.out.println("Server force close!");
//				try {
//					server.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				server = null;
//			}
//		}
//	}
//	
//	
//	class ServerHandler implements Runnable{
//
//		private Socket socket;
//		public ServerHandler(Socket socket)
//		{
//			this.socket = socket;
//		}
//		
//		@Override
//		public void run() {
//			BufferedReader in = null;
//			PrintWriter out = null;
//			
//			try {
//				in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//				out = new PrintWriter(socket.getOutputStream(),true);
//	            String currentTime = null;
//	            String body = null;
//	            while(true) {
//	                body = in.readLine();
//	                if (body == null) break;
//	                System.out.println("recvive order : " + body);
//
//	                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
//	                        System.currentTimeMillis()).toString() : "BAD ORDER";
//	                out.println(currentTime);
//	            }
//			} catch (IOException e) {
//				 if(in!=null){
//		                try{
//		                    in.close();
//		                }
//		                catch (IOException el){
//		                    el.printStackTrace();
//		                }
//		            }
//
//		            if(out!=null){
//		                out.close();
//		                out=null;
//		            }
//
//		            if(this.socket != null){
//		                try{
//		                    this.socket.close();
//		                }catch (IOException el){
//		                    el.printStackTrace();
//		                }
//		                this.socket=null;
//		            }
//		        }
//		}
//
//	}
//}
////
//class BIOClient implements Runnable{
//	private int Port = 8080;
//    private String HostAddr = "127.0.0.1";
//
//    public BIOClient(String hostAddr,int port){
//        this.HostAddr=hostAddr;
//        this.Port = port;
//    }
//    public void run(){
//        Socket socket = null;
//        BufferedReader in =null;
//        PrintWriter out = null;
//
//        try{
//            socket = new Socket(HostAddr,Port);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            out = new PrintWriter(socket.getOutputStream(),true);
//            out.println("QUERY TIME ORDER");
//            System.out.println("Send order 2 server succeed.");
//
//            String resp = in.readLine();
//            System.out.println("Now is : " + resp);
//        }
//        catch (Exception e){}
//        finally {
//            if(in!=null){
//                try{
//                    in.close();
//                }
//                catch (IOException el){
//                    el.printStackTrace();
//                }
//            }
//
//            if(out!=null){
//                out.close();
//                out=null;
//            }
//
//            if(socket != null){
//                try{
//                    socket.close();
//                }catch (IOException el){
//                    el.printStackTrace();
//                }
//                socket=null;
//            }
//        }
//    }
//}
