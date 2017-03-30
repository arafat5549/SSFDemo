package com.ssf.service;

import java.util.List;

import com.ssf.dao.CategoryDao;
import com.ssf.model.Category;
import com.ssf.model.Product;

/**
 * 分类Service业务层
 * @author wyy
 * 2017年3月29日
 *
 */
public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	//private ProductDao productDao = new ProductDao();
	ProductService productService = new ProductService();
	/**
	 * 找到一级分类 （一级分类代表父id为0）<br>
	 * 包含子分类
	 * @return
	 */
	public List<Category> findFirstCategorys(){
		List<Category> lists = categoryDao.findFirstCategorys();
		for (Category category : lists) {
			List<Product> products = productService.findAllSub(category.getId());
			//怎么取前5个
			category.setProducts(products.subList(0, 5));
		}
		return lists;
	}
}
