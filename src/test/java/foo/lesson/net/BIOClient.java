package foo.lesson.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO(BlockingIO)客户端
 * @author wyy
 * 2016年10月26日
 *
 */
public class BIOClient implements Runnable
{
    public static void main(String[]agrs){
        int port = 8080;
        String hostAddr = "127.0.0.1";
       //new Thread(new BIOClient(hostAddr, port),"线程").start();
        //使用线程池
        ExecutorService es = Executors.newFixedThreadPool(1);
        for (int i=1;i<=10000;i++) {
        	
        	BIOClient tc = new BIOClient(hostAddr, port,i);
        	es.execute(tc);
//            Thread tt = new Thread(tc,"线程"+i);
//            try {
//            	Thread.sleep(1000l);
//                tt.start();
//            }
//            catch (Exception e){}
        }
    }
	
    //========================================================
	private int Port = 8080;
    private String HostAddr = "127.0.0.1";
    private int index;

    public BIOClient(String hostAddr,int port,int index){
        this.HostAddr=hostAddr;
        this.Port = port;
        this.index = index;
    }
    @Override
    public void run(){
        Socket socket = null;
        BufferedReader in =null;
        PrintWriter out = null;

        try{
        	//1.建立-指定对应IP对应端口的Socket-生成新的连接请求
            socket = new Socket(HostAddr,Port);
            if(index % 2 ==0){
            	Thread.sleep(5000l);
            }
            //2.IO流操作（Socket）
            in = new BufferedReader(
            		new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("哈哈哈哈哈哈From前台"+index);
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
}
