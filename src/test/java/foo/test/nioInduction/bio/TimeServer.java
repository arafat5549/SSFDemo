package foo.test.nioInduction.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一、Java原生Socket编程  同步阻塞
 * 使用BIO没有使用NIO方式
 * 1:1  一个Server对应一个客户端请求
 * @author wyy
 *
 */
public class TimeServer 
{
	//%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;
	//%JAVA_HOME%\jre\lib\rt.jar;
	public static void main(String[] args) 
	{
		int port = 8080;
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(port);
			System.out.println("Server Start in port:"+port);
			while(true){
				Socket socket = null;
				socket = server.accept();
				new Thread(new TimerServerHandler(socket)).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
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
