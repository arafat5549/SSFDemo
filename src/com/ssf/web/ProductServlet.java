package com.ssf.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.model.Category;
import com.ssf.model.Pagenation;
import com.ssf.model.Product;
import com.ssf.service.CategoryService;
import com.ssf.service.ProductService;
import com.ssf.utils.Global;

@SuppressWarnings("serial")
public class ProductServlet extends HttpServlet{
	private ProductService productService = new ProductService();
	private CategoryService categoryService = new CategoryService();
	
	public static final String VIEW_PATH= "/WEB-INF/views/product/new/";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("list".equals(method)){
			
			//List<Product> productList = productService.list();
			//req.setAttribute("productList", productList);
			String cidStr = req.getParameter("cid");
			int cid = cidStr==null? -1 :Integer.parseInt(cidStr);
			
			String limitStr  = req.getParameter("limit");
			String offsetStr = req.getParameter("offset");
			int limit = limitStr==null  ? Global.PAGE_LIMIT : Integer.parseInt(limitStr);
			int offset = offsetStr==null? 0 :Integer.parseInt(offsetStr);
			System.out.println(limit+","+offset);
			
			List<Product> productList = productService.listPage(limit, offset,cid);
			int total = productService.getListCount(cid);//总数需要修改
			int totalPage = total%limit==0 ? total/limit : total/limit+1;
			int pageIndex =  offset%limit==0 ? offset/limit :offset/limit+1;
			Pagenation<Product> pagenation = new Pagenation<Product>();
			pagenation.setLimit(limit);
			pagenation.setOffset(offset);
			pagenation.setLists(productList);
			pagenation.setTotal(total);
			pagenation.setTotalPage(totalPage);
			pagenation.setPageIndex(pageIndex);
			
			req.setAttribute("pagenation", pagenation);
			
			List<Category> firstCategoryList = categoryService.findFirstCategoryList();
			req.setAttribute("firstCategoryList", firstCategoryList);
			req.getRequestDispatcher(VIEW_PATH+"/listPage.jsp").forward(req, resp);
		}
		else if("detail".equals(method)){
			String pidStr = req.getParameter("pid");
			int pid = pidStr==null? -1 :Integer.parseInt(pidStr);
			Product p = productService.findById(pid);
			req.setAttribute("p", p); 
			req.getRequestDispatcher(VIEW_PATH+"/productInfo.jsp").forward(req, resp);
		}
//		else if("index".equals(method)){
//			String cidStr = req.getParameter("cid");
//			int cid = cidStr==null? -1 :Integer.parseInt(cidStr);
//			List<Product> productList = productService.getProductListByCid(cid);
//			req.setAttribute("productList", productList);
//			req.getRequestDispatcher(VIEW_PATH+"/productItem.jsp").forward(req, resp);
//		}
	}
}
