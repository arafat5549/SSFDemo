package test;

import java.util.List;

import org.junit.Test;

import com.ssf.dao.EmployeeDao;
import com.ssf.dao.PermissionDao;
import com.ssf.model.Employee;
import com.ssf.service.PermissionService;

public class PermissionTest {

	PermissionService permissionService = new PermissionService();
	PermissionDao permissionDao = new PermissionDao();
	EmployeeDao employeeDao = new EmployeeDao();
	@Test
	public void baseTest(){
		Employee e =employeeDao.findById(1);
		List<Integer> ids = permissionDao.findAllEmpPermissions(e);
		System.out.println(ids);
		permissionService.addPermissionToLeaders(1111, 1);
	}
	
	//addPermissionToLeaders
}
