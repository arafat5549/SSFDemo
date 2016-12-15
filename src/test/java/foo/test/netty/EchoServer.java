package foo.test.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


//实现多线程有几种方式：
//1.继承Thread
//2.实现Runnable


public class EchoServer {
   
	public static void main(String[] args) {
		String ip = "127.0.0.1";
		int port = 8081;
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(port);//1.服务器建立监听端口
			System.out.println("Server Start in port:"+port);
			Socket socket = server.accept();//2.建立Socket连接
			System.out.println("--------");
			//3.处理数据流 - 
			//解析HTTP协议  USER-AGENT:  PARAM:
			new Thread(new TimerServerHandler(socket)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


class TimerServerHandler implements Runnable{
	Socket socket =null;
	public TimerServerHandler(Socket socket){
		this.socket = socket;
	}
	//
	@Override
	public void run() {
		BufferedReader in = null;  //客户端给我发的信息
		//PrintWriter out = null;    //我给客户端发的信息
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 String body = null;
			//char buf[]=new char[1024];
			//StringBuffer sbBuffer = new StringBuffer();
			//while(in.read(buf)!=-1){
			//	sbBuffer.append(buf);
			//}
			 while(true) {
	                body = in.readLine();
	                if (body == null) break;
	                System.out.println("recvive order111 : " + body);
	            }
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
