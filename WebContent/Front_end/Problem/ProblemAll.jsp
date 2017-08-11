<%@ page import="java.util.*"%>
<%@ page import="com.problem.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%List<ProblemVO> problemlist = (List<ProblemVO>) session.getAttribute("problemlist"); %>  
   <%
   Problem_Service proSvc=new Problem_Service();
   List<ProblemVO> list=proSvc.getAll();
   pageContext.setAttribute("list", list);
   %>
 
<%@ include file="page/problem_header.file"%>
 <div class="col-md-8">
 <div id="dashboard-content">
 <div class="container-fluid">
			<br>
			<div class="btn-group btn-group-justified">
				<div class="btn-group">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Problem/Problem.do">
						<input type="submit" class="btn btn-info" value="ALL"> <input
							type="hidden" name="action" value="get_All">
					</FORM>
				</div>
				<div class="btn-group">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Problem/Problem.do">
						<input type="submit" class="btn btn-info" value="帳號問題"> <input
							type="hidden" name="problem_type_no" value="10"> <input
							type="hidden" name="action" value="get_OneAll">
					</FORM>
				</div>
				<div class="btn-group">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Problem/Problem.do">
						<input type="submit" class="btn btn-info" value="商城問題"> <input
							type="hidden" name="problem_type_no" value="20"> <input
							type="hidden" name="action" value="get_OneAll">
					</FORM>
				</div>
				<div class="btn-group">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Problem/Problem.do">
						<input type="submit" class="btn btn-info" value="功能問題"> <input
							type="hidden" name="problem_type_no" value="30"> <input
							type="hidden" name="action" value="get_OneAll">
					</FORM>
				</div>
				<div class="btn-group">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Problem/Problem.do">
						<input type="submit" class="btn btn-info" value="預約服務">
						<input type="hidden" name="problem_type_no" value="40"> <input
							type="hidden" name="action" value="get_OneAll">
					</FORM>
				</div>

			</div>
		</div>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 <div class="container-fluid">
  <br>
<c:choose>
<c:when test="${problemlist!=null}">  

<c:forEach var="ProblemVO" items="${problemlist}" >


      <div class="panel-group" id="accordion">
    <div class="panel " style="background-color: #f5f5f5;" >
        
       <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#${ProblemVO.prob_no }">${ProblemVO.prob_no }.${ProblemVO.content}</a>
          
        </h4>
      </div>
      
      <div id="${ProblemVO.prob_no }" class="panel-collapse collapse ">
        <div class="panel-body">${ProblemVO.reply} </div>
      </div>

    
       


    </div>
    </c:forEach>
    </c:when>
 <c:when test="${list!=null}">  

<c:forEach var="ProblemVO" items="${list}" >


      <div class="panel-group" id="accordion">
    <div class="panel " style="background-color: #f5f5f5;" >
        
       <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#${ProblemVO.prob_no }">${ProblemVO.prob_no }.${ProblemVO.content}</a>
          
        </h4>
      </div>
      
      <div id="${ProblemVO.prob_no }" class="panel-collapse collapse ">
        <div class="panel-body">${ProblemVO.reply} </div>
      </div>

    
       


    </div>
    </c:forEach>
    </c:when>
    <c:otherwise></c:otherwise>
			</c:choose>
 
    </div>
    </div>

                    </div>
                </div>
		
<%@ include file="page/problem_footer.file"%>
