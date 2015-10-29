package main.java.com.atheby.meetings.filter;

import java.io.IOException; 

import javax.servlet.*;
import javax.servlet.http.*;  

import main.java.com.atheby.meetings.manager.LoginManager;

public class LoginFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		LoginManager loginBean = (LoginManager)((HttpServletRequest)request).getSession().getAttribute("loginManager");
		
		if (loginBean == null || loginBean.getLoggedUserId() == null) {
			
			String url = ((HttpServletRequest)request).getContextPath();
			
			((HttpServletResponse)response).sendRedirect(url + "/login.xhtml");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void destroy() {}
} 
