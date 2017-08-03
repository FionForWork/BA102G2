package com.image;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.place.model.PlaceService;
import com.place.model.PlaceVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

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
        else if (request.getParameter("pla_no") != null) {
            PlaceService placeService = new PlaceService();
            String pla_no = request.getParameter("pla_no");
            PlaceVO placeVO=placeService.getOnePlace(pla_no);
            buf=placeVO.getImg();
        }
        servletOutputStream.write(buf);
        servletOutputStream.close();
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
