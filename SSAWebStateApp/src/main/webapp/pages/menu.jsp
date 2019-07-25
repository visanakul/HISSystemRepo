<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/menu.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<div class="container-fluid">       
	<div class="row">
		<div class="col-md-3 nopadding" >
			<img src="img/his1.jpg" />
		</div>
		<div class="col-md-6 nopadding">
				<img src="img/his.gif" />
		</div>
		<div class="col-md-3 nopadding">
			<img src="img/his2.jpg" />
		</div>
	</div>
</div>
<div class="bs-example">
	<ul class="nav nav-pills mb-2">
		<li class="nav-item"><a href="show_home" class="nav-link"
			target="iframe1">Home</a></li>
		<li class="nav-item"><a href="show_app_reg" class="nav-link"
			target="iframe1">Application Registration</a></li>
		<li class="nav-item"><a href="get_application_list"
			class="nav-link" target="iframe1">View All Applications</a></li>

		<li class="nav-item dropdown"><a href="#"
			class="nav-link dropdown-toggle" data-toggle="dropdown">Messages</a>
			<div class="dropdown-menu">
				<a href="#" class="dropdown-item" target="iframe1">Inbox</a> <a
					href="#" class="dropdown-item" target="iframe1">Drafts</a> <a
					href="#" class="dropdown-item" target="iframe1">Sent Items</a>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item" target="iframe1"> Trash</a>
			</div></li>

		<c:if test="${sessionScope.login.role eq 'Admin'}">
			<li class="nav-item dropdown ml-auto"><a href="#"
				class="nav-link dropdown-toggle" data-toggle="dropdown">Admin</a>
				<div class="dropdown-menu dropdown-menu-right">
					<a href="show_plan" class="dropdown-item" target="iframe1">Add
						plan</a> <a href="get_plan_list" class="dropdown-item"
						target="iframe1">View plans</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item" target="iframe1">Logout</a>
				</div></li>
		</c:if>
	</ul>
</div>
<iframe id="tree" name="iframe1" src="show_home" scrolling="no"
	allowTransparency="true"></iframe>
