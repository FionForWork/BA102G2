<%@page import="com.advertising.model.*"%>
<%@page import="com.com.model.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	// ComVO comVO = (ComVO) session.getAttribute("comVO");
	// session.setAttribute("comVO", comVO);
	ComService comService = new ComService();
	ComVO comVO = comService.getOneCom("2001");
%>
<%
	AdvertisingService advSvc = new AdvertisingService();
	List<AdvertisingVO> list = (List<AdvertisingVO>) advSvc.getOneAll(comVO.getCom_no());
	pageContext.setAttribute("list", list);
%>

<%@ include file="page/advertising_header.file"%>

<div class="col-md-8 col-offset-1">
	<div class="container-fluid">
		<div>
			<h2>
				已申請廣告清單
				<div class="text-center" style="float: right">
					<input type="submit" class="btn btn-info " data-toggle="modal"
						data-target="#myModal" value="廣告申請">
				</div>
			</h2>

		</div>
		<!-- ********************************************************** -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<form METHOD="post" enctype="multipart/form-data"
					ACTION="<%=request.getContextPath()%>/advertising/advertising.do"
					name="form1">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">廣告申請</h4>
						</div>

						<div class="modal-body">
							<label for="inputdefault">標題</label> <input class="form-control"
								id="inputdefault" type="text" name="title">
						</div>
						<div class="modal-body">
							起始日期 <input type="text" id="startDay" name="startDay" /> 結束日期 <input
								type="text" id="endDay" name="endDay" />

						</div>
						<div>
							<p id="datetotal"></p>
						</div>
						<div>
							<input type="button" class="btn btn-info "
								onclick="Date_substr();" value="試算金額" />
						</div>


						<div class="modal-body">
							<label for="inputdefault">內容</label> <input class="form-control"
								id="inputdefault" type="text" name="text">
						</div>

						<div class="modal-body">
							<label for="inputdefault">上傳圖片</label> 
							<input type="file" id="imgInp" name="img">
							<img id="blah" src="#"  />
						</div>
						<div class="modal-footer">

							<input type="hidden" name="com_no" value="<%=comVO.getCom_no()%>">
							<input type="hidden" name="action" value="add"> <input
								type="hidden" name="status" value="0"> <input
								type="submit" class="btn btn-info" value="發佈">

							<button type="button" class="btn btn-danger" data-dismiss="modal">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<!-- ===================================================================================== -->







		<p></p>
		<table class="table table-striped ">
			<thead>
				<tr>
					<th>項次</th>
					<th>標題</th>
					<th>刊登時間</th>
					<th>結束時間</th>
					<th>刊登天數</th>
					<th>審核狀態</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="advertisingVO" items="${list}" varStatus="ddt">
					<tr>

						<td>${ddt.count }</td>
						<td><a
							href="/BA102G2/Front_end/Advertising/Advertising_Inquire.jsp?adv_no=${advertisingVO.adv_no }">${advertisingVO.title }</a></td>
						<td><fmt:formatDate type="both" pattern="yyyy-MM-dd "
								value="${advertisingVO.startDay}" /></td>
						<td><fmt:formatDate type="both" pattern="yyyy-MM-dd "
								value="${advertisingVO.endDay}" /></td>
<%-- 						<td>${(advertisingVO.endDay.time-advertisingVO.startDay.time)/(1000*60*60*24)}</td> --%>
	<td><fmt:formatNumber  pattern="#0" value="${(advertisingVO.endDay.time-advertisingVO.startDay.time)/(1000*60*60*24)}" /></td>

						<td>${(advertisingVO.status==1)?"已審核通過":"未審核通過"}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>
<script type="text/javascript">
$(function()
{
	$("#imgInp").change(function(){
		if (this.files && this.files[0]) {
			var reader = new FileReader();
			
			reader.onload = function (e) {
				$('#blah').attr('src', e.target.result);
			}
			
			reader.readAsDataURL(this.files[0]);
		}
	});
}) ;
</script>










<%@ include file="page/advertising_footer.file"%>