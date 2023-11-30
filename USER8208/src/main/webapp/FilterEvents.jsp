<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" %>
<%@ page import="javax.servlet.http.HttpSession,beans.Event,java.util.List,beans.User,java.util.Base64" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Eventos</title>
		<link rel="stylesheet" href="../styles/app.css">
	</head>
	<body style="position:relative; width:100%; height:100%; text-align:center; align-content:center;" class="body_class">
		<%
		sesion = request.getSession(false);
		%>
		<%@include file="nav_bar_to_include.jsp"%>
		<br>
		<br>
		<input type="text" name="filtrar_nombre" id="filtrar_nombre" placeholder="Filtar Nombre...">
		<input type="date" name="filtrar_fecha" id="filtrar_fecha" placeholder="Filtrar Fecha...">
		<input type="text" name="filtrar_ciudad" id="filtrar_ciudad" placeholder="Filtrar Ciudad...">
		<input type="text" name="filtrar_sala" id="filtrar_sala" placeholder="Filtrar Sala...">
		<input type="text" name="filtrar_categoria" id="filtrar_categoria" placeholder="Filtrar Categoría">
		<%
			int idevent;
			@SuppressWarnings("unchecked")
			List<Event> events = (List<Event>) request.getAttribute("eventList");
			for (int i=0; i<events.size(); i++) {
				idevent = events.get(i).getId();
		%>
		<div class="evento">
			<div class="evento-fila1">	
				<div class="evento-info">
					<span>Nombre:</span>
					<span id="name_<%=idevent%>"><%=events.get(i).getName_event()%></span>
					<br>
					<br>
					<span>Día:</span>
					<span id="date_<%=idevent%>"><%=events.get(i).getFecha()%></span>
					<br>
					<br>
					<span>Ciudad:</span>
					<span id="city_<%=idevent%>"><%=events.get(i).getCity()%></span>
					<br>
					<br>
					<span>Sala:</span>
					<span id="room_<%=idevent%>"><%=events.get(i).getSala()%></span>
					<br>
					<br>
					<span>Categoría:</span>
					<span id="category_<%=idevent%>"><%=events.get(i).getCategory()%></span>
					<br>
					<br>
					<span>Número de entradas a la venta:</span>
					<span><%=events.get(i).getNumberOfTickets()%></span>
					<br>	
					<br>
				</div>
				<div class="space"></div>
				<div class="evento-imagen">
					<%			
					if (events.get(i).getPhoto() != null) {
						String encodedString = events.get(i).getPhoto();
		            %>
		            
					<img id= "event-img" alt="img" src="data:image/jpeg;base64,<%=encodedString%>"/>
					<%
					}		
					else {
					%>
					<img id= "event-img" alt="img" src="<%=events.get(i).getPhoto_Path()%>"/>
					<%
					}	
					%>
				</div>
			</div>
				<div class="evento-fila2">
					<button class='button-nav' onclick="location.href='/ticketsell8208/viewTickets?Event_id=<%=idevent%>'">Ver entradas</button>	
					<% if (sesion != null) { %>
					<button class='button-nav' onclick="location.href='/ticketsell8208/sellTickets?Event_id=<%=idevent%>'">Vender entradas</button>
					<% } %>
				</div>
		</div>
		<% } %>
	</body>
	<%@include file="bottom_include.jsp" %>
	<script>
		function showOrHide(e, index) {
			document.querySelectorAll(".evento").forEach( event => {
				event.children[0].children[0].children[index].textContent.toLowerCase().includes(e.target.value.toLowerCase())
				? event.style.display = "block"
				: event.style.display = "none"
			} )
		}
		document.addEventListener("keyup", e => {
			if (e.target.id === "filtrar_nombre")    { showOrHide(e, 1); }
			if (e.target.id === "filtrar_fecha")     { showOrHide(e, 5); }
			if (e.target.id === "filtrar_ciudad")    { showOrHide(e, 9); }
			if (e.target.id === "filtrar_sala")      { showOrHide(e, 13); }
			if (e.target.id === "filtrar_categoria") { showOrHide(e, 17); }
		} );
	</script>
</html>