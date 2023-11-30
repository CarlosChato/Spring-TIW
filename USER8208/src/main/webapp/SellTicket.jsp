<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
    <%@ page import="javax.servlet.http.HttpSession,beans.Event,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vende tu entrada</title>
</head>
	<%@include file="nav_bar_to_include.jsp"%>
<body class="body_class">
	<div class="content" style="text-align:center">
		<form action="" method="POST">
			<input name="price" placeholder="Precio" type="number" class="input" required style='margin-top:80px'>
			<input name="type" placeholder="Tipo de entrada" type="text" class="input" required style='margin-top:80px'>
			<input name="code" placeholder="Código de la entrada" type="text" class="input" required style='margin-top:80px'>
			<br>
			<input type='submit' class='button-submit' value='Subir'  style='margin-top:80px'>
		</form>
	</div>
</body>
<%@include file="bottom_include.jsp" %>
</html>