package test;

import java.util.List;

import org.junit.Test;

import com.ssf.dao.EmployeeDao;
import com.ssf.dao.PermissionDao;
import com.ssf.model.Permission;
import com.ssf.service.PermissionService;

public class PermissionTest {

	PermissionService permissionService = new PermissionService();
	PermissionDao permissionDao = new PermissionDao();
	EmployeeDao employeeDao = new EmployeeDao();
	@Test
	public void baseTest(){
//		Employee e =employeeDao.findById(1);
//		List<Integer> ids = permissionDao.findAllEmpPermissions(e);
//		System.out.println(ids);
//		permissionService.addPermissionToLeaders(1111, 1);
		int empId = 1;
		int deptId = 1;
		int resId = 1;
//		List<Permission> lists = permissionDao.findPermissionsByEmpAndRes(empId,resId);
//		for (Permission permission : lists) {
//			System.out.println(permission);
//		}
//		System.out.println("---------------------------------");
//		List<Permission> lists2 = permissionDao.findPermissionsByDeptAndRes(deptId,resId);
//		for (Permission permission : lists2) {
//			System.out.println(permission);
//		}
		permissionService.findPermissionsByEmpAndRes(empId, resId);
	}
	
	//addPermissionToLeaders
}
