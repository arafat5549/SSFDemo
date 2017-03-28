package com.ssf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ssf.model.Category;
import com.ssf.model.Product;
import com.ssf.utils.DBUtils;

public class ProductDao implements BaseDao<Product>{
	CategoryDao categoryDao = new CategoryDao();
	//不用通配符来做
	private static String columns = 
			" a.id,"
			+ "a.name,"
			+ "a.sub_title AS 'subTitle',"
			+ "a.price,"
			+ "a.promote_price AS 'promotePrice',"
			+ "a.stock,"
			+ "a.category_id AS 'categoryId',"
			+ "a.create_time AS 'createTime'" //数据库命名规范和JavaBean命名规范
			;
	
	//根据分类id获取该分类下的所有产品
	public List<Product> getProductListByCid(int cid){
		
		List<Product> products = new ArrayList<Product>();
		//1.根据分类id获取所有的子分类
		List<Category> lists = categoryDao.getChildsById(cid);
		//2.获取所有的商品
		for (Category category : lists) {
			List<Product> childs = getListByCid(category.getId());
			products.addAll(childs);
			//Collections.addAll(products, childs);
		}
		return products;
	}
	
	public List<Product> getListByCid(int cid){
		String sql ="SELECT "+columns+" FROM sys_product a WHERE category_id=? ";
		List<Product> lists = null;
		try {
			lists = DBUtils.getInstance().listBean(sql, Product.class,cid);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
	
	public int getListCount(int cid){
		String sql ="SELECT "+columns+" FROM sys_product a WHERE category_id=? ";
		List<Product> lists = null;
		try {
			lists = DBUtils.getInstance().listBean(sql, Product.class,cid);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return lists!=null ? lists.size() : 0;
	}
	
	public int getListCount(){
		List<Product> lists = findAll();
		return lists!=null ? lists.size() : 0;
	}
	
	public List<Product> findByCategoryId(int limit, int offset,int cid){
		String sql ="SELECT "+columns+" FROM sys_product a WHERE category_id=? "
				+ "limit "+offset+","+limit+"";
		try {
			return DBUtils.getInstance().listBean(sql, Product.class,cid);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//-------------------------------------------------------
	@Override
	public List<Product> findAll() {
		String sql ="SELECT "+columns+" FROM sys_product a";
		try {
			return DBUtils.getInstance().listBean(sql, Product.class);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Product>();
	}

	@Override
	public Product findById(int id) {
		String sql ="SELECT "+columns+" FROM sys_product a WHERE id=?";
		try {
			return DBUtils.getInstance().queryBean(sql, Product.class,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> listPage(int limit, int offset) {
		String sql = "SELECT "+columns+" FROM sys_product a "
				+ "limit "+offset+","+limit+"";
		try {
			return DBUtils.getInstance().listBean(sql, Product.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Product>();
	}

	@Override
	public boolean save(Product t) {
		
		String idStr = "";
		String idHolder ="";
		boolean flag_id = t!=null && t.getId()!=null && t.getId() > 0;
		if(flag_id){
			idStr = "id,";
			idHolder = "?,";
		}
		
		String sql = "INSERT INTO sys_product"
				+ "("+idStr+"name,price,category_id,create_time) "
				+ "VALUES("+idHolder+"?,?,?,?)";
		try {
			//System.out.println("123000000000000000000000000000".length());
			
			System.out.println("price="+t.getPrice());
			t.preInsert();
			List<Object> params = new ArrayList<Object>();
			if(flag_id)
				params.add(t.getId());
			params.add(t.getName());
			params.add(t.getPrice());
			params.add(t.getCategoryId());
			params.add(t.getCreateTime());
			
			DBUtils.getInstance().executeUpdate2(sql,
					params
			);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void update(Product p) {
		String sql = "UPDATE sys_product SET "
				+ "name=?,price=?,category_id=?,create_time=? "
				+ "WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, 
					p.getName(),p.getPrice(),
					p.getCategoryId(),p.getCreateTime()
					,p.getId());
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM sys_product WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
