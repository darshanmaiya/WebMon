require(["alertUtil"], function (alertUtil) {
	
	function getWebsiteId() {
		var userid;
		
		userid = location.href.match(/\/[0-9]+/);
		userid = userid[0].substr(1);
		
		return userid;
	}
	
	$.get("/webmon/websites/" + getWebsiteId(), function (website) {
		if(!website) {
			alertUtil.danger("FATAL ERROR: Could not retrieve website details.");
			return;
		}
		
		$("#name").val(website.name);
		$("#url").val(website.url);
		if(website.notifyWhenDown && website.notifyWhenDown[0] === true)
			$("#notifyDown").prop("checked", true);
		if(website.notifyWhenResponseIsHigh && website.notifyWhenResponseIsHigh[0] === true)
			$("#notifyResponse").prop("checked", true);
		
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawChart);
		
		var responseInfos = [["Timestamp", "Response Time"]];
		var i;
		var len = website.responseInfo.length;
		var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
		                  "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
		                ];
		for(i=0; i<len; i++) {
			var d = new Date(parseInt(website.responseInfo[i].timestamp));
			var responseInfo = [monthNames[d.getMonth()] + "/" + d.getDate() + " " + d.getHours() + ":" + d.getMinutes(),
			                    parseInt(website.responseInfo[i].responseTime)]
			responseInfos[i+1] = responseInfo;
		}
		
		function drawChart() {
			var data = google.visualization.arrayToDataTable(responseInfos);

			var options = {
					hAxis: {
						title: 'Time'
					},
					vAxis: {
						title: 'Response Rate (ms)'
					}
			};

			var chart = new google.visualization.LineChart(document.getElementById('line_chart'));

			chart.draw(data, options);
		}
		
	}, "json");
	
	// Validate user input on signup
    $(document).ready(function() {
    	
		$('#addWebsiteForm').submit(function(event) {
			alertUtil.warning("Updating website...");
			
			$.ajax("/webmon/websites/" + getWebsiteId(), {
				data: $(this).serialize(),
				method: "PUT",
				dataType: "json",
				success: function (response) {
				if(response.status === "500")
					alertUtil.danger("Updating website details failed. Please try again");
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