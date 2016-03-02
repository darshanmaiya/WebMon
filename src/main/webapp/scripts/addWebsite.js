require(["alertUtil"], function (alertUtil) {
	
	$("#addWebsiteForm").submit(function (event) {

		event.preventDefault();
		event.stopPropagation();
		
		$.post("/webmon/websites", $(this).serialize(), function (response) {
			if(response.result === "fail")
				alertUtil.danger("New website cannot be added. Please try again.");
			else {
				alertUtil.success("Website added successfully. Redirecting back to dashboard...");
				setTimeout(function () {
					location.href = "/webmon";
				}, 2000);
			}
		});
		
		return false;
	});
});