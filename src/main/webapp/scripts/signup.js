require(["scripts/alert"], function (alert) {
	
	// Validate user input on signup
    $(document).ready(function() {
    	
		$('#signupForm').submit(function(event) {

			// Check for valid phone no
			var phoneNo = $('#phone').val();
			var phoneNoRegex = /^[1-9][0-9]{9}$/;
			if (phoneNo.length != 10 || !phoneNo.match(phoneNoRegex)) {
				alert.danger("Invalid Phone Number.");
				event.preventDefault();
				
				return false;
			}
			
			// Check for password length requirement. Minimum length of 6
			var pass = $('#password').val();
			var passlength = pass.length;

			if (passlength < 6) {
				alert.danger("Minimum password length required: 6");
				event.preventDefault();
				
				return false;
			}
			
			// Check for e-mail id validity
			var mail = $('#email').val();
			
			if (mail.indexOf('@') === -1) {
				alert.danger("Invalid Email.");
				event.preventDefault();
				
				return false;
			}
				
			if($('#password').val() != $('#repassword').val()) {
				alert.danger("Passwords entered don't match.");
				event.preventDefault();
				
				return false;
			}
			
			event.preventDefault();
			event.stopPropagation();
			
			alert.warning("Signing you up for WebMon...hang tight...");
			
			$.post("/signup", $(this).serialize(), function (response) {
				if(response === "User already exists")
					alert.danger("User with given details already exists. Please try again");
				else {
					alert.success("Sign up successful. Redirecting...");
					setTimeout(function () {
						location.href = "/login";
					}, 3000);
				}
			});
			
			return false;
		});
	});
});