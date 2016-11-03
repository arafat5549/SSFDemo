package foo.test.nioInduction.aio;

import java.io.IOException;

/**
 * 四、AIO Java原生AIO方式         异步非阻塞IO
 * 
 * @author wyy
 * 2016年10月24日
 *
 */
public class TimeServer 
{
    public static void main(String[]args)throws IOException{
        int port = 8081;
        if(args!=null && args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            }
            catch (NumberFormatException ex){}
        }

        AsyneTimeServerHandler timeServer=new AsyneTimeServerHandler(port);
        new Thread(timeServer,"xxxx").start();
    }
}
