package foo.lesson.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


/**
 * SpringAOP模块
 * 
 * 实现动态代理类 来实现不同类型的Provider
 * 
 * 在运行时确认你的Provider是提供字体还是图片的加载
 * 
 * @author wyy
 * 2016年11月30日
 *
 */

//动态代理类 - JDK的动态代理 - 缓存的代理

// JDK的动态代理
//1.实现InvocationHandler
//2.绑定你要代理的类
//3.INVOKE添加你要增强的功能
public class DynamicProxyProvider implements InvocationHandler{
	private static Cache cache = new Cache();
	
	//要绑定的代理类
	private Object target;
	public DynamicProxyProvider(Object p){ 
		this.target = p;
	}
	//绑定
	public Object Bind(Object p){
		this.target = p;
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(),//JDK动态代理默认靠接口来接口
				this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
	  Type[] types = method.getParameterTypes();
		//getFont , getImage()
		//getter方法-
      if (method.getName().matches("get.+") && (types.length == 1) &&
              (types[0] == String.class)) {
    	  
//    	  //做了缓存
//          String key = (String) args[0];
//          Object value = cache.getFromCache(key);
//          if (value == null) {
//              value = method.invoke(target, args);
//              cache.addToCache(key, value);
//          }
//          return value;
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
	          new DynamicProxyProvider(new NetFontProvider()));
	  }
	//2.
	  public static IImageProvider getImageProvider() 
	  {
	      Class<IImageProvider> targetClass = IImageProvider.class;
	      return (IImageProvider) 
	    		  Proxy.newProxyInstance(
	    	  targetClass.getClassLoader(),
	          new Class[] { targetClass },
	          new DynamicProxyProvider(new NetImageProvider()));
	  }
	
	  public static void main(String[] args) 
	  {
		    IFontProvider fp = getFontProvider();
			Font font = fp.getFont("宋体");
			System.out.println(font);
			Font font2 = fp.getFont("宋体");
			System.out.println(font==font2);
			
			IImageProvider mp =  getImageProvider();
			Image img = mp.getImage("1.jpg");
			System.out.println(img);
	}
	  
	  
		/**
		 * 这也是为什么Spring这么受欢迎的一个原因
		   Spring容器代替工厂ProviderFactory，Spring AOP代替JDK动态代理，让面向切面编程AOP更容易实现。
		     在Spring的帮助下轻松添加，移除动态代理，且对源代码无任何影响。
		 */
}




