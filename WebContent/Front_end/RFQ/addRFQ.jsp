<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
/* input { */
/* 	width: 300px; */
/* } */
</style>
<%@ include file="page/header.file"%>

<div class="container">
	<div class="panel panel-default col-md-offset-2 col-md-8">
		<div class="panel-heading">
			<h1>徵求店家報價</h1>
		</div>
		<div class="panel-body">
		       <div class="form col-md-offset-1 col-md-10">
		           <form method="post" action="<%= request.getContextPath() %>/rfq/rfq.do">
		               <div class="form-group">
		                   <label class="checkbox-inline">
		                     <input type="checkbox" name="type" value="0" id="0001" checked="true" onchange="toggle(this)">拍婚紗
		                   </label>
		                   <label class="checkbox-inline">
		                     <input type="checkbox" name="type" value="1" id="0002" onchange="toggle(this)">婚攝婚錄
		                   </label>
		                   <label class="checkbox-inline">
		                     <input type="checkbox" name="type" value="2" id="0003" onchange="toggle(this)">新娘秘書
		                   </label>
		               </div>
		               
		           <div id="form1">
		               <div class="form-group">
		                   <label><h3 style="color:#f14195">為您的拍婚紗服務填寫客製化需求</h3></label>
		                    <input type="hidden" name="stype_no" value="0001">
		                    </div>
		                    <div class="form-group">
		                        <label>需求日期</label>
		                        <input type="date" class="form-control" name="ser_date" 
		                        value=<%= new Timestamp(System.currentTimeMillis()) %>>
		                    </div>
		                    <div class="form-group">
		                        <label>需求時間</label>
		                        <select class="form-control" name="time">
		                        <% for(int i = 0; i < 10; i++){ %>
		                        	<option value="<%= i+9 %>:00:00"><%= i+9 %>:00</option>
		                        <% } %>
		                        </select>
		                    </div>
		                    <div class="form-group">
		                        <label>地點</label>
		                        <select name="location" class="form-control">
		                            <option value="台北市">台北市</option>
		                            <option value="桃園市">桃園市</option>
		                            <option value="台中市">台中市</option>
		                            <option value="台南市">台南市</option>
		                            <option value="高雄市">高雄市</option>
		                        </select>
		                    </div>
		                    <div class="from-group">
		                        <label>需求內容</label>
		                        <textarea rows="10" class="form-control" name="content">
1. 風格:時尚韓風/中式龍鳳掛/浪漫甜美/自然清新/不拘
2. 服務內容:
3. 備註:
		            </textarea>
		                    <br><hr>
		                    </div>
		                    </div>
		                    <div id="form2" style="display: none">
		               <div class="form-group">
		                   <label><h3 style="color:#f14195">為您的婚攝/婚錄服務填寫客製化需求...</h3></label>
		                        <input type="hidden" name="stype_no" value="0002">
		                    </div>
		                    <div class="form-group">
		                        <label>需求日期</label>
		                        <input type="date" class="form-control" value=<%= new Timestamp(System.currentTimeMillis()) %>
		                            name="ser_date">
		                    </div>
		                    <div class="form-group">
		                        <label>需求時間</label>
		                        <select class="form-control" name="time">
		                        <% for(int i = 0; i < 10; i++){ %>
		                        	<option value="<%= i+9 %>:00:00"><%= i+9 %>:00</option>
		                        <% } %>
		                        </select>
		                    </div>
		                    <div class="form-group">
		                        <label>地點</label>
		                        <select name="location" class="form-control">
		                            <option value="台北市">台北市</option>
		                            <option value="桃園市">桃園市</option>
		                            <option value="台中市">台中市</option>
		                            <option value="台南市">台南市</option>
		                            <option value="高雄市">高雄市</option>
		                        </select>
		                    </div>
		                    <div class="from-group">
		                        <label>需求內容</label>
		                        <textarea rows="10" class="form-control" name="content">
1. 風格:時尚韓風/中式龍鳳掛/浪漫甜美/自然清新/不拘
2. 服務內容:
3. 備註:
		            </textarea>
		                    <br><hr>
		                    </div>
		                    </div>
		                    <div id="form3" style="display: none">
		               <div class="form-group">
		                   <label><h3 style="color:#f14195">為您的新娘秘書服務填寫客製化需求...</h3></label>
		                        <input type="hidden" name="stype_no" value="0003">
		                    </div>
		                    <div class="form-group">
		                        <label>需求日期</label>
		                        <input type="date" class="form-control" value=<%= new Timestamp(System.currentTimeMillis()) %>
		                            name="ser_date">
		                    </div>
		                    <div class="form-group">
		                        <label>需求時間</label>
		                        <select class="form-control" name="time">
		                        <% for(int i = 0; i < 10; i++){ %>
		                        	<option value="<%= i+9 %>:00:00"><%= i+9 %>:00</option>
		                        <% } %>
		                        </select>
		                    </div>
		                    <div class="form-group">
		                        <label>地點</label>
		                        <select name="location" class="form-control">
		                            <option value="台北市">台北市</option>
		                            <option value="桃園市">桃園市</option>
		                            <option value="台中市">台中市</option>
		                            <option value="台南市">台南市</option>
		                            <option value="高雄市">高雄市</option>
		                        </select>
		                    </div>
		                    <div class="from-group">
		                        <label>需求內容</label>
		                        <textarea rows="10" class="form-control" name="content">
1. 風格:時尚韓風/中式龍鳳掛/浪漫甜美/自然清新/不拘
2. 服務內容:
3. 備註:
		            </textarea>
		                    </div>
		                    </div>
		                    <div>
		                        <input type="hidden" name="action" value="add">
		                        <input class="form-control btn btn-danger" type="submit" value="送出需求">
		                    </div>
		                </form>
		            </div>
		</div>
	</div>
</div>
<%@ include file="page/footer.file"%>
</body>
</html>