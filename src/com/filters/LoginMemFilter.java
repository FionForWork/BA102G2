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
public class LoginMemFilter implements Filter {
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
			session.setAttribute("memlocation", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/Front_end/login/login.jsp");
			return;
		} else {
			try{
				MemVO memVO = (MemVO)session.getAttribute("memVO");
				
				String status=memVO.getStatus();
				 memVO =(MemVO)session.getAttribute("memVO");
				memVO.getMem_no();
				
				if(status.equals("停權")){
					res.sendRedirect(req.getContextPath()+"/Front_end/login/statusNotGood.jsp");
					return;
				}
			}catch(Exception e){
				session.setAttribute("login","mem");
				session.setAttribute("memlocation", req.getRequestURI());
				String s=(String)session.getAttribute("memlocation");
				System.out.println("ssss"+s);
				res.sendRedirect(req.getContextPath()+"/Front_end/login/errorLogin2.jsp");
				return;
			}
				chain.doFilter(request, response);

		}
	}

}
