<%@page import="java.util.ArrayList"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page import="java.util.List"%>
<%@page import="com.protracking_list.model.Protracking_listVO"%>
<%@page import="com.protracking_list.model.Protracking_listService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="pages/mallIndexHeader.file"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String mem_no = "1010";
    int nowPage = (request.getParameter("nowPage") == null) ? 1 : Integer.parseInt((request.getParameter("nowPage")));
    int itemsCount = 5;
    Protracking_listService protracking_listService = new Protracking_listService();
    int allCount = protracking_listService.getRowCount(mem_no);
    int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
    ProductService productService = new ProductService();
    List<ProductVO> productList = new ArrayList<ProductVO>();

    List<String> protracking_list = protracking_listService.getSome(mem_no, nowPage, itemsCount);
    String preLocation = request.getContextPath() + "/front_end/mall";

    for (int i = 0; i < protracking_list.size(); i++) {
        productList.add(productService.getOneByPK(protracking_list.get(i)));
    }

    session.setAttribute("productList", productList);
    pageContext.setAttribute("preLocation", preLocation);
    pageContext.setAttribute("totalPages", totalPages);
%>
<div class="text-center" style="height: 50px; margin-top: 50px">
    <h2>您正在追蹤的商品</h2>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <%@include file="pages/mallAreaSidebar.file"%>
                <div class="col-md-1"></div>

                <div class="col-md-7">
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th>商品名稱</th>
                                <th>商品價格</th>
                                <th>商品庫存</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="productVO" items="${productList}">
                                <tr>
                                    <td>
                                        <a target="_blank" href="${preLocation}/product.jsp?pro_no=${productVO.pro_no}"> ${productVO.pro_name} </a>
                                    </td>
                                    <td>${productVO.price}</td>
                                    <td>${productVO.amount}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--//////////////////////////////////////////分頁//////////////////////////////////////////////////////////////// -->
            <div class="text-center ">
                <nav aria-label="Page navigation ">
                    <ul class="pagination pagination-lg ">
                        <%
                            if (totalPages <= 5) {
                        %>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class=""><a class="btn btn-primary" href="${preLocation}/protracking_list.jsp?nowPage=${i}" data-page="${i}">${i}</a></li>
                        </c:forEach>

                        <%
                            }
                            else {
                                if (nowPage < 5) {
                        %>

                        <c:forEach var="i" begin="1" end="5">
                            <li><a class="btn btn-primary" href="${preLocation}/protracking_list.jsp?nowPage=${i}" data-page="${i}">${i}</a></li>
                        </c:forEach>
                        <li><a class="disabled">...</a></li>
                        <li><a class="btn btn-primary" href="${preLocation}/protracking_list.jsp?nowPage=${totalPages}">${totalPages}</a></li>

                        <%
                            }
                                else if (totalPages - nowPage < 5) {
                        %>

                        <li><a class="btn btn-primary" href="${preLocation}/protracking_list.jsp?nowPage=1" data-page="1">1</a></li>
                        <li><a class="disabled">...</a></li>
                        <c:forEach var="i" begin="<%=totalPages-5%>" end="${totalPages}">
                            <li><a class="btn btn-primary" href="${preLocation}/protracking_list.jsp?nowPage=${i}" data-page="${i}">${i}</a></li>
                        </c:forEach>

                        <%
                            }
                                else {
                        %>

                        <li><a class="btn btn-primary" href="${preLocation}/protracking_list.jsp?nowPage=${i}" data-page="1">1</a></li>
                        <li><a class="disabled">...</a></li>
                        <c:forEach var="i" begin="<%=nowPage - 2%>" end="<%=nowPage + 2%>">
                            <li><a class="btn btn-primary" href="${preLocation}/protracking_list.jsp?nowPage=${i}" data-page="${i}">${i}</a></li>
                        </c:forEach>
                        <li><a class="disabled">...</a></li>
                        <li><a class="btn btn-primary" href="${preLocation}/protracking_list.jsp?nowPage=${i}" data-page="${totalPages}">${totalPages}</a></li>

                        <%
                            }
                            }
                        %>
                    </ul>
                </nav>
            </div>
            <!--//////////////////////////////////////////分頁//////////////////////////////////////////////////////////////// -->
        </div>
    </div>
</div>
<%@include file="pages/mallIndexFooter.file"%>