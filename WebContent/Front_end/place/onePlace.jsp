<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%-- <%@page import="com.mysql.fabric.xmlrpc.base.Array"%> --%>
<%@page import="com.placeview.model.PlaceViewVO"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="com.place.model.PlaceService"%>
<%@page import="com.placeview.model.PlaceViewService"%>
<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.place.model.PlaceVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/Front_end/place/pages/frontHeader.file"%>
<%
    String pla_no = request.getParameter("pla_no");
    PlaceService placeService = new PlaceService();
    PlaceVO placeVO = placeService.getOnePlace(pla_no);
    ArrayList<PlaceViewVO> viewList=new ArrayList<PlaceViewVO>();
    viewList.addAll(placeVO.getPlaceViewSet());
    pageContext.setAttribute("viewList", viewList);
    pageContext.setAttribute("placeVO", placeVO);
%>

<div class="container" style="margin-bottom: 20px;">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center" style="font-size: 20px;">
            <h3><b>景點名稱:</b>${placeVO.name}</h3>
            <h3><b>景點地址:</b>${placeVO.addr}</h3>
            <h3><b>景點描述:</b>${placeVO.pla_desc}</h3>
        </div>
        <c:forEach var="view" items="${viewList}">
            <div id="placeImg" class="col-xs-3 col-sm-3 col-md-3 col-lg-3" style="margin-bottom: 20px;">
                <img class="img-responsive"  src="<%=request.getContextPath()%>/image/ShowImage?view_no=${view.view_no}">
            </div>
        </c:forEach>
    </div>
</div>



<%@include file="/Front_end/place/pages/frontFooter.file"%>