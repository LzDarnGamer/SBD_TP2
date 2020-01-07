<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="admin.Admin"%>
<%@ page import="java.util.List" import="Main.Login"
	import="java.sql.Connection" import="java.sql.DriverManager"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.sql.*" import="utils.Javatosql"
	import="java.util.stream.Stream"%>
<%
	Admin admin = (Admin) session.getAttribute("admin");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Administrador</title>

<script src="../dist/the-datepicker.js"></script>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="../images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="../css/util.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<link rel="stylesheet" type="text/css" href="../css/tables.css">
<!--===============================================================================================-->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!--===============================================================================================-->
<link rel="icon" type="image/png" href="../images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="../vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="../fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="../vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="../vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="../vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="../vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="../vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="../css/util.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<link href="../dist/the-datepicker.css" rel="stylesheet" />
<!--===============================================================================================-->
<title>Marcar Consulta</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../dist/njtimepicker.css">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../css/lightpick.css">
<style>
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
	padding: 8px;
	text-align: center;
	border: 1px solid #fff;
	border-radius: 6px;
	width: 100%
}

.container1 {
	padding: 10px;
	text-align: center;
	border: 1px solid #fff;
	border-radius: 6px;
	width: 100%
}

select {
	margin-top: 15px;
	margin-bottom: 15px;
}

label {
	margin-top: 15px
}
</style>
</head>




<body style="background-image: url('../images/back.jpg');">

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-85 p-b-20">
				<form class="login100-form validate-form">
					<span class="login100-form-title p-b-70">Pesquisar Utentes</span>
				</form>
				<div class="wrapper" id="menuP">

					<form id="formEsp">
						<label style="color: black;" for="format_24">Selecione o
							Utente</label> <input class="container" list="browsers" name="utentes" required="required">
						<datalist id="browsers">
							<%
								String[] utentes = admin.listarUtenteNome();

								for (String e : utentes) {
							%>
							<option value="<%=e%>">
								<%
									}
								%>
							
						</datalist>
						<br> <br>
						<button class="login100-form-btn" name="button">Procurar
							Utentes</button>
					</form>
				</div>
			</div>
			<%
				String btn = request.getParameter("button");
				if (btn != null) {
					String utenteSel = request.getParameter("utentes");
					String[] utentesTodos = admin.listarUtente();
			%>
			<table id="customers">
				<tr>
					<th>NIF</th>
					<th>Nome Completo</th>
					<th>Idade</th>
					<th>Sexo</th>
					<th>Morada</th>
					<th>Foto</th>
					<th>Exportar</th>
				</tr>
				<%
					for (String s : utentesTodos) {
							if (s.contains(utenteSel)) {
								String[] tempString = s.split("=");
				%>
				<tr>
					<td><%=tempString[0].trim()%></td>
					<td><%=tempString[1].trim()%></td>
					<td><%=tempString[2].trim()%></td>
					<td><%=tempString[3].trim()%></td>
					<td><%=tempString[4].trim()%></td>
					<td><img height="120" width="100"
						src="data:image/png;base64,<%=tempString[5]%>"
						alt="Foto de Perfil" /></td>
					<td>
						<form id="formEsp" method="POST"
							action="EliminarWait.jsp?reloadDel=<%=tempString[0]%>&type=exportar">
							<button class="login100-form-btn" name="editUtente">Exportar</button>
						</form>
					</td>
				</tr>
				<%
					}
						}
					}
				%>



			</table>
			<div class="wrapper" id="myButton">
				<form class="login100-form validate-form" action="Admin_index.jsp">
					<button class="login100-form-btn" name="button">Voltar</button>
				</form>
			</div>
		</div>
	</div>


	<script>
		var input = document.getElementById('calendarInit');
		var datepicker = new TheDatepicker.Datepicker(input);
		datepicker.render();

		var input = document.getElementById('calendarEnd');
		var datepicker = new TheDatepicker.Datepicker(input);
		datepicker.render();
	</script>
</body>
</html>
