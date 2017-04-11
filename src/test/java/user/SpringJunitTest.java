package user;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ssf.dao.ICategoryDao;
import com.ssf.dao.IUserDao;
import com.ssf.model.Category;
import com.ssf.model.User;
import com.ssf.service.AccountService;
import com.ssf.service.UserService;
import com.ssf.service.back.AdminService;
import com.ssf.utils.JsonMapper;


@RunWith(SpringJUnit4ClassRunner.class)//由Junit单元测试整合Spring
@ContextConfiguration("classpath:spring-context.xml")
public class SpringJunitTest {

	@Autowired
	IUserDao userDao;
	@Autowired
	ICategoryDao categoryDao;
	
	@Autowired
	UserService  userService;
	@Autowired
	AdminService adminService;
	
	@Autowired
	AccountService accountService;
	
	List<Map<String, Object>> treeData(){
		String extId = "";
		String isShowHide = null;
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Category> list = categoryDao.findAll();
		for (int i=0; i<list.size(); i++){
			Category e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				if(isShowHide != null && isShowHide.equals("0") ){//&& e.getIsShow().equals("0")
					continue;
				}
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	@Test
	public void jsonTest(){
//		List<Map<String, Object>> list = treeData();
//		String json = JsonMapper.getInstance().toJson(list);
//		System.out.println(json);
//		
//		//把Java对象转化为JSON对象
//		Category c = categoryDao.findById(1);
//		json = JsonMapper.getInstance().toJson(c);
//		System.out.println("JSON:"+json);
		
		List<Category> list = categoryDao.findAll();
		String json = JsonMapper.getInstance().toJson(list);
		System.out.println("JSON:"+json);
		//JSON:{"id":1,"name":"个人用品/服饰","parentId":0,"parentIds":"0,1","createTime":1491874738000,"updateTime":1491874738000}
		
//		System.out.println(c);
//		
//		List<Category> l1 = categoryDao.findAllList();
//		for (Category category : l1) {
//			System.out.println(category);
//		}
//		System.out.println("------------------------");
//		List<Category> l2 = categoryDao.findAll();
//		for (Category category : l2) {
//			System.out.println(category);
//		}
	}
	
	
	
	
	@Test  
	public void userTest(){
		
		
		
		//userDao.sayHello();
		//System.out.println(2&3);
		//System.out.println(userDao.findAll());
		
		User user = userDao.findById(1);
		System.out.println(user);
//		
//		User user2 = userDao.findById(1);
//		System.out.println(user == user2); //两个相等说明使用了缓存，只会产生一句sql语句
	
		//userService.login(user);s
		//adminService.adminLogin(user);
//		userService.transferAccount(16029, 16030, new BigDecimal(100));
		
//		try {
//			accountService.transferAccount(16029, 16030, new BigDecimal(1000));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
