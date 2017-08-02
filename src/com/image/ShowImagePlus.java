package com.image;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductService;
import com.product.model.ProductVO;

public class ShowImagePlus extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("image/gif");
        HttpSession session = request.getSession();
        ProductService productService = new ProductService();

        if (request.getParameter("pro_no") != null) {
            String pro_no = request.getParameter("pro_no");
            ArrayList<ProductVO> products = (ArrayList<ProductVO>) session.getAttribute("productList");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getPro_no().equals(pro_no)) {
                    byte[] buf = products.get(i).getImg();
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
