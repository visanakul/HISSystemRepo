<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

td {
	padding: 10px;
	color: white;
	font-size: 1rem;
}

h2, h3 {
	text-align: center;
	color: white;
}

h2 {
	text-transform: uppercase;
	text-shadow: black 2px 5px;
}

table {
	padding: 20px;
	margin: auto;
	border: 0;
	border-radius: 1rem;
	box-shadow: 0 2rem 2rem 0 black;
	margin-top: 30px;
	background-color: #42b6f4;
	background: linear-gradient(to right, #0f6b84, #024060);
	margin: auto;
}

td:first-child {
	text-align: right;
}

.btn-container {
	display: flex;
	width: 100%;
	justify-content: space-around;
}

input[type='reset'], input[type='submit'] {
	padding: 5px 15px;
	border-radius: 10px;
	background-color: black;
	color: white;
	font-size: 1rem;
	outline: none;
}

input[type='text'], input[type='password'] {
	border-radius: 8px;
	padding: 2px 10px;
	outline: none;
	line-height: 1rem;
}

select,input[type='file'] {
	padding: 2px;
	font-size: 0.9rem;
	border-radius: 8px;
	outline: none;
}

form .error {
	margin-left: 5px; color : #ff0000;
	font-size: 0.8rem;
	color: #ff0000;
}
.ui-datepicker {
    width: 300px;
    height: 300px;
    margin: 5px auto 0;
    font: 12pt Arial, sans-serif;
    /*-webkit-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);
    -moz-box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, .5);*/
}

    .ui-datepicker table {
        width: 100%;
    }

.ui-datepicker-header {
    background: #3399ff;
    color: #ffffff;
    font-family:'Times New Roman';
    border-width: 1px 0 0 0;
    border-style: solid;
    border-color: #111;
}

.ui-datepicker-title {
    text-align: center;
    font-size: 15px;

}

.ui-datepicker-prev {
    float: left;
    cursor: pointer;
    background-position: center -30px;
}

.ui-datepicker-next {
    float: right;
    cursor: pointer;
    background-position: center 0px;
}

.ui-datepicker thead {
    background-color: #f7f7f7;

    /*border-bottom: 1px solid #bbb;*/
}

.ui-datepicker th {
    background-color:#808080;
    text-transform: uppercase;
    font-size: 8pt;
    color: #666666;
    /*text-shadow: 1px 0px 0px #fff;*/
    /*filter: dropshadow(color=#fff, offx=1, offy=0);*/
}

.ui-datepicker tbody td {
    padding: 0;
    /*border-right: 1px solid #808080;*/
}

    .ui-datepicker tbody td:last-child {
        border-right: 0px;
    }

.ui-datepicker tbody tr {
    border-bottom: 1px solid #bbb;
}

    .ui-datepicker tbody tr:last-child {
        border-bottom: 0px;
    }

.ui-datepicker a {
    text-decoration: none;
}

.ui-datepicker td span, .ui-datepicker td a {
    display: inline-block;
    /*font-weight: bold;*/
    text-align: center;
    width: 30px;
    height: 30px;
    line-height: 30px;
    color: #ffffff;
    /*text-shadow: 1px 1px 0px #fff;*/
    /*filter: dropshadow(color=#fff, offx=1, offy=1);*/
}

.ui-datepicker-calendar .ui-state-default {
      background: linear-gradient(#999999, #737373);
      color:#ffffff;
      height:40px;
      width:40px;

}

.ui-datepicker-calendar .ui-state-hover {
    background: #33adff;
    color: #FFFFFF;
}

.ui-datepicker-calendar .ui-state-active {
    background: #33adff;
    -webkit-box-shadow: inset 0px 0px 10px 0px rgba(0, 0, 0, .1);
    -moz-box-shadow: inset 0px 0px 10px 0px rgba(0, 0, 0, .1);
    box-shadow: inset 0px 0px 10px 0px rgba(0, 0, 0, .1);
    color: #e0e0e0;
    text-shadow: 0px 1px 0px #4d7a85;
    border: 1px solid #55838f;
    position: relative;
    margin: -1px;
}

.ui-datepicker-unselectable .ui-state-default {
    background: #D6E4BE;
    color: #000;
}
</style>
</head>
<body>

	<h3>${msg}</h3>
	
	<h2>Enrollment For SSN(Social Security Number)</h2>

	<form:form id="regForm" name="regForm" action="enroll"
		method="post" modelAttribute="user" autocomplete="off">
		<table>
			<tbody>
				<tr>
					<td>First Name :</td>
					<td><form:input path="fname" name="fname" /></td>
					<td><form:errors path="fname" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Last Name :</td>
					<td><form:input path="lname" name="lname" /></td>
					<td><form:errors path="lname" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Select DOB :</td>
					<td><form:input path="dob" id="datepicker" /></td>
					<td><form:errors path="dob" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Gender :</td>
					<td class="gen"><form:radiobuttons path="gender" items="${genders}"
							name="gender" /></td>
					<td><form:errors path="gender" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Phno :</td>
					<td><form:input path="phone" name="phone" /></td>
					<td><form:errors path="phone" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Select state :</td>
					<td><form:select path="state" items="${states}" itemValue="stateId" itemLabel="stateName" /></td>
				</tr>
				<tr>
					<td>Select photo :</td>
					<td><input type="file" name="photo" id="photo"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="btn-container">
							<input type="reset" value="Reset" style="float: left;" />
							<input type="submit" value="Enroll" style="float: right;" onclick="regFormValidate();" />
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	
</body>
<script type="text/javascript">
$( function() {
    $( "#datepicker" ).datepicker(
    							  { 
    							    changeYear: true, 
    					            changeMonth: true,
    							    yearRange:'-100:+0'
    							  }
    							);
  } );
function regFormValidate(){
debugger;
	console.log('Hello');
	var validator=$('#regForm').validate({
		rules:{
			"fname" : {
                required: true,
                minlength: 3,
                maxlength: 40
            },
			"lname" : {
                required: true,
                minlength: 3,
                maxlength: 40
            },
			
			"gender" : {
                required: true
            },
			"phone" : {
                required: true,
                number:true,
                minlength: 10,
                maxlength: 10
            },
			"state":{
                required: true
            },
            "dob":{
                required: true
            },
            "photo":{
                required: true
            }
		},
		errorElement:"span",
		messages:{
			"fname" :{
                required: "first name is required!",
                minlength: "first name must be at least 3 characters long"
            },
			"lname" :{
                required: "last name is required!",
                minlength: "last name must be at least 3 characters long"
            },
			"gender" :{
                required: "select gender!"
            },
			"phone" :{
                required: "Phone number is required!",
                minlength: "hone number must be 10 digit long"
            },
			"state":{
                required: "State is required!"
            },
            "dob":{
                required: "Date of birth is required!"
            },
            "photo":{
                required: "Photo is required!"
            }
		},
		 errorPlacement: function(error, element) 
      {
        if (element.is(":radio")) 
        {
            error.appendTo(element.parents('.gender'));
        }
        else 
        { 
            error.insertAfter( element );
        }
        }
	});
	
	
}
</script>
</html>