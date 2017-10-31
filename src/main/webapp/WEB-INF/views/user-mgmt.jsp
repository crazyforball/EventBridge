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
                	<li><a class="dropdown-item" href="<fmt:message key="nav_dropdown_my_account.path"/>">My Account Home</a></li>
                </ul>
            </li>
            <li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    User Censorship
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                	<li><a href="./admin/user-mgmt?adminName=${user.username}">User Management</a></li>
                	<!-- <li><a href="#">Operation Log</a></li> -->
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
	<input type="button" id="deny-btn" value="Deny User(s)" class="btn btn-warning" />
	<input type="button" id="pass-btn" value="Pass User(s)" class="btn btn-primary" />
	</div>
	</br>
	<table id="table_id" class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>User ID</th>
	            <th>User Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Phone Number</th>
				<th>Email</th>
				<th>User Type</th>
				<th>Status</th>
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
				"url":"./admin/getUserList",
				"dataSrc": function(data) {
					if (data == null) {
						return "";
					} else {
						return data;
					}
				},
				"error": function(data, e) {
					console.log("p1: " + e);
					alert("Network issues!");
				}
			},
			"columns":[
			           {"data":"uid"},
			           {"data":"username"},
			           {"data":"firstName"},
			           {"data":"lastName"},
			           {"data":"phoneNum"},
			           {"data":"email"},
			           {"data":"utype"},
			           {"data":"status"}
			           ],
			"ellipsis": 'popover',
			"displayLength":50
		});
		
		$('#table_id tbody').on( 'click', 'tr', function(){
			var uid = table.row( this ).data().uid;
			console.log(uid);
			
			var index = $.inArray(uid, idArray);
			if (index == -1) {
				idArray.push(uid);
			} else {
				idArray.splice(index, 1);
			}
			console.log(idArray);
			
	        $(this).toggleClass('selected');
	    });
		
		$('#deny-btn').click(function(){
			if (idArray.length < 1) {
				alert("No selected row, please try again!");
			} else {
				var param = JSON.stringify(idArray);
				console.log(param);
				$.ajax({
					url:"./admin/denyUserStatus",
					data:param,
					contentType:'application/json',
					type:'POST',
					dataType:'json',
					success:function(data) {
						console.log(data);
						if (data.status == 'ok') {
							location.replace(location.href);
						} else {
							alert("Network issues!");
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown) {
						console.log(XMLHttpRequest);
						console.log(textStatus);
						console.log(errorThrown);
						alert(textStatus);
					}
				});
			}
		});
		
		$('#pass-btn').click(function(){
			if (idArray.length < 1) {
				alert("No selected row, please try again!");
			} else {
				var param = JSON.stringify(idArray);
				console.log(param);
				$.ajax({
					url:"./admin/passUserStatus",
					data:param,
					contentType:'application/json',
					type:'POST',
					dataType:'json',
					success:function(data) {
						console.log(data);
						if (data.status == 'ok') {
							location.replace(location.href);
						} else {
							alert("Network issues!");
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown) {
						console.log(XMLHttpRequest);
						console.log(textStatus);
						console.log(errorThrown);
						alert(textStatus);
					}
				});
			}
		});
	});
</script>
</html>