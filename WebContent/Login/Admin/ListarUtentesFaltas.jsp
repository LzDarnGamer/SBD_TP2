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
	padding: 1em;
	text-align: center;
	border: 1px solid #fff;
	border-radius: 6px;
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
					<span class="login100-form-title p-b-70"> Visualizar horas
						perdidas por médico</span>
				</form>
				<div class="wrapper" id="menuP">

					<form id="formEsp">

						<label style="color: black;" for="format_24">Data Início</label> <input
							class="container" type="text" id="calendarInit"
							name="calendarInit"> <label style="color: black;"
							for="format_24">Data Fim</label> <input class="container"
							type="text" id="calendarEnd" name="calendarEnd"> <br>

						<label style="color: black;" for="format_24">Selecione o
							Médico</label> <select class="container" name="medics" id="medics">
							<%
								Integer[] medicos = Javatosql.listarMedicos(admin.getCon());

								for (int i = 0; i < medicos.length; ++i) {
							%>
							<option>
								<%=medicos[i]%>
							</option>
							<%
								}
							%>
						</select> <br>
						<button class="login100-form-btn" name="button">Visualizar
							Tempo</button>

					</form>

				</div>

			</div>

		</div>
		<span class="login100-form-title p-b-70" id="result"></span>
	</div>

	<script src="../dist/the-datepicker.js"></script>

	<script>
		var input = document.getElementById('calendarInit');
		var datepicker = new TheDatepicker.Datepicker(input);
		datepicker.render();

		var input = document.getElementById('calendarEnd');
		var datepicker = new TheDatepicker.Datepicker(input);
		datepicker.render();
	</script>



	<%
		String btn = request.getParameter("button");
		if (btn != null) {
			int value = Integer.parseInt(request.getParameter("medics"));
			String dateInit = request.getParameter("calendarInit");
			String dateFim = request.getParameter("calendarEnd");
			if (dateInit.length() > 5 && dateFim.length() > 5) {
				String[] dataI = dateInit.split("\\.");
				Date dI = Javatosql.dateFormater(Integer.parseInt(dataI[2].trim()),
						Integer.parseInt(dataI[1].trim()), Integer.parseInt(dataI[0].trim()));

				String[] dataF = dateFim.split("\\.");
				Date dF = Javatosql.dateFormater(Integer.parseInt(dataF[2].trim()),
						Integer.parseInt(dataF[1].trim()), Integer.parseInt(dataF[0].trim()));

				String res = admin.listarMedicoTempoPerdido(dI, dF, value);
	%>
	<script>
			document.getElementById("result").innerHTML = "O médico com NIF:<%=value%>, perdeu <%=res%> horas com faltas/cancelamentos entre <%=dI%> e <%=dF%>";
	</script>
	<%
		}
		}
	%>

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
