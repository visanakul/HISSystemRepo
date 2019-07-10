<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
    <script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.12.4.js">
    </script>
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>


</head>
<body>

<table id="example" class="table display dt-responsive nowrap table-bordered table-striped">
    <thead>
    <tr>
        <th>SSN</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>DOB</th>
        <th>Photo</th>
    </tr>
    </thead>
 </table>
<script>
$(document).ready(function() {
	$('#example').DataTable({
		"ajax" : "getlist",
		"bPaginate":true,
		"aLengthMenu": [ [2, 4, 8, -1], [2, 4, 8, "All"] ],
		"iDisplayLength": 4	,
		"sAjaxDataProp":"",
		"columns" : [ {
			"mDataProp" : "ssn"
		}, {
			"mDataProp" : "fname"
		}, {
			"mDataProp" : "lname"
		},

		{
			"mDataProp" : "dob",
			"render": function (mDataProp) {
			        var date = new Date(mDataProp);
			        var month = date.getMonth() + 1;
			        return (month.length > 1 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
			    }
		},
			{
			"mDataProp": "photo",
		    "render": function(mDataProp, type, row) {
		    	return '<img src="data:image/jpeg;base64,'+ mDataProp + '" width="100px" height="100px">';
		    }
		}
		]
	});
});
</script>
</body>
</html>
