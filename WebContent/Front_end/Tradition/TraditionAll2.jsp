<%@ page import="java.util.*"%>
<%@ page import="com.tradition.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
 Tradition_Service traSvc=new Tradition_Service();
   List<TraditionVO> list=traSvc.getAll();
   pageContext.setAttribute("list", list);
   
  
   %>
    
   
<%@ include file="page/tradition_header2.file"%>



 <div class="col-md-10">
 
 
 <div class="" >
    <ul class="nav nav-pills " style="padding-left: 8cm" >
    <li class="active"><a data-toggle="pill" href="#home">ALL</a></li>
    <li><a data-toggle="pill" href="#menu1">訂婚流程</a></li>
    <li><a data-toggle="pill" href="#menu2">結婚流程</a></li>
    <li><a data-toggle="pill" href="#menu3">禁忌、歸寧</a></li>
    <li><a data-toggle="pill" href="#menu4">六禮、十二禮</a></li>
  </ul> 
  </div>
<!--  *******************************************************************************************************************************  -->

<div id="home" class="tab-pane fade in active">  
 <div id="dashboard-content">
<div class="container-fluid">
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
</div>
<!-- ****************************************************************************************************************** -->
<div id="menu1" class="tab-pane fade in active">  
 <div id="dashboard-content">
<div class="container-fluid">
  
<%--   <c:forEach var="TraditionVO" items="${list}" > --%>
  <c:choose>
  <c:when test="${TraditionVO.tra_type_no==10}">
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
                       </c:when>
                       
                       <c:otherwise></c:otherwise>
                       </c:choose>
<%--                        </c:forEach> --%>
                    </div>
                </div>
</div>
<!-- ************************************************************************************************************************************** -->
<div id="menu2" class="tab-pane fade in active">  
 <div id="dashboard-content">
<div class="container-fluid">
  
<%--   <c:forEach var="TraditionVO" items="${list}" > --%>
  <c:choose>
  <c:when test="${TraditionVO.tra_type_no==20}">
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
                       </c:when>
                       
                       <c:otherwise></c:otherwise>
                       </c:choose>
<%--                        </c:forEach> --%>
                    </div>
                </div>
</div>
<!-- ************************************************************************************************************************************** -->
<div id="menu3" class="tab-pane fade in active">  
 <div id="dashboard-content">
<div class="container-fluid">
  
<%--   <c:forEach var="TraditionVO" items="${list}" > --%>
  <c:choose>
  <c:when test="${TraditionVO.tra_type_no==30}">
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
                       </c:when>
                       
                       <c:otherwise></c:otherwise>
                       </c:choose>
<%--                        </c:forEach> --%>
                    </div>
                </div>
</div>
<!-- ************************************************************************************************************************************** -->
<div id="menu4" class="tab-pane fade in active">  
 <div id="dashboard-content">
<div class="container-fluid">
  
<%--   <c:forEach var="TraditionVO" items="${list}" > --%>
  <c:choose>
  <c:when test="${TraditionVO.tra_type_no==40}">
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
                       </c:when>
                       
                       <c:otherwise></c:otherwise>
                       </c:choose>
<%--                        </c:forEach> --%>
                    </div>
                </div>
</div>
<!-- ************************************************************************************************************************************** -->
 
 </div>
        
        

<%@ include file="page/tradition_footer.file"%>