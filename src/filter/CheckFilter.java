package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssf.model.User;

/**
 * 检查用户登录过滤器 
 * 
 * @author wyy
 * 2017年4月1日
 *
 */
public class CheckFilter implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		//如果用户没有登录-我就将他强制返回登录页面
		if(req instanceof HttpServletRequest){
			HttpServletRequest httpReq = (HttpServletRequest)req;
			HttpServletResponse httpResp = (HttpServletResponse)resp;
			User user = (User)httpReq.getSession().getAttribute("session_user");
			if(user == null){
				//httpReq.getRequestDispatcher(UserController.VIEW_PATH+"login.jsp").
				//forward(req, resp);
				httpResp.sendRedirect(httpReq.getContextPath()+"/user?method=login");
				return; 
			}
		}
		
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
