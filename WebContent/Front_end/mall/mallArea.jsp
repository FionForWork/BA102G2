<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemService"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page import="com.order_detail.model.Order_detailVO"%>
<%@page import="com.order_detail.model.Order_detailService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ord.model.OrdVO"%>
<%@page import="java.util.List"%>
<%@page import="com.ord.model.OrdService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String mem_no = "1010";
    //     MemVO memVO=(MemVO)session.getAttribute("memVO");
    MemService memService = new MemService();
    MemVO memVO = memService.getOneMem(mem_no);
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    session.setAttribute("mem_no", mem_no);
    String role = (request.getParameter("role") == null) ? "0" : request.getParameter("role");
    String now_Status = (request.getParameter("now_Status") == null) ? "0" : request.getParameter("now_Status");
    String orderType = (request.getParameter("orderType") == null) ? "0" : request.getParameter("orderType");
    String[] statusList = { "買家未付款", "賣家未出貨", "已完成訂單", "賣家已評價", "已取消訂單" };
    String[] orderTypeList = { "依對方編號小>>大", "依對方編號大>>小", "依寄送地址", "依成立日期近>>遠", "依成立日期遠>>近", "依訂單總價小>>大", "依訂單總價大>>小" };
    OrdService ordService = new OrdService();
    int nowPage = (request.getParameter("nowPage") == null)? 1: Integer.valueOf(request.getParameter("nowPage"));
    int allCount = ordService.getAllOrderCount(role, memVO.getMem_no(), now_Status);
    int itemsCount = 5;
    int totalPages = (allCount % itemsCount == 0) ? (allCount / itemsCount) : (allCount / itemsCount + 1);
    List<OrdVO> ordList = ordService.getAllOrderByRole(nowPage,itemsCount,role, memVO.getMem_no(), now_Status, orderType);
    List<String> seller_accountList = new ArrayList<String>();
    List<String> cust_nameList = new ArrayList<String>();
    for (int i = 0; i < ordList.size(); i++) {
        seller_accountList.add(memService.getOneMem(ordList.get(i).getSeller_no()).getAccount());
        cust_nameList.add(memService.getOneMem(ordList.get(i).getCust_no()).getName());
    }
    Order_detailService order_detailService = new Order_detailService();
    ProductService productService = new ProductService();

    StringBuffer stringBuffer = new StringBuffer();
    if ("0".equals(role)) {
        stringBuffer.append("買家-");
    }
    else {
        stringBuffer.append("賣家-");
    }

    if ("0".equals(now_Status)) {
        stringBuffer.append("買家未付款");
    }
    else if ("1".equals(now_Status)) {
        stringBuffer.append("賣家未出貨");
    }
    else if ("2".equals(now_Status)) {
        stringBuffer.append("已完成訂單");
    }
    else if ("3".equals(now_Status)) {
        stringBuffer.append("賣家已評價");
    }
    else {
        stringBuffer.append("已取消訂單");
    }

    String preLocation = request.getContextPath() + "/Front_end/mall";
    List<ProductVO> productList = new ArrayList<ProductVO>();
    session.setAttribute("mem_name", memVO.getName());
    session.setAttribute("productList", productList);
    session.setAttribute("memService", memService);

    pageContext.setAttribute("itemsCount", itemsCount);
    pageContext.setAttribute("totalPages", totalPages);
    pageContext.setAttribute("productService", productService);
    pageContext.setAttribute("order_detailService", order_detailService);
    pageContext.setAttribute("cust_nameList", cust_nameList);
    pageContext.setAttribute("seller_accountList", seller_accountList);
    pageContext.setAttribute("orderTypeList", orderTypeList);
    pageContext.setAttribute("preLocation", preLocation);
    pageContext.setAttribute("statusList", statusList);
    pageContext.setAttribute("role", role);
    pageContext.setAttribute("now_Status", now_Status);
    pageContext.setAttribute("orderType", orderType);
    pageContext.setAttribute("ordList", ordList);
    pageContext.setAttribute("stringBuffer", stringBuffer);
%>
<%@include file="pages/mallIndexHeader.file"%>
<div class="text-center" style="height: 50px; margin-top: 50px">
    <h2>${stringBuffer.toString()}</h2>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <%@include file="pages/mallAreaSidebar.file"%>
            <div class="col-md-1"></div>
            <div class="col-md-7">
                <c:forEach var="status" items="${statusList}" varStatus="s">
                    <a href="${preLocation}/mallArea.jsp?now_Status=${s.index}&&role=${role}">${status}</a>/
				    </c:forEach>
                <table class="table table-hover table-striped">
                    <thead>
                        <tr>
                            <c:choose>
                                <c:when test="${role==0}">
                                    <th>賣家編號</th>
                                </c:when>
                                <c:otherwise>
                                    <th>買家編號</th>
                                </c:otherwise>
                            </c:choose>
                            <th>寄送地址</th>
                            <th>訂單成立日期</th>
                            <th>總價</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ordVO" items="${ordList}" varStatus="s">
                            <tr>
                                <c:choose>
                                    <c:when test="${role==0}">
                                        <td>${ordVO.seller_no}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${ordVO.cust_no}</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${ordVO.address}</td>
                                <td><fmt:formatDate value="${ordVO.ord_date}" pattern="yyyy-MM-dd HH:mm" /></td>
                                <td>${ordVO.total}</td>
                                <td><c:choose>
                                        <c:when test="${role=='0' && now_Status=='0'}">
                                            <p>請盡速繳款</p>
                                            <p>賣家帳戶:</p>
                                            <p>${seller_accountList[s.index]}</p>
                                            <p><a href="<%=request.getContextPath()%>/order/OrderServlet?action=CANCEL&&ord_no=${ordVO.ord_no}" class="btn btn-danger">取消訂單</a></p>
                                        </c:when>
                                        <c:when test="${role=='0' && now_Status=='1'}">
                                            <a href="<%=request.getContextPath()%>/order/OrderServlet?action=CHECK_GET_ITEM&&ord_no=${ordVO.ord_no}" class="btn btn-info">確認已收到貨物</a>
                                        </c:when>
                                        <c:when test="${role=='1' && now_Status=='0'}">
                                            <a href="<%=request.getContextPath()%>/order/OrderServlet?action=CHECK_GET_MONEY&&ord_no=${ordVO.ord_no}" class="btn btn-info">確認已收到貨款</a>
                                        </c:when>
                                        <c:when test="${role=='1' && now_Status=='1'}">
                                            <p>請盡速出貨</p>
                                            <p>買家姓名:${cust_nameList[s.index]}</p>
                                            <p>買家寄送地址:${ordVO.address}</p>
                                        </c:when>
                                        <c:when test="${role=='0' && now_Status=='2'}">
                                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#${ordVO.ord_no}">對商品評分</button>
                                            <div class="modal fade" id="${ordVO.ord_no}">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header text-center">
                                                            <h4 class="modal-title">評分</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form action="<%=request.getContextPath()%>/order/OrderServlet" method="post">
                                                                <table class="table">
                                                                    <tr>
                                                                        <td>商品名稱</td>
                                                                        <td>評價</td>
                                                                    </tr>
                                                                    <input type="hidden" name="ord_no" value="${ordVO.ord_no}">
                                                                    <c:forEach var="order_detailVO" items="${order_detailService.getAllByOrdNo(ordVO.ord_no)}">
                                                                        <tr>
                                                                            <c:if test="${!productList.contains(productService.getOneByPKNoImg(order_detailVO.pro_no))}">
                                                                                <!--${productList.add(productService.getOneByPKNoImg(order_detailVO.pro_no))}-->
                                                                            </c:if>
                                                                            <td><a target="_blank" href="${preLocation}/product.jsp?pro_no=${order_detailVO.pro_no}">${productService.getOneByPKNoImg(order_detailVO.pro_no).pro_name}</a></td>
                                                                            <td><c:choose>
                                                                                    <c:when test="${order_detailVO.status=='0'}">
                                                                                        <input type="hidden" name="pro_no" value="${order_detailVO.pro_no}">
                                                                                        <td><input name="score" value="0" type="number" min="0" max="5" class="form-control" placeholder="請輸入評分0-5之間"></td>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <p>您已評價過，分數為${order_detailVO.score}</p>
                                                                                    </c:otherwise>
                                                                                </c:choose></td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </table>
                                                                <input type="hidden" name="action" value="EVALUATION_TO_ITEM">
                                                                <input type="submit" class="btn btn-success" value="確認">
                                                            </form>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:when test="${role=='0' && now_Status=='3'}">
                                            <p>賣家已對您評價:${ordVO.score}分</p>
                                        </c:when>
                                        <c:when test="${role=='1' && now_Status=='2'}">
                                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#${ordVO.ord_no}">對買家評分</button>
                                            <div class="modal fade" id="${ordVO.ord_no}">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title">評分</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form action="<%=request.getContextPath()%>/order/OrderServlet" method="post">
                                                                <table class="table">
                                                                    <tr>
                                                                        <td>評分</td>
                                                                        <input type="hidden" name="ord_no" value="${ordVO.ord_no}">
                                                                        <td><input type="number" min="0" max="5" name="score" class="form-control" placeholder="請輸入評分0-5之間"></td>
                                                                    </tr>
                                                                </table>
                                                                <input type="hidden" name="action" value="EVALUATION_TO_MEM">
                                                                <input type="submit" class="btn btn-success" value="確認">
                                                            </form>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:when test="${role=='1' && now_Status=='3'}">
                                            <p>賣家已對買家評價:${ordVO.score}分</p>
                                        </c:when>
                                    </c:choose></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table><!--//////////////////////////////////////////分頁開始//////////////////////////////////////////////////////////////// -->
                        <div class="text-center ">
                            <nav aria-label="Page navigation ">
                                <ul class="pagination pagination-lg ">
                                    <c:choose>
                                        <c:when test="${totalPages<=5}">
                                            <c:forEach var="i" begin="1" end="${totalPages}">
                                                <li class=""><a class="btn btn-info" href="${preLocation}/mallArea.jsp?nowPage=${i}&&role=${role}&&now_Status=${now_Status}&&orderType=${orderType}" data-page="${i}">${i}</a></li>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${nowPage<5}">
                                            <c:forEach var="i" begin="1" end="5">
                                                <li><a class="btn btn-info" href="${preLocation}/mallArea.jsp?nowPage=${i}&&role=${role}&&now_Status=${now_Status}&&orderType=${orderType}" data-page="${i}">${i}</a></li>
                                            </c:forEach>
                                            <li><a class="disabled">...</a></li>
                                            <li><a class="btn btn-info" href="${preLocation}/mallArea.jsp?nowPage=${totalPages}&&role=${role}&&now_Status=${now_Status}&&orderType=${orderType}" data-page="${totalPages}">${totalPages}</a></li>
                                        </c:when>
                                        <c:when test="${totalPages-nowPage<5}">
                                            <li><a class="btn btn-info" href="${preLocation}/mallArea.jsp?nowPage=1&&role=${role}&&now_Status=${now_Status}&&orderType=${orderType}" data-page="1">1</a></li>
                                            <li><a class="disabled">...</a></li>
                                            <c:forEach var="i" begin="${totalPages-5}" end="${totalPages}">
                                                <li><a class="btn btn-info" href="${preLocation}/mallArea.jsp?nowPage=${i}&&role=${role}&&now_Status=${now_Status}&&orderType=${orderType}" data-page="${i}">${i}</a></li>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a class="btn btn-info" href="${preLocation}/mallArea.jsp?nowPage=1" data-page="1">1</a></li>
                                            <li><a class="disabled">...</a></li>
                                            <c:forEach var="i" begin="${nowPage-2}" end="${nowPage+2}">
                                                <li><a class="btn btn-info" href="${preLocation}/mallArea.jsp?nowPage=${i}&&role=${role}&&now_Status=${now_Status}&&orderType=${orderType}" data-page="${i}">${i}</a></li>
                                            </c:forEach>
                                            <li><a class="disabled">...</a></li>
                                            <li><a class="btn btn-info" href="${preLocation}/mallArea.jsp?nowPage=${totalPages}&&role=${role}&&now_Status=${now_Status}&&orderType=${orderType}" data-page="${totalPages}">${totalPages}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <!--//////////////////////////////////////////分頁結束//////////////////////////////////////////////////////////////// -->
             <div class="col-md-2">
                <div class="btn-group">
                    <button id="type" class="btn btn-default">排序方式</button>
                    <button data-toggle="dropdown" class="btn btn-default "><span class="caret"></span></button>
                    <ul class="dropdown-menu">
                    <c:forEach var="orderType" items="${orderTypeList}" varStatus="s">
                        <li><a href="${preLocation}/mallArea.jsp?now_Status=${now_Status}&&role=${role}&&now_Status=${now_Status}&&orderType=${s.count}">${orderType}</a></li>
                    </c:forEach>
                    </ul>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
<%@include file="pages/mallIndexFooter.file"%>