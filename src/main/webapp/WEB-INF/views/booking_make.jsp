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

<title>Booking Make</title>

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
				<table>
				 	<tr>
		                <td colspan="4" align="center"><h2>Make a Booking</h2></td>
		          	</tr>
					<tr>
						<td>
							<table border="1">
					            <tr>
					                <td>Event Name:</td>
					                <td>${event.eventName}</td>
					            </tr>
					            <tr>
					                <td>Event Organizer:</td>
					                <td>${event.creator.username}</td>
					            </tr>
					            <tr>
					                <td>Location:</td>
					                <td>${event.location}</td>
					            </tr>
					            <tr>
					                <td>Start Date:</td>
					                <td>${event.startDate}</td>
					            </tr>
					            <tr>
					                <td>End Date:</td>
					                <td>${event.endDate}</td>
					            </tr>
					            <tr>
					                <td>Capacity:</td>
					                <td>${event.capacity}</td>
					            </tr>
					            <tr>
					                <td>Fees ($AUD):</td>
					                <td>${event.fees}</td>
					            </tr>
					            <tr>
					                <td>Category:</td>
					                <td>${event.category}</td>
					            </tr>
					        </table>
						</td>
						<td>
						</td>
						<td>
							<form:form method="post" commandName="booking" action="booking/make?eventId=${event.eid}">
								<table border="1">
									<tr hidden="true">
										<td>UID:</td>
										<td><form:input path="uid" value="${user.uid}"/></td>
									</tr>
									<tr hidden="true">
										<td>EID:</td>
										<td><form:input path="eid" value="${event.eid}"/></td>
									</tr>
									<tr>
										<td>Firstname:</td>
										<td>${user.firstName}</td>
									</tr>
									<tr>
										<td>Lastname:</td>
										<td>${user.lastName}</td>
									</tr>
									<tr>
										<td>DoB (YYYY-MM-DD):</td>
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
										<td></td>
										<td>
											<button type="submit" >Confirm</button>
											<button type="reset" >Cancel </button>
										</td>
									</tr>
								</table>
							</form:form>
						</td>
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