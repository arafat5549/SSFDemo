package test;

import org.junit.Test;

import com.ssf.dao.UserDao;
import com.ssf.model.User;

/**
 * 单元测试相关类-用户部分的测试
 * @author wyy
 * 2017年3月29日
 *
 */
public class UserTest {

	UserDao userDao = new UserDao();
	
	/**
	 * SQL注入
	 */
	@Test
	public void sqlInjectTest(){
		User exist = userDao.findByName("1' or '1=1");
		System.out.println("findByName="+exist);
		
		exist = userDao.test_findByName_stmt("1' or '1=1");
		System.out.println("findByName_pre="+exist);
	}
	
	@Test
	public void  baseTest(){
		User user =new User();
		user.setUsername("king");
		user.setPassword("123");
		
		boolean flag = userDao.save(user);
		System.out.println(flag);
	}
	
}
