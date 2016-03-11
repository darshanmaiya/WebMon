require(["alertUtil"], function (alertUtil) {
	
	function getUserId() {
		var userid;
		
		userid = location.href.match(/\/[0-9]+/);
		userid = userid[0].substr(1);
		
		return userid;
	}
	
	$.get("/webmon/users/" + getUserId(), function (user) {
		if(!user) {
			alertUtil.danger("FATAL ERROR: Could not retrieve your details.");
			return;
		}
		
		$("#name").val(user.name);
		$("#email").val(user.email);
		$("#phone").val(user.phone);
	}, "json");
	
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

			if (passlength > 0 && passlength < 6) {
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
			
			alertUtil.warning("Updating your profile...");
			
			$.ajax("/webmon/users/" + getUserId(), {
				data: $(this).serialize(),
				method: "PUT",
				dataType: "json",
				success: function (response) {
				if(response.status === "500")
					alertUtil.danger("Updating user details failed. Please try again");
				else {
					alertUtil.success("Successfully updated details.");
					$("#password").text("");
					$("#repassword").text("");
				}
			}});
			
			return false;
		});
	});
});