package com.ssf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ssf.dao.IUserDao;
import com.ssf.dao.impl.UserDaoImpl;

//@Component
//@Repository
//@Service
//@Controller

@Service
public class UserService {

	//SLF4J :日记的接口类
	//jpa   :持久化的东西的接口类
	
	
	
	//1.解耦合 - 实现类的解耦合
	//2.
	
	//实现跟Servlet一样的效果
	
	//1.使用的时候才生成它
	//2.希望我们能控制它是不是单例模式
	
	UserDaoImpl userDao = new UserDaoImpl();//用户实现类1
	public void sayHello(){
		userDao.sayHello();
	}
	
	//依赖注入
	@Autowired
	IUserDao myDao = null;
	
	public IUserDao getMyDao() {
		return myDao;
	}
	public void setMyDao(IUserDao myDao) {
		this.myDao = myDao;
	}

	public void sayHello2(){
		myDao.sayHello();
	}
	
	
}
