package com.ssf.utils.test;

import java.util.HashMap;

import com.ssf.dao.UserDao;
import com.ssf.model.User;

/**
 * 自定义缓存
 * @author wyy
 * 2017年3月9日
 *
 */
public class CacheUtils {
	UserDao userDao = new UserDao();
   HashMap<String, Object> cahce = new HashMap<String, Object>();
   
   public User getUser(String username){
	   
	  String cacheKey = "user:findByUserName#" + username;
	  //1.先去缓存里面找
	  if(cahce.get(cacheKey)!=null)
	  {
		  return (User)cahce.get(cacheKey);
	  }
	  //2.缓存找不到 执行操作
	  User user = userDao.findByUserName(username);
	  cahce.put(cacheKey, user);
	  return user;
   }
   
   public User getUser(int id){
	   	  String cacheKey = "user:findById#" + id;
		  //1.先去缓存里面找
		  if(cahce.get(cacheKey)!=null)
		  {
			  return (User)cahce.get(cacheKey);
		  }
		  //2.缓存找不到 执行操作
		  User user = userDao.findById(id);
		  cahce.put(cacheKey, user);
		  return user; 
   }
   
   public static void main(String[] args) {
	
   }
}
