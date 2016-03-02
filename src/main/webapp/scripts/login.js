require(["scripts/alert"], function (alert) {
	
	var requestParameter = $("#request-parameters").text();
	
	if(requestParameter === "signup")
		alert.success("Sign up successful. Please use your new credentials to login.");
	
	if(requestParameter === "logout")
		alert.success("Logout successful.");
	
	$("#loginForm").submit(function (event) {

		event.preventDefault();
		event.stopPropagation();
		
		$.post("/login", $(this).serialize(), function (response) {
			if(response === "fail")
				alert.danger("Email and/or password incorrect.");
			else {
				alert.success("Login successful. Redirecting...");
				setTimeout(function () {
					location.href = "/webmon";
				}, 1000);
			}
		});
		
		return false;
	});
});