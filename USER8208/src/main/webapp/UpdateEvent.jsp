<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" %>
<%@ page import="javax.servlet.http.HttpSession,beans.Event,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event update</title>
</head>
<%@include file="nav_bar_to_include.jsp"%>
<body class="body_class">	
	
		<%
			Event event = (Event) request.getAttribute("event");
		%>	
			

	<div class="content" style="text-align:center">
			<p><%= "Nombre: " + event.getName_event()%></p>
			<p><%= "Ciudad: " + event.getCity()%></p>
			<p><%= "Sala: " + event.getSala()%></p>
			<p><%= "Fecha Evento: " + event.getFecha()%></p>
			<p><%= "Categoría: " + event.getCategory()%></p>
		<form action="" method="POST" enctype="multipart/form-data">
			<input name="name" placeholder="Nombre" type="text" class="input" value="<%= event.getName_event()%>">>
			<input name="city" placeholder="Ciudad" type="text" class="input" value="<%= event.getCity()%>">
			<input name="sala" placeholder="Sala" type="text" class="input" value="<%= event.getSala()%>">
			<input name="fecha" placeholder="Fecha" type="date" class="input" value="<%= event.getFecha()%>">
			<input name="category" placeholder="Categoría" type="text" class="input" value="<%= event.getCategory()%>">
			<input name="photo" placeholder="Foto" type="file" class="input"><br>
			<input type='submit' class='button-submit' value='Actualizar'>
		</form>
		
	</div>
	<br>
		<br>
		<br>
		<br>
</body>
<%@include file="bottom_include.jsp" %>
</html>