<%@ page import="java.util.List" import="Main.Login"
	import="java.sql.Connection" import="java.sql.DriverManager"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.sql.*"%>
<%
	Login l = new Login();
%>
<!DOCTYPE html>
<html class="no-js">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
<title>Marcar Consulta</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="dist/njtimepicker.css">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap"
	rel="stylesheet">
<style>
body, html {
	height: 100%;
	width: 100%;
	overflow: hidden;
}

body {
	font-family: 'Roboto', sans-serif;
	background: #9D50BB;
	background: -webkit-linear-gradient(to right, #6E48AA, #9D50BB)
		!important;
	background: linear-gradient(to right, #6E48AA, #9D50BB) !important;
	display: flex;
	align-items: center;
	justify-content: center;
}

.wrapper {
	text-align: center;
	color: #ffffff;
	margin-bottom: 10px;
}

.wrapper label {
	margin-bottom: 1em;
	display: inline-block;
}

.container {
	padding: 1em;
	text-align: center;
	border: 1px solid #fff;
	border-radius: 6px;
}
</style>
</head>

<body>
	<form>
		<div class="wrapper">
			
			<label for="format_24">Data da Consulta</label> 
			<select class="container" id="asdasd">
				<option>asdasd</option>
				<option>asdasd</option>
			</select>
			
			<input type='hidden' name='hora' value='Here is the value' id="date" />

			<label for="format_24">Hora da Consulta</label> 
			<select class="container" id="horas" onchange="update()">
				<%
				
				// Ir buscar horas a
				String[] horas = {"15:00", "18:00", "20:00"};
				
				for (int i = 0; i < horas.length; ++i) {
				
				%>
				<option><%=horas[i]%></option>
				<%
				}
				%>
			</select>
			<input type='hidden' name='hora' value='Here is the value' id="time" />

			<script>
			
			function update () {
				let valor = document.getElementById("horas").value;
				document.getElementById("time").setAttribute("value", valor);
			}
			
			</script>
		</div>
		<script src="dist/njtimepicker.js"></script>

		<div class="container-login100-form-btn">
			<button class="login100-form-btn" name="login">Entrar</button>
		</div>
	</form>

	<%
		String entrarBtn = request.getParameter("login");
		if (entrarBtn != null) {
			String infoLog = request.getParameter("hora");
		}
	%>
</body>

<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>



</html>