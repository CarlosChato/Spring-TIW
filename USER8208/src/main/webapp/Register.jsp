<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Crea tu nueva cuenta</title>
		<link rel="stylesheet" href="styles/app.css">
	</head>
	<body>
		<div class='container-box'>
			<div class='container-form-login' style='height:100%'>
				<h1>Registro</h1>
				<div class='div-login'>
					<form action="register" method="POST">
						<input name="name" placeholder="Nombre" type="text" class="input" maxlength="150" required style='margin-top:20px'>
						<input name="surname" placeholder="Apellidos" type="text" class="input" maxlength="150" required style='margin-top:20px'>
						<input name="email" placeholder="Email" type="text" class="input" maxlength="150" required style='margin-top:20px'>
						<input name="phone" placeholder="Teléfono" type="number" class="input" maxlength="9" required style='margin-top:20px'>
						<input name="username" placeholder="Nick" type="text" class="input" maxlength="150" required style='margin-top:20px'>
						<div class='container-pass'>
							<input name="password" placeholder="Contraseña" type="password" class="input" maxlength="150" required id='password' style='margin-top:20px'>
							<img src='assets/eye-slash-fill.svg' onclick='showPassword()' id='icon_pass' class='icon-password'>
						</div>
						<div class='container-pass'>
							<input placeholder="Repite la contraseña" type="password" class="input" maxlength="150" required id='repeat_password' style='margin-top:20px' onchange='checkPassword()'>
							<img src='assets/eye-slash-fill.svg' onclick='showPassword2()' id='icon_pass2' class='icon-password'>
							<br>
							<span id='message'></span>
						</div>
						<input type='submit' class='button-submit' value='Crear'>
					</form>
				
				</div>
				<div style='margin:auto;width:auto;height:25%'>
					<a class="button-guest " href="/ticketsell8208/Home" style='margin:80px auto auto 25%'>Entra como invitado</a>
				</div>
				<% if (request.getAttribute("errorMsg") != null) { %>
				<span style="color: red; align: center"><%= request.getAttribute("errorMsg") %></span>
				<br>
				<%} %>
				<div style='margin-top:1px;display:flex'>
						<p class='question-form'>¿Ya tienes cuenta?</p>
						<img alt="Happy Emoji" src="assets/happy-emoji.png" style='width:30px;height:30px;margin:auto 2% auto 5px'>
						<button class='button-change-form' onclick="location.href='login'">Accede</button>
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
		
		function showPassword2(){
			var x = document.getElementById('repeat_password');
			var y = document.getElementById('icon_pass2');
			if(x.type == 'text'){
				x.type = 'password';
				y.src='assets/eye-slash-fill.svg';
			}else{
				x.type = 'text';
				y.src='assets/eye-fill.svg';
			}
		}
		
		function checkPassword(){
			var x = document.getElementById('password');
			var y = document.getElementById('repeat_password');
			var m = document.getElementById('message');
			
			if(x.value != y.value){
				m.innerHTML ='Passwords not matching, check it!';
				m.style.color = 'red';
			}else{
				m.innerHTML ='';
			}
		}
	</script>
</html>