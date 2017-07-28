<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.mem.model.*"%>

<%
MemVO memVO = (MemVO) request.getAttribute("memVO");

%>
<html>
<head>
<title>會員資料</title>
</head>


<table >
	<tr >
		<td>
		<h3>員工資料 - ListOneEmp.jsp</h3>
		
		</td>
	</tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>會員姓名</th>
		<th>會員性別</th>
		<th>會員生日</th>
		<th>會員電話</th>
		<th>電子郵件</th>
		<th>銀行帳戶</th>
		<th>照片</th>
		<th>被檢舉次數</th>
		<th>狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr>
		<td>${memVO.mem_no}</td>
		<td>${memVO.id}</td>
		<td>${memVO.pwd}</td>
		<td>${memVO.name}</td>
		<td>${memVO.sex}</td>
		<td>${memVO.bday}</td>
		<td>${memVO.phone}</td>
		<td>${memVO.email}</td>
		<td>${memVO.account}</td>
		<td><img src="<%=request.getContextPath()%>/ShowPictureServletDAO?mem_no=${memVO.mem_no}" /></td>
		<td>${memVO.report}</td>
		<td>${memVO.status}</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mem_no"value="${memVO.mem_no}" >
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="memVO" value="${memVO.mem_no}">
			    <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
</table>

</body>
</html>
