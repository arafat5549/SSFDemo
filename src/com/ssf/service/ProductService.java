package com.ssf.service;

import java.util.List;

import com.ssf.dao.CategoryDao;
import com.ssf.dao.ProductDao;
import com.ssf.model.Category;
import com.ssf.model.Product;

/**
 * 商品
 *
 */
public class ProductService {
	
	private ProductDao productDao = new ProductDao();
	private CategoryDao categoryDao =new CategoryDao();
	
	public List<Product> getProductListByCid(int cid)
	{
		return productDao.getProductListByCid(cid);
	}
	
	public int getListCount(){
		return getListCount(0);
	}
	public int getListCount(int cid){
		if(cid>0)
		   return productDao.getListCount(cid);
		else
		   return productDao.getListCount();
	}
	
	public List<Product> listPage(int limit,int offset){
		return listPage(limit, offset,0);
	}
	
	public List<Product> listPage(int limit,int offset,int cid){
		if(cid >0)
			 return productDao.findByCategoryId(limit, offset, cid);
		else{
			List<Product> lists =productDao.listPage(limit, offset);
			return lists; 
		}
		
	}
	
	
	public boolean save(Product p){
		return productDao.save(p);
	}
	public Product findById(int id) {
		Product p = productDao.findById(id);
		if(p!=null){
			Category c = categoryDao.findById(p.getCategoryId());
			p.setCategory(c);
		}
		return p;
	}
	public void update(Product p) {
		productDao.update(p);
	}
	public void delete(int id) {
		productDao.delete(id);
	}
}
