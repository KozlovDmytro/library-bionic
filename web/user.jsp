<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/libraryTag.tld" prefix="libraryTag" %>
<jsp:useBean id="orders" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="books" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="orderBookId" scope="request" class="java.lang.String" />
<jsp:useBean id="orderMessage" scope="request" class="java.lang.String" />
<html>
	<head>
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="user.css">
	</head>
	
	<body>
	<div class="wrapper_body">
     		<div class="cbm_wrap ">
	<h1>Welcome, <%= session.getAttribute("login") %>! </h1>
	</br>
	</br>
	<table>
		<tr>
			<td>
				
				<h3>Please enter book's name in field and press "Search"</h3>
				If you want to view all catalog enter "all" in Book Name
				field</br>
				</br>
				<form method="POST" action="/library/BookSearch" >
					<b> Book Name </b> 
					<input type="text" name="bookName" size="20"> <br><br>
					<input type="submit" value="Search">
				</form>
			
		 	<libraryTag:getBooks books="<%= books%>"/>
		 	</td>	
		 	<td class="space">
		 	</td>
		 	<td>
				<h2>Please enter book's Id in field and press "Make Order"</h2> 	
		 		</br>
		 		</br>
		 		<form method="POST" action="/library/MakeOrder" >
					<b> Book Id </b> 
					<input type="text" name="bookId" size="20" > <br><br>
					<input type="submit" value="Make Order">
				</form>
				
			<%=orderMessage%>
			</br>
			
			<h2>To view current orders <br>
				press "Show orders"</h2>
				<form method="POST" action=/library/ShowUserOrders > 
					<input type="submit" value="Show orders">
				</form>
				<libraryTag:ShowAllOrders 
				orders="<%= orders %>"/> 
		 	</td> 
		</tr>
	
	</table> 	
	</br>
	</br>
	<a href="index.jsp">Sign Out</a>
	</div>
	</div>
	</body>
	
</html>