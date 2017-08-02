<%@page import="com.product_type.model.Product_typeVO"%>
<%@page import="com.product_type.model.Product_typeService"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.product.model.ProductService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="pages/mallIndexHeader.file"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    session.setAttribute("mem_no", "1010");
    String mem_no = String.valueOf(session.getAttribute("mem_no"));
    ProductService productService = new ProductService();
    List<ProductVO> productList = productService.getAllBySeller(mem_no);
    Product_typeService product_typeService = new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    pageContext.setAttribute("typeList", typeList);

    String preLocation = request.getContextPath() + "/front_end/mall";
    pageContext.setAttribute("preLocation", preLocation);
    session.setAttribute("productList", productList);
%>
<div class="text-center" style="height: 50px; margin-top: 50px">
    <h2>商品資料管理</h2>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <%@include file="pages/mallAreaSidebar.file"%>
                <div class="col-md-1"></div>
                <div class="col-md-7">
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th>商品名稱</th>
                                <th>價格</th>
                                <th>商品評價</th>
                                <th>修改資訊</th>
                                <th>上/下架</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="productVO" items="${productList}" varStatus="s">
                                <tr>
                                    <td><a target="_blank" href="${preLocation}/product.jsp?pro_no=${productVO.pro_no}">${productVO.pro_name}</a></td>
                                    <td>${productVO.price}</td>
                                    <c:choose>
                                        <c:when test="${productVO.score==0}">
                                            <td>尚無評價</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><fmt:formatNumber value="${productVO.score/productVO.times}" maxFractionDigits="1" /></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>
                                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#${productVO.pro_no}">商品資料修改</button>
                                        <div class="modal fade" id="${productVO.pro_no}">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h4 class="modal-title">商品資料修改</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="<%=request.getContextPath()%>/product/ProductServlet" method="post" enctype="multipart/form-data">
                                                            <table  class="table">
                                                                <tbody>
                                                                    <tr>
                                                                        <td>商品名稱</td>
                                                                        <td><input name="pro_name" class="form-control" type="text" placeholder="${productVO.pro_name}"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>商品描述</td>
                                                                        <td><textarea name="pro_desc" class="form-control" style="width: 300px; height: 100px;" placeholder="${productVO.pro_desc}"></textarea></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>價格</td>
                                                                        <td><input name="price" class="form-control" type="number" placeholder="${productVO.price}" min="1"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>庫存</td>
                                                                        <td><input name="amount" class="form-control" type="number" placeholder="${productVO.amount}" min="1"></td>
                                                                    </tr>
                                                                    <td><input name="img" type="file" class="form-control"></td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                            <input type="hidden" name="index" value="${s.index}">
                                                            <input type="hidden" name="action" value="UPDATE">
                                                            <input type="submit" class="btn btn-success" value="確認修改">
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <c:choose>
                                        <c:when test="${productVO.status=='0'}">
                                            <td>商品審核中</td>
                                        </c:when>
                                        <c:when test="${productVO.status=='1'}">
                                            <td><a class="btn btn-danger" href="<%=request.getContextPath() %>/product/ProductServlet?action=OFF&&pro_no=${productVO.pro_no}">商品下架</a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><a class="btn btn-success" href="<%=request.getContextPath() %>/product/ProductServlet?action=ON&&pro_no=${productVO.pro_no}">商品上架</a></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#NewProduct">申請新商品上架</button>
                    <div class="modal fade" id="NewProduct">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title">申請新商品上架</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="<%=request.getContextPath()%>/product/ProductServlet" method="post" enctype="multipart/form-data">
                                        <table class="table" >
                                            <tbody>
                                                <tr>
                                                    <td>商品名稱</td>
                                                    <td><input name="pro_name" class="form-control" type="text" placeholder="必須輸入商品名稱"></td>
                                                </tr>
                                                <tr>
                                                    <td>商品描述</td>
                                                    <td><textarea name="pro_desc" class="form-control" style="width: 300px; height: 100px;"></textarea></td>
                                                </tr>
                                                <tr>
                                                    <td>價格</td>
                                                    <td><input name="price" class="form-control" type="number" min="1" placeholder="1"></td>
                                                </tr>
                                                <tr>
                                                    <td>庫存</td>
                                                    <td><input name="amount" class="form-control" type="number" min="1" placeholder="1"></td>
                                                </tr>
                                                <tr>
                                                    <td>商品種類</td>
                                                    <td><select name="protype_no">
                                                            <c:forEach var="proType" items="${typeList}">
                                                                <option value="${proType.protype_no}">${proType.type_name}</option>
                                                            </c:forEach>
                                                        </select></td>
                                                </tr>
                                                <tr>
                                                <td><input name="img" type="file"></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <input type="hidden" name="action" value="ADD">
                                        <input type="submit" class="btn btn-success" value="確認申請">
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="pages/mallIndexFooter.file"%>