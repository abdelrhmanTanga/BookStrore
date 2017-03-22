<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Checkout | E-Shopper</title>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/aja x/libs/jquery/3.1.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="${pageContext.request.contextPath}/pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/price-range.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    </head><!--/head-->

    <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 


        <div class="container">
            <table id="cart" class="table table-hover table-condensed">
                <thead>
                    <tr>
                        <th style="width:50%">Product</th>
                        <th style="width:10%">Price</th>
                        <th style="width:8%">Quantity</th>
                        <th style="width:22%" class="text-center">Subtotal</th>
                        <th style="width:10%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="${0}"/>

                    <c:forEach var="product" items="${orderInfo.products}" >
                        <tr>
                            <td data-th="Product">
                                <div class="row">
                                    <div class="col-sm-2 hidden-xs"><img src="${pageContext.request.contextPath}/pages/images/${product.getImage()}" alt="..." class="img-responsive"/></div>
                                    <div class="col-sm-10">
                                        <h4 class="nomargin">${product.getName()}</h4>
                                        <p>Author : ${product.getAuthor()}</p>
                                        <p>ISBN : ${product.getIsbn()}</p>
                                    </div>
                                </div>
                            </td>
                            <td data-th="Price">$ ${product.getPrice()}</td>
                            <td data-th="Quantity">
                                <input type="number" readonly class="form-control text-center" value="${product.getQuantity()}">
                            </td>
                            <td data-th="Subtotal" class="text-center"> ${product.getPrice() * product.getQuantity() } </td>

                        </tr>
                        <c:set var="total" value="${total+(product.getPrice()*product.getQuantity())}"/>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr class="visible-xs">
                        <td class="text-center"><strong>Total ${total}</strong></td>  
                    </tr>
                    <tr>
                        <td><a href="/BookStore/productviewer" class="btn btn-warning"><i class="fa fa-angle-left"></i> Back To Home Page</a></td>
                        <td colspan="2" class="hidden-xs"></td>
                        <td class="hidden-xs text-center"><strong>Total $ ${total}</strong></td>

                    </tr>
                </tfoot>
            </table>
        </div>
    </body>
</html>