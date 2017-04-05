package com.ssf.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssf.model.User;

@Controller
@RequestMapping("/user")   //  /user/inex
public class UserController {

	private static final String VIEW_PATH = "/user/";
	
	@RequestMapping("/index")
	public String index(HttpServletRequest req){
		String prefix = "/WEB-INF/views/";
		String postFix = ".jsp";
		req.getRequestDispatcher(prefix+"home"+postFix);
		return "home";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginUI(){
		return VIEW_PATH + "login";
	}
	
	//1.传参简单，
	//2.servlet不需要我们配置和管理,路由指定到方法那一级别
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(User user,Model model){
		System.out.println("获取参数:"+user); 
		user.setUsername("123456");
		model.addAttribute("session_user", user);
		return VIEW_PATH + "login";
	}
}
