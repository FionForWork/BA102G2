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
    MemVO memVO=(MemVO)session.getAttribute("memVO");
    MemService memService=new MemService();
//     MemVO memVO =memService.getOneMem("1010");
    int carTotal = (session.getAttribute("carTotal") == null) ? 0 : Integer.valueOf(String.valueOf((session.getAttribute("carTotal"))));
    Product_typeService product_typeService = new Product_typeService();
    List<Product_typeVO> typeList = product_typeService.getAll();
    ProtraService protraService = new ProtraService();
    List<String> protrackingProNolist=new ArrayList<String>();;
    List<ProtraVO>protraList=protraService.getAllByMem(memVO.getMem_no());
    String[] productStatus = { "審核中", "上架中", "已下架" };
    String pro_no;
    int mine = 0;
    if (request.getParameter("pro_no") == null) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
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
    boolean tracing=false;
    for(int i=0;i<protraList.size();i++){
        if(protraList.get(i).getPro_no().equals(pro_no)){
            tracing=true;
        }
    }
    
    String preLocation = request.getContextPath() + "/Front_end/mall";
    
    pageContext.setAttribute("tracing", tracing);
    pageContext.setAttribute("preLocation", preLocation);
    pageContext.setAttribute("pro_no", pro_no);
    pageContext.setAttribute("mine", mine);
    pageContext.setAttribute("productStatus", productStatus);
    pageContext.setAttribute("typeList", typeList);
    
    session.setAttribute("carTotal", new Integer(carTotal));
%>
<%@include file="pages/indexHeader.file"%>
<!--//////////////////////////////////////////商品類型//////////////////////////////////////////////////////////////// -->
<div class="text-center" style="margin-top:50px"></div>
<div class="container">
    <div class="row">
        <div class="col-xs-2 col-md-2">
            <ul class="list-group">
                <a class="list-group-item menua"  href="${preLocation}/index.jsp?nowPage=1&&now_Pro_Type=0"><h4>全部類型</h4></a>
                <c:forEach var="typeVO" items="${typeList}" varStatus="s">
                    <a class="list-group-item menua ${productVO.protype_no==s.count?'active':''}" href="${preLocation}/index.jsp?nowPage=1&&now_Pro_Type=${typeVO.protype_no}"><h4>${typeVO.type_name}</h4></a>
                </c:forEach>
            </ul>
        </div>

        <div class="col-xs-10 col-md-10">
            <div class="row">
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-xs-5 col-md-5">
                            <img style="width:300px;"class="img-responsive" src="<%=request.getContextPath() %>/image/ShowImage?pro_no=${productVO.pro_no}">
                        </div>
                        <div class="col-xs-7 col-md-7">
                            <div class="row">
                                <dl>
                                    <dt>商品名稱</dt>
                                    <dd>${productVO.pro_name}</dd>
                                    <dt>賣家編號</dt>
                                    <dd>${productVO.seller_no}</dd>
                                    <dt>上架日期</dt>
                                    <dd><fmt:formatDate value="${productVO.pro_date}" pattern="yyyy-MM-DD HH:mm"/></dd>
                                    <dt>商品描述</dt>
<%--                                     <dd><pre style="border: 0px;margin: 0px; padding: 0px;">${productVO.pro_desc}</pre></dd> --%>
                                    <dd>${productVO.pro_desc}</dd>
                                    <dt>商品價格</dt>
                                    <dd>${productVO.price}</dd>
                                    <dt>商品庫存</dt>
                                    <c:choose>
                                        <c:when test="${productVO.amount == 0}">
                                            <dd>缺貨中，等待賣家補貨</dd>
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
                                            <label>數量</label>
                                            <input id="productCount" type="number" required="required" name="productCount" value="1" min="1" max="${productVO.amount}" style="width: 80px;">
                                            <a href="javascript:add(${productVO.pro_no})" class="btn btn-primary">加入購物車</a>
                                            <input type="submit" class="btn btn-danger" data-toggle="modal" onclick="showmodal('product','${productVO.pro_no}','${productVO.seller_no}','/Front_end/mall/product.jsp?pro_no='+${productVO.pro_no});" data-target="#myModal" value="檢舉">
                                            </c:if>
                                            <div id="protracking">
                                            <c:choose>
                                                <c:when test="${tracing}">
                                                        <a href="javascript:protracking('DELETE_AJAX')" class="btn btn-danger">取消追蹤</a>
                                                </c:when>
                                                <c:otherwise>
                                                        <a href="javascript:protracking('ADD_AJAX')" class="btn btn-success">加入追蹤</a>
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
                                    <i class="fa fa-shopping-cart" aria-hidden="true"></i>
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
 <div class="modal fade" id="myModal" role="dialog">
   </div>

<script>
    function protracking(todo){
        $.ajax({
            url:"<%=request.getContextPath()%>/protra/ProtracServlet",
            type : "post",
            data:{
                action:todo,
                pro_no:${pro_no}
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
    
    function add(){
        $.ajax({
            url:"<%=request.getContextPath()%>/product/ProductServlet",
            type : "post",
            data:{
                action:"ADD_TO_CAR_AJAX",
                productCount:$("#productCount").val(),
                pro_no:${pro_no}
            }, 
            error : function(xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            },
            success : function(response) {
                if(response=="out of amount"){
                    swal('數量錯誤',
                         '超過庫存，請降低數量',
                         'error'
                       )
                   $("#productCount").val(1);
                }
                else{
                    $(".simpleCart_total").text("NT$"+response);
                }
            }
        });       
    }
    
    function showmodal(action,rep_ob_no,reported_no,position){
    	if(action=='product'){
    		$('#myModal').load("<%=request.getContextPath()%>/Front_end/Report/Report.jsp?",{"rep_ob_no":rep_ob_no,"reported_no":reported_no,"position":position},
    		function(){$("#myModal").modal('show');}
    		)}
    }
</script>
<%@include file="pages/indexFooter.file"%>