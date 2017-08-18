<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.works.model.*"%>
<%@ page import="com.com.model.*"%>
<%@ page import="com.serv.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ComService comSvc = new ComService();
	ComVO comVO = comSvc.getOneCom(request.getParameter("com_no"));
	pageContext.setAttribute("comVO", comVO);

	WorksService worksSvc = new WorksService();
	List<WorksVO> worksList = worksSvc.getAllByComNo(request.getParameter("com_no"));
	pageContext.setAttribute("worksList", worksList);

	ServService servSvc = new ServService();
	List<ServVO> servList = servSvc.getAll();
	pageContext.setAttribute("servList", servList);
	
	Map<String,String> map =(LinkedHashMap) request.getAttribute("map");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body onload="connect('${memVO != null? memVO.mem_no : comVO.com_no}');" onunload="disconnect();">
	
	<!--聯絡我們 -->
	<form method="post" action="<%=request.getContextPath()%>/ContactUs">
	<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title text-center">聯絡我們</h4>
        </div>
        <div class="modal-body">
         	 姓名:<font color='red'>${errorMsgs.name}</font><input type="text" class="form-control" name="name" value="${(map.name==null)?'':map.name}">
         	Email:<font color='red'>${errorMsgs.email}</font><input type="email" class="form-control" name="email" value="${(map.email==null)?'':map.email}"> 
         	要說的話:<font color='red'>${errorMsgs.messagesArea}</font><br>
         	<textarea class="message-area" name="messagesArea" style="height:150px;width:100%;">${(map.messagesArea==null)?'':map.messagesArea}</textarea>
        </div>
        <div class="modal-footer">
          <input type="submit" class="btn btn-default" value="送出" onClick="validateForm(this.form)">
          <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="取消">
        </div>
      </div>
      
    </div>
  </div>
  </form>
	<!--聯絡我們 -->




	<%@ include file="page/before.file"%>


	<!--banner -->
	<c:forEach var="worksVO" items="${worksList}" begin="1" end="1">
		<div class="fade-carousel">
			<div class="home-banner fade-carousel" style="overflow: hidden;">
				<div class="item slides">
					<div class="slide-1 bg-cover lazy"
						style="background-image:url('<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}');"></div>

				</div>
			</div>
		</div>
		<br>
	</c:forEach>
	<!--banner -->

	<!--廠商大頭照-->
	<div class="com_head container">
		<img class="com_logo img-circle center-block"
			src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}"
			alt="She Said Yes">
	</div>
	<!--廠商大頭照-->

	<!--廠商名稱-->
	<div class="text-center">
		<h1>${comVO.name}</h1>
		<a href="#"><i class="fa fa-heart" style="color:deeppink">加入最愛</i></a>
	</div>
	<!--廠商名稱-->
	
	<!--麵包削-->
	<div class="catalog hidden-xs">
		<ul class="list-inline">
			<li><a href="#info" title="聯絡資訊"> 聯絡資訊 </a></li>
			<li><a href="#works" title="作品"> 作品 </a></li>
			<li><a href="#service" title="方案"> 方案 </a></li>
			<li><a href="#introduction" title="關於我"> 關於我 </a></li>
		</ul>
	</div>
	<!--麵包削-->
	
	<!--廠商資料-->
	<div class="container" id="info">
		<div class="col-sm-1"></div>
		<div class="col-sm-8">
			<div class="hidden-xs">
				<table class="table">
					<tbody>
						<tr>
							<th>店休日</th>
							<td>每周休六日</td>
						</tr>

						<tr>
							<th>電話</th>
							<td>${comVO.phone}</td>
						</tr>

						<tr>
							<th>地址</th>
							<td>${comVO.loc}</td>
						</tr>

						<tr>
							<th>信箱</th>
							<td>${comVO.id}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--廠商資料-->
		
		<!--預約+即時訊息按鈕-->
		<div class="col-sm-3">
			<p class="text-center">
				<a class="btn btn-reservation btn-lg" id="open_chat">立即連絡我們 <i class="fa fa-comment"></i></a>
				<br><br>
				<a class="btn btn-reservation btn-lg" href="">預約 </a>
			</p>
		</div>
	</div>
	<!--預約+即時訊息按鈕-->
	
	
	<!--立即聯絡我們-->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="chat_box panel panel" id="chatbox">
					<div class="panel-heading">
						<button id=close_chat class="chat-header-button pull-right" type="button"><i class="fa fa-times"></i></button>

						<div class="col-md-3"><img class="chat-header-logo img-circle" src="<%=request.getContextPath()%>/ShowPictureServletDAO?com_no=${comVO.com_no}"></div>
						<div class="col-md-8 chat-header-name">${comVO.name}</div>
<!-- 						<div id="statusOutput"></div> -->
					</div>
					<div class="panel-body">
					<ul id="textArea"></ul>
					</div>
					<div class="panel-footer">							
							<input id="message" class="col-md-9 text-field" type="text" autofocus="autofocus" onkeydown="if (event.keyCode == 13) sendMessage('${memVO != null? memVO.mem_no : comVO.com_no}',	
																																							  '${memVO != null? memVO.name : comVO.name}');" />	
							<input type="submit" id="sendMessage" class="col-md-3 button sendMessage_btn" value="送出" 
							onclick="sendMessage('${memVO != null? memVO.mem_no : comVO.com_no}',
												 '${memVO != null? memVO.name : comVO.name}')" />	
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--立即聯絡我們-->
	
	<%@ include file="page/message_script.file"%>
	
	
	

	<!--廠商相簿-->
	<div class="text-center" id="works">
		<span>
			<h1>作品</h1>
		</span>
	</div>
	<div class="container">
		<div class="row">
			<c:forEach var="worksVO" items="${worksList}" begin="2" end="10">
				<div class="col-xs-12 col-sm-4">
					<ul class="works_box">
						<li class="list-unstyled">
							<div class="works_a thumbnail thumbnail thumbnail-service mod-shadow img-label">
								<a data-lightbox="lightbox" href="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}">
									<img class="works_image img-thumbnail" src="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}">
								</a>	
								<div class="overlay">
									<div class="works_text">${worksVO.works_desc}</div>
								</div>
							</div>
						</li>
					</ul>
				</div>				
			</c:forEach>
			</div>
		</div>	
		<div class="container">
			<div class="row">
			<div id="more_works" style="display: none;">
			<c:forEach var="worksVO" items="${worksList}" begin="11">
				<div class="col-xs-12 col-sm-4">
					<ul class="works_box">
						<li class="list-unstyled">
							<div class="works_a thumbnail thumbnail thumbnail-service mod-shadow img-label">
								<a data-lightbox="lightbox">
									<img class="works_image img-thumbnail" src="<%=request.getContextPath()%>/ShowPictureServletDAO?works_no=${worksVO.works_no}">
								</a>
								<div class="overlay">
									<div class="works_text">${worksVO.works_desc}</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</c:forEach>
			</div>
		</div>
	</div>


	<div class="container text-center">
		<div class="row">
			<div class="col-xs-12 col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
				<a class="btn btn-info btn-lg" onclick="change(1)" id="more_works_btn"> 看更多作品
						<i class="fa fa-angle-double-right" aria-hidden="true"></i>
				</a>

			</div>
			<div class="col-xs-12 col-sm-3"></div>
		</div>
	</div>
	<!--廠商相簿-->

	<!--廠商方案-->
	<div class="text-center" id="service">
		<span>
			<h1>方案</h1>
		</span>
	</div>

	<div class="container">
		<div class="row">

			<c:forEach var="servVO" items="${servList}" begin="1" end="4">
			<c:if test="${servVO.com_no==comVO.com_no}">
				<div class="service col-xs-12 col-sm-3">
					<ul class="service_box">
						<li class="service_title"><div>${servVO.title}</div></li>
						<div class="text"><li>${servVO.content}</li></div>
						<li class="cost"><div>價格<b class="price text-pink" >${servVO.price}</b>元</div>
						</li>
					</ul>
				</div>
			</c:if>	
			</c:forEach>
			
			<div id="more_services" style="display: none;">
			<c:forEach var="servVO" items="${servList}" begin="5">
			<c:if test="${servVO.com_no==comVO.com_no}">
				<div class="service col-xs-12 col-sm-3">
					<ul class="service_box">
						<li class="service_title"><div>${servVO.title}</div></li>
						<div class="text"><li>${servVO.content}</li></div>
						<li class="cost"><div>價格<b class="price text-pink" >${servVO.price}</b>元</div>
						</li>
					</ul>
				</div>
			</c:if>		
			</c:forEach>
			</div>

		</div>
	</div>
	
	<div class="container text-center">
		<div class="row">
			<div class="col-xs-12 col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
				<a class="btn btn-info btn-lg" onclick="change(2)" id="more_services_btn"> 看更多方案
						<i class="fa fa-angle-double-right" aria-hidden="true"></i>
				</a>
				
			</div>
			<div class="col-xs-12 col-sm-3"></div>
		</div>
	</div>
	
	</div>
	<!--廠商方案-->
	

	<!--廠商影片-->
	<!--廠商影片-->

	<!--廠商自介-->
	<div class="text-center" id="introduction">
		<span>
			<h1>關於我</h1>
		</span>
	</div>
	<div class="container">
		<div class="row com_intro">

			<div class="col-xs-12 col-sm-4"></div>
			<div class="col-xs-12 col-sm-4">${comVO.com_desc}</div>
			<div class="col-xs-12 col-sm-4"></div>
		</div>
	</div>

	<!--廠商自介-->


	<%@ include file="page/after.file"%>


</body>
</html>