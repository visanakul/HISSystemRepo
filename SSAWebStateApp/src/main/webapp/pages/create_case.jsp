<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Case</title>
<style type="text/css">
span {
	color: white;
}
</style>
<link type="text/css" href="css/create_case.css" rel="stylesheet">

</head>
<body>

	<div align="center">
		<form action="app_no_check">
			<span>Enter application number :</span> <input type="text" id="appNo"
				name="appNo" value="${appNo}" /> <input type="submit" id="submit"
				value="Search" />
		</form>
	</div>
	<c:choose>
		<c:when test="${empty citizen}">
			<h3 id="application_msg">${msg}</h3>
		</c:when>
		<c:otherwise>
			<div>
				<h2>Create case</h2>
				<table>
					<tbody>
						<tr>
							<td>First Name :</td>
							<td><span id="fname">${citizen.fname}</span></td>
						</tr>
						<tr>
							<td>Last Name :</td>
							<td><span id="lname">${citizen.lname}</span></td>
						</tr>
						<tr>
							<td>Gender :</td>
							<td><span id="gender">${citizen.gender}</span></td>
						</tr>
						<tr>
							<td>Email :</td>
							<td><span id="email">${citizen.email}</span></td>
						</tr>
						<tr>
							<td>DOB :</td>
							<td><fmt:formatDate value="${citizen.dob}" var="dateString"
									pattern="dd-MMM-yyyy" /> <span id="dob">${dateString}</span></td>
						</tr>
						<tr>
							<td>SSN :</td>
							<td><span id="ssn">${citizen.ssn}</span></td>

						</tr>
						<tr>
							<td>Mobile Number :</td>
							<td><span id="mobile">${citizen.mobile}</span></td>
						</tr>
					</tbody>
				</table>
				<form action="show_generate_case" method="post">
					<div align="center" style="margin-top: 20px;">
						<input type="hidden" name="appNo" value="${appNo}" /> <input
							id="process" type="submit" value="Create Case" />
					</div>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
</body>
</html>