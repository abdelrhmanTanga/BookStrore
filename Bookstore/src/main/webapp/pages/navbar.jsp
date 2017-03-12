<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>BOOK Store</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </head><!--/head-->

    <body>

        <c:if test="${logged==null}">
        <header id="header"><!--header-->

            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="logo pull-left">
                                <a href="viewproducts.jsp"><img src="images/logo.png" alt="" /></a>
                            </div>

                        </div>
                        <div class="col-sm-5">
                            <div class="search_box pull-right">
                                <form action="/search" method="post">
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
                                    
                                   <!-- <li><a href="#"><i class="fa fa-user"></i> Account</a></li> -->

                                     <li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Cart</a></li> 
                                    <li><a href="login.html"><i class="fa fa-lock"></i> Login</a></li>
                                      <li><a href="login.html"><i class="fa fa-lock"></i> Signup</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->


        </header><!--/header-->
</c:if>
        <c:if test="${logged != null}">
        <header id="header"><!--header-->

            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="logo pull-left">
                                <a href="viewproducts.jsp"><img src="images/logo.png" alt="" /></a>
                            </div>

                        </div>
                        <div class="col-sm-5">
                            <div class="search_box pull-right">
                                <form action="/search" method="post">
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
                                    
                                   <li><a href="#"><i class="fa fa-user"></i> Account</a></li> 

                                     <li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Cart</a></li> 
                                    <li><a href="login.html"><i class="fa fa-lock"></i> Logout</a></li>
                                     <!-- <li><a href="login.html"><i class="fa fa-lock"></i> Signup</a></li>-->
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->


        </header><!--/header-->    
            
        </c:if>
        
        
        
        
        
        
        
        <!-- Category Part -->
        <div class="col-sm-3">
            <div class="left-sidebar">
                <h2>Category</h2>
                <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                    <c:forEach items="${categories}" var="category">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title"><a href="#">${category.name}</a></h4>
                        </div>
                    </div>
                    </c:forEach>
                    
                </div><!--/category-products-->

               

                <div class="price-range"><!--price-range-->
                    <h2>Price Range</h2>
                    <div class="well text-center">
                        <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
                        <b class="pull-left">$ 0</b> <b class="pull-right">$ 600</b>
                    </div>
                </div><!--/price-range-->
                <div class="shipping text-center"><!--shipping-->
                    <img src="images/home/shipping.jpg" alt="" />
                </div><!--/shipping-->

            </div>
        </div>
    </body>
</html>