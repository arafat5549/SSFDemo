package com.ssf.web.back;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ssf.dao.ICategoryDao;
import com.ssf.dao.IUserDao;
import com.ssf.model.User;
import com.ssf.service.back.AdminService;
import com.ssf.utils.ImageUtil;

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
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
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
		return "redirect:/admin/demo/1";//重定向
	} 
	@RequestMapping(value="/ajaxquery",method=RequestMethod.GET)
	@ResponseBody 
	public List<Category> ajaxquery(HttpServletRequest req,Model model){		
		//System.out.println("==ajaxquery===");
		List<Category> list = categoryDao.findAll();
		return list;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addUI(){
		return VIEW_PATH+"add";
	}
	@RequestMapping(value="/demo/add",method=RequestMethod.POST)
	public String addAction(Category category){
		//System.out.println(category);
		//构建我的parentIds
		int newid = categoryDao.findMaxId();
		category.setParentIds(category.getParentIds()+","+newid);
		category.preInsert();
		categoryDao.save(category);
		return "redirect:/admin/demo/1";
	} 
	
	@RequestMapping(value="/demo/getJson", produces={"application/json;charset=UTF-8"})  //默认类型是JSON
	@ResponseBody 
	public List<Category> treeData(){
		List<Category> list = categoryDao.findAll();
		return list;
	}
	*/
	//----------------------------------------------------------------
	@RequestMapping(value="/user/userInfo",method=RequestMethod.GET)
	public String userInfoUI(){
		return VIEW_PATH+"/user/userInfo";
	}
	@RequestMapping(value="/user/userInfo",method=RequestMethod.POST)
	public String userInfoAction(User user,HttpSession session,@RequestParam("avatarUrl") MultipartFile avatarUrl){
		if (!avatarUrl.isEmpty()) {
			System.out.println(avatarUrl);
            uploadImage(user, session, avatarUrl);
        }
		
		return VIEW_PATH+"/user/userInfo";
	}
	//上传图片
	private void uploadImage(User user, HttpSession session, MultipartFile file) {
        //1.要有一个唯一的名字 时间片
		String fileName = new Date().getTime() + ".jpg";
        //获取真实路径
        String path = session.getServletContext().getRealPath("/upload");
        
        String serverFile = path + "/" + fileName;
        try {
            logger.info(path);
            if (!new File(path).exists()) {//如果上传路径不存在 先创建文件夹
                new File(path).mkdirs();
            } 
            if (!new File(serverFile).exists()) {
                new File(serverFile).createNewFile();
            }
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(serverFile)));
            stream.write(bytes);
            stream.close();
            //缩放处理
            ImageUtil image = new ImageUtil(serverFile);
            image.resize(300,300);
            image.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setAvartarUrl("/upload/" + fileName);
        session.setAttribute("session_user", user);
        logger.info(user.toString());
        //userDao.update(user);
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
	
	
	
	
	
}
