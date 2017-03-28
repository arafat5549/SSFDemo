package demo;


//抽象类

//只要你有一个抽象方法你就一定是抽象类
//抽象类不能被实例化(instance)
public  abstract class AbstractClass {
   
	//抽象方法不需要方法体 - 我不实现这个方法谁继承我谁实现
	public abstract void method1();
	
	public void method2(){
		
		System.out.println("method2-");
	};
}
