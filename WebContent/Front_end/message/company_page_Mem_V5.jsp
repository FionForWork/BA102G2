<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.works.model.*"%>
<%@ page import="com.com.model.*"%>
<%@ page import="com.serv.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.message.model.*"%>
<%@ page import= "org.json.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
// 	ComService comSvc1 = new ComService();
// 	ComVO comVO1 = comSvc1.getOneCom("2003");
// 	session.setAttribute("comVO", comVO1);
	
	MemService memSvc = new MemService();
	MemVO memVO1 = memSvc.getOneMem("1001");
	session.setAttribute("memVO", memVO1);
	
	WorksService worksSvc = new WorksService();
	List<WorksVO> worksList = worksSvc.getAllByComNo(request.getParameter("com_no"));
	pageContext.setAttribute("worksList", worksList);

	ServService servSvc = new ServService();
	List<ServVO> servList = servSvc.getAll();
	pageContext.setAttribute("servList", servList);
	
	MessageService messageSvc = new MessageService();
	List<String> messageList = messageSvc.getMessageByMem_no("1001");
	pageContext.setAttribute("messageList", messageList);
	
	ComService comSvc2 = new ComService();
	ComVO comVO2 = comSvc2.getOneCom("2001");
	pageContext.setAttribute("comVO2", comVO2);
	
%>

<html>
<head>
<link href="css/message_V2.css" rel="stylesheet">
<script src="js/message.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body onload="connect('${memVO != null? memVO.mem_no : comVO.com_no}');" onunload="disconnect();">
	
		<!--預約按鈕-->
		<div class="col-sm-2">
			<p class="text-center" id="open_chat">
				<a class="btn btn-reservation btn-lg">立即聯絡我們 </a>
			</p>
			<br>
		</div>	
	<!--預約按鈕-->

	
	<!--聯絡我們-->
	
		
		<div class="msg_box_wrap">
			<div class="add_ask_view" style="height: 400px; width: 330px; right: 80px;">
				<div class="chat_box">
					<table style="width: 100%; height: 100%;">
						<tbody>
							<tr>
								<td class="chat_title chat_title_open_color ani-show">
									<h2 class="chat_h2"><span target="_blank" class="the_studio"><a href="廠商頁面網址">廠商名稱</a></span>
									<a href="javascript:;" onclick="ask_msg_view_close();" class="close_it">X</a></h2>
								</td>
							</tr>
							<tr>
								<td>
									<div class="v_chat_room"><div class="chat_time">2017/08/17</div>
									 <div class="business_chat clearfix">
										<a href="廠商頁面網址" target="_blank" class="def_pic" style="">
										<img src="廠商LOGO">
										</a>
										<span class="arrow_l_int" style=""></span>
										<p class="defautl_msg" style="">
											您好:<br>
											請留下您的<br>
											1.預計結婚日期<br>
											2.地點<br>
											3.需要的服務<br>
											4.聯繫方式<br>
											我們會立即回覆您。<br>
											<span class="on_time"> 16:17</span>
										</p>
									 </div>
									</div>
								</td>
							</tr>
							<tr>
								<td style="max-height:114px;">
									<div class="box_chat clearfix ask_msg_var">
									<div class="send_btn msg_descri" style="cursor:text;" contenteditable="true" 
									placeholder="輪入訊息 (Shift + Enter 換行)">
								
									</div>
									<a class="btn_send ask_msg_send" href="javascript:;" onclick="ask_msg_send();">送出
									</a>
									<span style="display:none;">未輸入訊息
									</span>
									<div style="clear: both">
								
									</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		
	
	
	<!--聯絡我們-->
	<%@ include file="page/message_script.file"%>

</body>
</html>