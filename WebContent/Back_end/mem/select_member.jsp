<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%@ include file="/Back_end/adm/page/backHeader.file"%>
<title>選擇會員</title>
<br><br><br>
<div id="content" style="padding:20px 1px 1px 20px;" ><h2>查看會員</h2></div>
<div id="content">
<a href='<%=request.getContextPath()%>/Back_end/mem/listAllMem.jsp'>觀看全部會員</a>  <br><br>
<table class="table table-striped">
	<tr>
		<td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" >
        <b>輸入會員編號 (如1001):</b></td>
        <td><input type="text" size="30" name="mem_no"></td>
        <td><input type="submit" class="btn btn-info" value="送出"></td>
        <input type="hidden" name="action" value="bgetOne_For_Display">
		</FORM>
		
	</tr>


<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
   
	<tr>
	<td>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" >
       <b>選擇會員編號:</b></td>
      <td> <select style="width: 223px;" id="select1" name="mem_no">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.mem_no}">${memVO.mem_no}</option>
         </c:forEach>   
       </select></td>
      <td><input type="submit" class="btn btn-info" value="送出"></td>
      	<input type="hidden" value="activate selectator" id="activate_selectator1" type="button">
       <input type="hidden" name="action" value="bgetOne_For_Display">
    </FORM>

	</tr>
	
	<tr><td>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" >
       <b>選擇會員姓名:</b></td>
       <td><select style="width: 223px;" id="select2" name="mem_no">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.mem_no}">${memVO.name}
         </c:forEach>   
       </select></td>
       <td><input type="submit" class="btn btn-info" value="送出"></td>
       <input type="hidden" value="activate selectator" id="activate_selectator2" type="button">
       <input type="hidden" name="action" value="bgetOne_For_Display">
     </FORM></td>
     </tr>
     
   
     
     <tr><td>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" >
        <b>根據被檢舉多少次以上:</b></td>
      <td>  <input type="text" size="30" name="report"></td>
     <td>   <input type="submit" class="btn btn-info" value="送出"></td>
        <input type="hidden" name="action" value="selectByReport">
	</FORM>
	</tr>
	
	

	<tr><td>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" >
       <b>狀態停權者</b></td>
      <td> <select style="width: 223px;" name="status">
        
          <option value="停權">停權
          <option value="正常">正常
       </select></td>
     <td>  <input type="submit" class="btn btn-info" value="送出"></td>
       <input type="hidden" name="action" value="selectByStatus">
     </FORM></td>
	</tr>
	
</table>
</div>

<link rel="stylesheet" href="<%=request.getContextPath()%>/Back_end/mem/assets/css/fm.selectator.jquery.css"/>
	<style>
	
		#wrapper {
			padding: 15px;
		}
		#select1 {
			width: 250px;
			padding: 7px 10px;
		}
		
	</style>
	<script src="<%=request.getContextPath()%>/Back_end/mem/assets/JS/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/Back_end/mem/assets/JS/fm.selectator.jquery.js"></script>
	<script>
		$(function () {
			var $activate_selectator1 = $('#activate_selectator1');
			$activate_selectator1.click(function () {
				var $select1 = $('#select1');
				
					$select1.selectator({
						labels: {
							search: 'Search here...'
						}
					});
					
			});
			$activate_selectator1.trigger('click');

		});
		$(function () {
			var $activate_selectator2 = $('#activate_selectator2');
			$activate_selectator2.click(function () {
				var $select2 = $('#select2');
				
					$select2.selectator({
						labels: {
							search: 'Search here...'
						}
					});
					
			});
			$activate_selectator2.trigger('click');

		});
	</script>
<%@ include file="/Back_end/pages/backFooter.file"%>
