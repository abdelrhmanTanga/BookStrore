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
        <link href="${pageContext.request.contextPath}/pages/css/bootstrap.min.css" rel="stylesheet" />
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
        <script src="${pageContext.request.contextPath}/pages/js/mali/addproduct.js" ></script>
        <!-- =======================================================
            Author: Forth Team
        ======================================================= -->
    </head>

    <body>
        
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                    <img src="${(pageContext.request.contextPath).concat('/pages/img/admin1.png')}" alt="admin"  width='40' height='40'   />
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
                            <a class="" href="Home.jsp">
                                <i class="icon_house_alt"></i>
                                <span>Home</span>
                            </a>
                        </li>
                        <li>                     
                            <a class="" href="#">
                                <i class="icon_book_alt"></i>
                                <span>Add Product</span>
                            </a>                                  
                        </li>
                        <li>                     
                            <a class="" href="ViewUsers.jsp">
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
                            <h3 class="page-header"><i class="fa fa-laptop"></i>Add Product Page</h3>
                            <ol class="breadcrumb">
                                <li><i class="fa fa-home"></i><a href="Home.jsp">Home</a> <a href="#"> / Add Product </a> </li>
                                <!--	<li><i class="fa fa-laptop"></i><a href="test.html"></a></li>		-->				  	
                            </ol>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='#' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-home"></i>
                                    <div class="count"> ${requestScope.productsCount} </div>
                                    <div class="title">Home </div>						
                                </div><!--/.info-box-->			
                            </a>
                        </div><!--/.col-->

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='#' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-book"></i>
                                    <div class="count"> ${requestScope.productsCount} </div>
                                    <div class="title">Add Product</div>						
                                </div><!--/.info-box-->			
                            </a>
                        </div><!--/.col-->

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='#' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-group"></i>
                                    <div class="count"> ${requestScope.usersCount} </div>
                                    <div class="title">View Customers</div>						
                                </div><!--/.info-box-->		
                            </a>
                        </div><!--/.col-->




                    </div><!--/.row-->

                    <!-- Form validations -->              
                    <div class="row">
                       
                        
                                
                        <div class="col-lg-12">
                        
                            <c:if test="${requestScope.check == true}" >   
                                <!-- success message  -->
                                <div class="alert alert-success">
                                    <strong>Success!</strong> The Book Added Successfully.
                                </div>
                            </c:if>
                        
                            <c:if test="${requestScope.check == false}" >
                                <!-- failed message  -->
                                <div class="alert alert-danger">
                                    <strong>Failed!</strong> The ISBN Already Exist.
                                </div>
                            </c:if>
                        
                            <section class="panel">  
                                <header class="panel-heading">
                                    Add Product
                                </header>
                                <div class="panel-body">
                                    <div class="form">
                                        <form class="form-validate form-horizontal" id="feedback_form" method="post" action="/OnlineBookstore/adminadd" enctype="multipart/form-data">
                                            <div class="form-group ">
                                                <label for="pname" class="control-label col-lg-2">Product Name <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control " id="pname" name="pname" minlength="5" maxlength="30"  type="text" required />
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <label for="quantity" class="control-label col-lg-2">Quantity <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control" id="quantity" type="number" min="1" max="9999" name="quantity" required />
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <label for="author" class="control-label col-lg-2">Author <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control " id="author" type="text" minlength="5" maxlength="30" name="author" required />
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <label for="isbn" class="control-label col-lg-2">ISBN <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control" id="isbn" name="isbn" type="number" min="1" max="9999999999999"  required />
                                                </div>
                                            </div>                                      
                                            <div class="form-group ">
                                                <label for="description" class="control-label col-lg-2">Description <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <textarea class="form-control " id="description" name="description" minlength="20" maxlength="150" required></textarea>
                                                </div>
                                            </div>
                                            
                                            <div class="form-group ">
                                                <label for="price" class="control-label col-lg-2">Price <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control" id="price" name="price" min="10" max="9999" type="number" required />
                                                </div>
                                            </div>   
                                            
                                            <div class="form-group ">
                                                <label for="category" class="control-label col-lg-2">Category <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <select class="form-control selectpicker" id="category" name="category" required >
                                                        
                                                        <c:forEach var="row" items="${ requestScope.categories }">
                                                               <option> ${ row.getName() } </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>   
                                            
                                            <div class="form-group ">
                                                <label for="pimage" class="control-label col-lg-2">Image <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control" id="pimage" name="pimage"  type="file" required />
                                                </div>
                                            </div>   
                                            
                                            <div class="form-group">
                                                <div class="col-lg-offset-2 col-lg-10">
                                                    <center>
                                                        <button class="btn btn-primary" type="submit">Save</button>
                                                        <button class="btn btn-primary" type="reset">Clear</button>
                                                    </center>
                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </section>
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

        <!-- javascripts -->
        
        <c:if test="${requestScope.check == false}" >
            <!-- failed message  -->
            <script> setData("${requestScope.name}",${requestScope.quantity},"${requestScope.author}","${requestScope.description}",${requestScope.price},"${requestScope.category}");  </script>    
        </c:if>
        
    </body>
</html>
