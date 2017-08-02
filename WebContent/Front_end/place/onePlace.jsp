<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="com.place.model.PlaceVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/Front_end/pages/frontHeader.file"%>

<%
    List<PlaceVO> placeList = (List<PlaceVO>) session.getAttribute("placeList");
    String pla_no = request.getParameter("pla_no");
    for (PlaceVO placeVO : placeList) {
        if (placeVO.getPla_no().equals(pla_no)) {
            pageContext.setAttribute("placeVO", placeVO);
        }
    }
%>

<div class="container">
    <div class="row">
        <div id="placeImg" class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <img src="<%=request.getContextPath()%>/image/ShowImage?pla_no=${placeVO.pla_no}">
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <dt>景點名稱</dt>
            <dd>${placeVO.name}</dd>
            <dt>景點地址</dt>
            <dd>${placeVO.addr}</dd>
        </div>
    </div>
</div>



<%@include file="/Front_end/pages/frontFooter.file"%>