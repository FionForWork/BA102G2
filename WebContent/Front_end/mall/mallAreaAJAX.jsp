<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page import="com.order_detail.model.Order_detailVO"%>
<%@page import="com.order_detail.model.Order_detailService"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ord.model.OrdVO"%>
<%@page import="java.util.List"%>
<%@page import="com.ord.model.OrdService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String mem_no = "1010";
    MemberService memberService = new MemberService();
    String mem_name = memberService.getOneMem(mem_no).getName();
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    session.setAttribute("mem_no", mem_no);
    String cust_no = String.valueOf(session.getAttribute("mem_no"));
    String role = (request.getParameter("role") == null) ? "0" : request.getParameter("role");
    String now_Status = (request.getParameter("now_Status") == null) ? "0" : request.getParameter("now_Status");
    String now_Order_Type = (request.getParameter("now_Order_Type") == null) ? "0" : request.getParameter("now_Order_Type");
    String[] statusList = { "買家未付款", "賣家未出貨", "已完成訂單", "賣家已評價", "已取消訂單" };
    String[] orderTypeList = { "依對方編號小>>大", "依對方編號大>>小", "依寄送地址", "依成立日期近>>遠", "依成立日期遠>>近", "依訂單總價小>>大", "依訂單總價大>>小" };

    OrdService ordService = new OrdService();
    List<OrdVO> ordList = (ordService.getAllByRoleAndOrder(role, cust_no, now_Status, now_Order_Type) == null) ? new ArrayList<OrdVO>() : ordService.getAllByRoleAndOrder(role, cust_no, now_Status, now_Order_Type);
    List<String> seller_accountList = new ArrayList<String>();
    List<String> cust_nameList = new ArrayList<String>();
    for (int i = 0; i < ordList.size(); i++) {
        seller_accountList.add(memberService.getOneMem(ordList.get(i).getSeller_no()).getAccount());
        cust_nameList.add(memberService.getOneMem(ordList.get(i).getCust_no()).getName());
    }
    Order_detailService order_detailService = new Order_detailService();
    ProductService productService = new ProductService();

    String preLocation = request.getContextPath() + "/front_end/mall";
    List<ProductVO> productList = new ArrayList<ProductVO>();
    session.setAttribute("mem_name", mem_name);
    session.setAttribute("productList", productList);
    session.setAttribute("memberService", memberService);

    pageContext.setAttribute("productService", productService);
    pageContext.setAttribute("order_detailService", order_detailService);
    pageContext.setAttribute("cust_nameList", cust_nameList);
    pageContext.setAttribute("seller_accountList", seller_accountList);
    pageContext.setAttribute("orderTypeList", orderTypeList);
    pageContext.setAttribute("preLocation", preLocation);
    pageContext.setAttribute("statusList", statusList);
    pageContext.setAttribute("role", role);
    pageContext.setAttribute("now_Status", now_Status);
    pageContext.setAttribute("now_Order_Type", now_Order_Type);
    pageContext.setAttribute("ordList", ordList);
%>
<%@include file="pages/mallIndexHeader.file"%>
<div class="text-center" style="height: 50px; margin-top: 50px">
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <%@include file="pages/mallAreaAJAXSidebar.file"%>
            <div class="col-md-1"></div>
            <div class="col-md-7">
                <ul class="nav nav-tabs">
                <c:forEach var="status" items="${statusList}" varStatus="s">
                    <li><a data-toggle="tab" href="#status${s.index}">${status}</a></li>
                </c:forEach>
                </ul>
                <div class="tab-content">
                <c:forEach var="status" items="${statusList}" varStatus="s">
                    <c:choose>
                        <c:when test="${s.index==0}">
                            <div id="status${s.index}" class="tab-pane fade active in">
                        </c:when>
                        <c:otherwise>
                            <div id="status${s.index}" class="tab-pane fade">
                        </c:otherwise>
                    </c:choose>
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>1</th>
                                    <th>2</th>
                                    <th>3</th>
                                    <th>4</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>11</td>
                                    <td>22</td>
                                    <td>33</td>
                                    <td>44</td>
                                </tr>
                            </tbody>
                        </table>                    
                    </div>
                </c:forEach>
                </div>
            </div>
            <div class="col-md-2">
                <div class="btn-group">
                    <button id="type" class="btn btn-default">排序方式</button>
                    <button data-toggle="dropdown" class="btn btn-default dropdown-toggle">
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <c:forEach var="orderType" items="${orderTypeList}" varStatus="s">
                            <li>
                                <a href="${preLocation}/mallArea.jsp?now_Status=${now_Status}&&role=${role}&&now_Order_Type=${s.index}">${orderType}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="pages/mallIndexFooter.file"%>