<%@ page import="java.util.List" import="Main.Login"
	import="java.sql.Connection" import="java.sql.DriverManager"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.sql.*" import="medico.Medico" import="utils.Javatosql"%>

<%
	String n = (String) session.getAttribute("nif");
	int nif = Integer.parseInt(n);
	Medico medico = new Medico(nif);
	session.setAttribute("medico", medico);
%>

<!DOCTYPE html>


<html>
<head>
<title>ISEL Clinic</title>
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
				<%
					String gender = "";
					if (medico.getGender().equalsIgnoreCase("m")) {
						gender = "o";
					} else {
						gender = "a";
					}
				%>
				<%
					String name = medico.getNome();
				%>
				<span class="login100-form-title p-b-70"> Bem-vind<%=gender%>
					Doutor<%
					if (gender.equalsIgnoreCase("a")) {
						out.print(gender);
					}
				%> <%=name%> <br> <br>
					<div class="container-login100-form-btn">
						<img style="border-radius: 50%; border: solid 5px black;" height="200" width="200"
							src="data:image/png;base64,<%=medico.getMedicoImagem()%>"
							alt="Foto de Perfil" />
					</div>
				</span>
				<form id="formEsp" method="POST" action="listarUtentes.jsp">
					<div class="container-login100-form-btn">
						<button class="login100-form-btn" name="listarUtentes">
							Listar Utentes</button>
					</div>
				</form>
				<form id="formEsp" method="POST" action="manchas.jsp">
					<div class="container-login100-form-btn">
						<button class="login100-form-btn" name="listarConsulta">
							Manipular Manchas Horárias</button>
					</div>
				</form>

				<form id="formEsp" method="POST" action="MarcarFaltaPresenca.jsp">
					<button class="login100-form-btn" name="gerirUtente">Marcar
						Falta/Presença</button>
				</form>

			</div>
		</div>
	</div>

</body>


</html>