<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.album.model.*"%>

<%@ include file="page/photo_header.file"%>

<jsp:useBean id="contSvc" scope="page"
	class="com.content.model.ContentService"></jsp:useBean>
<%-- <jsp:useBean id="albSvc" scope="page" --%>
<%-- 	class="com.album.model.AlbumService"></jsp:useBean> --%>

<%
	String alb_no = (String) request.getParameter("alb_no");
	//String alb_no = "0012";
	pageContext.setAttribute("alb_no", alb_no);
	AlbumService albSvc = new AlbumService();
	AlbumVO alb = albSvc.getOneAlbum(alb_no);
	pageContext.setAttribute("alb", alb);
%>
<script type="text/javascript">
$("document").ready(function(){
	$("#datePicker").datepicker({dateFormat: 'yy-mm-dd',maxDate: "+0D"});
});
</script>
		<div class="col-md-8 col-offset-1">

			<!-- Modal addContent -->
			<form action="<%=request.getContextPath()%>/content/content.do"
				method="post" enctype="multipart/form-data">
				<div class="modal fade" id="uploadModal" role="dialog">
					<div class="modal-dialog modal-lg">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" style="padding: 20px 50px;">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4>
									<span class="glyphicon glyphicon-picture"></span> 上傳照片或影片
								</h4>
							</div>
							<div class="modal-body" style="padding: 40px 50px;">
								<div class="form-group">
									<label for="inputFile"> 選擇照片或影片 <span id='emptyFile' style='color:red'></span></label> 
									<input id="inputFile" name="inputFile[]" type="file" multiple class="file-loading">
								</div>
								<input type='button' id='insertBtn' class="btn btn-info btn-block" value="新增">
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-default pull-left"
									data-dismiss="modal">
									<span class="glyphicon glyphicon-remove"></span> 取消
								</button>
								<input type='hidden' name='action' value='insert_Content'>
								<input type='hidden' name='alb_no' value='<%=alb_no%>'>
							</div>
						</div>
					</div>
				</div>
			</form>
			<!-- End Modal addContent -->

			<!-- Start Modal delete alb -->
			<div class="modal fade" id="deleteModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">刪除相簿</h4>
						</div>
						<div class="modal-body">
							<p>你確定想刪除「 ${alb.name} 」嗎？在這本相簿中的相片及影片也會被刪除。</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal"
								id='deletebtn'
								onclick="document.getElementById('deleteAlb').submit();">刪除</button>
						</div>
					</div>

				</div>
			</div>
			<!--  End Modal Delete Alb -->
			
			
			<!-- The lightbox Modal (img)-->
			<div id="lightboxImgModal" class="modal">
				<span class="closeImg" onclick='closeLightBox()'>&times;</span> <img
					class="lightbox-modal-content" id="lightboxImg">
			</div>
			<!-- The lightbox Modal (img)-->



			<!-- Modal delete Content -->
				<div class="modal fade" id="deleteContentModal" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">刪除相片或影片</h4>
							</div>
							<div class="modal-body">
								<p>刪除後將無法復原，確定刪除嗎?</p>
							</div>
							<input type='hidden' name='cont_no' value=''>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" id='cancel'>取消</button>

								<button type="button" class="btn btn-danger"
									data-dismiss="modal" id='deletebtn' onclick="javascript:deleteCont()">刪除</button>

							</div>
						</div>

					</div>
				</div>
				<!--  End Modal Delete Content -->
				
			
			
			<div class="jumbotron">
				<div class="row">

					<form action="<%=request.getContextPath()%>/album/album.do"
						method="post" id='updateAlb'>
						<h2>
							<div class="input-group">
								<label for="name" class="input-group-addon"> 相簿名稱 <span
									class='errorMsgs'> ${errorMsgs.get("name")}</span></label> <input
									type='text' id="name" name='name' value='${alb.name}'
									class="form-control">
							</div>
						</h2>
						<h4>
							<div class="input-group">
								<label for="datePicker" class="input-group-addon"> 建立日期
									<span class='errorMsgs'> ${errorMsgs.get("create_date")}</span>
								</label> <input type='text' name='create_date' class="form-control"
									id="datePicker" value='${alb.create_date.toString().substring(0,10)}'>
							</div>
						</h4>
						<input type='hidden' name='action' value='update_Album'> <input
							type='hidden' name='alb_no' value='${alb_no}'>
					</form>
					<form action="<%=request.getContextPath()%>/album/album.do"
						method="post" id='deleteAlb'>
						<input type='hidden' name='action' value='delete_Album'> <input
							type='hidden' name='alb_no' value='${alb_no}'>
					</form>

					<div class='btn-group'>
						<button class='btn btn-info'
							onclick="document.getElementById('updateAlb').submit();">儲存</button>
						<button class="btn btn-info" id="uploadbtn">新增相片</button>
						<button class='btn btn-default' data-toggle="modal"
							data-target="#deleteModal">刪除</button>
					</div>
				</div>
			</div>



			<div id='changeContent'>
			<c:forEach var="contVO" items="${contSvc.getAllByAlbNo(alb_no)}"
				varStatus="s">
				<c:if test="${(s.count % 4) == 1}">
					<div class="row">
				</c:if>
				<div class="col-md-3 col-sm-3 col-xs-6">
					<div class="image-container">
						<c:if test="${contVO.img == null}">
							<video
									width="400" controls class="img-responsive img-thumbnail">
									<source
										src="<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=${contVO.cont_no }"
										type="video/mp4">
									您的瀏覽器不支援此撥放程式
								</video>
							
						</c:if>
						<c:if test="${contVO.img != null}">
							<img
								class="img-responsive img-thumbnail aa" onclick='openLightBox(this)'
								src="<%=request.getContextPath()%>/ShowPictureServletDAO?cont_no=${contVO.cont_no}" />
							
						</c:if>

						<div class="overlap dropdown">
							<button class="btn btn-default btn-xs" type="submit"
								class='dropbtn'>
								<i class="fa fa-cog" aria-hidden="true"></i>
							</button>
							<div class='dropdownContent' id='dropdownContent${s.count}'>
									<a href='<%=request.getContextPath()%>/ShowPictureServletDAO?downloadCont_no=${contVO.cont_no}' id='download'>下載</a>
									<a href='#' id='setCover' onclick="doAjax('setCover','${contVO.cont_no}');">設成封面</a>
									<a data-toggle="modal" onclick="javascript:openDeleteContModal('${contVO.cont_no}')">刪除相片</a>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${(s.count % 4) == 0}">
		</div>
		</c:if>
		</c:forEach>
		</div>
		<br>
	</div>
<script type="text/javascript">
	
	// delete content
	function doAjax(action,cont_no){
		$.ajax({
			url:'<%=request.getContextPath()%>/content/content.do',
			type:'POST',
			data:{
				alb_no :'<%=alb_no%>',
				cont_no : cont_no,
				action : action
			},
			success:function success(){
				$("#changeContent").load("<%=request.getContextPath()%>/Front_end/Album/UpdateAlbum.jsp #changeContent",{
					alb_no :'<%=alb_no%>'
				});
			},
			error:function(xhr){
				alert('Ajax request error!');
			}
		});
	}
	function openDeleteContModal(cont_no){
		$("#deleteContentModal").modal();
		$('input[name=cont_no]').val(cont_no);
	}
	function deleteCont(){
		$("#cancel").click();
		var cont_no = $('input[name=cont_no]').val();
		doAjax('delete_Content',cont_no);
		
	}
	
</script>
	<%@ include file="page/album_footer.file"%>
