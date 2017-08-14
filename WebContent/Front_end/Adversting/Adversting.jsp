<%@page import="com.advertising.model.*"%>
<%@page import="com.com.model.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% 
// ComVO comVO = (ComVO) session.getAttribute("comVO");
// session.setAttribute("comVO", comVO);
ComService comService=new ComService();
ComVO comVO=comService.getOneCom("2001");
%>
<%
	AdvertisingService advSvc=new AdvertisingService();
	List<AdvertisingVO> list = (List<AdvertisingVO>)advSvc.getOneAll(comVO.getCom_no());
	pageContext.setAttribute("list", list);
	 %>

<%@ include file="page/adversting_header.file"%>

            <div class="col-md-8 col-offset-1">
            <div class="container-fluid" >
    <div>
  <h2>已申請廣告清單 
  <div class="text-center" style="float: right">
        <input type="submit" class="btn btn-info " data-toggle="modal" data-target="#myModal" value="廣告申請">           
         </div></h2>
  
  </div>
<!-- ********************************************************** -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">廣告申請</h4>
        </div>
       
        <div class="modal-body">
          <label for="inputdefault">內容</label> 
          <input class="form-control" id="inputdefault" type="text" name="title" value="">
        </div>
        <div class="modal-body">
          <label for="inputdefault">上傳圖片</label> 
          <input type="file" name="upfile" onchange="PreviewImage">
        </div>
        <div class="modal-footer">
        <input type="submit" class="btn btn-info" data-dismiss="modal" value="發佈">

          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

<!-- ===================================================================================== -->







  <p></p>            
  <table class="table table-striped ">
    <thead>
      <tr>
        <th>項次</th>
        
        <th>刊登時間</th>
        <th>結束時間</th>
        <th>審核狀態</th>
      </tr>
    </thead>
    <tbody>
     <c:forEach var="adverstingVO" items="${list}" varStatus="ddt">
      <tr>
     
        <td>${ddt.count }</td>
        
       <td><fmt:formatDate type="both" pattern="yyyy-MM-dd " value="${adverstingVO.startDay}" /></td>
		<td><fmt:formatDate type="both" pattern="yyyy-MM-dd " value="${adverstingVO.endDay}" /></td>
        <td>${adverstingVO.status}</td>
        
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

            </div>













<%@ include file="page/adversting_footer.file"%>