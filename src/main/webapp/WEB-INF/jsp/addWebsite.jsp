<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebMon - Add new website to monitor</title>
	
	<jsp:include page="head.jsp" />
</head>
<body>

	<jsp:include page="header.jsp" />
	<div class="container outer-body">
		<jsp:include page="alertUtil.jsp" />
		<div class="row">
			<div class="col-sm-12">
				<a href="/webmon">
					<button class="btn btn-link">
						<span class="glyphicon glyphicon-arrow-left"></span>
						Back to dashboard
					</button>
				</a>
			</div>
		</div>
		<form name="addWebsiteForm" id="addWebsiteForm" class="form-add-website form-signin">
		
			<h3 class="form-signin-heading">Add a new website to monitor</h3>
			<input class="form-control" type="text" required autofocus id="name" name="name" placeholder="Name" />
			<input class="form-control" type="text" required id="url" name="url" placeholder="URL" />
			
			<label><input type="checkbox" name="notifyDown" checked value="notifyDown" /> Notify me when this website is down</label>
			<label><input type="checkbox" name="notifyResponse" checked value="notifyResponse" /> Notify me when the response time is high</label>
			
			<button class="btn btn-primary btn-block btn-lg" type="submit">
				<span>Add website</span>
				<span class="glyphicon glyphicon-log-in"></span>
			</button>
		</form>
		
	</div>
	
	<jsp:include page="footer.jsp" />
	
	<script>
		require(["addWebsite"]);
	</script>
</body>
</html>