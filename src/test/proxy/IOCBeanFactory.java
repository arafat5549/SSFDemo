package test.proxy;

import java.util.HashMap;
import java.util.Map;

import com.ssf.dao.CartDao;
import com.ssf.model.Account;

public class IOCBeanFactory {
	
	private IOCBeanFactory(){};
	private static IOCBeanFactory instance;
	public static IOCBeanFactory getInstance(){
		if(instance == null){
			instance = new IOCBeanFactory();
		}
		return instance;
	}
	
	Map<String,Object> cache = new HashMap<String,Object>();
	
	//1.注册对象
	public  void register(String name,Object obj){
		cache.put(name, obj);
	}
	
	public Object getBean(String name){
		return cache.get(name);
	}
	public Object getBean(Class cls){
		if(cls == CartDao.class){
			
			Object obj = cache.get(cls.getName());
			if(obj == null){
				System.out.println("------------------"+cls.getName());
				CartDao c = new CartDao();
				cache.put(cls.getName(),c);
				return c;
			}
			return obj;
		}
		return null;
	}
	
	public static void main(String[] args) {
		CartService c = new CartService();
		CartService2 c2 = new CartService2();
		
		System.out.println(c.getCartDao() == c2.getCartDao());
		
	}
	
//	public static void main(String[] args) {
//		IOCBeanFactory ioc = new IOCBeanFactory();
//		
//		//<bean id="account" class="com.ssf.model.Account"> </bean>
//		
//		String name = "account";
//		String clsName = "com.ssf.model.Account";
//		
//		try {
//			ioc.register(name, Class.forName(clsName).newInstance());
//			
//			Account ac = (Account)ioc.getBean(name);
//			System.out.println(ac);
//			
//		} catch (InstantiationException | IllegalAccessException
//				| ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
}
