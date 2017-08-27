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
	String proType = (request.getParameter("proType") == null)? "0": request.getParameter("proType");
	String orderType = (request.getParameter("orderType") == null)? "0": request.getParameter("orderType");
	ProductService productService = new ProductService();
	int carTotal = (session.getAttribute("carTotal") == null) ? 0 : (Integer) session.getAttribute("carTotal");
	Product_typeService product_typeService = new Product_typeService();
	List<Product_typeVO> typeList = product_typeService.getAll();
	String[] orderTypeList = {"預設", "依商品名稱", "依上架日期(舊>>新)", "依上架日期(新>>舊)", "依價格(低>>高)", "依價格(高>>低)", "依賣家"};
	String preLocation = request.getContextPath() + "/Front_end/mall";
    String[] animated={"slideInUp","slideInDown","slideInRight","slideInLeft","fadeInLeft","fadeInRight","fadeInDown","fadeInUp"};
	session.setAttribute("carTotal", new Integer(carTotal));
	//////////////////////分頁需求參數//////////////////////////////////
	int nowPage = (request.getParameter("nowPage") == null)? 1: Integer.valueOf(request.getParameter("nowPage"));
    int itemsCount = 8;
	int allCount = productService.getAllCount(proType, "1");
	int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
	List<ProductVO> productList = productService.getPage(nowPage, itemsCount, proType, orderType,	"1");
	pageContext.setAttribute("animated", animated);
	pageContext.setAttribute("productList", productList);
	pageContext.setAttribute("itemsCount", itemsCount);
	pageContext.setAttribute("nowPage", nowPage);
	pageContext.setAttribute("totalPages", totalPages);
	//////////////////////分頁需求參數//////////////////////////////////
	pageContext.setAttribute("preLocation", preLocation);
	pageContext.setAttribute("typeList", typeList);
	pageContext.setAttribute("orderTypeList", orderTypeList);
	pageContext.setAttribute("proType", proType);
	pageContext.setAttribute("orderType", orderType);
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

<div class="container" id="container">
    <div class="row">
        <div class="col-xs-2 col-md-2">
            <ul id="proType" class="list-group">
                <a id="type0" class="list-group-item menua ${proType==0?'active':''}" href="javascript:change(1,0,0)">
                    <h4>全部類型</h4>
                </a>
                <c:forEach var="typeVO" items="${typeList}" varStatus="s">
                    <a id="type${s.count}" class="list-group-item menua ${proType==s.count?'active':''}" href="javascript:change(1,${s.count},0)">
                        <h4>${typeVO.type_name}</h4>
                    </a>           
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
                                <a class="thumbnail thumbnail-service mod-shadow img-label animated flipInY" style="animation-duration: <%=Math.random()*3%>s;" href="${preLocation}/product.jsp?pro_no=${productVO.pro_no}">
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
                                            <li><a class="btn btn-info ${nowPage==i?'active':''}" href="javascript:change(${i},${proType},${orderType})" data-page="${i}">${i}</a></li>
                                        </c:forEach>
                                    </c:when>
                                    <c:when test="${nowPage<5}">
                                        <c:forEach var="i" begin="1" end="5">
                                            <li><a class="btn btn-info ${nowPage==i?'active':''}" href="javascript:change(${i},${proType},${orderType})" data-page="${i}">${i}</a></li>
                                        </c:forEach>
                                        <li><a class="disabled">...</a></li>
                                        <li><a class="btn btn-info" href="javascript:change(${totalPages},${proType},${orderType})" data-page="${totalPages}">${totalPages}</a></li>
                                    </c:when>
                                    <c:when test="${totalPages-nowPage<5}">
                                        <li><a class="btn btn-info" href="javascript:change(1,${proType},${orderType})" data-page="1">1</a></li>
                                        <li><a class="disabled">...</a></li>
                                        <c:forEach var="i" begin="${totalPages-(5-1)}" end="${totalPages}">
                                            <li><a class="btn btn-info ${nowPage==i?'active':''}" href="javascript:change(${i},${proType},${orderType})" data-page="${i}">${i}</a></li>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="btn btn-info" href="javascript:change(1,${proType},${orderType})" data-page="1">1</a></li>
                                        <li><a class="disabled">...</a></li>
                                        <c:forEach var="i" begin="${nowPage-2}" end="${nowPage+2}">
                                            <li><a class="btn btn-info ${nowPage==i?'active':''}" href="javascript:change(${i},${proType},${orderType})" data-page="${i}">${i}</a></li>
                                        </c:forEach>
                                        <li><a class="disabled">...</a></li>
                                        <li><a class="btn btn-info" href="javascript:change(${totalPages},${proType},${orderType})" data-page="${totalPages}">${totalPages}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </nav>
                    </div>
                <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
                </div>
                <div id="orderType" class="btn-group">
                    <button id="type" class="btn btn-default" style="width: 150px;">${orderTypeList[orderType]}</button>
                    <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <c:forEach var="orderType" items="${orderTypeList}" varStatus="s">
                            <li><a href="javascript:change(1,${proType},${s.index})">${orderType}</a></li>
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
    function change(nowPage, proType, orderType) {
        $(window).scrollTop( $(window).scrollTop());
        $(window).scrollLeft($(window).scrollLeft());
        
        $("#container").load("index.jsp #container", {
        nowPage : nowPage,
        proType : proType,
        orderType : orderType
        });
        
    }
</script>
<%@include file="pages/indexFooter.file"%>