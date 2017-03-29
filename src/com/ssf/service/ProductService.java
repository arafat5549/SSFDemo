package com.ssf.service;

import java.util.ArrayList;
import java.util.List;

import com.ssf.dao.CategoryDao;
import com.ssf.dao.ProductDao;
import com.ssf.model.Category;
import com.ssf.model.Product;

public class ProductService {
	//一个Service可能会使用到多个Dao层，一个Dao层只处理它相关的实体
	ProductDao productDao = new ProductDao();
	CategoryDao categoryDao = new CategoryDao();
	
	/**
	 * 根据分类Id获取商品（会包含子分类）
	 */
	public List<Product> findAllSub(Integer categoryId){
		List<Product> lists = new ArrayList<Product>();
		List<Category> ids = categoryDao.findAllCategorysById(categoryId);
	    for (Category category : ids) {
	    	List<Product> ps = productDao.findProductsByCateId(category.getId());
	    	lists.addAll(ps);
		}
	    return lists;
	}
}
