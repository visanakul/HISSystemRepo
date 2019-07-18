function validateEmail(sEmail) {
	 var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (regex.test(sEmail)) {
		return true;
	} else {
		return false;
	}
}
function checkemail(e) {
	var email = document.getElementById("email").value;

	if(!validateEmail(email)){
		console.log('Invalid '+email);
		$('#email_status').html('Incorrect format');
		$('#email_status').addClass('error');
		$('#email').addClass('error');
		return;
	}
	if (email && email.length >= 6) {
		$.ajax({
			type : 'get',
			url : 'check_email?email=' + email,
			success : function(response) {
				$('#email_status').html(response);
				if (response == "OK") {
					$('#email_status').removeClass('error');
					$('#email').removeClass('error');
					return true;
				} else {
					$('#email_status').addClass('error');
					$('#email').addClass('error');
					// e.stopImmediatePropagation();
					return false;
				}
			}
		});
	} else {
		$('#email_status').html("");
		return false;
	}
}
function focus2() {
	var myLength = $('#ssn1').val().length;
	// console.log('Length : ' + myLength);
	if (myLength >= 3) {
		$('#ssn2').focus();
	}
}
function focus3() {
	var myLength = $('#ssn2').val().length;
	// console.log('Length : ' + myLength);
	if (myLength >= 2) {
		$('#ssn3').focus();
	}
}
function appentSSN() {
	var myLength = $('#ssn3').val().length;
	console.log('Length : ' + myLength);
	var data = $('#ssn1').val() + $('#ssn2').val() + $('#ssn3').val();
	console.log('Data : ' + data);
	$('#ssn').val(data);

}
function show() {
	$("#btn").fadeIn('slow').animate({
		opacity : 1.0
	}, 1500).effect("pulsate", {
		times : 2
	}, 800).fadeOut('slow');
}
$(document).ready(function() {
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'dd-M-yy',
			changeYear : true,
			changeMonth : true,
			yearRange : '-100:+0',
			maxDate : 0
		});
	});
	function regFormValidate() {
		console.log('Hello');
		var validator = $('#regForm').validate({
			rules : {
				"fname" : {
					required : true,
					minlength : 3,
					maxlength : 40
				},
				"lname" : {
					required : true,
					minlength : 3,
					maxlength : 40
				},
				"gender" : {
					required : true
				},
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
				},
				"dob" : {
					required : true
				},
				"ssn1" : {
					required : true,
					number : true,
					minlength : 3,
					maxlength : 3
				},
				"ssn2" : {
					required : true,
					number : true,
					minlength : 2,
					maxlength : 2
				},
				"ssn3" : {
					required : true,
					number : true,
					minlength : 4,
					maxlength : 4
				},
				"ssn" : {
					required : true,
					number : true,
					minlength : 9,
					maxlength : 9
				},

				"mobile" : {
					required : true,
					number : true,
					minlength : 10,
					maxlength : 10
				},
				"role" : {
					required : true
				}
			},
			errorElement : "span",
			messages : {
				"fname" : {
					required : "first name is required!",
					minlength : "first name must be at least 3 characters long"
				},
				"lname" : {
					required : "last name is required!",
					minlength : "last name must be at least 3 characters long"
				},
				"gender" : {
					required : "select gender!"
				},
				"email" : {
					required : "Email is required!",
					email : "Correct format required!",
					minlength : "Email must be at least 6 characters long"
				},
				"password" : {
					required : "Password is required!",
					minlength : "Password must be at least 6 characters long"
				},
				"dob" : {
					required : "Date of birth is required!"
				},
				"ssn1" : {
					required : "Number is required!",
					minlength : "SSN number must be 3 digit long"
				},
				"ssn2" : {
					required : "Number is required!",
					minlength : "SSN number must be 2 digit long"
				},
				"ssn3" : {
					required : "Number is required!",
					minlength : "SSN number must be 4 digit long"
				},
				"ssn" : {
					required : "SSN number is required!",
					minlength : "SSN number must be 9 digit long"
				},

				"mobile" : {
					required : "Mobile number is required!",
					minlength : "Mobile number must be 10 digit long"
				},
				"role" : {
					required : "State is required!"
				}
			},
			errorPlacement : function(error, element) {
				if (element.is(":radio")) {
					error.appendTo(element.parents('.gen'));
				}
				// else if (element.is("#ssn1") || element.is("#ssn2") ||
				// element.is("#ssn3"))
				// {
				// error.appendTo(element.parents('#my-ssn'));
				// }
				else {
					error.insertAfter(element);
					// $(".error").html( error );
				}
			}
		});
	}

});