package foo.test.netty;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient  implements Runnable
{
	String host = null;
	int port = 8081;
	public EchoClient(String host, int port){
		this.host = host;
		this.port = port;
	}
	@Override
	public void run() {
		 Socket socket = null;
		 PrintWriter out = null;
		
		 try {
			socket = new Socket(host, port);
			
			out = new PrintWriter(socket.getOutputStream(),true);
            out.println("你好你好你好-----");
            System.out.println("Send order 2 server succeed.");
            
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 finally{
//			 if(in!=null){
//	                try{
//	                    in.close();
//	                }
//	                catch (IOException el){
//	                    el.printStackTrace();
//	                }
//	            }

	            if(out!=null){
	                out.close();
	                out=null;
	            }

	            if(socket != null){
	                try{
	                    socket.close();
	                }catch (IOException el){
	                    el.printStackTrace();
	                }
	                socket=null;
	            }
		 }
	}

	
	public static void main(String[] args) {
		 int port = 8081;
	     String hostAddr = "127.0.0.1";
	     EchoClient tc = new EchoClient(hostAddr, port);
         Thread tt = new Thread(tc,"线程");
         tt.start();
	}
}
