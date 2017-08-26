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
    ArrayList<PlaceViewVO> viewList = new ArrayList<PlaceViewVO>();
    viewList.addAll(placeVO.getPlaceViewSet());
    pageContext.setAttribute("viewList", viewList);
    pageContext.setAttribute("placeVO", placeVO);
    pageContext.setAttribute("pla_no", pla_no);
%>

<div class="container" style="margin-bottom: 20px;">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-left" style="font-size: 20px;">
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <h3>
                    <b>景點名稱:</b>${placeVO.name}</h3>
                <h3>
                    <b>景點地址:</b>${placeVO.addr}</h3>
            </div>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <c:forEach var="view" items="${viewList}">
                <div id="placeImg" class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="margin-bottom: 20px; height: 200px;">
                    <img class="aa img-responsive" src="<%=request.getContextPath()%>/image/ShowImage?view_no=${view.view_no}" oncancel="openLightBox(this)">
                </div>
            </c:forEach>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <c:choose>
                <c:when test="${memVO!=null}">
                    <h3>
                        <b>景點描述</b>
                        &nbsp;&nbsp;&nbsp;
                        <a class="btn btn-success" href="<%=request.getContextPath()%>/Front_end/Preview/ImageCropper.jsp?pla_no=${pla_no}">前往實景預覽</a>
                    </h3>
                    <div style="font-size: 20px;">${placeVO.pla_desc}</div>
                </c:when>
                <c:otherwise>
                    <h3>
                        <b>景點描述</b>
                        &nbsp;&nbsp;&nbsp;
                    </h3>
                    <div style="font-size: 20px;">${placeVO.pla_desc}</div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>
<div id="lightboxImgModal" class="modal">
    <span class="closeImg" onclick='closeLightBox()'>&times;</span>
    <img class="lightbox-modal-content" id="lightboxImg">
</div>


<%@include file="/Front_end/place/pages/frontFooter.file"%>