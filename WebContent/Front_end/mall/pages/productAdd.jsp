<%@page import="com.mem.model.MemVO"%>
<%@page import="com.product_type.model.Product_typeService"%>
<%@page import="java.util.List"%>
<%@page import="com.product_type.model.Product_typeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    Product_typeService product_typeService = new Product_typeService();    
    List<Product_typeVO> typeList = product_typeService.getAll();
    MemVO memVO=(MemVO)session.getAttribute("memVO");
    pageContext.setAttribute("memVO", memVO);
    pageContext.setAttribute("typeList", typeList);
%>
<style>
.addImg {
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
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <h4 class="modal-title">申請新商品上架</h4>
        </div>
        <div class="modal-body">
            <form id="addForm" action="<%=request.getContextPath()%>/product/ProductServlet" method="post" enctype="multipart/form-data">
                <table class="table table-hover table-striped">
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
                            <td>
                                <select id="addType" name="protype_no">
                                    <c:forEach var="proType" items="${typeList}">
                                        <option value="${proType.protype_no}">${proType.type_name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>商品圖片(若拖拉上傳圖片，將自動申請上架，商品資料請填寫詳細，大小上限為3MB)</td>
                            <td>
                                <div class="addImg">
                                    <img class="img addPreview">
                                </div> 
                                <input type="file" id="add" name="img">
                            </td>
                        </tr>
                    </tbody>
                </table>
                <input type="hidden" name="action" value="ADD">
                <input type="button" class="btn btn-success" onclick="addCheck()" value="確認申請">
            </form>
                <input type="radio" onclick="fakeData()">
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
function fakeData() {
    $("#addName").val("彩蛋組");
    $("#addDesc").val("永結同心小彩蛋");
    $("#addPrice").val("500");
    $("#addAmount").val("500");
    $("#addType").val("3");
}
    function addCheck() {
        var pro_name = $("#addName").val();
        var pro_desc = $("#addDesc").val();
        var img=$(".addPreview").attr("src");
        console.log(img);
        if (pro_name == "") {
            swal('缺少資訊!',
                 '請輸入商品名稱!',
                 'error'
            )
            return;
        }
        else if(!img.match("img")){
            swal('錯誤資訊!',
                    '必須是圖片!',
                    'error'
               )
               return;
        }
        else if (pro_desc == "") {
            swal('缺少資訊!',
                 '請輸入商品描述!',
                 'error'
               )
            return;
        }
        else if (img == null) {
            swal('缺少資訊!',
                 '請上傳商品影像!',
                 'error'
                  )
            return;
        }
        else {
            swal('已申請，請耐心等候審核',
                 '請輸入商品描述!',
                 'success'
            )
            $("#addForm").submit();
        }
    }

    $(".addImg").on("dragover", function(e) {
        e.preventDefault();
    });

    $(".addImg").on("drop", function(e) {
        e.preventDefault();
        var files = event.dataTransfer.files;
        var xhr = new XMLHttpRequest();
        var url = "/BA102G2/product/ProductServlet?action=ADD_AJAX";
        xhr.open('POST', url);
        var form = new FormData();
        if(files==null){
            swal('錯誤資訊!',
                 '請上傳商品影像!',
                 'error'
            )
            return;
        }
        else if (!files[0].type.match("image")) {
            var name = files[0].name;
            swal('錯誤資訊!',
                 '必須是圖片!',
                 'error'
            )
            return;
        }
        else if ($("#addName").val() == "") {
            swal('缺少資訊!',
                 '請輸入商品名稱!',
                 'error'
            )
            return;
        }
        else if ($("#addDesc").val() == "") {
            swal('缺少資訊!',
                 '請輸入商品描述!',
                 'error'
            )
            return;
        }
        else{
            var reader = new FileReader();
            form.append("img", files[0]);
            form.append("pro_name", $("#addName").val());
            form.append("pro_desc", $("#addDesc").val());
            form.append("price", $("#addPrice").val());
            form.append("protype_no", $("#addType").val());
            form.append("amount", $("#addAmount").val());
            xhr.send(form);
            xhr.onreadystatechange = function() {
                if (xhr.responseText !=null) {
                console.log(xhr.responseText);
                    reader.readAsDataURL(files[0]);
                    reader.onload = function(e) {
                        document.location.href="<%=request.getContextPath()%>/Front_end/mall/productManagement.jsp";
                    }
                }
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
</script>
