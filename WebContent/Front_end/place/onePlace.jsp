<%@page import="com.place.model.PlaceService"%>
<%@page import="com.placeview.model.PlaceViewService"%>
<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.place.model.PlaceVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/Front_end/pages/frontHeader.file"%>
<%
    String pla_no = request.getParameter("pla_no");
    PlaceService placeService = new PlaceService();
    PlaceVO placeVO = placeService.getOnePlace(pla_no);
    PlaceViewService placeViewService = new PlaceViewService();
    List<String> viewList = placeViewService.getAllByFk(pla_no);
    pageContext.setAttribute("viewList", viewList);
    pageContext.setAttribute("placeVO", placeVO);
%>

<div class="container" style="margin-bottom: 20px;">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center" style="font-size: 20px;">
                <h3><b>景點名稱</b></h3>
                ${placeVO.name}
                <h3><b>景點地址</b></h3>
                ${placeVO.addr}
        </div>
        <c:forEach var="view" items="${viewList}">
            <div id="placeImg" class="col-xs-4 col-sm-4 col-md-4 col-lg-4" style="margin-bottom: 20px;">
                <img src="<%=request.getContextPath()%>/image/ShowImage?view_no=${view}">
            </div>
        </c:forEach>
    </div>
</div>



<%@include file="/Front_end/pages/frontFooter.file"%>