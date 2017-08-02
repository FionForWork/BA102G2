<%@page import="com.ord.model.OrdVO"%>
<%@page import="java.util.List"%>
<%@page import="com.ord.model.OrdService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="pages/mallIndexHeader.file"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String mem_no = String.valueOf(session.getAttribute("mem_no"));
    OrdService ordService = new OrdService();
    List<OrdVO> ordList = ordService.getAllByCust(mem_no, "3");
    double avg = 0.0;
    for (int i = 0; i < ordList.size(); i++) {
        avg += ordList.get(i).getScore();
    }
    avg = avg / ordList.size();

    pageContext.setAttribute("ordList", ordList);
    pageContext.setAttribute("avg", avg);
%>
<div class="text-center" style="height: 50px; margin-top: 50px">
    <h2>您的平均評價為 <fmt:formatNumber value="${avg}" maxFractionDigits="1" />
    </h2>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <%@include file="pages/mallAreaSidebar.file"%>
                <div class="col-md-1"></div>
                <div class="col-md-9">
                    <div class="col-md-12">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>訂單編號</th>
                                    <th>賣家編號</th>
                                    <th>寄送地址</th>
                                    <th>成立日期</th>
                                    <th>總價</th>
                                    <th>賣家對您的評價</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="ordVO" items="${ordList}">
                                    <td>${ordVO.ord_no}</td>
                                    <td>${ordVO.seller_no}</td>
                                    <td>${ordVO.address}</td>
                                    <td>
                                        <fmt:formatDate value="${ordVO.ord_date}" pattern="yyyy-MM-dd HH:mm" />
                                    </td>
                                    <td>${ordVO.total}</td>
                                    <td>${ordVO.score}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="pages/mallIndexFooter.file"%>