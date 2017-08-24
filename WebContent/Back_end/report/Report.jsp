<%@page import="com.article.model.Article_Service"%>
<%@page import="com.report.model.ReportVO"%>
<%@page import="java.util.List"%>
<%@page import="com.report.model.Report_Service"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="page/report_header.file"%>
<jsp:useBean id="report_type_Svc" scope="page" class="com.report_type.model.Report_type_Service" />
<jsp:useBean id="product_Svc" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="com_Svc" scope="page" class="com.com.model.ComService" />
<jsp:useBean id="mem_Svc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="art_Svc" scope="page" class="com.article.model.Article_Service" />
<jsp:useBean id="forum_Svc" scope="page" class="com.forum_comment.model.Forum_Comment_Service" />
<!-- ======================================================================================================================== -->
<%
	Report_Service report_Svc = new Report_Service();
	List<ReportVO> list = (List<ReportVO>) report_Svc.getAll();
	request.setAttribute("list", list);
	
%>



<!-- ======================================================================================================================== -->
<div id="content">
	<div class="content-wrapper">
		<br> <br>
		<div class="row">


			<div class="col-md-10 col-md-offset-1">
				<h3>檢舉管理</h3>
				<ul class="nav nav-tabs nav-justified">
					<li class="active"><a data-toggle="tab" href="#home">所有檢舉</a></li>
					<li><a data-toggle="tab" href="#menu1">未審核</a></li>
					<li><a data-toggle="tab" href="#menu2">已審核</a></li>
				</ul>
				<!-- ************************************************************************************* -->
				<div class="tab-content">
					<div id="home" class="tab-pane fade in active">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>檢舉編號</th>
									<th>檢舉類型</th>
									<th>檢舉標題</th>
									<th>檢舉日期</th>
									<th>狀態</th>
									<th>審核</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="reportVO" items="${list}">
									<tr>
										<td>${reportVO.rep_no}</td>
										<td>${report_type_Svc.getOneRep_Type(reportVO.rep_type_no).type}</td>
<!-- 									***********************************************************************************	 -->
										<c:choose>
											<c:when test="${art_Svc.getOneArt(reportVO.rep_ob_no)!=null }">
												<td>${art_Svc.getOneArt(reportVO.rep_ob_no).title }</td>
											</c:when>
											<c:when test="${forum_Svc.getOneForum_Comment(reportVO.rep_ob_no)!=null }">
												<td>討論版留言</td>
											</c:when>
											<c:when test="${product_Svc.getOneByPK(reportVO.rep_ob_no).getStatus()!=null}">
												<td>${product_Svc.getOneByPK(reportVO.rep_ob_no).pro_name}</td>
											</c:when>
											<c:otherwise>
												<td>已被刪除</td>
											</c:otherwise>
										</c:choose>
<!-- 									***********************************************************************************	 -->										
										<td>${reportVO.rep_date}</td>
<!-- 									***********************************************************************************	 -->										
										<c:choose>
											<c:when test="${reportVO.status==0 }">
												<td>未審核</td>
											</c:when>
											<c:when test="${reportVO.status==1 }">
											<td style="color:red">未通過</td>
											</c:when>
											<c:otherwise>
											<td style="color:blue">已通過</td>
											</c:otherwise>
										</c:choose>
<!-- 									***********************************************************************************	 -->
										<td>
										<c:choose>
										<c:when test="${reportVO.status==0 }">
										<input type="submit" class="btn btn-info"onclick="showmodal('article','${reportVO.rep_no}','${reportVO.rep_ob_no }')" value="待審核">
										</c:when>
										<c:when test="${reportVO.status==1 }">
										<input type="submit" class="btn btn-danger "  disabled="disabled" onclick="showmodal('article','${reportVO.rep_no}')" value="未通過">
										</c:when>
										<c:otherwise>
										<input type="submit" class="btn btn-info"disabled="disabled" onclick="showmodal('article','${reportVO.rep_no}')" value="已通過">
										</c:otherwise>
										</c:choose>
										
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${art_Svc.getOneArt(reportVO.rep_ob_no)!=null }"> --%>
<!-- 										<input type="submit" class="btn btn-info" -->
<%-- 										onclick="showmodal('article','${reportVO.rep_ob_no}','${art_Svc.getOneArt(reportVO.rep_ob_no).title }','${reportVO.rep_no}')" --%>
<!-- 										value="審核"> -->
<%-- 											</c:when> --%>
<%-- 											<c:when test="${forum_Svc.getOneForum_Comment(reportVO.rep_ob_no)!=null }"> --%>
<!-- 										<input type="submit" class="btn btn-info"   -->
<%-- 										onclick="showmodal('article','${reportVO.rep_ob_no}','${forum_Svc.getOneForum_Comment(reportVO.rep_ob_no).art_no} ','${reportVO.rep_no}')" value="審核"> --%>
<%-- 											</c:when> --%>
<%-- 											<c:otherwise> --%>
												
<%-- 											</c:otherwise> --%>
<%-- 											</c:choose> --%>
							
										</td>

									</tr>
									</c:forEach>
							</tbody>
							
						</table>
					</div>
					<div id="menu1" class="tab-pane fade">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>檢舉編號</th>
									<th>檢舉類型</th>
									<th>檢舉標題</th>
									<th>檢舉日期</th>
									<th>狀態</th>
									<th>審核</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="reportVO" items="${list}">
								<c:if test="${reportVO.status==0 }">
									<tr>
										<td>${reportVO.rep_no}</td>
										<td>${report_type_Svc.getOneRep_Type(reportVO.rep_type_no).type}</td>
<!-- 									***********************************************************************************	 -->
										<c:choose>
											<c:when test="${art_Svc.getOneArt(reportVO.rep_ob_no)!=null }">
												<td>${art_Svc.getOneArt(reportVO.rep_ob_no).title }</td>
											</c:when>
											<c:when test="${forum_Svc.getOneForum_Comment(reportVO.rep_ob_no)!=null }">
												<td>討論版留言</td>
											</c:when>
											<c:when test="${product_Svc.getOneByPK(reportVO.rep_ob_no).getStatus()!=null}">
												<td>${product_Svc.getOneByPK(reportVO.rep_ob_no).pro_name}</td>
											</c:when>
											<c:otherwise>
												<td>已被刪除</td>
											</c:otherwise>
										</c:choose>
<!-- 									***********************************************************************************	 -->										
										<td>${reportVO.rep_date}</td>
<!-- 									***********************************************************************************	 -->										
										<c:choose>
											<c:when test="${reportVO.status==0 }">
												<td>未審核</td>
											</c:when>
											<c:when test="${reportVO.status==1 }">
											<td style="color:red">未通過</td>
											</c:when>
											<c:otherwise>
											<td style="color:blue">已通過</td>
											</c:otherwise>
										</c:choose>
										<td>
										<c:choose>
										<c:when test="${reportVO.status==0 }">
										<input type="submit" class="btn btn-info"onclick="showmodal('article','${reportVO.rep_no}','${reportVO.rep_ob_no }')" value="待審核">
										</c:when>
										<c:when test="${reportVO.status==1 }">
										<input type="submit" class="btn btn-danger "  disabled="disabled" onclick="showmodal('article','${reportVO.rep_no}')" value="未通過">
										</c:when>
										<c:otherwise>
										<input type="submit" class="btn btn-info"disabled="disabled" onclick="showmodal('article','${reportVO.rep_no}')" value="已通過">
										</c:otherwise>
										</c:choose>
					</div>
					</td>

									</tr>
									</c:if>
									</c:forEach>
							</tbody>
							
						</table>
					</div>
					
					
					
					
					
					
					<div id="menu2" class="tab-pane fade">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>檢舉編號</th>
									<th>檢舉類型</th>
									<th>檢舉標題</th>
									<th>檢舉日期</th>
									<th>狀態</th>
									<th>審核</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="reportVO" items="${list}">
								<c:if test="${reportVO.status!=0}">
									<tr>
										<td>${reportVO.rep_no}</td>
										<td>${report_type_Svc.getOneRep_Type(reportVO.rep_type_no).type}</td>
<!-- 									***********************************************************************************	 -->
										<c:choose>
											<c:when test="${art_Svc.getOneArt(reportVO.rep_ob_no)!=null }">
												<td>${art_Svc.getOneArt(reportVO.rep_ob_no).title }</td>
											</c:when>
											<c:when test="${forum_Svc.getOneForum_Comment(reportVO.rep_ob_no)!=null }">
												<td>討論版留言</td>
											</c:when>
											<c:when test="${product_Svc.getOneByPK(reportVO.rep_ob_no).getStatus()!=null}">
												<td>${product_Svc.getOneByPK(reportVO.rep_ob_no).pro_name}</td>
											</c:when>
											<c:otherwise>
												<td>已被刪除</td>
											</c:otherwise>
										</c:choose>
<!-- 									***********************************************************************************	 -->										
										<td>${reportVO.rep_date}</td>
<!-- 									***********************************************************************************	 -->										
										<c:choose>
											<c:when test="${reportVO.status==0 }">
												<td>未審核</td>
											</c:when>
											<c:when test="${reportVO.status==1 }">
											<td style="color:red">未通過</td>
											</c:when>
											<c:otherwise>
											<td style="color:blue">已通過</td>
											</c:otherwise>
										</c:choose>
										<td>
										<c:choose>
										<c:when test="${reportVO.status==0 }">
										<input type="submit" class="btn btn-info"onclick="showmodal('article','${reportVO.rep_no}','${reportVO.rep_ob_no }')" value="待審核">
										</c:when>
										<c:when test="${reportVO.status==1 }">
										<input type="submit" class="btn btn-danger "  disabled="disabled" onclick="showmodal('article','${reportVO.rep_no}')" value="未通過">
										</c:when>
										<c:otherwise>
										<input type="submit" class="btn btn-info"disabled="disabled" onclick="showmodal('article','${reportVO.rep_no}')" value="已通過">
										</c:otherwise>
										</c:choose>
					</div>
					</td>

									</tr>
									</c:if>
									</c:forEach>
							</tbody>
							
						</table>
					</div>

				</div>

				<!-- 				******************************************************************************************************** -->
				<div class="modal fade" id="myModal" role="dialog">
					
				</div>



			</div>

		</div>
	</div>

</div>

<script type="text/javascript">
// function showmodal(action,rep_ob_no,reptitle,rep_no){
// 	console.log(rep_ob_no);
// 	console.log(reptitle);
// 	console.log(rep_no);
// 	if(action=='article'){
// 		console.log(action);
<%-- 		$('#myModal').load("<%=request.getContextPath()%>/Back_end/report/Report_Inquire.jsp?",{"rep_ob_no":rep_ob_no,"reptitle":reptitle,"rep_no":rep_no}); --%>
// 		$("#myModal").modal('show');
		
// 	}
// }
function showmodal(action,rep_no,rep_ob_no){
	
	if(action=='article'){
		console.log(action);
		$('#myModal').load("<%=request.getContextPath()%>/Back_end/report/Report_Inquire.jsp?",{"rep_no":rep_no,"rep_ob_no":rep_ob_no});
		$("#myModal").modal('show');
		
	}
}
</script>


<%@ include file="page/report_footer.file"%>
