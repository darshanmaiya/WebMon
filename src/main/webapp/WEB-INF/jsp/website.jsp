<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebMon - Website statistics</title>
	
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
		
			<input class="form-control" type="text" required readonly autofocus id="name" name="name" placeholder="Name" />
			<input class="form-control" type="text" required readonly id="url" name="url" placeholder="URL" />
			
			<label><input type="checkbox" id="notifyDown" name="notifyDown" value="notifyDown" /> Notify me when this website is down</label>
			<label><input type="checkbox" id="notifyResponse" name="notifyResponse" value="notifyResponse" /> Notify me when the response time is high</label>
			
			<button class="btn btn-primary btn-block btn-lg" type="submit">
				<span class="glyphicon glyphicon-floppy-disk"></span>
				<span>Update website</span>
			</button>
		</form>
		
		<div id="line_chart" style="width: 100%; height: 500px"></div>
		
	</div>
	
	<jsp:include page="footer.jsp" />
	
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script>
		require(["website"]);
	</script>
</body>
</html>