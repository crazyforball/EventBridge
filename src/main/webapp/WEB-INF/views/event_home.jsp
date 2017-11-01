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

<title>Event Home</title>

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
			<c:choose>
				<c:when test="${event.status == 'APPROVED'}">
				<div class="flex-container">
					<div class="flex-item">
						<table id="event-media">
				        	<tr>
				        		<td><img alt="${event.imageUrl}" src="${basePath}${event.imageUrl}" width="400" height="300"></td>
				        	</tr>
							<tr>
								<td><embed src="${basePath}${event.audioUrl}" width="400" height="50" loop="false" autostart="false"/></td>
							</tr>
							<tr height="50">
							</tr>
							<tr class="event-info">
								<td class="event-field">Description:</td>
							</tr>
							<tr class="event-info">
								<td>${event.description}</td>
							</tr>
						</table>
					</div>
					<div class="flex-item">
						<table id="event-desc">
				            <tr>
				                <!-- <td>Event Name:</td> -->
				                <td colspan="2"><h1 id="event-name">${event.eventName}</h1></td>
				            </tr>
				            <tr height="10">
				            </tr>
				            <tr class="event-info">
				            	<c:choose>
									<c:when test="${positiveRate == null}">
										<td>No Feedback Yet</td>
									</c:when>
									<c:otherwise>
				            			<td><a href="./event/comments?eventId=${event.eid}">${positiveRate}% Positive Feedback</a></td>
				            		</c:otherwise>
				            	</c:choose>
				            </tr>
				            <tr height="20">
				            </tr>
				            <tr class="event-info">
				                <td><span class="event-field">Location:</span>&nbsp&nbsp${event.location}</td>
				            </tr>
				            <tr class="event-info">
				                <td><span class="event-field">Start Date:</span>&nbsp&nbsp${event.startDate}</td>
				            </tr>
				            <tr class="event-info">
				                <td><span class="event-field">End Date:</span>&nbsp&nbsp${event.endDate}</td>
				            </tr>
				            <tr class="event-info">
				                <td><span class="event-field">Capacity:</span>&nbsp&nbsp${event.capacity}</td>
				            </tr>
				            <tr class="event-info">
				                <td><span class="event-field">Fees ($AUD):</span>&nbsp&nbsp${event.fees}</td>
				            </tr>
				            <tr class="event-info">
				                <td><span class="event-field">Category:</span>&nbsp&nbsp${event.category}</td>
				            </tr>
				            <tr height="20px">
				            	<td></td>
				            </tr>
				            <c:choose>
			            		<c:when test="${sessionScope.user.username == event.creator.username}">
			            		<tr class="event-info">
			      					<td align="left"><a href="./event/edit?eventId=${event.eid}"><Button type="button">&nbsp&nbspEdit&nbsp&nbsp</Button></a>&nbsp&nbsp<a href="./event/delete?eventId=${event.eid}"><Button type="button">Delete</Button></a></td>
			      				</tr>
			      				</c:when>
			      				<c:otherwise>
			      				<tr class="event-info">
			      					<td align="left"><a href="./booking/make?eventId=${event.eid}"><Button type="button">&nbspBook&nbsp</Button></a>&nbsp&nbsp<a href="./following/make?eventId=${event.eid}"><Button type="button">Follow</Button></a></td>
			      				</tr>
			      				</c:otherwise>
			      			</c:choose>
			      			<tr height="50">
							</tr>
			      			<tr>
			      				<td>
			      					<div class="google-map">
			      						<a href="http://maps.google.com/?q=${locationInURL}"><img src="https://maps.googleapis.com/maps/api/staticmap?center=${locationInURL}&zoom=14&size=640x400&maptype=roadmap&markers=${locationInURL}&key=AIzaSyBAHxui9Nd_RSSlCWV8kdpM2CATCFjDdmM" style="width:100%"></a>
			      					</div>
			      				</td>
			      			</tr>
				        </table>
				   </div>
			   </div>
			   </c:when>
			   <c:otherwise>
		        <table>
		       		<tr height="10px">
		            </tr>
		            <tr>
		                <td align="center"><h2>This event is not available now!</h2></td>
		            </tr>
		            <tr height="10px">
		            </tr>
		            <tr>
		            	<td align="center"><h3>Have a look at other events!</h3></td>
		            </tr>
		            <tr height="30px">
		            </tr>	
	            </table>		   
		        </c:otherwise>
			  </c:choose>
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