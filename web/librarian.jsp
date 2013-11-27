<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/libraryTag.tld" prefix="libraryTag" %>
<jsp:useBean id="orders" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="cards" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="readingRoom" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="orderId" scope="request" class="java.lang.String" />
<jsp:useBean id="readingRoomMessage" scope="request" class="java.lang.String" />
<jsp:useBean id="cardMessage" scope="request" class="java.lang.String" />
<jsp:useBean id="returnMessage" scope="request" class="java.lang.String" />
<html>
<head><title>Login</title>
	<link rel="stylesheet" type="text/css" href="librarian.css">
</head>


<body>
	<div class="wrapper_body">
     		<div class="cbm_wrap ">
	<h1>Welcome , <%= session.getAttribute("login") %> </h1>
	<br>
	<br>
	<table >
		<tr>
			<td class="space">
				
				<h3>To view all orders <br>
				press "Show orders"</h3>
				<form method="POST" action=/library/ShowOrders > 
					<input type="submit" value="Show orders">
				</form>
				<libraryTag:ShowAllOrders 
				orders="<%= orders %>"/> 
			</td>
			<td class="space">
			
			</td>
			<td class="space">
			<h2>To give book to the reading room please<br>
			enter Order Id and press "Give to Reading room"</h2>
				
				<form class = "form" method="POST" action=/library/GiveToReadingRoom >
				Order id
					<input type="text" name="id"/>
					<br><br>
					<input type="submit" value="Give to Reading room">
				</form>
				<%= readingRoomMessage %> 
			</td> 
		</tr>
		<tr>
			<td>
			<h3>To view all in Reading room<br>
			press "Show Reading room"</h3>
				<form method="POST" action=/library/ShowReadingRoom > 
					<input type="submit" value="Show Reading room">
				</form>
				<libraryTag:ShowAllInReadingRoom
				readingRoom="<%= readingRoom %>"/> 
			</td>
			<td class="space">
			
			</td>
			<td >
				<h2>To give book to the Card please<br>
				enter Order Id and press "Give to Card"</h2>
					<form class="form" method="POST" action=/library/GiveToCard >
						Order id
						<input type="text" name="id"/>
						<br><br>
						<input type="submit" value="Give to Card">
					</form>
				<%= cardMessage %> 
			</td>
		</tr> 
		<tr>
			<td>
			<h3>To view all books on cards <br>
			press "Show Book on cards"</h3>
				<form method="POST" action=/library/ShowCards > 
					<input type="submit" value="Show Book on cards">
				</form>
				<libraryTag:ShowAllCards
				cards="<%= cards %>"/> 
			
			</td>
			<td class="space">
				
			</td>
			<td>
				<h2>To put book back to the library please<br>
					enter Book Id and press "Return to Library"</h2>
						<form class="form" method="POST" action=/library/ReturnToLibrary >
							Book id
							<input type="text" name="id"/>
							<br><br>
							<input type="submit" value="Return to Library">
						</form>
						<br><br><br><br>
					<%= returnMessage %> 
			</td>
		</tr>
		<tr>
		
		</tr>
	  </table>	
	  </br>
	  </br>
	  <a href="index.jsp"> Sign out</a>	
	</div>
     		</div>
</body>
</html>