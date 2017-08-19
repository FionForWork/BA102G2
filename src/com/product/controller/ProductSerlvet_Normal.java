package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mem.model.MemVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class ProductSerlvet_Normal
 */
@WebServlet("/ProductSerlvet_Normal")
public class ProductSerlvet_Normal extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        MemVO memVO = (MemVO) session.getAttribute("memVO");
        List<ProductVO> carList = (session.getAttribute("carList") == null) ? new LinkedList<ProductVO>() : (List<ProductVO>) session.getAttribute("carList");
        List<Integer> countList = (session.getAttribute("countList") == null) ? new LinkedList<Integer>() : (List<Integer>) session.getAttribute("countList");
        int carTotal = 0;
        ///////////////////////////////////////////////////// 加入購物車/////////////////////////////////////////////////////////////////
        if ("ADD_TO_CAR".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            ProductVO productVO = (ProductVO) session.getAttribute("productVO");
            if (carList.contains(productVO)) {
                int count = countList.get(carList.indexOf(productVO)).intValue();
                count += Integer.valueOf(request.getParameter("product_account"));
                countList.set(carList.indexOf(productVO), new Integer(count));
            }
            else {
                carList.add(productVO);
                countList.add(new Integer(request.getParameter("product_account")));
            }
            for (int i = 0; i < carList.size(); i++) {
                carTotal += carList.get(i).getPrice() * countList.get(i);
            }
            session.setAttribute("carList", carList);
            session.setAttribute("countList", countList);
            session.setAttribute("carTotal", new Integer(carTotal));
            request.getRequestDispatcher(String.valueOf(session.getAttribute("location"))).forward(request, response);
        }
        ///////////////////////////////////////////////////// 刪除購物車一項物品/////////////////////////////////////////////////////////////////
        else if ("DELETE_FROM_CAR".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            String pro_no = request.getParameter("pro_no");
            for (int i = 0; i < carList.size(); i++) {
                if (pro_no.equals(carList.get(i).getPro_no())) {
                    carList.remove(i);
                    countList.remove(i);
                }
            }
            for (int i = 0; i < carList.size(); i++) {
                carTotal += carList.get(i).getPrice() * countList.get(i);
            }
            session.setAttribute("carList", carList);
            session.setAttribute("countList", countList);
            session.setAttribute("carTotal", new Integer(carTotal));
            request.getRequestDispatcher("/Front_end/mall/checkout.jsp").forward(request, response);
        }
        ///////////////////////////////////////////////////// 清空購物車/////////////////////////////////////////////////////////////////
        else if ("CLEAR".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            if (carList != null) {
                carList.removeAll(carList);
                countList.removeAll(countList);
                carTotal = 0;
                session.setAttribute("carList", carList);
                session.setAttribute("countList", countList);
                session.setAttribute("carTotal", new Integer(carTotal));
            }
            request.getRequestDispatcher("/Front_end/mall/mallIndex.jsp").forward(request, response);
        }
        ///////////////////////////////////////////////////// 申請上架商品/////////////////////////////////////////////////////////////////
        else if ("ADD".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            List<String> error = new ArrayList<String>();
            ProductVO productVO = new ProductVO();
            String seller_no = memVO.getMem_no();
            String pro_desc = request.getParameter("pro_desc");
            String price = request.getParameter("price");
            String amount = request.getParameter("amount");
            String protype_no = request.getParameter("protype_no");
            String pro_name = request.getParameter("pro_name").trim();
            System.out.println(pro_name);
            byte[] data = null;
            if (pro_name.equals("")) {
                error.add("請輸入商品名稱");
            }
            if (price.equals("")) {
                error.add("請輸入價格");
            }
            if (price.equals("")) {
                error.add("請輸入庫存數量");
            }
            if (protype_no == null) {
                error.add("請選擇商品種類");
            }
            Part part = request.getPart("img");
            if (part == null || part.getSize() == 0) {
                error.add("請上傳商品影像");
            }
            else {
                InputStream inputStream = part.getInputStream();
                data = new byte[inputStream.available()];
                inputStream.read(data);
            }

            if (!error.isEmpty()) {
                for (String msg : error) {
                    System.out.println(msg);
                }
                request.getRequestDispatcher("/Front_end/mall/productManagement.jsp").forward(request, response);
            }
            else {
                productVO.setPro_name(pro_name);
                productVO.setSeller_no(seller_no);
                productVO.setPro_desc(pro_desc);
                productVO.setPrice(Integer.valueOf(price));
                productVO.setAmount(Integer.valueOf(amount));
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                // productVO.setPro_date(new java.sql.Date(date.getTime()));
                productVO.setPro_date(timestamp);
                productVO.setProtype_no(protype_no);
                productVO.setImg(data);
                productVO.setStatus("0");
                productVO.setTimes(0);
                productVO.setScore(0);

                ProductService productService = new ProductService();
                productService.insert(productVO);
                System.out.println("ADD SUCESS");
                request.getRequestDispatcher("/Front_end/mall/productManagement.jsp").forward(request, response);
            }
        }
        ///////////////////////////////////////////////////// 下架商品/////////////////////////////////////////////////////////////////
        else if ("OFF".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            String pro_no = request.getParameter("pro_no");
            List<ProductVO> productList = (List<ProductVO>) session.getAttribute("productList");
            ProductService productService = new ProductService();
            for (ProductVO productVO : productList) {
                if (productVO.getPro_no().equals(pro_no)) {
                    productVO.setStatus("2");
                    productService.update(productVO);
                    request.getRequestDispatcher("/Front_end/mall/productManagement.jsp").forward(request, response);
                }
            }
        }
        ///////////////////////////////////////////////////// 上架商品/////////////////////////////////////////////////////////////////
        else if ("ON".equals(action)) {
            response.setCharacterEncoding("text/html; charset=utf-8");
            String pro_no = request.getParameter("pro_no");
            List<ProductVO> productList = (List<ProductVO>) session.getAttribute("productList");
            ProductService productService = new ProductService();
            for (ProductVO productVO : productList) {
                if (productVO.getPro_no().equals(pro_no)) {
                    productVO.setStatus("1");
                    productService.update(productVO);
                    request.getRequestDispatcher("/Front_end/mall/productManagement.jsp").forward(request, response);
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
