package com.ssf.service.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssf.dao.IUserDao;
import com.ssf.model.User;

@Service
public class AdminService {

	@Autowired
	IUserDao userDao;
	
	
	
	public String adminLogin(User user){
		return "";
	}
}
