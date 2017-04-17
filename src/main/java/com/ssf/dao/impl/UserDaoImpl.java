package com.ssf.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssf.dao.IUserDao;
import com.ssf.model.User;

/**
 * 实现类一
 * @author wyy
 * 2017年4月5日
 *
 */

//@Repository  //Dao层
public class UserDaoImpl implements IUserDao{
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	
	@Override
	public void sayHello() {
		
		System.out.println("sayHello");
	}

	@Override
	public List<User> findAll() {
		SqlSession session =  sqlSessionFactory.openSession();
		System.out.println("session="+session);
		String method = "findAll";//就是你的UserMapper里面定义的方法id
		return session.selectList(method);
	}

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

}
