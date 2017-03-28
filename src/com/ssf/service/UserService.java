package com.ssf.service;

import java.util.List;

import com.google.common.base.Objects;
import com.ssf.dao.UserDao;
import com.ssf.model.User;
import com.ssf.utils.Global;

/**
 *  用户相关
 */
public class UserService 
{
	private UserDao userDao = new UserDao();
	
	public void delete(int id){
		userDao.delete(id);
	}
	public User findById(int id){
		return userDao.findById(id);
	}
	public User findByUserName(String username){
		return userDao.findByUserName(username);
	}
	public boolean save(User user){
		return userDao.save(user);
	}
	public void update(User user){
		userDao.update(user);
	}
	
	public int getListCount(){
		return userDao.getListCount();
	}
	
	public List<User> listPage(int limit,int offset){
		return userDao.listPage(limit, offset);
	}
	
	
	//------------------------------------------------------
	/**
	 * 注册
	 */
     public String register(User user){
    	String username = user.getUsername();
    	String password = user.getPassword(); 
    	//0.参数的判定
    	//用户名必须为3-6位的中文字母数字下划线
    	String regex ="[\u4e00-\u9fa5a-zA-Z0-9_]{3,6}";
    	boolean f = Global.validate(username, regex);
    	if(!f){
    		return "用户名必须为3-6位的中文字母数字下划线";
    	}
    	//密码长度为6-12位 以字母数字开头，并且不能带中文
    	regex ="[a-zA-Z0-9][^\u4e00-\u9fa5]{5,11}";
    	f = Global.validate(password, regex);
    	if(!f){
    		return "密码长度为6-12位 以字母数字开头，并且不能带中文";
    	}
    	//1.判断有没有这个User对象
    	User exist = userDao.findByUserName(username);
    	if(exist!=null){
    		return "用户名已存在";
    	} 
    	//数据入库
    	f = userDao.save(user);
    	if(!f){
    		return "插入数据库错误";
    	}
    	return "";
     }
     
    /**
     * 登录
     */
	public String login(User exist,String password) {
		String ret = "";
		if(exist == null){
			ret = "该用户不存在";
		}
		else
		{
		    //if(password!=null && !"".equals(password) 
			//	&& password.equals(exist.getPassword()))
			if(Objects.equal(password, exist.getPassword()))
		    {
			 
			}
			else{
				ret = "输入密码错误";
			}
		}
		return ret;
	}
}
