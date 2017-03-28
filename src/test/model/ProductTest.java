package test.model;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.ssf.model.Category;
import com.ssf.model.Product;
import com.ssf.service.CategoryService;
import com.ssf.service.ProductService;

public class ProductTest {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    
	private static int ID = 1;
	
	@Test
	public void otherTest(){
//		int count = productService.getListCount();
//		System.out.println("count="+count);
//		List<Product> lists = productService.listPage(100, 0);
//		System.out.println(lists);
		
		//List<Category> lists = categoryService.findFirstCategoryList();
		
		//productService.listPage(limit, offset, cid)
		
		List<Product> lists = productService.getProductListByCid(69);
		System.out.println(lists.size());
		for (Product product : lists) {
			System.out.println(product);
		}
	}
	
	@Test
	public void saveTest(){
		
		Product p = new Product();
		//p.setId(id);
		p.setName("测试商品");
		p.setPrice(new BigDecimal(1.12));
		productService.save(p);
	}
	
	@Test
	public void updateTest(){
		Product p = productService.findById(ID);
		System.out.println(p);
		
		p.setCategoryId(1);
		productService.update(p);
	}
	
	@Test
	public void deleteTest(){
		//productService.delete(ID);
	}
}
