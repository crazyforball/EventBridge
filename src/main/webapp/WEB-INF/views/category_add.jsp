<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <a class="navbar-brand" href="./admin?adminName=${user.username}">Admin Center</a>
    </div>
    <div>
        <ul class="nav navbar-nav">
        	<li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Account
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                	<li><a class="dropdown-item" href="./">Event Bridge Home</a></li>
                	<li><a class="dropdown-item" href="<fmt:message key="nav_dropdown_my_account.path"/>">My Account Home</a></li>
                	<li><a class="dropdown-item" href="<fmt:message key="nav_dropdown_logout.path"/>">Log out</a></li>
                </ul>
            </li>
            <li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    User Censorship
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                	<li><a href="./admin/user-mgmt?adminName=${user.username}">User Management</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Event Verification
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="./admin/event-mgmt?adminName=${user.username}">Event Management</a></li>
                </ul>
            </li>
            <li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Category List
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                	<li><a href="./category/home?adminName=${user.username}">Category Management</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Operation Log
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="./admin/log-list?adminName=${user.username}">Log List</a></li>
                </ul>
            </li>
        </ul>
    </div>
    </div>
</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="container">
			<div align="center">
				<table>
				 	<tr>
		                <td colspan="4" align="center"><h2>Add Category</h2></td>
		          	</tr>
		          	<tr height="30px">
		          	</tr>
					<tr>
						<td>
							<form:form method="post" commandName="category" action="category/add">
								<table border="1" width="800">
									<tr>
										<td width="150px">Category Name:</td>
										<td><form:input path="categoryName" size="40"/></td>
									</tr>
									<tr>
										<td>Description:</td>
										<td><form:textarea path="cdesc" rows="5" cols="80"/></td>
									</tr>
									<tr>
										<td></td>
										<td>
											<button type="submit" >Submit</button>
											<button type="reset" onClick="javascript:history.back(-1);">Cancel </button>
										</td>
									</tr>
								</table>
							</form:form>
						</td>
					</tr>
				</table>
		    </div>
		</div>    
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
</body>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>