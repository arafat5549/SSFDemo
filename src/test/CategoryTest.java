package test;

import java.util.List;

import org.junit.Test;

import com.ssf.dao.CategoryDao;
import com.ssf.model.Category;

public class CategoryTest {
    CategoryDao categoryDao= new CategoryDao();
	@Test
	public void baseTest(){
//		Category cate = new Category();
//		cate.setId(2);
//		cate.setName("测试分类2");
//		cate.setParentId(1);
//		cate.setParentIds("0,1");
//		
//		categoryDao.save(cate);
		
		List<Category> lists = categoryDao.findAll();
		System.err.println(lists);
		
		Category t = categoryDao.findById(1);
		System.out.println(t);
		
		t.setName("哈哈哈");
		categoryDao.update(t);
		
		categoryDao.delete(t.getId());
	}
	@Test
	public void categoryTest(){
		List<Category> lists = null;
		//lists =	categoryDao.findFirstCategorys();
		lists = categoryDao.findAllCategorysById(1);
		for (Category category : lists) {
			System.out.println(category);
		}
		
		
	}
}
