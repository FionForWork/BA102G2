<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="">
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
		
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$("document").ready(function(){
		$("#insertComTra").click(function(){
			$.ajax({
				url:$('input[name="path"]').val(),
				type:'POST',
				data:{
					com_no : $('input[name="com_no"]').val(),
					mem_no : $('input[name="mem_no"]').val(),
					action : "insert_ComTra"
				},
				success:function success(){
					$("#snackbar").addClass("show");
					console.log("XXXXXXX");
					setTimeout('$("#snackbar").removeClass("show")',5000);
					console.log("@@@@@@@@@@");
				},
				error:function(xhr){
					alert('Ajax request error!');
				}
			});
			
		});
	});
</script>
<style>
#snackbar {
    visibility: hidden; /* Hidden by default. Visible on click */
    min-width: 250px; /* Set a default minimum width */
    margin-left: -125px; /* Divide value of min-width by 2 */
    background-color: #333; /* Black background color */
    color: #fff; /* White text color */
    text-align: center; /* Centered text */
    border-radius: 2px; /* Rounded borders */
    padding: 16px; /* Padding */
    position: fixed; /* Sit on top of the screen */
    z-index: 1; /* Add a z-index if needed */
    left: 50%; /* Center the snackbar */
    top: 30px; /* 30px from the bottom */
}

/* Show the snackbar when clicking on a button (class added with JavaScript) */
#snackbar.show {
    visibility: visible; /* Show the snackbar */

/* Add animation: Take 0.5 seconds to fade in and out the snackbar. 
However, delay the fade out process for 2.5 seconds */
    -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
    animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

/* Animations to fade the snackbar in and out */
@-webkit-keyframes fadein {
    from {top: 0; opacity: 0;} 
    to {top: 30px; opacity: 1;}
}

@keyframes fadein {
    from {top: 0; opacity: 0;}
    to {top: 30px; opacity: 1;}
}

@-webkit-keyframes fadeout {
    from {top: 30px; opacity: 1;} 
    to {top: 0; opacity: 0;}
}

@keyframes fadeout {
    from {top: 30px; opacity: 1;}
    to {top: 0; opacity: 0;}
}

</style>

</head>
<body>
	<%
		//String mem_no = (String)session.getAttribute("mem_no");
		String mem_no = "1002";
		pageContext.setAttribute("mem_no", mem_no);
		String location = request.getRequestURI();
	%>
	
		<button id='insertComTra' type='submit' class='btn btn-info'>加入我的最愛</button>
		<input type='hidden' name='com_no' value='2020'> 
		<input type='hidden' name='mem_no' value='${mem_no}'>
		<input type='hidden' name='path' value='<%= request.getContextPath()%>/comtra/comtra.do'>



<div id="snackbar">Some text some message..</div>
</body>
</html>