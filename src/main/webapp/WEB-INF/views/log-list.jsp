<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
<head>
	<title>My Admin Center</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">
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

<div class="container" align="center">
	<div align="left">
	
	</div>
	<table id="table_id" class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>Log ID</th>
	            <th>Administrator ID</th>
				<th>Log Type</th>
				<th>User ID</th>
				<th>Event ID</th>
				<th>Log Date</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>
</body>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		var idArray = [];
		var table = $("#table_id").DataTable({
			"responsive":true,
			"processing":true,
			/* "serverSide":true, */
			"ajax":{
				"url":"./admin/getLogList",
				"dataSrc": function(data) {
					if (data == null) {
						return "";
					} else {
						return data;
					}
				},
				"error": function(XMLHttpRequest, textStatus, errorThrown) {
					console.log("p1: " + textStatus);
					alert("Network shaking, please wait for a moment!");
				}
			},
			"columns":[
			           {"data":"logid"},
			           {"data":"adminId"},
			           {"data":"logType"},
			           {"data":"uid"},
			           {"data":"eid"},
			           {"data":"logDate"}
			           ],
			"ellipsis": 'popover',
			"order":[[0,'desc']],
			"displayLength":50
		});
		
	});
</script>
</html>