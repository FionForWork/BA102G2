<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>She Said Yes!</title>
    <meta charset="UTF-8">
    <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/earlyaccess/notosanstc.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Hind" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Sacramento" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,600,400italic,700' rel='stylesheet' type='text/css'>
    <link href="css/bootstrap-multiselect.css" rel="stylesheet">
    <link href="https://cdn.weddingday.com.tw/weddingday-file/v2/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/pnotify.custom.min.css" rel="stylesheet">
    <link rel="Short Icon" href="<%=request.getContextPath()%>/Front_end/Resource/img/ring_64.ico">
    <link href="css/index.css" rel="stylesheet">
    <script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/top.js" type="text/javascript"></script>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="com_Svc" scope="page" class="com.com.model.ComService" />
<jsp:useBean id="mem_Svc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="rep_type_Svc" scope="page" class="com.report_type.model.Report_type_Service" />
<jsp:useBean id="product_Svc" scope="page" class="com.product.model.ProductService" />
<%@page import="com.article.model.*"%>
<%@page import="com.com.model.ComVO"%>
<%@page import="com.com.model.ComService"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%
Integer reported_no =new Integer(request.getParameter("reported_no"));
String position = request.getParameter("position");
String rep_ob_no = request.getParameter("rep_ob_no");
if((rep_ob_no.charAt(0))=='5'){
	Article_Service art_Svc=new Article_Service();
	ArticleVO articleVO = art_Svc.getOneArt(Integer.valueOf(rep_ob_no));
	pageContext.setAttribute("articleVO", articleVO);
	System.out.println(rep_ob_no);
}
session.getAttribute("memVO");
session.getAttribute("comVO");
// Integer reproter_no = new Integer(request.getParameter("reproter_no"));

%>
 
 
    <div class="modal-dialog">
    
      <form METHOD="post" ACTION="<%=request.getContextPath()%>/report/report.do" name="form1">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">檢舉</h4>
        </div>
        <div class="modal-body">
        <label for="inputdefault">檢舉對象</label>
         <input class="form-control"  type="hidden" name="reported_no" value="<%=request.getParameter("reported_no")%>">
        <c:choose>
			<c:when test="${com_Svc.getOneCom(param.reported_no)!=null }">
				<div class="name">${com_Svc.getOneCom(param.reported_no).name }</div>
			</c:when>
			<c:otherwise>
				<div class="name">${mem_Svc.getOneMem(param.reported_no).name }</div>
			</c:otherwise>
		</c:choose>
        </div>
        <div class="modal-body">
        <label for="inputdefault">檢舉類型</label> 
        <select class="form-control" id="sel1" name="rep_type_no">
	        <c:forEach var="rep_typeVO" items="${rep_type_Svc.all}">
					<option value="${rep_typeVO.rep_type_no }"> ${rep_typeVO.type }</option>
			</c:forEach>
		</select>
        </div>
        <div class="modal-body">
        <label for="inputdefault">內容</label>
         <input class="form-control" id="content" type="text" name="content">
        </div>
        
        <c:if test="${product_Svc.getOneByPK(param.rep_ob_no).pro_no!=null}">
         <div class="modal-body">
        <label for="inputdefault">照片</label>
        <div>
        <img style="width:300px;"class="img-responsive" src="<%=request.getContextPath() %>/image/ShowImage?pro_no=${param.rep_ob_no}">
        </div>
        </div>
        </c:if>
        
        <div class="modal-footer">
        	<input	type="submit" class="btn btn-info" value="檢舉">
          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          <input type="button" value="" onclick="ShowAnswer()">
        </div>
        
      </div>
      
      <input type="hidden" name="action" value="insert">
      <input type="hidden" name="reporter_no" value="${(comVO.com_no!=null)?comVO.com_no:memVO.mem_no}">
      <input type="hidden" name="status" value="0">
      <input type="hidden" name="rep_ob_no" value="<%=request.getParameter("rep_ob_no")%>">
      <input type="hidden" name="position" value="<%=request.getParameter("position")%>">
      </form>
      
    </div>
 
  <script type="text/javascript">
function ShowAnswer(){
//     document.getElementById("title").value="2";
$("#content").val("會爆炸");

}
</script>


</body>
</html>