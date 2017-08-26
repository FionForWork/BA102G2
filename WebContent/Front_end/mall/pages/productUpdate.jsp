<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String pro_no = request.getParameter("pro_no");
    String nowPage = request.getParameter("nowPage");
    ProductService productService = new ProductService();
	ProductVO productVO = productService.getOneByPK(pro_no);
	pageContext.setAttribute("productVO", productVO);
	pageContext.setAttribute("nowPage", nowPage);
%>
<style>
 .updateImg {
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
<%-- <div class="modal fade" id="updateProduct${index}"> --%>
    <div class='modal-dialog'>
        <div class='modal-content'>
            <div class='modal-header'>
                <h4 class='modal-title'>商品資料修改</h4>
            </div>
            <div class='modal-body'>
                <form action='<%=request.getContextPath()%>/product/ProductServlet' method='post' enctype='multipart/form-data'>
                    <table class='table table-hover table-striped'>
                        <tbody>
                            <tr>
                                <td>商品名稱</td>
                                <td><input id='updateName' name='pro_name' class='form-control ' type='text' placeholder='${productVO.pro_name}'></td>
                            </tr>
                            <tr>
                                <td>商品描述</td>
                                <td><textarea id='updateDesc' name='pro_desc' class='form-control ' style='width: 300px; height: 100px;' placeholder='${productVO.pro_desc}'></textarea></td>
                            </tr>
                            <tr>
                                <td>價格</td>
                                <td><input id='updatePrice' name='price' class='form-control ' type='number' placeholder='${productVO.price}' min='1'></td>
                            </tr>
                            <tr>
                                <td>庫存</td>
                                <td><input id='updateAmount' name='amount' class='form-control ' type='number' placeholder='${productVO.amount}' min='1'></td>
                            </tr>
                            <tr>
                                <td>修改圖片(若以拖拉上傳圖片，將自動修改商品資料，請填寫詳細，大小上限為3MB)</td>
                                <td>
                                    <div class='updateImg'>
                                        <img class='img updatePreview' src="<%=request.getContextPath()%>/image/ShowImage?pro_no=${productVO.pro_no}">
                                    </div> 
                                    <input type='file' class='update' name='img'>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <input type='hidden' id='nowPage' name='nowPage' value='${nowPage}'>
                    <input type='hidden' id='updateNo' name='pro_no' value='${productVO.pro_no}'>
                    <input type='hidden' name='action' value='UPDATE'>
                    <input type='submit' class='btn btn-default' value='確認修改'>
                </form>
            </div>
            <div class='modal-footer'>
                <button type='button' class='btn btn-default' data-dismiss='modal'>取消</button>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">

    $(".updateImg").on("dragover", function(e) {
        e.preventDefault();
    });

    $(".updateImg").on("drop", function(e) {
        e.preventDefault();
        var files = event.dataTransfer.files;
        var xhr = new XMLHttpRequest();
        var url = "/BA102G2/product/ProductServlet";
        xhr.open('POST', url);
        var form = new FormData();
        if (!files[0].type.match("image")) {
            var name = files[0].name;
            alert(name + " 不是圖片，請上傳圖片!!!!");
            return;
        }
        else {
            var reader = new FileReader();
            form.append("img", files[0]);
            form.append("action", "UPDATE_AJAX");
            form.append("pro_no", $("#updateNo").val());
            form.append("pro_name", $("#updateName").val());
            form.append("pro_desc", $("#updateDesc").val());
            form.append("price", $("#updatePrice").val());
            form.append("amount", $("#updateAmount").val());
            xhr.send(form);
            xhr.onreadystatechange = function() {
                if (xhr.responseText == "OK") {
                    reader.readAsDataURL(files[0]);
                    reader.onload = function(e) {
                        document.location.href="<%=request.getContextPath()%>/Front_end/mall/productManagement.jsp";
                    }
                }
            }
        }
    });
    
    $(".update").on("change", function() {
        if (this.files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.onload = function(e) {
                $(".updatePreview").attr('src', e.target.result);
            }
        }
    });
    
</script>
