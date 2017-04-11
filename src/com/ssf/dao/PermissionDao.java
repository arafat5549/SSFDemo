package com.ssf.dao;

import java.util.List;

import com.ssf.model.Employee;
import com.ssf.model.Permission;
import com.ssf.utils.DBUtils;

/**
 * 权限DAO
 * @author wyy
 * 2017年4月10日
 *
 */
public class PermissionDao implements BaseDao<Permission>{
	private static final String COLUMN = 
			  " a.id,"
			+ " a.resource_id AS 'resourceId',"
			+ " a.action, "
			+ " a.create_time AS 'createTime', "
		    + " a.update_time AS 'updateTime' ";
	/**
	 * 获取员工的所有权限Id
	 */
	public List<Integer> findAllEmpPermissions(Employee e){
		
		
		String sql = "SELECT permission_id FROM sys_emp_permission WHERE emp_id="+e.getId();
		return DBUtils.getInstance().execute_list(sql);
	}
	
	/**
	 * 给员工赋予权限
	 * 1.为了不重复赋予权限，每次添加权限我都先从数据库里面比对一下
	 */
	public boolean addPermissionToEmp(Employee e,Permission p){
		//1.为了不重复赋予权限，每次添加权限我都先从数据库里面比对一下
		List<Integer> ids = findAllEmpPermissions(e);
		boolean flag = ids.contains(p.getId());//存在这个权限id
		if(flag){
			return false;
		}
		String sql = "INSERT INTO sys_emp_permission(emp_id,permission_id) VALUES (?,?)";
		return DBUtils.getInstance().execute(sql, e.getId(),p.getId());
	}
	
	/**
	 * 2.查询部门所拥有的所有权限
	 * 并过滤对应资源的权限
	 * @param deptId
	 * @param resId
	 * @return
	 */
	public List<Permission> findPermissionsByDeptAndRes(int deptId,int resId)
	{
		String sql =
			"SELECT "+COLUMN+" FROM sys_permission p"
			+" JOIN sys_dept_permission dp ON p.id = dp.permission_id"
			+" JOIN sys_department d ON dp.dept_id = d.id"
			+" WHERE d.id = ? AND p.resource_id=?";
		
		return DBUtils.getInstance().listBean(sql, Permission.class, deptId,resId);
	}

	@Override
	public List<Permission> findAll() {
		return null;
	}

	@Override
	public Permission findById(Integer id) {
		String sql = "SELECT " + COLUMN +" FROM sys_permission a WHERE a.id=?";
		return DBUtils.getInstance().queryBean(sql, Permission.class,id);
	}

	@Override
	public boolean save(Permission t) {
		String sql = "INSERT INTO sys_permission(resource_id,action,create_time,update_time) VALUES (?,?,?,?)";
		return DBUtils.getInstance().execute(sql, 
				t.getResourceId(),
				t.getAction(),
				t.getCreateTime(),
				t.getUpdateTime());
	}

	@Override
	public void update(Permission t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer findMaxId() {
		// TODO Auto-generated method stub
		return null;
	}
}
