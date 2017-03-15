<%-- 
     Document   : viewproducts
     Created on : Mar 10, 2017, 2:16:16 AM
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
        <title>Home | E-Shopper</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

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
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/pages/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/pages/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/pages/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/pages/images/ico/apple-touch-icon-57-precomposed.png">
    </head>
    <body>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

        <div class="container">
            <div class="col-sm-9 padding-left" style="padding-top: 2%">
                <div class="features_items"><!--features_items-->
                    <h2 class="title text-center">Features Items</h2>
                    <!--<div class="row row-eq-height"> <!--This is a row begining-->
                    <c:forEach items="${products}" var="product">   
                        <div class="col-sm-4">  <!--This is a column begining-->
                            <div class="product-image-wrapper">
                                <div class="single-products">
                                    <div class="productinfo text-center">
                                        <img src="${pageContext.request.contextPath}/pages/images/${product.image}" alt="" height="300"/>
                                        <h2>${product.price}</h2>
                                        <p>${product.name}</p>
                                        <button id="${product.id}" onclick="addToCart(this.id, this)" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                        <button id="${product.id}" onclick="viewProduct(this.id)" class="btn btn-default add-to-cart"><i class="glyphicon glyphicon-search"></i>View product</button>
                                    </div>
                                    <div class="product-overlay">
                                        <div class="overlay-content">
                                            <h2>${product.price}</h2>
                                            <p>${product.name}</p>
                                            <button id="${product.id}" onclick="addToCart(this.id, this)" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                            <button id="${product.id}" onclick="viewProduct(this.id)" class="btn btn-default add-to-cart"><i class="glyphicon glyphicon-search"></i>View product</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>
    </div>
    <script>
        function addToCart(clicked_id, element) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    if (this.responseText == "true") {
                        element.innerHTML = "<i class='fa fa-shopping-cart'></i>Remove Item";
                        element.setAttribute("onclick", "removeFromCart(this.id , this)");
                        $(clicked_id).html("Remove Item");
                        console.log(clicked_id);
                        console.log(this.responseText);
                    } else {
                        element.innerHTML = "<i class='fa fa-shopping-cart'></i>Remove Item";
                        element.setAttribute("onclick", "removeFromCart(this.id , this)");
                        console.log(this.responseText);
                        $(clicked_id).html("Remove Item");
                    }
                }
            };
            xhttp.open("GET", "/BookStore/addtocart?productid=" + clicked_id, true);
            xhttp.send();
        }

        function removeFromCart(clicked_id, element) {
            $.ajax({
                url: '/BookStore/removeitem',
                type: 'GET',
                contentType: 'application/json',
                data: "productid=" + clicked_id,
                dataType: 'text',
                success: function (data, textStatus, jqXHR) {
                    if (data == "true") {
                        element.innerHTML = "<i class='fa fa-shopping-cart'></i>Add to cart";
                        element.setAttribute("onclick", "addToCart(this.id, this)");
                    } else {
                        ///////////// logic handle failure
                    }
                }
            });
        }


        function viewProduct(clicked_id) {
            window.location.href = "/BookStore/productpage?productid=" + clicked_id;
        }
    </script>
</body>
</html>
