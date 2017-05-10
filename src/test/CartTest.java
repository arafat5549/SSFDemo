package test;

import org.junit.Test;

import com.ssf.dao.CartDao;

public class CartTest {

	CartDao cartDao = new CartDao();
	
	@Test
	public void baseTest(){
		int maxid = cartDao.findMaxId();
		System.out.println(maxid);
	}
}
