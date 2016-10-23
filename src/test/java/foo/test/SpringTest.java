package foo.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import foo.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-mybatis.xml")
public class SpringTest 
{
	@Resource	
	private IUserService userService;
	
	@Test
	public void UserServiceTest()
	{
		userService.update(1, "TestUser");
		
	}
}
