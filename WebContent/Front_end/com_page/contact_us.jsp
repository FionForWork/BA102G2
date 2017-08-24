<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%Map<String,String> contact_us_map =(LinkedHashMap) request.getAttribute("contact_us_map"); %>
<body>
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
         	 姓名:<font color='red'>${errorMsgs.name}</font><input type="text" class="form-control" name="name" value="${(contact_us_map.name==null)?'':contact_us_map.name}">
         	Email:<font color='red'>${errorMsgs.email}</font><input type="email" class="form-control" name="email" value="${(contact_us_map.email==null)?'':contact_us_map.email}"> 
         	要說的話:<font color='red'>${errorMsgs.messagesArea}</font><br>
         	<textarea class="message-area" name="messagesArea" style="height:150px;width:100%;">${(contact_us_map.messagesArea==null)?'':contact_us_map.messagesArea}</textarea>
        </div>
        <div class="modal-footer">
          <input type="submit" class="btn btn-default" value="送出" onClick="validateForm(this.form)">
          <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
          <input type="hidden" name="com_no" value="${param.com_no}">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="取消">
        </div>
      </div>
      
    </div>
  </div>
  </form>
	<!--聯絡我們 -->
</body>
</html>