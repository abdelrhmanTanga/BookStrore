<%-- 
    Document   : Slider
    Created on : Mar 21, 2017, 12:18:03 AM
    Author     : yasmeen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
           <section id="slider"><!--slider-->
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div id="slider-carousel" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
                                <li data-target="#slider-carousel" data-slide-to="1"></li>
                                <li data-target="#slider-carousel" data-slide-to="2"></li>
                            </ol>

                            <div class="carousel-inner">
                                <div class="item active">

                                    <div class="col-sm-12">
                                        <img src="${pageContext.request.contextPath}/pages/images/home/1.jpg" class="girl img-responsive" alt="" />

                                    </div>
                                </div>
                                <div class="item">
                                    <div class="col-sm-12">
                                        <img src="${pageContext.request.contextPath}/pages/images/home/2.jpg" class="girl img-responsive" alt="" />

                                    </div>
                                </div>

                                <div class="item">
                                    <div class="col-sm-12">
                                        <img src="${pageContext.request.contextPath}/pages/images/home/3.jpg" class="girl img-responsive" alt="" />

                                    </div>
                                </div>

                            </div>

                            <a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
                                <i class="fa fa-angle-left"></i>
                            </a>
                            <a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </div>

                    </div>
                </div>
            </div>
        </section><!--/slider-->
    </body>
</html>
