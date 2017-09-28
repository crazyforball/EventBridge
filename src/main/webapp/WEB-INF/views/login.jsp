<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<p>Login Module</p>
		
			<label>Account: </label>
			<input id="username" name="j_username" type="text"/><br/>
			<label>Password: </label>
			<input id="password" name="j_password" type="password"/><br/>
			<input type="button" value="Sign In" onclick="doLogin()"/>
			<div id="results"></div>
	</body>
	<script src="https://cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
	<script type="text/javascript">
		function doLogin(){
			var url = "/ctosii_middle/j_spring_security_check";
			var username = $("input[name='j_username']").val();
			var password = $("input[name='j_password']").val();
			$.ajax({
				url:url,
				type:"POST",
				data:"j_username="+username+"j_pasword="+password,
				success:function(data){
					$("#results").text(data);
				}
			});
		}
	</script>
</html>
