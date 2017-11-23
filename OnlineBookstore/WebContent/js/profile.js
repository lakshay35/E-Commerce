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
      if (responseText.subscribe == true)
    	  {
    	  	$("#sub").prop("checked", true);
    	  }
      else
    	  {
    	  	$("#sub").prop("checked", false);
    	  }
    },
    error : function() {
      alert("error occured");
    }
  });
  
});

$(document).on('click', "#save", function() {
	console.log("check");
		var temp = $("#sub").is(':checked');
	  $.ajax({
	  method : "post",
	  url : "BookstoreServlet",
	  data : {
	  "saveProfile" : "saveProfile",
	  "fname" : $("#fname").val(),
	  "lname" : $("#lname").val(),
	  "phone" : $("#phone").val(),
	  "sub" : temp
	  },

	  success: function(responseText) {
		  if(responseText == "Success") {
			  $(".fa-coffee").val("Successfully updated your profile.");
		  } else {
		  // return error message
		  	}
		  },
	  error: function() {
		  alert("error occured");
	  }
	  });
});
