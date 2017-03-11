<%-- 
    Document   : ProductPage
    Created on : Mar 11, 2017, 5:16:52 PM
    Author     : abdelrhman galal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Product Details | E-Shopper</title>
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
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/pages/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/pages/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/pages/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/pages/images/ico/apple-touch-icon-57-precomposed.png">
    </head><!--/head-->

    <div class="container">
        <div class="row">

            <div class="col-sm-9 padding-right">
                <div class="product-details"><!--product-details-->
                    <div class="col-sm-4">
                        <div class="view-product">
                            <img src="${pageContext.request.contextPath}/pages/images/${productInfo.image}" alt="" />
                        </div>
                        <div id="similar-product" class="carousel slide" data-ride="carousel">
                        </div>
                    </div>
                    <div class="col-sm-8">
                        <div class="product-information"><!--/product-information-->

                            <h2>${productInfo.name}</h2>
                            <p>ISBN: ${productInfo.ISBN}</p>
                            <span>
                                <span>US $${productInfo.price}</span>
                                <label>Quantity:</label>
                                <input type="text" value="3" />
                                <button type="button" class="btn btn-fefault cart">
                                    <i class="fa fa-shopping-cart"></i>
                                    Add to cart
                                </button>
                            </span>
                            <p><b>Availability:</b> In Stock</p>
                            <p><b>Author:</b> ${productInfo.author}</p>
                            <p><b>Category:</b> ${productInfo.category}</p>
                            <p><b>Description:</b> ${productInfo.description}</p>
                        </div><!--/product-information-->
                    </div>
                </div>
            </div>
        </div>
    </div>
</html>
