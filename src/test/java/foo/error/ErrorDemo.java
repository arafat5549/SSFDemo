package foo.error;

import org.junit.Test;

/**
 * 自己编写各种常见的Error<p>
 * 1.HeapOutOfMemory<br>
 * 2.Young OutOfMemory<br>
 * 3.MethodArea OutOfMemory<br>
 * 4.ConstantPool OutOfMemory<br>
 * 5.DirectMemory OutOfMemory<br>
 * 6.Stack OutOfMemory Stack OverFlow<br>
 * @author wyy
 * 2016年10月25日
 *
 */
public class ErrorDemo 
{
    @Test
    public void HeapOutOfMemoryTest(){
    	int len = Integer.MAX_VALUE;
    	int largArray[] = new int[len];
    	System.out.print(len);
    }
}
