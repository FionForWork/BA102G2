<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/Back_end/login/loginbackHeader.file"%>
<div id="sidebar">
        <div class="sidebar-inner">
            <ul id="sideNav" class="nav nav-pills nav-stacked">
               <c:if test="${aut.contains(\"02\")}">
               <li><a href="<%=request.getContextPath()%>/Back_end/mem/select_member.jsp" ><i class="en-users"></i>一般會員管理 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"03\")}">
				<li><a href="<%= request.getContextPath() %>/Back_end/com/listAllCom.jsp" ><i class="en-users"></i>廠商管理 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"01\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/adm/listAllAdm.jsp" class='active'><i class="en-users"> </i>管理者設定 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"08\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/advertising/ad.jsp"><i class="im-table2"></i>廣告刊登管理 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"07\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/place/placeManagement.jsp"><i class="br-location"></i>景點管理 </a></li>
				</c:if>
				
				<c:if test="${aut.contains(\"04\")}">
				<li><a href="#"><i class="im-newspaper"></i>熱門資訊管理</a></li>
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
    </script>
<title>修改密碼</title>
<div id="content">
<br><br><br><br><br><br>
<br><br><br>
<center> <h1>密碼修改</h1></center>


<c:if test="${not empty errorMsgs}">
	<font color='red'>
	請修正錯誤:<br>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/adm/adm.do" name="form9" onSubmit="return check();" >
		<div class="form-group">
		<span >員工編號: ${admVO.adm_no}<br><br><br></span>
           <span >舊密碼:<font color='red'>${errorMsgs.get(0)}</font></span>
           <input type="password" class="form-control"  name="oldpwd" value="">
    	</div>
    	<div class="form-group">
           <span >新密碼:</span>
           <input type="password" class="form-control" required title="只能輸入5~12個英數字" pattern="[0-9]{5,12}$"  name="pwd" value="">
    	</div>
    	<div class="form-group">
           <span >確認新密碼:</span>
           <input type="password" class="form-control" required title="只能輸入5~12個英數字" pattern="[0-9]{5,12}$"  value="">
    	</div>
    	<input type="hidden" name="adm_no" value="${admVO.adm_no}">
		<input type="hidden" name="action" value="updatePwd">
		<input type="submit" class="btn btn-info " value="送出新增">
</FORM>
   	


<script language="javascript"> 
	function check() 
	{ 
	if ((document.form9.elements[1].value)!=(document.form9.elements[2].value)){ 
	alert("確認新密碼不一致"); 
	return false; 
	} 
	return true; 
	} 
</script>



<br><br><br>
<br><br><br><br><br><br>

<br><br><br><br>

</div>   
<%@ include file="/Back_end/pages/backFooter.file" %>