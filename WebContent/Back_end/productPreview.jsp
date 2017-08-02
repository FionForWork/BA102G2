<%@page import="com.product_type.model.Product_typeVO"%>
<%@page import="com.product_type.model.Product_typeService"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.NEW"%>
<%@page import="com.product.model.ProductService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    ProductService productService = new ProductService();
    int nowPage = (request.getParameter("nowPage") == null) ? 1 : Integer.valueOf(request.getParameter("nowPage"));
    int itemCount = 8;
    int allCount = productService.getAllCountUnPreivew();
    int totalPages = allCount / itemCount + 1;
    List<ProductVO> productList = productService.getSomeUnPreview(nowPage, itemCount);
    String preLocation = request.getContextPath() + "/back_end";
    Product_typeService product_typeService=new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    
    pageContext.setAttribute("typeList", typeList);
    pageContext.setAttribute("preLocation", preLocation);
    session.setAttribute("productList", productList);
    pageContext.setAttribute("nowPage", nowPage);
    pageContext.setAttribute("totalPages", totalPages);
%>
<%@include file="pages/backHeader.file"%>
<div id="content">
    <div class="content-wrapper">
        <div class="row">
            <ul id="crumb" class="breadcrumb">
            </ul>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <table class="table">
            <thead>
                <tr>
                    <th>商品名稱</th>
                    <th>賣家編號</th>
                    <th>商品描述</th>
                    <th>商品類型</th>
                    <th>商品價格</th>
                    <th>商品庫存</th>
                    <th>商品影像</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="productVO" items="${productList}" >
                    <tr>
                        <td>${productVO.pro_name}</td>
                        <td>${productVO.seller_no}</td>
                        <td>${productVO.pro_desc}</td>
                        <td>${typeList.get(productVO.protype_no-1).type_name}</td>
                        <td>${productVO.price}</td>
                        <td>${productVO.amount}</td>
                        <td>
                            <img style="height: 80px;width: 80px;"src="<%=request.getContextPath()%>/image/ShowImage?pro_no=${productVO.pro_no}">
                        </td>
                        <td>
                            <a class="btn btn-success" href="<%=request.getContextPath()%>/product/ProductServlet?action=ABLE&&pro_no=${productVO.pro_no}">審核通過</a>
                            <a class="btn btn-danger" href="<%=request.getContextPath()%>/product/ProductServlet?action=DISABLE&&pro_no=${productVO.pro_no}">審核不通過</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!--//////////////////////////////////////////分頁開始//////////////////////////////////////////////////////////////// -->
        <div class="text-center">
            <nav aria-label="Page navigation ">
                <ul class="pagination pagination-lg ">
                    <c:choose>
                        <c:when test="${totalPages<=5}">
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class=""><a class="btn btn-primary" href="${preLocation}/productPreview.jsp?nowPage=${i}" data-page="${i}">${i}</a></li>
                            </c:forEach>
                        </c:when>
                        <c:when test="${nowPage<5}">
                            <c:forEach var="i" begin="1" end="5">
                                <li><a class="btn btn-primary" href="${preLocation}/productPreview.jsp?nowPage=${i}" data-page="${i}">${i}</a></li>
                            </c:forEach>
                            <li><a class="disabled">...</a></li>
                            <li><a class="btn btn-primary" href="${preLocation}/productPreview.jsp?nowPage=${totalPages}" data-page="${totalPages}">${totalPages}</a></li>
                        </c:when>
                        <c:when test="${totalPages-nowPage<5}">
                            <li><a class="btn btn-primary" href="${preLocation}/productPreview.jsp?nowPage=1" data-page="1">1</a></li>
                            <li><a class="disabled">...</a></li>
                            <c:forEach var="i" begin="${totalPages-5}" end="${totalPages}">
                                <li><a class="btn btn-primary" href="${preLocation}/productPreview.jsp?nowPage=${i}" data-page="${i}">${i}</a></li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li><a class="btn btn-primary" href="${preLocation}/productPreview.jsp?nowPage=1" data-page="1">1</a></li>
                            <li><a class="disabled">...</a></li>
                            <c:forEach var="i" begin="${nowPage-2}" end="${nowPage+2}">
                                <li><a class="btn btn-primary" href="${preLocation}/productPreview.jsp?nowPage=${i}" data-page="${i}">${i}</a></li>
                            </c:forEach>
                            <li><a class="disabled">...</a></li>
                            <li><a class="btn btn-primary" href="${preLocation}/productPreview.jsp?nowPage=${totalPages}" data-page="${totalPages}">${totalPages }</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </div>
    <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
</div>
</div>
<%@include file="pages/backFooter.file"%>