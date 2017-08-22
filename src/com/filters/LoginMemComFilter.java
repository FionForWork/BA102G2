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

import com.com.model.ComVO;
import com.mem.model.MemVO;

/**
 * Servlet Filter implementation class MemRoCom
 */
@WebFilter("/LoginMemFilter")
public class LoginMemComFilter implements Filter {
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
		Object role = session.getAttribute("role");


	
		
		
		
		if (id == null) {
		
			System.out.println(req.getRequestURI());
			if(req.getRequestURI().contains("com")){
	
				session.setAttribute("comlocation", req.getRequestURI());
				res.sendRedirect(req.getContextPath()+"/Front_end/login/login2.jsp");
				return;
			}else if(req.getRequestURI().contains("mem")){
				session.setAttribute("memlocation", req.getRequestURI());
				res.sendRedirect(req.getContextPath()+"/Front_end/login/login.jsp");
				return;
			}else{
				session.setAttribute("memlocation", req.getRequestURI());
				session.setAttribute("comlocation", req.getRequestURI());
				res.sendRedirect(req.getContextPath()+"/Front_end/login/login3.jsp");
				return;	
			}
				
			
			
		} else {
			if(role=="mem"){
				MemVO memVO = (MemVO)session.getAttribute("memVO");
				session.setAttribute("memlocation", req.getRequestURI());
				String status=memVO.getStatus();
			
				
				if(status.equals("停權")){
					res.sendRedirect(req.getContextPath()+"/Front_end/login/statusNotGood.jsp");
					return;
				}
			}else if(role=="com"){
				session.setAttribute("comlocation", req.getRequestURI());
				ComVO comVO = (ComVO)session.getAttribute("comVO");
				String status=comVO.getStatus();
				if(status.equals("停權")){
					res.sendRedirect(req.getContextPath()+"/Front_end/login/statusNotGood.jsp");
					return;
				}
			}else{
				res.sendRedirect(req.getContextPath()+"/Front_end/login/errorLogin2.jsp");
				return;
			}
			
				chain.doFilter(request, response);

		}
	}

}
