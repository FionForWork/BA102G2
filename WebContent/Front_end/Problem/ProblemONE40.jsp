<%@ page import="java.util.*"%>
<%@ page import="com.problem.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
   <%
   Problem_Service proSvc=new Problem_Service();
   List<ProblemVO> list=proSvc.getAll();
   pageContext.setAttribute("list", list);
   %>
 
<%@ include file="page/problem_header.file"%>
 <div class="col-md-8">
 <div id="dashboard-content">
 <div class="container-fluid">
  <h2>預約服務</h2>
  

<c:forEach var="ProblemVO" items="${list}" >
<c:if test="${ProblemVO.problem_type_no=='40' }">

      <div class="panel-group" id="accordion">
    <div class="panel " style="background-color: #f5f5f5;" >
        
       <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#${ProblemVO.prob_no }">${ProblemVO.prob_no }.${ProblemVO.content}</a>
          
        </h4>
      </div>
      
      <div id="${ProblemVO.prob_no }" class="panel-collapse collapse ">
        <div class="panel-body">${ProblemVO.reply}</div>
      </div>

    
       


    </div>
    </c:if>
    </c:forEach>
    </div>
    </div>

                    </div>
                </div>
		
<%@ include file="page/problem_footer.file"%>
