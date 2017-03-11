<%-- 
    Document   : ViewCart
    Created on : Mar 10, 2017, 6:03:53 PM
    Author     : omnia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="CartPage.css">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!--<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>-->
        <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

        <style>
            body
            {
                color: firebrick;
            }
        </style>
        <script>

            $(document).ready(function () {

                $("#tbUser").on('click', '.btnDelete', function () {
                    $(this).closest('tr').remove();
                });

            });

        </script>

    </head>
    <body>


        <h2 style="color:#DA4220;margin-left: 40px;">  Shopping Cart</h2> 
        <br>
        <div class="CartProducs container">

            <div class="row bookInformation container">


                <div class="col-sm-12" >

                    <table  id="tbUser" class="table table-hover" style="margin:0px;">

                        <tr>
                            <th>Cover</th>
                            <th>Book Name</th>
                            <th>Description </th>
                            <th> Price</th>
                            <th>Quantity</th>
                            <th>total price</th>
                            <th>remove</th>
                        </tr>

                        <tr>
                            <td>  <div class="thumbnail">
                                    <img src="mfcuuqrt.jpg" class="img-rounded" alt="image is not avilable" width="20" height="20"> 

                                </div>
                            </td> 
                        <c:forEach items="${cartObj}" var="cartObj">
                            <td>name</td>
                            <td>short description of the book</td>
                            <td>127$</td>
                            <td>2 </td>
                            <td>254$</td>
                            <td> <button class="btn btn-danger btnDelete" id="delete"><span class="glyphicon glyphicon-trash"></span></button></td>
                            </tr>

                            <tr>
                                <td>  <div class="thumbnail">
                                        <img src="mfcuuqrt.jpg" class="img-rounded" alt="image is not avilable" width="20" height="20"> 

                                    </div>
                                </td>
                                <td>name</td>
                                <td>short description of the book</td>
                                <td>127$</td>
                                <td>2 </td>
                                <td>254$</td>
                                <td> <button class="btn btn-danger btnDelete" id="delete"><span class="glyphicon glyphicon-trash"></span></button></td>
                            </tr>

                            <tr>
                                <td>  <div class="thumbnail">
                                        <img src="mfcuuqrt.jpg" class="img-rounded" alt="image is not avilable" width="20" height="20"> 

                                    </div>
                                </td>
                                <td>name</td>
                                <td>short description of the book</td>
                                <td>127$</td>
                                <td>2 </td>
                                <td>254$</td>
                                <td> <button class="btn btn-danger btnDelete" id="delete"><span class="glyphicon glyphicon-trash"></span></button></td>
                            </tr>

                    </table>


                </div>
            </div>

        </div>
        <div id="checkout-div">
            <span ><strong>  Total Price : </strong></span><br>
            <button style="margin:10px;" type="button" class="btn btn-danger" id="checkout-btn">Checkout</button>
        </div>
    </body>
</html>
