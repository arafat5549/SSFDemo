package com.ssf.utils;

import java.util.HashMap;
import java.util.Map;

import com.ssf.dao.AccountDao;
import com.ssf.dao.UserDao;
import com.ssf.service.UserService;

/**
 * 自定义Bean工厂 里面的Bean对象都是单例 可以通过id来获取
 * @author wyy
 * 2017年3月2日
 *
 */
public class BeanFactory {

	Map<String,Object> factory =new HashMap<String,Object>();
	
	private BeanFactory(){
		//初始化
		UserService userService = new UserService();
		factory.put("userService", userService);
		
		UserDao userDao = new UserDao();
		factory.put("userDao", userDao);
		
		AccountDao accountDao = new AccountDao();
		factory.put("accountDao", accountDao);
	}
	
	private static BeanFactory instance;
	public static BeanFactory getInstance()
	{
		if(instance == null){
			instance = new BeanFactory();
		}
		return instance;
	}
	//
	public Object getBean(String key){
		return factory.get(key);
	}
	
	
	
}
