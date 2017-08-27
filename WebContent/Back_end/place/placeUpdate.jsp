<%@page import="java.util.ArrayList"%>
<%@page import="com.placeview.model.PlaceViewVO"%>
<%@page import="java.util.List"%>
<%@page import="com.placeview.model.PlaceViewService"%>
<%@page import="com.place.model.PlaceVO"%>
<%@page import="com.place.model.PlaceService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String pla_no = request.getParameter("pla_no");
    PlaceService placeService = new PlaceService();
    PlaceVO placeVO = placeService.getOnePlace(pla_no);
    ArrayList<PlaceViewVO> viewList = new ArrayList<PlaceViewVO>();
    if (placeVO.getPlaceViewSet().size() != 0) {
        viewList.addAll(placeVO.getPlaceViewSet());
        pageContext.setAttribute("viewList", viewList);
    }
    String active = "0";
    pageContext.setAttribute("active", active);
    pageContext.setAttribute("placeVO", placeVO);		
%>
<%@include file="/Back_end/pages/backHeader.file"%>
<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <form action="<%=request.getContextPath()%>/place/PlaceServlet" method="post" enctype="multipart/form-data">
        <table class="table table-hover table-striped">
            <tr>
                <td><label>景點名稱</label> <input id="updateName" name="updateName" type="text" class="form-control " placeholder="${placeVO.name}"></td>
            </tr>
            <tr>
                <td><label>景點描述</label> <textarea id="updateDesc" name="updateDesc" rows="4" class="form-control" cols="20" placeholder="${placeVO.pla_desc}"></textarea></td>
            </tr>
            <tr>
                <td><label>景點地址</label> <input id="updateAddr" name="updateAddr" type="text" class="form-control " placeholder="${placeVO.addr}"></td>
            </tr>
            <tr>
                <td id="placeImg">
                    <p><label>景點圖片</label></p> <c:forEach var="view" items="${viewList}">
                        <div class="col-xs-3 col-md-3 btn-like-wrapper">
                            <div class="caption">
                                <img style="width: 100%; height: 200px; margin: 20px;" src="<%=request.getContextPath()%>/image/ShowImage?view_no=${view.view_no}">
                            </div>
                        </div>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td><label>新增圖片</label> <input id="updateImg" name="updateImg" type="file" multiple class="file-loading"></td>
            </tr>
        </table>
        <input type="hidden" name="pla_no" value="${placeVO.pla_no}">
        <input type="hidden" name="action" value="UPDATE_PLACE">
        <input class="btn btn-success" type="submit" value="確認">
        <button class="btn btn-danger" onclick="deletePlaceView()">刪除圖片</button>
    </form>
</div>
<script>
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

    function deletePlaceView() {
        $.ajax({
        url : "<%=request.getContextPath()%>/place/PlaceServlet",
        type : "post",
        data : {
        action : "DELETE_PLACEVIEW",
        pla_no :<%=pla_no%>
        },
        error : function(xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        },
        success : function(response) {
            $("#placeImg").load("placeUpdate.jps #placeImg", {
                pla_no :<%=pla_no%>
            });
        }
        });
    }
</script>
<%@include file="/Back_end/pages/backFooter.file"%>