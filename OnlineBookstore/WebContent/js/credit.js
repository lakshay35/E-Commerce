$(document).ready(function loadPage() {
	$('#expiration').monthpicker({'minYear' : 2017, 'maxYear' : 2050});
	
	$(document).on('click', ".credit", function () {
		if ($(this).css("background-color") != "rgb(255, 255, 255)")
			{
				$(".credit").css("background-color", "White");
				$("#deleteButton").val("");
			}
		else
			{
				$(".credit").css("background-color", "White");
				$(this).css("background-color", "Gray");
				$("#deleteButton").val($(this).find(".creditID").attr("value"));
			}
	});
});

