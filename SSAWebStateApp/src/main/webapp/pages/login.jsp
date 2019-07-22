<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" errorPage="error.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	<h2>Login page</h2>
	<form:form id="logForm" name="logForm" action="check_login"
		method="post" modelAttribute="login" autocomplete="off">
		<table>
			<tbody>
				<tr>
					<td>Email ID :</td>
					<td><form:input path="email" name="email" /> <form:errors
							path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Password :</td>
					<td><form:password path="password" name="password" /> <form:errors
							path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="reset" value="Reset" /> <input id="process" type="submit"
						value="Login" onclick="logFormValidate();" /></td>

				</tr>
			</tbody>
		</table>
	</form:form>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>