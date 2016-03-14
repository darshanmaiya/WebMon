<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="webmon.models.User" %>
<%@ page import="webmon.models.Website" %>
<%@ page import="webmon.utils.DatastoreUtils" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebMon - Dashboard</title>
	
	<jsp:include page="head.jsp" />
</head>
<body>

	<jsp:include page="header.jsp" />
	<div class="container outer-body">
		<jsp:include page="alertUtil.jsp" />
		<h2 class="text-muted">Welcome to your WebMon dashboard</h2>
		<hr class="text-muted" />
		
		<div class="row">
			<% User user = DatastoreUtils.getUser(String.valueOf(request.getSession(false).getAttribute("user"))); %>
			<% List<Long> monitoredWebsites = user.getMonitoredWebsites(); %>
			<% int numMonitoredWebsites = monitoredWebsites.size(); %>
			<div class="col-sm-8">
				<h5>You are currently monitoring <strong><%= numMonitoredWebsites %></strong> websites.</h5>
			</div>
			<div class="col-sm-4 text-right">
				<a id="addWebsite" href="webmon/websites/add">
					<button class="btn btn-primary btn-lg">
						<span class="glyphicon glyphicon-plus"></span>
						Add website
					</button>
				</a>
			</div>
		</div>
		<br /><br />
		<% if (numMonitoredWebsites == 0) { %>
			<h4>You are not monitoring any websites. Add a website to get started.</h4>
		<% } else { %>
			<h4>Websites you are monitoring:</h4>
			<br />
			<% for (int i=0; i<numMonitoredWebsites; i++) { %>
				<div class="row website-row">
					<% Website website = DatastoreUtils.getWebsite(monitoredWebsites.get(i)); %>
					<% if(website == null) { i--; continue; } %>
					<div class="col-md-8">
						<a class="btn btn-link" href="/webmon/websites/<%= website.getId() %>">
							<span title="<%= website.getUrl() %>"><%= website.getName() %></span>
						</a>
					</div>
					<div class="col-md-4" style="text-align: right">
						<a title="Go to website" class="btn btn-link" href="<%= website.getUrl() %>" target="_blank">
							<span class="glyphicon glyphicon-new-window"></span>
						</a>
						<a class="btn btn-link" href="/webmon/websites/<%= website.getId() %>">
							<span class="glyphicon glyphicon-pencil"></span>
						</a>
						<button data-id="<%= website.getId() %>" class="removeWebsite btn btn-link" title="Remove website" style="color: red">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
					</div>
				</div>
			<% } %>
		<% } %>
	</div>
	
	<jsp:include page="footer.jsp" />
	<script>
		require(["dashboard"]);
	</script>
</body>
</html>