<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Entra con tu cuenta</title>
		<link rel="stylesheet" href="styles/app.css">
	</head>
	<body>
		<div class='container-box'>
			<div class='container-form-login'>
				<h1>Entra con tu cuenta</h1>
				<div class='div-login'>
					<form action="" method="POST">
						<input name="username" placeholder="Nick" type="text" class="input" required autofocus style='margin-top:80px'>
						<div class='container-pass'>
							<input name="password" placeholder="Contraseña" type="password" class="input" required id='password' style='margin-top:80px'>
							<img src='assets/eye-slash-fill.svg' onclick='showPassword()' id='icon_pass' class='icon-password'>
						</div>
						<input type='submit' class='button-submit' value='Acceder'>
					</form>
				
				</div>
				<div style='margin:auto;width:auto;height:25%'>
					<a class="button-guest " href="/ticketsell8208/Home" style='margin:80px auto auto 25%'>Entra como invitado</a>
				</div>
				<% if (request.getAttribute("errorMsg") != null) { %>
				<span style="color: red; align: center"><%= request.getAttribute("errorMsg") %></span>
				<br>
				<%} %>
				<div style='margin-top:40px;display:flex'>
						<p class='question-form'>¿Aún no tienes cuenta?</p>
						<img alt="Scary emoji" src="assets/scared-emoji.png" style='width:30px;height:30px;margin:auto 2% auto 5px'>
						<button class='button-change-form'  onclick="location.href='register'">Regístrate</button>
				</div>
				<div class="title_anim_div">
					<div class="wordCarousel">
			            <span class="title_text_anim">Disfruta:</span>
			            <div>
			                <!-- Flip 4 to match the number of words -->
			                <ul class="flip4">
			                    <li class='special-li'>Eventos</li>
			                    <li class='special-li'>Tickets</li>
			                    <li class='special-li'>Artistas</li>
			                    <li class='special-li'>Guay</li>
			                </ul>
			            </div>
			        </div>
			      </div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		function showPassword(){
			var x = document.getElementById('password');
			var y = document.getElementById('icon_pass');
			if(x.type == 'text'){
				x.type = 'password';
				y.src='assets/eye-slash-fill.svg';
			}else{
				x.type = 'text';
				y.src='assets/eye-fill.svg';
			}
		}
	</script>
</html>