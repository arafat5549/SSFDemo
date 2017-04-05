package com.ssf.dao.impl;

import org.springframework.stereotype.Repository;

import com.ssf.dao.IUserDao;

/**
 * 实现类一
 * @author wyy
 * 2017年4月5日
 *
 */

@Repository//Dao层
public class UserDaoImpl implements IUserDao{

	@Override
	public void sayHello() {
		
		System.out.println("sayHello");
	}

}
