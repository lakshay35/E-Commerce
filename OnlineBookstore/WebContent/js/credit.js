$(document).ready(function loadPage() {
	$('#expiration').monthpicker({'minYear' : 2017, 'maxYear' : 2050});
});

$(document).on('click', ".credit", function () {
	var date = $(this).find(".creditDate").attr("value");
	var year = date.substring(0, 4);
	var month = date.substring(5, 7);
	month = month - 1;
	year = year - 0;
	
	$("#deleteButton").val($(this).find(".creditID").attr("value"))
	$("#number").val($(this).find(".creditNumber").attr("value"));
	$(".monthpick").val(month);
	$(".yearpick").val(year);
	$("#type").val($(this).find(".creditType").attr("value"));
});