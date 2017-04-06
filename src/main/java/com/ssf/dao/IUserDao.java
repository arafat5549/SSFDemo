package com.ssf.dao;

import org.springframework.cache.annotation.Cacheable;

import com.ssf.model.User;

/**
 * 
 * @author wyy
 * 2017年4月5日
 *
 */
public interface IUserDao extends BaseDao<User>
{
	
	void sayHello();
	
	
}
