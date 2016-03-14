require(["alertUtil"], function (alertUtil) {
	
	function getWebsiteId() {
		var websiteid;
		
		websiteid = location.href.match(/\/[0-9]+/);
		websiteid = websiteid[0].substr(1);
		
		return websiteid;
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
		
		var responseInfos = [["Timestamp", "Response Time"]];
		var i;
		var len = website.responseInfo.length;
		if(len === 0) {
			alertUtil.warning("This website doesn't have any monitored data as of now. Please check back after some time.");
			return;
		}

		google.charts.setOnLoadCallback(drawChart);
		
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
						title: 'Response Rate (ms)',
						viewWindowMode:'explicit',
						viewWindow:{
							min:0
						}
					},
					lineWidth: 5
			};

			var chart = new google.visualization.LineChart(document.getElementById('line_chart'));

			chart.draw(data, options);
		}
		
	}, "json");
	
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
				}
			}});
			
			return false;
		});
	});
});