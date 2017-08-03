<%@page import="com.protra.model.ProtraService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="pages/mallIndexHeader.file"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String mem_no = "1010";
	int nowPage = (request.getParameter("nowPage") == null)
	? 1
	: Integer.parseInt((request.getParameter("nowPage")));
	int itemsCount = 5;
	ProtraService protraService = new ProtraService();
	int allCount = protraService.getRowCount(mem_no);
	int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
	ProductService productService = new ProductService();
	List<ProductVO> productList = new ArrayList<ProductVO>();

	List<String> protracking_list = protraService.getSome(mem_no, nowPage, itemsCount);
	String preLocation = request.getContextPath() + "/Front_end/mall";

	for (int i = 0; i < protracking_list.size(); i++) {
        productList.add(productService.getOneByPK(protracking_list.get(i)));
	}
    session.setAttribute("mem_no", mem_no);
	session.setAttribute("productList", productList);
	pageContext.setAttribute("preLocation", preLocation);
	pageContext.setAttribute("itemsCount", itemsCount);
	pageContext.setAttribute("totalPages", totalPages);
%>
<style>
    img{
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
                            <c:forEach var="productVO" items="${productList}">
                                <tr>
                                    <td><a target="_blank" href="${preLocation}/product.jsp?pro_no=${productVO.pro_no}"> ${productVO.pro_name} </a></td>
                                    <td>${productVO.price}</td>
                                    <td>${productVO.amount}</td>
                                    <td><img src="<%=request.getContextPath()%>/image/ShowImage?pro_no=${productVO.pro_no}"></td>
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
                        <li>
                            <a class="btn btn-info active" href="javascript:change(1,${itemsCount})" data-page="1">1</a>
                        </li>
                        <c:forEach var="i" begin="2" end="5">
                            <li>
                                <a class="btn btn-info" href="javascript:change(${i},${itemsCount})" data-page="${i}">${i}</a>
                            </li>
                        </c:forEach>
                        <li>
                            <a class="disabled">...</a>
                        </li>
                        <li>
                            <a class="btn btn-info" href="javascript:change(${totalPages})">${totalPages}</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <!--//////////////////////////////////////////分頁//////////////////////////////////////////////////////////////// -->
        </div>
    </div>
</div>
<script type="text/javascript">
    function change(nowPage, itemsCount) {
        $.ajax({
        url : "/BA102G2/protra/ProtracServlet",
        type : "post",
        data : {
        action : "CHANGE_AJAX",
        nowPage : nowPage,
        itemsCount : itemsCount,
        },
        error : function(xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        },
        success : function(response) {
            var productList = JSON.parse(response).productList;
            var pronoList = [];
            var pronameList = [];
            var priceList = [];
            var amountList = [];
            var totalPages = JSON.parse(response).totalPages;
            for (var i = 0; i < productList.length; i++) {
                pronoList.push(productList[i].pro_no);
                console.log("pronoList "+pronoList[i]);
                pronameList.push(productList[i].pro_name);
                console.log("pronameList "+pronameList[i]);
                priceList.push(productList[i].price);
                console.log("priceList "+priceList[i]);
                amountList.push(productList[i].amount);
                console.log("amountList "+amountList[i]);
            }
            pageChange(itemsCount, totalPages, nowPage);
            tableChange(pronoList, pronameList, priceList, amountList);
        }
        });
    }

    function pageChange(itemsCount, totalPages, nowPage) {
        var preHref = "javascript:change(" + 1 + "," + itemsCount + ")";
        var afterHref = "javascript:change(" + totalPages + "," + itemsCount + ")";
        var nothing = "<li><a class='disabled'>...</a></li>";
        if (totalPages <= 5) {
            var page = "";
            for (var i = 0; i < totalPages; i++) {
                var href = "javascript:change(" + (i + 1) + "," + itemsCount + ")";
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
                    var href = "javascript:change(" + (i + 1) + "," + itemsCount + ")";
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
                    var href = "javascript:change(" + i + "," + itemsCount + ")";
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
                    var href = "javascript:change(" + i + "," + itemsCount
                    ")";
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

    function tableChange(pronoList, pronameList, priceList, amountList) {
        var tbody = "";
        for (var i = 0; i < pronoList.length; i++) {
            tbody = tbody + "<tr>"+
                   "<td><a target='_blank' href='/BA102G2/Front_end/mall/product.jsp?pro_no=" + pronoList[i] + "'>" + pronameList[i] + "</a></td>" + 
                   "<td>" + priceList[i] + "</td>"+
                   "<td>" + amountList[i] + "</td>"+
                   "<td>"+"<img src='/BA102G2/image/ShowImage?pro_no="+pronoList[i]+"'>"+
                   "</tr>";
        }
        $("tbody").html(tbody);
    }
</script>
<%@include file="pages/mallIndexFooter.file"%>