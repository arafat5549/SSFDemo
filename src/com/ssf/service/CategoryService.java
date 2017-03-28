package com.ssf.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssf.dao.CategoryDao;
import com.ssf.model.Category;
import com.ssf.utils.DBUtils;

public class CategoryService {

	private CategoryDao categoryDao = new CategoryDao();
	
	//获取某个分类的所有子类
	public List<Category> findSubCate(int parentid){
		String sql ="SELECT b.id,b.cname,b.parentid FROM sys_category a "+
		"JOIN sys_category b "  +
		"ON a.id = b.parentid " +
		"WHERE a.id = ?";
		try {
			return DBUtils.getInstance().listBean(sql, Category.class, parentid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Category>();
	}
	
	//获取所有子类id 
	//获取所有第一级分类
	public  List<Category> findFirstCategoryList(){
		return categoryDao.findFirstCategoryList();
	}

	public List<Category> listPage(int limit, int offset) {
		return categoryDao.listPage(limit, offset);
	}

	public boolean save(Category t) {
		return categoryDao.save(t);
	}

	public Category findById(int id) { 
		return categoryDao.findById(id);
	}

	public void update(Category p) {
		categoryDao.update(p);
	}

	public void delete(int id) {
		categoryDao.delete(id);
	}
	
}
