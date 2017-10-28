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
                	<li><a class="dropdown-item" href="<fmt:message key="nav_dropdown_my_account.path"/>">My Account Home</a></li>
                </ul>
            </li>
            <li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    User Censorship
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                	<li><a href="${pageContext.request.contextPath}/admin/user-mgmt?adminName=${user.username}">User Management</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Event Verification
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/admin/event-mgmt?adminName=${user.username}">Event Management</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Operation Log
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/admin/log-list?adminName=${user.username}">Log List</a></li>
                </ul>
            </li>
             <li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    Category List
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                	<li><a href="${pageContext.request.contextPath}/category/home?adminName=${user.username}">Category Management</a></li>
                	<li><a href="${pageContext.request.contextPath}/category/add?adminName=${user.username}">Add New Category</a></li>
                </ul>
            </li>
        </ul>
    </div>
    </div>
</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<div align="center">
				<c:choose>
					<c:when test="${sessionScope.user != null}">
				        <table>
				            <tr>
				                <td align="center"><h2>Category List</h2></td>
				            </tr>
				            <c:forEach items="${categoryList}" var="category">
				            	<tr>
				            		<td>
						            	<table border="1">
						            		<tr>
								                <td>Category ID:</td>
								                <td>${category.cid}</td>
							            	</tr>
							            	<tr>
								                <td>Category Name:</td>
								                <td>${category.categoryName}</td>
							            	</tr>
							            	<tr>
								                <td>Description:</td>
								                <td>${category.cdesc}</td>
							            	</tr>
							            	<tr>
							            		<td></td>
							            		<td align="right"><a href="./category/delete?categoryId=${category.cid}&adminName=${user.username}"><button type="button">Delete</button></a></td>
							            	</tr>
							            </table>
							         </td>
						        </tr>
						        <tr>
						        	<td></td>
						        </tr>
						        <tr>
						        	<td></td>
						        </tr>
						        <tr>
						        	<td></td>
						        </tr>
				            </c:forEach>
				        </table>
				     </c:when>
				 </c:choose>    
		    </div>
		</div>    
    </div>
	<!-- /container -->

</body>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>