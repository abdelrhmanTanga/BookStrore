<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login | E-Shopper</title>
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
<!--    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">-->
</head><!--/head-->

<body>

    <section id="form"><!--form-->
        <div class="container" style="text-align:center ; align-content:center; margin-left:28%">
            <div class="row">
                <div class="col-sm-4 col-sm-offset-1" >
                    <div class="login-form"><!--login form-->
                        <h2>Login to your account</h2>
                        <form action="/BookStore/SignIn" method="post">
                            <input type="email" name="email" id="email" placeholder="Email " />
                            <input type="password" name="password" id="email" placeholder="Password" />
                            <div class="alert alert-danger alert-dismissable"  style="display: none" id="error">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
                                <strong>Danger!</strong> Invalid email or password
                            </div>
                            <button type="submit" class="btn btn-default" style="margin-left : 37%">Login</button>
                        </form>
                    </div><!--/login form-->
                </div>
            </div>
        </div>
        <script>
            function signIn() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        if (this.responseText === "true") {
                            console.log(this.responseText);
                            window.location.href = "http://localhost:8084/BookStore/productviewer";
                        } else {
                            console.log(this.responseText);
                            document.getElementById("error").setAttribute("style", "display: inline");
                        }
                    }
                };
                xhttp.open("POST", "/BookStore/SignIn?date=" + new Date().getTime(), true);
                xhttp.send("username=" + document.getElementById("email").value + "&password=" + document.getElementById("password").value);
            }
        </script>
    </section><!--/form-->
</body>