<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
.bs-example {
	margin: 20px;
}
</style>
<div class="bs-example">
	<ul class="nav nav-pills mb-5">
		<li class="nav-item"><a href="#" class="nav-link active">Home</a>
		</li>
		<li class="nav-item"><a href="#" class="nav-link">Profile</a></li>
		<li class="nav-item dropdown"><a href="#"
			class="nav-link dropdown-toggle" data-toggle="dropdown">Messages</a>
			<div class="dropdown-menu">
				<a href="#" class="dropdown-item">Inbox</a> <a href="#"
					class="dropdown-item">Drafts</a> <a href="#" class="dropdown-item">Sent
					Items</a>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item">Trash</a>
			</div></li>

		<c:if test="${sessionScope.login.role eq 'Admin'}">
			<li class="nav-item dropdown ml-auto"><a href="#"
				class="nav-link dropdown-toggle" data-toggle="dropdown">Admin</a>
				<div class="dropdown-menu dropdown-menu-right">
					<a href="show_plan" class="dropdown-item">Add plans</a> <a href="get_plan_list"
						class="dropdown-item">View plans</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item">Logout</a>
				</div></li>
		</c:if>
	</ul>
</div>