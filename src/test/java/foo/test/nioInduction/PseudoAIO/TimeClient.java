package foo.test.nioInduction.PseudoAIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient implements Runnable{
	private int Port = 8080;
    private String HostAddr = "127.0.0.1";

    public TimeClient(String hostAddr,int port){
        this.HostAddr=hostAddr;
        this.Port = port;
    }
    public void run(){
        Socket socket = null;
        BufferedReader in =null;
        PrintWriter out = null;

        try{
            socket = new Socket(HostAddr,Port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY TIME ORDER");
            System.out.println("Send order 2 server succeed.");

            String resp = in.readLine();
            System.out.println("Now is : " + resp);
        }
        catch (Exception e){}
        finally {
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

    public static void main(String[]agrs){
        int port = 8080;
        String hostAddr = "localhost";

        if(agrs!=null && agrs.length==1){
            try {
                port = Integer.valueOf(agrs[0]);
            }
            catch (NumberFormatException ex){}
        }else if(agrs!=null && agrs.length==2){
            try {
                hostAddr=agrs[0];
                port = Integer.valueOf(agrs[1]);
            }
            catch (NumberFormatException ex){}
        }

        for (int i=1;i<=100000;i++) {
        	TimeClient tc = new TimeClient(hostAddr, port);
            Thread tt = new Thread(tc,"线程"+i);
            try {
                tt.sleep(1000);
                tt.start();
            }
            catch (Exception e){}
        }
    }
}
