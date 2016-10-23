package foo.test.nioInduction.PseudoAIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 二、伪NIO 同步阻塞利用线程池
 * 利用线程池实现M：N（M可以大于N）
 * @author wyy
 *
 */
public class TimeServer 
{
	public static void main(String[] args) {
		int port = 8080;
        if(args!=null && args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            }
            catch (NumberFormatException ex){}
        }

        ServerSocket server = null;
        try{
            server=new ServerSocket(port);
            System.out.println("The Server start in port : " + port);
            Socket socket = null;
            // 创建 IO 线程池
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,10000);
            while (true){
                socket=server.accept();
                singleExecutor.exceute(new TimeServerHandler(socket));
            }
        }
        catch(Exception e){
        
        }
        finally {
            if(server!=null){
                System.out.println("The Server close...");
                try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
                server=null;
            }
        }
	}
}
