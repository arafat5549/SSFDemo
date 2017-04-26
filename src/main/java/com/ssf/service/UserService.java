package com.ssf.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssf.dao.IAccountDao;
import com.ssf.model.User;

/**
 * 
 * @author wyy
 * 2017年4月5日
 *
 */
@Service //ioc注解
public class UserService {
	@Autowired
	IAccountDao accountDao;
//	//SLF4J :日记的接口类
//	//jpa   :持久化的东西的接口类
//	//1.解耦合 - 实现类的解耦合
//	//2.
//	
//	//实现跟Servlet一样的效果
//	
//	//1.使用的时候才生成它
//	//2.希望我们能控制它是不是单例模式
//	
//	UserDaoImpl userDao = new UserDaoImpl();//用户实现类1
//	public void sayHello(){
//		userDao.sayHello();
//	}
//	
//	//依赖注入
//	@Autowired
//	IUserDao myDao = null;
//	
//	public IUserDao getMyDao() {
//		return myDao;
//	}
//	public void setMyDao(IUserDao myDao) {
//		this.myDao = myDao;
//	}
//
//	public void sayHello2(){
//		myDao.sayHello();
//	}
	
	//IUserDao userDao;
	
	
	
	/**
	 * 直接使用事务的注解
	 */
	@Transactional
	public void transferAccount(int from ,int to ,BigDecimal money) 
	{
		accountDao.minusMoney(from, money);
		int i= 1/0;
		accountDao.addMoney(to, money);
	}
	
	
	public String login(User user){
		System.out.println("PC端登录");
		return "";
	}
}
