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

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!--===============================================================================================-->
<link rel="icon" type="image/png" href="../../images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="../../css/util.css">
<link rel="stylesheet" type="text/css" href="../../css/main.css">
<!--===============================================================================================-->
<title>Gerir Utente</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap"
	rel="stylesheet">
</head>




<body style="background-image: url('../../images/back.jpg');">

	<div class="limiter">
		<div class="wrapper" id="myButton">
			<form class="login100-form validate-form" action="../Admin_index.jsp">
				<button class="login100-form-btn" name="button">Voltar</button>
			</form>
		</div>
		<div class="container-login100">

			<div class="wrap-login100 p-t-85 p-b-20">
				<form class="login100-form validate-form">
					<span class="login100-form-title p-b-70">Médicos Existentes</span>
				</form>

				<form id="formEsp" method="POST" action="criarMedico.jsp">
					<button class="login100-form-btn" name="criarMedico">Adicionar
						novo médico</button>
				</form>
			</div>
			<br> <br> <br>
			<table id="customers">
				<%
					String[] medicos = admin.listarMedicos();
				%>

				<tr>
					<th>NIF</th>
					<th>Nome Completo</th>
					<th>Idade</th>
					<th>Sexo</th>
					<th>Morada</th>
					<th>Vencimento</th>
					<th>Especialidade</th>
					<th>Foto</th>
					<th>Editar</th>
					<th>Eliminar</th>
				</tr>
				<%
					for (int i = 0; i < medicos.length; i++) {
						String[] tempString = medicos[i].split("=");
				%>
				<tr>
					<td><%=tempString[0]%></td>
					<td><%=tempString[1]%></td>
					<td><%=tempString[2]%></td>
					<td><%=tempString[3]%></td>
					<td><%=tempString[4]%></td>
					<td><%=tempString[5]%></td>
					<td><%=tempString[6]%></td>

					<td><img height="120" width="100"
						src="data:image/png;base64,<%=tempString[7]%>"
						alt="Foto de Perfil" /></td>
					<td>
						<%
							session.setAttribute("redirectMedico" + i, tempString);
						%>
						<form id="formEsp" method="POST"
							action="criarMedico.jsp?attbuteRedirected=<%=i%>">
							<button class="login100-form-btn" name="editUtente">Editar</button>
						</form>
					</td>
					<td>
						<form id="formEsp" method="POST"
							action="../EliminarWait.jsp?reloadDel=<%=tempString[0]%>&type=medico">
							<button style="background-color: red;" class="login100-form-btn"
								name="eliminar">Eliminar</button>
						</form>
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>
	
</body>
</html>
