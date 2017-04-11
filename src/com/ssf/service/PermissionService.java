package com.ssf.service;

import java.util.ArrayList;
import java.util.List;

import com.ssf.dao.DepartmentDao;
import com.ssf.dao.EmployeeDao;
import com.ssf.dao.PermissionDao;
import com.ssf.model.Department;
import com.ssf.model.Employee;
import com.ssf.model.Permission;

public class PermissionService {

	DepartmentDao departmentDao = new DepartmentDao();
	PermissionDao permissionDao = new PermissionDao();
	EmployeeDao   employeeDao = new EmployeeDao();
	//前台往后台传参数一般是对象的id，后台直接从数据库取一般就是取对象出来
	
	/**
	 * 给定员工和资源，查询其所具有该资源的权限列表
	 * @param empid 员工id
	 * @param resid 资源id
	 */
	public void findPermissionsByEmpAndRes(int empid,int resid ){
		// 1.查询员工属于哪个部门#empid
		Department d = departmentDao.getDepartmentByEmpid(empid);
		
		permissionDao.findPermissionsByDeptAndRes(d.getId(), resid);
	}
	/**
	 * 1. 请设计关系型数据库表结构及编写相关代码，实现『赋予某个部门的所有上级具有某项权限』。
	   Tips：『部门的所有上级』是指部门及所有上级部门的直属上级的集合
	 */
	public String addPermissionToLeaders(int deptId,int permissonId){
		//1.判断这个id的部门对象存在不存在
		Department d = departmentDao.findById(deptId);
		if(d==null){
			return "该部门不存在";
		}
		Permission p= permissionDao.findById(permissonId);
		if(p ==null){
			return "该权限不存在";
		}
		
		List<Department> parents = departmentDao.getParents(d);
		
		//2.根据部门id获取所有的上级领导
		List<Employee> leaders = new ArrayList<Employee>();
		for (Department parent : parents) {
			int empid = parent.getLeaderId();
			if(empid>0)
			{
				Employee e = employeeDao.findById(empid);
				if(e!=null) leaders.add(e);
			}
		}
		//3.给所有的leaders赋予权限
		for (Employee employee : leaders) {//加事务
			permissionDao.addPermissionToEmp(employee, p);
		}
		return "";
	}
}
