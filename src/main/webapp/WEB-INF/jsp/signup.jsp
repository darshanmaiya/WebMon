<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebMon - Welcome</title>
	
	<jsp:include page="head.jsp" />
</head>
<body>

	<jsp:include page="header.jsp" />
	<div class="container outer-body">
		<form name="loginForm" action="/login" method="post" class="form-inline">
		
			<div class="row">
				<div class="col-sm-2">
					<label for="username">Name: </label>
				</div>
				<div class="col-sm-10">
					<input class="form-control" type="text" required id="name" />
				</div>
			</div>
			
			<br /><br />
			
			<div class="row">
				<div class="col-sm-2">
					<label for="username">Email: </label>
				</div>
				<div class="col-sm-10">
					<input class="form-control" type="text" required id="email" />
				</div>
			</div>
			
			<br /><br />
			
			<div class="row">
				<div class="col-sm-2">
					<label for="password">Password: </label>
				</div>
				<div class="col-sm-10">
					<input class="form-control" type="password" required id="password" />
				</div>
			</div>
			
			<br /><br />
			
			<div class="row">
				<div class="col-sm-2">
					<label for="password">Re-enter Password: </label>
				</div>
				<div class="col-sm-10">
					<input class="form-control" type="password" required id="re-password" />
				</div>
			</div>
			
			<br /><br />
			
			<input class="btn btn-primary" type="submit" value="Sign Up" />
		</form>
		
	</div>
	
	<jsp:include page="footer.jsp" />

	<script>
		require(["scripts/signup"]);
	</script>
</body>
</html>