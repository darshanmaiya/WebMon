define("scripts/alert", [], function () {
	var alert = {};
	
	alert.hideAll = function () {
		$("#success-alert").addClass("hide");
		$("#warning-alert").addClass("hide");
		$("#danger-alert").addClass("hide");
	}
	
	alert.success = function (string) {
		alert.hideAll();
		$("#success-alert").text(string).removeClass("hide");
	}
	
	alert.warning = function (string) {
		alert.hideAll();
		$("#warning-alert").text(string).removeClass("hide");
	}
	
	alert.danger = function (string) {
		alert.hideAll();
		$("#danger-alert").text(string).removeClass("hide");
	}
	
	return alert;
});