package com.ssf.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.Filter;;




import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.common.primitives.Ints;
import com.ssf.model.Cart;
import com.ssf.model.Pagenation;
import com.ssf.model.User;
import com.ssf.service.CartService;
import com.ssf.service.UserService;
import com.ssf.utils.CookieUtils;
import com.ssf.utils.Global;

@SuppressWarnings("serial")
public class UserServlet extends HttpServlet{
	
	private static String session_remember_username = "c_username";
	private static String session_remember_password = "c_password";
	
	public static final String VIEW_PATH=new String("/WEB-INF/views/user/new/");
	
	UserService userService =new UserService();
	CartService cartService =new CartService(); 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		String pString  = req.getParameter("p");
//		byte b[]= pString.getBytes("iso-8859-1");//先还原为byte数组
//		String newsS = new String(b,"UTF-8");//重新组合
//		System.out.println("pString="+pString);
//		System.out.println("newsS="+newsS);
		
		String method = req.getParameter("method");
		if("register".equals(method)){
			req.getRequestDispatcher(VIEW_PATH+"/register.jsp").forward(req, resp);
		}
		else if("login".equals(method))
		{
			User loginUser = (User)req.getSession().getAttribute(Global.SESSION_USER);
			if(loginUser!=null){//已登录
				resp.sendRedirect(req.getServletContext().getContextPath()+"/index.jsp");
				return;
			}
			//从cookie里获取登录名和密码
			String username = CookieUtils.getCookie(req, session_remember_username);
			String password = CookieUtils.getCookie(req, session_remember_password);
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			req.setAttribute(Global.SESSION_USER, user);
			
			req.getRequestDispatcher(VIEW_PATH+"/login.jsp").forward(req, resp);
		}
		else if("logout".equals(method))
		{
			req.getSession().setAttribute(Global.SESSION_USER, null);
			resp.sendRedirect(req.getServletContext().getContextPath()+"/index.jsp");
		}
		else if("userInfo".equals(method)){
			req.getRequestDispatcher(VIEW_PATH+"/userInfo.jsp").forward(req, resp);
		}
		else if("list".equals(method)){
			String limitStr  = req.getParameter("limit");
			String offsetStr = req.getParameter("offset");
			
			int limit = limitStr==null  ? Global.PAGE_LIMIT : Integer.parseInt(limitStr);
			int offset = offsetStr==null? 0 :Integer.parseInt(offsetStr);
			
			List<User> productList = userService.listPage(limit, offset);
			int total = userService.getListCount();//总几率
			int totalPage = total%limit==0 ? total/limit : total/limit+1;
			int pageIndex =  offset%limit==0 ? offset/limit :offset/limit+1;
			Pagenation<User> pagenation = new Pagenation<User>();
			pagenation.setLimit(limit);
			pagenation.setOffset(offset);
			pagenation.setLists(productList);
			pagenation.setTotal(total);
			pagenation.setTotalPage(totalPage);
			pagenation.setPageIndex(pageIndex);
			
			req.setAttribute("pagenation", pagenation);
			req.getRequestDispatcher(VIEW_PATH+"/listPage.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//req.setCharacterEncoding("UTF-8");//在IO流读取之前设置他
		 
		String method = req.getParameter("method");
		if("register".equals(method)){
			doRegister(req,resp);
		}
		else if("login".equals(method))
		{
			doLogin(req,resp); 
		}
		else if("userInfo".equals(method))
		{
			
			doUserInfo(req,resp);
		}
	}

	private void doUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User)req.getSession().getAttribute(Global.SESSION_USER);
		if(user==null){
			req.getRequestDispatcher(VIEW_PATH+"/login.jsp").forward(req, resp);
			return;
		}
		
		boolean isMultiPart = ServletFileUpload.isMultipartContent(req);
		if(isMultiPart){
			DiskFileItemFactory disk = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(disk);
			try {
				List<FileItem> fileItemList = upload.parseRequest(req);
				Map<String,Object> map = new HashMap<String,Object>();
				for (FileItem fileItem : fileItemList) 
				{
					if(fileItem.isFormField())//普通表单
					{
						String key   = fileItem.getFieldName();
						String value = new String(fileItem.get());
						//System.out.println(key+","+value);
						map.put(key, value);  
					} 
					else//文件表单
					{ 
						uploadImage(user,fileItem,req);
					}
				}
				BeanUtils.populate(user, map);
				userService.update(user);
				
				req.getRequestDispatcher(VIEW_PATH+"/userInfo.jsp").forward(req, resp);
							
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
    //上传文件-IO流
	//1.文件路径
	//2.文件名 - 保证不重名 - UUID / 时间片
	private void uploadImage(User user, FileItem fileItem,HttpServletRequest req) {
		try {
			String fileName = new Date().getTime() + ".jpg";
			String path = req.getServletContext().getRealPath(Global.UPLOAD_AVATAR_URL);
			
			
			String serverFile = path + "/" + fileName;
			System.out.println(path+","+serverFile);
				
			if (!new File(path).exists()) {
				 new File(path).mkdirs();
			 }
			 if (!new File(serverFile).exists()) {
				 new File(serverFile).createNewFile();
			 }
			
			 byte[] bytes = fileItem.get();
			 BufferedOutputStream stream =
	               new BufferedOutputStream(new FileOutputStream(new File(serverFile)));
			 stream.write(bytes);
			 stream.close();
			
			 user.setAvatarUrl(fileName); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doLogin(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = userService.findByUserName(username);
		
		String error = userService.login(user,password);
		if(error!=null && !"".equals(error)){
			req.setAttribute("msg", error);
			req.getRequestDispatcher(VIEW_PATH+"/login.jsp").forward(req, resp);
		}
		else{
			//存cookie
			CookieUtils.setCookie(resp, session_remember_username, username);
			CookieUtils.setCookie(resp, session_remember_password, password);
			
			
			req.setAttribute("msg", "登录成功");
			req.getSession().setAttribute(Global.SESSION_USER, user);
			
			//1.登录时候在获取下购物车
			Cart cart = cartService.findByUserId(user.getId());
			if(cart ==null){
				cart = new Cart();
				cart.setId(cartService.findMaxCount());
				cart.setUserId(user.getId());
				
				cartService.save(cart);
			}
			req.getSession().setAttribute(Global.SESSION_CART, cart);
			
			//重定向
			resp.sendRedirect(req.getServletContext().getContextPath()+"/index.jsp");
		}
		
	}

	private void doRegister(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//映射成JavaBean
		User user =new User();
		user.setUsername(username);
		user.setPassword(password);
		String error = userService.register(user);
		if(error!=null && !"".equals(error)){
			req.setAttribute("msg", error);
			req.getRequestDispatcher(VIEW_PATH+"register.jsp").forward(req, resp);
		}
		else{
			req.setAttribute("msg", "注册成功");
			req.setAttribute("user", user);
			req.getRequestDispatcher(VIEW_PATH+"/login.jsp").forward(req, resp);
		}
		
	}
}
