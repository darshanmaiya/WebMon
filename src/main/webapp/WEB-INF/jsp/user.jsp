<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebMon - Your profile</title>
	
	<jsp:include page="head.jsp" />
</head>
<body>

	<jsp:include page="header.jsp" />
	<div class="container outer-body">
		<jsp:include page="alertUtil.jsp" />
		<form id="signupForm" name="signupForm" class="form-signin">
		
			<h2 class="form-signin-heading">Your profile</h2>
			<input class="form-control" type="text" required autofocus id="name" name="name" placeholder="Name" />
			<input class="form-control" type="text" readonly id="email" name="email" placeholder="Email" />
			<input class="form-control" type="text" required id="phone" name="phone" placeholder="Phone" />
			<input class="form-control" type="password" id="password" name="password" placeholder="Password" />
			<input class="form-control" type="password" id="repassword" name="repassword" placeholder="Re-enter Password" />
			
			<button class="btn btn-primary btn-block btn-lg" id="submit" type="submit">
				<span>Update Profile</span>
				<span class="glyphicon glyphicon-check"></span>
			</button>
		</form>
		
	</div>
	
	<jsp:include page="footer.jsp" />

	<script>
		require(["user"]);
	</script>
</body>
</html>