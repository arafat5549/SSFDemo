package com.ssf.dao;

import java.util.List;

import com.ssf.model.Employee;
import com.ssf.utils.DBUtils;

/**
 * 
 * @author wyy
 * 2017年3月28日
 *
 */
public class EmployeeDao implements BaseDao<Employee>{
	
	private static final String COLUMN = 
			  " a.id,"
			+ " a.name,"
			+ " a.dept_id AS 'deptId' ";
	
	@Override
	public List<Employee> findAll() {
		String sql = "SELECT " + COLUMN +" FROM sys_employee a";
		return DBUtils.getInstance().listBean(sql, Employee.class);
	}

	@Override
	public boolean save(Employee t) {
		String sql = "INSERT INTO sys_employee(name,dept_id) VALUES (?,?)";
		return DBUtils.getInstance().execute(sql, 
				t.getName(),
				t.getDeptId());
	}

	@Override
	public void update(Employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee findById(Integer id) {
		String sql = "SELECT " + COLUMN +" FROM sys_employee a WHERE a.id=?";
		return DBUtils.getInstance().queryBean(sql, Employee.class,id);
	}

}
