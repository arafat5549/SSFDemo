package com.ssf.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;
import com.ssf.model.Cart;
import com.ssf.model.CartItem;
import com.ssf.model.Order;
import com.ssf.model.Order.orderStatus;
import com.ssf.model.User;
import com.ssf.service.CartService;
import com.ssf.service.OrderService;
import com.ssf.utils.Global;

/**
 * 订单 Controller层
 * @author wyy
 * 2017年2月28日
 *
 */
@SuppressWarnings("serial")
public class OrderServlet extends HttpServlet
{
	private CartService cartService = new CartService();
	private OrderService orderService = new OrderService();
	public static final String VIEW_PATH=new String("/WEB-INF/views/order/new/");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User)req.getSession().getAttribute(Global.SESSION_USER);
		if(user==null){
			req.getRequestDispatcher(UserServlet.VIEW_PATH+"/login.jsp").forward(req, resp);
			return;
		}
		
		String method = req.getParameter("method");
		if("add".equals(method)){
			//下订单#跳转去下订单  1,2@,@,,
			String oiid = req.getParameter("oiids");
			List<CartItem> items =new ArrayList<CartItem>();
			//System.out.println("oiids="+oiid);
			if(!Strings.isNullOrEmpty(oiid)){//oiid!=null&&!"".equals(oiid)
				//1.获取正确的参数 转化为int数组
				String os[] = oiid.split(",");
				List<Integer> params = new ArrayList<Integer>();
				for (String string : os) {
					boolean f = string.matches("[0-9]+");
					if(f){
						params.add(Integer.parseInt(string));
					}
				}
				//2.判断该id在购物车中存在不存在
				Cart cart = (Cart)req.getSession().getAttribute(Global.SESSION_CART);
				for (Integer integer : params) {
					CartItem item = cart.findByItemId(integer.intValue());
					items.add(item);
				}
				
			}
			req.setAttribute("orderItems", items);
			req.getRequestDispatcher(VIEW_PATH+"/order.jsp").forward(req, resp);
		}
		else if("list".equals(method)){
			//显示用户拥有的所有订单
			List<Order> orders = orderService.findAllByUserId(user.getId());
			req.setAttribute("orders", orders);
			req.getRequestDispatcher(VIEW_PATH+"/orderList.jsp").forward(req, resp);
		}
		else if("alipay".equals(method)){
			//去付款页面
			req.getRequestDispatcher(VIEW_PATH+"/alipay.jsp").forward(req, resp);
		}
	}
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	User user = (User)req.getSession().getAttribute(Global.SESSION_USER);
		if(user==null){
			req.getRequestDispatcher(UserServlet.VIEW_PATH+"/login.jsp").forward(req, resp);
			return;
		}
    	
    	String method = req.getParameter("method");
		if("add".equals(method)){//oid=1&oid=2
			//生成订单
			String[] oids = req.getParameterValues("oid");
			List<Integer> params = new ArrayList<Integer>();
			for (String oid : oids) {
				boolean f = oid.matches("[0-9]+");
				if(f){
					params.add(Integer.parseInt(oid));
				}
			}
			List<CartItem> items =new ArrayList<CartItem>();
			//2.判断该id在购物车中存在不存在
			Cart cart = (Cart)req.getSession().getAttribute(Global.SESSION_CART);
			for (Integer integer : params) {
				CartItem item = cart.findByItemId(integer.intValue());
				items.add(item);
			}
			
			int orderId = orderService.findMaxCount();
			Order order =new Order();
			order.setId(orderId);
			String ordercode = UUID.randomUUID().toString().replace("-", ""); //订单号（唯一,32个字符）
			order.setOrdercode(ordercode);
			order.setStatus(orderStatus.STATUS_WAITPAY.getValue());
			//TODO 1前台：参数验证
			String address  = req.getParameter("address");
			String receiver = req.getParameter("receiver");
			String mobile   = req.getParameter("mobile");
			String post     = req.getParameter("post");
			String message     = req.getParameter("userMessage");
			System.out.println("address="+address);
			order.setAddress(address);
			order.setReceiver(receiver);
			order.setMobile(mobile);
			order.setPost(post);
			order.setMessage(message);
			order.setUserId(user.getId());
			//TODO 2用事务封装你的下订单方法
			String error = orderService.order(items,order);
			if(Strings.isNullOrEmpty(error)){
				//从购物车中清掉已生成的订单项
				for (CartItem cartItem : items) {
					cart.removeByItemId(cartItem.getId());//清除内存
				}
				cartService.clearCartItems(items); 
				
				List<Order> orders = orderService.findAllByUserId(user.getId());
				req.setAttribute("orders", orders);
				req.getRequestDispatcher(VIEW_PATH+"/orderList.jsp").forward(req, resp);
			}
			else{
				req.setAttribute("msg", error);
				req.setAttribute("orderItems", items);
				req.setAttribute("order", order);
				req.getRequestDispatcher(VIEW_PATH+"/order.jsp").forward(req, resp);
			}
		}
		else if("alipay".equals(method)){ //确认支付
			//System.out.println("method==="+method);
			String oidStr = req.getParameter("oid");
			int oid = oidStr==null? -1 :Integer.parseInt(oidStr);
			Order order = orderService.findById(oid);
			String error = orderService.pay(order);
			if(Strings.isNullOrEmpty(error)){
				List<Order> orders = orderService.findAllByUserId(user.getId());
				req.setAttribute("orders", orders);
				req.getRequestDispatcher(VIEW_PATH+"/orderList.jsp").forward(req, resp);
			}
			else{
				req.setAttribute("msg", error);
				req.getRequestDispatcher(VIEW_PATH+"/orderList.jsp").forward(req, resp);
			}
		}
		else if("delivery".equals(method)){//确认发货
			String username = user.getUsername();
			if("admin".equals(username)){
				String oidStr = req.getParameter("oid");
				int oid = oidStr==null? -1 :Integer.parseInt(oidStr);
				Order order = orderService.findById(oid);
				String error = orderService.delivery(order);
				if(Strings.isNullOrEmpty(error)){
					List<Order> orders = orderService.findAllByUserId(user.getId());
					req.setAttribute("orders", orders);
					req.getRequestDispatcher(VIEW_PATH+"/orderList.jsp").forward(req, resp);
				}
				else{
					req.setAttribute("msg", error);
					List<Order> orders = orderService.findAllByUserId(user.getId());
					req.setAttribute("orders", orders);
					req.getRequestDispatcher(VIEW_PATH+"/orderList.jsp").forward(req, resp);

				}
			}
			else{
				req.setAttribute("msg", "权限不足-该用户没有发货权限");
				req.getRequestDispatcher(VIEW_PATH+"/orderList.jsp").forward(req, resp);
			}
		}
		else if("confirm".equals(method)){//确认收货
			String oidStr = req.getParameter("oid");
			int oid = oidStr==null? -1 :Integer.parseInt(oidStr);
			Order order = orderService.findById(oid);
			String error = orderService.confirm(order);
			if(Strings.isNullOrEmpty(error)){
				List<Order> orders = orderService.findAllByUserId(user.getId());
				req.setAttribute("orders", orders);
				req.getRequestDispatcher(VIEW_PATH+"/orderList.jsp").forward(req, resp);
			}
			else{
				req.setAttribute("msg", error);
				req.getRequestDispatcher(VIEW_PATH+"/orderList.jsp").forward(req, resp);
			}
		}
    }
	
}
