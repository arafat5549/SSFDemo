package test.model;

import java.util.List;

import org.junit.Test;

import com.ssf.dao.UserDao;
import com.ssf.model.User;
import com.ssf.service.UserService;
import com.ssf.utils.Global;

/**
 * 用户数据库Dao和Service测试
 * @author wyy
 * 2017年2月22日
 *
 */
public class UserTest 
{
	UserService userService = new UserService();
	UserDao userDao = new UserDao();
	private static int ID = 999999;
	
	@Test
	public void otherTest(){
//		int count = userService.getListCount();
//		System.out.println("count="+count);
//		List<User> lists = userService.listPage(100, 0);
//		System.out.println(lists);
//		
//		User user = userService.findByUserName(null);
//		System.out.println(user);
		
		//Global.validate("", null);
		
		userDao.findById(-111);
	}
	
	@Test
	public void saveTest(){
		User user = new User();
		user.setId(ID);
		user.setUsername("测试用户");
		user.setPassword("123456");
		//user.setAvatarUrl(avatarUrl);
		userService.save(user);
	}
	
	@Test
	public void updateTest(){
		User user = userService.findById(ID);
		System.out.println(user);
		user.setAvatarUrl("update.jpg");
		userService.update(user);
	}
	
	@Test
	public void deleteTest(){
		userService.delete(ID);
	}
}
