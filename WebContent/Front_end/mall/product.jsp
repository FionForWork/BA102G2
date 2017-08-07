<%@page import="com.mem.model.MemService"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.protra.model.ProtraVO"%>
<%@page import="com.product.model.ProductService"%>
<%@page import="com.protra.model.ProtraService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.product_type.model.Product_typeVO"%>
<%@page import="com.product_type.model.Product_typeService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    int carTotal = (session.getAttribute("carTotal") == null) ? 0 : Integer.valueOf(String.valueOf((session.getAttribute("carTotal"))));
    MemVO memVO=(session.getAttribute("memVO")==null)?new MemService().getOneMem("1010"):(MemVO)session.getAttribute("memVO");
    Product_typeService product_typeService = new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    ProtraService protraService = new ProtraService();
    List<String> protrackingProNolist=new ArrayList<String>();;
    List<ProtraVO>protraList=protraService.getAllByMem(memVO.getMem_no());
    for(int i=0;i<protraList.size();i++){
        protrackingProNolist.add(protraList.get(i).getPro_no());
    }
    String[] productStatus = { "審核中", "上架中", "已下架" };
    String pro_no;
    int mine = 0;
    if (request.getParameter("pro_no") == null) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("mallIndexAJAX.jsp");
        requestDispatcher.forward(request, response);
        return;
    }
    else {
        pro_no = request.getParameter("pro_no");
        ProductService productService=new ProductService();
        ProductVO productVO=productService.getOneByPKNoImg(pro_no);
        mine = (productVO.getSeller_no().equals(memVO.getMem_no())) ? 1 : 0;
        pageContext.setAttribute("productVO", productVO);
     
    }
    String preLocation = request.getContextPath() + "/Front_end/mall";
    
    pageContext.setAttribute("preLocation", preLocation);
    pageContext.setAttribute("pro_no", pro_no);
    pageContext.setAttribute("mine", mine);
    pageContext.setAttribute("protrackingProNolist", protrackingProNolist);
    pageContext.setAttribute("productStatus", productStatus);
    pageContext.setAttribute("typeList", typeList);
    
    session.setAttribute("carTotal", new Integer(carTotal));
%>
<%@include file="pages/ProductHeader.file"%>
<div class="container" style="margin-bottom: 10px;">
    <div class="row">
        <div class="col-md-2">
            <ul class="list-group">
                <a class="list-group-item menua"  href="${preLocation}/mallIndexAJAX.jsp?nowPage=1&&now_Pro_Type=0"><h4>全部類型</h4></a>
                <c:forEach var="typeVO" items="${typeList}" varStatus="s">
                     <c:choose>
                       <c:when test="${productVO.protype_no==s.count}">
                           <a class="list-group-item menua active" href="${preLocation}/mallIndexAJAX.jsp?nowPage=1&&now_Pro_Type=${typeVO.protype_no}"><h4>${typeVO.type_name}</h4></a>
                       </c:when>
                       <c:otherwise>
                           <a class="list-group-item menua" href="${preLocation}/mallIndexAJAX.jsp?nowPage=1&&now_Pro_Type=${typeVO.protype_no}"><h4>${typeVO.type_name}</h4></a>
                       </c:otherwise>
                   </c:choose>
                </c:forEach>
            </ul>
        </div>

        <div class="col-md-10">
            <div class="row">
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-5">
                            <img style="width:300px;"class="img-responsive" src="<%=request.getContextPath() %>/image/ShowImage?pro_no=${productVO.pro_no}">
                        </div>
                        <div class="col-md-7">
                            <div class="row">
                                <dl>
                                    <dt>商品名稱</dt>
                                    <dd>${productVO.pro_name}</dd>
                                    <dt>賣家編號</dt>
                                    <dd>${productVO.seller_no}</dd>
                                    <dt>上架日期</dt>
                                    <dd>${productVO.pro_date}</dd>
                                    <dt>商品描述</dt>
<%--                                     <dd><pre style="border: 0px;margin: 0px; padding: 0px;">${productVO.pro_desc}</pre></dd> --%>
                                    <dd>${productVO.pro_desc}</dd>
                                    <dt>商品價格</dt>
                                    <dd>${productVO.price}</dd>
                                    <dt>商品庫存</dt>
                                    <c:choose>
                                        <c:when test="${productVO.amount == 0}">
                                            <dd style="">${productVO.amount}</dd>
                                        </c:when>
                                        <c:otherwise>
                                            <dd>${productVO.amount}</dd>
                                        </c:otherwise>
                                    </c:choose>
                                    <dt>商品狀態</dt>
                                    <dd>${productStatus[productVO.status]}</dd>
                                </dl>
                                <div>
                                    <c:choose>
                                        <c:when test="${mine==0}">
                                            <c:if test="${productVO.status==1}">
<%--                                                 <form action="<%=request.getContextPath()%>/product/ProductServlet" method="post"> --%>
<!--                                                     <label>數量</label> -->
<%--                                                     <input type="hidden" name="action" value="ADD_TO_CAR"> <input type="number" name="product_account" value="1" min="1" max="${productVO.amount}" style="width: 80px;"> --%>
<!--                                                     <button type="submit" class="btn btn-default ">加入購物車</button> -->
<!--                                                 </form> -->
                                            <label>數量</label>
                                            <input id="productCount" type="number" name="productCount" value="1" min="1" max="${productVO.amount}" style="width: 80px;">
                                            <a href="javascript:add(${productVO.pro_no})" class="btn btn-primary">加入購物車</a>
                                            </c:if>
                                            <div id="protracking">
                                                <c:choose>
                                                <c:when test="${protrackingProNolist.contains(pro_no)}">
<%--                                                     <a href="<%=request.getContextPath() %>/protracking_list/Protracking_listServlet?action=DELETE" class="btn btn-danger">取消追蹤</a> --%>
                                                        <a href="javascript:protracking('DELETE_AJAX',${productVO.pro_no})" class="btn btn-danger">取消追蹤</a>
                                                </c:when>
                                                <c:otherwise>
<%--                                                     <a href="<%=request.getContextPath() %>/protracking_list/Protracking_listServlet?action=ADD" class="btn btn-success">加入追蹤</a> --%>
                                                        <a href="javascript:protracking('ADD_AJAX',${productVO.pro_no})" class="btn btn-success">加入追蹤</a>
                                                </c:otherwise>
                                            </c:choose>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <h3>請去看別人的商品，促進經濟好嗎?</h3>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="cart box_1">
                        <a href="checkout.jsp">
                            <h3>
                                <div class="total">
                                    <span class="simpleCart_total">NT$${carTotal}</span>
                                    <span class="glyphicon glyphicon-shopping-cart"></span>
                                </div>
                            </h3>
                        </a>
                        <a href="javascript:clear()" class="btn btn-danger btn-sm">清空購物車</a>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function protracking(todo,pro_no){
            $.ajax({
                url:"/BA102G2/protra/ProtracServlet",
                type : "post",
                data:{
                    action:todo,
                    pro_no:pro_no
                }, 
                error : function(xhr, ajaxOptions, thrownError) {
                    console.log(xhr.status);
                    console.log(thrownError);
                },
                success : function(response) {
                    $("#protracking").html(response);
                }
            });
    }
    
    function add(pro_no){
            $.ajax({
                url:"/BA102G2/product/ProductServlet",
                type : "post",
                data:{
                    action:"ADD_TO_CAR_AJAX",
                    productCount:$("#productCount").val(),
                    pro_no:pro_no
                }, 
                error : function(xhr, ajaxOptions, thrownError) {
                    console.log(xhr.status);
                    console.log(thrownError);
                },
                success : function(response) {
                    if(response=="out of amount"){
                        alert("超過庫存，請減少數量");
                    }
                    else{
                        $(".simpleCart_total").text("NT$"+response);
                    }
                }
            });
    }
</script>
<%@include file="pages/mallIndexFooter.file"%>