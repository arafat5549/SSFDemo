package com.ssf.dao.impl;

import org.springframework.stereotype.Repository;

import com.ssf.dao.IUserDao;

//@Repository
public class OtherUserDaoImpl implements IUserDao{

	@Override
	public void sayHello() {
		System.out.println("other Dao");
	}

}
