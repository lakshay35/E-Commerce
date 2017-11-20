$(document).ready(function loadPage() {
	$.ajax({
		type: "POST",
		url: "BookstoreServlet",
		data: {
			"checkLogin" : "checkLogin"
		}, dataType: "json",
		async:"false",
		success: function(responseText) {
			if (responseText == 1)
				{
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
					$("body").show();
				}
			else
				{
				window.location.href = "login.html";
				}
		}
	});
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

$(document).on('click', ".address", function () {
	$("#editButton").val($(this).find(".addressId").attr("value"));
	$("#deleteButton").val($(this).find(".addressId").attr("value"))
	$("#street").val($(this).find(".streetAddress").attr("value"));
	$("#city").val($(this).find(".cityAddress").attr("value"));
	$("#state").val($(this).find(".stateAddress").attr("value"));
	$("#zip").val($(this).find(".zipAddress").attr("value"));
});
