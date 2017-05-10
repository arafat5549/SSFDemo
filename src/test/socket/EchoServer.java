package test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  echo回声Socket 服务器端程序
 *  
 *  1.ServerSocket监听某个端口 就是创建ServerSocket对象
 *  2.接收客户端传过来的socket对象
 *  3.IO流的处理 （输出流out给服务端发数据） (输入流in接收服务端传过来的数据) 
 *    把IO流处理作为一个线程 Hanlder来做
 *  4.关闭流的连接
 *  
 */
public class EchoServer{

	public static void main(String[] args) 
	{
			int port = 8080;
			ServerSocket server = null;
			try {
				//1.监听端口号 - ServerSocket
				server = new ServerSocket(port);
				System.out.println("Server Start in port:"+port);
				//2.打开Socket连接
				Socket socket = null;
				while (true) //服务器保持连接
				{
					socket = server.accept();//监听
					//3.处理IO流
					new Thread(new BIOServerHandler(socket)).start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				if(server!=null){
					System.out.println("Server force close!");
					try {
						server.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					server = null;
				}
			}
		}
	
	
}

//数据流的处理
class BIOServerHandler implements Runnable{
	Socket socket = null;
	public BIOServerHandler(Socket socket){
		this.socket = socket;
	}
	@Override
	public void run() {
		//3.1读取的IO流
		//3.2写的IO流
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			in  = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
            String currentTime = null;
            String body = null;
            while(true) {
            	//.读取前台发送过来的消息
                body = in.readLine();
                if (body == null) break;
                System.out.println("recvive order : " + body);
                
                currentTime="嘻嘻嘻爱From后台";
                //.给前台发送消息
                out.println(currentTime);
            }
		} catch (IOException e) {
			 if(in!=null){
	                try{
	                    in.close();
	                }
	                catch (IOException el){
	                    el.printStackTrace();
	                }
	            }

	            if(out!=null){
	                out.close();
	                out=null;
	            }

	            if(this.socket != null){
	                try{
	                    this.socket.close();
	                }catch (IOException el){
	                    el.printStackTrace();
	                }
	                this.socket=null;
	            }
	        }
	}
}