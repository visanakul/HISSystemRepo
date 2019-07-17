$(document).ready(function() {
	$('#example').DataTable({
		"ajax" : "getlist",
		"bPaginate":true,
		"aLengthMenu": [ [2, 4, 8, -1], [2, 4, 8, "All"] ],
		"iDisplayLength": 4	,
		"sAjaxDataProp":"",

		"columns" : [ 
	    {
			"mDataProp" : "fname"
		}, 
		{
			"mDataProp" : "lname"
		},
		{
			"mDataProp" : "gender"
		},
		{
			"mDataProp" : "email"
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
			"mDataProp" : "ssn"
		},	
		{
			"mDataProp" : "mobile"
		},
		{
			"mDataProp" : "role"
		},
		 {
           "searchable": false,
            "sortable": false,
            "mDataProp":"accNo",
            "render": function (mDataProp, type, full, meta) {
            	 return '<a href=edit/'+mDataProp+'/account><img src='+'"img/edit_icon.png"'+' width=20px height=20px /></a>';
          		}
		 },
		 {
	           "searchable": false,
	            "sortable": false,
	            "mDataProp":"accNo",
	           
	            "render": function (mDataProp, type, full, meta) {
	            	 return '<a href=delete/'+mDataProp+'/account><img src='+'"img/delete_icon.jpg"'+' width=20px height=20px /></a>';
	          		}
			 }
		
		]
	});
});