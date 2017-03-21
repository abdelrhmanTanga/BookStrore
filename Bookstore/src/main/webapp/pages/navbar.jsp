<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>BOOK Store</title>
        <link href="${pageContext.request.contextPath}/pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/price-range.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/responsive.css" rel="stylesheet">
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
        <script src="${pageContext.request.contextPath}/pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/jquery.scrollUp.min.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/price-range.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/jquery.prettyPhoto.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/main.js"></script>
        <style> 
            a.notif {
                position: relative;
                display: block;

                background-size: contain;
                text-decoration: none;
            }
            .num {
                text-align: center;
                position: absolute;
                right: 40px;
                width: 17px;
                height: 17px;
                top: -5px;
                color: white;
                border-radius: 50%;
                background-color: tomato;
            }

        </style>
    </head><!--/head-->

    <body>

        <c:if test="${loggedIn==null}">
            <header id="header"><!--header-->

                <div class="header-middle"><!--header-middle-->
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-3">
                                <div class="logo pull-left">
                                    <a href="/BookStore/productviewer"><img src="${pageContext.request.contextPath}/pages/images/logo.png" alt="" /></a>
                                </div>

                            </div>
                            <div class="col-sm-5">
                                <div class="search_box pull-right">
                                    <form action="/BookStore/Search" method="post">
                                        <div class="input-group">
                                            <input type="text" placeholder="Search" name="searchkey"/>
                                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>

                                        </div>
                                    </form>
                                </div>
                            </div>

                            <div class="col-sm-4">
                                <div class="shop-menu pull-right">
                                    <ul class="nav navbar-nav">

                                        <!-- <li><a href="#"><i class="fa fa-user"></i> Account</a></li> -->

                                        <li><a href="/BookStore/productviewer"><i class="fa fa-lock"></i> home</a></li>
                                        <li><a href="/BookStore/cart"><i class="fa fa-shopping-cart"><span class="num" id="loggedCart">${loggedCart}</span></i> Cart</a></li>
                                        <li><a href="${pageContext.request.contextPath}/pages/temp.jsp"><i class="fa fa-lock"></i> Login</a></li>
                                        <!--<li><a href="${pageContext.request.contextPath}/pages/temp.jsp"><i class="fa fa-lock"></i> Signup</a></li>-->
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--/header-middle-->


            </header><!--/header-->
        </c:if>
        <c:if test="${loggedIn != null}">
            <header id="header"><!--header-->

                <div class="header-middle"><!--header-middle-->
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-3">
                                <div class="logo pull-left">
                                    <a href="/BookStore/productviewer"><img src="${pageContext.request.contextPath}/pages/images/logo.png" alt="" /></a>
                                </div>

                            </div>
                            <div class="col-sm-5">
                                <div class="search_box pull-right">
                                    <form action="/BookStore/Search" method="post">
                                        <div class="input-group">
                                            <input type="text" placeholder="Search"/>
                                            <div class="input-group-btn">
                                                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <div class="col-sm-4">
                                <div class="shop-menu pull-right">
                                    <ul class="nav navbar-nav">
                                        <li><a href="/BookStore/productviewer"><i class="fa fa-lock"></i> home</a></li>
                                        <li><a href="#"><i class="fa fa-user"></i>${loggedIn}</a></li> 
                                        <li><a href="/BookStore/cart"><i class="fa fa-shopping-cart"><span class="num" id="loggedCart">${loggedCart}</span></i> Cart</a></li> 
                                        <li><a href="/BookStore/signout"><i class="fa fa-lock"></i> Logout</a></li>

                                        <!-- <li><a href="login.html"><i class="fa fa-lock"></i> Signup</a></li>-->
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--/header-middle-->


            </header><!--/header-->    

        </c:if>

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
        <!-- Category Part --
        <div class="col-sm-3">
            <div class="left-sidebar">
                <h2>Category</h2>
                <div class="panel-group category-products" id="accordian">
        <c:forEach items="${categories}" var="category">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title"><a href="/BookStore/ProductsByCategory?id=${category.id}" id="${category.id}">${category.name}</a></h4>
                </div>
            </div>
        </c:forEach>
        -->
    </div><!--/category-products-->


    <!--
                    <div class="price-range">
                        <h2>Price Range</h2>
                        <div class="well text-center">
                            <form action="BookStore/SearchByPrice" method="post">
                            <div class="input-group">
                                <input name="price" type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" placeholder="Search" >
                                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>  
                                <input type="range" min="10" max="1000">
                            </div>
                                </form><b class="pull-left">$ 0</b> <b class="pull-right">$ 6000</b></div>
                    </div>-->


</div>
</div>
</body>
</html>
