package user;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssf.dao.IUserDao;
import com.ssf.dao.impl.UserDaoImpl;
import com.ssf.service.UserService;

public class UserTest {

	IUserDao userDao = new UserDaoImpl();
	
	@Test
	public void userTest(){
		userDao.sayHello();
		//
		//1.加载IOC容器
		ApplicationContext ctx =new ClassPathXmlApplicationContext("spring-context.xml");
		
		UserDaoImpl d = ctx.getBean(UserDaoImpl.class);
		d.sayHello();
//		//根据id来获取
//		UserDaoImpl d2 = ctx.getBean("userDao",UserDaoImpl.class);
//		d2.sayHello();
		
		UserService s = ctx.getBean("userService",UserService.class);
		s.sayHello2();
	}
}
