<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page import="com.ord.model.OrdVO"%>
<%@page import="java.util.List"%>
<%@page import="com.ord.model.OrdService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    MemVO memVO = (MemVO) session.getAttribute("memVO");
    MemService memService = new MemService();
    //  MemVO memVO = memService.getOneMem("1010");
    String mem_no = String.valueOf(session.getAttribute("mem_no"));
    ProductService productService = new ProductService();
    int nowPage = (request.getParameter("nowPage") == null)
            ? 1
            : Integer.valueOf(request.getParameter("nowPage"));
    int itemsCount = 8;
    int allCount = productService.getAllCountBySeller(memVO.getMem_no());
    int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
    List<ProductVO> productList = productService.getPageBySeller(nowPage, itemsCount, memVO.getMem_no());
    
    String preLocation = request.getContextPath() + "/Front_end/mall";
    pageContext.setAttribute("preLocation", preLocation);
    pageContext.setAttribute("itemsCount", itemsCount);
    pageContext.setAttribute("nowPage", nowPage);
    pageContext.setAttribute("totalPages", totalPages);
    pageContext.setAttribute("productList", productList);		
%>
<%@include file="pages/indexHeader.file"%>
<div class="text-center" style="height: 50px; margin-top: 50px">
    <h3>全部商品平均評價</h3>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <%@include file="pages/mallAreaSidebar.file"%>
            <div class="col-md-2"></div>
            <div class="col-md-6">
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
        <%@include file="pages/navPage.file"%>
    </div>
</div>
<script type="text/javascript">
    function pageChange(nowPage){ 
        $(window).scrollTop("0");
        $(window).scrollLeft("0");
        $("table").load("<%=preLocation%>/productScore.jsp table",{"nowPage":nowPage});
        $(".pagination").load("<%=preLocation%>/productScore.jsp .pagination", {
            "nowPage" : nowPage
        });
    }
</script>
<%@include file="pages/indexFooter.file"%>