<%@page import="admin.Admin"%>
<%@ page import="java.util.List" import="Main.Login"
	import="java.sql.Connection" import="java.sql.DriverManager"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.sql.*"%>
<%
	Admin admin = new Admin();
	session.setAttribute("admin", admin);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>ISEL Clinic</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="../images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="../css/util.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<!--===============================================================================================-->
</head>
<body style="background-image: url('../images/back.jpg');">

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-85 p-b-20">
				<form class="login100-form validate-form">
					<span class="login100-form-title p-b-70"> Bem vindo
						Administrador</span> <span class="login100-form-avatar"> <img
						src="../images/admin_icon.png" alt="AVATAR">
					</span>
				</form>
				<form id="formEsp" method="POST" action="criar_Utente/Admin_Utente.jsp">
					<button class="login100-form-btn" name="gerirUtente">Gerir Utente</button>
				</form>
				<br>
				<form id="formEsp" method="POST" action="ListarUtentesAutoComplete.jsp">
					<button class="login100-form-btn" name="listarUtentesAutoComplete">Procurar fichas de utentes</button>
				</form>
				<br>
				<form id="formEsp" method="POST">
					<button class="login100-form-btn" name="OKesp">Gerir Medico</button>
				</form>
				<br>
				<form id="formEsp" method="POST">
					<button class="login100-form-btn" name="OKesp">Exportar/Importar fichas</button>
				</form>
				<br>
				<form id="formEsp" method="POST" action="ListarEspecialidades.jsp">
					<button class="login100-form-btn" name="listMedicosEsp">Listar total de médicos</button>
				</form>
				<br>
				<form id="formEsp" method="POST" action="ListarEspecialidadeStatus.jsp">
					<button class="login100-form-btn" name="listEspStats">Listar estados das consultas por Especialidade</button>
				</form>
				<br>
				<form id="formEsp" method="POST" action="ListarUtentesFaltas.jsp">
					<button class="login100-form-btn" name="listFaltas">Listar faltas de utentes</button>
				</form>
				<br>
				<form id="formPerdidas" method="POST" action="ListarHorasPerdidas.jsp">
					<button class="login100-form-btn" name="horasPerd">Listar horas perdidas</button>
				</form>
			</div>
		</div>
	</div>



	<div id="dropDownSelect1"></div>

	<!--===============================================================================================-->
	<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="../vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="../vendor/bootstrap/js/popper.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="../vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="../vendor/daterangepicker/moment.min.js"></script>
	<script src="../vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="../vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="../js/main.js"></script>

</body>
</html>
