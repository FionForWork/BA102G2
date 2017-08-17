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
      <td> <select style="width: 223px;" name="mem_no">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.mem_no}">${memVO.mem_no}
         </c:forEach>   
       </select></td>
      <td><input type="submit" class="btn btn-info" value="送出"></td>
       <input type="hidden" name="action" value="bgetOne_For_Display">
    </FORM>

	</tr>
	
	<tr><td>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" >
       <b>選擇會員姓名:</b></td>
       <td><select style="width: 223px;" name="mem_no">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.mem_no}">${memVO.name}
         </c:forEach>   
       </select></td>
       <td><input type="submit" class="btn btn-info" value="送出"></td>
       <input type="hidden" name="action" value="bgetOne_For_Display">
     </FORM></td>
     </tr>
     
     <tr><td>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" >
       <b>根據被檢舉次數:</b></td>
       <td><select style="width: 223px;" name="mem_no">
         <c:forEach var="memVO" items="${memSvc.all}" > 

          <option value="${memVO.mem_no}">${memVO.name}:檢舉次數:${memVO.report}

         </c:forEach>   
       </select></td>
     <td>  <input type="submit" class="btn btn-info" value="送出"></td>
       <input type="hidden" name="action" value="bgetOne_For_Display">
     </FORM>
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
       <b>檢舉三次以上:</b></td>
      <td> <select style="width: 223px;"  name="mem_no">
         <c:forEach var="memVO" items="${memSvc.all}" > 
         <c:if test="${memVO.report>=3}">
          <option value="${memVO.mem_no}">${memVO.name}:檢舉次數:${memVO.report}
         </c:if>
         </c:forEach>   
       </select></td>
      <td> <input type="submit" class="btn btn-info" value="送出"></td>
       <input type="hidden" name="action" value="bgetOne_For_Display">
     </FORM>
	</tr>

	<tr><td>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do" >
       <b>狀態停權者</b></td>
      <td> <select style="width: 223px;" name="mem_no">
         <c:forEach var="memVO" items="${memSvc.all}" > 
         <c:if test="${memVO.status==\"停權\"}">
          <option value="${memVO.mem_no}">${memVO.name}◆狀態:${memVO.status}
         </c:if>
         </c:forEach>   
       </select></td>
     <td>  <input type="submit" class="btn btn-info" value="送出"></td>
       <input type="hidden" name="action" value="bgetOne_For_Display">
     </FORM></td>
	</tr>
	
</table>
</div>

<%@ include file="/Back_end/pages/backFooter.file"%>
