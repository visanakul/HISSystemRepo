$( function() {
    $( "#datepicker" ).datepicker(
    							  { 
    								format: 'DD-MM-YYYY',
    							    changeYear: true, 
    					            changeMonth: true,
    							    yearRange:'-100:+0'
    							  }
    							);
  } );
function regFormValidate(){
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
            "photoFile":{
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
            "photoFile":{
                required: "Photo is required!"
            }
		},
		 errorPlacement: function(error, element) 
      {
        if (element.is(":radio")) 
        {
            error.appendTo(element.parents('.gen'));
        }
        else 
        { 
            error.insertAfter(element);
        	//$(".error").html( error );  
        }
        }
	});
}
