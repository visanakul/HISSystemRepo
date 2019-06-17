<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body {
	font-family: sans-serif;
	background: #007bff;
	background: linear-gradient(to right, #420c04, #911705);
}

div {
	margin-top: 100px; display : flex;
	justify-content: center;
	display: flex;
}

span {
	padding: 30px;
	color: white;
	font-size: 2rem;
	padding: 20px;
	border: 0;
	border-radius: 1rem;
	box-shadow: 0 2rem 2rem 0 black;
	background-color: #42b6f4;
	background: linear-gradient(to right, #0f6b84, #024060);
	margin: auto;
}
</style>
</head>
<body>
	<div>
		<span>${errMsg}</span>
	</div>
</body>
</html>