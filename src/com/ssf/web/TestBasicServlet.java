package com.ssf.web;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TestBasicServlet implements Servlet{

	

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		//读取配置， jsp容器在读取WEB.XML的时候初始化Servlet （只有一次）
	}

	@Override
	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
		//一次请求就调用一次service方法 
	}
	
	@Override
	public void destroy() {
		//服务器关了或者手动销毁（只有一次）
	}
}
