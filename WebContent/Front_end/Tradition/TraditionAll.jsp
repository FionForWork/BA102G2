<%@ page import="java.util.*"%>
<%@ page import="com.tradition.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
 Tradition_Service traSvc=new Tradition_Service();
   List<TraditionVO> list=traSvc.getAll();
   pageContext.setAttribute("list", list);
   %>
<%@ include file="page/tradition_header.file"%>
 <div class="col-md-9">
 <div id="dashboard-content">
<div class="container-fluid">
  <h2>訂婚流程</h2>
  <c:forEach var="TraditionVO" items="${list}" >
   <div class="panel-group" id="accordion">
    <div class="panel " style="background-color: #f5f5f5;" >
     <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#${TraditionVO.tra_no }">${TraditionVO.tra_no }.${TraditionVO.title}</a>
          
        </h4>
      </div>
      
      <div id="${TraditionVO.tra_no }" class="panel-collapse collapse ">
        <div class="panel-body"><pre>${TraditionVO.article}</pre> </div>
      </div>

                       </div>
                       </c:forEach>
                    </div>
                </div>

<%@ include file="page/tradition_footer.file"%>