package com.ssf.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssf.model.Category;
import com.ssf.utils.DBUtils;

public class CategoryDao implements BaseDao<Category>{

	private static final String COLUMNS =
			" a.id,"
	       + " a.name,"
	       + " a.parent_id AS 'parentId',"
	       + " a.parent_ids AS 'parentIds',"
	       + " a.create_time AS 'createTime', "
	       + " a.update_time AS 'updateTime' ";
	
	
	/**
	 * 获取一级分类(父类id为0)
	 */
	public List<Category> findFirstCategorys(){
		List<Category> lists = new ArrayList<Category>();
		String sql = "SELECT " + COLUMNS +" FROM sys_category a WHERE a.parent_id=0";;
		lists = DBUtils.getInstance().listBean(sql, Category.class);
		return lists;
	}
	
	@Override
	public List<Category> findAll() {
		String sql ="SELECT " + COLUMNS +" FROM sys_category a";
		return DBUtils.getInstance().listBean(sql, Category.class);
	}

	@Override
	public Category findById(Integer id) {
		String sql ="SELECT " + COLUMNS +" FROM sys_category a WHERE a.id=?";
		return DBUtils.getInstance().queryBean(sql, Category.class,id);
	}

	@Override
	public boolean save(Category t) {
		String idStr = "";
		String idSuffix ="";
		boolean hasId = t!=null && t.getId()!=null && t.getId() >0;
		if(hasId)
		{
			idStr = "id,";
			idSuffix="?,";
		}
		String sql ="INSERT INTO sys_category("
				+ idStr+"name,parent_id,parent_ids,create_time,update_time) "
				+ " VALUES("+idSuffix+"?,?,?,?,?)";
		System.out.println(sql);
		t.preInsert();
		
		if(hasId){
			return DBUtils.getInstance().execute(sql,
					t.getId(),
					t.getName(),
					t.getParentId(),
					t.getParentIds(),
					t.getCreateTime(),
					t.getUpdateTime());
		}
		else{
			return DBUtils.getInstance().execute(sql, 
					t.getName(),
					t.getParentId(),
					t.getParentIds(),
					t.getCreateTime(),
					t.getUpdateTime());
		}
		
	}

	@Override
	public void update(Category t) {
		String sql = "UPDATE sys_category SET "
				+ "name=?,parent_id=?,parent_ids=?,create_time=?,update_time=? "
				+ " WHERE id=?";
		 t.preUpdate();
		 DBUtils.getInstance().execute(sql, 
					t.getName(),
					t.getParentId(),
					t.getParentIds(),
					t.getCreateTime(),
					t.getUpdateTime(),
					t.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM sys_category WHERE id=?";
		DBUtils.getInstance().execute(sql, id);
	}

}
