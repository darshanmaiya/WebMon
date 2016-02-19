require(["scripts/alert"], function (alert) {
	if(location.href.indexOf("logout") != -1)
		alert.success("You have successfully logged out of WebMon.");
});