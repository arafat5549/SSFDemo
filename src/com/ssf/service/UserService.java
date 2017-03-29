package com.ssf.service;

import com.ssf.dao.UserDao;
import com.ssf.model.User;

/**
 * 用户Service业务层 - 处理逻辑
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
	/**
	 * 注册功能
	 */
	public String register(User user){
		String username = user.getUsername();
		//1.所有入库的东西第一步做什么(参数验证)
		//用户名: (数字字母下划线和中文) 3-6位
		//密码:   (数字字母)3-6位
		//2.先判断重名不重名
		User exist = userDao.findByName(username);
		if(exist!=null){
			return "无法注册，该用户名已被占用";
		}
		boolean flag = userDao.save(user);
		if(!flag){
			return "数据库错误";
		}
		return "";
	}
}
