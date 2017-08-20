package com.order_detail.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order_detail.model.Order_detailVO;

@WebServlet("/order_detail/Order_detailServlet")
public class Order_detailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Order_detailServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("text/html; charset=utf-8");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
