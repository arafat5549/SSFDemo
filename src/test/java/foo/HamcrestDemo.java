package foo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;

import org.junit.Test;

/**
 * 单元测试断言 推荐度5星<p>
 * 
 * 基本和Junit成双成对
 * Junit4.4整合了Hamcrest
 * @author wyy
 * 2016年10月29日
 *
 */
public class HamcrestDemo 
{
	@Test
	public void  HamcresTest()
	{ 
		int number = 6;
		String text= "Helloxxxx";
		ArrayList<String> array = new ArrayList<String>();
		array.add("World");
		
		assertThat(number, greaterThan(5));
		assertThat(text, startsWith("Hello"));
		assertThat(array, hasItem("World"));
	}
}
