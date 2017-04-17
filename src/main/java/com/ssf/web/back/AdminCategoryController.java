package com.ssf.web.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssf.dao.ICategoryDao;
import com.ssf.model.Category;
import com.ssf.model.Pagenation;

@Controller
@RequestMapping("/admin/category/")
public class AdminCategoryController {
	private static final String VIEW_PATH = "/admin/category/";
	
	@Autowired
	ICategoryDao categoryDao;
	
	
	//
	@RequestMapping(value="/list/{page}",method=RequestMethod.GET)
	public String pagenation(@PathVariable("page") Integer page,Model model){
		List<Category> list = categoryDao.findAll();
		int limit = 20;
		int total = list.size();
		int totalPage = total % limit ==0 ? total/limit : total/limit+1; 
		page = page - 1;//
		page = Math.max(0, page);
		page = Math.min(totalPage, page);
		int offset = page * limit;
		list = categoryDao.findPage(offset, limit);
		//model.addAttribute("categoryList",list);//jsp的做法
		
		Pagenation<Category> pagenation = new Pagenation<Category>();
		pagenation.setData(list);
		pagenation.setLimit(limit);
		pagenation.setOffset(offset);
		pagenation.setTotal(total);
		pagenation.setTotalPage(totalPage);
		pagenation.setPage(page+1);
		model.addAttribute("pagenation",pagenation);
		
		return VIEW_PATH+"list";  
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String index(Model model){
		return "redirect:/admin/category/list/1";//重定向
	} 
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addUI(){
		return VIEW_PATH+"add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addAction(Category category){
		//System.out.println(category);
		//构建我的parentIds
		int newid = categoryDao.findMaxId();
		category.setParentIds(category.getParentIds()+","+newid);
		category.preInsert();
		categoryDao.save(category);
		return "redirect:/admin/category/list/1";
	} 
	
	/**
	 * 往前台传JSON对象（直接把你需要的数据给你）
	 * @return
	 */
	@RequestMapping(value="/ajaxquery",method=RequestMethod.GET)
	@ResponseBody 
	public List<Category> ajaxquery(Model model){
		return categoryDao.findAll();
	}
	
//	@RequestMapping(value="/getJson", produces={"application/json;charset=UTF-8"})  //默认类型是JSON
//	@ResponseBody 
//	public List<Category> treeData(){
//		List<Category> list = categoryDao.findAll();
//		return list;
//	}
}
