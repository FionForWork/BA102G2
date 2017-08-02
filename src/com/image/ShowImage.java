package com.image;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.place.model.PlaceVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

public class ShowImage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("image/gif");
        HttpSession session = request.getSession();
        ProductService productService = new ProductService();

        if (request.getParameter("pro_no") != null) {
            String pro_no = request.getParameter("pro_no");
//            ArrayList<ProductVO> productList = (ArrayList<ProductVO>) session.getAttribute("productList");
//            ProductVO productVO=productService.getOneByPK(pro_no);
//            ServletOutputStream servletOutputStream = response.getOutputStream();
//            for (int i = 0; i < productList.size(); i++) {
//                if (productList.get(i).getPro_no().equals(pro_no)) {
//                    byte[] buf = productList.get(i).getImg();
//                    servletOutputStream.write(buf);
//                    return;
//                }
//            }
//            byte[] buf=productVO.getImg();
//            servletOutputStream.write(buf);
//            servletOutputStream.close();
        }
        else if (request.getParameter("pla_no") != null) {
            String pla_no = request.getParameter("pla_no");
            ArrayList<PlaceVO> placeList = (ArrayList<PlaceVO>) session.getAttribute("placeList");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            for (int i = 0; i < placeList.size(); i++) {
                if (placeList.get(i).getPla_no().equals(pla_no)) {
                    byte[] buf = placeList.get(i).getImg();
                    servletOutputStream.write(buf);
                    return;
                }
            }
            servletOutputStream.close();
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
