<%@page import="com.placeview.model.PlaceViewVO"%>
<%@page import="java.util.List"%>
<%@page import="com.placeview.model.PlaceViewService"%>
<%@page import="com.place.model.PlaceVO"%>
<%@page import="com.place.model.PlaceService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/Back_end/pages/backHeader.file"%>
<%
    String pla_no = request.getParameter("pla_no");
			PlaceService placeService = new PlaceService();
			PlaceVO placeVO = placeService.getOnePlace(pla_no);
			PlaceViewService placeViewService = new PlaceViewService();
			List<String> viewList = placeViewService.getAllByFk(pla_no);
			pageContext.setAttribute("placeVO", placeVO);
			pageContext.setAttribute("viewList", viewList);
%>
<div id="content">
    <div class="content-wrapper">
        <div class="row">
            <ul id="crumb" class="breadcrumb">
            </ul>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="margin-top: 50px;">
            <form action="<%=request.getContextPath()%>/place/PlaceServlet" method="post" enctype="multipart/form-data">
                <table class="table table-hover table-striped">
                    <tr>
                        <td>景點名稱</td>
                        <td><input id="updateName" name="updateName" type="text" class="form-control " placeholder="${placeVO.name}"></td>
                    </tr>
                    <tr>
                        <td>景點描述</td>
                        <td><textarea id="updateDesc" name="updateDesc" rows="4" class="form-control" cols="20" placeholder="${placeVO.pla_desc}"></textarea></td>
                    </tr>
                    <tr>
                        <td>景點地址</td>
                        <td><input id="updateAddr" name="updateAddr" type="text" class="form-control " placeholder="${placeVO.addr}"></td>
                    </tr>
                    <tr>
                        <td>景點圖片</td>
                        <td><c:forEach var="view" items="${viewList}">
                                <div class="col-xs-3 col-md-3 btn-like-wrapper">
                                    <div class="caption">
                                        <img style="width: 100%; height: 200px; margin: 20px;" src="<%=request.getContextPath()%>/image/ShowImage?view_no=${view}">
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="form-group">
                                <input id="updateImg" name="updateImg" type="file" multiple class="file-loading">
                            </div></td>
                    </tr>
                </table>
                <input type="hidden" name="pla_no" value="${placeVO.pla_no}">
                <input type="hidden" name="action" value="UPDATE_PLACE">
                <input class="btn btn-success" type="submit" value="確認">
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
        $("document").ready(function() {
            $("#updateImg").fileinput({
                maxFileCount : 50,
                showUpload : false,
                allowedFileTypes : [ "image" ],
                language : 'zh-TW',
                theme : "fa",
                uploadAsync : true,
                browseOnZoneClick : true
                 });
        });
    </script>
    <%@include file="/Back_end/pages/backFooter.file"%>