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

import com.com.model.ComService;
import com.com.model.ComVO;
import com.mem.model.MemVO;

@WebFilter("/LoginComFilter")
public class LoginComFilter implements Filter {
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
	
	
		
		try{
			ComVO comVO =(ComVO)session.getAttribute("comVO");
			comVO.getCom_no();
		}catch(Exception e){
			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
		      out.println("<BODY>你的帳號錯誤請先登出後登入廠商帳號!<BR>");
		      out.println("</BODY></HTML>");
		    return;
		}
		if (id == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/Front_end/login/login.jsp");
			return;
		} else {
				chain.doFilter(request, response);
			}

			
		}
	}

	

