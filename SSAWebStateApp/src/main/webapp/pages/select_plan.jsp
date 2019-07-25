<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" href="css/select_plan.css" rel="stylesheet">

</head>
<body>

	<h3>${msg}</h3>
	<h2>Select Plan</h2>
		<form action="show_selected_plan" method="post">
			<table>
				<tbody>
					<tr>
						<td>Application Number :</td>
						<td>${sessionScope.citizenPlanModel.appNo}</td>
					</tr>
					<tr>
						<td>Case No :</td>
						<td>${sessionScope.citizenPlanModel.caseNo}</td>
					</tr>
					<tr>
						<td>Select Plan :</td>
						<td><select name="planName" >
								<c:forEach items="${activePlans}" var="name">
									<option value="${name}">${name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Submit"></td>
					</tr>
				</tbody>
			</table>
		</form>
</body>
</html>