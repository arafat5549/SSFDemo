package test.designpattern;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
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
public class ReflectDemo {
	
	private Integer id;
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public void test(){
		System.out.println("测试");
	}
	
	@Test
	public void reflectDemo() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		/**
		 * 1.三种获取类信息的方式Class
		 */
		
		//正常获得类信息的方式
		ReflectDemo demo = new ReflectDemo();
		//还没有实例化类对象 我可以知道类的字段和方法
		Class<?> cls  = ReflectDemo.class;
		Class<?> cls2 = Class.forName("test.designpattern.ReflectDemo");
		
		System.out.println(demo.getClass());
		System.out.println(cls);
		System.out.println(cls2);
		
		//一个类的有用信息：属性Filed ,方法Method,构造器Constructor,注解Annotation
		
		/**
		 * 1.在运行时判断任意一个类所具有的成员变量和方法。
		 */
		//获取方法
		Method[] publicMethods   = cls.getMethods(); //获取所有public的方法
		Method[] declaredMethods = cls.getDeclaredMethods();//你所有声明都会有
		//cls.getMethod(name, parameterTypes)
		//cls.getDeclaredMethod(name, parameterTypes)
		
		//获取属性
		Field[] publicFileds =cls.getFields();
		Field[] declaredFileds = cls.getDeclaredFields();
		//cls.getField(name)
		
		//获取构造器
		//cls.getConstructors();
		//获取注解
		//cls.getAnnotations();
		
		/**
		 * 3.在运行时调用任意一个对象的方法。
		 */
		Object obj = cls.newInstance();
		Method method = cls.getMethod("test", null);
		method.invoke(obj, null);
		
		
		/**
		 * DEMO
		 * 
		 * 通过属性的名称和类别 调用方法
		 * 5.在运行时变更field的内容
		 */
		Field[] fileds = cls.getDeclaredFields();
		for (Field field : fileds) {
			String name = field.getName();
			//field.getType()
			System.out.println(name);
			String setter = "set" + StringUtils.capitalize(name);
			System.out.println(setter);
			//2.判断他的参数类别
			Method m = cls.getMethod(setter, Integer.class);
			m.invoke(obj, new Integer(1));
			System.out.println(":::"+m);
		}
		
		System.out.println(obj);
	}

	@Override
	public String toString() {
		return "ReflectDemo [id=" + id + "]";
	}
	
	
}
