package foo.lesson;

import java.util.regex.Pattern;

import javassist.expr.NewArray;

import org.junit.Test;

/**
 * 经典面试题解析<p>
 * 1.String,StringBuffer,StringBuilder的区别<br>
 * 2.String的split操作的优化<br>
 * 
 * 
 * 3.
 * 
 * @author wyy
 * 2016年11月23日
 *
 */
public class LessonInterView 
{
	private static Pattern pattern = Pattern.compile("$\\#");
	//1.
	@Test
	public void StringDemo(){
		String s1 = "hello";//常量池里
		String s2 = "world";
		String s3 = new String("hello");//堆里
		//#引用对象s3在stack
		//#"hello"常量池
		//new String()堆里
		
		System.out.println(s1 == s3); 
		System.out.println(s1.equals(s3)); 
		String s4 = s1 + s2;
		String s5 = s3 + s2;
		System.out.println(s4==s5); 
		System.out.println(s4.equals(s5)); 
		
		String s6 = "helloworld";
		System.out.println(s4 == s6);
		
		String demo = "1"+","+new String("2"); //1,2,4
		//字符串产生大量的临时string数据
		
		//大量字符串拼接
		StringBuffer sb = new StringBuffer();   //线程安全    -
		StringBuilder sbt = new StringBuilder();//线程不安全-
		sb.append("1");
		sb.append(",");
		sb.append("2");
		
//		//#2equals
//		//1.HASHCODE相同
//		//2.值相同
//		Object object = new Object();
//		object.hashCode();
//		s3.hashCode();
//		Apple apple   = new Apple("hello");
//		Orange orange = new Orange("hello"); 
//		System.out.println(apple.hashCode()+","+orange.hashCode());
//		System.out.println(apple.equals(orange));
		
		//split应用到正则表达式 大量调用效率很低
		String baesp = "araf#arrr#4444";
		String sp ="";
		long begin = System.currentTimeMillis();
		for(int i=0;i<1000000;i++){
			pattern.split(sp);//调用正则表达式 ， 需要compile
		}
		long end = System.currentTimeMillis();
		System.out.println((end-begin)+"ms"); 
		
		sp = "";
		begin = System.currentTimeMillis();
		for(int i=0;i<1000000;i++){
			sp.split("$\\#");
		}
		end = System.currentTimeMillis();
		System.out.println((end-begin)+"ms"); 
	}
	
	
}

class Fruit{
	String string;
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof Fruit)
		{
			return string.equals(((Fruit)obj).string);
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		int hash = string.hashCode();
		return hash;
	}
}
class Apple extends Fruit{
	
	public Apple(String s){string =s;}
	
	@Override
	public int hashCode() {
		//System.out.println("---");
		return 1;
	}
	
}
class Orange extends Fruit{
	public Orange(String s){string =s;}
	
}
