package com.ssf.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssf.model.Department;
import com.ssf.utils.DBUtils;

/**
 * 部门Dao
 * @author wyy
 * 2017年3月29日
 *
 */
public class DepartmentDao implements BaseDao<Department>{
	
	//指定列查询 和 别名AS
	private static final String COLUMN = 
			  " a.id,"
			+ " a.name,"
			+ " a.parent_id AS 'parentId', "
			+ " a.parent_ids AS 'parentIds',"
			+ " a.leader_id AS 'leaderId'";
	
	
	
	/**
	 * 根据员工id获取部门
	 */
	public Department getDepartmentByEmpid(int empid){
		//String sql = "SELECT " + COLUMN +" FROM sys_department a WHERE a.parent_id=?";
		String sql = 
				"SELECT "+COLUMN+" FROM sys_department a"
				+" JOIN sys_employee e"
				+" ON a.id = e.dept_id AND e.dept_id=?";
		return DBUtils.getInstance().queryBean(sql, Department.class, empid);
	}
	
	/**
	 * 1.获取所有的父部门
	 */
	public void getParents(Department dept,List<Department> lists){
		String sql = "SELECT " + COLUMN +" FROM sys_department a WHERE a.id=?";
		List<Department> childs = DBUtils.getInstance().listBean(sql, Department.class,dept.getParentId());
		for (Department department : childs) {
			getParents(department,lists);
		}
		lists.addAll(childs);
	}
	/**
	 * 2.获取所有父部门
	 * 避免频繁的查询数据库
	 */
	public List<Department> getParents(Department dept){
		List<Department> lists = new ArrayList<Department>();
		String parents = dept.getParentIds();
		String []ids = parents.split(",");
		for (String idStr : ids) {
			int id = Integer.parseInt(idStr);
			if(id >0){
				Department d = findById(id);
				if(d!=null)
					lists.add(d);
			}
		}
		return lists;
	}
	
	/**
	 * 1.无限级分类  第一种做法#递归
	 * 获取所有的子部门
	 * @param dept  部门对象
	 * @param lists
	 */
	public void getChilds(Department dept,List<Department> lists){//1
		
		String sql = "SELECT " + COLUMN +" FROM sys_department a WHERE a.parent_id=?";
		List<Department> childs = DBUtils.getInstance().listBean(sql, Department.class,dept.getId());
		for (Department department : childs) {
			getChilds(department,lists);
		}
		lists.addAll(childs);
	}
    /**
     * 2. 第二种做法 parentids 记录所有父类的id  模糊查询
     * 获取所有的子部门
     * @param dept  部门对象
     * @return
     */
	public List<Department> getChilds2(Department dept){
		String sql = "SELECT " + COLUMN +" FROM sys_department a WHERE a.parent_ids LIKE '"+dept.getParentIds()+"%'";
		return DBUtils.getInstance().listBean(sql, Department.class);
	}
	
	
	@Override
	public List<Department> findAll() {
		String sql = "SELECT " + COLUMN +" FROM sys_department a";
		return DBUtils.getInstance().listBean(sql, Department.class);
	}

	@Override
	public Department findById(Integer id) {
		String sql = "SELECT " + COLUMN +" FROM sys_department a WHERE a.id=?";
		return DBUtils.getInstance().queryBean(sql, Department.class,id);
	}

	@Override
	public boolean save(Department t) {
		String sql = "INSERT INTO sys_department(name,parent_id,parent_ids,leader_id) "
				+ "VALUES(?,?,?,?)";
		return DBUtils.getInstance().execute(sql, 
				t.getName(),
				t.getParentId(),
				t.getParentIds(),
				t.getLeaderId());
	}

	@Override
	public void update(Department t) {
		String sql = "UPDATE sys_department SET name=?,parent_id=?,parent_ids=?,leader_id=? "
				+ " WHERE id =?";
		DBUtils.getInstance().execute(sql, 
				t.getName(),
				t.getParentId(),
				t.getParentIds(),
				t.getLeaderId(),
				t.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM sys_department WHERE id = ?";
		DBUtils.getInstance().execute(sql, id);
	}
	@Override
	public Integer findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

}
