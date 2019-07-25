<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Snap Plan Form</title>
</head>
<link type="text/css" href="css/snap_plan.css" rel="stylesheet">
<body>
	<h3>${msg}</h3>
	<h2>Snap plan</h2>
	<form:form id="snapForm" name="snapForm" action="show_ed" method="post" modelAttribute="snapPlanData" autocomplete="off">
		<table>
			<tbody>
				<tr>
					<td>Enter family Income :</td>
					<td><form:input path="familyIncome" name="familyIncome" /> 
					<form:errors path="familyIncome" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Employed :</td>
					<td><form:radiobuttons path="isEmployed"
							items="${isEmployed}" name="isEmployed" /> <form:errors path="isEmployed"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit"></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>