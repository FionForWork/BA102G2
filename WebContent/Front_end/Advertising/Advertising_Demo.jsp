<%@page import="com.advertising.model.AdvertisingVO"%>
<%@page import="com.advertising.model.AdvertisingService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
  AdvertisingService advSvc1=new AdvertisingService();
List<AdvertisingVO> list1=advSvc1.getAll();
pageContext.setAttribute("list1", list1);
%>

<div>
    <section id="main_ad" class="carousel slide" data-ride="carousel">
        <!-- 活動指示器 -->
        <ol class="carousel-indicators">
            <li data-target="#main_ad" data-slide-to="0" class="active"></li>
            <c:forEach var="advertisingVO" items="${list1}" varStatus="ddt">
            <c:if test="${advertisingVO.status==1 }">
            <li data-target="#main_ad" data-slide-to="${ddt.count }"></li>
            </c:if>
            </c:forEach>
            
        </ol>
        <!-- 輪播項 -->
        <div class="carousel-inner" role="listbox">
            <div class="item active" data-image-lg="<%=request.getContextPath()%>/Front_end/Article/img/banner1.jpg"
                data-image-xs="<%=request.getContextPath()%>/Front_end/Article/img/banner1.jpg" >
                    
                    <div class="banner_hero text-center">
            <h1 class="" style="font-family: 'Hind', sans-serif;font-size:26px; text-shadow: 0px 1px 10px rgba(0, 0, 0, 1);">
                <b class="hello_word">
            妳需要的婚禮廠商，我們幫妳嚴選
            </b>
            </h1>
            <h2 style="font-family: 'Hind';text-shadow: 0px 1px 10px rgba(0, 0, 0, 1);">80,118位新娘找到了命定廠商</h2>
           
        </div>
                </div>
                <c:forEach var="advertisingVO" items="${list1}" varStatus="ddt">
                <c:if test="${advertisingVO.status==1 }">
            <div class="item " data-image-lg="<%=request.getContextPath()%>/ShowPictureServletDAO?adv_no=${advertisingVO.adv_no}"  
            data-image-xs="<%=request.getContextPath()%>/ShowPictureServletDAO?adv_no=${advertisingVO.adv_no}" 
            onclick="location.href='<%=request.getContextPath()%>/Front_end/com_page/company_page.jsp?com_no=${advertisingVO.com_no}';">
            
            
            
              <div class="banner_hero text-center">
            <h1 class="" style="font-family: 'Hind', sans-serif;font-size:26px; text-shadow: 0px 1px 10px rgba(0, 0, 0, 1);">
                <b class="hello_word">
      			${advertisingVO.title}
            </b>
            </h1>
            <h2 style="font-family: 'Hind';text-shadow: 0px 1px 10px rgba(0, 0, 0, 1);">${advertisingVO.text}</h2>
            
            
            
            </div>
            </div>
            </c:if>
            </c:forEach>
        </div>
        <!-- 控制按鈕 -->
        <a class="left carousel-control" href="#main_ad" role="button"
            data-slide="prev"> 
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> 
            <span class="sr-only">Previous</span>
        </a> 
        <a class="right carousel-control" href="#main_ad" role="button" data-slide="next"> 
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </section>
    </div>