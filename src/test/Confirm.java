package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.com.model.ComService;
import com.com.model.ComVO;

/**
 * Servlet implementation class Confirm
 */
@WebServlet("/Confirm")
public class Confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		

		//驗證廠商
				if("conFirmCom".equals(action)){
					//http://localhost:8081/BA102G2/Confirm?action=conFirmCom&&com_no=2001
					ComVO comVO = new ComVO();
					String com_no = req.getParameter("com_no").trim();

					 ComService comSvc = new ComService();
					 comVO =comSvc.confirmCom(com_no);
					 
				      res.sendRedirect(req.getContextPath()+"/Front_end/com/Confirm.jsp");
				     
				      return;
					 
				}
		
		
		
	}

	

}
