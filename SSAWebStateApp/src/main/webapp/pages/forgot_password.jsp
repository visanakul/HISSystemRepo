<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link type="text/css" href="css/login.css" rel="stylesheet">
<body>
	<h3>${msg}</h3>
	<h2>Forgot Password</h2>
	<form id="forgotForm" name="forgotForm" action="send_password"
		method="post" autocomplete="off">
		<table>
			<tbody>
				<tr>
					<td>Email ID :</td>
					<td><input type="text" name="email" /> <span id="error"
						class="error" style="display: none;"></span></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="reset" value="Reset" /> <input id="process" type="submit"
						value="Submit" onclick="forgotFormValidate();" /></td>

				</tr>
			</tbody>
		</table>
	</form>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="js/forgot.js"></script>
</body>
</html>