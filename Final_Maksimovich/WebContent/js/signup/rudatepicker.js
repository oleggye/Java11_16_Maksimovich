$(document).ready(function() {
	$('#datetimepicker1').datetimepicker({
		pickTime : false,
		constrainInput : true,
		language : "ru",
		maxDate : "31/12/1999",
		minDate : "01/01/1929",
		defaultDate : "31/12/1999",
		autoclose : true

	});
	$("#show").click(function() {
		$('#datetimepicker1').data("DateTimePicker").show();
	});

});
