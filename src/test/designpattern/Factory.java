package test.designpattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 2.设计模式-工厂模式
 * 
 * new
 * 需要从工厂里面取（统一管理），解耦合
 * 
 * @author wyy
 * 2017年3月31日
 *
 */

//水果类接口
interface Fruit{
	public void eat() ;
}
class Apple implements Fruit{

	@Override
	public void eat() {
		System.out.println("吃苹果。");
	}
}
class Banana implements Fruit{
	@Override
	public void eat() {
		System.out.println("吃香蕉。");
	}
}

public class Factory{
	/**
	 * 不使用反射 水果有好多实现类，每加一个实现类我就需要修改我的源代码
	 * @param className
	 * @return
	 */
	public static Fruit getFruit(String className) 
	{
		Map<String,Fruit> cache=new HashMap<String,Fruit>();
		
		if("apple".equals(className)){//缓存（只会有一份，有则从缓存里面获取）
			Fruit f = cache.get(className);
			if(f!=null){
				return f;
			}
			Fruit value = new Apple();
			//System.out.println(value.getClass().getName());
			cache.put(className, value);
			return value;
		}
		else if("banana".equals(className)){
			return new Banana();
		}
		return	null;
	}
	//通用
	public static Fruit getFruit2(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		 return (Fruit)Class.forName(className).newInstance();
	}
	
	public static void main(String[] args) {
		Factory.getFruit("apple").eat();
		
		
		Factory.getFruit("test.designpattern.Apple").eat();
	}
}


//interface Fruit {
//	public void eat() ;
//}
//
//class Apple implements Fruit {
//	public void eat() {
//		System.out.println("吃苹果。");
//	};
//}
//
//class Orange implements Fruit {
//	public void eat() {
//		System.out.println("吃橘子。");
//	};
//}
//
//public class Factory {
//	public static Fruit getInstance(String className) 
//	{
//		if("apple".equals(className)){
//			return new Apple();
//		}
//		return	null;
//	}
//	//反射
//	public static Fruit getInstance2(String className) 
//	{
//		Fruit f = null;
//		try{
//			f = (Fruit) Class.forName(className).newInstance() ;
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return f ;
//	}
//}

