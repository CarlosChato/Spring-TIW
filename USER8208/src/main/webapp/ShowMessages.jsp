<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" %>
<%@ page import="javax.servlet.http.HttpSession,beans.Chat,beans.Message,java.util.List,beans.User,java.util.Base64" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Mis chats</title>
		<link rel="stylesheet" href="../styles/app.css">
	</head>
	<body style="position:relative; width:100%; height:100%; text-align:center; align-content:center;" 
		  class="body_class">
		<%
		sesion = request.getSession(false);
		%>
		<%@include file="nav_bar_to_include.jsp"%>
		<%
		@SuppressWarnings("unchecked")
		List<Message> messages = (List<Message>) request.getAttribute("messageList");
		int thisUserId = ( (User) sesion.getAttribute("user") ).getId();
		
		if(messages.size() != 0){

		for (int i=0; i < messages.size(); i++) {
		%>
		<div class="evento" 
			 style="<%= thisUserId == messages.get(i).getSendBy().getId()
									? "text-align:right;background:#89dcdc" 
									: "text-align: left" %>">
			<span><%=messages.get(i).getMessage()%></span>
		</div>
		<% } }%>
		<div class="evento">	
			<form method="POST">
				<input name="msg" placeholder="Escribe un mensaje..." style="width:60%">
				<input name="id" value="<%= request.getParameter("id") %>" type="hidden">
				<input type='submit' value='Enviar'>
			</form>
		</div>
	</body>
	<%@include file="bottom_include.jsp" %>
</html>