package com.ssf.utils;

/**
 * 废弃类
 * 只用于一些描述
 * @author wyy
 * 2017年3月29日
 *
 */
public class Describe{
   /*
    * JDk可以分为几大模块
    * 
    * 0.环境变量 配置运行环境
    * #0.JAVA语言基础
    *  -
    * #1.(面向对象模块)：
    *  -封装(setter和getter)，
    *  -继承(通用的抽取出来)，
    *  -多态(interface接口)
    * 2.数据结构(集合Collection)
    *  -常用数据结构:List , Map , Set , Stack , Queue # Tree,Graph(图论)
    * 3.算法
    *  -排序   合并排序，冒泡排序
    *  -查找   二分查找法(前提集合先做排序，每次都用中间数来对比)
    * 4.IO流
    *  -文件IO
    *  -数据库IO
    *  -网络IO
    *  注意事项：IO流规范化代码,记得关闭流
    * #5.多线程和并发(提高你流处理的效率)
    *  注意事项：同步和异步的区别?
    * #6.内存管理和Java内存模型和JVM
    *  -栈内存和堆内存. (Java的元素哪些放在栈内存，哪些放在堆内存)
    *  -垃圾收集GC
    *  -
    * 7.异常处理(逻辑的严密性,越严密你的系统漏洞越少越安全)  # 经验
    * 8.JAVA的高级特性
    *  -反射
    *  -泛型
    *  -动态代理
    *  -注解
    *  -枚举
    * 10.数据库
    * 11.网络编程
    * 
    * 12.工具类
    *  java.lang(Java的语言包)
    *  (Math数据相关,String相关 , 时间Date ,随机数Random)
    *  java.util(Java的工具包)
    */
	
	//时间复杂度  (期望值)
	//O(1)      : Hash表
	//O(n)      : 搜索一个阵列(数据库)
	//O(log(n)) : 二分查找
	//O(n的2次方) : 
	
	public static void main(String[] args) {
		//数学相关    O,log()
		System.out.println(Math.log(1000000000));
		
		//Math.log10(a)
		int n =100;
		for (int i = 0; i <n; i++) {
			for (int j = 0; j <n; j++) {
				
			}
		}
		
		//同步处理
		//..做一些事
		readFile();
		//..做一些事
		
		//异步处理
		//..做一些事
//		new Thread(){
//		   readFile();
//		}
		//..做一些事
	}
	
	//.耗时一个小时
	public static void readFile(){
		
	}
}
