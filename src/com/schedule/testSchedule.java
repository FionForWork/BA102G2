package com.schedule;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class testSchedul
 */
@WebServlet("/testSchedul")
public class testSchedule extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Timer              timer            = new Timer();

    public void destroy() {
        timer.cancel();
    }

    public void init() {
        ProductService productService = new ProductService();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                int itemsCount = (int) Math.round(Math.random() * 10 + 1);
                while (itemsCount > 0) {
                    String pro_no = String.valueOf(Math.round((Math.random() * 100) + 4001));
                    int score = (int) Math.round((Math.random() * 5));
                    ProductVO productVO = productService.getOneByPK(pro_no);
                    try{
                        productVO.setTimes(productVO.getTimes() + 1);
                        productVO.setScore(productVO.getScore() + score);
                        productService.update(productVO);
                    }
                    catch (NullPointerException e) {
                        System.out.println(productVO.getPro_no()+" is null");
                    }
                    itemsCount--;
                }
            }
        };
        timer.schedule(task, 0 * 1000, 1 * 1000);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
