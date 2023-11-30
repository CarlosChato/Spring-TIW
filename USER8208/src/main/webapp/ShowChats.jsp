<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" %>
<%@ page import="javax.servlet.http.HttpSession,beans.Chat,java.util.List,beans.User,java.util.Base64" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Mis chats</title>
		<link rel="stylesheet" href="../styles/app.css">
	</head>
	<body style="position:relative; width:100%; height:100%; text-align:center; align-content:center;" class="body_class">
		<%
		sesion = request.getSession(false);
		%>
		<%@include file="nav_bar_to_include.jsp"%>
		<%
		int idevent;
		@SuppressWarnings("unchecked")
		List<Chat> chats = (List<Chat>) request.getAttribute("chatList");
		for (int i=0; i < chats.size(); i++) {
		%>
		<div class="evento" 
			 onclick="location.href='/ticketsell8208/chat?id=<%=chats.get(i).getId()%>'">
			<span>
				<%=chats.get(i).getUser1().getUsername()%> y <%=chats.get(i).getUser2().getUsername()%>
			</span>
		</div>
		<% } %>
		<!-- div class="evento">	
			<form method="POST">
				<input name="msg" placeholder="Escribe un mensaje..." style="width:60%">
				<input type='submit' value='Enviar'>
			</form>
		</div--->
	</body>
	<%@include file="bottom_include.jsp" %>
</html>