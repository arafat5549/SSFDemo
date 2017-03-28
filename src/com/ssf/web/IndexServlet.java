package com.ssf.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.model.Category;
import com.ssf.model.Product;
import com.ssf.model.User;
import com.ssf.service.CartService;
import com.ssf.service.CategoryService;
import com.ssf.service.ProductService;
import com.ssf.utils.Global;


@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet{

	private CategoryService categoryService = new CategoryService();
	private ProductService productService = new ProductService();
	private CartService cartService = new CartService();
	
	public static final String VIEW_PATH=new String("/WEB-INF/views/");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Category> first = categoryService.findFirstCategoryList();
		req.setAttribute("firstCategoryList", first);
		
		//分类底下的所有商品
		for (Category category : first) {
			 List<Product> productList = productService.getProductListByCid(category.getId());
			 category.setProducts(productList);
		}
		
		//User user =(User)req.getSession().getAttribute(Global.SESSION_USER);
		//int cartTotalItemCount = cartService.getCartItemCount(user);
		//req.setAttribute("cartTotalItemCount", cartTotalItemCount);
		//User user =new User()
		req.getRequestDispatcher(VIEW_PATH+"home.jsp").forward(req, resp);
	}
	
//	public String index(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		List<Category> first = categoryService.findFirstCategoryList();
//		request.setAttribute("firstCategoryList", first);
//		System.out.println("-----IndexServlet-----");
//		return "f:"+VIEW_PATH+"home.jsp";
//		return "r:"+VIEW_PATH+"home.jsp";
//	}
}
