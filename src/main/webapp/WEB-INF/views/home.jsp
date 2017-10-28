<%@ include file="/WEB-INF/views/include.jsp" %>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="http://getbootstrap.com/favicon.ico">

<title>Event Bridge Home</title>

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
		<div class="container" id="idContainer">
			<ul id="idSlider">
				<c:forEach items="${sessionScope.events}" var="event">
					<li><a href="./event/home?eventId=${event.eid}"> <img src="${basePath}${event.imageUrl}" alt="${event.imageUrl}" width="800px" height="450px" /> </a></li>
				</c:forEach>
			</ul>
			<ul class="num" id="idNum">
			</ul>
		</div>
	</div>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<c:forEach items="${sessionScope.categories}" var="category">
				<div class="col-md-4">
					<h3>${category.categoryName}</h3>
					<p>${category.cdesc}</p>
					<p>
						<a class="btn btn-secondary"
							href="./event/category?category=${category.categoryName}"
							role="button">View details </a>
					</p>
				</div>
			</c:forEach>
		</div>

		<hr>
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
	<script>
		var nums = [], timer, n = $$("idSlider").getElementsByTagName("li").length,
		st = new SlideTrans("idContainer", "idSlider", n, {
			onStart: function(){
				forEach(nums, function(o, i){ o.className = st.Index == i ? "on" : ""; })
			}
		});
		for(var i = 1; i <= n; AddNum(i++)){};
		function AddNum(i){
			var num = $$("idNum").appendChild(document.createElement("li"));
			num.innerHTML = i--;
			num.onmouseover = function(){
				timer = setTimeout(function(){ num.className = "on"; st.Auto = false; st.Run(i); }, 200);
			}
			num.onmouseout = function(){ clearTimeout(timer); num.className = ""; st.Auto = true; st.Run(); }
			nums[i] = num;
		}
		st.Run();
	</script>
	<script src="<fmt:message key="css_js.path"/>/js/popper.min.js"></script>
	<script src="<fmt:message key="css_js.path"/>/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<fmt:message key="css_js.path"/>/js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>