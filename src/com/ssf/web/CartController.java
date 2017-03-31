package com.ssf.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.dao.ProductDao;
import com.ssf.model.Cart;
import com.ssf.model.CartItem;
import com.ssf.service.CartItemService;

/**
 * 购物车WEB层
 * 
 * @author wyy
 * 2017年3月30日
 *
 */
@SuppressWarnings("serial")
public class CartController extends HttpServlet{

	CartItemService cartItemService = new CartItemService();
	ProductDao productDao = new ProductDao();
	
	public static final String VIEW_PATH = "/WEB-INF/views/cart/";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//TODO 1.判断你登录了没有
		
		
		String method = req.getParameter("method");
		if("add".equals(method)){//加入购物车
			String pidStr   = req.getParameter("pid");
			String countStr = req.getParameter("count");
			int pid = 0;
			int count = 0;
			if(pidStr!=null){
				pid= Integer.parseInt(pidStr);
			}
			if(countStr!=null){
				count = Integer.parseInt(countStr);
			}
			//
			Cart cart = (Cart)req.getSession().getAttribute("session_cart");
			
			//System.out.println(cart);
			if(cart!=null){
				int cartId = cart.getId();
				
				 CartItem item = cart.findByProductId(pid);//为什么这时候从缓存获取而不是数据库
				 //cartItemService.findByCartIdAndProductId(cartId, pid);
				 if(item == null ){//1.item还没有 需要创建
					 item = new CartItem();
					 item.setId(cartItemService.findMaxId());
					 item.setCartId(cartId);
					 item.setCount(1);
					 item.setProductId(pid);
					 item.setProduct(productDao.findById(pid));
					 
					 cartItemService.save(item);//更新数据库
					 cart.getItems().add(item);//更新你的缓存
				 }
				 else{//2.item已经创建完了 需要更新
					 item.setCount(item.getCount()+1);
					 cartItemService.update(item);
				 }
				 req.getRequestDispatcher(VIEW_PATH+"cartInfo.jsp").forward(req, resp);
			}
		}
		else if("list".equals(method)){
			 req.getRequestDispatcher(VIEW_PATH+"cartInfo.jsp").forward(req, resp);
		}
		
	}
}
