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
    int nowPage = 1;
    String now_Pro_Type = (request.getParameter("now_Pro_Type") == null)? "0": String.valueOf(request.getParameter("now_Pro_Type"));
    String now_Order_Type = "0";
    ProductService productService = new ProductService();
    int allCount = productService.getTypeAllCount(now_Pro_Type);
    int itemsCount = 8;
    int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
    int carTotal = (session.getAttribute("carTotal") == null) ? 0 : (Integer) session.getAttribute("carTotal");
    List<ProductVO> productList = productService.getSome(nowPage, itemsCount, now_Pro_Type, now_Order_Type);
    Product_typeService product_typeService = new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    String[] orderTypeList = {"預設", "依商品名稱", "依上架日期(舊>>新)", "依上架日期(新>>舊)", "依價格(低>>高)", "依價格(高>>低)", "依賣家"};
    String preLocation = request.getContextPath() + "/Front_end/mall";
    String location = "/Front_end/mall/mallIndexAJAX.jsp?nowPage=" + nowPage + "&&now_Pro_Type=" + now_Pro_Type + "&&now_Order_Type=" + now_Order_Type;

    session.setAttribute("carTotal", new Integer(carTotal));
    session.setAttribute("location", location);
    session.setAttribute("memVO", memVO);

    pageContext.setAttribute("productList", productList);
    pageContext.setAttribute("itemsCount", itemsCount);
    pageContext.setAttribute("nowPage", nowPage);
    pageContext.setAttribute("totalPages", totalPages);
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
                        <a id="type0" class="list-group-item menua active" href="javascript:change(1,${itemsCount},0,0)"><h4>全部類型</h4></a>
                    </c:when>
                    <c:otherwise>
                        <a id="type0" class="list-group-item menua" href="javascript:change(1,${itemsCount},0,0)"><h4>全部類型</h4></a>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="typeVO" items="${typeList}" varStatus="s">
                    <c:choose>
                        <c:when test="${now_Pro_Type==s.count}">
                            <a id="type${s.count}" class="list-group-item menua active" href="javascript:change(1,${itemsCount},${s.count},0)"><h4>${typeVO.type_name}</h4></a>
                        </c:when>
                        <c:otherwise>
                            <a id="type${s.count}" class="list-group-item menua" href="javascript:change(1,${itemsCount},${s.count},0)"><h4>${typeVO.type_name}</h4></a>
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
                                <li>
                                    <a class="btn btn-info active" href="javascript:change(1,${itemsCount},${now_Pro_Type},${now_Order_Type})" data-page="1">1</a>
                                </li>
                                <c:forEach var="i" begin="2" end="5">
                                    <li>
                                        <a class="btn btn-info" href="javascript:change(${i},${itemsCount},${now_Pro_Type},${now_Order_Type})" data-page="${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <li>
                                    <a class="disabled">...</a>
                                </li>
                                <li>
                                    <a class="btn btn-info" href="javascript:change(${totalPages},${itemsCount},${now_Pro_Type},${now_Order_Type})">${totalPages}</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
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
                <div class="btn-group" style="margin-top: 50px;">
                    <button id="type" class="btn btn-default" style="width: 150px;">${orderTypeList[now_Order_Type]}</button>
                    <button data-toggle="dropdown" class="btn btn-default dropdown-toggle">
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <c:forEach var="orderType" items="${orderTypeList}" varStatus="s">
                            <li>
                                <a href="javascript:change(1,${itemsCount},${now_Pro_Type},${s.index})">${orderType}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function change(nowPage, itemsCount, now_Pro_Type, now_Order_Type) {
        $.ajax({
        url : "/BA102G2/product/ProductServlet",
        type : "post",
        data : {
        action : "CHANGE_LAMBDA",
        nowPage : nowPage,
        itemsCount : itemsCount,
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
            var proTypeList=JSON.parse(response).product_typeList;
            var pronoList =[];
            var pronameList = [];
            var priceList = [];
            var proTypeNameList = [];
            var totalPages=JSON.parse(response).totalPages;
            for(var i=0;i<productList.length;i++){
                pronoList.push(productList[i].pro_no);
                pronameList.push(productList[i].pro_name);
                priceList.push(productList[i].price);
            }
            for(var i=0;i<proTypeList.length;i++){
                proTypeNameList.push(proTypeList[i].type_name)
            }
            imageChange(pronoList, pronameList, priceList);
            pageChange(itemsCount, now_Pro_Type, now_Order_Type, totalPages, nowPage);
            proTypeChange(now_Pro_Type, itemsCount, proTypeNameList);
            orderChange(orderTypeList, itemsCount, now_Pro_Type, now_Order_Type);
            $("#type").text(orderTypeList[now_Order_Type]);
        }
        });
    }

//     function change(nowPage, itemsCount, now_Pro_Type, now_Order_Type) {
//         $.ajax({
//         url : "/BA102G2/product/ProductServlet",
//         type : "post",
//         data : {
//         action : "CHANGE_AJAX",
//         nowPage : nowPage,
//         itemsCount : itemsCount,
//         now_Pro_Type : now_Pro_Type,
//         now_Order_Type : now_Order_Type
//         },
//         error : function(xhr, ajaxOptions, thrownError) {
//             console.log(xhr.status);
//             console.log(thrownError);
//         },
//         success : function(response) {
//             var orderTypeList = [ "預設", "依商品名稱", "依上架日期(舊>>新)", "依上架日期(新>>舊)", "依價格(低>>高)", "依價格(高>>低)", "依賣家" ];
//             var pronoList = JSON.parse(response).pronoList;
//             var pronameList = JSON.parse(response).pronameList;
//             var priceList = JSON.parse(response).priceList;
//             var proTypeNameList = JSON.parse(response).proTypeList;
//             var totalPages = JSON.parse(response).totalPages;
//             imageChange(pronoList, pronameList, priceList);
//             pageChange(itemsCount, now_Pro_Type, now_Order_Type, totalPages, nowPage);
//             proTypeChange(now_Pro_Type, itemsCount, proTypeNameList);
//             orderChange(orderTypeList, itemsCount, now_Pro_Type, now_Order_Type);
//             $("#type").text(orderTypeList[now_Order_Type]);
//         }
//         });
//     }

    function imageChange(pronoList, pronameList, priceList) {
        var imgDiv = "";
        for (var i = 0; i < pronoList.length; i++) {
            var caption = "<div class='col-xs-3 col-md-3 btn-like-wrapper'>";
            var a = "<a class='thumbnail thumbnail-service mod-shadow img-label' href='/BA102G2/Front_end/mall/product.jsp?pro_no=" + pronoList[i] + "'>";
            var img = "<img style='width: 100%; height: 200px;' src='/BA102G2/image/ShowImage?pro_no=" + pronoList[i] + "'>"
            var h5 = "<h5  style='height: 5px;'>" + pronameList[i] + "</h5>";
            var b = "<span class='text-pink price'>NT </span><b class='text-pink price' style='font-size: 2em;''>" + priceList[i] + "</b>"
            imgDiv = imgDiv + caption + a + "<div class='caption'>" + img + h5 + b + "</div></a></div>"

        }
        $("#ajax").html(imgDiv);
    }

    function pageChange(itemsCount, now_Pro_Type, now_Order_Type, totalPages, nowPage) {
        var preHref = "javascript:change(" + 1 + "," + itemsCount + "," + now_Pro_Type + "," + now_Order_Type + ")";
        var afterHref = "javascript:change(" + totalPages + "," + itemsCount + "," + now_Pro_Type + "," + now_Order_Type + ")";
        var nothing = "<li><a class='disabled'>...</a></li>";
        if (totalPages <= 5) {
            var page = "";
            for (var i = 0; i < totalPages; i++) {
                var href = "javascript:change(" + (i + 1) + "," + itemsCount + "," + now_Pro_Type + "," + now_Order_Type + ")";
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
                    var href = "javascript:change(" + (i + 1) + "," + itemsCount + "," + now_Pro_Type + "," + now_Order_Type + ")";
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
                    var href = "javascript:change(" + i + "," + itemsCount + "," + now_Pro_Type + "," + now_Order_Type + ")";
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
                    var href = "javascript:change(" + i + "," + itemsCount + "," + now_Pro_Type + "," + now_Order_Type + ")";
                    if (i == nowPage) {
                        active = "active";
                    }
                    page = page + "<li><a class='btn btn-info "+ active+"' href='" + href + "'" + "data-page='" + i + "'" + ">" + i + "</a></li>"
                }
                page = page + nothing;
                page = page + "<li><a class='btn btn-info' href='"+afterHref+"'"+"data-page='"+totalPages+"'"+">" + totalPages + "</a></li>";
            }
        }
        $("ul.pagination-lg").html(page);
    }

    function proTypeChange(now_Pro_Type, itemsCount, proTypeNameList) {
        var li = "";
        var active = "";
        if (now_Pro_Type == 0) {
            active = "active";
        }
        li = li + "<a class='list-group-item menua " + active + "'href='javascript:change(1," + itemsCount + ",0,0)'><h4>全部類型</h4></a>";
        for (var i = 0; i < proTypeNameList.length; i++) {
            active = "";
            if (now_Pro_Type == i + 1) {
                active = "active";
            }
            li = li + "<a class='list-group-item menua " + active + "'href='javascript:change(1," + itemsCount + "," + (i + 1) + ",0)'><h4>" + proTypeNameList[i] + "</h4></a>";
        }
        $(".list-group").html(li);
    }

    function orderChange(orderTypeList, itemsCount, now_Pro_Type, now_Order_Type) {
        var li = ""
        for (var i = 0; i < orderTypeList.length; i++) {
            li = li + "<li><a href=\"javascript:change(1," + itemsCount + "," + now_Pro_Type + "," + i + ")\">" + orderTypeList[i] + "</a></li>"
        }
        $(".dropdown-menu").html(li);
    }
</script>
<%@include file="pages/mallIndexFooter.file"%>