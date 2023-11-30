<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="styles/app.css">
	</head>
	<%@include file="nav_bar_to_include.jsp"%>
	<body class="body_class">
		
	<div class="content">
		<% if (request.getAttribute("statusMsg") != null 
			  && request.getAttribute("statusMsg").equals("Entrada subida")) { %>
			<p> ¡La entrada se ha subido con éxito!</p>
			<p>En cuanto la validemos estará visible para todos. </p>
		<% } %>
	</div>	
		
	</body>
	<%@include file="bottom_include.jsp" %>
</html>