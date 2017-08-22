<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String ord_no = request.getParameter("ord_no");
    String orderType = request.getParameter("orderType");
    String nowPage = request.getParameter("nowPage");
    pageContext.setAttribute("ord_no", ord_no);
    pageContext.setAttribute("orderType", orderType);
    pageContext.setAttribute("nowPage", nowPage);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">評分</h4>
            </div>
            <div class="modal-body">
                <form action="<%=request.getContextPath()%>/order/OrderServlet" method="post">
                    <table class="table">
                        <tr>
                            <td>評分</td>
                            <input type="hidden" name="ord_no" value="${ord_no}">
                            <td><input type="number" min="0" max="5" name="score" class="form-control" placeholder="請輸入評分0-5之間"></td>
                        </tr>
                    </table>
                    <input type="hidden" name="nowPage" value="${nowPage}">
                    <input type="hidden" name="orderType" value="${orderType}">
                    <input type="hidden" name="action" value="EVALUATION_TO_MEM">
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