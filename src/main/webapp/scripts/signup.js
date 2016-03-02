require(["alertUtil"], function (alertUtil) {
	
	// Validate user input on signup
    $(document).ready(function() {
    	
		$('#signupForm').submit(function(event) {

			// Check for valid phone no
			var phoneNo = $('#phone').val();
			var phoneNoRegex = /^[1-9][0-9]{9}$/;
			if (phoneNo.length != 10 || !phoneNo.match(phoneNoRegex)) {
				alertUtil.danger("Invalid Phone Number.");
				event.preventDefault();
				
				return false;
			}
			
			// Check for password length requirement. Minimum length of 6
			var pass = $('#password').val();
			var passlength = pass.length;

			if (passlength < 6) {
				alertUtil.danger("Minimum password length required: 6");
				event.preventDefault();
				
				return false;
			}
			
			// Check for e-mail id validity
			var mail = $('#email').val();
			
			if (mail.indexOf('@') === -1) {
				alertUtil.danger("Invalid Email.");
				alertUtil.preventDefault();
				
				return false;
			}
				
			if($('#password').val() != $('#repassword').val()) {
				alertUtil.danger("Passwords entered don't match.");
				event.preventDefault();
				
				return false;
			}
			
			event.preventDefault();
			event.stopPropagation();
			
			alertUtil.warning("Signing you up for WebMon...hang tight...");
			
			$.post("/signup", $(this).serialize(), function (response) {
				if(response === "User already exists")
					alertUtil.danger("User with given details already exists. Please try again");
				else {
					alertUtil.success("Sign up successful. Redirecting...");
					setTimeout(function () {
						location.href = "/login";
					}, 1000);
				}
			});
			
			return false;
		});
	});
});