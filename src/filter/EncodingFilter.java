package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 编码过滤器
 * 
 * 所有的WEB请求统一设置UTF-8编码
 * 
 * @author wyy
 * 2017年4月1日
 *
 */
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf8");
		
		//System.out.println("请求线过Filter");
		//放行请求
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
