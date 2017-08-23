<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Back_end/login/loginbackHeader.file"%>
<title>Insert title here</title>
<div id="content">

<div><center><h1>登入</h1></center></div>
<div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          
          <h4><span class="glyphicon glyphicon-lock"></span> 管理員登入</h4>
        </div> 
        <div class="modal-body" style="padding:40px 50px;">
          <form   action="<%= request.getContextPath() %>/adm/adm.do" method="post">

            <div class="form-group">
              <label for="usrname"><span class="glyphicon glyphicon-user"></span> 帳號:</label>
              <input type="text" class="form-control" name="id" placeholder="Enter email">
            </div>
            <div class="form-group">
              <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> 密碼:</label>
              <input type="password" class="form-control" name="pwd" placeholder="Enter password">
            </div>
            <div class="checkbox">
             
            </div>
           <center>
              <input type="hidden" name="action" value="login">
              <button type="submit"  type="submit" class="btn btn-info btn-block"><span class="glyphicon glyphicon-off"></span> 登入</button></center>
          </form>
        </div>
        <div class="modal-footer">
         
        </div>
      </div>

</div>
<%@ include file="/Back_end/pages/backFooter.file"%>