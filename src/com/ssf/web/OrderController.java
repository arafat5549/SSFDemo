package com.ssf.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.model.Cart;
import com.ssf.model.CartItem;
import com.ssf.model.Order;
import com.ssf.model.Order.OrderStatus;
import com.ssf.model.OrderItem;
import com.ssf.model.User;
import com.ssf.service.CartItemService;
import com.ssf.service.OrderItemService;
import com.ssf.service.OrderService;

/**
 * 订单WEB层
 * 
 * 1.需要验证参数(所有用户输入的地方都需要验证参数)
 * 2.判断用户登录没有
 * 3.怎么保证数据库同时失败 同时失败（数据库事务）
 * 
 * @author wyy
 * 2017年3月31日
 *
 */
@SuppressWarnings("serial")
public class OrderController extends HttpServlet{
	//private CartItemService cartItemService = new CartItemService();
	private OrderService orderService = new OrderService();
	//private OrderItemService orderItemService = new OrderItemService();
	
	public static final String VIEW_PATH = "/WEB-INF/views/order/";
	//
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Cart cart = (Cart)req.getSession().getAttribute("session_cart");
		User user = (User)req.getSession().getAttribute("session_user");
		
		int userId = user.getId();
		
		String method = req.getParameter("method");
//		if("order".equals(method)){ 
//			req.getRequestDispatcher(VIEW_PATH+"order.jsp").forward(req, resp);
//		}
//		else 
		if("add".equals(method)){//1-1.去订单页面
			String[] oids  = req.getParameterValues("oiid");
			List<CartItem> items = new ArrayList<CartItem>();
			for (String s : oids) {
				int id = Integer.parseInt(s);
				//CartItem item = cartItemService.findById(id);
				//items.add(item);
				CartItem item = cart.findItemById(id);
				items.add(item);
			}
			req.setAttribute("orderItems", items);
			req.getRequestDispatcher(VIEW_PATH+"order.jsp").forward(req, resp);
		}
		else if("list".equals(method)){//2.获取订单列表
			//orders
			List<Order> orders = orderService.findOrdersByUserId(userId);
			req.setAttribute("orders", orders); 
			req.getRequestDispatcher(VIEW_PATH+"orderList.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		String method = req.getParameter("method");
		if("add".equals(method)){ //1-2.提交订单
			Cart cart = (Cart)req.getSession().getAttribute("session_cart");
			User user = (User)req.getSession().getAttribute("session_user");
			
			int userId = user.getId();
			
			String address   = req.getParameter("address");//收货地址    
			String post      = req.getParameter("post");   //邮政编号
			String receiver  = req.getParameter("receiver");//收件人
			String mobile    = req.getParameter("mobile"); //手机
			String message   = req.getParameter("message"); //备注
			//cartItemId  有多个id，怎么传多个相同名称的参数?
			//URL路径类似于: localhost/Myshop/order?oid="1,2,3"
			//1. "id1,id2,id3" #  构建一个String
			//URL路径类似于: localhost/Myshop/order?oid=1&oid=2&oid=3
			String[] oids  = req.getParameterValues("oid");
			
			address = "地址";
			receiver = "王耀";
			mobile = "123456789";
			//TODO 参数验证(用户传过来的参数一定要做参数验证)
			
			//生成新的Order订单
			Order order =new Order();
			order.setId(orderService.findMaxId());
			order.setAddress(address);
			order.setPost(post);
			order.setReceiver(receiver);
			order.setMobile(mobile);
			order.setMessage(message);
			order.setStatus(OrderStatus.WAIT_PAY.getValue());//下订单就是等待付款状态
			order.setUserId(userId);
			order.setOrdercode(UUID.randomUUID().toString());//唯一的编码
			//将购物车的项目转为订单项目
			List<CartItem> items = new ArrayList<CartItem>();
			for (String s : oids) {
				int id = Integer.parseInt(s);
				CartItem item = cart.findItemById(id);
				items.add(item);
				
				OrderItem oi = new OrderItem();
				//oi.setId(orderItemService.findMaxId());
				oi.setOrderId(order.getId());
				oi.setProductId(item.getProductId());
				oi.setCount(item.getCount());
				oi.setUserId(userId);
				order.getItems().add(oi);
			}
			
			String error = orderService.order(order,items);
			if(error!=null && !"".equals(error))//失败了怎么样
			{
				req.setAttribute("error", error);
				req.setAttribute("orderItems", items);
				req.getRequestDispatcher(VIEW_PATH+"order.jsp").forward(req, resp);
			}
			else//成功了怎么样
			{
				cart.clearByIds(oids);
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}
			
		}
	}
}
