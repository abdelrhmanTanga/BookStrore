<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!doctype html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>FB-Style Inline Edit Fields</title>
        <meta name="author" content="Jake Rocheleau">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/pages/css/Userprofilestyle.css">
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pages/js/profile.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>

    <body>
        <section id="core" style="height: 100%;">


            <div class="profileinfo">	
                <div class="gear">
                    <label> E-Mail</label>
                    <span id="pemail" class="datainfo" >${loggedIn}</span>

                </div>


                <div class="gear">
                    <label>User Name</label>
                    <input id="fullname" class="datainfo" value="${clientData.userName}" readonly name="uname">
                    <a href="#" class="editlink"><span class="glyphicon glyphicon-pencil"></span>Edit Info</a>
                    <a class="savebtn">Save</a>
                </div>


                <div class="gear">
                    <label>password</label>
                    <input id="pw" class="datainfo" type="password" value="${clientData.password}" readonly name="pw"/>
                    <a href="#" class="editlink"><span class="glyphicon glyphicon-pencil"></span>Edit Info</a>
                    <a class="savebtn">Save</a>
                </div>


                <div class="gear">
                    <label>Credit</label>
                    <input id="credit" class="datainfo" type="number" value="${clientData.credit}" readonly name="credit"/>
                    <a href="#" class="editlink"><span class="glyphicon glyphicon-pencil"></span>Edit Info</a>
                    <a class="savebtn">Save</a>
                </div>



                <div class="gear">
                    <label>Birthday:</label>
                    <input type="text" id="birthday" class="datainfo" name="dob" readonly value="${clientData.birthDay}">
                    <a href="#" class="editlink"><span class="glyphicon glyphicon-pencil"></span>Edit Info</a>
                    <a class="savebtn">Save</a>
                </div>

                <div class="gear">
                    <label>Country </label>
                    <input type="text" id="citytown" class="datainfo" name="country" readonly value="${clientData.country}"/>
                    <a href="#" class="editlink"><span class="glyphicon glyphicon-pencil"></span>Edit Info</a>
                    <a class="savebtn">Save</a>
                </div>

                <div class="gear">
                    <label>Phone</label>
                    <input type="tel" id="phonenum" class="datainfo" name="phone" value="${clientData.phone}" readonly/>
                    <a href="#" class="editlink"><span class="glyphicon glyphicon-pencil"></span>Edit Info</a>
                    <a class="savebtn">Save</a>
                </div>

                <div class="gear">
                    <label>Job</label>
                    <input type="text" id="job" class="datainfo" name="job" value="${clientData.job}" readonly/>
                    <a href="#" class="editlink"><span class="glyphicon glyphicon-pencil"></span>Edit Info</a>
                    <a class="savebtn">Save</a>
                </div>



                <div class="gear">
                    <label>Address</label>
                    <input type="text" id="address" class="datainfo" name="address" value="${clientData.address}" readonly />
                    <a href="#" class="editlink"><span class="glyphicon glyphicon-pencil"></span>Edit Info</a>
                    <a class="savebtn">Save</a>
                </div>

                <div class="gear">
                    <label>Gender</label>
                    <input type="text" id="gender" class="datainfo" name="gender" value="${clientData.gender}" readonly />
                    <a href="#" class="editlink"><span class="glyphicon glyphicon-pencil"></span>Edit Info</a>
                    <a class="savebtn">Save</a>
                </div>


            </div>
        </section>

    </body>
</html>