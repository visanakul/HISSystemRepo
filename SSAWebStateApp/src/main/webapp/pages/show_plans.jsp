<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Plans Detail</title>
</head>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
<script type="text/javascript" language="javascript"
	src="//code.jquery.com/jquery-1.12.4.js">
	
</script>
<script type="text/javascript" language="javascript"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

<link type="text/css" href="css/plan_table.css" rel="stylesheet">
<body>
<c:if test="${not empty planModelList}">
		<h2>Plan Details</h2>
		<table id="example">
			<thead>
				<tr>
					<th>Plan Name</th>
					<th>Description</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${planModelList}" var="plan">
					<tr>
						<td>${plan.name}</td>
						<td>${plan.description}</td>
						<td><fmt:formatDate pattern="dd-MMM-yyyy"
								value="${plan.startDate}" /></td>
						<td><fmt:formatDate pattern="dd-MMM-yyyy"
								value="${plan.endDate}" /></td>
						<td>
							<div>
								<a href="show_plan?accNo=${plan.id}"> <img
									src="img/edit_icon.png" width=20px height=20px />
								</a>
								<a href="#"><img src="${plan.active?'img/delete_icon.jpg':'img/active_icon.png'}" width=20px height=20px
									onclick="return confirm_status_change(this,${plan.id});" />
								</a>
							</div>
						</td>

					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th>Plan Name</th>
					<th>Description</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Action</th>
				</tr>
			</tfoot>
		</table>
	</c:if>
	<c:if test="${empty planModelList}">
		<h2>No record to display</h2>
	</c:if>
	<a href="#" onClick="javascript:history.go(-1)">Back</a>

	<script type="text/javascript" src="js/plan_table.js"></script>
</body>
</html>