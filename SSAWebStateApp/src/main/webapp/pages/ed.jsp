<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" href="css/ed.css" rel="stylesheet">
</head>
<body>
	<h3>${msg}</h3>

	<h2>Eligibility Determination</h2>
	<table>
		<tbody>
			<tr>
				<td>Case Number :</td>
				<td>${sessionScope.citizenPlanModel.caseNo}</td>
			</tr>
			<tr>
				<td>Selected Plan :</td>
				<td>${sessionScope.planName}</td>
			</tr>
			<tr>
				<td colspan="2">
					<form action="determine_eligibility">
						<input type="submit" value="Determine Eligibility" />
					</form>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>