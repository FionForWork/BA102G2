package com.image;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.placeview.model.PlaceViewService;
import com.placeview.model.PlaceViewVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/image/ShowImage")
public class ShowImage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("image/gif");
        byte[] buf=null;
        ServletOutputStream servletOutputStream = response.getOutputStream();
        if (request.getParameter("pro_no") != null) {
            ProductService productService = new ProductService();
            String pro_no = request.getParameter("pro_no");
            ProductVO productVO=productService.getOneByPK(pro_no);
            buf=productVO.getImg();
            
        }
        else if (request.getParameter("view_no") != null) {
            PlaceViewService placeViewService = new PlaceViewService();
            String view_no = request.getParameter("view_no");
            PlaceViewVO placeViewVO =placeViewService.getOneByPK(view_no);
            buf=placeViewVO.getImg();
        }
        servletOutputStream.write(buf);
        servletOutputStream.close();
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
