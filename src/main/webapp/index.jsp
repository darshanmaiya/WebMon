<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WebMon - Welcome</title>
	
	<jsp:include page="WEB-INF/jsp/head.jsp" />
</head>
<body>

	<jsp:include page="WEB-INF/jsp/header.jsp" />
	<div class="container outer-body">
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="images/downtimes.png" alt="Carousel" />
					<div class="carousel-caption">
						Monitor when you website goes down
      				</div>
    			</div>
				<div class="item">
					<img src="images/response-times.png" alt="Carousel" />
					<div class="carousel-caption">
						Record the response times of your website
					</div>
				</div>
				<div class="item">
					<img src="images/notifications.png" alt="Carousel" />
					<div class="carousel-caption">
						Get fast notifications when your website is down
					</div>
				</div>
		  	</div>

			<!-- Controls -->
			<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
			
		</div>
		
		<div>
			<h3>
				<a href="/login">Log In</a> or <a href="/signup">Sign Up</a> now to get started!
			</h3>
		</div>
		
	</div>
	
	
	<jsp:include page="WEB-INF/jsp/footer.jsp" />
</body>
</html>