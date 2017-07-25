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


<%@ include file="page/photo_header.file"%>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-9">
			<table class="table table-hover table-responsive" id="tempList">
				<caption>成品清單</caption>

				<thead>
					<tr>
						<th>#</th>
						<th>成品名稱</th>
						<th>會員名稱</th>
						<th>可挑選數量</th>
						<th>拍攝時間</th>
						<th>狀態</th>
						<th colspan="4" align="center"><button
								class="btn btn-info btn-block" name='createTemp'
								onclick="javascript:location.href='<%=request.getContextPath()%>/Front_end/Temp/ComPage_CreateTemp.jsp'">新增成品</button></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="tempVO" items="${tempSvc.getAllByComNo(com_no)}" varStatus="s">
					<!-- Modal delete temp -->
						<div class="modal fade" id="deleteModal${s.count}" role="dialog">
							<div class="modal-dialog">

								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">刪除成品</h4>
									</div>
									<div class="modal-body">
										<p>你確定想刪除嗎？在這本成品中的相片也會被刪除。</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button class="btn btn-danger"
											data-dismiss="modal" onclick="document.getElementById('delete${s.count}').submit();">刪除</button>
									</div>
								</div>

							</div>
						</div>
						<!--  End Modal Delete temp -->
						<tr>
							<td>${s.count}</td>
							<td>${tempVO.name }</td>
							<td>${memSvc.getOneMem(tempVO.mem_no).name}</td>
							<td>${tempVO.available}</td>
							<td>${tempVO.create_date.toString().substring(0,10)}</td>
							<td>${tempVO.status}</td>
							<td>
								<form action="<%=request.getContextPath()%>/temp/temp.do"
									method="post">
									<input type='hidden' name='action' value='getOne_For_Display'>
									<input type='hidden' name='temp_no' value='${tempVO.temp_no}'>
									<button class="btn btn-default" type='submit' id='displayTemp'
										name='displayTemp'>查看</button>

								</form>
							</td>
							<form
								action="<%=request.getContextPath()%>/Front_end/Temp/ComPage_UpdateTemp.jsp" method="post">
								<input type='hidden' name='temp_no' value='${tempVO.temp_no}'>
								<td><button class="btn btn-default" type='submit'
										name='updateTemp'>修改</button></td>
							</form>
							<form action="<%=request.getContextPath()%>/temp/temp.do" method="post" id="delete${s.count}">
								<input type='hidden' name='action' value='delete_Temp'>
								<input type='hidden' name='temp_no' value='${tempVO.temp_no}'>
								<td><button type='button' class="btn btn-default" data-toggle="modal" data-target="#deleteModal${s.count}">刪除</button></td>
							</form>
							<td><button class='btn btn-default'>匯入會員相簿</button>	
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>



<%@ include file="page/album_footer.file"%>
