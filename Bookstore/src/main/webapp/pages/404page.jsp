<%-- 
    Document   : 404page
    Created on : 15-Mar-2016, 17:17:16
    Author     : ElGazzar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404 Page</title>
            <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <meta name="viewport" content="width=device-width">
        <!-- Css Files Start -->
        <link href="css/style.css" rel="stylesheet" type="text/css" /><!-- All css -->
        <link href="css/bs.css" rel="stylesheet" type="text/css" /><!-- Bootstrap Css -->
        <link rel="stylesheet" type="text/css" href="css/main-slider.css" /><!-- Main Slider Css -->
        <!--[if lte IE 10]><link rel="stylesheet" type="text/css" href="css/customIE.css" /><![endif]-->
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css" /><!-- Font Awesome Css -->
        <link href="css/font-awesome-ie7.css" rel="stylesheet" type="text/css" /><!-- Font Awesome iE7 Css -->
        <noscript>
        <link rel="stylesheet" type="text/css" href="css/noJS.css" />
        </noscript>
        <!-- Css Files End -->
    </head>
<body>
    <!-- Start Main Wrapper -->
    <div class="wrapper">
      <!-- Start Main Header -->
      <!-- Start Top Nav Bar -->
      <%@include file='header.jsp'%>
      <!-- End Main Header -->
        <!-- Start Main Content Holder -->
        <section id="content-holder" class="container-fluid container">
          <section class="row-fluid">
              <!-- Start Main Content -->
              <section class="span12">
                  <h2 class="heading-404">404</h2>
                  <h3 class="sub-heading-404">We are sorry! But the page you were looking for does not exist.</h3>
              </section>
              <!-- End Main Content -->

          </section>
        </section>
        <!-- End Main Content Holder -->
        <!-- Start Footer Top 1 -->
        <%@include file='footer.jsp'%>
    <!-- JS Files Start -->
    </div>
<script type="text/javascript" src="js/lib.js"></script><!-- lib Js -->
<script type="text/javascript" src="js/modernizr.js"></script><!-- Modernizr -->
<script type="text/javascript" src="js/easing.js"></script><!-- Easing js -->
<script type="text/javascript" src="js/bs.js"></script><!-- Bootstrap -->
<script type="text/javascript" src="js/bxslider.js"></script><!-- BX Slider -->
<script type="text/javascript" src="js/input-clear.js"></script><!-- Input Clear -->
<script src="js/range-slider.js"></script><!-- Range Slider -->
<script src="js/jquery.zoom.js"></script><!-- Zoom Effect -->
<script type="text/javascript" src="js/bookblock.js"></script><!-- Flip Slider -->
<script type="text/javascript" src="js/custom.js"></script><!-- Custom js -->
<script type="text/javascript" src="js/social.js"></script><!-- Social Icons -->
<!-- JS Files End -->
<noscript>
	<style>
	#socialicons>a span { top: 0px; left: -100%; -webkit-transition: all 0.3s ease; -moz-transition: all 0.3s ease-in-out; -o-transition: all 0.3s ease-in-out; -ms-transition: all 0.3s ease-in-out; transition: all 0.3s 	ease-in-out;}
	#socialicons>ahover div{left: 0px;}
	</style>
</noscript>
<script type="text/javascript">
  /* <![CDATA[ */
  $(document).ready(function() {
  $('.social_active').hoverdir( {} );
})
/* ]]> */
</script>
</body>
</html>
