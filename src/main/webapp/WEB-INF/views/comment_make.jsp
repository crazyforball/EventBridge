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

<title>Comment Make</title>

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
				              <a class="dropdown-item" href="<fmt:message key="nav_dropdown_my_followings.path"/>?username=${user.username}">My Followings</a>
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
		<div class="container">
			<div align="center">
				<h2>Leave Comment</h2>
				<div class="flex-container-mid">
					<div class="flex-item-mid">
						<table border="0">
							<tr height="50px"></tr>
							<tr class="event-info">
								<td colspan="2"><b>Event Details:</b></td>
							</tr>
				            <tr class="event-info">
				                <td width="150px">Event Name:</td>
				                <td>${event.eventName}</td>
				            </tr>
				            <tr class="event-info">
				                <td>Location:</td>
				                <td>${event.location}</td>
				            </tr>
				            <tr class="event-info">
				                <td>Start Date:</td>
				                <td>${event.startDate}</td>
				            </tr>
				            <tr class="event-info">
				                <td>End Date:</td>
				                <td>${event.endDate}</td>
				            </tr>
				            <tr class="event-info">
				                <td>Fees ($AUD):</td>
				                <td>${event.fees}</td>
				            </tr>
				            <tr class="event-info">
				                <td>Category:</td>
				                <td>${event.category}</td>
				            </tr>
				        </table>
					</div>
					<div class="flex-item-mid">
						<form:form method="post" commandName="comment" action="comment/make?eventId=${event.eid}">
							<table border="0">
								<tr height="50px"></tr>
								<tr class="user-info">
									<td colspan="2"><b>Comment Details:</b></td>
								</tr>
								<tr class="user-info">
									<td width="200px">Rating:</td>
									<td>&nbsp<form:radiobutton path="rating" value="1"/>&nbsp&nbspPositive&nbsp&nbsp<form:radiobutton path="rating" value="0"/>&nbsp&nbspNeutral&nbsp&nbsp<form:radiobutton path="rating" value="-1"/>&nbsp&nbspNegative&nbsp&nbsp</td>
								</tr>
								<tr class="user-info">
									<td>Tell us more:</td>
									<td><form:textarea path="content" rows="5" cols="50"/></td>
								</tr>
								<tr height="10px">
								</tr>
								<tr class="user-info">
									<td></td>
									<td>
										<button type="submit" >Confirm</button>&nbsp&nbsp
										<button type="reset" onClick="javascript:history.back(-1);">Cancel</button>
									</td>
								</tr>
							</table>
						</form:form>
					</div>
				</div>
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