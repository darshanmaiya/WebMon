require([], function () {
	if(location.href.indexOf("logout") != -1)
		$("#alert").removeClass("hide");
});