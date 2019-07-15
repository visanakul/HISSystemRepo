<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Plan</title>
<link type="text/css" href="css/add_plan.css" rel="stylesheet">
<link type="text/css" href="css/datetimepicker.css" rel="stylesheet">
</head>
<body>
	<h3>${msg}</h3>
	<h2>Plan Info</h2>
	<form:form id="planForm" name="planForm" action="savePlan"
		method="post" modelAttribute="plan" autocomplete="off">
		<table>
			<tbody>
				<tr>
					<td>Plan Name :</td>
					<td><form:input path="name" name="name" /> <form:errors
							path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Plan Description :</td>
					<td><form:textarea rows="5" path="description"
							name="description" /> <form:errors path="description"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td>Plan Start Date :</td>
					<td><form:input path="startDate" name="startDate" id="datepicker1" /> <form:errors
							path="startDate" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Plan End Date :</td>
					<td><form:input path="endDate" name="endDate" id="datepicker2" /> <form:errors
							path="endDate" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="reset" value="Reset" /> <input type="submit"
						value="Add Plan" onclick="addPlanFormValidate();" /></td>

				</tr>
			</tbody>
		</table>
	</form:form>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="js/add_plan.js"></script>
</body>
</html>