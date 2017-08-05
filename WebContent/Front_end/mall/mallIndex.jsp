<%@page import="com.mem.model.MemService"%>
<%@page import="com.product_type.model.Product_typeVO"%>
<%@page import="com.product_type.model.Product_typeService"%>
<%@page import="java.util.List"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    String mem_no="1010";
//     String mem_no=String.valueOf(session.getAttribute("mem_no"));
    MemService memService=new MemService();
    String mem_name=memService.getOneMem(mem_no).getName();
    int nowPage = (request.getParameter("nowPage") == null)? 1: Integer.valueOf(request.getParameter("nowPage"));
    String now_Pro_Type = (request.getParameter("now_Pro_Type") == null)? "0": request.getParameter("now_Pro_Type");
    String now_Order_Type = (request.getParameter("now_Order_Type") == null)? "0": (request.getParameter("now_Order_Type"));
    ProductService productService = new ProductService();
    int allCount = productService.getTypeAllCount(now_Pro_Type);
    int itemsCount = 8;
    int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
    
    int carTotal = (session.getAttribute("carTotal") == null) ? 0 : (Integer) session.getAttribute("carTotal");
    List<ProductVO> productList = productService.getSome(nowPage, itemsCount, now_Pro_Type, now_Order_Type);
    Product_typeService product_typeService = new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    String[] orderTypeList = {"預設", "依商品名稱", "依上架日期(新>>舊)", "依上架日期(舊>>新)", "依價格(低>>高)", "依價格(高>>低)", "依賣家"};
    String preLocation = request.getContextPath() + "/Front_end/mall";
    String location = "/front_end/mall/mallIndex.jsp?nowPage=" + nowPage + "&&now_Pro_Type=" + now_Pro_Type+ "&&now_Order_Type=" + now_Order_Type;

    session.setAttribute("carTotal", new Integer(carTotal));
    session.setAttribute("location", location);
    session.setAttribute("productList", productList);
    session.setAttribute("mem_no", mem_no);

    pageContext.setAttribute("mem_name", mem_name);
    pageContext.setAttribute("nowPage", nowPage);
    pageContext.setAttribute("totalPages", totalPages);
    pageContext.setAttribute("preLocation", preLocation);
    pageContext.setAttribute("typeList", typeList);
    pageContext.setAttribute("orderTypeList", orderTypeList);
    pageContext.setAttribute("now_Pro_Type", now_Pro_Type);
    pageContext.setAttribute("now_Order_Type", now_Order_Type);
%>
<%@include file="pages/mallIndexHeader.file"%>

<div class="text-center" style="height: 50px; margin-top: 50px">
    <c:choose>
        <c:when test="${now_Pro_Type==0}">
            <h1>全部商品 ${orderTypeList[now_Order_Type]}</h1>
        </c:when>
        <c:otherwise>
            <h1>${typeList.get(now_Pro_Type-1).getType_name()} ${orderTypeList[now_Order_Type]}</h1>
        </c:otherwise>
    </c:choose>
</div>
                    <!--//////////////////////////////////////////商品類型//////////////////////////////////////////////////////////////// -->
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <ul class="list-group">
                 <c:choose>
                       <c:when test="${now_Pro_Type==0}">
                          <a class="list-group-item menua active"  href="${preLocation}/mallIndex.jsp?nowPage=1&&now_Pro_Type=0"><h4>全部類型</h4></a>
                       </c:when>
                       <c:otherwise>
                          <a class="list-group-item menua"  href="${preLocation}/mallIndex.jsp?nowPage=1&&now_Pro_Type=0"><h4>全部類型</h4></a>
                       </c:otherwise>
                   </c:choose>
                <c:forEach var="typeVO" items="${typeList}" varStatus="s">
                   <c:choose>
                       <c:when test="${now_Pro_Type==s.count}">
                           <a class="list-group-item menua active" href="${preLocation}/mallIndex.jsp?nowPage=1&&now_Pro_Type=${typeVO.protype_no}"><h4>${typeVO.type_name}</h4></a>
                       </c:when>
                       <c:otherwise>
                           <a class="list-group-item menua" href="${preLocation}/mallIndex.jsp?nowPage=1&&now_Pro_Type=${typeVO.protype_no}"><h4>${typeVO.type_name}</h4></a>
                       </c:otherwise>
                   </c:choose>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-10">
            <div class="row">
            <!--//////////////////////////////////////////商品圖片//////////////////////////////////////////////////////////////// -->
                <div class="col-md-10">
                    <c:forEach var="productVO" items="${productList}">
                        <div class="col-xs-3 col-md-3 btn-like-wrapper">
                            <a class="thumbnail thumbnail-service mod-shadow img-label" href="${preLocation}/product.jsp?pro_no=${productVO.pro_no}">
                                <div class="caption">
                                    <img style="width: 100%; height: 200px;" src="<%=request.getContextPath()%>/image/ShowImage?pro_no=${productVO.pro_no}">
                                    <h5 class="small " style="height: 5px;">${productVO.pro_name}</h5>
                                    <span class="text-pink price">NT</span>
                                    <b class="text-pink price " style="font-size: 2em;">${productVO.price}</b>
                                </div>
                            </a>
                        </div>
                        </c:forEach>
                        <div class="clearfix"></div>
            <!--//////////////////////////////////////////分頁開始//////////////////////////////////////////////////////////////// -->
                        <div class="text-center ">
                            <nav aria-label="Page navigation ">
                                <ul class="pagination pagination-lg ">
                                    <c:choose>
                                        <c:when test="${totalPages<=5}">
                                            <c:forEach var="i" begin="1" end="${totalPages}">
                                                <li class=""><a class="btn btn-primary" href="${preLocation}/mallIndex.jsp?nowPage=${i}&&now_Pro_Type=${now_Pro_Type}&&now_Order_Type=${now_Order_Type}" data-page="${i}">${i}</a></li>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${nowPage<5}">
                                            <c:forEach var="i" begin="1" end="5">
                                                <li><a class="btn btn-primary" href="${preLocation}/mallIndex.jsp?nowPage=${i}&&now_Pro_Type=${now_Pro_Type}&&now_Order_Type=${now_Order_Type}" data-page="${i}">${i}</a></li>
                                            </c:forEach>
                                            <li><a class="disabled">...</a></li>
                                            <li><a class="btn btn-primary" href="${preLocation}/mallIndex.jsp?nowPage=${totalPages}&&now_Pro_Type=${now_Pro_Type}&&now_Order_Type=${now_Order_Type}" data-page="${totalPages}">${totalPages}</a></li>
                                        </c:when>
                                        <c:when test="${totalPages-nowPage<5}">
                                            <li><a class="btn btn-primary" href="${preLocation}/mallIndex.jsp?nowPage=1&&now_Pro_Type=${now_Pro_Type}&&now_Order_Type=${now_Order_Type}" data-page="1">1</a></li>
                                            <li><a class="disabled">...</a></li>
                                            <c:forEach var="i" begin="${totalPages-5}" end="${totalPages}">
                                                <li><a class="btn btn-primary" href="${preLocation}/mallIndex.jsp?nowPage=${i}&&now_Pro_Type=${now_Pro_Type}&&now_Order_Type=${now_Order_Type}" data-page="${i}">${i}</a></li>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a class="btn btn-primary" href="${preLocation}/mallIndex.jsp?nowPage=1" data-page="1">1</a></li>
                                            <li><a class="disabled">...</a></li>
                                            <c:forEach var="i" begin="${nowPage-2}" end="${nowPage+2}">
                                                <li><a class="btn btn-primary" href="${preLocation}/mallIndex.jsp?nowPage=${i}&&now_Pro_Type=${now_Pro_Type}&&now_Order_Type=${now_Order_Type}" data-page="${i}">${i}</a></li>
                                            </c:forEach>
                                            <li><a class="disabled">...</a></li>
                                            <li><a class="btn btn-primary" href="${preLocation}/mallIndex.jsp?nowPage=${totalPages}&&now_Pro_Type=${now_Pro_Type}&&now_Order_Type=${now_Order_Type}" data-page="${totalPages}">${totalPages}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
                <div class="btn-group">
                    <button id="type" class="btn btn-default">${orderTypeList[now_Order_Type]}</button>
                    <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                    <ul class="dropdown-menu">
                    <c:forEach var="orderType" items="${orderTypeList}" varStatus="s">
                        <li><a href="${preLocation}/mallIndex.jsp?nowPage=1&&now_Pro_Type=${now_Pro_Type}&&now_Order_Type=${s.index}">${orderType}</a></li>
                    </c:forEach>
                    </ul>
                </div>

                <div class="col-md-2">
                    <div class="cart box_1">
                        <a href="${preLocation}/checkout.jsp">
                            <h3>
                                <div class="total">
                                    <span class="simpleCart_total">累計金額:NT${carTotal}</span>
                                    <span id="simpleCart_quantity" class="simpleCart_quantity"></span>
                                </div>
                            </h3>
                        </a>
                        <form action="<%=request.getContextPath()%>/product/ProductServlet" method="post">
                            <input type="hidden" name="action" value="CLEAR">
                            <button type="submit" class="btn btn-danger btn-sm">清空購物車</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <script>
        
    </script>
    <%@include file="pages/mallIndexFooter.file"%>