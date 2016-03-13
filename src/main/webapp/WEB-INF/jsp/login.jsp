<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebMon - Log In</title>
	
	<jsp:include page="head.jsp" />
</head>
<body>

	<jsp:include page="header.jsp" />
	<div class="container outer-body">
		<div class="hide" id="request-parameters"><%= request.getAttribute("parameter") %></div>
		<jsp:include page="alertUtil.jsp" />
		<form name="loginForm" id="loginForm" class="form-signin">
		
			<h2 class="form-signin-heading">Log In to WebMon</h2>
			<input class="form-control" type="text" required autofocus id="email" name="email" placeholder="Email" />
			<input class="form-control" type="password" required id="password" name="password" placeholder="Password" />
			
			<button class="btn btn-primary btn-block btn-lg" type="submit">
				<span class="glyphicon glyphicon-log-in"></span>
				<span>Log In</span>
			</button>
		</form>
		
	</div>
	
	<jsp:include page="footer.jsp" />
	
	<script>
		require(["login"]);
	</script>
</body>
</html>