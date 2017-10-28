<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
<head>
	<title>My Admin Center</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">
	<style type="text/css">
		td.details-control {
		    background: url('../resources/details_open.png') no-repeat center center;
		    cursor: pointer;
		}
		tr.shown td.details-control {
		    background: url('../resources/details_close.png') no-repeat center center;
		}
	</style>
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
                	<li><a href="#">Operation Log</a></li>
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
                </ul>
            </li>
        </ul>
    </div>
    </div>
</nav>

<div class="container" align="center">
	<div align="left">
		<input type="button" id="deny-btn" value="Deny Event(s)" class="btn btn-warning" />
		<input type="button" id="pass-btn" value="Approve Event(s)" class="btn btn-primary" />
	</div>
	</br>
	<table id="table_id" class="table table-bordered table-hover table-striped display">
		<thead>
			<tr>
				<th></th>
				<th>Event ID</th>
	            <th>Event Name</th>
				<th>Start Date</th>
				<th>Creator</th>
				<th>Status</th>
				
			</tr>
		</thead>
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
				"url":"${pageContext.request.contextPath}/admin/getEventList",
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
			           {
			        	   "className":'details-control',
			        	   "orderable":false,
			        	   "data":null,
			        	   "defaultContent":''
			           },
			           {"data":"eid"},
			           {"data":"eventName"},
			           {"data":"startDate"},
			           {"data":"creator.username"},
			           {"data":"status"}
			           ],
			"order":[[3,'desc']],
			"ellipsis": 'popover',
			"displayLength":50
		});
		
		// Add event listener for opening and closing details
	    $('#table_id tbody').on('click', 'td.details-control', function () {
	        var tr = $(this).closest('tr');
	        var row = table.row( tr );
	 		console.log(row.data());
	 		
	        if ( row.child.isShown() ) {
	            // This row is already open - close it
	            row.child.hide();
	            tr.removeClass('shown');
	        }
	        else {
	            // Open this row
	            row.child( format(row.data()) ).show();
	            tr.addClass('shown');
	        }
	    } );
		
		$('#table_id tbody').on( 'click', 'tr', function(){
			var eid = table.row( this ).data().eid;
			console.log(eid);
			
			var index = $.inArray(eid, idArray);
			if (index == -1) {
				idArray.push(eid);
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
					url:"${pageContext.request.contextPath}/admin/denyEvent",
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
					url:"${pageContext.request.contextPath}/admin/approveEvent",
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
	
	function format ( d ) {
	    // `d` is the original data object for the row
	    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
	        '<tr>'+
	            '<td>Location:</td>'+
	            '<td>'+d.location+'</td>'+
	        '</tr>'+
	        '<tr>'+
	            '<td>End Date:</td>'+
	            '<td>'+d.endDate+'</td>'+
	        '</tr>'+
	        '<tr>'+
	            '<td>Description:</td>'+
	            '<td>'+d.desc+'</td>'+
	        '</tr>'+
	        '<tr>'+
            	'<td>Capacity:</td>'+
            	'<td>'+d.capacity+'</td>'+
        	'</tr>'+
        	'<tr>'+
        		'<td>Fee:</td>'+
        		'<td>A$ '+d.fee+'</td>'+
    		'</tr>'+
    		'<tr>'+
    			'<td>Creator ID:</td>'+
    			'<td>'+d.creator.uid+'</td>'+
			'</tr>'+
	    '</table>';
	}
</script>
</html>