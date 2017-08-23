<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="com.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="mall/pages/indexHeader.file"%>
<%
    ProductService productService=new ProductService();
    List<HashMap<String, Double>> avgScoreList=productService.getAllAvgSorce();
    List<String>proList=new ArrayList<String>();
    List<Double>scoreList=new ArrayList<Double>();
    
    for(int i=0;i<avgScoreList.size();i++){
        proList.add(avgScoreList.get(i).keySet().toArray()[0].toString());
        scoreList.add(avgScoreList.get(i).get(proList.get(i)));
    }

    pageContext.setAttribute("proList", proList);
    pageContext.setAttribute("scoreList", scoreList);
%>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>
<div class="container">
    <svg style="height: 800px;width: 800px;"></svg>
</div>

<script type="text/javascript">
    var nodes = [];
    <%for(int i=0;i<proList.size();i++){%>
        nodes.push({
                idx : <%=i%>,
                r:(parseInt(<%=proList.get(i)%>)%1000)/4
        });
        <%}%>
    var circles = d3.select("svg").selectAll("circle").data(nodes).enter().append("circle");
    var color=d3.scale.linear().domain([0,1024]).range(["#f00","#00f"]);
    var force = d3.layout.force() // 建立 Layout
    .nodes(nodes) // 綁定資料
    .size([ 800, 800 ]) // 設定範圍
    .on("tick", tick) // 設定 tick 函式
    .start();
    
    function tick() { // tick 會不斷的被呼叫
        circles.attr({
        cx : function(it) {
            return it.x;
        }, // it.x 由 Force Layout 提供
        cy : function(it) {
            return it.y;
        }, // it.y 由 Force Layout 提供
        r : function(it) { return it.r; },
        fill: function(it) { return color((it.r)*25.6);}
        });
    }
</script>
<%@include file="mall/pages/indexFooter.file"%>