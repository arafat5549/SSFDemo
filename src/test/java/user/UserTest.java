package user;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssf.utils.DBUtils;
import com.ssf.web.AccountController;

public class UserTest {

//	IUserDao userDao = new UserDaoImpl();
	
	@Test
	public void userTest(){
//		userDao.sayHello();
//		//
//		//1.加载IOC容器
//		ApplicationContext ctx =new ClassPathXmlApplicationContext("spring-context.xml");
//		
//		UserDaoImpl d = ctx.getBean(UserDaoImpl.class);
//		d.sayHello();
////		//根据id来获取
////		UserDaoImpl d2 = ctx.getBean("userDao",UserDaoImpl.class);
////		d2.sayHello();
//		
//		UserService s = ctx.getBean("userService",UserService.class);
//		s.sayHello2();
	}
	
	@Test
	public void readConf(){
//		//1.读取properties文件
//		URL url = getClass().getClassLoader().getResource("");
//		System.out.println(url);
	}
	
	@Test
	public void applicationContextTest(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
		
		AccountController ac = (AccountController)ctx.getBean("accountController");
		ac.test();
		//System.out.println(ac);
		
//		AccountController ac2 =ctx.getBean(AccountController.class);
//		System.out.println(ac2);
//		
//		DBUtils ac3 =ctx.getBean(DBUtils.class);
//		System.out.println(ac3);
	}
}
