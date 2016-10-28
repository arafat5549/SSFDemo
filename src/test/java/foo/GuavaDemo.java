package foo;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.compose;
import static com.google.common.base.Predicates.in;
import static com.google.common.base.Predicates.not;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
/**
 * Guava工程包含了若干被Google的 Java项目广泛依赖 的核心库
 * 跟ApacheCommons包是很好的补充，谷歌的东西你懂得！<p>
 * 1.Google Collections 谷歌集合包 推荐度5星 <br>
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
		
	}
	
	
	@Test
	public void LangTest(){
		String string = CharMatcher.DIGIT.retainFrom("some text89983 and more");
		System.out.println(string); 
//		String ZEROES = "0\u0660\u06f0\u07c0\u0966\u09e6\u0a66\u0ae6\u0b66\u0be6"
//			      + "\u0c66\u0ce6\u0d66\u0e50\u0ed0\u0f20\u1040\u1090\u17e0\u1810\u1946\u19d0\u1b50\u1bb0"
//			      + "\u1c40\u1c50\ua620\ua8d0\ua900\uaa50\uff10";
//		System.out.println(ZEROES);
		
		int x = CharMatcher.DIGIT.countIn("some text89983 and more");
		System.out.println(x);
		
		String str = "test1, , test2, test3";
		Iterable<String> strArr = Splitter.on(',')
		  .trimResults()
		  .omitEmptyStrings()
		  .split(str);
		System.out.println(strArr);
		
		String str2 = "key1: 1; key2: 2  ; key3: 3";
		Map<String, String> m = Splitter.on(';')
		  .trimResults()
		  .withKeyValueSeparator(":")
		  .split(str2);
		System.out.println(m);
		
		
		int[] array = { 1, 2, 3, 4, 5 };
		int a= 4;
		boolean contains = Ints.contains(array, a);
		System.out.println(contains);
	}
	
	@Test
	public void CollectionTest(){
		//1.
		List<String> list1 = Lists.newArrayList("1", "2", "3");
		List<Double> list2 = Lists.transform(list1, new Function<String, Double>() {
		   public Double apply(String from) {
		      return Double.parseDouble(from);
		   }
		});
		System.out.println(Joiner.on(" | ").join(list2));
		
		//2.
		List<String> list = Lists.newArrayList("A100", "B100", null, "B200");
		Iterable<String> filtered = Iterables.filter(list, new Predicate<String>() {
		   public boolean apply(String input) {
		      return input == null || input.startsWith("B");
		   }
		});
		System.out.println(Joiner.on("; ").useForNull("B000").join(filtered));
		//3.
		List<String> list1_1 = Lists.newArrayList("1", "2", "3");
		List<String> list2_1 = Lists.newArrayList("1", "4", "5");
		List<String> list3_1 = Lists.newArrayList("1", "4", "6");

		boolean result = and(not( in(list1_1) ), in(list2_1), in(list3_1)).apply("1");
		System.out.println(result);  
		
		//4.
		List<String> list1_2 = Lists.newArrayList("A1", "A2", "A3");
		boolean result2 = compose(in(list1_2), new Function<String, String>() {
		   public String apply(String from) {
		      return "A" + from;
		   }
		}).apply("1");
		System.out.println(result2);  // true
		
		//Map
		BiMap<Integer,String> biMap = HashBiMap.create();
		biMap.put(Integer.valueOf(5), "Five");
		biMap.put(Integer.valueOf(1), "One");
		biMap.put(Integer.valueOf(9), "Nine");
		biMap.put(Integer.valueOf(5), "Another Five");
		biMap.put(Integer.valueOf(55), "Five");
		System.out.println(biMap);
		System.out.println(biMap.inverse());
	}
	//函数式编程
	@Test
	public void FunctionalTest(){
//		Map usdPriceMap = Maps.transformValues(eurPriceMap, new Function() {
//		    double eurToUsd = 1.4888;
//		    public Double apply(final Double from) {
//		        return from * eurToUsd;
//		    }
//		});
	}
}
