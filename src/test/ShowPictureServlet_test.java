package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.content.model.ContentVO;


@WebServlet("/ShowPictureServlet_test")
public class ShowPictureServlet_test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		HttpSession session = request.getSession();
		ContentVO cont = (ContentVO) session.getAttribute("contVO");
		out.write(cont.getImg());
		out.close(); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
