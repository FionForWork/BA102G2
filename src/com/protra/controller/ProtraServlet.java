package com.protra.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductVO;
import com.protra.model.ProtraService;
import com.protra.model.ProtraVO;

/**
 * Servlet implementation class protracking_listServlet
 */
@WebServlet("/protracking_list/Protracking_listServlet")
public class ProtraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String mem_no = String.valueOf(session.getAttribute("mem_no"));
        ProductVO productVO=(ProductVO)session.getAttribute("productVO");
        String pro_no = productVO.getPro_no();
        ProtraService protracking_listService = new ProtraService();

        if ("ADD".equals(action)) {
            ProtraVO protracking_listVO = new ProtraVO();
            protracking_listVO.setPro_no(pro_no);
            protracking_listVO.setMem_no(mem_no);
            protracking_listService.addProtracking_list(protracking_listVO);
            request.getRequestDispatcher("/front_end/mall/product.jsp?pro_no=" + pro_no).forward(request, response);
        }
        else if ("DELETE".equals(action)) {
            protracking_listService.deleteProductTracking(pro_no);
            request.getRequestDispatcher("/front_end/mall/product.jsp?pro_no=" + pro_no).forward(request, response);
        }
        else if("ADD_AJAX".equals(action)){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            ProtraVO protracking_listVO = new ProtraVO();
            protracking_listVO.setPro_no(pro_no);
            protracking_listVO.setMem_no(mem_no);
            protracking_listService.addProtracking_list(protracking_listVO);
            printWriter.println("<a href=\"javascript:protracking('DELETE_AJAX',"+pro_no+")\" class='btn btn-danger'>取消追蹤</a>");
            printWriter.close();
        }
        else if("DELETE_AJAX".equals(action)){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            protracking_listService.deleteProductTracking(pro_no);
            printWriter.print("<a href=\"javascript:protracking('ADD_AJAX',"+pro_no+")\" class='btn btn-info'>加入追蹤</a>");
            printWriter.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
