package test;

import org.junit.Test;

import com.ssf.dao.UserDao;
import com.ssf.model.User;

public class UserTest {

	UserDao userDao = new UserDao();
	
	@Test
	public void select(){
		User exist = userDao.findByName("1' or '1=1");
		System.out.println("findByName="+exist);
		
		exist = userDao.findByName_stmt("1' or '1=1");
		System.out.println("findByName_pre="+exist);
	}
	
	@Test
	public void  save(){
		User user =new User();
		user.setUsername("king");
		user.setPassword("123");
		
		boolean flag = userDao.save(user);
		System.out.println(flag);
	}
	
}
