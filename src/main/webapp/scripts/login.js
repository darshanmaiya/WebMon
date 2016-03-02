require(["alertUtil"], function (alertUtil) {
	
	var requestParameter = $("#request-parameters").text();
	
	if(requestParameter === "signup")
		alertUtil.success("Sign up successful. Please use your new credentials to login.");
	
	if(requestParameter === "logout")
		alertUtil.success("Logout successful.");
	
	$("#loginForm").submit(function (event) {

		event.preventDefault();
		event.stopPropagation();
		
		$.post("/login", $(this).serialize(), function (response) {
			if(response === "fail")
				alertUtil.danger("Email and/or password incorrect.");
			else {
				alertUtil.success("Login successful. Redirecting...");
				setTimeout(function () {
					location.href = "/webmon";
				}, 1000);
			}
		});
		
		return false;
	});
});