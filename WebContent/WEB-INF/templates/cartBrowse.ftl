<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Online Book Store</title>

	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/customerhome.js" type="text/javascript"></script>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/heroic-features.css" rel="stylesheet">
    <link href="css/browsebutton.css" rel="stylesheet">

  </head>

  <body onload="showValue()">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" id="welcome" href="#"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          <li class="nav-item">
					<form class="form-inline" action="BookstoreServlet" method="post">
						<input type="text" class="form-control" placeholder="Search here">
						<select class="form-control" id="dropDown_search">
							<option>Search By</option>
							<option>ISBN</option>
							<option>Author</option>
							<option>Name</option>
						</select>
						<button type="submit" class="form-control">Search</button>
					</form>
				</li>
            <li class="nav-item active">
              <a class="nav-link" href="Customer.html">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item"> 
            <form action="CustomerServlet" method="post">
              <button type="submit"class="btn btn-link browsebutton" name="browse" id="browse" value="Browse Books">Browse Books</button>
            </form>
            </li>
              <li class="nav-item">
              <a class="nav-link" href="MyCart.html">MyCart</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="History.html">Order-History</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="Settings.html">Settings</a>
            </li>
            <li class="nav-item">
					<a class="nav-link logout" id="logout" href="#">Logout</a>
			</li>
          </ul>
        </div>
      </div>
    </nav>
    
    <br/><br/>
    
    <div class="container">
    	<div class="row">
    		<div class="col-sm-3"></div>
			<div class="col-sm-3"><h4>Title</h4></div>
			<div class="col-sm-3"><h4>Quantity</h4></div>
			<div class="col-sm-3"><h4>Total</h4></div>
		</div>
		<#assign sum = 0>
    	<#list cart as cart>
    		<#assign sum = sum + cart.getTotal()>
    		<div class="row">
    			<div class="col-sm-3"><img class="img-thumbnail" src="${book[cart?index].getPicture()}" alt="${book[cart?index].getTitle()}"></div>
    			<div class="col-sm-3"><p>${book[cart?index].getTitle()}</p></div>
    			<div class="col-sm-3"><p>${cart.getQty()}</p></div>
    			<div class="col-sm-3"><p class = "total">${cart.getTotal()}</p></div>
			</div>
		</#list>
	</div>
	
   <div class="container">
    	<div class="row">
    		<div class="col-sm-3"><a href="#" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></div>
			<div class="col-sm-3"><a href="#" class="btn btn-success btn-block">Promo Code <i class="fa fa-angle-right"></i></a></div>
			<div class="col-sm-3"><span>Total: <label id="totalValue">${sum}</label></span></div>
			<div class="col-sm-3"><a href="#" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a></div>
		</div>
	</div>	
	
	
	
        
    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper/popper.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

  </body>

</html>