<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Edit Book</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.js" type="text/javascript"></script>
    <script src="js/admin.js" type="text/javascript"></script>
      
    <!-- Custom styles for this template -->
    
    <link href="css/browsebutton.css" rel="stylesheet">
    
  </head>

  <body>
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
              <a class="nav-link" href="Admin.html">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="AddBook.html">Add Book</a>
            </li>
             <li class="nav-item"> 
            <form action="AdminServlet" method="post">
              <button type="submit" class="btn btn-link browsebutton" name="browse" id="browse" value="Browse Books">Browse Books</button>
              </form>
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
    <br/>
    <br/>
    <br/>
    <div class="container">
      <div style="
    display: inline-block;
    /*position: absolute;*/
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    width: 900px;
    height: 300px;
    margin: auto;
   ">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 class="panel-title">Edit Book</h3>
			 			</div>
			 			<div class="panel-body">
			    		<form role="form" action="AdminServlet" method="post">
			    		<input type="hidden" name="isbn" id="isbn" class="form-control input-sm" placeholder="ISBN" value="${book.getIsbn()?c}">
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="title" id="title" class="form-control input-sm" placeholder="Title" value="${book.getTitle()}">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="author" id="author" class="form-control input-sm" placeholder="Author Name" value="${book.getAuthor()}">
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="edition" id="edition" class="form-control input-sm" placeholder="Edition" value="${book.getEdition()}">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="category" id="category" class="form-control input-sm" placeholder="Category" value="${book.getCategory()}">
			    					</div>
			    				</div>
			    			</div>
							
							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="publisher" id="publisher" class="form-control input-sm" placeholder="Publisher" value="${book.getPublisher()}">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="year" id="year" class="form-control input-sm" placeholder="Publication Year" value="${book.getYear()?c}">
			    					</div>
			    				</div>
			    			</div>
							
							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="number" name="threshold" id="threshold" class="form-control input-sm" placeholder="Minimum Threshold" value="${book.getThreshold()?c}">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="number" name="quantity" id="quantity" class="form-control input-sm" placeholder="Quantity" value="${book.getQuantity()?c}">
			    					</div>
			    				</div>
			    			</div>
							
							<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                <input type="text" name="buyprice" id="buyprice" class="form-control input-sm" placeholder="Buying Price" value="${book.getBuyingPrice()?c}">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="sellprice" id="sellprice" class="form-control input-sm" placeholder="Selling Price" value="${book.getSellingPrice()?c}">
			    					</div>
			    				</div>
			    			</div>
							
			    			<div class="form-group">
			    				<input type="url" name="picture" id="picture" class="form-control input-sm" placeholder="Cover URL" value="${book.getPicture()}">
			    			</div>
			    			
			    			<div class="form-group">
			    				<textarea name="description" id="description" class="form-control input-sm" placeholder="Description">${book.getDescription()}</textarea>
			    			</div>
			    			
			    			<input type="submit" value="Edit Book" name="submitedit" class="btn btn-info btn-block">
			    		
			    		</form>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
      </div>
</html>
