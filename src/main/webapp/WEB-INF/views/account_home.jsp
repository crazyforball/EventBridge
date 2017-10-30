<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="http://getbootstrap.com/favicon.ico">

<title>Account Home</title>

<!-- Bootstrap core CSS -->
<link href="<fmt:message key="css_js.path"/>/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<fmt:message key="css_js.path"/>/css/jumbotron.css" rel="stylesheet">
<link rel="stylesheet" href="<fmt:message key="css_js.path"/>/css/style.css" media="screen" type="text/css">
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">


</head>

<body>

	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="./">Event Bridge</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<c:choose>
					<c:when test="${sessionScope.user != null}">
						<li class="nav-item"><a class="nav-link" href="<fmt:message key="nav_home.path"/>">Home <span class="sr-only">(current)</span></a></li>
						<li class="nav-item"><a class="nav-link" href="<fmt:message key="nav_contact.path"/>">Contact</a></li>
						<li class="nav-item dropdown">
				            <a class="nav-link dropdown-toggle" href="http://example.com/" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${sessionScope.user.username}</a>
				            <div class="dropdown-menu" aria-labelledby="dropdown01">
				              <a class="dropdown-item" href="<fmt:message key="nav_dropdown_my_account.path"/>">My Account Home</a>
				              <a class="dropdown-item" href="<fmt:message key="nav_dropdown_my_bookings.path"/>?username=${user.username}">My Bookings</a>
				              <a class="dropdown-item" href="<fmt:message key="nav_dropdown_my_posts.path"/>?username=${user.username}">My Posts</a>
				              <a class="dropdown-item" href="<fmt:message key="nav_dropdown_post_event.path"/>">Post an Event</a>
				              <a class="dropdown-item" href="<fmt:message key="nav_dropdown_logout.path"/>">Log out</a>
				            </div>
         				 </li>
					</c:when>
					<c:otherwise>
							<!-- <li class="nav-item active"> -->
							<li class="nav-item"><a class="nav-link" href="<fmt:message key="nav_home.path"/>">Home <span class="sr-only">(current)</span></a></li>
							<li class="nav-item"><a class="nav-link" href="<fmt:message key="nav_signup.path"/>">Sign up</a></li>
							<li class="nav-item"><a class="nav-link" href="<fmt:message key="nav_login.path"/>">Log in</a></li>
							<li class="nav-item"><a class="nav-link" href="<fmt:message key="nav_contact.path"/>">Contact</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<form class="form-inline my-2 my-lg-0" action="<fmt:message key="nav_search.path"/>">
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search" name="keyword"> 
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="layered-menu">
			<ul id="accordion" class="accordion">
				<c:choose>
					<c:when test="${user.utype == 2}">
						<li>
							<div class="link"><i class="fa fa-chevron-down"></i>Admin Center</div>
							<ul class="submenu">
								<li><a href="./admin?adminName=${user.username}">Admin Home</a></li>
								<li><a href="${pageContext.request.contextPath}/admin/user-mgmt?adminName=${user.username}">User Management</a></li>
								<li><a href="${pageContext.request.contextPath}/admin/event-mgmt?adminName=${user.username}">Event Management</a></li>
								<li><a href="${pageContext.request.contextPath}/category/home?adminName=${user.username}">Category Management</a></li>
								<li><a href="${pageContext.request.contextPath}/admin/log-list?adminName=${user.username}">Log List</a></li>
							</ul>
						</li>
					</c:when>
				</c:choose>
					<li>
						<div class="link"><i class="fa fa-chevron-down"></i>My Account</div>
						<ul class="submenu">
							<li><a href="./account/home?username=${user.username}">Account Details</a></li>
							<li><a href="./account/update?username=${user.username}">Update Account</a></li>
						</ul>
					</li>
					<li>
						<div class="link"><i class="fa fa-chevron-down"></i>My Activity</div>
						<ul class="submenu">
							<li><a href="./booking/home?username=${user.username}">My Bookings</a></li>
							<li><a href="./event/myPosts?username=${user.username}">My Posts</a></li>
							<li><a href="#">My Followings</a></li>
							<li><a href="./event/post">Post an Event</a></li>
						</ul>
					</li>
				</ul>
		</div>
		
		<div class="container">
			<div align="center">
				<h3>Greetings!</h3>
				<h3>Here's a review of your account details:</h3>
		        <table border="0">
		        	<tr height="50px"></tr>
		            <tr class="account-info">
		                <td width="150px">Username:</td>
		                <td>${user.username}</td>
		            </tr>
		            <tr class="account-info">
		                <td>First Name:</td>
		                <td>${user.firstName}</td>
		            </tr>
		            <tr class="account-info">
		                <td>Last Name:</td>
		                <td>${user.lastName}</td>
		            </tr>
		            <tr class="account-info">
		                <td>Date of Birth:</td>
		                <td>${user.DOB}</td>
		            </tr>
		            <tr class="account-info">
		                <td>Phone:</td>
		                <td>${user.phoneNum}</td>
		            </tr>
		            <tr class="account-info">
		                <td>Email:</td>
		                <td>${user.email}</td>
		            </tr>
		            <tr height="120px"></tr>
		        </table>
		    </div>
		</div>    
    </div>

	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<fmt:message key="css_js.path"/>/js/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	<script src="<fmt:message key="css_js.path"/>/js/gallery.js"></script>
	<script src="<fmt:message key="css_js.path"/>/js/popper.min.js"></script>
	<script src="<fmt:message key="css_js.path"/>/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<fmt:message key="css_js.path"/>/js/ie10-viewport-bug-workaround.js"></script>
	
	<script type="text/javascript" src="<fmt:message key="css_js.path"/>/js/jquery.js"></script>
	<script type="text/javascript" src="<fmt:message key="css_js.path"/>/js/index.js"></script>
	<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>
</body>
</html>