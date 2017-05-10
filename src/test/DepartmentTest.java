package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ssf.dao.DepartmentDao;
import com.ssf.model.Department;

public class DepartmentTest {
	DepartmentDao departmentDao= new DepartmentDao();
	
	@Test
	public void baseTest(){
		List<Department> lists = new ArrayList<Department>();
		Department d = departmentDao.findById(1111);
		departmentDao.getParents(d,lists);
		lists.add(d);
		for (Department department : lists) {
			System.out.println(department);
		}
		System.out.println("------------------------");
		
		List<Department> lists2 = departmentDao.getParents(d);
		for (Department department : lists2) {
			System.out.println(department);
		}
	}
}
