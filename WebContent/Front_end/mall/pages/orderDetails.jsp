<%@page import="com.product.model.ProductService"%>
<%@page import="com.order_detail.model.Order_detailVO"%>
<%@page import="com.order_detail.model.Order_detailService"%>
<%@page import="com.ord.model.OrdVO"%>
<%@page import="com.ord.model.OrdService"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    OrdService ordService = new OrdService();
    String ord_no = request.getParameter("ord_no");
    String role = request.getParameter("role");
    String status = request.getParameter("status");
    String orderType = request.getParameter("orderType");
    String nowPage = request.getParameter("nowPage");
    String index = request.getParameter("index");
    OrdVO ordVO = ordService.getOneByPK(ord_no);
    Order_detailService order_detailService = new Order_detailService();
    List<Order_detailVO> detailList = order_detailService.getAllByOrd(ord_no);
    MemService memService = new MemService();
    MemVO cust = memService.getOneMem(ordVO.getCust_no());
    MemVO seller = memService.getOneMem(ordVO.getSeller_no());
    ProductService productService = new ProductService();
    String[] statusList = {"買家未付款", "賣家未出貨", "已完成訂單", "賣家已評價", "已取消訂單"};
    pageContext.setAttribute("statusList", statusList);
    pageContext.setAttribute("detailList", detailList);
    pageContext.setAttribute("productService", productService);
    pageContext.setAttribute("ordVO", ordVO);
    pageContext.setAttribute("cust", cust);
    pageContext.setAttribute("seller", seller);
    pageContext.setAttribute("role", role);
    pageContext.setAttribute("status", status);
    pageContext.setAttribute("orderType", orderType);
    pageContext.setAttribute("nowPage", nowPage);
    pageContext.setAttribute("index", index);		
%>
<div class="panel-group" id="accordion">
    <div class="panel">
        <div class="panel-heading text-center">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#collapse${ordVO.ord_no}">
                    <c:choose>
                        <c:when test="${role=='0'}">
                            <img src="<%=request.getContextPath()%>/image/ShowImage?mem_no=${seller.mem_no}" class="img-circle" style="width: 30px; height: 30px">
                            <label>賣家名稱:${seller.name}</label>
                        </c:when>
                        <c:otherwise>
                            <img src="<%=request.getContextPath()%>/image/ShowImage?mem_no=${cust.mem_no}" class="img-circle" style="width: 30px; height: 30px">
                            <label>買家名稱:${cust.name}</label>
                        </c:otherwise>
                    </c:choose>
                    訂單總價:NT$${ordVO.total}
                    <i style="color: #f14195" aria-hidden="true"></i>
                </a>
            </h4>
        </div>
    </div>
    <div id="collapse${ordVO.ord_no}" class="panel-collapse collapse">
        <div class="panel-body">
            <div class="row">
                <div class="text-left col-md-6">
                    <p>成立日期:<fmt:formatDate value="${ordVO.ord_date}" pattern="yyyy-MM-dd HH:mm" /></p>
                    <p>買家寄送地址:${ordVO.address}</p>
                </div>
                <div class="text-right col-md-6">
                    <p>買家電話:${cust.phone}</p>
                    <p>買家電子信箱:${cust.email}</p>
                </div>
            </div>
            <hr>
            <c:forEach var="detail" items="${detailList}">
                <div class="row">
                    <div class="col-md-8 text-left">
                        <img src="<%=request.getContextPath()%>/image/ShowImage?pro_no=${detail.pro_no}" style="height: 100px;">
                        <b>${productService.getOneByPKNoImg(detail.pro_no).pro_name}</b>
                        ${productService.getOneByPKNoImg(detail.pro_no).pro_desc}
                    </div>
                    <div class="col-md-4">
                        <div class="col-md-6">
                            <label>購買數量 X ${detail.qty}</label>
                        </div>
                        <div class="col-md-6">
                            <label>商品單價${detail.price}</label>
                        </div>
                    </div>
                </div>
                <hr style="height: 3px; background-color: #426255;">
            </c:forEach>
            <hr style="height: 10px; background-color: #ff2266;">
            <div>
                <div class="text-left">
                    <c:choose>
                        <c:when test="${role=='0' && status=='0'}">
                            <div class="col-md-6">
                                <p>請盡速繳款</p>
                                <p>賣家帳戶:${seller.account}</p>
                                <p>賣家電話:${seller.phone}</p>
                                <p>賣家電子信箱:${seller.email}</p>
                                <p><a href="javascript:orderCancel(${nowPage},${ordVO.ord_no},${role},${status},${orderType})" class="btn btn-danger">取消訂單</a></p>
                            </div>
                        </c:when>
                        <c:when test="${role=='0' && status=='1'}">
                            <a href="javascript:checkGet('CHECK_GET_ITEM',${nowPage},${ordVO.ord_no},${role},${status},${orderType})" class="btn btn-info">確認已收到貨物</a>
                        </c:when>
                        <c:when test="${role=='1' && status=='0'}">
                            <a href="javascript:checkGet('CHECK_GET_MONEY',${nowPage},${ordVO.ord_no},${role},${status},${orderType})" class="btn btn-info">確認已收到貨款</a>
                        </c:when>
                        <c:when test="${role=='1' && status=='1'}">
                            <p>請盡速出貨</p>
                            <p>買家姓名:${cust.name}</p>
                        </c:when>
                        <c:when test="${role=='0' && status=='2'}">
                            <a class="btn btn-info" onclick="showModal('EVAL_TO_PRODUCT','${ordVO.ord_no}','${nowPage}')">對商品評分</a>
                        </c:when>
                        <c:when test="${role=='0' && status=='3'}">
                            <p>賣家已對您評價:${ordVO.score}分</p>
                        </c:when>
                        <c:when test="${role=='1' && status=='2'}">
                            <a class="btn btn-info" onclick="showModal('EVAL_TO_MEM','${ordVO.ord_no}','${nowPage}')">對買家評分</a>
                        </c:when>
                        <c:when test="${role=='1' && status=='3'}">
                            <p>賣家已對買家評價:${ordVO.score}分</p>
                        </c:when>
                    </c:choose>
                </div>
                <div class="text-right">
                    <h4>
                        <label>總價格$${ordVO.total}元</label>
                    </h4>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="evalToProduct"></div>
﻿
<div class="modal fade" id="evalToMem"></div>
<script type="text/javascript">


function orderCancel(nowPage,ord_no,role,status,orderType){ 
    $.ajax({
        url : "<%=request.getContextPath()%>/order/OrderServlet",
        type : "post",
        data : {
        action : "CANCEL_AJAX",
        ord_no :ord_no,
        },
        error : function(xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        },
        success : function(response) {
            tabsChange(role,status,orderType);
            pageChange(nowPage,role,status,orderType);
        }
    });
}

function checkGet(check,nowPage,ord_no,role,status,orderType){ 
    $.ajax({
        url : "<%=request.getContextPath()%>/order/OrderServlet",
        type : "post",
        data : {
        action : check,
        ord_no : ord_no,
        },
        error : function(xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        },
        success : function(response) {
            if(check=="CHECK_GET_ITEM"){
                swal('成功',
                     '已確認收到貨物',
                     'success'
                )
            }
            else{
                swal('成功',
                     '已確認收到貨款',
                     'success'
                   )
            }
            tabsChange(role, status, orderType);
            pageChange(nowPage, role, status, orderType);
        }
        });
    }
</script>