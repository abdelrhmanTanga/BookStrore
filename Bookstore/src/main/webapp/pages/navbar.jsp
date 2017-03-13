<html>
    <head>
    
    <link href="${pageContext.request.contextPath}/pages/css/navbar.css" type="text/css" rel="stylesheet"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/pages/js/navbar.js"></script>
        
    <script>
    $(document).ready(function(){

 $("#dropdownCart").on('click','.btnDelete',function(){
       $(this).closest('li').remove();
     });

});
    </script>
    </head>
    
<body>
    <div class="container">
  <nav class="navbar navbar-inverse">
    <div class="navbar-header">
    	<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">BOOK STORE</a>
	</div>
	
	<div class="collapse navbar-collapse js-navbar-collapse">
		<ul class="nav navbar-nav">
            <li>
           <form class="navbar-form" role="search" action="/OnlineBookstore/Search" method="post">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search" name="searchkey">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
            </div>
        </div>
        </form>
            </li>
			<li class="dropdown mega-dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Category <span class="caret"></span></a>				
				<ul class="dropdown-menu mega-dropdown-menu">
					<li class="col-sm-3">
						<ul>
							<li class="dropdown-header">Categorie</li>                            
                            <div id="menCollection" class="carousel slide" data-ride="carousel">
                              <div class="carousel-inner">
                                <div class="item active">
                                    <a href="#"><img src="images/mfcuuqrt.jpg" class="img-responsive" alt="product 1"></a>
                                    <h4><small>A book is a dream that you hold in your hands.</small></h4>                                            
                                </div><!-- End Item -->
                                <div class="item">
                                    <a href="#"><img src="http://placehold.it/254x150/3498db/f5f5f5/&text=New+Collection" class="img-responsive" alt="product 2"></a>
                                    <h4><small>Gold sandals with shiny touch</small></h4>                                        
                                    <button class="btn btn-primary" type="button">9,99 €</button> <button href="#" class="btn btn-default" type="button"><span class="glyphicon glyphicon-heart"></span> Add to Wishlist</button>        
                                </div><!-- End Item -->
                                <div class="item">
                                    <a href="#"><img src="http://placehold.it/254x150/2ecc71/f5f5f5/&text=New+Collection" class="img-responsive" alt="product 3"></a>
                                    <h4><small>Denin jacket stamped</small></h4>                                        
                                    <button class="btn btn-primary" type="button">49,99 €</button> <button href="#" class="btn btn-default" type="button"><span class="glyphicon glyphicon-heart"></span> Add to Wishlist</button>      
                                </div><!-- End Item -->                                
                              </div><!-- End Carousel Inner -->
                              <!-- Controls -->
                              <a class="left carousel-control" href="#menCollection" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                              </a>
                              <a class="right carousel-control" href="#menCollection" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                              </a>
                            </div><!-- /.carousel -->
                            <li class="divider"></li>
                           
						</ul>
					</li>
					<li class="col-sm-3">
						<ul>
							<li class="dropdown-header">Categories</li>
							<li><a href="#">Art & Design</a></li>
                            <li><a href="#">Children</a></li>
                            <li><a href="#">History & Politics</a></li>
							<li><a href="#">Literature & Fiction</a></li>
                            							<li><a href="#">Religion</a></li>

						</ul>
					</li>
					<li class="col-sm-3">
						<ul>
							<li class="dropdown-header">Languages</li>
							<li><a href="#">Arabic</a></li>
							<li><a href="#">English</a></li>
							<li><a href="#">French</a></li>                            
							<li><a href="#">German</a></li>							
						</ul>
					</li>
<!--
					<li class="col-sm-3">
						<ul>
							<li class="dropdown-header">Much more</li>
                            <li><a href="#">Easy to Customize</a></li>
							<li><a href="#">Calls to action</a></li>
							<li><a href="#">Custom Fonts</a></li>
							<li><a href="#">Slide down on Hover</a></li>                         
						</ul>
					</li>
-->
				</ul>				
			</li>
           
		</ul>
      
        <ul class="nav navbar-nav navbar-right">
            
            <li>
        <a class="navbar-brand" href="#">My Account</a>
                </li>
<!--        <li><a href="cartPage.html">My cart<span> (0) </span>items</a></li>-->
              <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <span class="glyphicon glyphicon-shopping-cart"></span> Cart Items<span class="caret"></span></a>
          <ul class="dropdown-menu dropdown-cart" role="menu" id="dropdownCart">
              <li>
                  <span class="item">
                    <span class="item-left">
                        <img src="" alt="" />
                        <span class="item-info">
                            <span>Item name</span>
                            <span>23$</span>
                        </span>
                    </span>
                    <span class="item-right">
                        <button class="btnDelete btn btn-xs btn-danger pull-right">x</button>
                    </span>
                </span>
              </li>
              <li>
                  <span class="item">
                    <span class="item-left">
                        <img src="" alt="" />
                        <span class="item-info">
                            <span>Item name</span>
                            <span>23$</span>
                        </span>
                    </span>
                    <span class="item-right">
                        <button class="btnDelete btn btn-xs btn-danger pull-right">x</button>
                    </span>
                </span>
              </li>
              <li>
                  <span class="item">
                    <span class="item-left">
                        <img src="" alt="" />
                        <span class="item-info">
                            <span>Item name</span>
                            <span>23$</span>
                        </span>
                    </span>
                    <span class="item-right">
                        <button class="btnDelete btn btn-xs btn-danger pull-right">x</button>
                    </span>
                </span>
              </li>
              
              <li class="divider"></li>
              <li><a class="text-center" href="cartPage.jsp">View Cart</a></li>
          </ul>
        </li>
      </ul>
    
      </ul>
	</div><!-- /.nav-collapse -->
  </nav>
</div>
    
</body>

</html>