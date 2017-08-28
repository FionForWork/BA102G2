<%@page import="javafx.beans.property.IntegerProperty"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	MemService memService = new MemService();
	//     MemVO memVO =memService.getOneMem("1010");
	List<ProductVO> carList = (session.getAttribute("carList") == null)	? new ArrayList<ProductVO>(): (List<ProductVO>) session.getAttribute("carList");
	List<Integer> countList = (session.getAttribute("countList") == null)? new ArrayList<Integer>(): (List<Integer>) session.getAttribute("countList");
	int totalPrice = 0;
	for (int i = 0; i < carList.size(); i++) {
		totalPrice += carList.get(i).getPrice() * countList.get(i);
	}
	int count = 0;
	String preLocation = request.getContextPath() + "/Front_end/mall";
	pageContext.setAttribute("preLocation", preLocation);
%>
<%@include file="pages/indexHeader.file"%>

<style>
div .vertical-center {
	margin-top: 8px
}
</style>

<div class="text-center" style="height: 50px;">
    <h1 style="margin-bottom: 0px; color: #00F">確認訂單</h1>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form id="buy" action="<%=request.getContextPath()%>/order/OrderServlet" method="post">
                <table class="table table-hover table-striped" style="vertical-align: middle;">
                    <thead>
                        <tr>
                            <th>商品名稱</th>
                            <th>賣家編號</th>
                            <th>描述</th>
                            <th>商品價格</th>
                            <th>商品庫存</th>
                            <th>購買數量</th>
                            <th>自購物車中刪除</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${carList}" varStatus="s">
                            <tr>
                                <td><div class="text-center vertical-center">
                                        <a class="pro_name" href="${preLocation}/product.jsp?pro_no=${item.pro_no}">${item.pro_name}</a>
                                    </div></td>
                                <td>
                                    <div class="text-center vertical-center">${item.seller_no}</div>
                                </td>
                                <td>
                                    <div class="text-center vertical-center">${item.pro_desc}</div>
                                </td>
                                <td>
                                    <div class="text-center vertical-center">${item.price}</div>
                                </td>
                                <td>
                                    <div class="text-center vertical-center">${item.amount}</div>
                                </td>
                                <td>
                                    <div class="form-group" style="margin-top: 20px;">
                                        <label class="sr-only" for="buyCount">數量</label>
                                        <input type="number" class="form-control amountInput" name="buyCount" placeholder="${countList[s.index]}" value="${countList[s.index]}" min="1" max="${item.amount}">
                                    </div>
                                <td>
                                    <div class="text-center vertical-center">
                                        <a class="btn btn-danger" href="<%=request.getContextPath()%>/product/ProductServlet?action=DELETE_FROM_CAR&&pro_no=${item.pro_no}">刪除</a>
                                    </div>
                                </td>
                            </tr>
                            <%count++;%>
                        </c:forEach>
                        <tr>
                            <td colspan="6">
                                <div class="form-group">
                                    <label for="address">寄送地址</label>
                                    <input type="text" class="form-control addrInput" id="address" name="address" placeholder="${errorMsg}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-center" colspan="6">
                                <p style="color: #f14195">總金額:<b id="totalPrice" style="font-size: 25px;">$${carTotal}</b></p> 
                                <input type="hidden" name="action" value="BUY"> 
                                <input type="button" onclick="addCheck()" class="btn btn-success" value="確認購買"> 
                                <a class="btn btn-primary " href="${preLocation}/index.jsp">返回</a>
                                <input type="radio" onclick="fakeData()">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    function fakeData() {
        $("#address").val("桃園市平鎮區中大路22號");
    }
    
    $("input").blur(changeTotal);
    $("input").change(changeTotal);
    
    function changeTotal(){
        for (var i = 0; i < $(".amountInput").length; i++) {
            if (Number($(".amountInput")[i].value) > Number($(".amountInput")[i].max)) {
                var errorMessage =  $(".pro_name")[i].text + "超過庫存，請減少數量";
                swal('數量錯誤',
                     errorMessage,
                     'error'
                )
                $(".amountInput")[i].value=1;
            }
        }
        var priceArray=[];
        var countArray=[];
        <%for(int i=0;i<carList.size();i++){%>
            priceArray.push(<%=carList.get(i).getPrice()%>);
            countArray.push(Number($(".amountInput")[<%=i%>].value));
        <%}%>
        $.ajax({
            url:"/BA102G2/product/ProductServlet",
            type : "post",
            data:{
                action:"CAR_CHANGE",
                priceArray:priceArray,
                countArray:countArray
            }, 
            error : function(xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            },
            success : function(response) {
                $("#totalPrice").text("$"+response);
            }
        });
    }
    
    function addCheck() {
        var flag = true;
        var errorMessage = "";
        for (var i = 0; i < $(".amountInput").length; i++) {
            if (Number($(".amountInput")[i].value) > Number($(".amountInput")[i].max)) {
                errorMessage = errorMessage + $(".pro_name")[i].text + "超過庫存，請減少數量\n";
            }
        }
        if ($(".addrInput")[0].value == "") {
            flag = false;
            errorMessage = errorMessage + "請填寫地址";
        }
        if (errorMessage != "") {
            flag = false;
            swal('資訊錯誤',
                 errorMessage,
                 'error'
               )
        }
        if (flag) {
            $("#buy").submit();
        }
    }
</script>
<%@include file="pages/indexFooter.file"%>