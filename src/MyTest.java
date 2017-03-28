import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyTest {
   //static关键字有什么作用
	static{
		//静态初始化
		System.out.println("/静态初始化");
	}
	public final void test(){
		System.out.println("/test初始化");
	}
	//编译时产生
	private String url ="123";
	
	private static String url2 = "123";
	
	public static void main(String[] args) {
		System.out.println("Hello World@"); 
		
		ArrayList<String> lists = new ArrayList<String>();
		
		//动态绑定 解耦合#消除类型之间的耦合关系。
		List<String> lists2 = new ArrayList<String>();
		List<String> lists3 = new LinkedList<String>();
		
		lists2.add("dddd");
		lists2.add("aaaa");
		lists2.add("bbb");
		lists2.add("zzzz");
		
		//Arrays
		Collections.sort(lists2);
		//Collections.binarySearch(list, key)
		for (String string : lists2) {
			System.out.println(string);
		}
		
		
		//HashMap怎么做遍历# 键值对 key,value
		HashMap<String,String> maps = new HashMap<String,String>();
		maps.put("1", "wang");
		maps.put("2", "wang2");
		
		maps.put("1", "wang3");
		//无序
		for (String key : maps.keySet()) {
			System.out.println(key +"," +maps.get(key));
			
		}
		//----------------------------------------------------------------------
		//String放在常量池里面
		String str = "hello";//内存有两份对象： str引用 + 字符串常量
		String str2 = new String("hello");//内存有三份对象：str引用 + 字符串常量 +String对象
		
		String s1 = "helloworld";
		String s2 = "hello" +"world";
		
		String s3 =new String("helloworld");
		String s4 =new String("hello") + new String("world");
		
		String s5 = "hello" + new String("world") + "iid";
		
		System.out.println(s1==s2); //引用的地址
		System.out.println(s1.equals(s2));
		
		System.out.println(s3==s4); 
		System.out.println(s3.equals(s4));
		
		System.out.println(s3==s5); 
		System.out.println(s3.equals(s5));
		
		//
		StringBuffer sb = new StringBuffer();
		sb.append("hello");
		sb.append("world");
		sb.append("iid");
		//正则表达式
		
		String p = "11111a";
		String regex ="[0-9]*";
		System.out.println("正则表达式:"+p.matches(regex));
		
		 Pattern pattern = Pattern.compile(regex);
	     Matcher m = pattern.matcher(p);
	     
	     //1.数学  Math
	     
	     //2.日期  计算机怎么处理时间的#long timewills ; 1970
	     //System.currentTimeMillis()
	     //System.nanoTime()
	     
	     //3.正则表达式
	     
	     //4.
	}
}


class SubTest extends MyTest{
//	@Override//重写
//	public void test() {
//		// TODO Auto-generated method stub
//		super.test();
//	}
}