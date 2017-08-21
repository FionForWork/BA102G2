<%@page import="com.placeview.model.PlaceViewVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.commons.collections.map.HashedMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.script.ScriptContext"%>
<%@page import="com.place.model.PlaceVO"%>
<%@page import="java.util.List"%>
<%@page import="com.place.model.PlaceService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String south = (request.getParameter("south") == null) ? "24.96" : request.getParameter("south");
    String west = (request.getParameter("west") == null) ? "121.18" : request.getParameter("west");
    String north = (request.getParameter("north") == null) ? "24.90" : request.getParameter("north");
    String east = (request.getParameter("east") == null) ? "121.20" : request.getParameter("east");
    int nowPage = (request.getParameter("nowPage") == null) ? 1 : Integer.parseInt((request.getParameter("nowPage")));
    int itemsCount = 4;
    PlaceService placeService = new PlaceService();
    List<PlaceVO> originList = placeService.getRange(south, west, north, east);
    List<HashMap<String,String>>placeList=new ArrayList<HashMap<String,String>>();
    for(PlaceVO placeVO:originList){
        Iterator<PlaceViewVO> iterator = placeVO.getPlaceViewSet().iterator();
        PlaceViewVO placeViewVO=null;
        HashMap<String, String> map=new HashMap<String, String>();
        if(iterator.hasNext()){
            placeViewVO = (PlaceViewVO) iterator.next();
            map.put("pla_no", placeVO.getPla_no());
            map.put("name", placeVO.getName());
            map.put("lat", placeVO.getLat());
            map.put("lng", placeVO.getLng());
            placeList.add(map);
            map.put("view_no", placeViewVO.getView_no());
        }
    }
    int allCount = placeList.size();
    int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
    int start = (nowPage - 1) * itemsCount;
    int end = start + itemsCount - 1;
    pageContext.setAttribute("nowPage", nowPage);
    pageContext.setAttribute("totalPages", totalPages);
    pageContext.setAttribute("placeList", placeList);
    pageContext.setAttribute("start", start);
    pageContext.setAttribute("end", end);
%>

<%@include file="/Front_end/place/pages/frontHeader.file"%>
<style>
#map {
    height:600px;
	width: 100%;
}
</style>
<div class="container">
    <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <div id="placeImg" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <c:forEach var="placeVO" items="${placeList}" begin="${start}" end="${end}">
                    <div class="col-xs-6 col-md-6 btn-like-wrapper">
                        <a target="_blank" class="thumbnail thumbnail-service mod-shadow img-label animated fadeInUp" style="animation-duration: <%=Math.random() * 3%>s;" href="<%=request.getContextPath()%>/Front_end/place/onePlace.jsp?pla_no=${placeVO.pla_no}">
                            <div class="caption">
                                <img style="width: 100%; height: 200px;" src="<%=request.getContextPath()%>/image/ShowImage?view_no=${placeVO.view_no}">
                                <div id="name">
                                    <h5>${placeVO.name}</h5>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
                <!--//////////////////////////////////////////分頁開始//////////////////////////////////////////////////////////////// -->
                <div class="text-center">
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
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <div id="map"></div>
        </div>
    </div>
</div>

<script>
    var map;
    var initialLocation;
    var i = 0;
    var south = [];
    var west = [];
    function initMap() {
        var g = window.navigator.geolocation;
        g.getCurrentPosition(succ, fail);
    }

    function fail(event) {
        console.log("Get location fail");
        var position = {
        lat : 24.9694,
        lng : 121.1925
        };
        map = new google.maps.Map(document.getElementById("map"), {
        minZoom : 10,
        zoom : 15,
        center : position
        });
        console.log(map);
        var marker = new google.maps.Marker({
        position : position,
        title : '現在位置',
        label : '現在位置',
        map : map
        });
        map.addListener('bounds_changed',boundsChanged);
    }

    function succ(event) {
        console.log("Get location succ");
        initialLocation = {
        lat : event.coords.latitude,
        lng : event.coords.longitude
        };
        map = new google.maps.Map(document.getElementById("map"), {
        minZoom : 10,
        zoom : 15,
        center : initialLocation
        });
        var marker = new google.maps.Marker({
        position : initialLocation,
        title : '現在位置',
        label : '現在位置',
        map : map
        });
        var i = 0;
        var south = [];
        var west = [];
        map.addListener('bounds_changed',boundsChanged);
    }

    function boundsChanged() {
        var range = map.getBounds().toJSON();
        south.push(range.south);
        west.push(range.west);
        if (Math.abs(south[0] - south[1]) < 0.008 && Math.abs(west[0] - west[1]) < 0.008) {
            south.pop();
            west.pop();
        }
        else if (i > 1 && (Math.abs(south[0] - south[1]) > 0.008 || Math.abs(west[0] - west[1]) > 0.008)) {
            south[0] = south[1];
            west[0] = west[1];
            south.pop();
            west.pop();
            loadMark(range.south, range.west, range.north, range.east);
        }
        i++;
    }
    
    function loadMark(south, west, north, east) {
        console.log("Changed");
        $("#placeImg").load("<%=request.getContextPath()%>/Front_end/place/placeView.jsp #placeImg",{
            south:south,
            west:west,
            north:north,
            east:east,
            nowPage:'${nowPage}'
        });
        var placeArray=<%=placeList%>;
        showPlace(south, west, north, east);
    }
    
    var placeMarkers = [];
    function showPlace(south, west, north, east){
        $.ajax({
            url:"/BA102G2/place/PlaceServlet",
            type : "post",
            data:{
                action:"MAP_CHANGE",
                type:"place",
                south:south,
                west:west,
                north:north,
                east:east,
                nowPage:<%=nowPage%>
            }, 
            error : function(xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            },
            success : function(response) {
                var placeList=JSON.parse(response);
                var position;
                var marker;
                comMarkers=[];
                for(var i=0;i<placeList.length;i++){
                    position = {
                    lat : parseFloat(placeList[i].lat),
                    lng : parseFloat(placeList[i].lng)
                    };
                    marker = new google.maps.Marker({
                    position : position,
                    title : placeList[i].name,
                    label : placeList[i].name,
                    map : map
                    });
                    placeMarkers.push(marker);
                }
            }
        });  
    }
    
    function change(nowPage) {
        $("#placeImg").load("<%=request.getContextPath()%>/Front_end/place/placeView.jsp #placeImg", {
        south : comRange[0],
        west : comRange[1],
        north : comRange[2],
        east : comRange[3],
        nowPage : nowPage
        });
    }
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBzbntmAuGW16US8FK_QIoDNXOPlspRjNw&callback=initMap"></script>


<%@include file="/Front_end/place/pages/frontFooter.file"%>