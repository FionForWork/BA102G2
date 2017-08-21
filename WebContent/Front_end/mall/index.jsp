<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.product_type.model.Product_typeVO"%>
<%@page import="com.product_type.model.Product_typeService"%>
<%@page import="java.util.List"%>
<%@page import="com.product.model.ProductVO"%>
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
// 	MemVO memVO = memService.getOneMem("1010");
	String now_Pro_Type = (request.getParameter("now_Pro_Type") == null)? "0": request.getParameter("now_Pro_Type");
	String now_Order_Type = (request.getParameter("now_Order_Type") == null)? "0": request.getParameter("now_Order_Type");
	ProductService productService = new ProductService();
	int carTotal = (session.getAttribute("carTotal") == null) ? 0 : (Integer) session.getAttribute("carTotal");
	Product_typeService product_typeService = new Product_typeService();
	List<Product_typeVO> typeList = product_typeService.getAll();
	String[] orderTypeList = {"預設", "依商品名稱", "依上架日期(舊>>新)", "依上架日期(新>>舊)", "依價格(低>>高)", "依價格(高>>低)", "依賣家"};
	String preLocation = request.getContextPath() + "/Front_end/mall";

	session.setAttribute("carTotal", new Integer(carTotal));
	session.setAttribute("memVO", memVO);
	//////////////////////分頁需求參數//////////////////////////////////
	int nowPage = (request.getParameter("nowPage") == null)? 1: Integer.valueOf(request.getParameter("nowPage"));
    int itemsCount = 8;
	int allCount = productService.getAllCount(now_Pro_Type, "1");
	int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
	List<ProductVO> productList = productService.getPage(nowPage, itemsCount, now_Pro_Type, now_Order_Type,	"1");
	pageContext.setAttribute("productList", productList);
	pageContext.setAttribute("itemsCount", itemsCount);
	pageContext.setAttribute("nowPage", nowPage);
	pageContext.setAttribute("totalPages", totalPages);
	//////////////////////分頁需求參數//////////////////////////////////
	pageContext.setAttribute("preLocation", preLocation);
	pageContext.setAttribute("typeList", typeList);
	pageContext.setAttribute("orderTypeList", orderTypeList);
	pageContext.setAttribute("now_Pro_Type", now_Pro_Type);
	pageContext.setAttribute("now_Order_Type", now_Order_Type);
%>
<%@include file="pages/indexHeader.file"%>
<div class="text-center" style="height: 50px; margin-top: 50px"></div>
<!--//////////////////////////////////////////商品類型//////////////////////////////////////////////////////////////// -->
<style>
    #pro_name {
  overflow : hidden;
  text-overflow : ellipsis;
  white-space : nowrap;
}
</style>

<div class="container">
    <div class="row">
        <div class="col-xs-2 col-md-2">
            <ul id="proType" class="list-group">
                <c:choose>
                    <c:when test="${now_Pro_Type==0}">
                        <a id="type0" class="list-group-item menua active" href="javascript:change(1,0,0)">
                            <h4>全部類型</h4>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a id="type0" class="list-group-item menua" href="javascript:change(1,0,0)">
                            <h4>全部類型</h4>
                        </a>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="typeVO" items="${typeList}" varStatus="s">
                    <c:choose>
                        <c:when test="${now_Pro_Type==s.count}">
                            <a id="type${s.count}" class="list-group-item menua active" href="javascript:change(1,${s.count},0)">
                                <h4>${typeVO.type_name}</h4>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a id="type${s.count}" class="list-group-item menua" href="javascript:change(1,${s.count},0)">
                                <h4>${typeVO.type_name}</h4>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
        <div class="col-xs-10 col-md-10">
            <div class="row">
                <!--//////////////////////////////////////////商品圖片//////////////////////////////////////////////////////////////// -->
                <div class="col-md-10">
                    <div id="imgDiv">
                        <c:forEach var="productVO" items="${productList}" varStatus="s">
                            <div class="col-xs-3 col-md-3 btn-like-wrapper">
                                <a class="thumbnail thumbnail-service mod-shadow img-label animated flipInX" href="${preLocation}/product.jsp?pro_no=${productVO.pro_no}">
                                    <div class="caption">
                                        <img style="width: 100%; height: 200px;" src="<%=request.getContextPath()%>/image/ShowImage?pro_no=${productVO.pro_no}">
                                        <div id="pro_name"><h5>${productVO.pro_name}</h5></div>
                                        <b class="text-pink price " style="font-size: 2em;">NT${productVO.price}</b>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="clearfix"></div>
                    <!--//////////////////////////////////////////分頁開始//////////////////////////////////////////////////////////////// -->
                    <div class="text-center">
                        <nav aria-label="Page navigation ">
                            <ul id="pagination" class="pagination pagination-lg">
                                <c:choose>
                                    <c:when test="${totalPages<=5}">
                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <c:choose>
                                                <c:when test="${nowPage==i}">
                                                    <li class=""><a class="btn btn-info active" href="javascript:change(1,${now_Pro_Type},${now_Order_Type})" data-page="${i}">${i}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class=""><a class="btn btn-info" href="javascript:change(${i},${now_Pro_Type},${now_Order_Type})" data-page="${i}">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:when>
                                    <c:when test="${nowPage<5}">
                                        <c:forEach var="i" begin="1" end="5">
                                            <c:choose>
                                                <c:when test="${nowPage==i}">
                                                    <li class=""><a class="btn btn-info active" href="javascript:change(${i},${now_Pro_Type},${now_Order_Type})" data-page="${i}">${i}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class=""><a class="btn btn-info" href="javascript:change(${i},${now_Pro_Type},${now_Order_Type})" data-page="${i}">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <li><a class="disabled">...</a></li>
                                        <li><a class="btn btn-info" href="javascript:change(${totalPages},${now_Pro_Type},${now_Order_Type})" data-page="${totalPages}">${totalPages}</a></li>
                                    </c:when>
                                    <c:when test="${totalPages-nowPage<5}">
                                        <li><a class="btn btn-info" href="javascript:change(1,${now_Pro_Type},${now_Order_Type})" data-page="1">1</a></li>
                                        <li><a class="disabled">...</a></li>
                                        <c:forEach var="i" begin="${totalPages-5}" end="${totalPages}">
                                            <c:choose>
                                                <c:when test="${nowPage==i}">
                                                    <li class=""><a class="btn btn-info active" href="javascript:change(${i},${now_Pro_Type},${now_Order_Type})" data-page="${i}">${i}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class=""><a class="btn btn-info" href="javascript:change(${i},${now_Pro_Type},${now_Order_Type})" data-page="${i}">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="btn btn-info" href="javascript:change(1,${now_Pro_Type},${now_Order_Type})" data-page="1">1</a></li>
                                        <li><a class="disabled">...</a></li>
                                        <c:forEach var="i" begin="${nowPage-2}" end="${nowPage+2}">
                                            <c:choose>
                                                <c:when test="${nowPage==i}">
                                                    <li class=""><a class="btn btn-info active" href="javascript:change(${i},${now_Pro_Type},${now_Order_Type})" data-page="${i}">${i}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class=""><a class="btn btn-info" href="javascript:change(${i},${now_Pro_Type},${now_Order_Type})" data-page="${i}">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <li><a class="disabled">...</a></li>
                                        <li><a class="btn btn-info" href="javascript:change(${totalPages},${now_Pro_Type},${now_Order_Type})" data-page="${totalPages}">${totalPages}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </nav>
                    </div>
                </div>
                <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
                <div id="orderType" class="btn-group">
                    <button id="type" class="btn btn-default" style="width: 150px;">${orderTypeList[now_Order_Type]}</button>
                    <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <c:forEach var="orderType" items="${orderTypeList}" varStatus="s">
                            <li><a href="javascript:change(1,${now_Pro_Type},${s.index})">${orderType}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-xs-2 col-md-2">
                    <div class="cart box_1">
                        <a href="${preLocation}/checkout.jsp">
                            <h3>
                                <div class="total">
                                    <span class="simpleCart_total">NT$${carTotal}</span>
                                    <span class="glyphicon glyphicon-shopping-cart"></span>
                                </div>
                            </h3>
                        </a>
                        <a href="javascript:clear()" class="btn btn-danger btn-sm">清空購物車</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function change(nowPage, now_Pro_Type, now_Order_Type) {
        $(window).scrollTop( $(window).scrollTop());
        $(window).scrollLeft($(window).scrollLeft());
        $("#imgDiv").load("index.jsp #imgDiv", {
        "nowPage" : nowPage,
        "now_Pro_Type" : now_Pro_Type,
        "now_Order_Type" : now_Order_Type
        });
        
        $("#pagination").load("index.jsp #pagination", {
        "nowPage" : nowPage,
        "now_Pro_Type" : now_Pro_Type,
        "now_Order_Type" : now_Order_Type
        });
        
        $("#orderType").load("index.jsp #orderType", {
        "nowPage" : nowPage,
        "now_Pro_Type" : now_Pro_Type,
        "now_Order_Type" : now_Order_Type
        });
        
        $("#proType").load("index.jsp #proType", {
        "nowPage" : nowPage,
        "now_Pro_Type" : now_Pro_Type,
        "now_Order_Type" : now_Order_Type
        });
        
    }
</script>
<%@include file="pages/indexFooter.file"%>