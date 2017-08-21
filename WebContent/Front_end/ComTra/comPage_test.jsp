<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.comtra.model.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="comSvc" scope="page" class="com.com.model.ComService" />
<%
//String mem_no = (String)session.getAttribute("mem_no");
		String mem_no = "1003";
		pageContext.setAttribute("mem_no", mem_no);
%>

<%@ include file="page/comtra_header.file"%>

<!--�ѥ]�h�ѥ]�h�ѥ]�h�ѥ]�h�ѥ]�h�ѥ]�h�ѥ]�h�ѥ]�h-->
<div class="container">
	<div class="col-md-offset-1">
		<ul class="breadcrumb">
			<li><a href="#">����</a></li>
			<li><a href="#">�|���M��</a></li>
			<li class="active">�ڪ��̷R</li>
		</ul>
	</div>
</div>
<!--�ѥ]�h�ѥ]�h�ѥ]�h�ѥ]�h�ѥ]�h�ѥ]�h�ѥ]�h�ѥ]�h-->

<div class="container">
	<div class="row">
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->
		<div class="col-md-offset-1 col-md-2">
			<ul class="list-group">
				<a href="#" class="list-group-item menua">�s��ӤH���</a>
				<br>
				<a href="#" class="list-group-item menua">�K�X�ק�</a>
				<br>
				<a href="#" class="list-group-item menua">�w�������d��</a>
				<br>
				<a href="#" class="list-group-item menua">���������d��</a>
				<br>
				<a href="#" class="list-group-item menua">�@�~�D��޲z</a>
				<br>
				<a href="#" class="list-group-item menua">�ڪ���ï</a>
				<br>
				<a href="#" class="list-group-item menua active">�ڪ��̷R</a>
				<br>
				<a href="#" class="list-group-item menua">�ӫ��M��</a>
				<br>
			</ul>


			<a href="#" class="btn btn-block btn-default">�d�ݭӤH���</a>
		</div>
		<!--sidebar sidebar sidebar sidebar sidebar sidebar -->

		<!--�o�̶}�l===========================================================================-->
		<!--���a�j�Y��-->
		<div class="com_head container">
			<img class="com_logo img-circle center-block" src="img/logo.jpg"
				alt="She Says Yes">
		</div>
		<!--���a�j�Y��-->

		<div class="text-center">
			<h1>�t�ӦW��</h1>
		</div>

		<div class="catalog hidden-xs">
			<ul class="list-inline">
				<li><a href="#info" title="�p����T"> �p����T </a></li>
				<li><a href="#album" title="�@�~"> �@�~ </a></li>
				<li><a href="#service" title="���"> ��� </a></li>
				<li><a href="#about" title="�����"> ����� </a></li>
			</ul>
		</div>
		<!--���a���-->
		<div class="container" id="info">
			<div class="col-md-8 col-offset-1">



				<div class="hidden-xs">
					<table class="table table-condensed">
						<tbody>
							<tr>
								<th>��~�ɶ�</th>
								<td>13:00 ~ 22:00</td>
							</tr>

							<tr>
								<th>�����</th>
								<td>�C�ӬP���T�T�w����</td>
							</tr>

							<tr>
								<th>�q��</th>
								<td><a href="">02-1234567</a></td>
							</tr>

							<tr>
								<th>�a�}</th>
								<td>XXX���G�qXXXXXX��</td>
							</tr>

							<tr>
								<th>�H�c</th>
								<td><a href="">XXXXXXXXXX@gmail.com</a></td>
							</tr>

							<tr>
								<th>Line ID</th>
								<td>@wth7964p</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!--////////////////////////////-->
			<!--�w�����s-->
			<div class="col-sm-3">
				<p class="text-center">
					<a class="btn btn-reservation btn-lg" href=""> �u�W�w�� </a>
				</p>
				<p class="text-center">
					<a class="btn btn-reservation btn-lg" id='insertComTra'> �[�J���� </a>
				</p>
				<input type='hidden' name='com_no' value='2020'> 
				<input
					type='hidden' name='mem_no' value='${mem_no}'> 
				<input
					type='hidden' name='path'
					value='<%=request.getContextPath()%>/comtra/comtra.do'>
			</div>
			<div id="snackbar">Some text some message..</div>
		</div>
		<!--���a���-->

		<!--���a��ï-->
		<div class="text-center" id="album">
			<span>
				<h1>�t�ӧ@�~</h1>
			</span>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-4">
					<ul class="album_box">
						<li class="list-unstyled"><a href="album.html"
							class="thumbnail thumbnail thumbnail-service mod-shadow img-label">
								<img class="album_image img-thumbnail" src="img/wedding1.jpg">
						</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-4">
					<ul class="album_box">
						<li class="list-unstyled"><a href="#"
							class="thumbnail thumbnail thumbnail-service mod-shadow img-label">
								<img class="album_image img-thumbnail" src="img/wedding1.jpg">
						</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-4">
					<ul class="album_box">
						<li class="list-unstyled"><a href="#"
							class="thumbnail thumbnail thumbnail-service mod-shadow img-label">
								<img class="album_image img-thumbnail" src="img/wedding1.jpg">
						</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="container text-center">
			<div class="row">
				<div class="col-xs-12 col-sm-3"></div>
				<div class="col-xs-12 col-sm-6">
					<a href="#">
						<div class="panel panel-default">
							<div class="panel-body">�d�ݩҦ����e</div>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-3"></div>
			</div>
		</div>
		<!--���a��ï-->

		<!--���a���-->
		<div class="text-center" id="service">
			<span>
				<h1>�t�Ӥ��</h1>
			</span>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-3">
					<ul class="service_box">
						<li class="service_title">��צW��</li>
						<li class="cost"><span>$NT</span> 81000</li>
						<li class="text">cillum dolore eu fugiat nulla pariatur.
							Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia deserunt mollit anim id est laborum.</li>
						<li class="check_service"><a href="#">�d�ݤ��</a></li>
						<li class="photo"><a href="#"> <img class="sercive_image"
								src="img/wedding6.jpg">
						</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-3">
					<ul class="service_box">
						<li class="service_title">��צW��</li>
						<li class="cost"><span>$NT</span> 81000</li>
						<li class="text">cillum dolore eu fugiat nulla pariatur.
							Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia deserunt mollit anim id est laborum.</li>
						<li class="check_service"><a href="#">�d�ݤ��</a></li>
						<li class="photo"><a href="#"> <img class="sercive_image"
								src="img/wedding6.jpg">
						</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-3">
					<ul class="service_box">
						<li class="service_title">��צW��</li>
						<li class="cost"><span>$NT</span> 81000</li>
						<li class="text">cillum dolore eu fugiat nulla pariatur.
							Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia deserunt mollit anim id est laborum.</li>
						<li class="check_service"><a href="#">�d�ݤ��</a></li>
						<li class="photo"><a href="#"> <img class="sercive_image"
								src="img/wedding6.jpg">
						</a></li>
					</ul>
				</div>
				<div class="col-xs-12 col-sm-3">
					<ul class="service_box">
						<li class="service_title">��צW��</li>
						<li class="cost"><span>$NT</span> 81000</li>
						<li class="text">cillum dolore eu fugiat nulla pariatur.
							Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
							officia deserunt mollit anim id est laborum.</li>
						<li class="check_service"><a href="#">�d�ݤ��</a></li>
						<li class="photo"><a href="#"> <img class="sercive_image"
								src="img/wedding6.jpg">
						</a></li>
					</ul>
				</div>
			</div>
		</div>


		<div class="container text-center">
			<div class="row">
				<div class="col-xs-12 col-sm-3"></div>
				<div class="col-xs-12 col-sm-6">
					<a href="#">
						<div class="panel panel-default">
							<div class="panel-body">�d�ݩҦ����e</div>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-3"></div>
			</div>
		</div>
		<!--���a���-->

		<!--���a�v��-->
		<!--���a�v��-->

		<!--���a�ۤ�-->
		<div class="text-center" id="introduction">
			<span>
				<h1>�t�Ӧۤ�</h1>
			</span>
		</div>
		<div class="com_intro container">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<div class="col-xs-12 col-sm-4"></div>
					<div class="col-xs-12 col-sm-8">�򥻤��СG
						�q�j���즨���A�q�������{�A�k�İ��F�@�ͤ��̬��B�̭��n���M�w�C �ڭ̤��Q�ȶȰ��@�ӱB����v�u�@�ǡA�ڭ̧Ʊ��z�L�@�ӭӪ��e���A
						���X�C�Ӥk�ġA�q�̪쪺���ʨ�̬������u�A�o�@�����I�w�P�P�ʡC</div>
				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="col-xs-12 col-sm-4">���ѪA�ȡG �ۧU�B�� �B���]�M</div>
					<div class="col-xs-12 col-sm-8"></div>
				</div>
			</div>
		</div>

		<!--���a�ۤ�-->


		<br> <br> <br> <br> <br> <br>
		<!--menu-->



	</div>
	<!--�W����F��===========================================================================-->
</div>



<%@ include file="page/comtra_footer.file"%>