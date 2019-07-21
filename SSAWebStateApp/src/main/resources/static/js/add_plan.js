$(function() {
	$("#datepicker1").datepicker({
		dateFormat : 'dd-M-yy',
		changeYear : true,
		changeMonth : true,
		minDate : 0,
		maxDate : "+90d",
		yearRange : '0:+1',
		onSelect: function(selectedDate){
			console.log('Selected '+selectedDate);
			 $("#datepicker2").datepicker("option", "minDate", selectedDate);
			 var dateObject=new Date(selectedDate);
	         dateObject.setDate(dateObject.getDate()+90);    
			$("#datepicker2").datepicker("option", "maxDate", dateObject);
		}
	});
	$("#datepicker2").datepicker({
		dateFormat : 'dd-M-yy',
		changeYear : true,
		changeMonth : true,
		minDate : 0 + 2,
		maxDate : "+90d",
		yearRange : '0:+1',
		onSelect: function(date){
			console.log('Selected '+date);
		}
	});
});
function addPlanFormValidate() {
	console.log('Hello');
	var validator = $('#planForm').validate({
		rules : {
			"name" : {
				required : true,
				minlength : 3,
				maxlength : 40
			},
			"description" : {
				required : true,
				minlength : 10,
				maxlength : 100
			},
			"startDate" : {
				required : true,
			},
			"endDate" : {
				required : true,
			}
		},
		errorElement : "span",
		messages : {
			"name" : {
				required : "Plan name is required!",
				minlength : "Plan name must be at least 3 characters long"
			},
			"description" : {
				required : "Description name is required!",
				minlength : "Description must be at least 10 characters long"
			},
			"startDate" : {
				required : "Please select start date"
			},
			"endDate" : {
				required : "Please select end date"
			}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element);
		}
	});
}

