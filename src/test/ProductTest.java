package test;

import java.util.List;

import org.junit.Test;

import com.ssf.model.Product;
import com.ssf.service.ProductService;

public class ProductTest {
	ProductService productService = new ProductService();
	
	@Test
	public void productTest(){
		List<Product> lists = productService.findAllSub(1);
		
		for (Product product : lists) {
			System.out.println(product);
		}
	}
}
