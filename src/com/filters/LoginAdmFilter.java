package com.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adm.model.AdmVO;

/**
 * Servlet Filter implementation class LoginAdmFilter
 */
@WebFilter("/LoginAdmFilter")
public class LoginAdmFilter implements Filter {

	private static final long serialVersionUID = 1L;
       
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
	
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object id = session.getAttribute("id");
		
	
		
		
		if (id == null) {
			session.setAttribute("Admlocation", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/Back_end/login/login.jsp");
			return;
		} else {
			try{
				AdmVO admVO =(AdmVO)session.getAttribute("admVO");
				admVO.getAdm_no();
			}catch(Exception e){
				res.sendRedirect(req.getContextPath()+"/Back_end/login/errorlogin2.jsp");
				return;
			}
				chain.doFilter(request, response);

		}
	}

}
