package com.ssf.dao;

import java.util.List;

/**
 * DAO
 * 通用DAO对象#数据连接对象 负责所有数据相关的操作
 */
public interface BaseDao<T> {
	    //1.获取所有对象
		public List<T> findAll();
		//2.根据id获取某个对象
		public T findById(int id);
		//3.获取分页对象 limit,offset
		public List<T> listPage(int limit,int offset);
		
		//4.save存储对象
		public boolean save(T t);
		//5.修改对象-User
		public void update(T t);
		//6.删除对象
		public void delete(int id);
}
