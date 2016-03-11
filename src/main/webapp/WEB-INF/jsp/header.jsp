<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="webmon.utils.AuthenticationUtils" %>
<%@ page import="webmon.utils.DatastoreUtils" %>
<%@ page import="webmon.models.User" %>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#webmon-collapsed" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">WebMon</a>
			<p class="navbar-text">A simple website monitoring service</p>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="webmon-collapsed">
			
			<ul class="nav navbar-nav navbar-right">
				<% if(AuthenticationUtils.isLoggedIn(request)) { %>
				<% User user = DatastoreUtils.getUser(String.valueOf(request.getSession(false).getAttribute("user"))); %>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%= user.getName() %> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/webmon">Go to Dashboard</a></li>
							<li><a href="/webmon/users/<%= user.getId() %>">View profile</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="/logout">Logout</a></li>
						</ul>
					</li>
				<% } else { %>
					<li>
						<p class="navbar-btn">
							<a href="/login" class="btn btn-primary">Log In</a>&nbsp;&nbsp;
						</p>
					</li>
					<li>
						<p class="navbar-btn">
							<a href="/signup" class="btn btn-default">Sign Up</a>
						</p>
					</li>
				<% } %>
			</ul>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid -->
</nav>