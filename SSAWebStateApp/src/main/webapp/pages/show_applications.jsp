<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Application List</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
<script type="text/javascript" language="javascript"
	src="//code.jquery.com/jquery-1.12.4.js">
	
</script>
<script type="text/javascript" language="javascript"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

<link type="text/css" href="css/application_table.css" rel="stylesheet">

</head>
<body>
	<c:choose>
		<c:when test="${not empty applicationModelList}">
			<h2>Application Details</h2>
			<table id="example">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Gender</th>
						<th>Email</th>
						<th>DOB</th>
						<th>SSN</th>
						<th>Mobile</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${applicationModelList}" var="citizen">
						<tr>
							<td>${citizen.fname}</td>
							<td>${citizen.lname}</td>
							<td>${citizen.gender}</td>
							<td>${citizen.email}</td>
							<td><fmt:formatDate pattern="dd-MMM-yyyy"
									value="${citizen.dob}" /></td>
							<td>${citizen.ssn}</td>
							<td>${citizen.mobile}</td>
							<td>
								<div>
									<a href="show_application?caseNo=${citizen.appNo}"> <img
										src="img/edit_icon.png" width=20px height=20px />
									</a> <a href="#"><img
										src="${citizen.active?'img/delete_icon.jpg':'img/active_icon.png'}"
										width=20px height=20px
										onclick="return confirm_status_change(this,${citizen.appNo});" />
									</a>
								</div>
							</td>

						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Gender</th>
						<th>Email</th>
						<th>DOB</th>
						<th>SSN</th>
						<th>Mobile</th>
						<th>Action</th>

					</tr>
				</tfoot>
			</table>
		</c:when>
		<c:otherwise>
			<h1>No record to display</h1>
		</c:otherwise>

	</c:choose>
	<a href="#" onClick="javascript:history.go(-1)">Back</a>
</body>
</html>