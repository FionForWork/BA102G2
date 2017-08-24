<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
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
	OrdService ordService = new OrdService();
	String preLocation = request.getContextPath() + "/Front_end/mall";
	//////////////////////分頁需求參數//////////////////////////////////
	int itemsCount = 5;
	int nowPage = (request.getParameter("nowPage") == null)	? 1 : Integer.valueOf(request.getParameter("nowPage"));
	int allCount = ordService.getAllRowCountByRole("0", memVO.getMem_no(), "3");
	int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
	List<OrdVO> ordList = ordService.getPageByRole(nowPage, itemsCount, "0", memVO.getMem_no(), "3", "0");
	double avg = 0.0;
	if (ordList.size() > 0) {
		for (int i = 0; i < ordList.size(); i++) {
			avg += ordList.get(i).getScore();
		}
		avg = avg / ordList.size();
	}
	pageContext.setAttribute("ordList", ordList);
	pageContext.setAttribute("itemsCount", itemsCount);
	pageContext.setAttribute("nowPage", nowPage);
	pageContext.setAttribute("totalPages", totalPages);
	//////////////////////分頁需求參數//////////////////////////////////
    String active="1";
    pageContext.setAttribute("active", active);
	pageContext.setAttribute("preLocation", preLocation);
	pageContext.setAttribute("avg", avg);
%>
<%@include file="pages/indexHeader.file"%>
<div class="text-center" style="height: 50px; margin-top: 50px">
    <h2>
        您的平均評價為
        <fmt:formatNumber value="${avg}" maxFractionDigits="1" />
    </h2>
</div>
<div class="container" id="container">
    <div class="row">
        <div class="col-md-12">
            <%@include file="pages/mallAreaSidebar.file"%>
            <div id="content" class="col-md-10">
                <c:forEach var="ord" items="${ordList}" varStatus="s">
                    <jsp:include page="pages/orderDetails.jsp?ord_no=${ord.ord_no}&&status=3&&role=0&&orderType=0&&nowPage=${nowPage}&&index=${s.index}" flush="true" />
                </c:forEach>
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
                                        <li><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>
                        <c:when test="${nowPage<5}">
                            <c:forEach var="i" begin="1" end="5">
                                <c:choose>
                                    <c:when test="${nowPage==i}">
                                        <li><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
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
                                        <li><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
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
                                        <li><a class="btn btn-info active" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="btn btn-info" href="javascript:change(${i})" data-page="${i}">${i}</a></li>
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
        <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
    </div>
</div>
<script type="text/javascript">

    function change(nowPage){ 
        $(window).scrollTop("0");
        $(window).scrollLeft("0");
        $("#container").load("<%=preLocation%>/memberScore.jsp #container",{"nowPage":nowPage});
<%--         $(".pagination").load("<%=preLocation%>/memberScore.jsp .pagination", { --%>
//             "nowPage" : nowPage
//         });
    }
</script>
<%@include file="pages/indexFooter.file"%>