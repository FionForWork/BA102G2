<%@ page import="java.util.*"%>
<%@ page import="com.tradition.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
   <%
   Tradition_Service traSvc=new Tradition_Service();
   List<TraditionVO> list=traSvc.getAll();
   pageContext.setAttribute("list", list);
   %>
 
<%@ include file="page/tradition_header.file"%>
<c:forEach var="TraditionVO" items="${list}" >
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
                         




<%@ include file="page/tradition_footer.file"%>