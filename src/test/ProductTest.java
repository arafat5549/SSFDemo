package test;

import java.util.List;

import org.junit.Test;

import com.ssf.model.Product;
import com.ssf.service.ProductService;

/**
 * 单元测试相关类-商品部分的测试
 * @author wyy
 * 2017年3月29日
 *
 */
public class ProductTest {
	ProductService productService = new ProductService();
	
	@Test
	public void baseTest(){
		List<Product> lists = productService.findAllSub(1);
		
		for (Product product : lists) {
			System.out.println(product);
		}
	}
}
