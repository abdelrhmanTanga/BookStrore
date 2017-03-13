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
            <div class="col-sm-9 padding-left">
                <div class="features_items"><!--features_items-->
                    <h2 class="title text-center">Features Items</h2>
                    <!--<div class="row row-eq-height"> <!--This is a row begining-->
                    <c:forEach items="${products}" var="product">   
                        <div class="col-sm-3" id="${product.id}">  <!--This is a column begining-->
                            <div class="product-image-wrapper">
                                <div class="single-products">
                                    <div class="productinfo text-center">
                                        <img src="${'/OnlineBookstore/imageloader?path='.concat(product.image)}" alt="" />
                                        <h2>${product.price}</h2>
                                        <p>${product.name}</p>
                                        <button id="${product.id}" onclick="addToCart(this.id)" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                    </div>
                                    <div class="product-overlay">
                                        <div class="overlay-content">
                                            <h2>${product.price}</h2>
                                            <p>${product.name}</p>
                                            <button id="${product.id}" onclick="addToCart(this.id)" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</button>
                                            <button id="${product.id}" onclick="viewProduct(this.id)" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>View product</button>
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
        function addToCart(clicked_id) {
            console.log("here");
            console.log(clicked_id);
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    document.write("true");
                    console.log(clicked_id);
                }
            };
            xhttp.open("GET", "/BookStore/addtocart?productid=" + clicked_id, true);
            xhttp.send();
        }
        
        function viewProduct(clicked_id){
            window.location.href = "/BookStore/productpage?productid=" + clicked_id;
        }
    </script>
</body>
</html>