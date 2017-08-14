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
	int nowPage = (request.getParameter("nowPage") == null)? 1: Integer.valueOf(request.getParameter("nowPage"));
	int itemsCount = 9;
	int allCount = placeService.getAllCount();
	int totalPages = allCount / itemsCount + 1;
	List<PlaceVO> placeList = placeService.getPage(nowPage, itemsCount);
	String preLocation = request.getContextPath() + "/Back_end/place";
	pageContext.setAttribute("placeList", placeList);
	pageContext.setAttribute("preLocation", preLocation);
	pageContext.setAttribute("totalPages", totalPages);
	pageContext.setAttribute("nowPage", nowPage);
	pageContext.setAttribute("itemsCount", itemsCount);
%>
<%@include file="/Back_end/pages/backHeader.file"%>
<div id="content">
    <div class="content-wrapper">
        <div class="row">
            <ul id="crumb" class="breadcrumb">
            </ul>
        </div>
    </div>
    <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10" style="margin-top: 50px;">
        <table class="table"  >
            <thead>
                <tr>
                    <th>景點名稱</th>
                    <th>景點地址</th>
                    <th>景點描述</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="tbody">
                <c:forEach var="placeVO" items="${placeList}">
                    <tr>
                        <td>${placeVO.name}</td>
                        <td>${placeVO.addr}</td>
                        <td style="word-break:break-all;width:500px;">${placeVO.pla_desc}</td>
                        <td style="width: 80px;">
                            <a style="width: 100%;" class="btn btn-success" href="${preLocation}/placeUpdate.jsp?pla_no=${placeVO.pla_no}">資料修改</a>
                            <a style="width: 100%;" class="btn btn-danger" href="<%=request.getContextPath()%>/place/PlaceServlet?action=DELETE&&pla_no=${placeVO.pla_no}">刪除景點</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!--//////////////////////////////////////////分頁開始//////////////////////////////////////////////////////////////// -->
        <div class="text-center ">
            <nav aria-label="Page navigation ">
                <ul class="pagination pagination-lg ">
                    <li>
                        <a class="btn btn-info active" href="javascript:change(1,${itemsCount},${totalPages})" data-page="1">1</a>
                    </li>
                    <c:forEach var="i" begin="2" end="5">
                        <li>
                            <a class="btn btn-info" href="javascript:change(${i},${itemsCount},${totalPages})" data-page="${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li>
                        <a class="disabled">...</a>
                    </li>
                    <li>
                        <a class="btn btn-info" href="javascript:change(${totalPages},${itemsCount},${totalPages})">${totalPages}</a>
                    </li>
                </ul>
            </nav>
        </div>
        <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
    </div>
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

    function change(nowPage, itemsCount, totalPages) {
        $.ajax({
        url : "/BA102G2/place/PlaceServlet",
        type : "post",
        data : {
        action : "CHANGE_AJAX",
        nowPage : nowPage,
        itemsCount : itemsCount,
        },
        error : function(xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        },
        success : function(response) {
            var placeList = JSON.parse(response);
            pageChange(itemsCount, totalPages, nowPage);
            tableChange(placeList);
        }
        });
    }

    function pageChange(itemsCount, totalPages, nowPage) {
        var preHref = "javascript:change(" + 1 + "," + itemsCount + "," + totalPages + ")";
        var afterHref = "javascript:change(" + totalPages + "," + itemsCount + "," + totalPages + ")";
        var nothing = "<li><a class='disabled'>...</a></li>";
        if (totalPages <= 5) {
            var page = "";
            for (var i = 0; i < totalPages; i++) {
                var href = "javascript:change(" + (i + 1) + "," + itemsCount + "," + totalPages + ")";
                var active = "";
                if ((i + 1) == nowPage) {
                    active = "active";
                }
                page = page + "<li><a class='btn btn-info " + active + "' href='" + href + "'" + "data-page='" + (i + 1) + "'" + ">" + (i + 1) + "</a></li>"
            }
        }
        else {
            if (nowPage < 5) {
                var page = "";
                for (var i = 0; i < 5; i++) {
                    var href = "javascript:change(" + (i + 1) + "," + itemsCount + "," + totalPages + ")";
                    var active = "";
                    if ((i + 1) == nowPage) {
                        active = "active";
                    }
                    page = page + "<li><a class='btn btn-info " + active + "' href='" + href + "'" + "data-page='" + (i + 1) + "'" + ">" + (i + 1) + "</a></li>";
                }
                page = page + nothing;
                page = page + "<li><a class='btn btn-info' href='" + afterHref + "''" + "data-page='" + (totalPages) + "'" + ">" + (totalPages) + "</a></li>";
            }
            else if (totalPages - nowPage < 5) {
                var page = "<li><a class='btn btn-info' href='" + preHref + "'" + "data-page='" + (1) + "'" + ">" + (1) + "</a></li>";
                page = page + "<li><a class='disabled'>...</a></li>";
                for (var i = totalPages - 5; i <= totalPages; i++) {
                    var active = "";
                    var href = "javascript:change(" + i + "," + itemsCount + "," + totalPages + ")";
                    if (i == nowPage) {
                        active = "active";
                    }
                    page = page + "<li><a class='btn btn-info "+ active+"' href='" + href + "'" + "data-page='" + i + "'" + ">" + i + "</a></li>";
                }
            }
            else {
                var page = "<li><a class='btn btn-info' href='"+preHref+"'"+"data-page='"+1+"'"+">" + 1 + "</a></li>";
                page = page + nothing;
                for (var i = nowPage - 2; i <= nowPage + 2; i++) {
                    var active = "";
                    var href = "javascript:change(" + i + "," + itemsCount + "," + totalPages + ")";
                    if (i == nowPage) {
                        active = "active";
                    }
                    page = page + "<li><a class='btn btn-info "+ active+"' href='" + href + "'" + "data-page='" + i + "'" + ">" + i + "</a></li>"
                }
                page = page + nothing;
                page = page + "<li><a class='btn btn-info' href='"+afterHref+"'"+"data-page='"+totalPages+"'"+">" + totalPages + "</a></li>";
            }
        }
        $("ul.pagination-lg").html(page);
    }

    function tableChange(placeList) {
        var tbody = "";
        for (var i = 0; i < placeList.length; i++) {
            tbody = tbody + "<tr>" + 
            "<td>"+ placeList[i].name + "</td>" + 
            "<td>" + placeList[i].addr + "</td>" + 
            "<td style='word-break:break-all;width:500px;'>"+placeList[i].pla_desc+"</td>"+
            "<td style='width: 80px;'><a style='width: 100%;' class='btn btn-success' href='/BA102G2/Back_end/place/placeUpdate.jsp?pla_no=" + placeList[i].pla_no + "'>資料修改</a>" + 
            "<a style='width: 100%;' class='btn btn-danger' href='/BA102G2/place/PlaceServlet?action=DELETE&&pla_no=" + placeList[i].pla_no + "'>刪除景點</a></td>"+
            "</tr>";
        }
        $("#tbody").html(tbody);
    }
    $("document").ready(function() {
        
        $("#addImg").fileinput({
        maxFileCount : 50,
        showUpload:false,
        allowedFileTypes : [ "image" ],
        language : 'zh-TW',
        theme : "fa",
        uploadAsync : true,
        browseOnZoneClick : true
        });
    
    });
    
</script>
<%@include file="/Back_end/pages/backFooter.file"%>