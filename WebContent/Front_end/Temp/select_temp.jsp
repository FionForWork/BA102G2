<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
#tempList {
	border-collapse: collapse; /* Collapse borders */
	width: 100%; /* Full-width */
	border: 1px solid #ddd; /* Add a grey border */
	font-size: 16px; /* Increase font-size */
}

#tempList th, #tempList td {
	text-align: left; /* Left-align text */
	padding: 12px; /* Add padding */
}

#tempList tr {
	/* Add a bottom border to all table rows */
	border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
	/* Add a grey background color to the table header and on hover */
	background-color: #f1f1f1;
}
</style>
</head>

<body>

<jsp:useBean id="tempSvc" scope="page" class="com.temp.model.TempService"/>
<% 
	//String com_no = (String)session.getAttribute("com_no");  
	String com_no = "2001";
	session.setAttribute("com_no",com_no);

%>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-9">
				<table class="table table-hover table-responsive" id="tempList">
					<caption>成品挑選清單</caption>
					<thead>
						<tr>
							<th>#</th>
							<th>待挑選作品</th>
							<th>廠商名稱</th>
							<th>可挑選張數</th>
							<th>拍攝時間</th>
							<th>狀態</th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="tempVO" items="${tempSvc.getAllByComNo(com_no)}" varStatus="s">
						<tr>
					
							<td>${s.count}</td>
							<td>${tempVO.name }</td>
							<td>超完美婚攝公司</td>
							<td>${tempVO.available}</td>
							<td>${tempVO.create_date}</td>
							<td>${tempVO.status}</td>
							<td><input type='submit' value='查看' name='displayTemp'></td>
							<td><input type='submit' value='修改' name='updateTemp'></td>
							<td><input type='submit' value='刪除' name='deleteTemp'></td>
						</tr>
					</c:forEach>	
											
					</tbody>
				</table>
			</div>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>