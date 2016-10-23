package foo.test.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimerServerHandler implements Runnable{

	private Socket socket;
	public TimerServerHandler(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
            String currentTime = null;
            String body = null;
            while(true) {
                body = in.readLine();
                if (body == null) break;
                System.out.println("recvive order : " + body);

                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
                        System.currentTimeMillis()).toString() : "BAD ORDER";
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
