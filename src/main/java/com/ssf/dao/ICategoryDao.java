package com.ssf.dao;

import java.util.List;

import com.ssf.base.BaseDao;
import com.ssf.model.Category;

public interface ICategoryDao extends BaseDao<Category>{
	List<Category> findAllList();
	
	List<Category> findPage(Integer offset,Integer limit);
}
