require(["alertUtil"], function (alertUtil) {
	
    $(document).ready(function() {
    	
		$('.removeWebsite').click(function(event) {
			if(!confirm("Are you sure you want to remove this website? You will loose all data associated with it."))
				return;
			
			alertUtil.warning("Deleting website...");
			
			var websiteId = $(event.target).closest("button").attr("data-id");
			
			$.ajax("/webmon/websites/" + websiteId, {
				data: $(this).serialize(),
				method: "DELETE",
				dataType: "json",
				success: function (response) {
				if(response.status === 500)
					alertUtil.danger("Deleting website failed. Please try again");
				else {
					alertUtil.success("Successfully deleted website.");
					setTimeout(function () {
						location.reload();
					}, 1000);
				}
			}});
			
			return false;
		});
	});
});