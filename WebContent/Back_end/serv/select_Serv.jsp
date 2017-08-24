
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ include file="/Back_end/adm/page/backHeader.file"%>
<div id="sidebar">

        <div class="sidebar-inner">
  
            <ul id="sideNav" class="nav nav-pills nav-stacked">
				 <c:if test="${aut.contains(\"02\")}">
                <li><a href="<%=request.getContextPath()%>/Back_end/mem/select_member.jsp" ><i class="en-users"></i>一般會員管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"03\")}">
				<li><a href="<%=request.getContextPath() %>/Back_end/com/listAllCom.jsp" class='active'><i class="en-users"></i>廠商管理 </a></li>
				</c:if>
				<c:if test="${aut.contains(\"01\")}">
				<li><a href="<%=request.getContextPath()%>/Back_end/adm/listAllAdm.jsp" ><i class="en-users "> </i>管理者設定 </a></li>
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
<title>服務搜尋</title>

<br><br><br>
<div id="content" style="padding:20px 1px 1px 20px;" ><h2>服務搜尋</h2></div>
<div id="content">
<a href='<%=request.getContextPath()%>/Back_end/serv/listAllServ.jsp'>觀看全部服務</a>  <br><br>
<table class="table table-striped">

	<tr>
		<td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/serv/serv.do" >
        <b>根據分類 :</b></td>
        <td>
        	<select style="width: 223px;"  name="stype_no">
        	 	<option value="0001">拍婚紗</option>
        	 	<option value="0002">婚設婚錄</option>
        	 	<option value="0003">新娘秘書</option>
        </td>
        <td><input type="submit" class="btn btn-info" value="送出"></td>
        <input type="hidden" name="action" value="selectByStype">
		</FORM>
		
	</tr>


<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService" />
   
	<tr>
	<td>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/serv/serv.do" >
       <b>根據廠商:</b></td>
      <td> <select style="width: 223px;" id="select1" name="com_no">
         <c:forEach var="comVO" items="${comSvc.all}" > 
          <option value="${comVO.com_no}">${comVO.com_no}-${comVO.name}</option>
         </c:forEach>   
       </select></td>
      <td><input type="submit" class="btn btn-info" value="送出"></td>
      	<input type="hidden" value="activate selectator" id="activate_selectator1" type="button">
       <input type="hidden" name="action" value="selectByCom">
    </FORM>

	</tr>
	

     <jsp:useBean id="servSvc" scope="page" class="com.serv.model.ServService" />
   
	<tr>
	<td>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/serv/serv.do" >
       <b>根據編號:</b></td>
      <td> <select style="width: 223px;" id="select2" name="serv_no">
         <c:forEach var="servVO" items="${servSvc.all}" > 
          <option value="${servVO.serv_no}">${servVO.com_no}-${servVO.title}</option>
         </c:forEach>   
       </select></td>
      <td><input type="submit" class="btn btn-info" value="送出"></td>
 <input type="hidden" value="activate selectator" id="activate_selectator2" type="button">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>

	</tr>
   
    

	
</table>
</div>

<link rel="stylesheet" href="<%=request.getContextPath()%>/Back_end/mem/assets/css/fm.selectator.jquery.css"/>
	<style>
	
		#wrapper {
			padding: 15px;
		}
		#select1 {
			width: 250px;
			padding: 7px 10px;
		}
		
	</style>
	<script src="<%=request.getContextPath()%>/Back_end/mem/assets/JS/jquery-1.11.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/Back_end/mem/assets/JS/fm.selectator.jquery.js"></script>
	<script>
		$(function () {
			var $activate_selectator1 = $('#activate_selectator1');
			$activate_selectator1.click(function () {
				var $select1 = $('#select1');
				
					$select1.selectator({
						labels: {
							search: 'Search here...'
						}
					});
					
			});
			$activate_selectator1.trigger('click');

		});
		$(function () {
			var $activate_selectator2 = $('#activate_selectator2');
			$activate_selectator2.click(function () {
				var $select2 = $('#select2');
				
					$select2.selectator({
						labels: {
							search: 'Search here...'
						}
					});
					
			});
			$activate_selectator2.trigger('click');

		});
	</script>

<%@ include file="/Back_end/pages/backFooter.file"%>