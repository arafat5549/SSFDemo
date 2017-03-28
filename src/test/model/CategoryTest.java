package test.model;

import java.util.List;

import org.junit.Test;

import com.ssf.dao.CategoryDao;
import com.ssf.model.Category;
import com.ssf.service.CategoryService;

public class CategoryTest {
    CategoryService  categoryService = new CategoryService();
    CategoryDao categoryDao = new CategoryDao();
	private static int ID = 999999;
	
	@Test
	public void otherTest(){
		//int count = categoryService.getListCount();
		//System.out.println("count="+count);
		//List<Category> lists = categoryService.listPage(100, 0);
		//System.out.println(lists);
		
//		List<Category> lists = categoryService.findFirstCategoryList();
//		for (Category category : lists) {
//			System.out.println(category);
//			for (Category child :category.getChilds()){
//				System.out.println(child);
//				
//				for (Category grandchild :child.getChilds()){
//					System.out.println(grandchild);
//				}
//			}
//			System.out.println("--------");
//		}
		
		List<Category> lists = categoryDao.getChildsById(1);
		for (Category category : lists)
		{
			System.out.println(category);
		}
	}
	
	@Test
	public void saveTest(){
		Category p = new Category();
		p.setId(ID);
		p.setName("测试分类");
		categoryService.save(p);
	}
	
	@Test
	public void updateTest(){
		Category p = categoryService.findById(ID);
		System.out.println(p);
		p.setParentId(1);
		categoryService.update(p);
	}
	
	@Test
	public void deleteTest(){
		//categoryService.delete(ID);
	}
}
