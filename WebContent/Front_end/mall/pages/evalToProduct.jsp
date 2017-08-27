<%@page import="com.product.model.ProductService"%>
<%@page import="java.util.List"%>
<%@page import="com.order_detail.model.Order_detailService"%>
<%@page import="com.order_detail.model.Order_detailVO"%>
<%@page import="com.ord.model.OrdVO"%>
<%@page import="com.ord.model.OrdService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<%
    String ord_no = request.getParameter("ord_no");
    String orderType = request.getParameter("orderType");
    String nowPage = request.getParameter("nowPage");
    ProductService productService=new ProductService();
    Order_detailService order_detailService = new Order_detailService();
    List<Order_detailVO> detailList = order_detailService.getAllByOrd(ord_no);
    pageContext.setAttribute("productService", productService);
    pageContext.setAttribute("ord_no", ord_no);
    pageContext.setAttribute("detailList", detailList);
    pageContext.setAttribute("orderType", orderType);
    pageContext.setAttribute("nowPage", nowPage);
    System.out.println(nowPage);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title">評分</h4>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/order/OrderServlet" method="post">
                    <table class="table">
                        <tr>
                            <td>商品名稱</td>
                            <td>評價</td>
                        </tr>
                        <input type="hidden" name="ord_no" value="${ord_no}">
                        <c:forEach var="order_detailVO" items="${detailList}">
                            <tr>
                                <td><a target="_blank" href="${preLocation}/product.jsp?pro_no=${order_detailVO.pro_no}">${productService.getOneByPKNoImg(order_detailVO.pro_no).pro_name}</a></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${order_detailVO.status=='0'}">
                                            <input type="hidden" name="pro_no" value="${order_detailVO.pro_no}">
                                            <input type="hidden" name="nowPage" value="${nowPage}">
                                            <input type="hidden" name="orderType" value="${orderType}">
                                            <input name="score" value="0" type="number" min="0" max="5" class="form-control" placeholder="請輸入評分0-5之間">
                                        </c:when>
                                        <c:otherwise>
                                            <p>您已評價過，分數為${order_detailVO.score}</p>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <input type="hidden" name="nowPage" value="${nowPage}">
                    <input type="hidden" name="orderType" value="${orderType}">
                    <input type="hidden" name="action" value="EVALUATION_TO_ITEM">
                    <input type="submit" class="btn btn-success" value="確認">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</body>
</html>