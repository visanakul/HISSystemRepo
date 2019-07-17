$( document ).ready(function() {
    console.log( "ready!" );
    console.log('Account number '+$('#accNo').val());
    var ssn=$('#ssn').val();
    console.log('SSN : '+ssn);
    var ssn1=ssn.substring(0,3);
    var ssn2=ssn.substring(3,5);
    var ssn3=ssn.substring(5,9);
    console.log(ssn1+'-'+ssn2+'-'+ssn3);
    $('#ssn1').val(ssn1);
    $('#ssn2').val(ssn2);
    $('#ssn3').val(ssn3);
});