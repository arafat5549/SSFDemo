package user;

import java.net.URL;

import org.junit.Test;

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
		//1.读取properties文件
		URL url = getClass().getClassLoader().getResource("");
		System.out.println(url);
	}
}
