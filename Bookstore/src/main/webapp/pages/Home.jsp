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
                            <form class="navbar-form" action="javascript:productSearch(1);" method="post"  >
                                <input class="form-control" id="search" name="search"  placeholder="Search" type="text">

                                <select id="searchcategory" style="height:50% ;" name="searchcategory" class="form-control selectpicker">
                                        <option>All Categories</option>
                                    <c:forEach var="row" items="${ requestScope.categories }">
                                        <option> ${ row.getName() } </option>
                                    </c:forEach>
                                </select>

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
                                <li><i class="fa fa-home"></i><a href="#">Home</a></li>
                                <!--	<li><i class="fa fa-laptop"></i><a href="test.html"></a></li>		-->				  	
                            </ol>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='HomeServletController' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-home"></i>
                                    <div id="productCount1" class="count"> ${ requestScope.productsCount } </div>
                                    <div class="title">Home </div>						
                                </div><!--/.info-box-->			
                            </a>
                        </div><!--/.col-->

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='AddProductController' >
                                <div class="info-box blue-bg">
                                    <i class="fa fa-book"></i>
                                    <div id="productCount2" class="count"> ${ requestScope.productsCount } </div>
                                    <div class="title">Add Product</div>						
                                </div><!--/.info-box-->			
                            </a>
                        </div><!--/.col-->

                        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                            <a href='#' >
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

                                <div class="alert alert-success" style="display:none;" id="alert-product-add">
                                    <button type="button" class="close" data-dismiss="alert">x</button>
                                    <strong>The Product Added Successfully! </strong>
                                    the product added to db.
                                </div>


                                <div class="col-md-12">
                                    <h4>Products Information</h4>
                                    <div class="table-responsive">


                                        <table id="mytable" class="table table-bordred table-striped">

                                            <thead>

                                            <th><input type="checkbox" id="checkall" onchange="selectAll()" /></th>
                                            <th>Product Name</th>
                                            <th>Available Quantity</th>
                                            <th>ISBN</th>
                                            <th>Price</th>
                                            <th>Category</th>
                                            <th>Edit</th>
                                            <th>Delete</th>

                                            </thead>

                                            <tbody id="tbody" >

                                                <c:forEach var="row" items="${requestScope.products}">
                                                    <tr id="${row.getId()}" >
                                                        <td><input type="checkbox" name="checkboxs" id="select${row.getId()}" class="checkthis" /></td>
                                                        <td>${row.getName()}</td>
                                                        <td>${row.getQuantity()}</td>
                                                        <td>${row.getISBN()}</td>
                                                        <td>${row.getPrice()}</td>
                                                        <td> <c:forEach  var="category" items="${requestScope.categories}" >  <c:if test="${row.getCategory()==category.getId()}" > ${category.getName()}   </c:if>  </c:forEach>  </td>
                                                        <td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" onclick="setUpdataData(this.id)" data-title="Edit" data-toggle="modal" id="edit${row.getId()}" data-target="#edit" ><span class="glyphicon glyphicon-pencil"></span></button></p></td>
                                                        <td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" onclick="deleteProduct(this.id)" data-title="Delete" data-toggle="modal" id="delete${row.getId()}" data-target="#delete" ><span class="fa fa-times"></span></button></p></td>
                                                    </tr>
                                                </c:forEach>    

                                            </tbody>

                                        </table>
                                        <button type="button"  class="btn btn-danger" data-toggle="modal" onclick="CheckIfProductsSelceted()" id="" data-target="" > Delete Selected Products </button>
                                        <div class="clearfix"></div>
                                        <ul id="pagination" class="pagination pull-right">
                                            <li class="disabled"><a href="#"><span class="fa fa-chevron-left"></span></a></li>
                                        </ul>

                                    </div>

                                </div>
                            </div>
                        </div>


                        <div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="fa fa-times" aria-hidden="true"></span></button>
                                        <h4 class="modal-title custom_align" id="Heading">Edit Product Detail</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form">
                                            <form class="form-validate form-horizontal" id="feedback_form" method="post" enctype="multipart/form-data" action="javascript:editProduct();" >
                                                <input type="hidden" id="pid" name="pid"  value="" required="" />
                                                <div class="form-group ">
                                                    <label for="pname" class="control-label col-lg-2">Product Name <span class="required">*</span></label>
                                                    <div class="col-lg-10">
                                                        <input class="form-control " id="pname" name="pname" oninvalid="setCustomValidity('must be from 5 to 25 character')" onchange="try {
                                                                    setCustomValidity('')
                                                                } catch (e) {
                                                                }" pattern="^[a-zA-Z][a-zA-Z0-9\s]{5,25}"  type="text" required />
                                                    </div>
                                                </div>
                                                <div class="form-group ">
                                                    <label for="quantity" class="control-label col-lg-2">Quantity <span class="required">*</span></label>
                                                    <div class="col-lg-10">
                                                        <input class="form-control" id="quantity" type="number" oninvalid="setCustomValidity('max quantity is 9999')" onchange="try {
                                                                    setCustomValidity('')
                                                                } catch (e) {
                                                                }"  min="1" max="9999" name="quantity" required />
                                                    </div>
                                                </div>
                                                <div class="form-group ">
                                                    <label for="author" class="control-label col-lg-2">Author <span class="required">*</span></label> <br>
                                                    <div class="col-lg-10">
                                                        <input class="form-control " id="author" name="author" type="text" oninvalid="setCustomValidity('must be from 5 to 20 digits')" onchange="try {
                                                                    setCustomValidity('')
                                                                } catch (e) {
                                                                }" minlength="5" pattern="^[a-zA-Z][a-zA-Z0-9\s]{5,25}" required />
                                                    </div>
                                                </div>
                                                <div class="form-group ">
                                                    <label for="isbn" class="control-label col-lg-2">ISBN <span class="required">*</span></label>
                                                    <div class="col-lg-10">
                                                        <input class="form-control" id="isbn" name="isbn" oninvalid="setCustomValidity('must be 13 digits')" onchange="try {
                                                                    setCustomValidity('')
                                                                } catch (e) {
                                                                }" type="text" pattern="[0-9]{13}"  required />
                                                    </div>
                                                </div>                                      
                                                <div class="form-group ">
                                                    <label for="description"  class="control-label col-lg-2">Description <span class="required">*</span></label>
                                                    <div class="col-lg-10">
                                                        <input class="form-control " oninvalid="setCustomValidity('must be from 20 to 100 character')" onchange="try {
                                                                    setCustomValidity('')
                                                                } catch (e) {
                                                                }" id="description" name="description" pattern="^[a-zA-Z][a-zA-Z0-9\s]{20,100}"   required />
                                                    </div>
                                                </div>

                                                <div class="form-group ">
                                                    <label for="price" class="control-label col-lg-2">Price <span class="required">*</span></label>
                                                    <div class="col-lg-10">
                                                        <input class="form-control" id="price" name="price" oninvalid="setCustomValidity('minimum is 10$ and max is 999$')" onchange="try {
                                                                    setCustomValidity('')
                                                                } catch (e) {
                                                                }" min="10" max="999" type="number" required />
                                                    </div>
                                                </div>   




                                                <div class="form-group ">
                                                    <label for="category" class="control-label col-lg-2">Category <span class="required">*</span></label>
                                                    <div class="col-lg-10" >
                                                        <select class="form-control selectpicker" id="category" name="category" required >
                                                            <c:forEach var="row" items="${ requestScope.categories }">
                                                                <option> ${ row.getName() } </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group ">
                                                    <label for="pimage" class="control-label col-lg-2">Image <span class="required"></span></label>
                                                    <div class="col-lg-10">
                                                        <input class="form-control" id="pimage" name="pimage" type="file"  accept=' image/jpeg, image/png'  />
                                                    </div>
                                                </div>   

                                                <div class="alert alert-danger" style="display:none;" id="danger-alert-product-not-updated">
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



                                                <div class="modal-footer ">
                                                    <button type="submit"  class="btn btn-warning btn-lg" id="" name="editbutton"   style="width: 100%;"  ><span class="fa fa-check-square"></span> Update</button>
                                                </div>
                                            </form>
                                        </div>

                                    </div>
                                    <!-- /.modal-content --> 
                                </div>
                                <!-- /.modal-dialog --> 
                            </div>
                        </div>        


                        <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="fa fa-times" aria-hidden="true"></span></button>
                                        <h4 class="modal-title custom_align" id="Heading">Delete This Product</h4>
                                    </div>
                                    <div class="modal-body">

                                        <div class="alert alert-danger"><span class="fa fa-warning"></span> Are you sure you want to delete this Product?</div>

                                    </div>
                                    <div class="modal-footer ">
                                        <button type="button" class="btn btn-success" name="deleteFinalName" id="" onclick="deleteFinal(this.id)" data-dismiss="modal" ><span class="fa fa-check"></span> Yes</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="fa fa-times"></span> No</button>
                                    </div>
                                </div>
                                <!-- /.modal-content --> 
                            </div>
                            <!-- /.modal-dialog --> 
                        </div>



                        <div class="modal fade" id="deleteselected" tabindex="-1" role="dialog" aria-labelledby="deleteSelecetdProducts" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="fa fa-times" aria-hidden="true"></span></button>
                                        <h4 class="modal-title custom_align" id="Heading">Delete Selected Products</h4>
                                    </div>
                                    <div class="modal-body">

                                        <div class="alert alert-danger"><span class="fa fa-warning"></span> Are you sure you want to delete Selected Products?</div>

                                    </div>
                                    <div class="modal-footer ">
                                        <button type="button" class="btn btn-success" name="deleteFinalName" id="" onclick="deleteSelectedProducts()" data-dismiss="modal" ><span class="fa fa-check"></span> Yes</button>
                                        <button type="button" id="closedialog" class="btn btn-default" data-dismiss="modal"><span class="fa fa-times"></span> No</button>
                                    </div>
                                </div>
                                <!-- /.modal-content --> 
                            </div>
                            <!-- /.modal-dialog --> 
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
        <script src="${pageContext.request.contextPath}/pages/js/mali/update.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/mali/delete.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/mali/searchproducts.js"></script>
        <script>  makePagination(${requestScope.productsCount},1)</script>
        <!-- javascripts -->
    </body>
</html>