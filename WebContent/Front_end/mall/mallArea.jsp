<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page import="com.order_detail.model.Order_detailVO"%>
<%@page import="com.order_detail.model.Order_detailService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ord.model.OrdVO"%>
<%@page import="java.util.List"%>
<%@page import="com.ord.model.OrdService"%>
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
	session.setAttribute("memVO", memVO);
	String role = (request.getParameter("role") == null) ? "0" : request.getParameter("role");
	String status = (request.getParameter("status") == null) ? "0" : request.getParameter("status");
	String orderType = (request.getParameter("orderType") == null) ? "0" : request.getParameter("orderType");
	String[] statusList = {"買家未付款", "賣家未出貨", "已完成訂單", "賣家已評價", "已取消訂單"};
	String[] orderTypeList = {"預設", "依對方編號小>>大", "依對方編號大>>小", "依寄送地址", "依成立日期舊>>新", "依成立日期新>>舊", "依訂單總價小>>大","依訂單總價大>>小"};
	OrdService ordService = new OrdService();
	int nowPage = (request.getParameter("nowPage") == null)? 1 :Integer.valueOf(request.getParameter("nowPage"));
	int allCount = ordService.getAllRowCountByRole(role, memVO.getMem_no(), status);
	int itemsCount = 5;
	int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
	List<OrdVO> ordList = ordService.getPageByRole(nowPage, itemsCount, role, memVO.getMem_no(), status,
	orderType);
	List<String> seller_accountList = new ArrayList<String>();
	List<String> cust_nameList = new ArrayList<String>();
	for (int i = 0; i < ordList.size(); i++) {
        seller_accountList.add(memService.getOneMem(ordList.get(i).getSeller_no()).getAccount());
        cust_nameList.add(memService.getOneMem(ordList.get(i).getCust_no()).getName());
	}
	Order_detailService order_detailService = new Order_detailService();
	ProductService productService = new ProductService();

	StringBuffer stringBuffer = new StringBuffer();
	if ("0".equals(role)) {
        stringBuffer.append("買家-");
	} else {
        stringBuffer.append("賣家-");
	}

	if ("0".equals(status)) {
        stringBuffer.append("買家未付款");
	} else if ("1".equals(status)) {
        stringBuffer.append("賣家未出貨");
	} else if ("2".equals(status)) {
        stringBuffer.append("已完成訂單");
	} else if ("3".equals(status)) {
        stringBuffer.append("賣家已評價");
	} else {
        stringBuffer.append("已取消訂單");
	}

	String preLocation = request.getContextPath() + "/Front_end/mall";
	String location = "/Front_end/mall/mallArea.jsp?nowPage=" + nowPage + "&&role=" + role + "&&status="+ status + "&&orderType=" + orderType;
	List<ProductVO> productList = new ArrayList<ProductVO>();
    String active=(role.equals("0"))?"0":"3";
	pageContext.setAttribute("active", active);
	pageContext.setAttribute("mem_name", memVO.getName());
	pageContext.setAttribute("location", location);
	pageContext.setAttribute("productList", productList);
	pageContext.setAttribute("status", status);
	pageContext.setAttribute("nowPage", nowPage);
	pageContext.setAttribute("itemsCount", itemsCount);
	pageContext.setAttribute("totalPages", totalPages);
	pageContext.setAttribute("productService", productService);
	pageContext.setAttribute("orderTypeList", orderTypeList);
	pageContext.setAttribute("preLocation", preLocation);
	pageContext.setAttribute("statusList", statusList);
	pageContext.setAttribute("role", role);
	pageContext.setAttribute("orderType", orderType);
	pageContext.setAttribute("ordList", ordList);
	pageContext.setAttribute("stringBuffer", stringBuffer);
%>
<%@include file="pages/indexHeader.file"%>
<style>
<!--
.tab-content{
    padding: 20px 50px;
    background: rgba(255,200,180,0.8);
    font-size: 1.2em;
}
.nav-tabs > li.active {
  color: #000;
  background-color: rgba(255,200,180,0.6);  
} 
-->
</style>
<div style="margin-top: 50px;"></div>
<div class="container" id="container" >
    <div class="row">
        <div id="showStatus" class="text-center col-md-12">
            <h2>${stringBuffer.toString()}</h2>
        </div>
        <div class="col-md-12">
            <%@include file="pages/mallAreaSidebar.file"%> 
            <div class="col-xs-1 col-md-1"></div>
            <div class="col-xs-7 col-md-7">
                <ul class="nav nav-tabs" role="tablist" id="maintab">
                    <c:forEach var="state" items="${statusList}" varStatus="s">
                        <c:choose>
                            <c:when test="${s.index==status}">
                                <li class="active"><a onmouseover="tabsChange(${role},${s.index},${orderType})">${state}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class=""><a onmouseover="tabsChange(${role},${s.index},${orderType})">${state}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tab">
                        <div id="detailContent">
                            <c:forEach var="ord" items="${ordList}" varStatus="s">
                                <jsp:include page="pages/orderDetails.jsp?ord_no=${ord.ord_no}&&status=${status}&&role=${role}&&orderType=${orderType}&&nowPage=${nowPage}&&index=${s.index}" flush="true" />
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <!--//////////////////////////////////////////分頁開始//////////////////////////////////////////////////////////////// -->
                <div class="text-center">
                    <nav aria-label="Page navigation ">
                        <ul class="pagination pagination-lg ">
                            <c:choose>
                                <c:when test="${totalPages<=5}">
                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                        <c:choose>
                                            <c:when test="${i==nowPage}">
                                                <li><a class="btn btn-info active" href="javascript:pageChange(${i},${role},${status},${orderType})" data-page="${i}">${i}</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a class="btn btn-info" href="javascript:pageChange(${i},${role},${status},${orderType})" data-page="${i}">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${nowPage<5}">
                                    <c:forEach var="i" begin="1" end="5">
                                        <c:choose>
                                            <c:when test="${i==nowPage}">
                                                <li><a class="btn btn-info active" href="javascript:pageChange(${i},${role},${status},${orderType})" data-page="${i}">${i}</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a class="btn btn-info" href="javascript:pageChange(${i},${role},${status},${orderType})" data-page="${i}">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <li><a class="disabled">...</a></li>
                                    <li><a class="btn btn-info" href="javascript:pageChange(${totalPages},${role},${status},${orderType})" data-page="${totalPages}">${totalPages}</a></li>
                                </c:when>
                                <c:when test="${totalPages-nowPage<5}">
                                    <li><a class="btn btn-info" href="javascript:pageChange(1,${role},${status},${orderType})" data-page="1">1</a></li>
                                    <li><a class="disabled">...</a></li>
                                    <c:forEach var="i" begin="${totalPages-5}" end="${totalPages}">
                                        <c:choose>
                                            <c:when test="${i==nowPage}">
                                                <li><a class="btn btn-info active" href="javascript:pageChange(${i},${role},${status},${orderType})" data-page="${i}">${i}</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a class="btn btn-info" href="javascript:pageChange(${i},${role},${status},${orderType})" data-page="${i}">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <li><a class="btn btn-info" href="javascript:pageChange(1,${role},${status},${orderType})" data-page="1">1</a></li>
                                    <li><a class="disabled">...</a></li>
                                    <c:forEach var="i" begin="${nowPage-2}" end="${nowPage+2}">
                                        <c:choose>
                                            <c:when test="${i==nowPage}">
                                                <li><a class="btn btn-info active" href="javascript:pageChange(${i},${role},${status},${orderType})" data-page="${i}">${i}</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <li><a class="btn btn-info" href="javascript:pageChange(${i},${role},${status},${orderType})" data-page="${i}">${i}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <li><a class="disabled">...</a></li>
                                    <li><a class="btn btn-info" href="javascript:pageChange(${totalPages},${role},${status},${orderType})" data-page="${totalPages}">${totalPages}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>
            <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
            <div class="col-xs-2 col-md-2">
                <div id="orderType" class="btn-group">
                    <button class="btn btn-default" style="width: 150px;">${orderTypeList[orderType]}</button>
                    <button data-toggle="dropdown" class="btn btn-default"><span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <c:forEach var="orderType" items="${orderTypeList}" varStatus="s">
                            <li><a href="javascript:tabsChange(${role},${status},${s.index})">${orderType}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var orignStatus=0;
    var orignorderType=0;
    
    function tabsChange(role,status,orderType){ 
        if(orignStatus==status&&orignorderType==orderType){
            return;
        }
        else{
            orignStatus=status;
            orignorderType=orderType;
            $(window).scrollTop( $(window).scrollTop());
            $(window).scrollLeft($(window).scrollLeft());
            $(this).tab('show');
            $("#container").load("${preLocation}/mallArea.jsp #container",{"role":role,"status":status,"orderType":orderType});
        } 
    }
    
    function pageChange(nowPage,role,status,orderType) {
        $("#detailContent").load("${preLocation}/mallArea.jsp #detailContent",{"role":role,"status":status,"orderType":orderType});
        $(".pagination").load("${preLocation}/mallArea.jsp .pagination",{"nowPage":nowPage,"role":role,"status":status,"orderType":orderType}); 
        $(window).scrollTop("0");
        $(window).scrollLeft($(window).scrollLeft());
    }
</script>
<%@include file="pages/indexFooter.file"%>