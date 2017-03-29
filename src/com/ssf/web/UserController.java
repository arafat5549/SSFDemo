package com.ssf.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.model.User;
import com.ssf.service.UserService;

/**
 * 用户控制层
 * 
 * 控制页面跳转和数据传递
 * 
 * @author wyy
 * 2017年3月23日
 *
 */
@SuppressWarnings("serial")
public class UserController extends HttpServlet
{
	private UserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("login".equals(method)){
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		else if("logout".equals(method)){
			req.getSession().setAttribute("session_user", null);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
		else if("listUser".equals(method)){
			List<User> lists = new ArrayList<User>();
			for(int i=0;i<5;i++){ //随机创建5个对象
				User user = new User();
				user.setUsername("测试用户"+i);
				user.setPassword("123456");
				lists.add(user);
			}
			req.setAttribute("userlist", lists);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1.获取参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		String error = userService.login(user);
		if(error!=null && !"".equals(error)){
			req.setAttribute("msg", error);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		else{
			req.setAttribute("msg", "登录成功");
			req.getSession().setAttribute("session_user", user);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
