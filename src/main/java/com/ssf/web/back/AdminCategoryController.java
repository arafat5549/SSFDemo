package com.ssf.web.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssf.dao.ICategoryDao;
import com.ssf.model.Category;

@Controller
@RequestMapping("/admin/category/")
public class AdminCategoryController {
	
	@Autowired
	ICategoryDao categoryDao;
	
	@RequestMapping(value="/ajaxquery",method=RequestMethod.GET)
	@ResponseBody 
	public List<Category> ajaxquery(Model model){
		return categoryDao.findAll();
	}
}
