<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" href="css/ssnenroll.css" rel="stylesheet">
<link type="text/css" href="css/datetimepicker.css" rel="stylesheet">
</head>
<body>

	<h3>${msg}</h3>

	<h2>Enrollment For SSN(Social Security Number)</h2>

	<form:form id="regForm" name="regForm" action="enroll" method="post"
		modelAttribute="user" autocomplete="off" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td>First Name :</td>
					<td><form:input path="fname" name="fname" /><form:errors path="fname" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Last Name :</td>
					<td><form:input path="lname" name="lname" /><form:errors path="lname" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Select DOB :</td>
					<td><form:input path="dob" id="datepicker" /><form:errors path="dob" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Gender :</td>
					<td class="gen"><form:radiobuttons path="gender"
							items="${genders}" name="gender" /><form:errors path="gender" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Phno :</td>
					<td><form:input path="phone" name="phone" /><form:errors path="phone" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Select state :</td>
					<td><form:select path="state" items="${states}"
							itemValue="stateName" itemLabel="stateName" /></td>
				</tr>
				<tr>
					<td>Select photo :</td>
					<td><input type="file" name="photoFile" id="photoId" /></td>
				</tr>
				<tr >
					<td colspan="2" style="text-align: center;"><input type="reset" value="Reset" /> <input
						type="submit" value="Enroll" onclick="regFormValidate();" /></td>

				</tr>
			</tbody>
		</table>
	</form:form>
	<a href="showall">Show all Records</a>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="js/ssnreg.js"></script>

</body>

</html>