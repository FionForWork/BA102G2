<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.aut.model.*"%>
<%@ page import="java.util.*"%>
<%
AutVO autVO = (AutVO) request.getAttribute("autVO");
Map<String,String> errorMsgs = (HashMap) request.getAttribute("errorMsgs");
%>
<%@ include file="/Back_end/adm/page/backHeader.file"%>
<script src="<%=request.getContextPath()%>/Front_end/Resource/js/jquery-3.1.1.min.js" type="text/javascript"></script>
  <div id="sidebar">

        <div class="sidebar-inner">
  
            <ul id="sideNav" class="nav nav-pills nav-stacked">
				 <c:if test="${aut.contains(\"02\")}">
                <li><a href="<%=request.getContextPath()%>/Back_end/mem/select_member.jsp"><i class="en-users"></i>一般會員管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"03\")}">
				<li><a href="<%=request.getContextPath() %>/Back_end/com/listAllCom.jsp"><i class="en-users"></i>廠商管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"01\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/adm/listAllAdm.jsp" class='active'><i class="en-users "> </i>管理者設定 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"08\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/advertising/ad.jsp"><i class="im-table2"></i>廣告刊登管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"07\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/place/placeManagement.jsp"><i class="br-location"></i>景點管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"06\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/mall/productPreview.jsp"><i class="fa-shopping-cart"></i>商城管理</a></li>
				</c:if>
				<c:if test="${aut.contains(\"04\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/tradition/Traditionall.jsp"><i class="im-list2"></i>婚禮習俗管理</a></li>
				</c:if>
				<c:if test="${aut.contains(\"05\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/problem/Problemall.jsp"><i class="im-question"></i>常見問題管理</a></li>
				</c:if>
				<c:if test="${aut.contains(\"09\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/report/Report.jsp"><i class="br-warning"></i>檢舉管理</a></li>
				</c:if>
            </ul>

        </div>

    </div>
<br><br><br>
<div id="content">
<div class="text-center well" >
	<h2 style="font-weight:900">新增管理員權限 </h3>
</div>
<input type="button" class="btn btn-info" value="返回" onclick="location.href='<%=request.getContextPath()%>/Back_end/aut/listAllAut.jsp'" >


<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/aut/aut.do" name="form1" onsubmit="return ifChecked();">
<table class="table table-striped">
	<jsp:useBean id="admSvc" scope="page" class="com.adm.model.AdmService" />
	<tr>
		<td>管理員:<font color=red><b>*</b></font></td>
		<td><select size="1" name="adm_no">
			<c:forEach var="admVO" items="${admSvc.all}">
				<option value="${admVO.adm_no}" ${(admVO.adm_no==autVO.adm_no)? 'selected':'' } >${admVO.adm_no}${admVO.name} 職位:${admVO.job}
			</c:forEach>
		</select></td>
	</tr>
	
	
	
	
	<jsp:useBean id="funSvc" scope="page" class="com.fun.model.FunService" />
	<tr>
		<td>權限:</td>
		<td>
		<font color='red'>${errorMsgs.get("length")}${errorMsgs.get("aut")}</font><br>
			 <input TYPE="checkbox"　name="id" id="id" VALUE="01">員工資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="02">會員資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="03">廠商資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="04">其他資料管理<BR><BR>
			 <input TYPE="checkbox" name="id" id="id" VALUE="05">客服資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="06">商城資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="07">景點資料管理　　　
			 <input TYPE="checkbox" name="id" id="id" VALUE="08">廣告資料管理<BR><BR>
			 <input TYPE="checkbox" name="id" id="id"  VALUE="09">檢舉資料管理　　　<BR><BR><BR><BR>
			 <input TYPE="button" class="btn btn-info" VALUE="員工" id="adm">　　　　　　　　
			 <input TYPE="button"class="btn btn-info " VALUE="經理" id="manager">　　　　　　　　
　　　　　　　　
		</td>
	</tr>
<script>
$(document).ready(function(){
	$("#manager").click(function() {
		$("input[name=id][value=02]").attr("checked",true);
		$("input[name=id][value=03]").attr("checked",true);
		$("input[name=id][value=04]").attr("checked",true);
		$("input[name=id][value=05]").attr("checked",true);
		$("input[name=id][value=06]").attr("checked",true);
		$("input[name=id][value=07]").attr("checked",true);
		$("input[name=id][value=08]").attr("checked",true);
		$("input[name=id][value=09]").attr("checked",true);
		});
$("#adm").click(function() {
	$("input[name=id][value=02]").attr("checked",true);
	$("input[name=id][value=03]").attr("checked",true);
	$("input[name=id][value=04]").attr("checked",true);
	$("input[name=id][value=05]").attr("checked",true);
	$("input[name=id][value=06]").attr("checked",true);
	$("input[name=id][value=07]").attr("checked",true);
	$("input[name=id][value=09]").attr("checked",true);
	});
});
</script>
<script>
function ifChecked(){
   var a = document.getElementsByName("id"); 
   var n = a.length;
   var k = 0;
   for (var i=0; i<n; i++){
        if(a[i].checked){
            k = 1;
        }
    }
        if(k==0){
        	alert("請填選權限");
        	return  false;
    	}
 }	
</script>
</table>
<br>
<input type="hidden"  name="action" value="insert">
<input type="submit"  id="submit"class="btn btn-info "  value="送出新增"></FORM>
<%@ include file="/Back_end/pages/backFooter.file"%>
