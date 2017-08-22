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
// 	MemVO memVO = memService.getOneMem("1010");
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

img {
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
                                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#${productVO.pro_no}">商品資料修改</button>
                                        <%@include file="pages/productUpdate.file"%>
                                    </td>
                                    <c:choose>
                                        <c:when test="${productVO.status=='0'}">
                                            <td>商品審核中</td>
                                        </c:when>
                                        <c:when test="${productVO.status=='1'}">
                                            <td><div id="status${productVO.pro_no}">
                                                    <a class="btn btn-danger" href="javascript:onOrOff(${productVO.pro_no})">商品下架</a>
                                                </div></td>
                                        </c:when>
                                        <c:when test="${productVO.status=='2'}">
                                            <td>
                                                <div id="status${productVO.pro_no}">
                                                    <a class="btn btn-info" href="javascript:onOrOff(${productVO.pro_no})">商品上架</a>
                                                </div>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <div id="status${productVO.pro_no}">
                                                    <p>審核未過，3天後刪除資料</p>
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
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#NewProduct">申請新商品上架</button>
                    <%@include file="pages/productAdd.file"%>
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
<script>
    function change(nowPage){
        $(".pagination").load("/BA102G2/Front_end/mall/productManagement.jsp .pagination",{"nowPage":nowPage});
        $("#content").load("/BA102G2/Front_end/mall/productManagement.jsp #content",{"nowPage":nowPage});
        $(window).scrollLeft("0");
        $(window).scrollTop("0");
    }
    
    function addCheck() {
        var pro_name = $("#addName").val();
        if (pro_name == "") {
            alert("請輸入商品名稱");
        }
        else {
            $("#addForm").submit();
        }
    }

    $(".updateImg").on("dragover", function(e) {
        e.preventDefault();
    });

    $(".updateImg").on("drop", function(e) {
        e.preventDefault();
        var count = $(this).attr("id");
        var files = event.dataTransfer.files;
        var xhr = new XMLHttpRequest();
        var url = "/BA102G2/product/ProductServlet";
        xhr.open('POST', url);
        var form = new FormData();
        if (!files[0].type.match("image")) {
            var name = files[0].name;
            alert(name + "請上傳圖片!!!!");
            return;
        }
        else {
            form.append("img", files[0]);
            form.append("action", "UPDATE_AJAX");
            form.append("pro_no", $("#updateNo" + count).val());
            form.append("pro_name", $("#updateName" + count).val());
            form.append("pro_desc", $("#updateDesc" + count).val());
            form.append("price", $("#updatePrice" + count).val());
            form.append("amount", $("#updateAmount" + count).val());
            xhr.send(form);
            xhr.onreadystatechange = function() {
                if (xhr.responseText == "OK") {
                    alert("已更新資料");
                    var reader = new FileReader();
                    reader.readAsDataURL(files[0]);
                    reader.onload = function(e) {
                        $(".updatePreview" + count).attr("src", e.target.result);
                        }
                    }
                }
        }
    });

    $(".addImg").on("dragover", function(e) {
        e.preventDefault();
    });

    $(".addImg").on("drop", function(e) {
        console.log("add");
        e.preventDefault();
        var files = event.dataTransfer.files;
        var xhr = new XMLHttpRequest();
        var url = "/BA102G2/product/ProductServlet?action=ADD_AJAX";
        xhr.open('POST', url);
        var form = new FormData();
        if (!files[0].type.match("image")) {
            var name = files[0].name;
            alert(name + "請上傳圖片!!!!");
            return;
        }
        else {
            form.append("img", files[0]);
        }
        if ($("#addName" + count).val() == "") {
            alert("請輸入商品名稱");
            return;
        }
        form.append("pro_name", $("#addName").val());
        form.append("pro_desc", $("#addDesc").val());
        form.append("price", $("#addPrice").val());
        form.append("protype_no", $("#addType").val());
        form.append("amount", $("#addAmount").val());
        xhr.send(form);
        xhr.onreadystatechange = function() {
            if (xhr.responseText == "OK") {
                alert("已申請，請耐心等待審核");
                var reader = new FileReader();
                reader.readAsDataURL(files[0]);
                reader.onload = function(e) {
                    $(".addPreview").attr("src", e.target.result);
                }
            }
        }
    });

    $(".update").on("change", function() {
        if (this.files[0]) {
            var count = $(this).attr("id");
            console.log(count);
            var reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.onload = function(e) {
                $(".updatePreview" + count).attr('src', e.target.result);
            }
        }
    });
    $("#add").on("change", function() {
        if (this.files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.onload = function(e) {
                $(".addPreview").attr('src', e.target.result);
            }
        }
    });

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