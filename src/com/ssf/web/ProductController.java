package com.ssf.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.model.Product;
import com.ssf.service.ProductService;

/**
 * 商品WEB层
 * 
 * @author wyy
 * 2017年3月29日
 *
 */
@SuppressWarnings("serial")
public class ProductController extends HttpServlet{

	ProductService  productService = new ProductService();
	
	public static final String VIEW_PATH = "/WEB-INF/views/product/";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//将前台传过来的字符串转化为int类型
		int categoryId = Integer.parseInt(req.getParameter("cid"));
		
		List<Product> products = productService.findAllSub(categoryId);
		
		req.setAttribute("products", products);
		req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
	}
}
