<%@page import="com.mem.model.MemService"%>
<%@page import="com.com.model.ComService"%>
<%@page import="com.adm.model.AdmVO"%>
<%@page import="com.adm.model.AdmService"%>
<%@page import="org.logicalcobwebs.proxool.admin.Admin"%>
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
//     AdmVO admVO=(AdmVO)session.getAttribute("admVO");
    ProductService productService = new ProductService();
    MemService memService=new MemService();
    int nowPage = (request.getParameter("nowPage") == null) ? 1 : Integer.valueOf(request.getParameter("nowPage"));
    int itemsCount = 5;
    int allCount = productService.getAllCount("0", "0");
    int totalPages = allCount / itemsCount + 1;
    List<ProductVO> productList = productService.getPage(nowPage, itemsCount, "0", "0", "0");
    String preLocation = request.getContextPath() + "/Back_end/mall";
    Product_typeService product_typeService = new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    String active="1";
    pageContext.setAttribute("memService", memService);
    pageContext.setAttribute("typeList", typeList);
    pageContext.setAttribute("preLocation", preLocation);
    pageContext.setAttribute("productList", productList);
    pageContext.setAttribute("nowPage", nowPage);
    pageContext.setAttribute("totalPages", totalPages);
%>
<%@include file="/Back_end/pages/backHeader.file"%>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <table class="table">
            <thead>
                <tr>
                    <th>商品名稱</th>
                    <th>賣家姓名</th>
                    <th>商品描述</th>
                    <th>商品類型</th>
                    <th>商品價格</th>
                    <th>商品庫存</th>
                    <th>商品影像</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="productVO" items="${productList}" varStatus="s">
                    <tr class="animated fadeInUp" style="animation-duration: ${s.index*0.3}s">
                        <td>${productVO.pro_name}</td>
                        <td>${memService.getOneMem(productVO.seller_no).getName()}(${productVO.seller_no})</td>
                        <td>${productVO.pro_desc}</td>
                        <td>${typeList.get(productVO.protype_no-1).type_name}</td>
                        <td>${productVO.price}</td>
                        <td>${productVO.amount}</td>
                        <td><img style="height: 80px; width: 80px;" src="<%=request.getContextPath()%>/image/ShowImage?pro_no=${productVO.pro_no}"></td>
                        <td style="width: 80px;">
                            <a style="width: 100%;" class="btn btn-success" href="javascript:able('ABLE',${nowPage},${productVO.pro_no})">審核通過</a> 
                            <a style="width: 100%;" class="btn btn-danger" href="javascript:able('DISABLE',${nowPage},${productVO.pro_no})">審核不通過</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!--//////////////////////////////////////////分頁開始//////////////////////////////////////////////////////////////// -->
            <div class="text-center ">
                <nav aria-label="Page navigation ">
                    <ul class="pagination pagination-lg ">
                        <c:choose>
                            <c:when test="${totalPages<=5}">
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <c:choose>
                                        <c:when test="${nowPage==i}">
                                            <li><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:when test="${nowPage<5}">
                                <c:forEach var="i" begin="1" end="5">
                                    <c:choose>
                                        <c:when test="${nowPage==i}">
                                            <li><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
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
                                            <li><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
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
                                            <li><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
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
    <script type="text/javascript">
    function change(nowPage){ 
        $(window).scrollTop($(window).scrollTop());
        $(window).scrollLeft($(window).scrollLeft());
        $("table").load("<%=preLocation%>/productPreview.jsp table",{"nowPage":nowPage});
        $(".pagination").load("<%=preLocation%>/productPreview.jsp .pagination", {"nowPage" : nowPage });
    }
    
    function able(able,nowPage,pro_no){ 
        $.ajax({
            url : "<%=request.getContextPath()%>/product/ProductServlet",
            type : "post",
            data : {
            action : able,
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
<%@include file="/Back_end/pages/backFooter.file"%>