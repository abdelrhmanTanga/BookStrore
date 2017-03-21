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
                            <h3 class="page-header"><i class="fa fa-laptop"></i>Add Product Page</h3>
                            <ol class="breadcrumb">
                                <li><i class="fa fa-home"></i><a href="Home.jsp">Home</a> <a href="#"> / Add Product </a> </li>
                                <!--	<li><i class="fa fa-laptop"></i><a href="test.html"></a></li>		-->				  	
                            </ol>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='HomeServletController' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-home"></i>
                                    <div class="count" id="productCount1" > ${requestScope.productsCount} </div>
                                    <div class="title">Home </div>						
                                </div><!--/.info-box-->			
                            </a>
                        </div><!--/.col-->

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='AddProductController' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-book"></i>
                                    <div class="count" id="productCount2" > ${requestScope.productsCount} </div>
                                    <div class="title">Add Product</div>						
                                </div><!--/.info-box-->			
                            </a>
                        </div><!--/.col-->

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='HomeServletController' >
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

                            <div class="alert alert-success" style="display:none;" id="danger-alert-product-add">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>The Product Added Successfully! </strong>
                                the product added to db.
                            </div>


                            <div class="alert alert-danger" style="display:none;" id="danger-alert-product-not-added">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>Failure In Adding The Product To DB </strong>
                                The ISBN Of The Product Already Exist!
                            </div>


                            <div class="alert alert-danger" style="display:none;" id="danger-alert">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>Failure In Uploading Image! </strong>
                                The File Must Be png or jpg Image.
                            </div>

                            <div class="alert alert-danger" style="display:none;" id="danger-alert-size">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>Failure In Uploading Image! </strong>
                                The Image Size Must Not Be Greater Than 1 MB.
                            </div>

                            <div class="alert alert-danger" style="display:none;" id="danger-alert-filename">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>Failure In Uploading Image! </strong>
                                The Image Name Must Not Be Greater Than 50 Character.
                            </div>

                            <section class="panel">  
                                <header class="panel-heading">
                                    Add Product
                                </header>
                                <div class="panel-body">
                                    <div class="form">
                                        <form class="form-validate form-horizontal" id="addproductform" method="post" action="javascript:addProductToDB();" enctype="multipart/form-data">
                                            <div class="form-group ">
                                                <label for="pname" class="control-label col-lg-2">Product Name(5-20 character) <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control " id="pname" name="pname" oninvalid="setCustomValidity('must be from 5 to 20 character')" onchange="try{setCustomValidity('')}catch(e){}" pattern="^[a-zA-Z][a-zA-Z0-9\s]{5,25}"  type="text" required />
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <label for="quantity" class="control-label col-lg-2">Quantity <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control" id="quantity" type="number" oninvalid="setCustomValidity('max quantity is 9999')" onchange="try{setCustomValidity('')}catch(e){}" min="1" max="9999" name="quantity" required />
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <label for="author" class="control-label col-lg-2">Author(5-20 character) <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control " name="author" oninvalid="setCustomValidity('must be from 5 to 20 digits')" onchange="try{setCustomValidity('')}catch(e){}" id="author" type="text" minlength="5" pattern="^[a-zA-Z][a-zA-Z0-9\s]{5,25}" required />
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <label for="isbn" class="control-label col-lg-2">ISBN(13 digit) <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control" id="isbn" oninvalid="setCustomValidity('must be 13 digits')" onchange="try{setCustomValidity('')}catch(e){}" name="isbn" type="text" pattern="[0-9]{13}"  required />
                                                </div>
                                            </div>                                      
                                            <div class="form-group ">
                                                <label for="description" class="control-label col-lg-2">Description(20-100 character) <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control " id="description" oninvalid="setCustomValidity('must be from 20 to 100 character')" onchange="try{setCustomValidity('')}catch(e){}" name="description" pattern="^[a-zA-Z][a-zA-Z0-9\s]{20,100}"   required />
                                                </div>
                                            </div>

                                            <div class="form-group ">
                                                <label for="price" class="control-label col-lg-2">Price <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control" id="price" oninvalid="setCustomValidity('minimum is 10$ and max is 9999$')" onchange="try{setCustomValidity('')}catch(e){}" name="price" min="10" max="9999" type="number" required />
                                                </div>
                                            </div>   

                                            <div class="form-group col-sm-12">

                                                <div class="col-sm-2"  >
                                                    <label for="category" style="margin-left: 90px;" class="control-label col-lg-2">Category<span class="required">*</span> </label>
                                                </div>
                                                <div class="col-sm-4">
                                                    <select class="form-control selectpicker" id="category" name="category" required >

                                                        <c:forEach var="row" items="${ requestScope.categories }">
                                                            <option> ${ row.getName() } </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="col-sm-2">
                                                    <label for="newcategory" class="control-label "  style="margin-left: 35px;" >Add New Category</label></div>
                                                <div class="col-sm-2"><input class="form-control " id="newcategory" name="newcategory" type="text"  pattern="^[a-zA-Z][a-zA-Z0-9\s]{5,40}"  /></div>
                                                <div class="col-sm-2"><button class="btn btn-primary form-control" type="button" onclick="addNewCategory()" >Add New Category</button> 
                                                </div>

                                            </div>
                                            
                                            <div class="form-group " style="display: none;" id="success-alert-new-categorysuccess1" >
                                                <label for="" class="control-label col-lg-6"></label>
                                                <div class="alert alert-success col-lg-6"  id="success-alert-new-category-success">
                                                        <button type="button" class="close" data-dismiss="alert">x</button>
                                                        <strong>Successfully Category Added! </strong>
                                                        The Category Added Successfully.
                                                </div>
                                             </div> 
                                            
                                            
                                            <div class="form-group " style="display: none;" id="danger-alert-new-category-fail1" >
                                                <label for="" class="control-label col-lg-6"></label>
                                                <div class="alert alert-danger col-lg-6"  id="danger-alert-new-category-fail">
                                                        <button type="button" class="close" data-dismiss="alert">x</button>
                                                        <strong>Failure In Adding Category! </strong>
                                                        This Category Name Is Already Exist.
                                                </div>
                                             </div>   
                                            
                                            
                                            <div class="form-group " style="display: none;" id="danger-alert-new-category-empty1" >
                                                <label for="" class="control-label col-lg-6"></label>
                                                <div class="alert alert-danger col-lg-6"  id="danger-alert-new-category-empty">
                                                        <button type="button" class="close" data-dismiss="alert">x</button>
                                                        <strong>Failure In Adding Category! </strong>
                                                       Please Add Category Name To Add.
                                                </div>
                                             </div>   



                                            <div class="form-group ">
                                                <label for="pimage" class="control-label col-lg-2">Image <span class="required">*</span></label>
                                                <div class="col-lg-10">
                                                    <input class="form-control" id="pimage" name="pimage" type="file"  accept=' image/jpeg, image/png' required />
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
        <script src="${pageContext.request.contextPath}/pages/js/mali/addproduct.js" ></script>
        <!-- javascripts -->

        <c:if test="${requestScope.check == false}" >
            <!-- failed message  -->
            <script> setData("${requestScope.name}",${requestScope.quantity}, "${requestScope.author}", "${requestScope.description}",${requestScope.price}, "${requestScope.category}");</script>    
        </c:if>

    </body>
</html>
