<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="medico.Medico"%>
<%@ page import="java.util.List" import="Main.Login"
	import="java.sql.Connection" import="java.sql.DriverManager"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.sql.*" import="utils.Javatosql"
	import="java.util.stream.Stream"%>
<%
	Medico medico = (Medico) session.getAttribute("medico");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Administrador</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!--===============================================================================================-->
<link rel="icon" type="image/png" href="../images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="../css/util.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<!--===============================================================================================-->
<title>Gerir Utente</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap"
	rel="stylesheet">
</head>




<body style="background-image: url('../images/back.jpg');">

	<div class="limiter">
		<div class="wrapper" id="myButton">
			<form class="login100-form validate-form" action="Medico.jsp">
				<button class="login100-form-btn" name="button">Voltar</button>
			</form>
		</div>
		<div class="container-login100">
			<div class="wrap-login100 p-t-85 p-b-20">
				<form class="login100-form validate-form">
					<span class="login100-form-title p-b-70">Marcar status das
						consultas passadas</span>
				</form>
			</div>
			<table id="customers">
				<%
					String[] status = medico.listarStatusConsulta();
				%>
				<tr>
					<th>NIF</th>
					<th>Nome Completo</th>
					<th>Data Consulta</th>
					<th>Hora Consulta</th>
					<th>Especialidade</th>
					<th>Estado</th>
				</tr>
				<%
					for (int i = 0; i < status.length; i++) {
						System.out.println(status[i]);
						String[] tempString = status[i].split("=");
				%>
				<tr>
					<td><%=tempString[0]%></td>
					<td><%=tempString[1]%></td>
					<td><%=tempString[2]%></td>
					<td><%=tempString[3]%></td>
					<td><%=tempString[4]%></td>
					<%
						if (tempString[5].equalsIgnoreCase("falta")) {
					%>
					<td style="background-color: red"><%=tempString[5]%></td>
					<%
						} else if (tempString[5].equalsIgnoreCase("presenca")) {
					%>
					<td style="background-color: green"><%=tempString[5]%></td>
					<%
						} else {
					%>
					<%
						session.setAttribute("redirectStatusConsulta" + i, tempString);
					%>
					<td>
						<form id="formEsp" method="POST"
							action="MedicoEliminarWait.jsp?id=<%=i%>&type=falta">
							<button style="background-color: red" class="login100-form-btn"
								name="falta">Falta</button>
						</form>
						<form id="formEsp" method="POST"
							action="MedicoEliminarWait.jsp?id=<%=i%>&type=presenca">
							<button style="background-color: green" class="login100-form-btn"
								name="presenca">Presença</button>
						</form>


					</td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</table>
		</div>
</body>
</html>
