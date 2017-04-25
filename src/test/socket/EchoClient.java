package test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * echo回声Socket 客户端程序
 * 
 * 我发出一条数据，服务器需要回应
 * 
 * 把Client作为一个Runnable线程
 * 
 * 1.创建Socket对象 需要host和port
 * 2.IO流的处理 （输出流out给服务端发数据） (输入流in接收服务端传过来的数据)
 * 3.关闭流的连接
 */
public class EchoClient implements Runnable{
	
	private int Port = 8080;
    private String HostAddr = "127.0.0.1";
	
	public static void main(String[] args) {
		//1.连接服务器 需要ip地址和端口号
		new Thread(new EchoClient()).start();//一次请求
	}

	@Override
	public void run() {
		 Socket socket = null;
		 BufferedReader in =null;
		 PrintWriter out = null;
		//1.建立-指定对应IP对应端口的Socket-生成新的连接请求
        //客户端生成socket对象 服务端接收这个socket
		try {
			socket = new Socket(HostAddr,Port);
			
			out = new PrintWriter(socket.getOutputStream(),true);
            out.println("哈哈哈哈哈哈From前台");
            System.out.println("Send order 2 server succeed.");
            
            in = new BufferedReader(
            		new InputStreamReader(socket.getInputStream()));
            //接收后台数据
            String resp = in.readLine();
            System.out.println("后台传过来的数据 : " + resp);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
