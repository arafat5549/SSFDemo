package com.ssf.web.back;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssf.dao.ICategoryDao;
import com.ssf.dao.IUserDao;
import com.ssf.model.Category;
import com.ssf.model.Pagenation;
import com.ssf.model.User;
import com.ssf.service.back.AdminService;

//1.需要路径最后的斜杠：  /admin/ 和  /admin
//2.web层都是mvc扫描的，如果你建了新的包路径 也要扫描进来


//3.全局错误管理，不再用返回一个错误string判断你有没有错误

@Controller
@RequestMapping("/admin/") //   /admin/login
public class AdminController 
{
	@Autowired
	AdminService adminService;
	@Autowired
	IUserDao userDao;
	@Autowired
	ICategoryDao categoryDao;
	
	static final String VIEW_PATH = "/admin/";
	
	//分页内容{page} 第几页#limit固定20
	@RequestMapping(value="/demo/{page}",method=RequestMethod.GET)
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
		
		return VIEW_PATH+"demo";  
	}
	
	@RequestMapping(value="/demo",method=RequestMethod.GET)
	public String index(Model model){
		List<Category> list = categoryDao.findAll();
		model.addAttribute("categoryList",list);//jsp的做法
		return VIEW_PATH+"demo";
	}
	@RequestMapping(value="/ajaxquery",method=RequestMethod.GET)
	@ResponseBody 
	public List<Category> ajaxquery(Model model){
		System.out.println("==ajaxquery===");
		List<Category> list = categoryDao.findAll();
		return list;
	}
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String adminUI(){
		return VIEW_PATH+"login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String adminLogin(@Valid User user,BindingResult result,Model model){//直接接收参数
		List<ObjectError> errors = result.getAllErrors();
		StringBuffer sb = new StringBuffer();
		for (ObjectError objectError : errors) {
			sb.append(objectError.getDefaultMessage()+"\r\n");
		}
		System.out.println(sb.toString());
		model.addAttribute("msg", sb.toString());
		
		
		//获取用户信息
		List<User> lists = userDao.findAll();
		model.addAttribute("userlist", lists);
		return VIEW_PATH+"listUser";
	}
	
	@RequestMapping(value="/test")
	public void test(){
		userDao.sayHello();
	}
	
	
	
}
