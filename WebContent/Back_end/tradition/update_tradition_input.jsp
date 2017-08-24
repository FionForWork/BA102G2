<%@ page import="java.util.*"%>
<%@ page import="com.tradition.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
TraditionVO traditionVO = (TraditionVO) request.getAttribute("traditionVO"); 

%>
<%@ include file="page/tradition_header_update.file"%>

                  <div class="panel-heading">
                        <h4>修改習俗</h4>
                  </div>
<div class="container-fluid">
  <h5>&nbsp;&nbsp;</h5>
  
  <Form METHOD="post" ACTION="Tradition.do" name="form1">
  <div class="form-group">
      <label for="inputdefault">習俗項次</label>
      <br>
      <a ><%=traditionVO.getTra_no() %></a>
      
    </div>
<jsp:useBean id="tradition_type_Svc" scope="page" class="com.tradition_type.model.Tradition_Type_Service" />

  <div class="form-group">
      <label for="sel1">習俗類別</label>
      <select class="form-control" id="sel1" name="tra_type_no" >
     
       
        
        <c:forEach var="tradition_typeVO" items="${tradition_type_Svc.all}">
				<option  value="${tradition_typeVO.tra_type_no}" ${(tradition_typeVO.tra_type_no==traditionVO.tra_type_no)?'selected':'' }>
				${tradition_typeVO.type}
			</c:forEach>

        
      </select>
    </div>
    <div class="form-group">
      <label for="sel1">習俗順序</label>
      <input class="form-control" id="inputdefault" name="tra_order" type="text" value="<%=traditionVO.getTra_order() %>">
  </div>
    <div class="form-group">
      <label for="inputdefault">習俗標題</label>
      <input class="form-control" id="inputdefault" name="title" type="text" value="<%=traditionVO.getTitle() %>">
    </div>
    <div class="form-group">
      <label for="inputlg">習俗文章</label>
     
      <textarea class="form-control" rows="5" id="comment" name="article"   value="<%=traditionVO.getArticle()%>"><%=traditionVO.getArticle()%></textarea>
    </div>
   
    
 
   
 

  <div class="modal-footer">
    <input type="hidden" name="action" value="update">  
    <input type="hidden" name="tra_no" value="<%=traditionVO.getTra_no()%>">                                        
   <input type="submit" class="btn btn-info" data-dismiss="modal" value="存檔">

  <input type="submit" class="btn btn-danger" data-dismiss="modal"  href="/tradition/Traditionall.jsp" value="返回">
    </div>
    </Form> 
</div>


<%@ include file="page/tradition_footer_update.file"%>