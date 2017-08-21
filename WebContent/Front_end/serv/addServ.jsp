<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.serv.model.*"%>
<%@ page import="java.util.*"%>

<%
Map<String,String> errorMsgs = (HashMap) request.getAttribute("errorMsgs");
Object memVO = session.getAttribute("memVO");     
ServVO servVO = (ServVO) request.getAttribute("servVO");
%>
<%@ include file="/Front_end/com/page/company_header.file"%>
<title></title>
<div class="container">
    <div class="col-md-offset-1">
        <ul class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/Front_end/login/homepage(loginC).jsp">首頁</a></li>
            <li class="active">修改資料</li>        
        </ul>
    </div>
</div>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
   
    <div class="container">
        <div class="row">
<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
            <div class="col-md-offset-1 col-md-2">
                <ul class="list-group">
                    <a href="<%=request.getContextPath()%>/Front_end/com/updatecompany.jsp" class="list-group-item menua">編輯廠商資料</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/com/updatePwd.jsp" class="list-group-item menua">密碼修改</a><br>
                    <a href="#" class="list-group-item menua">預約紀錄查詢</a><br>
                    <a href="#" class="list-group-item menua">報價紀錄查詢</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTemps.jsp" class="list-group-item menua">作品挑選管理</a><br>
                    <a href="#" class="list-group-item menua">行事曆</a><br>
                    <a href="<%=request.getContextPath()%>/Front_end/Works/ListAllWorks.jsp" class="list-group-item menua">作品管理</a><br>
                  	<a href="#" class="list-group-item menua">瀏覽服務</a><br>
                  	<a href="<%=request.getContextPath()%>/Front_end/serv/addServ.jsp" class="list-group-item menua active">新增服務</a><br>
                </ul>

				
                <a href="<%=request.getContextPath()%>/Front_end/com/listOneCom.jsp" name="action" value="insert" class="btn btn-block btn-default">查看廠商資料</a>
            </div>
<style type="text/css">
			#big{
				font-size:19px;
				font-weight:600;
			}
			h1{font-weight:600;}
		</style>

<div class="container">
<div class="col-md-offset-1 col-md-7" id="big">
<h3>新增廠商服務:</h3>


<%--錯誤處理 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/serv/serv.do" name="form1">

	<div class="form-group">
		<span>服務類型:<br></span>
		
		<select style="width: 223px;" name="stype_no">
		 <option value="0001">拍婚紗
		 <option value="0002">婚設婚錄
		  <option value="0003">新娘秘書
		</select>
	</div>

	<div class="form-group">
		<td>廠商編號:</td>
		<td>${comVO.com_no}</td>
	</div>
	
	
<div class="form-group">
		<span>訂金:<font color='red'>${errorMsgs.get("deposit")}</font><br></span>
		<input type="TEXT" name="deposit" 
			value="<%= (servVO==null)? "0" : servVO.getDeposit()%>" />
		</div>
			
	<div class="form-group">
		<span>價錢:<font color='red'>${errorMsgs.get("price")}</font><br></span>
		<input type="TEXT" name="price"
			value="<%= (servVO==null)? "0" : servVO.getPrice()%>" />
	</div>

	<div class="form-group">
		<span>服務標題:<font color='red'>${errorMsgs.get("title")}</font><br></span>
		<input type="TEXT" name="title" 
			value="<%= (servVO==null)? "標題" : servVO.getTitle()%>" />
	</div>

	<div class="form-group">
		<span>服務介紹:<font color='red'>${errorMsgs.get("content")}</font><br></span>
		<input type="TEXT" name="content"  
			value="<%= (servVO==null)? "介紹內容" : servVO.getContent()%>" />
	</div>

	
	<div class="form-group">
		<span>服務狀態:<br></span>
	
		<select style="width: 223px;" name="status">
		 <option value="正常">正常
		 <option value="下架">下架
		</select>
	
	</div>

	


<br>
<input type="hidden" name="com_no" value="${comVO.com_no}">
<input type="hidden" name="action" value="insert">
<input type="submit" class="btn btn-info" value="送出新增"></FORM>
</div></div>
<%@ include file="/Front_end/mem/page/register_footer.file"%>