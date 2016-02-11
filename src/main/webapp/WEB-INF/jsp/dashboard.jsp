<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<div class="alert alert-success hide" role="alert" id="alert">
		
		</div>
		Welcome to your WebMon dashboard.
		
	</div>
	
	<jsp:include page="footer.jsp" />
	
	<script>
		require(["scripts/login"]);
	</script>
</body>
</html>