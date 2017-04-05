package com.ssf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") 
public class IndexController {

	@RequestMapping("/index")
	public String index(Model model){
		
		model.addAttribute("msg", "测试参数");
		return "home";
	}
}
