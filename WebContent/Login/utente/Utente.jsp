<%@ page import="java.util.List" import="Main.Login"
	import="java.sql.Connection" import="java.sql.DriverManager"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.sql.*" import="utente.Utente" import="utils.Javatosql"%>

<%
	String n = (String) session.getAttribute("nif");
	int nif = Integer.parseInt(n);
	Utente pessoa = new Utente(nif);
	session.setAttribute("utente", pessoa);
%>

<!DOCTYPE html>


<html>
<head>
<title>Utente</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="../images/icons/favicon.ico" />
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
<link rel="stylesheet" type="text/css" href="../css/util.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<!--===============================================================================================-->
</head>

<body style="background-image: url('../images/back.jpg');">

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-85 p-b-20">
				<span class="login100-form-title p-b-70"> Bem-vind<%
					if (Javatosql.getSex(pessoa.getCon(), nif).equalsIgnoreCase("m")) {
				%>o<%
					} else {
				%>a<%
					}
				%> <%=Javatosql.nomePessoa(pessoa.getCon(), nif)%> <br> <br>
					<div class="container-login100-form-btn">
						<img style="border-radius: 50%; border: solid 5px black;" height="200" width="200"
							src="data:image/png;base64,<%=pessoa.getUtenteImagem()%>"
							alt="Foto de Perfil" />
					</div>
				</span>

				<form method="POST" action="MarcarConsulta.jsp">
					<div class="container-login100-form-btn">
						<button class="login100-form-btn" name="marcarConsulta">
							Marcar consulta</button>
					</div>
				</form>

				<form method="POST" action="ListarConsultas.jsp">
					<div class="container-login100-form-btn">
						<button class="login100-form-btn" name="listarConsulta">
							Manipular Consultas Marcadas</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>