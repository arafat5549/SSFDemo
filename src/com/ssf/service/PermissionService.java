package com.ssf.service;

import com.ssf.dao.DepartmentDao;
import com.ssf.dao.PermissionDao;
import com.ssf.model.Department;

public class PermissionService {

	DepartmentDao departmentDao = new DepartmentDao();
	PermissionDao permissionDao = new PermissionDao();
	//给定员工和资源，查询其所具有该资源的权限列表
	//int empid =1,int resid =1
	public void test(int empid,int resid ){
		// 1.查询员工属于哪个部门#empid
		Department d = departmentDao.getDepartmentByEmpid(empid);
		
		permissionDao.findPermissionsByDeptAndRes(d.getId(), resid);
	}
	
}
