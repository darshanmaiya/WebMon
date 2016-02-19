require([], function () {
	
	// Validating user input while signup
    $(document).ready(function() {
    	
		$('#submit').submit(function(event){
			// Check for password length requirement. Minimum length of 8
			pass = $('#password').val();
			var passlength = pass.length;

			if (passlength < 8){
				alert("Minimum password length required: 8");
				// Prevent form submission
				event.preventDefault();
				return false;
			}
			
			// Check for phone no
			phoneno = $('#phone').val();
			var phonelength = phoneno.length();
			
			if (phonelength < 10){
				alert("Invalid phone number: Numbers less than 10");
				// Prevent form submission
				event.preventDefault();
				return false;
			}
				
			var phonenochecker = /^\+?([0-9]{2})\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})$/;
			if (!phoneno.value.match(phonenochecker))
			{
				alert("Invalid phone number");
				event.preventDefault();
				return false;
			}
				
			// Check for e-mail id validity
			mail = $('#email').val();
			
			if (mail.indexOf('@') === -1)
			{
				alert("Invalid mail id");
				event.preventDefault();
				return false;
			}
				
			if($('#password').val() != $('#repassword').val()) {
				alert("Password and Confirm Password doesn't match");
				// Prevent form submission
				event.preventDefault();
				return false;
			}
		});
	});
});