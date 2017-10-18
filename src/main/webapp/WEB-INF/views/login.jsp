<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<p>Login Module</p>
		
			<label>Username: </label>
			<input id="username" name="username" type="text"/><br/>
			<label>Password: </label>
			<input id="password" name="password" type="password"/><br/>
			<input type="button" value="Sign In" onclick="doLogin()"/>
			<div id="results"></div>
	</body>
	<script src="https://cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
	<script type="text/javascript">
		function doLogin(){
			var url = "/emsrepo/doLogin";
			var username = $("input[name='username']").val();
			var password = $("input[name='password']").val();
			console.log("username="+username+" password="+password);
			$.ajax({
				url:url,
				type:"POST",
				data:"username="+username+"&password="+password,
				success:function(data){
					console.log(data);
				}
			});
		}
	</script>
</html>
