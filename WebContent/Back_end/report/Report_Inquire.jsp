<%@page import="com.report.model.ReportVO"%>
<%@page import="com.report.model.Report_Service"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>She Said Yes!</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/icons.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/sprflat-theme/jquery.ui.all.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/bootstrap.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/plugins.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/main.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/Back_end/homepage/assets/css/custom.css" rel="stylesheet" />
<link rel="icon" href="<%=request.getContextPath()%>/Back_end/homepage/assets/img/ico/favicon.ico" type="image/png">
 <link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
<meta name="msapplication-TileColor" content="#3399cc" />
</head>
<body>
<jsp:useBean id="report_type_Svc" scope="page" class="com.report_type.model.Report_type_Service" />
<jsp:useBean id="product_Svc" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="com_Svc" scope="page" class="com.com.model.ComService" />
<jsp:useBean id="mem_Svc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="art_Svc" scope="page" class="com.article.model.Article_Service" />
<jsp:useBean id="forum_Svc" scope="page" class="com.forum_comment.model.Forum_Comment_Service" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

Integer rep_no=new Integer(request.getParameter("rep_no"));
Integer rep_ob_no=new Integer(request.getParameter("rep_ob_no"));

Report_Service rep_Svc = new Report_Service();
ReportVO reportVO = rep_Svc.getOneReport(rep_no);
request.setAttribute("reportVO", reportVO);

%>

<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">審核檢舉</h4>
							</div>
							<div class="modal-body">
								<label for="inputdefault">檢舉編號:</label>
								<p>${reportVO.rep_no}</p>
							</div>
							<div class="modal-body">
								<label for="inputdefault">被檢舉標題:</label>
								<c:choose>
									<c:when test="${art_Svc.getOneArt(reportVO.rep_ob_no)!=null }">
										<p>${art_Svc.getOneArt(reportVO.rep_ob_no).title }</p>
									</c:when>
									<c:when test="${forum_Svc.getOneForum_Comment(reportVO.rep_ob_no)!=null }">
										<p>${forum_Svc.getOneForum_Comment(reportVO.rep_ob_no).cont}</p>
									</c:when>
										<c:when test="${product_Svc.getOneByPK(reportVO.rep_ob_no)!=null }">
									<p>${product_Svc.getOneByPK(reportVO.rep_ob_no).pro_name}</p>
										</c:when>
									<c:otherwise>
										<p>${com_Svc.OneCom(reportVO.rep_ob_no).name}</p>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="modal-body">
								<label for="inputdefault">檢舉內容:</label>
								<p>${reportVO.content}</p>
							</div>
							
							
							
							<c:if test="${product_Svc.getOneByPK(param.rep_ob_no).pro_no != null}">
<%-- 							<c:if test="${param.rep_ob_no.startsWith('4')}"> --%>

								<div class="modal-body">
								<label for="inputdefault">被檢舉內容:</label>
								<p>${product_Svc.getOneByPK(param.rep_ob_no).pro_desc}</p>
							</div>
							
							<div class="modal-body">
								<label for="inputdefault">照片</label>
								<div>
				<img style="width:300px;"class="img-responsive" src="<%=request.getContextPath() %>/image/ShowImage?pro_no=${param.rep_ob_no}">
								</div>
								
							</div>
							</c:if>
							
							<div class="modal-footer">
							<div class="row">
							<div class="col-md-offset-6 col-md-2" style="padding:0px">
							<form MCETHOD="post" ACTION="<%=request.getContextPath()%>/report/report.do" name="form1">
								<input	type="submit" class="btn btn-info  form-control" value="通過">
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="rep_no" value="${reportVO.rep_no }">
								<input type="hidden" name="status" value="2">
								<input type="hidden" name="rep_ob_no" value="${reportVO.rep_ob_no }"> 
								
								
							<c:if test="${mem_Svc.getOneMem(reportVO.reported_no).mem_no!=null }">
								<input type="hidden" name="mem_no" value="${reportVO.reported_no }">
								<input type="hidden" name="mem_report" value="${mem_Svc.getOneMem(reportVO.reported_no).report+1 }">
							</c:if>
							
							</form></div>
							<div class="col-md-2"  style="padding:0px">
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/report/report.do" name="form1">
								<input	type="submit" class="btn btn-danger form-control" value="不通過">
								<input type="hidden" name="action" value="update">
								<input type="hidden" name="rep_no" value="${reportVO.rep_no }">
								<input type="hidden" name="status" value="1">
							</form></div>
							<div   class="col-md-2" style="padding:0px">
								<input type="submit" class="btn btn-default form-control" data-dismiss="modal" value="返回">
							</div>
						</div>
					</div>


					











</body>
</html>