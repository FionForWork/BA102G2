<%@ page import="java.util.*"%>
<%@ page import="com.tradition.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="page/tradition_header_update.file"%>
<%
TraditionVO traditionVO = (TraditionVO) request.getAttribute("traditionVO"); 

%>
 <div class="panel-heading">
                        <h4>新增習俗 </h4>
                    </div>
<div class="container-fluid">
  <h5>&nbsp;&nbsp;</h5>
  
  <form METHOD="post" ACTION="<%=request.getContextPath()%>/Tradition/Tradition.do" name="form1">
  
<jsp:useBean id="tradition_type_Svc" scope="page" class="com.tradition_type.model.Tradition_Type_Service" />
  <div class="form-group">
      <label for="sel1">習俗類別</label>
      <select class="form-control" id="sel1" name="tra_type_no">
        <c:forEach var="tradition_typeVO" items="${tradition_type_Svc.all}">
				<option  value="${tradition_typeVO.tra_type_no}" }>
				${tradition_typeVO.type}
			</c:forEach>
      </select>
    </div>
  
  <div class="form-group">
      <label for="inputdefault">習俗順序</label>
      <input class="form-control" id="inputdefault" type="text" name="tra_order"
      value="<%= (traditionVO==null)? " " : traditionVO.getTra_order()%>">
    </div>
  
    <div class="form-group">
      <label for="inputdefault">習俗標題</label>
      <input class="form-control" id="inputdefault" type="text" name="title"
      value="<%= (traditionVO==null)? " " : traditionVO.getTitle()%>">
    </div>
    
    
    <div class="form-group">
      <label for="inputlg">習俗文章</label>
      <textarea class="form-control" id="inputdefault"  name="article"
      value="<%= (traditionVO==null)? " " : traditionVO.getArticle()%>"></textarea>
    </div>
   
    
   
   
 

  <div class="modal-footer">
                                              
   <input type="hidden" name="action" value="insert">
                                        
   <input type="submit" class="btn btn-info" data-dismiss="modal" value="新增">

  <input type="submit" class="btn btn-danger" data-dismiss="modal" href="<%=request.getContextPath()%>/Back_end/tradition/TraditionVOall.jsp" value="返回">


    </div>
     </form>
</div>




<%@ include file="page/tradition_footer_update.file"%>