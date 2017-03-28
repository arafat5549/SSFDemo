package com.ssf.dao;

import java.util.List;

/**
 * 通用DAO接口(泛型类 )
 * Template模板
 * @author wyy
 * 2017年3月25日
 *
 */
public interface BaseDao<T> {
    /**
     * 返回所有的对象(泛型属性)
     * @return
     */
	List<T> findAll(); //如果是泛型类的话不需要必须指定为泛型方法
	
	/**
	 * 根据ID获取对象
	 */
	T findById(Integer id);
	
	//(泛型方法)
	///<T> List<T> query3(String sql);
	/**
	 * 保存对象(泛型的参数)
	 * @param t
	 */
	boolean save(T t);
	/**
	 * 更新对象
	 */
	void update(T t);
	/**
	 * 删除对象
	 */
	void delete(Integer id);
}



