<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Admin Website Online Book Store" />
        <meta name="author" content="ForthTeam" />
        <meta name="keyword" content="Creative, Dashboard, Admin,Book, Theme, Bootstrap, Responsive,Store " />
        <link rel="shortcut icon" href="img/favicon.png" />

        <title> Admin Panel </title>

        <!-- Bootstrap CSS -->    
        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <!-- bootstrap theme -->
        <link href="css/bootstrap-theme.css" rel="stylesheet" />
        <!--external css-->
        <!-- font icon -->
        <link href="css/elegant-icons-style.css" rel="stylesheet" />
        <link href="css/font-awesome.min.css" rel="stylesheet" />    
        <!-- Custom styles -->
        <link href="css/style.css" rel="stylesheet">
        <link href="css/style-responsive.css" rel="stylesheet" />
        <link href="css/jquery-ui-1.10.4.min.css" rel="stylesheet">

        <!-- =======================================================
            Author: Forth Team
        ======================================================= -->
    </head>

    <body>
        <!--navbar start-->

        <section id='container'>





            <!--header start-->
            <header class="header dark-bg">
                <div class="toggle-nav">
                    <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i class="icon_menu"></i></div>
                </div>

                <!--logo start-->
                <a href="index.html" class="logo">Book Store <span class="lite">Admin Panel</span></a>
                <!--logo end-->

                <div class="nav search-row" id="top_menu">
                    <!--  search form start -->
                    <ul class="nav top-menu">                    
                        <li>
                            <form class="navbar-form">
                                <input class="form-control" placeholder="Search" type="text">
                            </form>
                        </li>                    
                    </ul>
                    <!--  search form end -->                
                </div>

                <div class="top-nav notification-row">                
                    <!-- notificatoin dropdown start-->
                    <ul class="nav pull-right top-menu">

                        <!-- alert notification end-->
                        <!-- user login dropdown start-->
                        <li class="dropdown">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="profile-ava">
                                    <img alt="" width='40' height='40' src="img/admin.png">
                                </span>
                                <span class="username">Admin</span>
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu extended logout">
                                <div class="log-arrow-up"></div>
                                <li>
                                    <a href="login.html"><i class="icon_key_alt"></i> Log Out</a>
                                </li>
                            </ul>
                        </li>
                        <!-- user login dropdown end -->
                    </ul>
                    <!-- notificatoin dropdown end-->
                </div>
            </header>      
            <!--header end-->

            <!--sidebar start-->
            <aside>
                <div id="sidebar"  class="nav-collapse ">
                    <!-- sidebar menu start-->
                    <ul class="sidebar-menu">                
                        <li class="active">
                            <a class="" href="#">
                                <i class="icon_house_alt"></i>
                                <span>Home</span>
                            </a>
                        </li>
                        <li>                     
                            <a class="" href="AddProduct.html">
                                <i class="icon_book_alt"></i>
                                <span>Add Product</span>
                            </a>                                  
                        </li>
                        <li>                     
                            <a class="" href="ViewUsers.html">
                                <i class="icon_group"></i>
                                <span>View Customers</span>
                            </a>
                        </li>
                    </ul>
                    <!-- sidebar menu end-->
                </div>
            </aside>
            <!--sidebar end-->
            <section id="main-content">
                <section class="wrapper">            
                    <!--overview start-->
                    <div class="row">
                        <div class="col-lg-12">
                            <h3 class="page-header"><i class="fa fa-laptop"></i>Home Page</h3>
                            <ol class="breadcrumb">
                                <li><i class="fa fa-home"></i><a href="test.html">Home</a></li>
                                <!--	<li><i class="fa fa-laptop"></i><a href="test.html"></a></li>		-->				  	
                            </ol>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='#' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-home"></i>
                                    <div class="count">6000 </div>
                                    <div class="title">Home </div>						
                                </div><!--/.info-box-->			
                            </a>
                        </div><!--/.col-->

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='#' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-book"></i>
                                    <div class="count">6000 </div>
                                    <div class="title">Add Product</div>						
                                </div><!--/.info-box-->			
                            </a>
                        </div><!--/.col-->

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='#' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-group"></i>
                                    <div class="count">6000 </div>
                                    <div class="title">View Customers</div>						
                                </div><!--/.info-box-->		
                            </a>
                        </div><!--/.col-->


                    </div><!--/.row-->

                    <div class="row">

                        <div class="container">
                            <div class="row">
                                <div class="col-sm-12 col-md-10 col-md-offset-1">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>Product</th>
                                                <th>Quantity</th>
                                                <th class="text-center">Price</th>
                                                <th> total </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td class="col-sm-8 col-md-6">
                                                    <div class="media">
                                                        <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;">
                                                        <div class="media-body">
                                                            <h4 class="media-heading"><a href="#">Product name</a></h4>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="col-sm-1 col-md-1" style="text-align: center">
                                                    <input type="number" class="form-control" id="quantity" value="3" disabled >
                                                </td>
                                                <td class="col-sm-1 col-md-1 text-center"><strong>$4.87</strong></td>
                                                <td class="col-sm-1 col-md-1 text-center"><strong>$14.61</strong></td>
                                                
                                            </tr>
                                           
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td>   </td>
                                                <td>   </td>
                                                <td><h3>Total</h3></td>
                                                <td class="text-right"><h3>$31.53</h3></td>
                                            </tr>
                                            
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>


                    </div>

                </section>
                <div class="text-left">
                    <div class="credits">
                        <!-- 
                                All the links in the footer should remain intact. 
                                You can delete the links only if you purchased the pro version.
                                Licensing information: https://bootstrapmade.com/license/
                                Purchase the pro version form: https://bootstrapmade.com/buy/?theme=NiceAdmin
                        -->
                        <a href="https://bootstrapmade.com/free-business-bootstrap-themes-website-templates/">This Website Is made </a> by <a href="https://bootstrapmade.com/">The Forth Team</a>
                    </div>
                </div>

            </section>



        </section>




        <!-- javascripts -->
        <script src="js/jquery.js"></script>
        <script src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
        <!-- bootstrap -->
        <script src="js/bootstrap.min.js"></script>
        <!-- nice scroll -->
        <script src="js/jquery.scrollTo.min.js"></script>
        <script src="js/jquery.nicescroll.js" type="text/javascript"></script>
        <!--script for this page only-->
        <script src="js/jquery.rateit.min.js"></script>
        <!-- custom select -->
        <script src="js/jquery.customSelect.min.js" ></script>

        <!--custome script for all page-->
        <script src="js/scripts.js"></script>
        <!-- custom script for this page-->
        <script src="js/jquery.autosize.min.js"></script>
        <script src="js/jquery.placeholder.min.js"></script>
        <script src="js/gdp-data.js"></script>	
        <script src="js/morris.min.js"></script>
        <script src="js/sparklines.js"></script>	
        <script src="js/jquery.slimscroll.min.js"></script>

        <!-- javascripts -->
    </body>
</html>