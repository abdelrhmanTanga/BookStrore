<%-- 
    Document   : cart
    Created on : Mar 12, 2017, 4:09:28 AM
    Author     : omnia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Cart | E-Shopper</title>
        <link href="${pageContext.request.contextPath}/pages/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/prettyPhoto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/price-range.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/pages/css/responsive.css" rel="stylesheet">

        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%-- <%@page import="Facade.CartHandler"%>
        <%-- <%@page import="websiteview.model.CartDTO"%>
       
        <%-- <jsp:useBean id="CartHandler" scope="page" class="" /> 
        <jsp:useBean id="email" scope="session" type="" />--%>


        <c:set var="total" value="0"/>
    </head>
    <body>
        <section id="cart_items">
            <div class="container  col-sm-9">
                <div class="breadcrumbs">
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li class="active">Shopping Cart</li>
                    </ol>
                </div>
                <div class="table-responsive cart_info">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="image">Item</td>
                                <td class="description"></td>
                                <td class="price">Price</td>
                                <td class="quantity">Quantity</td>
                                <td class="total">Total</td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${cartlist}" var="cartItem">

                                <tr id="${cartItem.id}">
                                    <td class="cart_product">
                                        <a href="/BookStore/productpage?productid=${cartItem.id}"><img src="${pageContext.request.contextPath}/pages/images/${cartItem.image}" alt="" height="100" width="100"></a>
                                    </td>
                                    <td class="cart_description">
                                        <h4><a href="">${cartItem.name}</a></h4>
                                        <p>Web ID: ${cartItem.ISBN}</p>
                                    </td>
                                    <td class="cart_price">
                                        <p>${cartItem.price}</p>
                                    </td>
                                    <td class="cart_quantity">
                                        <div class="cart_quantity_button">
                                            <a class="cart_quantity_up" href=""> + </a>
                                            <input class="cart_quantity_input" type="text" name="quantity" value="${cartItem.itemQuantity}" autocomplete="off" size="2">
                                            <a class="cart_quantity_down" href=""> - </a>
                                        </div>
                                    </td>
                                    <td class="cart_total">
                                        <p class="cart_total_price">${cartItem.price * cartItem.itemQuantity}</p>
                                    </td>
                                    <td class="cart_delete">
                                        <a class="cart_quantity_delete" onclick="deleteProduct(${cartItem.id})"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>

                                <c:set var="total" value="30"/>
                            </c:forEach>  
                        </tbody>
                    </table>
                </div>
            </div>
        </section> <!--/#cart_items-->
        <script>
            function deleteProduct(clicked_id){
                alert(clicked_id);
                $.ajax({
                url: '/BookStore/removeitem',
                type: 'GET',
                contentType: 'application/json',
                data: "productid=" + clicked_id,
                dataType: 'text',
                success: function (data, textStatus, jqXHR) {
                    if (data == "true") {
                        document.getElementById(clicked_id).parentElement.removeChild(document.getElementById(clicked_id));
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
