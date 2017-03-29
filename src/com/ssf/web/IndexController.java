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
	
	//所有页面要放到WEB-INF底下
	public static final String VIEW_PATH = "/WEB-INF/views/";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取一级分类 传递给前台#
		List<Category> firstCategorys = categoryService.findFirstCategorys();
		req.setAttribute("firstCategorys", firstCategorys);
		
		req.getRequestDispatcher(VIEW_PATH+"home.jsp").forward(req, resp);
	}
}
