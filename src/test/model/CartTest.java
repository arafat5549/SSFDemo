package test.model;

import java.util.List;

import org.junit.Test;

import com.ssf.dao.CartItemDao;
import com.ssf.model.Cart;
import com.ssf.model.CartItem;
import com.ssf.service.CartService;

public class CartTest {

	CartService cartService = new CartService();
	
	CartItemDao cartItemDao = new CartItemDao();
	@Test
	public void cartTest(){
		int userid = 1;
		Cart cart = cartService.findByUserId(userid);
		System.out.println(cart);
		System.out.println(cart.getItems());
	}
	
	@Test
	public void addToCartTest(){
		int userid = 1;
		Cart cart = cartService.findByUserId(userid);
		
		int pid = 212121;
		String ret = cartService.addToCart(cart, pid, 1);
		System.out.println(ret);
	}
}
