<%-- 
    Document   : categoryBar
    Created on : Mar 14, 2017, 5:00:52 PM
    Author     : yasmeen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <script src="${pageContext.request.contextPath}/pages/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/jquery.scrollUp.min.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/price-range.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/jquery.prettyPhoto.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/main.js"></script>
    </head>
    <body>
        <!-- Category Part -->
        <div class="col-sm-3">
            <div class="left-sidebar">
                <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                    <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="/BookStore/productviewer">ALL Products</a></h4>
                            </div>
                        </div>
                    <c:forEach items="${categories}" var="category">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="/BookStore/ProductsByCategory?id=${category.id}" id="${category.id}">${category.name}</a></h4>
                            </div>
                        </div>
                    </c:forEach>

                </div><!--/category-products-->
            </div></div>
    </body>
</html>
