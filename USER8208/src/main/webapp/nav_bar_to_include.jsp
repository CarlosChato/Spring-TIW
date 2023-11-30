<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<%@ page import="javax.servlet.http.HttpSession,beans.User" %>

<jsp:useBean id="user" class="beans.User" scope="request" />
<!DOCTYPE html>
<html>
	<%!HttpSession sesion;%>
	<%
	sesion = request.getSession(false);
		   if (sesion !=null) {
		   	user = (User) sesion.getAttribute("user");
		   	}
	%>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/ticketsell8208/styles/app.css">
	</head>
	<body>
		<header class='header'>
			<div class='logo'>
				<a href="/ticketsell8208/Home">
				<img alt="Our logo" src="/ticketsell8208/assets/ticket.png">
				</a>
			</div>
			<nav class='links-nav'>
				<ul >
					<li>
						<button class='button-nav' onclick="location.href='/ticketsell8208/show_events'">Eventos</button>
					</li>
					<%
					if (sesion != null) {
					%>
					<li>
						<button class='button-nav' onclick="location.href='/ticketsell8208/viewTickets?user=<%=((User)sesion.getAttribute("user")).getEmail()%>'">Mis entradas</button>
					</li>
					<li>
						<button onclick="location.href='/ticketsell8208/chat'" class='button-nav2'>Chat</button>
					</li>
					<% } %>
				</ul>
			</nav>
			<%--Set condition to show log in buttons or user's profile with log out button --%>
			<% 
			if (sesion == null) { %>
			<button onclick="location.href='/ticketsell8208/login'" class="button-nav2">Entrar</button>
			<button onclick="location.href='/ticketsell8208/register'" class='button-nav2'>Registrarse</button>
			<% } else { %>
			<h2 style="margin-right:14px"><%= user.getUsername()%></h2>
			<button onclick="location.href='/ticketsell8208/logout'" class='button-nav2'>Salir</button>
			<%} %>
		</header>
	</body>
</html>