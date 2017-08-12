<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	<%@ include file="page/before.file"%>
	
	<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" id="contact_us_btn">聯絡我們</button>
	

    <form method="post" action="<%=request.getContextPath()%>/ContactUs">
	<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title text-center">聯絡我們</h4>
        </div>
        <div class="modal-body">
         	 姓名:<input type="text" class="form-control" name="name">
         	Email:<input type="text" class="form-control" name="email"> 
         	要說的話:<br>
         	<textarea class="message-area" name="messagesArea" style="height:150px;width:100%;"></textarea>
        </div>
        <div class="modal-footer">
          <input type="submit" class="btn btn-default" data-dismiss="modal" value="送出">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="取消">
        </div>
      </div>
      
    </div>
  </div>
  </form>


	<%@ include file="page/after.file"%>
</body>
</html>