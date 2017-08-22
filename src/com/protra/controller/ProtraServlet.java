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

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mem.model.MemVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.protra.model.ProTraService;
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
        MemVO memVO=(MemVO)session.getAttribute("memVO");
        String mem_no = memVO.getMem_no();
        ProductVO productVO=(ProductVO)session.getAttribute("productVO");
        ProTraService protraService = new ProTraService();

        if ("ADD".equals(action)) {
            String pro_no = productVO.getPro_no();
            ProtraVO protraVO = new ProtraVO();
            protraVO.setPro_no(pro_no);
            protraVO.setMem_no(mem_no);
            protraService.insert(protraVO);
            request.getRequestDispatcher("/front_end/mall/product.jsp?pro_no=" + pro_no).forward(request, response);
        }
        else if ("DELETE".equals(action)) {
            String pro_no = productVO.getPro_no();
            protraService.deleteByFK(pro_no);
            request.getRequestDispatcher("/front_end/mall/product.jsp?pro_no=" + pro_no).forward(request, response);
        }
        else if("ADD_AJAX".equals(action)){
            String pro_no = request.getParameter("pro_no");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            ProtraVO protraVO = new ProtraVO();
            protraVO.setPro_no(pro_no);
            protraVO.setMem_no(mem_no);
            protraService.insert(protraVO);
            printWriter.println("<a href=\"javascript:protracking('DELETE_AJAX',"+pro_no+")\" class='btn btn-danger'>取消追蹤</a>");
            printWriter.close();
        }
        else if("DELETE_AJAX".equals(action)){
            String pro_no = request.getParameter("pro_no");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            protraService.deleteByComposite(pro_no, mem_no);
            printWriter.print("<a href=\"javascript:protracking('ADD_AJAX',"+pro_no+")\" class='btn btn-info'>加入追蹤</a>");
            printWriter.close();
        }
        else if("CHANGE_AJAX".equals(action)){
            response.setContentType("text/html;charset=utf-8");
            List<ProtraVO>protraList=protraService.getAllByMem(mem_no);
            if(protraList.size()>0){
                int nowPage=Integer.valueOf(request.getParameter("nowPage"));
                int itemsCount=Integer.valueOf(request.getParameter("itemsCount"));
                int start=(nowPage-1)*itemsCount;
                int end=(nowPage*itemsCount>protraList.size())?protraList.size():nowPage*itemsCount;
                List<ProductVO> productList=new ArrayList<ProductVO>();
                ProductService productService=new ProductService();
                for(int i=start;i<end;i++){
                    productList.add(productService.getOneByPKNoImg(protraList.get(i).getPro_no()));
                }
                PrintWriter printWriter=response.getWriter();
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("productList", productList);
                    printWriter.println(jsonObject.toString());
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                printWriter.close();
            }
            else{
                
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
