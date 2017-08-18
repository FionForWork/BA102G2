
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="tempSvc" scope="page"
	class="com.temp.model.TempService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<%
	//String com_no = (String)session.getAttribute("com_no");  
	String com_no = "2001";
	session.setAttribute("com_no", com_no);
%>



<%@ include file="page/temp_com_header.file"%>
<style type="text/css">
.table-hover>tbody>tr:hover {
	background-color: rgb(247, 209, 211);
}
</style>

<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->
<div class="container">
	<div class="col-md-offset-1">
		<ul class="breadcrumb">
			<li><a href="#">首頁</a></li>
			<li><a href="#">廠商專區</a></li>
			<li class="active">作品挑選管理</li>
		</ul>
	</div>
</div>
<!--麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑麵包屑-->



<div class="container">
	<div class="row">
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
		<div class="col-md-offset-1 col-md-2">
			<ul class="list-group">
				<a href="<%=request.getContextPath()%>/Front_end/com/updatecompany.jsp" class="list-group-item menua">編輯廠商資料</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/com/updatePwd.jsp" class="list-group-item menua">修改密碼</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/reservation/comReservation.jsp" class="list-group-item menua">預約紀錄查詢</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/quote/listMyQuote.jsp" class="list-group-item menua">報價紀錄查詢</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTemps.jsp" class="list-group-item menua active">作品挑選管理</a><br>
                <a href="<%= request.getContextPath() %>/Front_end/calendar/calendar.jsp" class="list-group-item menua">行事曆</a><br>
                <a href="<%=request.getContextPath()%>/Front_end/Works/ListAllWorks.jsp" class="list-group-item menua">作品管理</a><br>
			</ul>


			<a href="#" class="btn btn-block btn-default">查看廠商資料</a>
		</div>
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

		<!--這裡開始===========================================================================-->



		<div class="col-md-8 col-offset-1">

			<ul class="nav nav-tabs nav-justified" role="tablist">
				<li class="active"><a data-toggle="tab" href="#unselect">未挑選</a></li>
				<li><a data-toggle="tab" href="#selected">已挑選</a></li>
			</ul>
			<br>
			<div class="tab-content" style="border:0">
			<!-- Unselect Section -->
				<div id="unselect" class="tab-pane fade in active">

					<table class="table table-hover table-responsive tempList">
						<thead>
							<tr>
								
								<th>成品名稱</th>
								<th>會員名稱</th>
								<th>可挑選數量</th>
								<th>拍攝時間</th>
								<th>狀態</th>
								<th colspan="3" align="center">
									<button class="btn btn-info btn-block" name='createTemp'
										onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Temp/ComPage_CreateTemp.jsp'">新增成品</button>
								</th>

							</tr>
						</thead>

						<tbody>
							<c:forEach var="tempVO" items="${tempSvc.getAllByComNo(com_no)}"
								varStatus="s">
								<c:if test='${tempVO.status.equals("未挑選")}'>

									<!-- Start Modal delete temp -->
									<div class="modal fade" id="deleteModal${s.count}"
										role="dialog">
										<div class="modal-dialog">

											<!-- Modal content-->
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">刪除成品</h4>
												</div>
												<div class="modal-body">
													<p>你確定想刪除「 ${tempVO.name} 」嗎？在這本成品中的相片也會被刪除。</p>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">取消</button>
													<button class="btn btn-danger" data-dismiss="modal"
														onclick="document.getElementById('delete${s.count}').submit();">刪除</button>
												</div>
											</div>

										</div>
									</div>
									<!--  End Modal Delete temp -->


									<tr>
										
										<td>${tempVO.name }</td>
										<td>${memSvc.getOneMem(tempVO.mem_no).name}</td>
										<td>${tempVO.available}</td>
										<td>${tempVO.create_date.toString().substring(0,10)}</td>

										<td>${tempVO.status}</td>
										<td><button class="btn btn-default" type='submit' name='displayTemp' onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTempConts.jsp?temp_no=${tempVO.temp_no}'">查看</button></td>
										<td><button class="btn btn-default" type='submit' name='updateTemp' onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Temp/ComPage_UpdateTemp.jsp?temp_no=${tempVO.temp_no}'">修改</button></td>
										<form action="<%=request.getContextPath()%>/temp/temp.do"
											method="post" id="delete${s.count}">
											<input type='hidden' name='action' value='delete_Temp'>
											<input type='hidden' name='temp_no' value='${tempVO.temp_no}'>
										<td><button type='button' class="btn btn-default"
													data-toggle="modal" data-target="#deleteModal${s.count}">刪除</button></td>
										</form>

									</tr>

								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>




<br>
<!-- Selected Section -->
				<div id="selected" class="tab-pane fade">
					<table class="table table-hover table-responsive tempList">
						<thead>
							<tr>
								<th>成品名稱</th>
								<th>會員名稱</th>
								<th>可挑選數量</th>
								<th>拍攝時間</th>
								<th>狀態</th>
								<th colspan="2" align="center"></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="tempVO" items="${tempSvc.getAllByComNo(com_no)}"
								varStatus="s">
								<c:if test='${tempVO.status.equals("已挑選")}'>


									<!-- Start Modal Transfer Temp -->
									<div class="modal fade" id="transferModal${s.count}"
										role="dialog">
										<div class="modal-dialog">

											<!-- Modal content-->
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">匯入會員相簿</h4>
												</div>
												<div class="modal-body">
													<p>你確定想將「 ${tempVO.name} 」匯入到會員相簿嗎？ 匯入後此筆成品將會從清單中移除。</p>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">取消</button>
													<button class="btn btn-danger" data-dismiss="modal"
														class='transferConfirm'
														onclick="document.getElementById('transfer${s.count}').submit();">確定</button>
												</div>
											</div>

										</div>
									</div>
									<!--  End Modal Transfer Temp -->

									<tr>
										
										<td>${tempVO.name}</td>
										<td>${memSvc.getOneMem(tempVO.mem_no).name}</td>
										<td>${tempVO.available}</td>
										<td>${tempVO.create_date.toString().substring(0,10)}</td>

										<td>${tempVO.status}</td>
										<td><button class="btn btn-default" type='submit' name='displayTemp' onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTempConts.jsp?temp_no=${tempVO.temp_no}'">查看</button></td>
										<form action="<%=request.getContextPath()%>/temp/temp.do" method="post" id='transfer${s.count}'>
											<input type='hidden' name='action' value='Transfer_Temp'>
											<input type='hidden' name='temp_no' value='${tempVO.temp_no}'>
										</form>
										<td><button class='btn btn-default' data-toggle="modal"
												data-target="#transferModal${s.count}">匯入會員相簿</button></td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function doAjax(action,temp_no){
		$.ajax({
			url:'<%=request.getContextPath()%>/temp/temp.do',
			type:'POST',
			data:{
				temp_no : temp_no,
				action : action
			},
			success:function success(){
				
			},
			error:function(xhr){
				alert('Ajax request error!');
			}
		});
	}
</script>
<%@ include file="page/temp_footer.file"%>
