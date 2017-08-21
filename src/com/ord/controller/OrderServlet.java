package com.ord.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import com.email.MailService;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.order_detail.model.Order_detailService;
import com.order_detail.model.Order_detailVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.sun.scenario.effect.impl.prism.PrImage;

import sun.print.PrinterJobWrapper;

@WebServlet("/order/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("text/html; charset=utf-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        MemVO memVO = (MemVO) session.getAttribute("memVO");
        String errorMsg = "";
        OrdService ordService = new OrdService();
        ///////////////////////////////////////////////////// 訂單新增/////////////////////////////////////////////////////////////////
        if ("BUY".equals(action)) {
            String[] buyCounts = request.getParameterValues("buyCount");
            String address = request.getParameter("address");
            if (address.equals("")) {
                errorMsg = "請輸入地址";
                request.setAttribute("errorMsg", errorMsg);
                request.getRequestDispatcher("/Front_end/mall/checkout.jsp").forward(request, response);
            }
            else {
                List<ProductVO> carList = (List<ProductVO>) session.getAttribute("carList");
                List<String> sellerList = new ArrayList<String>();
                for (int i = 0; i < carList.size(); i++) {
                    if (!sellerList.contains(carList.get(i).getSeller_no())) {
                        sellerList.add(carList.get(i).getSeller_no());
                    }
                }
                ProductService productService = new ProductService();
                for (int i = 0; i < sellerList.size(); i++) {
                    List<Order_detailVO> order_detailList = new ArrayList<Order_detailVO>();
                    int total = 0;
                    for (int j = 0; j < carList.size(); j++) {
                        if (carList.get(j).getSeller_no().equals(sellerList.get(i))) {
                            Order_detailVO order_detailVO;
                            total += carList.get(j).getPrice() * Integer.valueOf(buyCounts[j]);
                            order_detailVO = new Order_detailVO();
                            order_detailVO.setPro_no(carList.get(j).getPro_no());
                            ProductVO productVO = productService.getOneByPK(carList.get(i).getPro_no());
                            order_detailVO.setPrice(carList.get(j).getPrice());
                            order_detailVO.setQty(Integer.valueOf(buyCounts[j]));
                            productVO.setAmount(productVO.getAmount() - Integer.valueOf(buyCounts[j]));
                            order_detailVO.setItemtot(Integer.valueOf(buyCounts[j]) * carList.get(i).getPrice());
                            order_detailVO.setScore(0);
                            order_detailVO.setStatus("0");
                            productService.update(productVO);
                            order_detailList.add(order_detailVO);
                        }
                    }
                    OrdVO ordVO = new OrdVO();
                    ordVO.setSeller_no(sellerList.get(i));
                    ordVO.setCust_no(memVO.getMem_no());
                    ordVO.setAddress(address);
                    ordVO.setOrd_date(new java.sql.Timestamp(System.currentTimeMillis()));
                    ordVO.setTotal(total);
                    ordVO.setScore(0);
                    ordVO.setStatus("0");
                    ordService.insert(ordVO, order_detailList);
                }
                List<Integer> countList = (session.getAttribute("countList") == null) ? new LinkedList<Integer>() : (List<Integer>) session.getAttribute("countList");
                int carTotal = 0;
                carList.removeAll(carList);
                countList.removeAll(countList);
                carTotal = 0;
                session.setAttribute("carList", carList);
                session.setAttribute("countList", countList);
                session.setAttribute("carTotal", new Integer(carTotal));
                // String to = "ixlogic@pchome.com.tw";
                // String to = new
                // MemService().getOneMem(productVO.getSeller_no()).getEmail();
                // String subject = "訂單確認通知";
                // String cust_name = memVO.getName();
                // String seller_account = new
                // MemService().getOneMem(sellerList.get(i)).getAccount();
                // String messageText = "Hello! " + cust_name +
                // "您的訂單已經確認，請付款,賣家帳戶 " + seller_account;
                // MailService mailService = new MailService();
                // mailService.sendMail(to, subject, messageText);
            }
            request.getRequestDispatcher("/Front_end/mall/mallArea.jsp").forward(request, response);
        }
        else if ("CHECK_GET_ITEM".equals(action)) {
            response.setCharacterEncoding("utf-8");
            String ord_no = request.getParameter("ord_no");
            OrdVO ordVO = ordService.getOneByPK(ord_no);
            ordVO.setStatus("2");
            ordService.update(ordVO);
//            String to = "ixlogic@pchome.com.tw";
            // String to = new MemService().getOneMem(mem_no).getEmail();
            // String subject = "訂單完成通知";
            // String cust_name = memVO.getName();
            // String messageText = "Hello! " + cust_name + "您的訂單已經完成，請評價 ";
            // MailService mailService = new MailService();
            // mailService.sendMail(to, subject, messageText);
            response.setContentType("text/html; charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            printWriter.print("OK");
            printWriter.close();
        }
        else if ("CHECK_GET_MONEY".equals(action)) {
            String ord_no = request.getParameter("ord_no");
            OrdVO ordVO = ordService.getOneByPK(ord_no);
            ordVO.setStatus("1");
            ordService.update(ordVO);
//            String to = "ixlogic@pchome.com.tw";
            // String to = new MemService().getOneMem(mem_no).getEmail();
            // String subject = "訂單出貨通知";
            // String cust_name = memVO.getName();
            // String messageText = "Hello! " + cust_name + "賣家已出貨，請耐心等待領收 ";
            // MailService mailService = new MailService();
            // mailService.sendMail(to, subject, messageText);
            response.setContentType("text/html; charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            printWriter.print("OK");
            printWriter.close();
        }
        else if ("CANCEL_AJAX".equals(action)) {
            String ord_no = request.getParameter("ord_no");
            OrdVO ordVO = ordService.getOneByPK(ord_no);
            Order_detailService order_detailService = new Order_detailService();
            ordVO.setStatus("4");
            ordService.update(ordVO);
            List<Order_detailVO> detailList = order_detailService.getAllByOrd(ord_no);
            ProductService productService = new ProductService();
            for (int i = 0; i < detailList.size(); i++) {
                ProductVO productVO = productService.getOneByPK(detailList.get(i).getPro_no());
                productVO.setAmount(productVO.getAmount() + detailList.get(i).getQty());
                productService.update(productVO);
            }
            response.setContentType("text/html; charset=utf-8");
            PrintWriter printWriter=response.getWriter();
            printWriter.print("OK");
            printWriter.close();
        }
        else if ("EVALUATION_TO_ITEM".equals(action)) {
            String ord_no = request.getParameter("ord_no");
            String orderType =  request.getParameter("orderType");
            String nowPage =  request.getParameter("nowPage");
            String[] pro_noList = request.getParameterValues("pro_no");
            if (request.getParameterValues("pro_no") == null) {
                request.getRequestDispatcher("/Front_end/mall/mallArea.jsp?role=0&&status=2").forward(request, response);
            }
            else {
                String[] scoreList = request.getParameterValues("score");
                Order_detailService order_detailService = new Order_detailService();
                ProductService productService = new ProductService();
                for (int i = 0; i < pro_noList.length; i++) {
                    Order_detailVO order_detailVO = order_detailService.getOneByComposite(ord_no, pro_noList[i]);
                    int score = (scoreList[i].equals("")) ? 0 : Integer.valueOf(scoreList[i]);
                    order_detailVO.setScore(score);
                    order_detailVO.setStatus("1");
                    order_detailService.updateOrder_detail(order_detailVO);
                    ProductVO productVO = productService.getOneByPK(pro_noList[i]);
                    productVO.setTimes(productVO.getTimes() + 1);
                    productVO.setScore(productVO.getScore() + score);
                    productService.update(productVO);
                }
                String url="/Front_end/mall/mallArea.jsp?role=1&&now_Status=3&&orderType="+orderType+"&&nowPage="+nowPage;
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
        else if ("EVALUATION_TO_MEM".equals(action)) {
            String ord_no = request.getParameter("ord_no");
            String orderType =  request.getParameter("orderType");
            String nowPage =  request.getParameter("nowPage");
            int score = (request.getParameter("score") == null) ? 0 : Integer.valueOf(request.getParameter("score"));
            OrdVO ordVO = ordService.getOneByPK(ord_no);
            ordVO.setScore(score);
            ordVO.setStatus("3");
            ordService.update(ordVO);
            String url="/Front_end/mall/mallArea.jsp?role=1&&now_Status=3&&orderType="+orderType+"&&nowPage="+nowPage;
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
