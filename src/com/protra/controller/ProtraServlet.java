package com.protra.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.protra.model.ProtraService;
import com.protra.model.ProtraVO;

/**
 * Servlet implementation class protracking_listServlet
 */
@WebServlet("/protra/ProtracServlet")
public class ProtraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String mem_no = String.valueOf(session.getAttribute("mem_no"));
        ProductVO productVO=(ProductVO)session.getAttribute("productVO");
        ProtraService protraService = new ProtraService();

        if ("ADD".equals(action)) {
            String pro_no = productVO.getPro_no();
            ProtraVO protracking_listVO = new ProtraVO();
            protracking_listVO.setPro_no(pro_no);
            protracking_listVO.setMem_no(mem_no);
            protraService.addProtracking_list(protracking_listVO);
            request.getRequestDispatcher("/front_end/mall/product.jsp?pro_no=" + pro_no).forward(request, response);
        }
        else if ("DELETE".equals(action)) {
            String pro_no = productVO.getPro_no();
            protraService.deleteProductTracking(pro_no);
            request.getRequestDispatcher("/front_end/mall/product.jsp?pro_no=" + pro_no).forward(request, response);
        }
        else if("ADD_AJAX".equals(action)){
            String pro_no = request.getParameter("pro_no");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            ProtraVO protracking_listVO = new ProtraVO();
            protracking_listVO.setPro_no(pro_no);
            protracking_listVO.setMem_no(mem_no);
            protraService.addProtracking_list(protracking_listVO);
            printWriter.println("<a href=\"javascript:protracking('DELETE_AJAX',"+pro_no+")\" class='btn btn-danger'>取消追蹤</a>");
            printWriter.close();
        }
        else if("DELETE_AJAX".equals(action)){
            String pro_no = request.getParameter("pro_no");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            protraService.deleteProductTracking(pro_no);
            printWriter.print("<a href=\"javascript:protracking('ADD_AJAX',"+pro_no+")\" class='btn btn-info'>加入追蹤</a>");
            printWriter.close();
        }
        else if("CHANGE_AJAX".equals(action)){
            response.setContentType("text/html;charset=utf-8");
            int nowPage=Integer.valueOf(request.getParameter("nowPage"));
            int itemsCount=Integer.valueOf(request.getParameter("itemsCount"));
            int allCount=protraService.getRowCount(mem_no);
            int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
            List<ProtraVO>protraList=protraService.getAllByMem(mem_no);
            int start=(nowPage-1)*itemsCount;
            int end=(nowPage*itemsCount>protraList.size())?protraList.size():nowPage*itemsCount;
            List<ProductVO> productList=new ArrayList<ProductVO>();
            ProductService productService=new ProductService();
            for(int i=start;i<end;i++){
                productList.add(productService.getOneByPKNoImg(protraList.get(i).getPro_no()));
            }
            Gson gson =new Gson();
            PrintWriter printWriter=response.getWriter();
            JsonObject jsonObject=new JsonObject();
            jsonObject.add("productList", gson.toJsonTree(productList));
            jsonObject.add("totalPages", gson.toJsonTree(totalPages));
            printWriter.println(jsonObject);
            printWriter.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
