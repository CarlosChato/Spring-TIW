<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<%@ page import="javax.servlet.http.HttpSession,beans.Ticket,java.util.ArrayList,beans.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tickets a la venta para el evento</title>
</head>
<body>
<body class="body_class">
	<%@include file="nav_bar_to_include.jsp"%>
	<br>
	<br>
	<%
	sesion = request.getSession(false);
	Integer idticket;
	@SuppressWarnings("unchecked")
	ArrayList<Ticket> tickets = (ArrayList<Ticket>) request.getAttribute("ticketList");
	for (Ticket t: tickets) {
		idticket = t.getId();
	%>
	<div class="evento" style="width:30%;left:35%">
		<div style="text-align:center">
			<span>Precio:</span>
			<span><%=t.getPrice() + " EUR"%></span>
			<br>
			<br>
			<span>Tipo de entrada:</span>
			<span><%=t.getType()%></span>
			<br>
			<br>
			<span>Código:</span>
			<span><%=t.getCode()%></span>
			<br>
			<br>
			<span>Vendida por:</span>
			<span><%=t.getUsers().getName_user() + " (id user = " + t.getUsers().getId() + ")"%></span>
			<br>
			<br>
			<span>Evento:</span>
			<span><%=t.getEvent().getName_event()%></span>
			<br>
			<br>
			<button class='button-nav' onclick="alert('Funcionalidad no requerida para la práctica, está de adorno.');">Comprar entrada</button>
			<button class='button-nav' onclick="location.href='/ticketsell8208/chatCreate?User_id=<%= t.getUsers().getId() %>'">Chat right now</button>
		</div>
	</div>
	<%  } %>
	<% if (request.getAttribute("noTicketsMsg") != null) { %>
	<div>
		<%= request.getAttribute("noTicketsMsg")%>
	</div>
	<% } %>
</body>
<%@include file="bottom_include.jsp" %>
</html>