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
//     MemVO memVO=(MemVO)session.getAttribute("memVO");
    MemService memService = new MemService();
    MemVO memVO=memService.getOneMem("1010");
    String now_Pro_Type = "0";
    String now_Order_Type = "0";
    ProductService productService = new ProductService();
    int carTotal = (session.getAttribute("carTotal") == null) ? 0 : (Integer) session.getAttribute("carTotal");
    Product_typeService product_typeService = new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    String[] orderTypeList = {"預設", "依商品名稱", "依上架日期(舊>>新)", "依上架日期(新>>舊)", "依價格(低>>高)", "依價格(高>>低)", "依賣家"};
    String preLocation = request.getContextPath() + "/Front_end/mall";

    session.setAttribute("carTotal", new Integer(carTotal));
    session.setAttribute("memVO", memVO);
    //////////////////////分頁需求參數//////////////////////////////////
    int nowPage = 1;
    int itemsCount = 8;
    int allCount = productService.getTypeAllCount(now_Pro_Type);
    int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
    List<ProductVO> productList = productService.getSome(nowPage, itemsCount, now_Pro_Type, now_Order_Type);
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
<%@include file="pages/mallIndexHeader.file"%>
<div class="text-center" style="height: 50px; margin-top: 50px"></div>
<!--//////////////////////////////////////////商品類型//////////////////////////////////////////////////////////////// -->
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <ul class="list-group">
                <c:choose>
                    <c:when test="${now_Pro_Type==0}">
                        <a id="type0" class="list-group-item menua active" href="javascript:change(1,0,0)"><h4>全部類型</h4></a>
                    </c:when>
                    <c:otherwise>
                        <a id="type0" class="list-group-item menua" href="javascript:change(1,0,0)"><h4>全部類型</h4></a>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="typeVO" items="${typeList}" varStatus="s">
                    <c:choose>
                        <c:when test="${now_Pro_Type==s.count}">
                            <a id="type${s.count}" class="list-group-item menua active" href="javascript:change(1,${s.count},0)"><h4>${typeVO.type_name}</h4></a>
                        </c:when>
                        <c:otherwise>
                            <a id="type${s.count}" class="list-group-item menua" href="javascript:change(1,${s.count},0)"><h4>${typeVO.type_name}</h4></a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-10">
            <div class="row">
                <!--//////////////////////////////////////////商品圖片//////////////////////////////////////////////////////////////// -->
                <div class="col-md-10">
                    <div id="ajax">
                        <c:forEach var="productVO" items="${productList}">
                            <div class="col-xs-3 col-md-3 btn-like-wrapper">
                                <a class="thumbnail thumbnail-service mod-shadow img-label" href="${preLocation}/product.jsp?pro_no=${productVO.pro_no}">
                                    <div class="caption">
                                        <img style="width: 100%; height: 200px;" src="<%=request.getContextPath()%>/image/ShowImage?pro_no=${productVO.pro_no}">
                                        <h5 style="height: 5px;">${productVO.pro_name}</h5>
                                        <span class="text-pink price">NT </span>
                                        <b class="text-pink price " style="font-size: 2em;">${productVO.price}</b>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="clearfix"></div>
                    <!--//////////////////////////////////////////分頁開始//////////////////////////////////////////////////////////////// -->
                    <div class="text-center ">
                        <nav aria-label="Page navigation ">
                            <ul class="pagination pagination-lg ">
                            <c:choose>
                                <c:when test="${totalPages<5}">
                                     <li>
                                        <a class="btn btn-info active" href="javascript:change(1,${now_Pro_Type},${now_Order_Type})" data-page="1">1</a>
                                    </li>
                                    <c:forEach var="i" begin="2" end="${totalPages}">
                                        <li>
                                            <a class="btn btn-info" href="javascript:change(${i},${now_Pro_Type},${now_Order_Type})" data-page="${i}">${i}</a>
                                        </li>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <a class="btn btn-info active" href="javascript:change(1,${now_Pro_Type},${now_Order_Type})" data-page="1">1</a>
                                    </li>
                                <c:forEach var="i" begin="2" end="5">
                                    <li>
                                        <a class="btn btn-info" href="javascript:change(${i},${now_Pro_Type},${now_Order_Type})" data-page="${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <li>
                                    <a class="disabled">...</a>
                                </li>
                                <li>
                                    <a class="btn btn-info" href="javascript:change(${totalPages},${now_Pro_Type},${now_Order_Type})" data-page="${totalPages}">${totalPages}</a>
                                </li>
                                </c:otherwise>
                            </c:choose>
                            </ul>
                        </nav>
                    </div>
                </div>
                <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
                <div class="btn-group" >
                    <button id="type" class="btn btn-default" style="width: 150px;">${orderTypeList[now_Order_Type]}</button>
                    <button data-toggle="dropdown" class="btn btn-default dropdown-toggle">
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <c:forEach var="orderType" items="${orderTypeList}" varStatus="s">
                            <li>
                                <a href="javascript:change(1,${now_Pro_Type},${s.index})">${orderType}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-2">
                    <div class="cart box_1">
                        <a href="${preLocation}/checkout.jsp">
                            <h3>
                                <div class="total">
                                    <span class="simpleCart_total">NT$${carTotal}</span>
                                    <span class="glyphicon glyphicon-shopping-cart"></span>
                                </div>
                            </h3>
                        </a> <a href="javascript:clear()" class="btn btn-danger btn-sm">清空購物車</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function change(nowPage, now_Pro_Type, now_Order_Type) {
        $.ajax({
        url : "/BA102G2/product/ProductServlet",
        type : "post",
        data : {
        action : "CHANGE_LAMBDA",
//         action : "CHANGE_AJAX",
        nowPage : nowPage,
        itemsCount : <%=itemsCount%>,
        now_Pro_Type : now_Pro_Type,
        now_Order_Type : now_Order_Type
        },
        error : function(xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        },
        success : function(response) {
            var orderTypeList = [ "預設", "依商品名稱", "依上架日期(舊>>新)", "依上架日期(新>>舊)", "依價格(低>>高)", "依價格(高>>低)", "依賣家" ];
            var productList=JSON.parse(response).productList;
            var proTypeNameList=JSON.parse(response).proTypeNameList;
            var totalPages=JSON.parse(response).totalPages;
            imageChange(productList);
            pageChange(now_Pro_Type, now_Order_Type, nowPage,totalPages);
            proTypeChange(now_Pro_Type, proTypeNameList);
            orderChange(orderTypeList, now_Pro_Type, now_Order_Type);
            $("#type").text(orderTypeList[now_Order_Type]);
        }
        });
    }

    function imageChange(productList) {
        var imgDiv = "";
        for (var i = 0; i < productList.length; i++) {
            var caption = "<div class='col-xs-3 col-md-3 btn-like-wrapper'>";
            var a = "<a class='thumbnail thumbnail-service mod-shadow img-label' href='/BA102G2/Front_end/mall/product.jsp?pro_no=" + productList[i].pro_no + "'>";
            var img = "<img style='width: 100%; height: 200px;' src='/BA102G2/image/ShowImage?pro_no=" + productList[i].pro_no + "'>"
            var h5 = "<h5  style='height: 5px;'>" + productList[i].pro_name + "</h5>";
            var b = "<span class='text-pink price'>NT </span><b class='text-pink price' style='font-size: 2em;''>" + productList[i].price + "</b>"
            imgDiv = imgDiv + caption + a + "<div class='caption'>" + img + h5 + b + "</div></a></div>"

        }
        $("#ajax").html(imgDiv);
    }

    function pageChange(now_Pro_Type, now_Order_Type,nowPage,totalPages) {
        var preHref = "javascript:change(" + 1 + "," + now_Pro_Type + "," + now_Order_Type + ")";
        var afterHref = "javascript:change(" + totalPages + "," + now_Pro_Type + "," + now_Order_Type + ")";
        var nothing = "<li><a class='disabled'>...</a></li>";
        if (totalPages <= 5) {
            var page = "";
            for (var i = 0; i < totalPages; i++) {
                var href = "javascript:change(" + (i + 1) +  "," + now_Pro_Type + "," + now_Order_Type + ")";
                var active = "";
                if ((i + 1) == nowPage) {
                    active = "active";
                }
                page = page + "<li><a class='btn btn-info " + active + "' href='" + href + "'" + "data-page='" + (i + 1) + "'" + ">" + (i + 1) + "</a></li>"
            }
        }
        else {
            if (nowPage < 5) {
                var page = "";
                for (var i = 0; i < 5; i++) {
                    var href = "javascript:change(" + (i + 1) +  "," + now_Pro_Type + "," + now_Order_Type + ")";
                    var active = "";
                    if ((i + 1) == nowPage) {
                        active = "active";
                    }
                    page = page + "<li><a class='btn btn-info " + active + "' href='" + href + "'" + "data-page='" + (i + 1) + "'" + ">" + (i + 1) + "</a></li>";
                }
                page = page + nothing;
                page = page + "<li><a class='btn btn-info' href='" + afterHref + "''" + "data-page='" + (totalPages) + "'" + ">" + (totalPages) + "</a></li>";
            }
            else if (totalPages - nowPage < 5) {
                var page = "<li><a class='btn btn-info' href='" + preHref + "'" + "data-page='" + (1) + "'" + ">" + (1) + "</a></li>";
                page = page + "<li><a class='disabled'>...</a></li>";
                for (var i = totalPages - 5; i <= totalPages; i++) {
                    var active = "";
                    var href = "javascript:change(" + i + "," + now_Pro_Type + "," + now_Order_Type + ")";
                    if (i == nowPage) {
                        active = "active";
                    }
                    page = page + "<li><a class='btn btn-info "+ active+"' href='" + href + "'" + "data-page='" + i + "'" + ">" + i + "</a></li>";
                }
            }
            else {
                var page = "<li><a class='btn btn-info' href='"+preHref+"'"+"data-page='"+1+"'"+">" + 1 + "</a></li>";
                page = page + nothing;
                for (var i = nowPage - 2; i <= nowPage + 2; i++) {
                    var active = "";
                    var href = "javascript:change(" + i +  "," + now_Pro_Type + "," + now_Order_Type + ")";
                    if (i == nowPage) {
                        active = "active";
                    }
                    page = page + "<li><a class='btn btn-info "+ active+"' href='" + href + "'" + "data-page='" + i + "'" + ">" + i + "</a></li>"
                }
                page = page + nothing;
                page = page + "<li><a class='btn btn-info' href='"+afterHref+"'"+"data-page='"+totalPages+"'"+">" + totalPages+ "</a></li>";
            }
        }
        $("ul.pagination-lg").html(page);
    }

    function proTypeChange(now_Pro_Type, proTypeNameList) {
        var li = "";
        var active = "";
        if (now_Pro_Type == 0) {
            active = "active";
        }
        li = li + "<a class='list-group-item menua " + active + "'href='javascript:change(1,0,0)'><h4>全部類型</h4></a>";
        for (var i = 0; i < proTypeNameList.length; i++) {
            active = "";
            if (now_Pro_Type == i + 1) {
                active = "active";
            }
            li = li + "<a class='list-group-item menua " + active + "'href='javascript:change(1," + (i + 1) + ",0)'><h4>" + proTypeNameList[i] + "</h4></a>";
        }
        $(".list-group").html(li);
    }

    function orderChange(orderTypeList, now_Pro_Type, now_Order_Type) {
        var li = ""
        for (var i = 0; i < orderTypeList.length; i++) {
            li = li + "<li><a href=\"javascript:change(1," + now_Pro_Type + "," + i + ")\">" + orderTypeList[i] + "</a></li>"
        }
        $(".dropdown-menu").html(li);
    }
</script>
<%@include file="pages/mallIndexFooter.file"%>