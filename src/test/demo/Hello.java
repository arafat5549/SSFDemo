package test.demo;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;

class B extends A{
	 
    static {
        System.out.print("a");
    }
 
    public B() {
        System.out.print("b");
    }
}

class A {
	 
    static {
        System.out.print("1");
    }
 
    public A() {
        System.out.print("2");
    }
}
 

 
public class Hello {
 
	static void Test1(){
		 A ab = new B();
	        ab = new B();
	        ab = new A(); //1a2b2b2
	}
	
	static void assertTest(){
		int a = 1;
		assert(a > 0); 
		System.out.println("a0");
		a = 2;
		assert(a > 2); 
		System.out.println("a1");
	}
	static void Test2(){
    	//简单的说，如果整型字面量的值在-128到127之间，那么不会new新的Integer对象，
    	//而是直接引用常量池中的Integer对象，所以上面的面试题中f1==f2的结果是true，而f3==f4的结果是false。
    	
//        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
//        //Integer.valueOf(i)
//        System.out.println(f1 == f2);
//        System.out.println(f3 == f4);
    	
//        Integer a = new Integer(3);
//        Integer b = 3;                  
//        int c = 3;
//        System.out.println(a == b);     // false 两个引用没有引用同一对象
//        System.out.println(a == c);     // true a自动拆箱成int类型再和c比较
	}
	
    public static void main(String[] args) {
    	//怎么检查一个字符串只包含数字？ 并把其中的数字抽取出来
    	String str = "1121esadsdaasdasdd4234234234234s";
    	String ss = CharMatcher.DIGIT.retainFrom(str);
    	
    	String s2 = CharMatcher.DIGIT.removeFrom(str);
    	
    	System.out.println(ss);
    	System.out.println(s2);
    	
    	
    	Integer arr[] = new Integer[5];
    	for (int i = 0; i < arr.length; i++) {
    		arr[i] = new Integer(i);
		}
    	System.out.println(Joiner.on(",").join(arr));
    	
    	
    	//0,0,0,0,0
    	//assertTest();
    }
 
}
