<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="note" scope="request" class="java.lang.String" />
<html>
	<head>
		<title>
			Login
		</title>
		<link rel="stylesheet" type="text/css" href="index.css">
		<script src="passEnc.js"></script>
		
	</head>
	<body>
		<div class="wrapper_body">
     		<div class="cbm_wrap ">
		<h1>Please enter your login and password </h1>
		</br>
				
		 <b> <span style="padding:0px 12px;">Login</span> </b> <input type="text" id = "login" name="login" size="20"> <br><br>
			<b> Password </b> <input type="password" id = "password" name="pass" size="20">
			</br>
			</br>
		<button onclick="post_to_url('/library/authentification','POST',document.getElementById('login').value, document.getElementById('password').value)">Try it</button> 

		<hr>
		<table>
			<tr>
	 
				<td><font color="red"> <%= note %> </font></td>
		
			</tr>

		</table>
		</div>
	</div>
	</body>
</html>