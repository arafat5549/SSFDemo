package com.ssf.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssf.dao.ICategoryDao;
import com.ssf.dao.IUserDao;
import com.ssf.model.Category;
import com.ssf.model.User;

@Controller
@RequestMapping("/") 
public class IndexController {

	@Autowired
	ICategoryDao categoryDao;
	@Autowired
	IUserDao userDao;
	
	@RequestMapping("/index")
	public String index(Model model){
		
		model.addAttribute("msg", "测试参数");
		return "home";
	}
	
	//JSON测试注解
	@RequestMapping("/test/{id}")
	@ResponseBody
	public Category test(@PathVariable("id") Integer id){
		return categoryDao.findById(id);
	}
	
	@RequestMapping(value="/getXML", produces={"application/xml;charset=UTF-8"})  //默认类型是JSON
	@ResponseBody 
	public User xml_treeData(){
		User list = userDao.findById(1);
		return list;
	}
	
	/**
	 * 往前台传JSON对象（直接把你需要的数据给你）
	 * @return
	 */
	@RequestMapping(value="/getJson", produces={"application/json;charset=UTF-8"})  //默认类型是JSON
	@ResponseBody 
	public List<Category> treeData(){
		List<Category> list = categoryDao.findAll();
//		String extId = "";
//		String isShowHide = null;
//		List<Map<String, Object>> mapList = Lists.newArrayList();
//		for (int i=0; i<list.size(); i++){
//			Category e = list.get(i);
//			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
//				if(isShowHide != null && isShowHide.equals("0") ){//&& e.getIsShow().equals("0")
//					continue;
//				}
//				Map<String, Object> map = Maps.newHashMap();
//				map.put("id", e.getId());
//				map.put("pId", e.getParentId());
//				map.put("name", e.getName());
//				mapList.add(map);
//			}
//		}
		return list;
	}
	
	/**
	 * 前台往后台传Json数据
	 */
	//@RequestBody User user
}
