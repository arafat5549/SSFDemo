package com.ssf.web.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssf.dao.IProductDao;

@Controller
@RequestMapping("/admin/product/")
public class AdminProductController {
private static final String VIEW_PATH = "/admin/product/";
	
	@Autowired
	IProductDao categoryDao;
}
