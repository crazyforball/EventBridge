<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>My Admin Center</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">Administration Management Center</a>
    </div>
    <div>
        <ul class="nav navbar-nav">
            <li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    User Censorship
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                	<li><a href="${pageContext.request.contextPath}/admin/user-mgmt">Pending Host User</a></li>
                	<li><a href="#">Operation Log</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Event Verification
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">User Center</a></li>
                    <li><a href="#">EJB</a></li>
                    <li><a href="#">Jasper Report</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Jasper Report</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Jasper Report</a></li>
                </ul>
            </li>
        </ul>
    </div>
    </div>
</nav>

<div class="container" align="center">
	<div id="welcome-pannel" align="left">
		<h3 id="welcome-heading"></h3>
	</div>
	<div id="info-pannel">
		<table border="1">
			<tr>
				<td>User ID: </td>
				<td id="t-uid"></td>
			</tr>
			<tr>
				<td>Account: </td>
				<td id="t-username"></td>
			</tr>
			<tr>
				<td>Full Name: </td>
				<td id="t-fullname"></td>
			</tr>
			<tr>
				<td>User Type: </td>
				<td id="t-utype"></td>
			</tr>
		</table>
	</div>
</div>

</body>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var url = "${pageContext.request.contextPath}/admin/5";
		$.ajax({
			url:url,
			type:"GET",
			success: function(data) {
				var ddd = $.parseJSON(data);
				var user_type;
				if (typeof(ddd) == 'undefined') {
					alert("Invalid Operation!");
					windows.history.back(-1);
				}
				/* alert(data); */
				$("#welcome-heading").text("Welcome, " + ddd.username);
				$("#t-uid").text(ddd.uid);
				$("#t-username").text(ddd.username);
				$("#t-fullname").text(ddd.firstName + " " + ddd.lastName);
				if (ddd.utype == 3) {
					user_type="Administrator";
				}
				$("#t-utype").text(user_type);
			}
		});
	});
</script>
</html>