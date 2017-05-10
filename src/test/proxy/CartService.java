package test.proxy;

import test.proxy.IOCBeanFactory;

import com.ssf.dao.CartDao;
import com.ssf.model.Cart;

public class CartService {
	
	CartDao cartDao;// = new CartDao();
	
	public CartDao getCartDao(){
		if(cartDao == null){
			cartDao = (CartDao)IOCBeanFactory.getInstance().getBean(CartDao.class);//new CartDao();
		}
		return cartDao;
	}
	
	public boolean save(Cart t){
		return getCartDao().save(t);
	}
	/**
	 * 获取可用的Id
	 * @return
	 */
	public Integer findMaxId(){
		return getCartDao().findMaxId() + 1;
	}
	
	/**
	 * 取用户关联的购物车
	 */
	public Cart findCartByUserId(Integer userId){
		return getCartDao().findCartByUserId(userId);
	}
	
	/**
	 * 添加入购物车
	 */
	
	
}
