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
					<span class="login100-form-title p-b-70">Utentes Existentes</span>
				</form>

				<form id="formEsp" method="POST" action="criarUtente.jsp">
					<button class="login100-form-btn" name="criarUtente">Adicionar
						novo utente</button>
				</form>
			</div>
			<br> <br> <br>
			<table id="customers">
				<%
					String[] utentes = admin.listarUtente();
				%>

				<tr>
					<th>NIF</th>
					<th>Nome Completo</th>
					<th>Idade</th>
					<th>Sexo</th>
					<th>Morada</th>
					<th>Foto</th>
					<th>Editar</th>
				</tr>
				<%
					for (int i = 0; i < utentes.length; i++) {
						String[] tempString = utentes[i].split("=");
				%>
				<tr>
					<td><%=tempString[0]%></td>
					<td><%=tempString[1]%></td>
					<td><%=tempString[2]%></td>
					<td><%=tempString[3]%></td>
					<td><%=tempString[4]%></td>
					<td><img height="120" width="100"
						src="data:image/png;base64,<%=tempString[5]%>"
						alt="Foto de Perfil" /></td>
					<td>
						<%
							session.setAttribute("redirect" + i, tempString);
						%>
						<form id="formEsp" method="POST"
							action="criarUtente.jsp?attbuteRedirected=<%=i%>">
							<button class="login100-form-btn" name="editUtente">Editar</button>
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
