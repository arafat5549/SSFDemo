package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ssf.dao.DepartmentDao;
import com.ssf.dao.EmployeeDao;
import com.ssf.model.Department;
import com.ssf.model.Employee;
import com.ssf.service.EmployeeService;

public class EmployeeTest {
    EmployeeDao employeeDao = new EmployeeDao();
    DepartmentDao departmentDao = new DepartmentDao();
    
    EmployeeService employeeService = new EmployeeService();
	@Test
	public void daoTest(){
//		List<Employee> lists = employeeDao.findAll();
//		System.out.println(lists);
		
//		for (int i = 0; i < 10; i++) {
//			Employee e = new Employee();
//			e.setName("测试员工"+i);
//			e.setDeptId(1);
//			employeeDao.save(e);
//		}
		
		boolean flag = employeeService.isInDeptByEmpAndDept(3, 1);
		System.out.println(flag);
	}
	
	@Test
	public void Mytest(){
		Department dept = departmentDao.findById(11);
		
		List<Department> lists = new ArrayList<Department>();
		lists.add(dept);
		departmentDao.getChilds(dept,lists);
		
		for (Department department : lists) {
			System.out.println(department);
		}
		System.out.println("---------------第二种做法-------------------");
		
		List<Department> lists2 = departmentDao.getChilds2(dept);
		for (Department department : lists2) {
			System.out.println(department);
		}
	}
}
