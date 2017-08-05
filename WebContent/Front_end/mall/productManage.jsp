<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.product_type.model.Product_typeVO"%>
<%@page import="com.product_type.model.Product_typeService"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.product.model.ProductService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="pages/mallIndexHeader.file"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    MemVO memVO=(session.getAttribute("memVO")==null)?new MemService().getOneMem("1010"):(MemVO)session.getAttribute("memVO");
    ProductService productService = new ProductService();
    List<ProductVO> productList = productService.getAllBySeller(memVO.getMem_no());
    Product_typeService product_typeService = new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    pageContext.setAttribute("typeList", typeList);

    String preLocation = request.getContextPath() + "/Front_end/mall";
    pageContext.setAttribute("preLocation", preLocation);
    session.setAttribute("productList", productList);
%>
<style>
    .addImg,.updateImg{
        width:200px;
        height: 200px;
        border-color: black;
        border: 1px; 
        border: solid;
    }
     img { 
         width:100%; 
         height:100%; 
     } 
</style>
<div class="text-center" style="height: 50px; margin-top: 50px">
    <h2>商品資料管理</h2>
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
                                <th>價格</th>
                                <th>商品評價</th>
                                <th>修改資訊</th>
                                <th>上/下架</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="productVO" items="${productList}" varStatus="s">
                                <tr>
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
                                        <div class="modal fade" id="${productVO.pro_no}">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">商品資料修改</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="<%=request.getContextPath()%>/product/ProductServlet" method="post" enctype="multipart/form-data">
                                                            <table  class="table">
                                                                <tbody>
                                                                    <tr>
                                                                        <td>商品名稱</td>
                                                                        <td><input id="updateName${s.index}" name="pro_name" class="form-control " type="text" placeholder="${productVO.pro_name}"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>商品描述</td>
                                                                        <td><textarea id="updateDesc${s.index}" name="pro_desc" class="form-control " style="width: 300px; height: 100px;" placeholder="${productVO.pro_desc}"></textarea></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>價格</td>
                                                                        <td><input id="updatePrice${s.index}" name="price" class="form-control " type="number" placeholder="${productVO.price}" min="1"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>庫存</td>
                                                                        <td><input id="updateAmount${s.index}" name="amount" class="form-control " type="number" placeholder="${productVO.amount}" min="1"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>修改圖片(若以拖拉上傳圖片，將自動修改商品資料，請填寫詳細，大小上限為3MB)</td>
                                                                        <td>
                                                                       <div id="${s.index}" class="updateImg"><img class="updatePreview${s.index}"></div>
                                                                       <input type="file" class="update" id="${s.index}" name="img">
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                            <input type="hidden"  id="updateNo${s.index}" name="pro_no" value="${productVO.pro_no}">
                                                            <input type="hidden" name="action" value="UPDATE">
                                                            <input type="submit" class="btn btn-default" value="確認修改">
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <c:choose>
                                        <c:when test="${productVO.status=='0'}">
                                            <td>商品審核中</td>
                                        </c:when>
                                        <c:when test="${productVO.status=='1'}">
                                        <td><div id="status${productVO.pro_no}"><a class="btn btn-danger" href="javascript:onOrOff(${productVO.pro_no})">商品下架</a></div></td>
                                        </c:when>
                                        <c:when test="${productVO.status=='2'}">
                                        <td><div id="status${productVO.pro_no}"><a class="btn btn-danger" href="javascript:onOrOff(${productVO.pro_no})">商品下架</a></div></td>
                                        </c:when>
                                        <c:otherwise>
                                        <td><div id="status${productVO.pro_no}">審核未過，3天後刪除資料</a></div></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#NewProduct">申請新商品上架</button>
                    <div class="modal fade" id="NewProduct">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title">申請新商品上架</h4>
                                </div>
                                <div class="modal-body">
                                    <form id="addForm" action="<%=request.getContextPath()%>/product/ProductServlet" method="post" enctype="multipart/form-data">
                                        <table class="table">
                                            <tbody>
                                                <tr>
                                                    <td>商品名稱</td>
                                                    <td><input id="addName" name="pro_name" type="text" placeholder="必須輸入商品名稱"></td>
                                                </tr>
                                                <tr>
                                                    <td>商品描述</td>
                                                    <td><textarea id="addDesc" name="pro_desc" class="form-control" style="width: 300px; height: 100px;"></textarea></td>
                                                </tr>
                                                <tr>
                                                    <td>價格</td>
                                                    <td><input id="addPrice" name="price" type="number" value="1" min="1" placeholder="1"></td>
                                                </tr>
                                                <tr>
                                                    <td>庫存</td>
                                                    <td><input id="addAmount" name="amount" type="number" value="1" min="1" placeholder="1"></td>
                                                </tr>
                                                <tr>
                                                    <td>商品種類</td>
                                                    <td><select id="addType" name="protype_no">
                                                            <c:forEach var="proType" items="${typeList}">
                                                                <option value="${proType.protype_no}">${proType.type_name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>商品圖片(若拖拉上傳圖片，將自動申請上架，商品資料請填寫詳細，大小上限為3MB)</td>
                                                    <td>
                                                   <div id="${s.index}" class="addImg" ><img class="addPreview"></div>
                                                   <input type="file" id="add" name="img">
                                                    </td>
                                                </tr>
                                            </tbody>    
                                        </table>
                                        <input type="hidden" name="action" value="ADD">
                                        <input type="button" class="btn btn-success" onclick="addCheck()" value="確認申請">
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script>
function addCheck(){
    var pro_name=$("#addName").val();
    if(pro_name==""){
        alert("請輸入商品名稱");
    }
    else{
        $("#addForm").submit();
    }
}

$(".updateImg").on("dragover",function(e){
    e.preventDefault();
});

$(".updateImg").on("drop",function(e){
    e.preventDefault();
    var count=$(this).attr("id");
    var files  = event.dataTransfer.files ; 
    var xhr=new XMLHttpRequest();
    var url="/BA102G2/product/ProductServlet";
    xhr.open('POST', url) ;
    var form=new FormData();
    if(!files[0].type.match("image")){
        var name=files[0].name;
        alert(name+"請上傳圖片!!!!");
    }
    else{
        form.append("img",files[0]);
    }
    form.append("action","UPDATE_AJAX");
    form.append("pro_no",$("#updateNo"+count).val());
    form.append("pro_name",$("#updateName"+count).val());
    form.append("pro_desc",$("#updateDesc"+count).val());
    form.append("price",$("#updatePrice"+count).val());
    form.append("amount",$("#updateAmount"+count).val());
    xhr.send(form);
    xhr.onreadystatechange = function() {
        if(xhr.responseText=="OK"){
            alert("已更新資料");
            var reader=new FileReader();
            reader.readAsDataURL(files[0]);
            reader.onload=function(e){
                $(".updatePreview"+count).attr("src",e.target.result);
            }
        }
    }
});

$(".addImg").on("dragover",function(e){
    e.preventDefault();
});

$(".addImg").on("drop",function(e){
    console.log("add");
    e.preventDefault();
    var files  = event.dataTransfer.files ; 
    var xhr=new XMLHttpRequest();
    var url="/BA102G2/product/ProductServlet?action=ADD_AJAX";
    xhr.open('POST', url) ;
    var form=new FormData();
    if(!files[0].type.match("image")){
        var name=files[0].name;
        alert(name+"請上傳圖片!!!!");
        return;
    }
    else{
        form.append("img",files[0]);
    }
    if($("#addName"+count).val()==""){
        alert("請輸入商品名稱");
        return;
    }
    form.append("pro_name",$("#addName").val());
    form.append("pro_desc",$("#addDesc").val());
    form.append("price",$("#addPrice").val());
    form.append("protype_no",$("#addType").val());
    form.append("amount",$("#addAmount").val());
    xhr.send(form);
    xhr.onreadystatechange = function() {
        if(xhr.responseText=="OK"){
            alert("已申請，請耐心等待審核");
            var reader=new FileReader();
            reader.readAsDataURL(files[0]);
            reader.onload=function(e){
                $(".addPreview").attr("src",e.target.result);
            }
        }
    }
});

$(".update").on("change",function(){
    if(this.files[0]){
        var count=$(this).attr("id");
        console.log(count);
        var reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.onload = function (e) {
        $(".updatePreview"+count).attr('src', e.target.result);
        }
    }
});
$("#add").on("change",function(){
    if(this.files[0]){
        var reader = new FileReader();
        reader.readAsDataURL(this.files[0]);
        reader.onload = function (e) {
        $(".addPreview").attr('src', e.target.result);
        }
    }
});

// function preview(input){
//     if(input.files[0]){
//         var reader = new FileReader();
//         reader.readAsDataURL(input.files[0]);
//         reader.onload = function (e) {
//         $(".updatePreview").attr('src', e.target.result);
//         }
//     }
// }

function onOrOff(pro_no){
    $.ajax({
        url:"/BA102G2/product/ProductServlet",
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
            if(response==1){
                $("#status"+pro_no).html("<a class='btn btn-danger' href='javascript:onOrOff("+pro_no+")'>商品下架</a>");
            }
            else if(response==2){
                $("#status"+pro_no).html("<a class='btn btn-success' href='javascript:onOrOff("+pro_no+")'>商品上架</a>");
            }
        }
    });
}

</script>
    <%@include file="pages/mallIndexFooter.file"%>