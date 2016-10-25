package foo;

import java.util.List;
import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Guava工程包含了若干被Google的 Java项目广泛依赖 的核心库
 * 跟ApacheCommons包是很好的补充，谷歌的东西你懂得！
 * @author wyy
 *
 */
public class GuavaDemo 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void GuavaTest(){
	    //Java                    Goslin
		//编译器 javac-Scale -      Martin Odersky
		//Collection和Concurrent   DougLea
		//<<EffectiveJava>>       JoshBlotch
		
		//BigInteger//大数字
		String str = "100000000000000";
		BigInteger bigInteger =new BigInteger(str);
		//int[]mag; //[1,0,0,0,0,0...]
		//1.用字符串构建一个大数字
		//2.signal正负值
		//3.验证字符串格式,全数字
		//4.转化成int数组
		//5.利用数组来进行加减乘除运算
		
		//final int size;
		if("xxx".equals(str)){
			
		}
		if("xxx".equals(str) && str!=null){
			
		}
		
		List<Integer> list = new ArrayList<Integer>();
		
		//Collections
		//ConcurrentHashMap Map<A, B> b = new ConcurrentHashMap Map<A, B>
	}
}
