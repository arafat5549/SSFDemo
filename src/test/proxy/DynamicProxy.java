package test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;



//JDK的动态代理
//1.实现InvocationHandler
//2.绑定你要代理的类
//3.INVOKE添加你要增强的功能
public class DynamicProxy implements InvocationHandler{
	//private static Cache cache = new Cache();
	
	//要绑定的代理类
	private Object target;
	public DynamicProxy(Object p){ 
		this.target = p;
	}
	//绑定
	public Object Bind(Object p){
		this.target = p;
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(),//JDK动态代理默认靠接口来接口 ，CGLIB代理
				this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
	  Type[] types = method.getParameterTypes();
   if (method.getName().matches("get.+") && (types.length == 1) &&
           (types[0] == String.class)) {
// 	  Annotation[] anns = proxy.getClass().getAnnotations();
// 	  for (Annotation annotation : anns) {
//			if (annotation.equals(obj)) {
//				
//			}
// 	  }
// 	  //做了缓存
//       String key = (String) args[0];
//       Object value = cache.getFromCache(key);
//       if (value == null) {
//           value = method.invoke(target, args);
//           cache.addToCache(key, value);
//       }
//       return value;
	   
	    System.out.println("----doSomething----"); 
	   
 	    return method.invoke(target, args);
   }
    return method.invoke(target, args);
  }
	
	 //1.IFontProvider
	  public static IFontProvider getFontProvider() 
	  {
	      Class<IFontProvider> targetClass = IFontProvider.class;
	      return (IFontProvider) Proxy.newProxyInstance(
	    		  targetClass.getClassLoader(),
	          new Class[] { targetClass },
	          new DynamicProxy(new NetFontProvider()));
	  }	
	
	  public static void main(String[] args) {
		    IFontProvider fp = new NetFontProvider();
			Font font = fp.getFont("宋体");
			System.out.println(font);
//			Font font2 = fp.getFont("宋体");
//			System.out.println(font==font2);
			
			IFontProvider mp =  getFontProvider();
			Font fontX = mp.getFont("宋体2");
			System.out.println(fontX);
//			Image img = mp.getImage("1.jpg");
//			System.out.println(img);
	}
	
}