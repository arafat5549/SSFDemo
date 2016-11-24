package foo.lesson.summary;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 反射Reflect<p>
 * 
 * 反射是Java语言的一个很重要的特征，它使得Java具体了“动态性”
 * 起效时期:运行时RunTime
 * 
 * ${page.name} //com.ssf.action.Page
 * 这种“看透class”的能力 ，introspection（内省、内观、反省）
 * 
 *  4.在运行时判断任意一个对象所属的类。
	2.在运行时构造任意一个类的对象。
	1.在运行时判断任意一个类所具有的成员变量和方法。
	3.在运行时调用任意一个对象的方法。
	5.在运行时变更field的内容
 * 
 * @author wyy
 * 2016年11月24日
 *
 */
public class LessonReflect 
{
	public int id;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void Test(List list){System.out.println("Test");}
	
	@Test
	public void ReflectTest() throws ClassNotFoundException{
		//正常获得类信息的方式
		LessonReflect demo = new LessonReflect();
		//还没有实例化类对象 我可以知道类的字段和方法
		Class cls  = LessonReflect.class;
		Class cls2 = Class.forName("foo.lesson.summary.LessonReflect");
		
		System.out.println(demo.getClass());
		System.out.println(cls);
		System.out.println(cls2);
		
		//1.获得类的对象 
		System.out.println("--cls.getDeclaredFields--");
		Field fs[] = cls.getDeclaredFields();//所有的字段都能获取
		for (Field f:fs) {
			System.out.println(f); 
		}
		System.out.println("--cls.getFields--");
		Field fss[] = cls.getFields();//只有public的修饰才能获取到
		for (Field f:fss) {
			System.out.println(f); 
		}
		//2.获得类的方法
		System.out.println("--cls.getDeclaredMethods--");
		Method ms[]=cls.getDeclaredMethods();
		for (Method f:ms) {
			System.out.println(f); 
		}
		//3.获取构造器
		//4.获取注解
		//5.获取接口
		
		//1.在运行时判断任意一个类所具有的成员变量和方法。
		//#2.在运行时构造任意一个类的对象。 对象的复制
		//3.在运行时调用任意一个对象的方法。
		//4.在运行时判断任意一个对象所属的类。
		//5.在运行时变更field的内容
	}
	
	//
	@Test
	public void introspectionDemo() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		
		//List<String> lists =new ArrayList<String>();
		//List<String> lists2 =new LinkedList<String>();
		
		Class cls  = LessonReflect.class;
		Object object = cls.newInstance();
		String var = "name";
		//1.
		Field fields[] = cls.getDeclaredFields();//1.获取成员对象和方法
		for (Field field : fields) {
			String fieldName = field.getName();
			if(fieldName.equals(var)){
				 String firstLetter = fieldName.substring(0, 1).toUpperCase();//N
		            // 获得和属性对应的getXXX()方法的名字
		         String getMethodName = "get" + firstLetter + fieldName.substring(1);
		         System.out.println("getMethodName="+getMethodName);
		         
		         String setMethodNam = "set" + firstLetter + fieldName.substring(1);
		         
		         //构建方法和调用方法
		         Method setMethod = cls.getMethod(setMethodNam, new Class[]{String.class});
		         setMethod.invoke(object, new Object[]{"Test222"});
		         
		         Method getMethod = cls.getMethod(getMethodName, new Class[]{});
		         Object name =getMethod.invoke(object, new Object[]{});
		         System.out.println(name);
			} 
		}
		
		System.out.println(((LessonReflect)object).getName());
		
	}
	
}


class Demo{
	String name;
}
