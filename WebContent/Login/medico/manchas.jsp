<%@ page import="java.util.List" import="Main.Login"
	import="java.sql.Connection" import="java.sql.DriverManager"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.sql.*" import="utils.Javatosql" import="medico.Medico"
	import="java.util.Arrays" import="java.sql.Date"%>
<%
	Medico m = (Medico) session.getAttribute("medico");
	//Medico m = new Medico(830013912);
%>
<!DOCTYPE html>
<html class="no-js">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

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
<title>Manipular manchas de disponibilidade horaria</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="dist/njtimepicker.css">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap"
	rel="stylesheet">
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}

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

.container1 { //
	padding: 1em; //
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

<style>
#customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#customers td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

#customers tr:nth-child(even) {
	background-color: #f2f2f2;
}

#customers tr:hover {
	background-color: #ddd;
}

#customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: white;
}
</style>

</head>

<body>

	<div class="wrapper">
		<form id="formEsp" method="POST">
			<label for="format_24">Data</label> <input type="text" name="date">
			<label for="format_24">Hora</label> <input type="text" name="time">
			<label for="format_24">Especialidade</label> <input type="text"
				name="especialidade" value="<%=m.getEspecialidade()%>" disabled>

			<button class="login100-form-btn" name="AddMancha"
				style="margin-bottom: 15px; height: 30px">Adicionar Mancha
				Horaria</button>

			<%
				String ajuda = (String) request.getParameter("AddMancha");
				if (ajuda != null) {
					String diaaa = (String) request.getParameter("date");
					String diaa[] = diaaa.split("-");
					Date dia = Javatosql.dateFormater(Integer.parseInt(diaa[0]), Integer.parseInt(diaa[1]),
							Integer.parseInt(diaa[2]));

					String hora = (String) request.getParameter("time");
					Time horaInit = Javatosql.timeFormater(hora.split(":")[0], hora.split(":")[1]);

					m.criarMancha(dia, horaInit);
				}
			%>
			<table id="customers">
				<tr>
					<th>ID</th>
					<th>Dia</th>
					<th>Hora de Inicio</th>
					<th>Hora de Fim</th>
				</tr>
				<tr>

				</tr>
				<%
					String[] a = m.listarManchasHorarias();
					System.out.println(Arrays.toString(a));
					System.out.println(a.length);
					if (a.length >= 3 && a != null) {
						for (int i = 0; i < a.length; ++i) {
				%>
				<tr>
					<td style="color: black"><%=a[i]%></td>

					<td style="color: black"><input type="text" name="date<%=i%>"
						value="<%=a[i + 1]%>">
						
						<button class="login100-form-btn" name="dateBtn<%=i%>"
							style="float: right; margin-bottom: 10px; height: 20px; width: 50px">OK</button>
					</td>

					<td style="color: black"><input type="text" name="hour<%=i%>"
						value="<%=a[i + 2]%>">
						<button class="login100-form-btn" name="hourBtn<%=i%>"
							style="float: right; margin-bottom: 10px; height: 20px; width: 50px">OK</button>
					</td>

					<td style="color: black"><%=a[i + 3]%></td>
				</tr>

				<%
							String dateBtn = (String) request.getParameter("dateBtn" + i);
							String hourBtn = (String) request.getParameter("hourBtn" + i);

							if (dateBtn != null) {
								
								session.setAttribute("type", "data");
								
								// Mandar para o update
								response.sendRedirect("AtualizarWait.jsp?data=" + (String)request.getParameter("date" + i) +
																	"&hora=" + (String)request.getParameter("hour" + i));
								
								// Dar update conforme a data
								/*String data = (String) request.getParameter("date" + i);

								String[] datee = data.split("-");

								String horaInicio = (String) request.getParameter("hour" + i);
								String[] h1 = horaInicio.split(":");

								m.atualizarMancha(Javatosql.dateFormater(Integer.parseInt(datee[0]), Integer.parseInt(datee[1]),
										Integer.parseInt(datee[2])), Javatosql.timeFormater(h1[0], h1[1]));
								*/
							}

							if (hourBtn != null) {
								
								session.setAttribute("type", "hora");
								
								response.sendRedirect("AtualizarWait.jsp?data=" + (String)request.getParameter("date" + i) +
										"&hora=" + (String)request.getParameter("hour" + i));
								
								// Dar update conforme a hora
								/*String hora = (String) request.getParameter("hour" + i);
								String[] h = hora.split(":");

								String data = (String) request.getParameter("date" + i);
								String[] datee = data.split("-");
								Date d = Javatosql.dateFormater(Integer.parseInt(datee[0]), Integer.parseInt(datee[1]),
										Integer.parseInt(datee[2]));

								m.atualizarMancha(Javatosql.timeFormater(h[0], h[1]), d);
								*/

							}

							i += 3;
						}
					}
				%>
			</table>

		</form>
	</div>
	<div class="wrapper" id="myButton">
		<form class="login100-form validate-form" action="Medico.jsp">
			<button class="login100-form-btn" name="button">Voltar</button>
		</form>
	</div>
</body>

</html>