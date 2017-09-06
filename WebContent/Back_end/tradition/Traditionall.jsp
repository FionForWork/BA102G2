<%@ page import="java.util.*"%>
<%@ page import="com.tradition.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
   <%
   Tradition_Service traSvc=new Tradition_Service();
   List<TraditionVO> list=traSvc.getAll();
   pageContext.setAttribute("list", list);
   %>
 
 <%  int rowsPerPage = 5;  //每頁的筆數    
    int rowNumber=0;      //總筆數
    int pageNumber=0;     //總頁數      
    int whichPage=1;      //第幾頁
    int pageIndexArray[]=null;
    int pageIndex=0; 
%>

<%  
    rowNumber=list.size();
    if (rowNumber%rowsPerPage !=0)
     pageNumber=rowNumber/rowsPerPage +1;
    else pageNumber=rowNumber/rowsPerPage;    

    pageIndexArray=new int[pageNumber]; 
    for (int i=1 ; i<=pageIndexArray.length ; i++)
    pageIndexArray[i-1]=i*rowsPerPage-rowsPerPage;
%>

<%  try {
      whichPage = Integer.parseInt(request.getParameter("whichPage"));
      pageIndex=pageIndexArray[whichPage-1];
    } catch (NumberFormatException e) { //第一次執行的時候
       whichPage=1;
       pageIndex=0;
    } catch (ArrayIndexOutOfBoundsException e) { //總頁數之外的錯誤頁數
         if (pageNumber>0){
              whichPage=pageNumber;
              pageIndex=pageIndexArray[pageNumber-1];
         }
    } 
%>

<%@ include file="page/tradition_header.file"%>
<c:forEach var="TraditionVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
  <tbody>
                                <tr>
                                    <td class="text-center">${TraditionVO.tra_no }</td>
                                    <td class="text-center">${TraditionVO.tra_type_no }</td>
                                    <td>${TraditionVO.tra_order }</td>
                                    <td>${TraditionVO.title }</td>
                                    
<!-- ===================================================================================================== -->
									 <td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Tradition/Tradition.do">
                                    <input type="submit" class="btn btn-info " value="修改" >
                                    
                                    <input type="hidden" name="tra_no" value="${TraditionVO.tra_no}">
			     					<input type="hidden" name="action"	value="getOne_For_Update"></FORM></td>

  
 <!-- ====================================================================================================== -->
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Tradition/Tradition.do">
                                    <td><input type="submit" class="btn btn-default btn-danger btn-primary" value="移除">
                                    	<input type="hidden" name="action" value="delete">
                                    	<input type="hidden" name="tra_no" value="${TraditionVO.tra_no}">
                                    </td></FORM>
                                </tr>
                                </tbody>
 </c:forEach>
                         
 <table border="0">    
 <tr>

    <div align="center">
    <nav aria-label="Page navigation ">
         <ul id="pagination" class="pagination ">
         	<%for (int i=1; i<=pageNumber; i++){%>
         		<%if(whichPage == i) {%>
 					<li class=""><a class="btn btn-info active" href="<%=request.getRequestURI()%>?whichPage=<%=i%>"><%=i%></a></li>
 				<%} else {%>
 					<li class=""><a class="btn btn-info" href="<%=request.getRequestURI()%>?whichPage=<%=i%>"><%=i%></a></li>
 				<%}%>
 			<%}%>
 		 </ul>
 	</nav>
  	</div>
  	
 </tr>
</table>



<%@ include file="page/tradition_footer.file"%>