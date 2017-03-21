<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Admin Website Online Book Store" />
        <meta name="author" content="ForthTeam" />
        <meta name="keyword" content="Creative, Dashboard, Admin,Book, Theme, Bootstrap, Responsive,Store " />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/pages/img/favicon.png" />

        <title> Admin Panel </title>

        <!-- Bootstrap CSS -->    
        <link href="${pageContext.request.contextPath}/pages/css/bootstrap.min1.css" rel="stylesheet" />
        <!-- bootstrap theme -->
        <link href="${pageContext.request.contextPath}/pages/css/bootstrap-theme.css" rel="stylesheet" />
        <!--external css-->
        <!-- font icon -->
        <link href="${pageContext.request.contextPath}/pages/css/elegant-icons-style.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/pages/css/font-awesome.css" rel="stylesheet" />    
        <!-- Custom styles -->
        <link href="${pageContext.request.contextPath}/pages/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/style-responsive.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/pages/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
        <!-- =======================================================
            Author: Forth Team
        ======================================================= -->
    </head>

    <body>
        <!--navbar start-->
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
                            <form class="navbar-form" action="javascript:searchUsers(1);" method="post"  >
                                <input class="form-control" id="search" name="search"  placeholder="Search" type="text">
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
                                    <img alt="" width='40' height='40' src="${(pageContext.request.contextPath).concat('/pages/img/admin1.png')}">
                                </span>
                                <span class="username">Admin</span>
                            </a>
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
                            <a class="" href="HomeServletController">
                                <i class="icon_house_alt"></i>
                                <span>Home</span>
                            </a>
                        </li>
                        <li>                     
                            <a class="" href="AddProductController">
                                <i class="icon_book_alt"></i>
                                <span>Add Product</span>
                            </a>                                  
                        </li>
                        <li>                     
                            <a class="" href="ViewUsersController">
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
                                <li><i class="fa fa-home"></i><a href="HomeServletController">Home</a></li>
                                <!--	<li><i class="fa fa-laptop"></i><a href="test.html"></a></li>		-->				  	
                            </ol>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='HomeServletController' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-home"></i>
                                    <div class="count" id="productCount1" > ${ requestScope.productsCount } </div>
                                    <div class="title">Home </div>						
                                </div><!--/.info-box-->			
                            </a>
                        </div><!--/.col-->

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='AddProductController' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-book"></i>
                                    <div class="count" id="productCount2" > ${ requestScope.productsCount } </div>
                                    <div class="title">Add Product</div>						
                                </div><!--/.info-box-->			
                            </a>
                        </div><!--/.col-->

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='ViewUsersController' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-group"></i>
                                    <div class="count" id="userscount" > ${ requestScope.usersCount } </div>
                                    <div class="title">View Customers</div>						
                                </div><!--/.info-box-->		
                            </a>
                        </div><!--/.col-->


                    </div><!--/.row-->


                    <div class='row'>

                        <div class="container">
                            <div class="row">


                                <div class="col-md-12">
                                    <h4>Users Information</h4>
                                    <div class="table-responsive">


                                        <table id="mytable" class="table table-bordred table-striped">

                                            <thead>

                                           
                                            <th>Name</th>
                                            <th>Email</th>
                                            <th>Phone</th>
                                            <th>Address</th>
                                            <th>Country</th>
                                            <th>Gender</th>
                                            <th>DOB</th>
                                            <th>Job</th>
                                            <th>View Order History</th>

                                            </thead>

                                            <tbody id="tbody">
                                                <c:forEach var="row" items="${requestScope.clients}" >
                                                    <tr id="${row.getEmail()}">
                                                        <td>${row.getName()}</td>
                                                        <td>${row.getEmail()}</td>
                                                        <td>${row.getPhone()}</td>
                                                        <td>${row.getAddress()}</td>
                                                        <td>${row.getCountry()}</td>
                                                        <td>
                                                        <c:if test="${row.getGender() eq 'm'}">
                                                            male
                                                        </c:if>
                                                        <c:if test="${row.getGender() eq 'f'}">
                                                            female
                                                        </c:if>   
                                                        </td>
                                                        <td>${row.getBirthday()}</td>
                                                        <td>${row.getJob()}</td>
                                                        <td><button type="button" onclick="javascript:window.location.href='ViewOrdersHistory?userMail=${row.getEmail()}';" class="btn btn-success">View Orders History</button></td>
                                                    </tr>
                                                </c:forEach>        
                                            </tbody>

                                        </table>

                                        <div class="clearfix"></div>
                                        <ul class="pagination pull-right" id="pagination" >

                                         <!--   <li><a href="#"><span class="fa fa-chevron-right"></span></a></li> -->
                                        </ul>
                                    </div>

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
                        <a href="#">This Website Is made </a> by <a href="#">The Forth Team</a>
                    </div>
                </div>

            </section>



        </section>




        <!-- javascripts -->
        <script src="${pageContext.request.contextPath}/pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/jquery-1.8.3.min.js"></script>
        <script type="${pageContext.request.contextPath}/pages/text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
        <!-- bootstrap -->
        <script src="${pageContext.request.contextPath}/pages/js/bootstrap.min.js"></script>
        <!-- nice scroll -->
        <script src="${pageContext.request.contextPath}/pages/js/jquery.scrollTo.min.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/jquery.nicescroll.js" type="text/javascript"></script>
        <!--script for this page only-->
        <script src="${pageContext.request.contextPath}/pages/js/jquery.rateit.min.js"></script>
        <!-- custom select -->
        <script src="${pageContext.request.contextPath}/pages/js/jquery.customSelect.min.js" ></script>

        <!--custome script for all page-->
        <script src="${pageContext.request.contextPath}/pages/js/scripts.js"></script>
        <!-- custom script for this page-->
        <script src="${pageContext.request.contextPath}/pages/js/jquery.autosize.min.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/jquery.placeholder.min.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/gdp-data.js"></script>	
        <script src="${pageContext.request.contextPath}/pages/js/morris.min.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/sparklines.js"></script>	
        <script src="${pageContext.request.contextPath}/pages/js/jquery.slimscroll.min.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/mali/home.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/mali/viewusers.js"></script>
        <script> makePagination(${requestScope.usersCount}, 3);</script>

        <!-- javascripts -->

    </body>
</html>