package com.ssf.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.ssf.model.Cart;
import com.ssf.model.User;
import com.ssf.service.CartService;
import com.ssf.utils.Global;

@SuppressWarnings("serial")
public class CartServlet extends HttpServlet{

	CartService cartService = new CartService();
	public static final String VIEW_PATH=new String("/WEB-INF/views/cart/new/");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		Cart cart = (Cart)req.getSession().getAttribute(Global.SESSION_CART);
		User user = (User)req.getSession().getAttribute(Global.SESSION_USER);
		
		if(user == null){//先登录
			req.getRequestDispatcher(UserServlet.VIEW_PATH+"/login.jsp").forward(req, resp);
			return;
		}
		
		if("add".equals(method)){
			String pidStr = req.getParameter("pid");
			int pid = pidStr!=null ? Integer.parseInt(pidStr) : 0;
			String error = cartService.addToCart(cart, pid, 1);
			if(StringUtils.isEmpty(error)){
				req.setAttribute("msg", error); 
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
			else{
				req.getRequestDispatcher(VIEW_PATH+"/cartInfo.jsp").forward(req, resp);
			}
		}
		else if("list".equals(method)){
			req.getRequestDispatcher(VIEW_PATH+"/cartInfo.jsp").forward(req, resp);
		}
		else if("delete".equals(method)){
			String itemidStr = req.getParameter("itemid");
			int itemid = itemidStr ==null ? 0: Integer.parseInt(itemidStr);
			
			cartService.deleteCartItem(cart, itemid);
			req.getRequestDispatcher(VIEW_PATH+"/cartInfo.jsp").forward(req, resp);
		}
		else if("clear".equals(method)){
			cartService.clearCart(cart);
			req.getRequestDispatcher(VIEW_PATH+"/cartInfo.jsp").forward(req, resp);
		}
	}
}
