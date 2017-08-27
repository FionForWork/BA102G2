<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.protra.model.ProtraService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    MemVO memVO = (MemVO) session.getAttribute("memVO");
    MemService memService = new MemService();
    //  MemVO memVO = memService.getOneMem("1010");
    int nowPage = (request.getParameter("nowPage") == null) ? 1 : Integer.parseInt((request.getParameter("nowPage")));
    int itemsCount = 5;
    ProtraService protraService = new ProtraService();
    int allCount = protraService.getAllCount(memVO.getMem_no());
    int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
    ProductService productService = new ProductService();
    List<ProductVO> productList = new ArrayList<ProductVO>();
    
    List<String> protracking_list = protraService.getPage(nowPage, itemsCount, memVO.getMem_no());
    String preLocation = request.getContextPath() + "/Front_end/mall";
    
    for (int i = 0; i < protracking_list.size(); i++) {
        productList.add(productService.getOneByPKNoImg(protracking_list.get(i)));
    }
    String active = "2";
    pageContext.setAttribute("active", active);
    session.setAttribute("memVO", memVO);
    pageContext.setAttribute("nowPage", nowPage);
    pageContext.setAttribute("productList", productList);
    pageContext.setAttribute("preLocation", preLocation);
    pageContext.setAttribute("itemsCount", itemsCount);
    pageContext.setAttribute("totalPages", totalPages);
%>
<%@include file="pages/indexHeader.file"%>
<style>
.img {
	width: 80px;
	height: 80px;
}
</style>
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
                                <th>商品影像</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="productVO" items="${productList}" varStatus="s">
                                <tr class="animated fadeInUp" style="animation-duration: ${s.index*0.4}s;">
                                    <td><a target="_blank" href="${preLocation}/product.jsp?pro_no=${productVO.pro_no}"> ${productVO.pro_name} </a></td>
                                    <td>${productVO.price}</td>
                                    <td>${productVO.amount}</td>
                                    <td><img class="img" src="<%=request.getContextPath()%>/image/ShowImage?pro_no=${productVO.pro_no}"> <a href="javascript:protraDelete(${productVO.pro_no},${nowPage})" class="btn btn-danger">取消追蹤</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <%@include file="pages/navPage.file"%>
        </div>
    </div>
</div>
<script type="text/javascript">
    function pageChange(nowPage){ 
        $(window).scrollTop($(window).scrollTop());
        $(window).scrollLeft($(window).scrollLeft());
        $("table").load("<%=preLocation%>/protra.jsp table",{nowPage:nowPage});
        $(".pagination").load("<%=preLocation%>/protra.jsp .pagination", {nowPage: nowPage });
    }
    
    function protraDelete(pro_no,nowPage){ 
        $.ajax({
            url : "<%=request.getContextPath()%>/protra/ProtracServlet",
            type : "post",
            data : {
                action : "DELETE_AJAX",
                pro_no : pro_no,
            },
            error : function(xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            },
            success : function(response) {
                change(nowPage);
            }
        });
    }
</script>
<%@include file="pages/indexFooter.file"%>