package com.ord.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.order_detail.model.Order_detailService;
import com.order_detail.model.Order_detailVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/order/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("text/html; charset=utf-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String mem_no = String.valueOf(session.getAttribute("mem_no"));
        String errorMsg = "";
        OrdService ordService = new OrdService();
        ///////////////////////////////////////////////////// 訂單新增/////////////////////////////////////////////////////////////////
        if ("BUY".equals(action)) {
            String[] buyCounts = request.getParameterValues("buyCount");
            String address = request.getParameter("address");
            if (address.equals("")) {
                errorMsg = "請輸入地址";
                request.setAttribute("errorMsg", errorMsg);
                request.getRequestDispatcher("/front_end/mall/checkout.jsp").forward(request, response);
            }
            else {
                List<ProductVO> carList = (List<ProductVO>) session.getAttribute("carList");
                List<String> sellerList = new ArrayList<String>();
                List<OrdVO> ordList = new ArrayList<OrdVO>();
                for (int i = 0; i < carList.size(); i++) {
                    if (!sellerList.contains(carList.get(i).getSeller_no())) {
                        sellerList.add(carList.get(i).getSeller_no());
                    }
                }

                for (int i = 0; i < sellerList.size(); i++) {
                    int total = 0;
                    for (int j = 0; j < carList.size(); j++) {
                        if (carList.get(j).getSeller_no().equals(sellerList.get(i))) {
                            total += carList.get(j).getPrice() * Integer.valueOf(buyCounts[j]);
                        }
                    }
                    OrdVO ordVO = new OrdVO();
                    ordVO.setSeller_no(sellerList.get(i));
                    ordVO.setCust_no(String.valueOf(session.getAttribute("mem_no")));
                    ordVO.setAddress(address);
                    ordVO.setOrd_date(new java.sql.Timestamp(new Date().getTime()));
                    ordVO.setTotal(total);
                    ordVO.setScore(0);
                    ordVO.setStatus("0");
                    ordService.addOrd(ordVO);
                    ordList.add(ordService.getOneOrdByCustAndSeller(String.valueOf(session.getAttribute("mem_no")), sellerList.get(i)));
                }
                Order_detailService order_detailService = new Order_detailService();
                for (int i = 0; i < ordList.size(); i++) {
                    for (int j = 0; j < carList.size(); j++) {
                        if (ordList.get(i).getSeller_no().equals(carList.get(j).getSeller_no())) {
                            Order_detailVO order_detailVO = new Order_detailVO();
                            order_detailVO.setOrd_no(ordList.get(i).getOrd_no());
                            order_detailVO.setPro_no(carList.get(j).getPro_no());
                            order_detailVO.setPrice(carList.get(j).getPrice());
                            order_detailVO.setQty(Integer.valueOf(buyCounts[j]));
                            order_detailVO.setItemtot(carList.get(j).getPrice() * Integer.valueOf(buyCounts[j]));
                            order_detailVO.setScore(0);
                            order_detailVO.setStatus("0");
                            order_detailService.addOrder_detail(order_detailVO);
                        }
                    }
                }
                request.getRequestDispatcher("/Front_end/mall/mallIAJAXndex.jsp").forward(request, response);
            }
        }
        else if ("CHECK_GET_ITEM".equals(action)) {
            String ord_no = request.getParameter("ord_no");
            OrdVO ordVO = ordService.getOneOrd(ord_no);
            ordVO.setStatus("2");
            ordService.updateOrd(ordVO);
            request.getRequestDispatcher("/front_end/mall/mallArea.jsp&&role=0").forward(request, response);
        }
        else if ("CHECK_GET_MONEY".equals(action)) {
            String ord_no = request.getParameter("ord_no");
            OrdVO ordVO = ordService.getOneOrd(ord_no);
            ordVO.setStatus("1");
            ordService.updateOrd(ordVO);
            request.getRequestDispatcher("/front_end/mall/mallArea.jsp&&role=1").forward(request, response);
        }
        else if ("CANCEL".equals(action)) {
            String ord_no = request.getParameter("ord_no");
            OrdVO ordVO = ordService.getOneOrd(ord_no);
            ordVO.setStatus("3");
            ordService.updateOrd(ordVO);
            request.getRequestDispatcher("/front_end/mall/mallArea.jsp").forward(request, response);
        }
        else if ("EVALUATION_TO_ITEM".equals(action)) {
            String ord_no = request.getParameter("ord_no");
            String[] pro_noList = request.getParameterValues("pro_no");
            if (request.getParameterValues("pro_no") == null) {
                request.getRequestDispatcher("/front_end/mall/mallArea.jsp?role=0&&now_Status=2").forward(request, response);
            }
            else {
                String[] scoreList = request.getParameterValues("score");
                Order_detailService order_detailService = new Order_detailService();
                ProductService productService = new ProductService();
                for (int i = 0; i < pro_noList.length; i++) {
                    Order_detailVO order_detailVO = order_detailService.getOneOrder_detailVO(ord_no, pro_noList[i]);
                    int score = (scoreList[i].equals("")) ? 0 : Integer.valueOf(scoreList[i]);
                    order_detailVO.setScore(score);
                    order_detailVO.setStatus("1");
                    order_detailService.updateOrder_detail(order_detailVO);
                    ProductVO productVO = productService.getOneByPK(pro_noList[i]);
                    productVO.setTimes(productVO.getTimes() + 1);
                    productVO.setScore(productVO.getScore() + score);
                    productService.updateProduct(productVO);
                }
                request.getRequestDispatcher("/front_end/mall/mallArea.jsp?role=0&&now_Status=2").forward(request, response);
            }
        }
        else if ("EVALUATION_TO_MEM".equals(action)) {
            String ord_no = request.getParameter("ord_no");
            int score = (request.getParameter("score") == null) ? 0 : Integer.valueOf(request.getParameter("score"));
            System.out.println(score);
            OrdVO ordVO = ordService.getOneOrd(ord_no);
             ordVO.setScore(score);
             ordVO.setStatus("3");
             ordService.updateOrd(ordVO);
            request.getRequestDispatcher("/front_end/mall/mallArea.jsp?role=1&&now_Status=3").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
