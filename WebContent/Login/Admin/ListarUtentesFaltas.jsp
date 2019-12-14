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
					<span class="login100-form-title p-b-70"> Listar Utentes com
						Faltas</span>
				</form>
				<div class="wrapper" id="menuP">

					<form id="formEsp">
						<label style="color: black;" for="format_24">Número de
							Faltas</label> <input class="container1" type="number" name="numFaltas">
						<br> <label style="color: black;" for="format_24">Data
							Início</label> <input class="container" type="text" id="calendarInit"
							name="calendarInit"> <br> <label
							style="color: black;" for="format_24">Data Fim</label> <input
							class="container" type="text" id="calendarEnd" name="calendarEnd">

						<br> <label style="color: black;" for="format_24">Selecione
							o tipo</label> <select class="container" name="status" id="status">
							<%
								String[] status = new String[]{"Falta", "Remarcacoes", "Cancelamento", "Presenca"};

								for (int i = 0; i < status.length; ++i) {
							%>
							<option>
								<%=status[i]%>
							</option>
							<%
								}
							%>
						</select> <br>
						<button class="login100-form-btn" name="button">Procurar
							Utentes</button>

					</form>
					<div class="wrapper" id="myButton">
						<form class="login100-form validate-form" action="Admin_index.jsp">
							<button class="login100-form-btn" name="button">Voltar</button>
						</form>
					</div>
				</div>

			</div>
			<%
				String btn = request.getParameter("button");
				if (btn != null) {
					String stats = request.getParameter("status");
					String n = request.getParameter("numFaltas");
					int numFaltas = 0;
					if (!n.isEmpty()) {
						numFaltas = Integer.parseInt(n);
					}
					String dateInit = request.getParameter("calendarInit");
					String dateFim = request.getParameter("calendarEnd");
					if (dateInit.length() > 5 && dateFim.length() > 5) {
						String[] dataI = dateInit.split("\\.");
						Date dI = Javatosql.dateFormater(Integer.parseInt(dataI[2].trim()),
								Integer.parseInt(dataI[1].trim()), Integer.parseInt(dataI[0].trim()));

						String[] dataF = dateFim.split("\\.");
						Date dF = Javatosql.dateFormater(Integer.parseInt(dataF[2].trim()),
								Integer.parseInt(dataF[1].trim()), Integer.parseInt(dataF[0].trim()));
						List<String> res = admin.listarUtentesStatus(dI, dF, numFaltas, stats);
			%>
			<table id="customers">
				<tr>
					<th>NIF</th>
					<th>Hora da Consulta</th>
					<th>Data da Consulta</th>
				</tr>
				<%
					for (String s : res) {
								String[] tempString = s.split(" ");
				%>
				<tr>
					<td><%=tempString[0].trim()%></td>
					<td><%=tempString[1].trim()%></td>
					<td><%=tempString[2].trim()%></td>
				</tr>
				<%
					}
						}
					}
				%>


			</table>
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



	<!-- 
	<script>
			document.getElementById("result").innerHTML = ;
	</script>
	-->

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
