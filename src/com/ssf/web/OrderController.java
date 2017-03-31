package com.ssf.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.model.Order;
import com.ssf.service.OrderService;

public class OrderController extends HttpServlet{

	private OrderService orderService = new OrderService();
	public static final String VIEW_PATH = "/WEB-INF/views/order/";
	//
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getParameter("method");
		if("order".equals(method)){ //1.去订单页面
			req.getRequestDispatcher(VIEW_PATH+"order.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		String method = req.getParameter("method");
		if("add".equals(method)){ //提交订单
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
			
			//生成新的Order订单
			Order order =new Order();
			
			
			String error = orderService.order(order);
			//成功了怎么样
			//失败了怎么样
		}
	}
}
