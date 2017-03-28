package com.ssf.dao;

import java.util.List;

import com.ssf.model.Department;
import com.ssf.utils.DBUtils;

public class DepartmentDao implements BaseDao<Department>{
	
	private static final String COLUMN = 
			  " a.id,"
			+ " a.name,"
			+ " a.parent_id AS 'parentId', "
			+ " a.parent_ids AS 'parentIds'";
	
	//1.无限级分类  第一种做法#递归
	public void getChilds(Department dept,List<Department> lists){//1
		
		String sql = "SELECT " + COLUMN +" FROM sys_department a WHERE a.parent_id=?";
		List<Department> childs = DBUtils.getInstance().listBean(sql, Department.class,dept.getId());
		for (Department department : childs) {
			getChilds(department,lists);
		}
		lists.addAll(childs);
	}
    //2. 第二种做法 parenids 记录所有父类的id  模糊查询
	public List<Department> getChilds2(Department dept){
		String sql = "SELECT " + COLUMN +" FROM sys_department a WHERE a.parent_ids LIKE '"+dept.getParentIds()+"%'";
		return DBUtils.getInstance().listBean(sql, Department.class);
	}
	
	
	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department findById(Integer id) {
		String sql = "SELECT " + COLUMN +" FROM sys_department a WHERE a.id=?";
		return DBUtils.getInstance().queryBean(sql, Department.class,id);
	}

	@Override
	public boolean save(Department t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Department t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
