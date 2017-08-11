<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page import="com.ord.model.OrdVO"%>
<%@page import="java.util.List"%>
<%@page import="com.ord.model.OrdService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="pages/mallIndexHeader.file"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    session.setAttribute("mem_no", "1010");
    String mem_no = String.valueOf(session.getAttribute("mem_no"));
    ProductService productService = new ProductService();
    List<ProductVO> productList = productService.getAllBySeller(mem_no);

    String preLocation = request.getContextPath() + "/Front_end/mall";
    pageContext.setAttribute("preLocation", preLocation);
    session.setAttribute("productList", productList);
%>
<div class="text-center" style="height: 50px; margin-top: 50px">
    <h3>全部商品平均評價</h3>
</div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <%@include file="pages/mallAreaSidebar.file"%>
            <div class="col-md-1"></div>
            <div class="col-md-7">
                <div class="col-md-7">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>商品名稱</th>
                                <th>平均評價</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="productVO" items="${productList}">
                                <tr>
                                    <td><a href="${preLocation}/product.jsp?pro_no=${productVO.pro_no}">${productVO.pro_name}</a></td>
                                    <c:choose>
                                        <c:when test="${productVO.score==0}">
                                            <td>尚無評價</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><fmt:formatNumber value="${productVO.score/productVO.times}" maxFractionDigits="1" /></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="pages/mallIndexFooter.file"%>