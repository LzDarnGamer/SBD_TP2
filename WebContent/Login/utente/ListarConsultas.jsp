<%@ page import="java.util.List" import="Main.Login"
	import="java.sql.Connection" import="java.sql.DriverManager"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.sql.Date" import="java.sql.*" import="utente.Utente"
	import="utils.*" import="java.util.List" import="java.util.*"%>
<%
Utente u = (Utente)session.getAttribute("utente");
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
<body style="background-image: url('../images/back.jpg');">

	<div class="limiter">
		<div class="container-login100">
			<div>
				<span class="login100-form-title p-b-70"> Consultas </span>
				<form class="login100-form validate-form">

					<table id="customers">
						<tr>
							<th>Data</th>
							<th>Hora</th>
							<th>Medico</th>
							<th>Especialidade</th>
							<th>Eliminar</th>
						</tr>
						<%
					System.out.println(u.listarConsultas());
					String ajuda[] = u.listarConsultas();
					int counter = 0;
					List<String> aaa = new ArrayList<String>();
					System.out.println("HEREE " + Arrays.toString(ajuda));
					if (ajuda != null) {
					for (int i = 0; i < ajuda.length; ++i) {
						
						String abc = "";
				%>

						<%
						String help[] = ajuda[i].split(" ");
					%>
						<tr>
							<%
						
						for (int j = 0; j < help.length; ++j) {%>
							<!-- <div style="text-align:center;"> -->
							<%if (j == 0) { %>
							<td><b><%=help[j]%></b></td>
							<!-- <label><h4><b>=help[j]%></b></h4></label> -->
							<%} else if (j == 3 && help.length > 4) {
								
								for (int k = j; k < help.length; ++k) {
									abc += " " + help[k];
								}	
								aaa.add(abc);
							%>
							<td><%=abc%></td>
							<!-- <label>abc</label> -->
							<%
							j = help.length-1;
								
							} else { %>
							<td><%=help[j]%></td>
							<!-- <label>%=help[j]%></label> -->
							<%} 
						//System.out.println("HEREEEEEE " + j);
						if (j == help.length-1) {
							System.out.println("AQUIIIIII " + help[j]);
							%>
							<td>
								<button class="login100-form-btn" name="eliminar<%=i%>" id="lg">Eliminar</button>
							</td>
						</tr>

						<%
							aaa.add(help[j]);
						}
						%>


						<!-- </div -->
						<%}System.out.println("-----------------");%>

						<!-- 
					<div class="container-login100-form-btn">
						<button class="login100-form-btn" name="eliminar<%=i%>" id="lg">Eliminar</button>
					</div> -->
						<hr />



						<%
							String welp = request.getParameter("eliminar" + i);
									if (welp != null) {

										String haha[] = help[0].split("-");
										Date data = Javatosql.dateFormater(Integer.parseInt(haha[0]), Integer.parseInt(haha[1]),
												Integer.parseInt(haha[2]));

										String spaghetti[] = help[1].split(":");
										Time hora = Javatosql.timeFormater(spaghetti[0], spaghetti[1]);

										//String aav = (String)session.getAttribute("espe"+i);
										String especialidade = aaa.get(i).trim();
										//System.out.println("DENTRO: " + aaa.get(i));

										int nif_medico = Integer.parseInt(help[2]);

										u.cancelarConsulta(data, hora, especialidade, nif_medico);
									}

								}
							} else {

								System.out.println("Nao existem consultas para este utente");

							}
						%>
					</table>
				</form>
			</div>
			<div class="wrapper" id="myButton">
				<form class="login100-form validate-form" action="Utente.jsp">
					<button class="login100-form-btn" name="button">Voltar</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>