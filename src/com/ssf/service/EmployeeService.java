package com.ssf.service;

import java.util.ArrayList;
import java.util.List;

import com.ssf.dao.DepartmentDao;
import com.ssf.dao.EmployeeDao;
import com.ssf.model.Department;
import com.ssf.model.Employee;

public class EmployeeService {
	EmployeeDao employeeDao = new EmployeeDao();
	DepartmentDao departmentDao = new DepartmentDao();
	/**
	 * 3. 编写程序实现：给定员工和部门，给出员工是否在这个部门。
	 */
	public boolean isInDeptByEmpAndDept(Integer empId,Integer deptId){
	
		Employee emp = employeeDao.findById(empId);
		if(emp == null){
			return false;
		}
		Department dept =departmentDao.findById(deptId);
		if(dept == null){
			return false;
		}
		//获取某个部门的所有子部门
		List<Department> lists = new ArrayList<Department>();
		lists.add(dept);
		departmentDao.getChilds(dept, lists);
		
		for (Department department : lists) {
			if(emp.getDeptId() == department.getId()){
				return true;
			}
		}
		
		return false;
	}
}
