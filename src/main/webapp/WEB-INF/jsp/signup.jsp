<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebMon - Sign Up</title>
	
	<jsp:include page="head.jsp" />
</head>
<body>

	<jsp:include page="header.jsp" />
	<div class="container outer-body">
		<form name="loginForm" action="/signup" method="post" class="form-signin">
		
			<h2 class="form-signin-heading">Sign Up for WebMon</h2>
			<input class="form-control" type="text" required autofocus id="name" placeholder="Name" />
			<input class="form-control" type="text" required id="email" placeholder="Email" />
			<input class="form-control" type="text" required id="phone" placeholder="Phone" />
			<input class="form-control" type="password" required id="password" placeholder="Password" />
			<input class="form-control" type="password" required id="re-password" placeholder="Re-enter Password" />
			
			<button class="btn btn-primary btn-block btn-lg" type="submit">
				<span>Sign Up</span>
				<span class="glyphicon glyphicon-check"></span>
			</button>
		</form>
		
	</div>
	
	<jsp:include page="footer.jsp" />

	<script>
		require(["scripts/signup"]);
	</script>
</body>
</html>