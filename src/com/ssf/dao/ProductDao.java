package com.ssf.dao;

import java.util.List;

import com.ssf.model.Product;
import com.ssf.utils.DBUtils;

/**
 * 商品Dao层
 * @author wyy
 * 2017年3月29日
 *
 */
public class ProductDao implements BaseDao<Product>{
	
	private static final String COLUMNS =
			" a.id,"
	       + " a.name,"
	       + " a.subTitle,"		
	       + " a.originPrice,"
	       + " a.promotoPrice,"
	       + " a.stock,"
	       + " a.category_id AS 'categoryId',"
	       
	       + " a.create_time AS 'createTime', "
	       + " a.update_time AS 'updateTime' ";
	
	/**
	 * 根据分类Id获取商品
	 */
	public List<Product> findProductsByCateId(Integer categoryId){
		String sql ="SELECT " + COLUMNS +" FROM sys_product a WHERE a.category_id=?";
		return DBUtils.getInstance().listBean(sql, Product.class,categoryId);
	}
	
	@Override
	public List<Product> findAll() {
		String sql ="SELECT " + COLUMNS +" FROM sys_product a";
		return DBUtils.getInstance().listBean(sql, Product.class);
	}

	@Override
	public Product findById(Integer id) {
		String sql ="SELECT " + COLUMNS +" FROM sys_product a WHERE a.id=?";
		return DBUtils.getInstance().queryBean(sql, Product.class,id);
	}

	@Override
	public boolean save(Product t) {
		String idStr = "";
		String idSuffix ="";
		boolean hasId = t!=null && t.getId()!=null && t.getId() >0;
		if(hasId)
		{
			idStr = "id,";
			idSuffix="?,";
		}
		String sql ="INSERT INTO sys_product("
				+ idStr+"name,originPrice,promotoPrice,stock,category_id,create_time,update_time) "
				+ " VALUES("+idSuffix+"?,?,?,?,?,?,?)";
		System.out.println(sql);
		t.preInsert();
		
		if(hasId){
			return DBUtils.getInstance().execute(sql,
					t.getId(),
					t.getName(),
					t.getOriginPrice(),
					t.getPromotoPrice(),
					t.getStock(),
					t.getCategoryId(),
					t.getCreateTime(),
					t.getUpdateTime());
		}
		else{
			return DBUtils.getInstance().execute(sql, 
					t.getName(),
					t.getOriginPrice(),
					t.getPromotoPrice(),
					t.getStock(),
					t.getCategoryId(),
					t.getCreateTime(),
					t.getUpdateTime());
		}
	}

	@Override
	public void update(Product t) {
		String sql = "UPDATE sys_product SET "
				+ "name=?,originPrice=?,promotoPrice=?,stock=?,category_id=?,"
				+ "create_time=?,update_time=? "
				+ " WHERE id=?";
		 t.preUpdate();
		 DBUtils.getInstance().execute(sql, 
					t.getName(),
					t.getOriginPrice(),
					t.getPromotoPrice(),
					t.getStock(),
					t.getCategoryId(),
					
					t.getCreateTime(),
					t.getUpdateTime(),
					t.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM sys_product WHERE id=?";
		DBUtils.getInstance().execute(sql, id);
	}


	@Override
	public Integer findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

}
