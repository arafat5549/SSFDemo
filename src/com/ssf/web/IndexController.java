package com.ssf.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.model.Category;
import com.ssf.service.CategoryService;

/**
 * 主页
 * @author wyy
 * 2017年3月29日
 *
 */
public class IndexController extends HttpServlet{

	CategoryService categoryService  = new CategoryService();
	
	private static final String VIEW_PATH = "/WEB-INF/views/";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Category> firstCategorys = categoryService.findFirstCategorys();
		req.setAttribute("firstCategorys", firstCategorys);
		
		req.getRequestDispatcher(VIEW_PATH+"home.jsp").forward(req, resp);
	}
}
