$(document).on('click', '.shipButton', function () {
	var status = $(this).parent().find(".statusSelect").val();
	$.ajax({
		type: "POST",
		url: "ShippingServlet",
		data: {
		"orderChange" : $(this).val(),
		"status": status
		}, async:"false",
		success: function (responseText) {
			if (responseText == '1')
				{
				alert("Successfully changed order status.");
				}
			else
				{
				alert("Failed to change order status.");
				}
		}
	});
});