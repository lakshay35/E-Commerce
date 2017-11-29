$(document).ready(function() {
	
	$.ajax({
		type: "POST",
		url: "BookstoreServlet",
		data: {
			"checkLogin" : "checkLogin"
		}, dataType: "json",
		async:"false",
		success: function(responseText) {
			
			if (responseText != "Customer")
				{
					$("#addressTab").remove();
					$("#creditTab").remove();
				}
		}
	});
	
	$.ajax({
		type: "POST",
		url: "BookstoreServlet",
		data: {
			"changeHome" : "changeHome"
		}, dataType: "json",
		async:"false",
		success: function(responseText) {
			$("#home").attr("href", responseText);
		}
	});
	
  $.ajax({
    method : "post",
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
		$("#messagePara").text("");
		if (($.trim($("#fname").val()) == '') || ($.trim($("#lname").val()) == '')
				|| ($.trim($("#phone").val()) == ''))
			{
				$("#messagePara").text("No inputs other than email should be left blank.");
			}
		else
			{
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
				  $("#messagePara").text("Successfully updated your profile.");
			  } else {
				  $("#messagePara").text("Failed to update your profile.");
			  	}
			  },
		  error: function() {
			  $("#messagePara").text("Failed to update your profile.");
		  }
		  });
	}
});

$(document).on('click', ".logout", function () { 
	$.ajax({
		type: "POST",
		url: "BookstoreServlet",
		data: {
		"logout" : "logout"
		}, async:"false",
		success: function () {
			window.location.href = "index.html"
		}
	});
});
