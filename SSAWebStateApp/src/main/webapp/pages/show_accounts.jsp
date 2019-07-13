<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
    <script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
    </script>
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

<link type="text/css" href="css/datatable.css" rel="stylesheet">

</head>
<body>
<h2>User Details</h2>
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
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
 </table>
 <a href="#" onClick="javascript:history.go(-1)" >Back</a>
<script src="js/data_table.js"></script>
</body>
</html>