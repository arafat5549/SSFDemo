package com.ssf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssf.model.Category;
import com.ssf.utils.DBUtils;

public class CategoryDao implements BaseDao<Category>{
    
	//不用通配符来做
	private static String columns = 
			" a.id, "
			+ "a.name,"
			+ "a.parent_id AS 'parentId',"//数据库命名规范和JavaBean命名规范
			+ "a.parent_ids AS 'parentIds',"
			+ "a.create_time AS 'createTime' "
			;
	
	
	
	//根据分类id获取所有的分类
	public List<Category> getChildsById(int cid){
		List<Category> lists = new ArrayList<Category>();
		Category cate = findById(cid);
		if(cate == null){
			return lists;
		}
		String sql = "SELECT " + columns +" FROM sys_category a WHERE a.parent_ids LIKE ?";
		//System.out.println(sql+","+cate.getParentIds());
		try {
			lists = DBUtils.getInstance().listBean(sql, Category.class,cate.getParentIds()+"%");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
	//遍历多级分类-无线级分类
	void listChild(String sql,List<Category> lists) throws SQLException{
		for (Category parent : lists)
		{
			List<Category> childs = DBUtils.getInstance().listBean(sql, Category.class,parent.getId());
			parent.setChilds(childs);
			listChild(sql, childs);
		}
	}
	//获取所有带子分类的一级分类
	public List<Category> findFirstCategoryList(){
		//所有父类id的分类 代表他没有父类
		String sql ="SELECT "+columns+" FROM sys_category a WHERE a.parent_id = 0";
		try {
			List<Category> lists = DBUtils.getInstance().listBean(sql, Category.class);
			
			//获取所有子分类设置进去
			sql ="SELECT "+columns+" FROM sys_category a "+
			"JOIN sys_category b "+
			"ON b.id = a.parent_id "+
			"WHERE b.id = ?";
			
			listChild(sql, lists);
//			for (Category parent : lists) {
//				List<Category> childs = DBUtils.getInstance().listBean(sql, Category.class,parent.getId());
//				parent.setChilds(childs);
//				for(Category c : childs)
//				{
//					List<Category> grands = DBUtils.getInstance().listBean(sql, Category.class,c.getId());
//					c.setChilds(grands);
//					
//				}
//			}
			return lists;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Category>();
	}
	//----------------------------------
	@Override()
	public List<Category> findAll() {
		String sql ="SELECT "+columns+" FROM sys_category a" ;
		try {
			return DBUtils.getInstance().listBean(sql, Category.class);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Category>();
	}

	@Override
	public Category findById(int id) {
		String sql ="SELECT "+columns+" FROM sys_category a WHERE id=?";
		try {
			return DBUtils.getInstance().queryBean(sql, Category.class,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> listPage(int limit, int offset) {
		String sql ="SELECT "+columns+" FROM sys_category a " 
				+ "limit "+offset+","+limit+"";
		try {
			return DBUtils.getInstance().listBean(sql, Category.class);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Category>();
	}

	@Override
	public boolean save(Category t) {
		String idStr = "";
		String idHolder ="";
		boolean flag_id = t.getId() > 0;
		if(flag_id){
			idStr = "id,";
			idHolder = "?,";
		}
		
		String sql = "INSERT INTO sys_category"
				+ "("+idStr+"name,parent_id,create_time) "
				+ "VALUES("+idHolder+"?,?,?)";
		try {
			
			t.preInsert();
			List<Object> params = new ArrayList<Object>();
			if(flag_id)
				params.add(t.getId());
			params.add(t.getName());
			params.add(t.getParentId());
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
	public void update(Category t) {
		String sql = "UPDATE sys_category SET name=?,parent_id=?,create_time=? WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, 
					t.getName(),
					t.getParentId(),
					t.getCreateTime(),
					t.getId());
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM sys_category WHERE id=?";
		try {
			DBUtils.getInstance().executeUpdate(sql, id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
