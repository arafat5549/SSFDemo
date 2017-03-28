package com.ssf.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet{
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		resp.setContentType("text/html; charset=UTF-8");
//		//resp.getWriter().print("输出中文");
//		//String user =new String("");//放在堆里面
//		
//		//---Servlet输出HTML格式
//		OutputStream out = resp.getOutputStream();
//		out.write("<html>".getBytes());
//		out.write("<body>".getBytes());
//		out.write("</body>".getBytes());
//		out.write("</html>".getBytes());
		
		
		String method = req.getParameter("method");
		if("add".equals(method)){
			String oiids = req.getParameter("oiids");
			//System.out.println("oiids="+oiids);
			
	       List<String> result = Splitter.on(",").trimResults().splitToList(oiids);  
	       Lists.transform(result, new Function<String, Integer>() {

				@Override
				public Integer apply(String str) {
					return Ints.tryParse(str);
				}
	       });
	       System.out.println(result);  
		}
		else if("addCart".equals(method)){
			System.out.println(method);  
			resp.getWriter().write("success");
		}
		else{
			//resp.getWriter().write("success");
		}
	}
}
