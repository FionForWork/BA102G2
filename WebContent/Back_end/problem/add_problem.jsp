<%@ page import="java.util.*"%>
<%@ page import="com.problem.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="page/problem_header_update.file"%>
<%
ProblemVO problemVO = (ProblemVO) request.getAttribute("problemVO"); 


%>
 <div class="panel-heading">
                        <h4>新增Q&A </h4>
                    </div>
<div class="container-fluid">
  <h5>&nbsp;&nbsp;</h5>
  
  <form METHOD="post" ACTION="<%=request.getContextPath()%>/Problem/Problem.do" name="form1">
  
<jsp:useBean id="problem_type_Svc" scope="page" class="com.problem_type.model.Problem_Type_Service" />
  <div class="form-group">
      <label for="sel1">Q&A類別</label>
      <select class="form-control" id="sel1" name="problem_type_no">
        <c:forEach var="problem_typeVO" items="${problem_type_Svc.all}">
				<option  value="${problem_typeVO.problem_type_no}" >
				${problem_typeVO.type}
			</c:forEach>
      </select>
    </div>
  
    <div class="form-group">
      <label for="inputdefault">Q&A問題</label>
      <input class="form-control" id="a" type="text" name="content"
      value="<%= (problemVO==null)? " " : problemVO.getContent()%>">
    </div>
    
    
    <div class="form-group">
      <label for="inputlg">Q&A回答</label>
      <textarea class="form-control" id="reply"  name="reply"
      value="<%= (problemVO==null)? " " : problemVO.getReply()%>"></textarea>
    </div>
   
    
   
   
 

  <div class="modal-footer">
                                              
   <input type="hidden" name="action" value="insert">
                                        
   <input type="submit" class="btn btn-info" data-dismiss="modal" value="新增">

  <input type="submit" class="btn btn-danger" data-dismiss="modal" href="<%=request.getContextPath()%>/Back_end/problem/Problemall.jsp" value="返回">
  <input type="button"  data-dismiss="modal" id="problem" onclick="Show()" value="">

    </div>
     </form>
</div>
<script type="text/javascript">
function Show(){
//     document.getElementById("title").value="2";
$("#a").val("發現賣家的商品似乎有違規，如何檢舉呢 ?");
$("#reply").val("為了維持良好商城交易秩序，如果會員發現站上的商品頁面有違反公告政策情形，請透過商品頁面內檢舉違規提出，收到檢舉時，系統會依序對於被檢舉的商品頁面進行檢視。")

}
</script>











<%@ include file="page/problem_footer_update.file"%>