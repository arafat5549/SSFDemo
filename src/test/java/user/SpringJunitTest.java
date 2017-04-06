package user;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssf.dao.IUserDao;
import com.ssf.model.User;
import com.ssf.service.AccountService;
import com.ssf.service.UserService;
import com.ssf.service.back.AdminService;


@RunWith(SpringJUnit4ClassRunner.class)//由Junit单元测试整合Spring
@ContextConfiguration("classpath:spring-context.xml")
public class SpringJunitTest {

	@Autowired
	IUserDao userDao;
	
	@Autowired
	UserService  userService;
	@Autowired
	AdminService adminService;
	
	@Autowired
	AccountService accountService;
	//
	//010
    //011
	@Test  
	public void userTest(){
		//userDao.sayHello();
		//System.out.println(2&3);
		//System.out.println(userDao.findAll());
		
		User user = userDao.findById(1);
//		System.out.println(user);
//		
//		User user2 = userDao.findById(1);
//		System.out.println(user == user2); //两个相等说明使用了缓存，只会产生一句sql语句
	
		//userService.login(user);s
		//adminService.adminLogin(user);
		userService.transferAccount(16029, 16030, new BigDecimal(100));
		
//		try {
//			accountService.transferAccount(16029, 16030, new BigDecimal(1000));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
