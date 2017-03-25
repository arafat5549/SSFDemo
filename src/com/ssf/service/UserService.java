package com.ssf.service;

import com.ssf.dao.UserDao;
import com.ssf.model.User;

/**
 * 业务层 - 处理逻辑
 * @author wyy
 * 2017年3月23日
 *
 */
public class UserService {

	private UserDao userDao = new UserDao();
	//面向对象
	/**
	 * 登录功能
	 * @param user
	 * @return
	 */
	public String login(User user)//(String username,String password)
	{
		User exist = userDao.findByName(user.getUsername());
		if(exist==null){
			return "该用户不存在";
		}
		if(!exist.getPassword().equals(user.getPassword())){
			return "密码错误";
		}
		return "";
	}
}
