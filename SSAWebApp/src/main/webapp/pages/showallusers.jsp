<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" href="css/demo.css" rel="stylesheet">
</head>
<body>
	<c:if test="${not empty userModelList}">
		<table class="books" border="2">
			<caption>
				<b>User Details</b>
			</caption>
			<thead>
				<tr>
					<th>SSN</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>DOB</th>
					<th>Gender</th>
					<th>Phone</th>
					<th>State</th>
					<th>Created date</th>
					<th>Updated Date</th>
					<th>Photo</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userModelList}" var="user">
					<tr>
						<td>${user.ssn}</td>
						<td>${user.fname}</td>
						<td>${user.lname}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${user.dob}" /></td>

						<td>${user.gender}</td>
						<td>${user.phone}</td>
						<td>${user.state}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${user.creationDate}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${user.updateDate}" /></td>
						<td><img id="profileImage"
							src="data:image/jpg;base64,${user.photoString}" width="100px" height="100px"></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th>SSN</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>DOB</th>
					<th>Gender</th>
					<th>Phone</th>
					<th>State</th>
					<th>Created date</th>
					<th>Updated Date</th>
					<th>Photo</th>
				</tr>
			</tfoot>
		</table>
	</c:if>
</body>
</html>