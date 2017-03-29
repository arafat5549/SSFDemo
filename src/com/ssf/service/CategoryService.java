package com.ssf.service;

import java.util.List;

import com.ssf.dao.CategoryDao;
import com.ssf.model.Category;

/**
 * 分类Service业务层
 * @author wyy
 * 2017年3月29日
 *
 */
public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();

	/**
	 * 找到一级分类 （一级分类代表父id为0）<br>
	 * 包含子分类
	 * @return
	 */
	public List<Category> findFirstCategorys(){
		return categoryDao.findFirstCategorys();
	}
}
