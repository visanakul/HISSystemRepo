<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
<script type="text/javascript" language="javascript"
	src="//code.jquery.com/jquery-1.12.4.js">
	
</script>
<script type="text/javascript" language="javascript"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

<link type="text/css" href="css/account_table.css" rel="stylesheet">

</head>
<body>
	<c:if test="${not empty accountModelList}">
		<h2>Account Details</h2>
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
					<th>Role</th>
					<th>Action</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${accountModelList}" var="account">
					<tr>
						<td>${account.fname}</td>
						<td>${account.lname}</td>
						<td>${account.gender}</td>
						<td>${account.email}</td>
						<td><fmt:formatDate pattern="dd-MMM-yyyy"
								value="${account.dob}" /></td>
						<td>${account.ssn}</td>
						<td>${account.mobile}</td>
						<td>${account.role}</td>
						<td>
							<div>
								<a href="show_account?accNo=${account.accNo}"> <img
									src="img/edit_icon.png" width=20px height=20px />
								</a>
								<a href="#"><img src="${account.active?'img/delete_icon.jpg':'img/active_icon.png'}" width=20px height=20px
									onclick="return confirm_status_change(this,${account.accNo});" />
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
					<th>Role</th>
					<th>Action</th>

				</tr>
			</tfoot>
		</table>
	</c:if>
	<c:if test="${empty accountModelList}">
		<h1>No record to display</h1>
	</c:if>
	<a href="#" onClick="javascript:history.go(-1)">Back</a>

	<script type="text/javascript" src="js/account_table.js"></script>
</body>
</html>