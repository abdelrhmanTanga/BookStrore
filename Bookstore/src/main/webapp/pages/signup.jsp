<html>
    <head>
        <title>Sign Up</title>

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/pages/assets/ico/shopping_cart.png">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/form-elements.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/pages/assets/css/style.css">


        <script type= "text/javascript" src = "${pageContext.request.contextPath}/pages/assets/js/countries.js"></script>



    </head>
    <body>
        <div class="allContent">
            <div class="col-sm-5">

                <div class="form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>Join us now</h3>
                            <p>Fill in the form below:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-pencil"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="/BookStore/SignUp" method="post" class="registration-form">
                            <!-- username -->
                            <div class="form-group">
                                <label class="sr-only" for="name">Username</label>
                                <input type="text" name="name" placeholder="Username" class="form-first-name form-control" id="signup-username">
                            </div>

                            <!-- password -->

                            <div class="form-group">
                                <label class="sr-only" for="password">Username</label>
                                <input type="text" name="password" placeholder="Password" class="form-password form-control" id="signup-password">
                            </div>

                            <!-- email address -->
                            <div class="form-group">
                                <label class="sr-only" for="email">Email</label>
                                <input type="text" name="email" placeholder="Email" class="form-email form-control" id="form-email">
                            </div>
                            <!-- home address -->
                            <div class="form-group">
                                <label class="sr-only" for="address">Address</label>
                                <input type="text" name="address" placeholder="Address"
                                       class="form-address form-control" id="form-address"></textarea>
                            </div>
                            <!-- country  -->

                            <div class="container">
                                <div class="form-group form-horizontal">
                                    <label>Select Country</label><br>
                                    <select id="country" name ="country"></select><br><br>
                                    <label>Select State</label>
                                    <select name ="state" id ="state"></select>
                                </div>
                            </div>

                            <!-- gender -->

                            <div class="form-group">
                                <label >Gender</label>
                                <select name="gender" class="form-gender form-control" id="form-gender">
                                    <option>Male</option>
                                    <option>Female</option>
                                </select>
                            </div>
                            <!-- birthday -->
                            <div class="form-group">
                                <label >Date of birth</label>
                                <input type="date" name="BirthDay"class="form-DOB form-control" id="form-DOB">
                            </div>
                            <!-- Job -->
                            <div class="form-group">
                                <label class="sr-only" for="job">Job</label>
                                <input type="text" name="job" placeholder="Job" class="form-job form-control" id="form-job"> </div>
                            <!-- favorite category-->
                            <div class="form-group">
                                <label >Favorite Category</label>
                                <select name="favourite" class="form-category form-control" id="form-category">
                                    <option>category 1</option>
                                    <option>category 2</option>
                                </select>
                            </div>
                            <!-- phone number-->
                            <div class="form-group">
                                <label class="sr-only" for="phone">phone</label>
                                <input type="number" name="phone" placeholder="Phone Number  " class="form-phone form-control" id="form-phone">
                            </div>
                            <!-- credit card limit-->
                            <div class="form-group">
                                <label class="sr-only" for="creditcard">credit limit</label>
                                <input type="number" name="creditcard" placeholder="Creadet Cared limits  " class="form-credit form-control" id="form-gender">
                            </div>

                            <button type="submit" class="btn">Sign me up!</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script language="javascript">
        populateCountries("country", "state");
    </script>

</body>
</html>