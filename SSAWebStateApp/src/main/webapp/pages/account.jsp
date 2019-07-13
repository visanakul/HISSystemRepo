<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" href="css/account.css" rel="stylesheet">
<link type="text/css" href="css/datetimepicker.css" rel="stylesheet">
</head>
<body>

	<c:if test="${not empty msg}">
			<h3 id="ssn_msg">${msg}</h3>
	</c:if>
	<h2>Account Registration</h2>

	<form:form id="regForm" name="regForm" action="save_account"
		method="post" modelAttribute="account" autocomplete="off">
		<form:hidden path="accNo" name="accNo" />
		<form:hidden path="ssn" name="ssn" id="ssn" />
		<table>
			<tbody>
				<tr>
					<td>First Name :</td>
					<td><form:input path="fname" name="fname" /> <form:errors
							path="fname" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Last Name :</td>
					<td><form:input path="lname" name="lname" /> <form:errors
							path="lname" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Gender :</td>
					<td class="gen"><form:radiobuttons path="gender"
							items="${genders}" name="gender" /> <form:errors path="gender"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td>Email :</td>
					<td><form:input id="email" path="email" name="email"
							onkeyup="checkemail();" /><span id="email_status"></span> <form:errors
							path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Password :</td>
					<td><form:password path="password" name="password" /> <form:errors
							path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Select DOB :</td>
					<td><form:input path="dob" id="datepicker" /> <form:errors
							path="dob" cssClass="error" /></td>
				</tr>
				<tr>
					<td>SSN :</td>
					<td>
						<div id="my-ssn">
							<input type="text" id="ssn1" name="ssn1" onkeydown="focus2();"
								maxlength="3" size="3" /> <input type="text" id="ssn2"
								name="ssn2" onkeydown="focus3();" maxlength="2" size="2" /> <input
								type="text" name="ssn3" id="ssn3" onblur="appentSSN();"
								maxlength="4" size="4" /><br />
							<form:hidden path="ssn" name="ssn" id="ssn" />
							<form:errors id="ssn" path="ssn" cssClass="error" />
						</div>

					</td>

				</tr>
				<tr>
					<td>Mobile Number :</td>
					<td><form:input path="mobile" name="mobile" /> <form:errors
							path="mobile" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Select Role :</td>
					<td><form:select path="role" items="${roles}"
							itemValue="roleName" itemLabel="roleName" /></td>
				</tr>

				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="reset" value="Reset" /> <input type="submit"
						value="Register" onclick="regFormValidate();" /></td>

				</tr>
			</tbody>
		</table>
	</form:form>
	<a href="showall">Show all Accounts</a>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="js/accountreg.js"></script>

</body>

</html>