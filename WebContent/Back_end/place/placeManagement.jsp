<%@page import="com.place.model.PlaceVO"%>
<%@page import="java.util.List"%>
<%@page import="com.place.model.PlaceService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	PlaceService placeService = new PlaceService();
	int nowPage = (request.getParameter("nowPage") == null)	? 1	: Integer.valueOf(request.getParameter("nowPage"));
	int itemsCount = 4;
	int allCount = placeService.getAllCount();
	int totalPages = allCount / itemsCount + 1;
	List<PlaceVO> placeList = placeService.getPage(nowPage, itemsCount);
	String preLocation = request.getContextPath() + "/Back_end/place";
    String active="0";
	pageContext.setAttribute("placeList", placeList);
	pageContext.setAttribute("preLocation", preLocation);
	pageContext.setAttribute("totalPages", totalPages);
	pageContext.setAttribute("nowPage", nowPage);
	pageContext.setAttribute("itemsCount", itemsCount);
%>
<%@include file="/Back_end/pages/backHeader.file"%>
<div id="content">
    <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" style="margin-top: 50px;">
        <table id="table" class="table">
            <thead>
                <tr>
                    <th>景點名稱</th>
                    <th>景點地址</th>
                    <th>景點描述</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="tbody">
                <c:forEach var="placeVO" items="${placeList}" varStatus="s">
                    <tr class="animated fadeInUp" style="animation-duration: ${s.index*0.4}s;">
                        <td>${placeVO.name}</td>
                        <td>${placeVO.addr}</td>
                        <td style="word-break: break-all; width: 500px;">${placeVO.pla_desc}</td>
                        <td style="width: 80px;">
                            <a style="width: 100%;" class="btn btn-success" href="${preLocation}/placeUpdate.jsp?pla_no=${placeVO.pla_no}">資料修改</a> 
                            <a style="width: 100%;" class="btn btn-danger" href="javascript:deletePlace(${nowPage},${placeVO.pla_no})">刪除景點</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
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
    </div>
    <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
    <div class="col-md-2" style="margin-top: 50px;">
        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#NewPlace">新增景點</button>
        <div class="modal fade" id="NewPlace">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">新增景點</h4>
                    </div>
                    <div class="modal-body">
                        <form id="addForm" action="<%=request.getContextPath()%>/place/PlaceServlet" method="post" enctype="multipart/form-data">
                            <table class="table table-hover table-striped">
                                <tbody>
                                    <tr>
                                        <td>景點名稱</td>
                                        <td><input id="addName" class="form-control " name="addName" type="text" placeholder="必須輸入景點名稱"></td>
                                    </tr>
                                    <tr>
                                        <td>景點地址</td>
                                        <td><input id="addAddr" class="form-control " name="addAddr" type="text" placeholder="必須輸入景點地址"></td>
                                    </tr>
                                    <tr>
                                        <td>景點描述</td>
                                        <td><textarea id="addAddr" class="form-control " name="addDesc" class="form-control" style="width: 300px; height: 100px;" placeholder="必須輸入景點描述"></textarea></td>
                                    </tr>
                                    <tr>
                                        <td>景點圖片</td>
                                        <td>
                                            <div class="form-group">
                                                <input id="addImg" name="addImg" type="file" multiple class="file-loading">
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <input type="hidden" name="action" value="ADD_PLACE">
                            <button class="btn btn-success" onclick="addCheck()">確認</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function addCheck() {
        var flag = true;
        var name = $("#addName").val();
        if (name == "") {
            alert("請輸入景點名稱");
            flag = false;
        }
        var addr = $("#addAddr").val();
        if (addr == "") {
            alert("請輸入景點名稱");
            flag = false;
        }
        var desc = $("#addDesc").val();
        if (desc == "") {
            alert("請輸入景點描述");
            flag = false;
        }
        if (flag) {
            $("#addForm").submit();
        }
    }
    
    function deletePlace(nowPage,pla_no) {
        $.ajax({
            url : "/BA102G2/place/PlaceServlet",
            type : "post",
            data : {
            action : "DELETE",
            pla_no : pla_no
            },
            error : function(xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            },
            success : function(response) {
                change(nowPage);
            }
        });
    }
    
    function change(nowPage){ 
        $(window).scrollTop("0");
        $(window).scrollLeft($(window).scrollLeft());
        $("#table").load("<%=preLocation%>/placeManagement.jsp #table",{"nowPage":nowPage});
        $(".pagination").load("<%=preLocation%>/placeManagement.jsp .pagination", {
            "nowPage" : nowPage
        });
    }

    $("document").ready(function() {
        $("#addImg").fileinput({
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