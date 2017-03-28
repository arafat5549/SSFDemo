package com.ssf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 编码过滤器
 * @author wyy
 * 2017年2月10日
 *
 */
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		//System.out.println("所有请求先进去Filter");
		chain.doFilter(req, resp); 
		
		//
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	

	

}
