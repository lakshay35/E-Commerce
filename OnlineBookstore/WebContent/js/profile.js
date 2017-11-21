$(document).ready(function() {
  $.ajax({
    method : "get",
    url : "BookstoreServlet",
    data : {
      "viewProfile" : "viewProfile"
    }, dataType : "json",
    success : function(responseText) {
      $("#fname").val(responseText.fname);
      console.log(responseText);
      $("#lname").val(responseText.lname);
      $("#email").val(responseText.email);
      $("#phone").val(responseText.phone);
    },
    error : function() {
      alert("error occured");
    }
  });
  
  $("#save").click(function() {
	  console.log("Fucking work");
	  $.ajax({
	  method : "post",
	  url : "BookstoreServlet",
	  data : {
	  "saveProfile" : "saveProfile",
	  "fname" : $("#fname").val(),
	  "lname" : $("#lname").val(),
	  "phone" : $("#phone").val()
	  },

	  success: function(responseText) {
		  if(responseText == "Success") {
			  //update div or redirect
		  } else {
		  // return error message
		  	}
		  },
	  error: function() {
		  alert("error occured");
	  }
	  });
  });
  
});
