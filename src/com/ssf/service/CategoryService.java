package com.ssf.service;

import java.util.List;

import com.ssf.dao.CategoryDao;
import com.ssf.model.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	
	//找到一级分类 （它的父id为0）
	public List<Category> findFirstCategorys(){
		return categoryDao.findFirstCategorys();
	}
}
