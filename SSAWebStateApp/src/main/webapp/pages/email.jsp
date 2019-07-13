<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" language="javascript"
	src="//code.jquery.com/jquery-1.12.4.js">
	
</script>
<script type="text/javascript">
	function checkemail() {
		var email = document.getElementById("UserEmail").value;

		if (email) {
			$.ajax({
				type : 'get',
				url : 'check_email?email=' + email,
				success : function(response) {
					$('#email_status').html(response);
					if (response == "OK") {
						return true;
					} else {
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
		//console.log('Length : ' + myLength);
		if (myLength >= 3) {
			$('#ssn2').focus();
		}
	}
	function focus3() {
		var myLength = $('#ssn2').val().length;
		//console.log('Length : ' + myLength);
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
</script>
</head>
<body>

	<form method="GET">

		<input type="text" name="useremail" id="UserEmail"
			onkeyup="checkemail();"> <span id="email_status"></span> <br />
		<input id="btn" type="submit" name="submit_form" value="Submit"
			onclick="show();">
	</form>

	<input id="ssn1" type="text" onkeydown="focus2();" maxlength="3"
		size="3" />
	<input id="ssn2" type="text" onkeydown="focus3();" maxlength="2"
		size="2" />
	<input id="ssn3" type="text" onblur="appentSSN();" maxlength="4"
		size="4" />
	<br />
	<input type="hidden" id="ssn" />
	<br />
</body>
</html>