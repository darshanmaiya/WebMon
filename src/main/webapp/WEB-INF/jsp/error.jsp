<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebMon - Error</title>
	
	<jsp:include page="head.jsp" />
	
	<link rel="stylesheet" href="../../css/error.css">
</head>
<body>

	<jsp:include page="header.jsp" />
	<div class="container outer-body">
	
		<div class="jumbotron">
			<h1 class="text-danger">Blimey! That's an error :-(</h1>
			<% if (request.getAttribute("errorCode") != null) { %>
				<h3 class="text-warning">Status: <strong><%= request.getAttribute("errorCode") %></strong></h3>
				<h3 class="text-warning">Message: <strong><%= request.getAttribute("errorMessage") %></strong></h3>
			<% } else { %>
				<h3 class="text-warning">Status: <strong>Unknown</strong></h3>
				<h3 class="text-warning">Message: <strong>Unknown</strong></h3>
			<% } %>
			<p>Click <a href="/">here</a> to go back to the WebMon home page.</p>
		</div>
	</div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>