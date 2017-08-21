<%@page import="java.util.ArrayList"%>
<%@page import="javax.script.ScriptContext"%>
<%@page import="com.place.model.PlaceVO"%>
<%@page import="java.util.List"%>
<%@page import="com.place.model.PlaceService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="/Front_end/place/pages/frontHeader.file"%>
<style>
#map {
	height: 800px;
	width: 100%;
}
</style>
<div class="container">
    <div class="row">
        <div  class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <div id="placeImg" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            </div>
            <hr>
            <div id="comImg" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            </div>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <div id="map"></div>
        </div>
    </div>
</div>

<script>
    var map;
    var initialLocation;

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
        var map = new google.maps.Map(document.getElementById("map"), {
        minZoom : 15,
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
        var i = 0;
        var south = [];
        var west = [];
        map.addListener('bounds_changed', function() {
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
                loadMark(range.south, range.west, range.north, range.east, map);
            }
            i++;
        })
    }

    function succ(event) {
        console.log("Get location succ");
        initialLocation = {
        lat : event.coords.latitude,
        lng : event.coords.longitude
        };
        map = new google.maps.Map(document.getElementById("map"), {
        minZoom : 15,
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
        map.addListener('bounds_changed', function() {
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
                loadMark(range.south, range.west, range.north, range.east, map);
            }
            i++;
        })
    }

    function loadMark(south, west, north, east, map) {
        var originView = [];
        $.ajax({
            url : '/BA102G2/place/PlaceServlet',
            type : "post",
            data : {
                action : 'MAP_CHANGE',
                south : south - 0.003,
                west : west - 0.003,
                north : north - 0.003,
                east : east - 0.003
                },
            timeout : 5000,
            error : function(xhr, ajaxOptions, thrownError) {
                console.log("xhr.status " + xhr.status);
                console.log("thrownError " + thrownError);
            },
            success : function(response) {
                var placeArray = JSON.parse(response).placeArray;
                var comArray = JSON.parse(response).comArray;
                showPlace(placeArray);
                showCom(comArray);
            }
        })
    }
    
    var placeMarkers = [];
    function showPlace(placeArray){
        var position;
        var marker;
        placeMarkers=[];
        for (var i = 0; i < placeArray.length; i++) {
            position = {
            lat : parseFloat(placeArray[i].lat),
            lng : parseFloat(placeArray[i].lng)
            };
            marker = new google.maps.Marker({
            position : position,
            title : placeArray[i].name,
            label : placeArray[i].name,
            map : map
            });
            placeMarkers.push(marker);
        }
        var imgDiv = "";
        for (var i = 0; i < placeArray.length; i++) {
            var a = "<a target='_blank' class='thumbnail thumbnail-service mod-shadow img-label animated fadeInUp' style='animation-duration:" + i * 0.4 + "s' href='/BA102G2/Front_end/place/onePlace.jsp?pla_no=" + placeArray[i].pla_no + "'>";
            var img = "<img style='width:100%; height:200px;' src='<%=request.getContextPath()%>/image/ShowImage?view_no=" + placeArray[i].view_no + "'>"
            var h5 = "<h5>" + placeArray[i].name + "</h5>";
            var caption = "<div class='col-xs-3 col-md-6 btn-like-wrapper'><div class='caption'>" + a + img + h5 + "</a></div></div>"
            imgDiv = imgDiv + caption;
        }
        $("#placeImg").html(imgDiv);
    }
    
    var comMarkers = [];
    function showCom(comArray){
        var position;
        var marker;
        comMarkers=[];
        for (var i = 0; i < comArray.length; i++) {
            position = {
            lat : parseFloat(comArray[i].lat),
            lng : parseFloat(comArray[i].lng)
            };
            marker = new google.maps.Marker({
            position : position,
            title : comArray[i].name,
            label : comArray[i].name,
            map : map
            });
            comMarkers.push(marker);
        }
        var imgDiv = "";
        for (var i = 0; i < comArray.length; i++) {
            var a = "<a target='_blank' class='thumbnail thumbnail-service mod-shadow img-label animated fadeInUp' style='animation-duration:" + i * 0.4 + "s' href='<%=request.getContextPath()%>/Front_end/com/listOneCom.jsp?com_no="+ comArray[i].com_no + "'>";
            var img = "<img style='width:100%; height:200px;' src='<%=request.getContextPath()%>/image/ShowImage?com_no=" + comArray[i].com_no + "'>"
            var h5 = "<h5>" + comArray[i].name + "</h5>";
            var caption = "<div class='col-xs-3 col-md-6 btn-like-wrapper'><div class='caption'>" + a + img + h5 + "</a></div></div>"
            imgDiv = imgDiv + caption;
        }
        $("#comImg").html(imgDiv);
    }
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBzbntmAuGW16US8FK_QIoDNXOPlspRjNw&callback=initMap"></script>


<%@include file="/Front_end/place/pages/frontFooter.file"%>