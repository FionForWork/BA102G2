<%@page import="java.util.ArrayList"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<ProductVO> carList = (session.getAttribute("carList") == null) ? new ArrayList<ProductVO>() : (List<ProductVO>) session.getAttribute("carList");
    List<Integer> countList = (session.getAttribute("countList") == null) ? new ArrayList<Integer>() : (List<Integer>) session.getAttribute("countList");
    int totalPrice = 0;
    for (int i = 0; i < carList.size(); i++) {
        totalPrice += carList.get(i).getPrice() * countList.get(i);
    }
    int count = 0;
    String preLocation = request.getContextPath() + "/Front_end/mall";
    pageContext.setAttribute("preLocation", preLocation);
%>
<%@include file="pages/mallIndexHeader.file"%>
<div class="text-center" style="height: 50px; margin-top: 50px">
    <h1>確認訂單</h1>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form action="<%=request.getContextPath()%>/order/OrderServlet" method="post">
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                        <tr>
                            <th>商品名稱</th>
                            <th>賣家編號</th>
                            <th>描述</th>
                            <th>商品價格</th>
                            <th>購買數量</th>
                            <th>自購物車中刪除</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${carList}" varStatus="s">
                            <tr>
                                <td>
                                    <a href="${preLocation}/product.jsp?pro_no=${item.pro_no}">${item.pro_name}</a>
                                </td>
                                <td>${item.seller_no}</td>
                                <td>${item.pro_desc}</td>
                                <td>${item.price}</td>
                                <td>
                                    <div class="form-group">
                                        <label class="sr-only" for="buyCount">數量</label>
                                        <input type="number" class="form-control" name="buyCount" placeholder="<%=countList.get(count)%>" value="<%=countList.get(count)%>" min="1" max="${item.amount}">
                                    </div>
                                <td>
                                    <a class="btn btn-danger" href="<%=request.getContextPath()%>/product/ProductServlet?action=DELETE_FROM_CAR&&pro_no=${item.pro_no}">刪除</a>
                                </td>
                            </tr>
                            <%
                                count++;
                            %>
                        </c:forEach>
                        <tr>
                            <td colspan="6">
                                <div class="form-group">
                                    <label for="address">寄送地址</label>
                                    <input type="text" class="form-control" name="address" placeholder="${errorMsg}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-center" colspan="6">
                                <p>總金額:${carTotal}</p>
                                <input type="hidden" name="action" value="BUY">
                                <button type="submit" class="btn btn-success">確認購買</button>
                                <a class="btn btn-primary " href="${preLocation}/mallIndex.jsp">返回</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<%@include file="pages/mallIndexFooter.file"%>