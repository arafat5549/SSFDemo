package foo.test.nioInduction.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyneTimeServerHandler>{
    @Override
    public  void completed(AsynchronousSocketChannel result,AsyneTimeServerHandler attachment){
        attachment.asynchronousServerSocketChannel.accept(attachment,this);

        ByteBuffer buffer =ByteBuffer.allocate(1024);
        result.read(buffer,buffer,new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc,AsyneTimeServerHandler attachment){
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}
