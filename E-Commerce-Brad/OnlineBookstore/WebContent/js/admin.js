$(document).ready(function loadPopular() {
	$.ajax({
		type: "POST",
		url: "BookstoreServlet",
		data: {
			"name" : "name"
		}, dataType: "json",
		async:"false",
		success: function(responseText) {
			var response = "Welcome, " + responseText;
			$("#welcome").append(response);
		}
	});
});
