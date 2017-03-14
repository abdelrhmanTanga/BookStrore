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
                        <h2>Register</h2>
                        <form action="/BookStore/SignUp" method="post" name='myform' id="myform">
                            <input type="email" name="email" id="email" placeholder="Email " />
                            <input type="password" name="password" id="password" placeholder="Password" />
                            <input type="text" name="username" id="username" placeholder="Username" />
                            <input type="text" name="address" id="address" placeholder="Address" />
                            <input type="text" name="country" id="country" placeholder="country" />


                            <input type="date" name="birthday" id="birthday"/>
                            <input type="text" name="job" id="job" placeholder="job" />
                            <input type="number" name="phone" id="phone" placeholder="Phone number" />
                            <input type="number" name="credit" id="credit" placeholder="Credit Limit" />

                            <select name="gender" id="gender" style="margin-bottom:10px;">
                                <option value = 'm'>Male</option>
                                <option value = 'f'>Female</option>
                            </select>

                            <select name="favorites" id="favorites">
                                <option value=""></option>
                                <option value="">Female</option>
                            </select>
                            <button type="submit" class="btn btn-default" style="margin-left : 37%">Sign up</button>


                        </form>
                    </div><!--/login form-->
                </div>

            </div>
        </div>
        <script>
//            $(document).ready(function () {
//                var email = $('#email').val();
//                var password = $('#password').val();
//                var username = $('#username').val();
//                var country = $('#country').val();
//                var address = $('#address').val();
//                var job = $('#job').val();
//                var gender = $('#gender').val();
//                var credit = $('#credit').val();
//                var phone = $('#phone').val();
//                var birthday = $('#birthday').val();
//                var favorites = ""; //$('#favorites').val();
//                var signupJson = {email: email, password: password, username: username, country: country,
//                    address: address, job: job, gender: gender, credit: credit, phone: phone, birthday: birthday,
//                    favorites: favorites};
//                $.ajax({
//                    url: 'SignUp' + new Date().getTime(),
//                    type: 'GET',
//                    contentType: 'application/json',
//                    data: signupJson,
//                    dataType: 'json',
//                    success: function (data, textStatus, jqXHR) {
//                        
//                    }
//                });
//            }
//            })

        </script>
    </section><!--/form-->
</body>