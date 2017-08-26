<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.com.model.*"%>
<jsp:useBean id="tempSvc" scope="page"
	class="com.temp.model.TempService" />
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService" />

<%
	ComVO comVO = (ComVO) session.getAttribute("comVO");
	System.out.println("comVO" + comVO);
	//String com_no = (String)session.getAttribute("com_no");  
	//String com_no = "2001";
	//session.setAttribute("com_no", com_no);
%>



<%@ include file="page/temp_com_header.file"%>
<style type="text/css">
.table-hover>tbody>tr:hover {
	background-color: rgb(247, 209, 211);
}
</style>

<div class="col-md-8 col-offset-1">

	<!-- Start Modal delete temp -->
	<div class="modal fade" id="deleteTempModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						id='cancelDeleteModal'>&times;</button>
					<h4 class="modal-title">刪除成品</h4>
				</div>
				<div class="modal-body">
					<p>你確定想刪除「 ${tempVO.name} 」嗎？在這本成品中的相片及影片也會被刪除。</p>
				</div>
				<input type='hidden' name='temp_no' value=''>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id='cancelDeleteModal'>取消</button>
					<button class="btn btn-danger" data-dismiss="modal"
						onclick="deleteTemp()">刪除</button>
				</div>
			</div>

		</div>
	</div>
	<!--  End Modal Delete temp -->
	<!-- Start Modal Transfer Temp -->
	<div class="modal fade" id="transferTempModal" role="dialog">
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
				<input type='hidden' name='temp_no' value=''>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id='cancelTransferModal'>取消</button>
					<button class="btn btn-danger" data-dismiss="modal"
						class='transferConfirm' onclick="transferTemp()">確定</button>
				</div>
			</div>

		</div>
	</div>
	<!--  End Modal Transfer Temp -->

<div id="snackbar">已成功匯入會員相簿囉...</div>
	<div id='changeContent'>
		<ul class="nav nav-tabs nav-justified" role="tablist">
			<li class="active"><a data-toggle="tab" href="#unselect">未挑選</a></li>
			<li><a data-toggle="tab" href="#selected" id='selectedBtn'>已挑選</a></li>
		</ul>
		<br>

		<div class="tab-content" style="border: 0">

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
						<c:forEach var="tempVO"
							items="${tempSvc.getAllByComNo(comVO.com_no)}" varStatus="s">
							<c:if test='${tempVO.status.equals("未挑選")}'>



								<tr>

									<td>${tempVO.name }</td>
									<td>${memSvc.getOneMem(tempVO.mem_no).name}</td>
									<td>${tempVO.available}</td>
									<td>${tempVO.create_date.toString().substring(0,10)}</td>

									<td>${tempVO.status}</td>
									<td><button class="btn btn-default" type='submit'
											name='displayTemp'
											onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTempConts.jsp?temp_no=${tempVO.temp_no}'">查看</button></td>
									<td><button class="btn btn-default" type='submit'
											name='updateTemp'
											onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Temp/ComPage_UpdateTemp.jsp?temp_no=${tempVO.temp_no}'">修改</button></td>
									<form action="<%=request.getContextPath()%>/temp/temp.do"
										method="post" id="delete${s.count}">
										<input type='hidden' name='action' value='delete_Temp'>
										<input type='hidden' name='temp_no' value='${tempVO.temp_no}'>
										<td><button type='button' class="btn btn-default"
												onclick="openDeleteTempModal('${tempVO.temp_no}','${tempVO.name}')"">刪除</button></td>
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
						<c:forEach var="tempVO"
							items="${tempSvc.getAllByComNo(comVO.com_no)}" varStatus="s">
							<c:if test='${tempVO.status.equals("已挑選")}'>

								<tr>

									<td>${tempVO.name}</td>
									<td>${memSvc.getOneMem(tempVO.mem_no).name}</td>
									<td>${tempVO.available}</td>
									<td>${tempVO.create_date.toString().substring(0,10)}</td>

									<td>${tempVO.status}</td>
									<td><button class="btn btn-default" type='submit'
											name='displayTemp'
											onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTempConts.jsp?temp_no=${tempVO.temp_no}'">查看</button></td>
									<form action="<%=request.getContextPath()%>/temp/temp.do"
										method="post" id='transfer${s.count}'>
										<input type='hidden' name='action' value='Transfer_Temp'>
										<input type='hidden' name='temp_no' value='${tempVO.temp_no}'>
									</form>
									<td><button class='btn btn-default'
											onclick="openTransferTempModal('${tempVO.temp_no}','${tempVO.name}')">匯入會員相簿</button></td>
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
				
				$("#changeContent").load("<%=request.getContextPath()%>/Front_end/Temp/ComPage_ListAllTemps.jsp #changeContent",{
					"com_no":${comVO.com_no}
				});
				if("Transfer_Temp" === action){
					$("#selectedBtn").click();
					$("#snackbar").addClass("show");
					setTimeout('$("#snackbar").removeClass("show")',5000);
				}
					
			},
			error : function(xhr) {
				alert('Ajax request error!');
			}
		});
	}
	// Delete
	function openDeleteTempModal(temp_no, name) {

		$("#deleteTempModal").modal();
		$('input[name=temp_no]').val(temp_no);
		$('.modal-body p').html("你確定想刪除「" + name + " 」嗎？在這本成品中的相片及影片也會被刪除。");
	}
	function deleteTemp() {
		var temp_no = $('input[name=temp_no]').val();
		doAjax('delete_Temp', temp_no);
		$("#cancelDeleteModal").click();
	}

	// Transfer
	function openTransferTempModal(temp_no, name) {

		$("#transferTempModal").modal();
		$('input[name=temp_no]').val(temp_no);
		$('.modal-body p').html(
				"你確定想將「" + name + " 」匯入到會員相簿嗎？ 匯入後此筆成品將會從清單中移除。");
	}
	function transferTemp() {
		var temp_no = $('input[name=temp_no]').val();
		doAjax('Transfer_Temp', temp_no);
		$("#cancelTransferModal").click();
	}
</script>
<%@ include file="page/temp_footer.file"%>
