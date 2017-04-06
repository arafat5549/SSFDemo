package user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssf.dao.IUserDao;


@RunWith(SpringJUnit4ClassRunner.class)//由Junit单元测试整合Spring
@ContextConfiguration("classpath:spring-context.xml")
public class SpringJunitTest {

	@Autowired
	IUserDao userDao;
	//
	//010
    //011
	@Test  
	public void userTest(){
		//userDao.sayHello();
		System.out.println(2&3);
		System.out.println(userDao.findAll());
	}
}
