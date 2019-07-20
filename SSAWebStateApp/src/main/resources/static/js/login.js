function logFormValidate() {
	console.log('logFormValidate');
	var validator = $('#logForm').validate({
		rules : {
			"email" : {
				required : true,
				email : true,
				minlength : 6,
				maxlength : 40
			},
			"password" : {
				required : true,
				minlength : 6,
				maxlength : 40
			}
		},
		errorElement : "span",
		messages : {
			"email" : {
				required : "Email is required!",
				email : "Correct format required!",
				minlength : "Email must be at least 6 characters long"
			},
			"password" : {
				required : "Password is required!",
				minlength : "Password must be at least 6 characters long"
			}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element);
		}
	});
}