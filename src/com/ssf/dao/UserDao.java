package com.ssf.dao;

import java.util.List;

import com.ssf.model.User;
/**
 * DataAccessObject(DAO层) # Resposity层
 * 用户DAO接口层 
 * @author wyy
 * 2017年3月23日
 *
 */
public class UserDao implements BaseDao<User>{
 
	//根据名字超照你有没有该对象
	public User findByName(String name){
		if(name.equals("wang")){
			User user = new User();
			user.setUsername(name);
			user.setPassword("123");
			return user;
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public void save(User t) {
		// TODO Auto-generated method stub
		
	}
}
