package com.ssf.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.model.Review;
import com.ssf.model.User;
import com.ssf.service.ReviewService;

public class ReviewController extends HttpServlet{

	private ReviewService reviewService = new ReviewService();
	public static final String VIEW_PATH = "/WEB-INF/views/review/";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		String method = req.getParameter("method");
		if("list".equals(method)){
			String pidStr = req.getParameter("pid");
			if(pidStr!=null){
				int pid = Integer.parseInt(pidStr);
				
				List<Review> lists = reviewService.findOrderReviewListByProductId(pid, 1);
				req.setAttribute("reviewList", lists);
				
				req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
			}
		}
		else if("add".equals(method)){
			req.getRequestDispatcher(VIEW_PATH+"review.jsp").forward(req, resp);
		}
	}
	//
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User)req.getSession().getAttribute("session_user");
		
		String method = req.getParameter("method");
		if("add".equals(method)){
			String text   = req.getParameter("text");
			String pidStr = req.getParameter("pid");
			System.out.println(pidStr);
			int pid = 0;
			if(pidStr!=null){
				pid = Integer.parseInt(pidStr);
			}
			Review review = new Review();
			review.setUserId(user.getId());
			review.setProductId(pid);
			review.setText(text);
			
			String error = reviewService.publish(review);
			if(error!=null && !"".equals(error)){
				req.setAttribute("msg", error);
				req.getRequestDispatcher(VIEW_PATH+"review.jsp").forward(req, resp);
				//resp.sendRedirect(arg0);
				//resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}
			else{
				//resp.sendRedirect(req.getContextPath()+"/review?method=list&pid="+pid);
				List<Review> lists = reviewService.findOrderReviewListByProductId(pid, 1);
				req.setAttribute("reviewList", lists);
				req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
			}
		}
	}
	
	
}
