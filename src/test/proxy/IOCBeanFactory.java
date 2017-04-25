package test.proxy;

import java.util.HashMap;
import java.util.Map;

import com.ssf.model.Account;

public class IOCBeanFactory {
	
	Map<String,Object> cache = new HashMap<String,Object>();
	
	//1.注册对象
	public  void register(String name,Object obj){
		cache.put(name, obj);
	}
	
	public Object getBean(String name){
		return cache.get(name);
	}
//	public Object getBean(Class cls){
//		
//	}
	
	public static void main(String[] args) {
		IOCBeanFactory ioc = new IOCBeanFactory();
		
		//<bean id="account" class="com.ssf.model.Account"> </bean>
		
		String name = "account";
		String clsName = "com.ssf.model.Account";
		
		try {
			ioc.register(name, Class.forName(clsName).newInstance());
			
			Account ac = (Account)ioc.getBean(name);
			System.out.println(ac);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
