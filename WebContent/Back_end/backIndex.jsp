<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    //     String adm_no=String.valueOf(session.getAttribute("adm_no"));
    //     AdministratorService administratorservice=new AdministratorService();
    //     AdministratorVO administratorVO=administratorservice.getOne(adm_no);
    //     session.setAttribute("administratorVO", administratorVO);
    String preLocation = request.getContextPath() + "/back_end";
    pageContext.setAttribute("preLocation", preLocation);
%>
<%@include file="pages/backHeader.file"%>
<div id="content">
    <div class="content-wrapper">
        <div class="row">
            <ul id="crumb" class="breadcrumb">
            </ul>
        </div>
    </div>
    <div class="outlet">
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="carousel-tile carousel vertical slide">
                    <div class="carousel-inner">
                        <div class="item active">
                            <div class="tile red">
                                <div class="tile-icon">
                                    <i class="br-warning s64"></i>
                                </div>
                                <div class="tile-content">
                                    <div class="number">2</div>
                                    <h2>檢舉</h2>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="carousel-tile carousel slide">
                    <div class="carousel-inner">
                        <div class="item active">
                            <div class="tile blue">
                                <div class="tile-icon">
                                    <i class="ec-mail s64"></i>
                                </div>
                                <div class="tile-content">
                                    <div class="number">24</div>
                                    <h2>信箱</h2>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="carousel-tile carousel vertical slide">
                    <div class="carousel-inner" >
                        <div class="item active">
                            <div class="tile green">
                                <div class="tile-icon">
                                    <i class="ec-users s64"></i>
                                </div>
                                <div class="tile-content">
                                    <div class="number">325</div>
                                    <h3>New users</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                <div class="carousel-tile carousel slide">
                    <div class="carousel-inner">
                        <div class="item active">
                            <div class="tile teal">
                                <div class="tile-icon">
                                    <i class="ec-images s64"></i>
                                </div>
                                <div class="tile-content">
                                    <div class="number">45</div>
                                    <h3>New images</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-12 sortable-layout">
            <div class="row">
                <div class="panel panel-teal toggle panelMove panelClose panelRefresh">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <i class="im-bars"></i>
                           <div>檢舉清單</div> 
                        </h4>
                    </div>
                    <div class="panel-body">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>項目</th>
                                    <th>標題</th>
                                    <th>檢舉者</th>
                                    <th>檢舉時間</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>資料</td>
                                    <td>資料</td>
                                    <td>資料</td>
                                    <td>資料</td>
                                </tr>
                                <tr>
                                    <td>資料</td>
                                    <td>資料</td>
                                    <td>資料</td>
                                    <td>資料</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer teal-bg">
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="tile teal m0">
                                <div class="tile-content text-center pl0 pr0">
                                    <div id="countToday" class="number">75</div>
                                    <h3>Today</h3>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="tile teal m0">
                                <div class="tile-content text-center pl0 pr0">
                                    <div id="countYesterday" class="number">69</div>
                                    <h3>Yesterday</h3>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="tile teal m0">
                                <div class="tile-content text-center pl0 pr0">
                                    <div id="countWeek" class="number">380</div>
                                    <h3>This Week</h3>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="tile teal m0">
                                <div class="tile-content text-center pl0 pr0">
                                    <div id="countTotal" class="number">1254</div>
                                    <h3>Total</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="pages/backFooter.file"%>