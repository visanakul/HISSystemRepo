<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Application Registration</title>
<link type="text/css" href="css/account.css" rel="stylesheet">
<link type="text/css" href="css/datetimepicker.css" rel="stylesheet">
</head>
<body>
	<c:if test="${not empty msg}">
		<h3 id="account_msg">${msg}</h3>
	</c:if>

	<c:set var="registerStatus" value="${empty citizen.appNo}"></c:set>
	<c:if test="${empty operationPerformedMsg}">
		<h2>${operationSelectedMsg}</h2>
	</c:if>

	<c:if test="${not empty operationPerformedMsg}">
		<h2>${operationPerformedMsg}</h2>
	</c:if>


	<form:form id="regForm" name="regForm"
		action="${registerStatus?'save_application':'update_application'}"
		method="post" modelAttribute="citizen" autocomplete="off">
		<form:hidden path="appNo" name="appNo" />
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
							onkeydown="checkemail();" /><span id="email_status"></span> <form:errors
							path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Select DOB :</td>
					<td><fmt:formatDate value="${citizen.dob}" var="dateString"
							pattern="dd-MMM-yyyy" /> <form:input path="dob" id="datepicker"
							value="${dateString}" /> <form:errors path="dob"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td>SSN :</td>
					<td>
						<div id="my-ssn">
							<input type="text" id="ssn1" name="ssn1" onkeydown="focus2(event);"
								maxlength="3" size="3" /> <input type="text" id="ssn2"
								name="ssn2" onkeydown="focus3(event);" maxlength="2" size="2" /> <input
								type="text" name="ssn3" id="ssn3" onblur="appendSSN(event);"
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
					<td colspan="2" style="text-align: center;"><input
						type="reset" value="Reset" /> <input id="process" type="submit"
						value="${operation}" onclick="regFormValidate();" /></td>

				</tr>
			</tbody>
		</table>
	</form:form>

	<a href="get_application_list">Show all Applications</a>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<c:if test="${registerStatus}">
		<script type="text/javascript" src="js/applicationreg.js"></script>
	</c:if>
	<c:if test="${not registerStatus}">
		<script type="text/javascript" src="js/applicationupdate.js"></script>
	</c:if>
</body>

</html>