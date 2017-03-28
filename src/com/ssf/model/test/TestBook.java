package com.ssf.model.test;

import com.ssf.model.Product;

//访问修饰符modifier
//public    整个工程内都能访问   
//friendly  在当前包内能访问
//protected 子类能访问
//private   当前类能访问

//被static修饰后有什么影响
//1.修饰变量  
//1#内存中的位置不同，静态变量位于静态方法区，只有一份                  调用方式  Class.x
   //成员变量位于堆内存，每创建一个对象就会有一个新的成员变量，调用方式  对象.x


//2#静态变量在编译时(转化为class文件时)生成
   //成员变量在运行时产生

//2.修饰方法 基本跟修饰变量一样

//3.不能修饰类 为什么？
  //类也是存放在静态方法区里面，也是在编译时产生。


//被final修饰后会有什么影响?  不可变的
//1.修饰变量   这个变量不能被重新赋值
//2.修饰方法   方法不能被重写override
//3.修饰类      不能被继承
public class TestBook extends Product{
	//如何定义常量  只有一份而且不可变
	public static final int MAX_VALUE =10000;
	
    private final int fVar = 1;//final变量不可变
	public TestBook(){
		System.out.println("调用Book的构造器"+fVar);
	}
	
	//方法的重载overload
	void test(int i){
		
	}
	void test(){}
//	@Override
//	public void Product() {
//		super.Product();
//	}
	
	public static void main(String[] args) {
//		Book book= new Book();
//		System.out.println(book.var);
//		
//		System.out.println(Book.s_var);
		
		//book.fVar = 2; 
		//book.getId()
		
	}
}
