package com.ssf.dao;

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
