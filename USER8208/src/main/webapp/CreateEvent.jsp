<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event creation</title>
</head>
	<%@include file="nav_bar_to_include.jsp"%>
<body class="body_class">

	<div class="content" style="text-align:center">
		<form action="" method="POST" enctype="multipart/form-data"> 
			<input name="name" placeholder="Nombre" type="text" class="input" required>
			<input name="city" placeholder="Ciudad" type="text" class="input" required>
			<input name="sala" placeholder="Sala" type="text" class="input" required>
			<input name="fecha" placeholder="Fecha" type="date" class="input" required>
			<input name="category" placeholder="Categoría" type="text" class="input" required>
			<input name="photo" placeholder="Foto" type="file" class="input" required>
			<br>
			<input type='submit' class='button-submit' value='Crear'>
		</form>
	</div>
</body>
<%@include file="bottom_include.jsp" %>
</html>