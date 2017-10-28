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
				              <a class="dropdown-item" href="<fmt:message key="nav_dropdown_logout.path"/>">Log out</a>
				            </div>
         				 </li>
					</c:when>
					<c:otherwise>
							<!-- <li class="nav-item active"> -->
							<li class="nav-item"><a class="nav-link" href="<fmt:message key="nav_home.path"/>">Home <span class="sr-only">(current)</span></a></li>
							<li class="nav-item"><a class="nav-link" href="<fmt:message key="nav_signup.path"/>">Sign Up</a></li>
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
			<a class="nav-link" href="<fmt:message key="nav_advanced_search.path"/>">Advanced Search</a>
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<div align="center">
		        <table border="0">
		            <tr>
		                <td colspan="2" align="center"><h2>Account Home</h2></td>
		            </tr>
		            <tr>
		                <td colspan="2" align="center">
		                    <h3>Greetings! Here's the review of your details:</h3>
		                </td>
		            </tr>
		            <tr>
		                <td>Username:</td>
		                <td>${user.username}</td>
		            </tr>
		            <tr>
		                <td>First Name:</td>
		                <td>${user.firstName}</td>
		            </tr>
		            <tr>
		                <td>Last Name:</td>
		                <td>${user.lastName}</td>
		            </tr>
		            <tr>
		                <td>Date of Birth:</td>
		                <td>${user.DOB}</td>
		            </tr>
		            <tr>
		                <td>Phone:</td>
		                <td>${user.phoneNum}</td>
		            </tr>
		            <tr>
		                <td>Email:</td>
		                <td>${user.email}</td>
		            </tr>
		            <tr>
		                <td>Passport:</td>
		                <td>${user.passport}</td>
		            </tr>
		            <tr>
		                <td>DriverLicense:</td>
		                <td>${user.driverLicense}</td>
		            </tr>
		            <!-- account setting -->
		        	<c:choose>
		        		<c:when test="${user.utype == 2}">
		        			<tr>
		        				<td><a href="./admin?adminName=${user.username}">My Admin Center</a></td>
		        			</tr>
		        		</c:when>
		        	</c:choose>
          			<tr>
          				<td><a href="./booking/home?username=${user.username}">My Bookings</a></td>
          			</tr>
          			<tr>
          				<td><a href="./event/myPosts?username=${user.username}">My Posts</a></td>
          			</tr>
          			<tr>
          				<td><a href="./event/post">Post an Event</a></td>
          			</tr>
          			<tr>
          				<td><a href="./account/update?username=${user.username}">Update Account</a></td>
          			</tr>
		          	<tr>
		          		<td><a href="./">Go to Event Bridge Home</a></td>
		          	</tr>
		 
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

</body>
</html>