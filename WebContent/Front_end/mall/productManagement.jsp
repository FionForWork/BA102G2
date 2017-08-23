<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.product_type.model.Product_typeVO"%>
<%@page import="com.product_type.model.Product_typeService"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.product.model.ProductService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
     MemVO memVO=(MemVO)session.getAttribute("memVO");
    MemService memService = new MemService();
//  MemVO memVO = memService.getOneMem("1010");
    ProductService productService = new ProductService();
    int nowPage = (request.getParameter("nowPage") == null)? 1: Integer.valueOf(request.getParameter("nowPage"));
    int itemsCount = 8;
    int allCount = productService.getAllCountBySeller(memVO.getMem_no());
    int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
    List<ProductVO> productList = productService.getPageBySeller(nowPage, itemsCount, memVO.getMem_no());
    Product_typeService product_typeService = new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    pageContext.setAttribute("typeList", typeList);

    String preLocation = request.getContextPath() + "/Front_end/mall";
    pageContext.setAttribute("preLocation", preLocation);
    pageContext.setAttribute("totalPages", totalPages);
    pageContext.setAttribute("nowPage", nowPage);
    session.setAttribute("productList", productList);
%>
<%@include file="pages/indexHeader.file"%>
<style>
.addImg, .updateImg {
    width: 200px;
    height: 200px;
    border-color: black;
    border: 1px;
    border: solid;
}

.img {
    width: 100%;
    height: 100%;
}
</style>
<div class="text-center" style="height: 50px; margin-top: 50px">
    <h2>商品資料管理</h2>
</div>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-md-12">
            <div id="content" class="row">
                <%@include file="pages/mallAreaSidebar.file"%>
                <div class="col-xs-1 col-md-1"></div>
                <div class="col-xs-7 col-md-7">
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th>商品名稱</th>
                                <th>價格</th>
                                <th>商品評價</th>
                                <th>修改資訊</th>
                                <th>上/下架</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="productVO" items="${productList}" varStatus="s">
                                <tr class="animated fadeInLeft" style="animation-duration: ${s.index*0.4}s;">
                                    <td><a target="_blank" href="${preLocation}/product.jsp?pro_no=${productVO.pro_no}">${productVO.pro_name}</a></td>
                                    <td>${productVO.price}</td>
                                    <c:choose>
                                        <c:when test="${productVO.score==0}">
                                            <td>尚無評價</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><fmt:formatNumber value="${productVO.score/productVO.times}" maxFractionDigits="1" /></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>
                                        <button type="button" class="btn btn-info" onclick="showUpdate('${productVO.pro_no}')">商品資料修改</button>
                                    </td>
                                    <c:choose>
                                        <c:when test="${productVO.status=='0'}">
                                            <td>商品審核中</td>
                                        </c:when>
                                        <c:when test="${productVO.status=='1'}">
                                            <td>
                                                <div id="status${productVO.pro_no}">
                                                    <a class="btn btn-danger" href="javascript:onOrOff(${productVO.pro_no})">商品下架</a>
                                                </div>
                                            </td>
                                        </c:when>
                                        <c:when test="${productVO.status=='2'}">
                                            <td>
                                                <div id="status${productVO.pro_no}">
                                                    <a class="btn btn-info" href="javascript:onOrOff(${productVO.pro_no})">商品上架</a>
                                                </div>
                                            </td>
                                        </c:when>
                                        <c:when test="${productVO.status=='3'}">
                                            <td>
                                                <div id="status${productVO.pro_no}">
                                                    <p>審核未通過</p>
                                                </div>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <div id="status${productVO.pro_no}">
                                                    <p>被檢舉下架</p>
                                                </div>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-xs-2 col-md-2">
                    <button type="button" class="btn btn-info" onclick="showAdd()">申請新商品上架</button>
                </div>
            </div>
            <!--//////////////////////////////////////////分頁開始//////////////////////////////////////////////////////////////// -->
            <div class="text-center ">
                <nav aria-label="Page navigation ">
                    <ul class="pagination pagination-lg ">
                        <c:choose>
                            <c:when test="${totalPages<=5}">
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <c:choose>
                                        <c:when test="${nowPage==i}">
                                            <li class=""><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class=""><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:when test="${nowPage<5}">
                                <c:forEach var="i" begin="1" end="5">
                                    <c:choose>
                                        <c:when test="${nowPage==i}">
                                            <li class=""><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class=""><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <li><a class="disabled">...</a></li>
                                <li><a class="btn btn-info" href="javascript:change(${totalPages})" data-page="${totalPages}">${totalPages}</a></li>
                            </c:when>
                            <c:when test="${totalPages-nowPage<5}">
                                <li><a class="btn btn-info" href="javascript:change(1)" data-page="1">1</a></li>
                                <li><a class="disabled">...</a></li>
                                <c:forEach var="i" begin="${totalPages-5}" end="${totalPages}">
                                    <c:choose>
                                        <c:when test="${nowPage==i}">
                                            <li class=""><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class=""><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <li><a class="btn btn-info" href="javascript:change(1)" data-page="1">1</a></li>
                                <li><a class="disabled">...</a></li>
                                <c:forEach var="i" begin="${nowPage-2}" end="${nowPage+2}">
                                    <c:choose>
                                        <c:when test="${nowPage==i}">
                                            <li class=""><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class=""><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <li><a class="disabled">...</a></li>
                                <li><a class="btn btn-info" href="javascript:change(${totalPages})" data-page="${totalPages}">${totalPages}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>
            </div>
        </div>
        <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
    </div>
</div>
﻿<div class="modal fade" id="productUpdate"></div>
﻿<div class="modal fade" id="productAdd"></div>
<script>
    $("#productUpdate").on("hidden.bs.modal", function() {  
        $(this).removeData("modal");  
      });

    $("#productAdd").on("hidden.bs.modal", function() {  
        $(this).removeData("modal");  
      });
    
    function showUpdate(pro_no) {
        $("#productUpdate").load("${preLocation}/pages/productUpdate.jsp",{pro_no:pro_no,nowPage:${nowPage}},
                function () {
                    $("#productUpdate").modal("show");
                }
        );
    }
    
    function showAdd() {
        $("#productAdd").load("${preLocation}/pages/productAdd.jsp",
                function () {
                    $("#productAdd").modal("show");
                }
        );
    }
    
    
    function change(nowPage){
        $(".pagination").load("${preLocation}/productManagement.jsp .pagination",{"nowPage":nowPage});
        $("#content").load("${preLocation}/productManagement.jsp #content",{"nowPage":nowPage});
        $(window).scrollLeft("0");
        $(window).scrollTop("0");
    }
   
    
    function onOrOff(pro_no) {
        $.ajax({
        url : "/BA102G2/product/ProductServlet",
        type : "post",
        data : {
        action : "onOrOff",
        pro_no : pro_no
        },
        error : function(xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        },
        success : function(response) {
            if (response == 1) {
                $("#status" + pro_no).html("<a class='btn btn-danger' href='javascript:onOrOff(" + pro_no + ")'>商品下架</a>");
            }
            else if (response == 2) {
                $("#status" + pro_no).html("<a class='btn btn-success' href='javascript:onOrOff(" + pro_no + ")'>商品上架</a>");
            }
        }
        });
    }
</script>
<%@include file="pages/indexFooter.file"%>