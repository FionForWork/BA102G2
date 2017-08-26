<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ include file="page/share_header_v2.file"%>
<div class="container">
<br>
<c:if test="${not empty errorMsgs}">
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<div class="alert alert-danger alert-dismissable fade in col-md-offset-3 col-md-6">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>詢價失敗!</strong> ${message}
			</div>
		</c:forEach>
	</ul>
	</font>
</c:if>
	<div class="panel panel-default col-md-offset-2 col-md-8">
		<div class="panel-heading">
			<h1>徵求店家報價</h1>
		</div>
		<div class="panel-body">
		       <div class="form col-md-offset-1 col-md-10">
		           <form method="post" action="<%= request.getContextPath() %>/rfq/rfq.do" id="addRFQForm">
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
		                   <label><h3 style="color:#f14195">為您的拍婚紗服務填寫客製化需求
		                    <input type="radio" name="optradio" onclick="formOne()">
		                   </h3></label>
		                    <input type="hidden" name="stype_no" value="0001">
		                    </div>
		                    <div class="form-group col-md-6">
		                        <label>需求日期</label>
		                        <input id="formOneDate" type="date" class="form-control" name="ser_date" 
		                        value=<%= new Timestamp(System.currentTimeMillis()) %>>
		                    </div>
		                    <div class="form-group col-md-6">
		                        <label>地點</label>
		                        <select id="formOneLocation" name="location" class="form-control">
		                            <option value="台北市">台北市</option>
		                            <option value="新北市">新北市</option>
		                            <option value="桃園市">桃園市</option>
		                            <option value="台中市">台中市</option>
		                            <option value="台南市">台南市</option>
		                            <option value="高雄市">高雄市</option>
		                            <option value="基隆市">基隆市</option>
		                            <option value="新竹市">新竹市</option>
		                            <option value="新竹縣">新竹縣</option>
		                            <option value="嘉義市">嘉義市</option>
		                            <option value="嘉義縣">嘉義縣</option>
		                            <option value="苗栗縣">苗栗縣</option>
		                            <option value="彰化縣">彰化縣</option>
		                            <option value="南投縣">南投縣</option>
		                            <option value="雲林縣">雲林縣</option>
		                            <option value="屏東縣">屏東縣</option>
		                            <option value="宜蘭縣">宜蘭縣</option>
		                            <option value="花蓮縣">花蓮縣</option>
		                            <option value="台東縣">台東縣</option>
		                            <option value="澎湖縣">澎湖縣</option>
		                            <option value="國外">國外</option>
		                        </select>
		                    </div>
		                    <div class="from-group col-md-12">
		                        <label>需求內容</label>
		                        <textarea id="formOneContent" rows="10" class="form-control" name="content">
1. 風格:時尚韓風/中式龍鳳掛/浪漫甜美/自然清新/不拘
2. 服務內容:
3. 備註:
		            </textarea>
		                    <br><hr>
		                    </div>
		                    </div>
		                    <div id="form2" style="display: none">
		               <div class="form-group">
		                   <label><h3 style="color:#f14195">為您的婚攝/婚錄服務填寫客製化需求
		                   <input type="radio" name="optradio" onclick="formTwo()">
		                   </h3></label>
		                        <input type="hidden" name="stype_no" value="0002">
		                    </div>
		                    <div class="form-group col-md-6">
		                        <label>需求日期</label>
		                        <input id="formTwoDate" type="date" class="form-control" value=<%= new Timestamp(System.currentTimeMillis()) %>
		                            name="ser_date">
		                    </div>
		                    <div class="form-group col-md-6">
		                        <label>地點</label>
		                        <select id="formTwoLocation" name="location" class="form-control">
		                             <option value="台北市">台北市</option>
		                            <option value="新北市">新北市</option>
		                            <option value="桃園市">桃園市</option>
		                            <option value="台中市">台中市</option>
		                            <option value="台南市">台南市</option>
		                            <option value="高雄市">高雄市</option>
		                            <option value="基隆市">基隆市</option>
		                            <option value="新竹市">新竹市</option>
		                            <option value="新竹縣">新竹縣</option>
		                            <option value="嘉義市">嘉義市</option>
		                            <option value="嘉義縣">嘉義縣</option>
		                            <option value="苗栗縣">苗栗縣</option>
		                            <option value="彰化縣">彰化縣</option>
		                            <option value="南投縣">南投縣</option>
		                            <option value="雲林縣">雲林縣</option>
		                            <option value="屏東縣">屏東縣</option>
		                            <option value="宜蘭縣">宜蘭縣</option>
		                            <option value="花蓮縣">花蓮縣</option>
		                            <option value="台東縣">台東縣</option>
		                            <option value="澎湖縣">澎湖縣</option>
		                            <option value="國外">國外</option>
		                        </select>
		                    </div>
		                    <div class="from-group col-md-12">
		                        <label>需求內容</label>
		                        <textarea id="formTwoContent" rows="10" class="form-control" name="content">
1. 風格:時尚韓風/中式龍鳳掛/浪漫甜美/自然清新/不拘
2. 服務內容:
3. 備註:
		            </textarea>
		                    <br><hr>
		                    </div>
		                    </div>
		                    <div id="form3" style="display: none">
		               <div class="form-group">
		                   <label><h3 style="color:#f14195">為您的新娘秘書服務填寫客製化需求
		                   <input type="radio" name="optradio"  onclick="formThree()">
		                   </h3></label>
		                        <input type="hidden" name="stype_no" value="0003">
		                    </div>
		                    <div class="form-group col-md-6">
		                        <label>需求日期</label>
		                        <input id="formThreeDate" type="date" class="form-control" value=<%= new Timestamp(System.currentTimeMillis()) %>
		                            name="ser_date">
		                    </div>
		                    <div class="form-group col-md-6">
		                        <label>地點</label>
		                        <select  id="formThreeLocation" name="location" class="form-control">
		                             <option value="台北市">台北市</option>
		                            <option value="新北市">新北市</option>
		                            <option value="桃園市">桃園市</option>
		                            <option value="台中市">台中市</option>
		                            <option value="台南市">台南市</option>
		                            <option value="高雄市">高雄市</option>
		                            <option value="基隆市">基隆市</option>
		                            <option value="新竹市">新竹市</option>
		                            <option value="新竹縣">新竹縣</option>
		                            <option value="嘉義市">嘉義市</option>
		                            <option value="嘉義縣">嘉義縣</option>
		                            <option value="苗栗縣">苗栗縣</option>
		                            <option value="彰化縣">彰化縣</option>
		                            <option value="南投縣">南投縣</option>
		                            <option value="雲林縣">雲林縣</option>
		                            <option value="屏東縣">屏東縣</option>
		                            <option value="宜蘭縣">宜蘭縣</option>
		                            <option value="花蓮縣">花蓮縣</option>
		                            <option value="台東縣">台東縣</option>
		                            <option value="澎湖縣">澎湖縣</option>
		                            <option value="國外">國外</option>
		                        </select>
		                    </div>
		                    <div class="from-group col-md-12">
		                        <label>需求內容</label>
		                        <textarea id="formThreeContent" rows="10" class="form-control" name="content">
1. 風格:時尚韓風/中式龍鳳掛/浪漫甜美/自然清新/不拘
2. 服務內容:
3. 備註:
		            </textarea><br>
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
<script>
	function checkForm(e){
		var type = document.getElementsByName("type");
		if( type[0].checked===false && type[1].checked===false && type[2].checked===false){
		   window.alert("請勾選想詢價的服務類型再送出表單，謝謝!");
		   e?e.preventDefault() : event.returnValue = false;
		   return;   
		}	
	}
	window.onload = function (){
	  //註冊表單被送出時檢驗資料
	  document.getElementById("addRFQForm").onsubmit = checkForm;
	};
	
	function formOne(){
		$('#formOneDate').val("2017-09-14");
		$('#formOneLocation').val("桃園市");
		$('#formOneContent').html("1. 風格:時尚韓風/中式龍鳳掛/浪漫甜美/自然清新/不拘\n2. 服務內容:\n3. 備註:123");
	}
	function formTwo(){
		$('#formTwoDate').val("2017-09-14");
		$('#formTwoLocation').val("桃園市");
		$('#formTwoContent').html("1. 風格:時尚韓風/中式龍鳳掛/浪漫甜美/自然清新/不拘\n2. 服務內容:\n3. 備註:123");
	}
	function formThree(){
		$('#formThreeDate').val("2017-09-14");
		$('#formThreeLocation').val("桃園市");
		$('#formThreeContent').html("1. 風格:時尚韓風/中式龍鳳掛/浪漫甜美/自然清新/不拘\n2. 服務內容:\n3. 備註:123");
	}
</script>