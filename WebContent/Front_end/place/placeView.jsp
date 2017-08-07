<%@page import="java.util.ArrayList"%>
<%@page import="javax.script.ScriptContext"%>
<%@page import="com.place.model.PlaceVO"%>
<%@page import="java.util.List"%>
<%@page import="com.place.model.PlaceService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="/Front_end/pages/frontHeader.file"%>
<style>
#map {
	height: 800px;
	width: 100%;
}
</style>
<div class="container">
    <div class="row">
        <div id="placeImg" class="col-xs-6 col-sm-6 col-md-6 col-lg-6"></div>
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
        title:'現在位置',
        label : '現在位置',
        map : map
        });
        var i = 0;
        var south=[];
        var west=[];
        map.addListener('bounds_changed', function() {
            var range = map.getBounds().toJSON();
            south.push(range.south);
            west.push(range.west);
            if (Math.abs(south[0] - south[1]) < 0.008 && Math.abs(west[0] - west[1]) < 0.008) {
                south.pop();                    
                west.pop();
            }
            else if(i>1&&(Math.abs(south[0] - south[1]) > 0.008 || Math.abs(west[0] - west[1]) > 0.008)){
                south[0]=south[1];
                west[0]=west[1];
                south.pop();                    
                west.pop();
                loadMark(range.south, range.west, range.north, range.east,map);
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
        title:'現在位置',
        label : '現在位置',
        map : map
        });
        var i = 0;
        var south=[];
        var west=[];
        map.addListener('bounds_changed', function() {
            var range = map.getBounds().toJSON();
            south.push(range.south);
            west.push(range.west);
            if (Math.abs(south[0] - south[1]) < 0.008 && Math.abs(west[0] - west[1]) < 0.008) {
                south.pop();                    
                west.pop();
            }
            else if(i>1&&(Math.abs(south[0] - south[1]) > 0.008 || Math.abs(west[0] - west[1]) > 0.008)){
                south[0]=south[1];
                west[0]=west[1];
                south.pop();                    
                west.pop();
                loadMark(range.south, range.west, range.north, range.east,map);
            }
            i++;
        })
    }

    
    function loadMark(south, west, north, east,map) {
        $.ajax({
        url : '/BA102G2/place/PlaceServlet',
        type : "post",
        data : {
        action : 'AJAX',
        south : south-0.003,
        west : west-0.003,
        north : north-0.003,
        east : east-0.003
        },
        timeout : 5000,
        error : function(xhr, ajaxOptions, thrownError) {
            console.log("xhr.status " + xhr.status);
            console.log("thrownError " + thrownError);
        },
        success : function(response) {
            var placeList=JSON.parse(response).placeList
            var viewnoList = JSON.parse(response).viewnoList;
            var markers=[];
            var position;
            var marker;
            for(var i=0;i<markers.length;i++){
                markers[i].setMap(null);
                markers[i]=null;
                }
            for (var i = 0; i < placeList.length; i++) {
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
                markers.push(marker);
            }
                var imgDiv = "";
                for (var i = 0; i < placeList.length; i++) {
                    var a = "<a target='_blank' class='thumbnail thumbnail-service mod-shadow img-label' href='/BA102G2/Front_end/place/onePlace.jsp?pla_no=" + placeList[i].pla_no + "'>";
                    var img = "<img style='width:100%;' src='/BA102G2/image/ShowImage?view_no=" + viewnoList[i] + "'>"
                    var h5 = "<h5 class='small' style='height: 5px;'>" + placeList[i].name + "</h5>";
                    var caption = "<div class='col-xs-3 col-md-6 btn-like-wrapper'><div class='caption'>" + a + img + h5 + "</a></div></div>"
                    imgDiv = imgDiv + caption;
                }
                $("#placeImg").html(imgDiv);
        }
        })
    }
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBzbntmAuGW16US8FK_QIoDNXOPlspRjNw&callback=initMap"></script>


<%@include file="/Front_end/pages/frontFooter.file"%>