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
		<h2 class="text-muted">Welcome to your WebMon dashboard</h2>
		<hr class="text-muted" />
		
		<div class="row">
			<div class="col-sm-8">
				<h5>You are currently monitoring &lt;&gt; websites.</h5>
			</div>
			<div class="col-sm-4 text-right">
				<a href="webmon/websites/add">
					<button class="btn btn-primary btn-lg">
						<span class="glyphicon glyphicon-plus"></span>
						Add website
					</button>
				</a>
			</div>
		</div>
		<br /><br />
		<h4>Websites you are monitoring:</h4>
		
	</div>
	
	<jsp:include page="footer.jsp" />
	
	<script>
		require(["scripts/login"]);
	</script>
</body>
</html>