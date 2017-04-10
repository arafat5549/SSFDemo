package com.ssf.dao;

import java.util.List;

import com.ssf.model.Permission;
import com.ssf.utils.DBUtils;

/**
 * 权限DAO
 * @author wyy
 * 2017年4月10日
 *
 */
public class PermissionDao {
	private static final String COLUMN = 
			  " a.id,"
			+ " a.resource_id AS 'resourceId',"
			+ " a.action, "
			+ " a.create_time AS 'createTime', "
		    + " a.update_time AS 'updateTime' ";
	
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
}
