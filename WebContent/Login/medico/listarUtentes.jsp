<%@ page import="java.util.List" import="Main.Login"
	import="java.sql.Connection" import="java.sql.DriverManager"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.sql.*" import="utils.Javatosql" import="medico.Medico"
	import="java.util.Arrays" import="java.sql.Date"%>
<%
	//Medico m = (Medico)session.getAttribute("utente");
	Medico m = new Medico(830013912);
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
<title>Listar Utentes</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="dist/njtimepicker.css">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap"
	rel="stylesheet">
<style>
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
			<label for="format_24">Data</label>
			<%
				String dataSel = (String) request.getParameter("dataSel");
			%>
			<select class="container" id="dataID" onchange="updateData()">
				<%
					// Array com todas as datas das consultas do medico
					String[] datas = m.listarDatas();
					for (int i = 0; i < datas.length; ++i) {
						out.print("<option>" + datas[i] + "</option>");
					}
				%>
			</select>
			<button class="login100-form-btn" name="OKdata"
				style="margin-bottom: 15px; height: 30px">OK</button>
			<input type='hidden' name='dataS' value='Data' id="dataaa" />

			<%
				if (dataSel != null) {
			%>
			<label for="format_24">Mancha Horaria</label> <select
				class="container" id="horaID" onchange="updateHora()">

				<%
					String[] bb = dataSel.split("-");
						Date daataa = Javatosql.dateFormater(Integer.parseInt(bb[0]), Integer.parseInt(bb[1]),
								Integer.parseInt(bb[2]));
						String[] aaa = m.listarManchasHorariasMarcadas(daataa);
						for (int i = 0; i < aaa.length; ++i) {
				%>
				<option><%=aaa[i]%></option>
				<%
					}
				%>

			</select>
			<button class="login100-form-btn" name="OKhora"
				style="margin-bottom: 15px; height: 30px">OK</button>
			<input type='hidden' name='horaS' value='Data' id="hora" />
			<%
				}
			%>

			<%
				String ajuda = (String) request.getParameter("horaSel");
				if (ajuda != null) {
			%>
			<table id="customers">
				<tr>
					<th>Nif</th>
				</tr>
				<tr>
					<%
						String[] bb = dataSel.split("-");
							Date daataa = Javatosql.dateFormater(Integer.parseInt(bb[0]), Integer.parseInt(bb[1]),
									Integer.parseInt(bb[2]));
							Time ads = Javatosql.timeFormater(ajuda.split(":")[0], ajuda.split(":")[1]);
							String pess[] = m.listarPessoasManchas(daataa, ads);
							String[] unique = Arrays.stream(pess).distinct().toArray(String[]::new);

							for (int i = 0; i < unique.length; ++i) {
					%>
					<td><%=unique[i]%></td>
					<%
						}
					%>
				</tr>
			</table>
			<%
				}
			%>
		</form>
	</div>
	<div class="wrapper" id="myButton">
		<form class="login100-form validate-form" action="Utente.jsp">
			<button class="login100-form-btn" name="button">Voltar</button>
		</form>
	</div>
</body>

<%
	String dataBtn = request.getParameter("OKdata");
	if (dataBtn != null) {
		String lista = request.getParameter("dataS");
		response.sendRedirect("listarUtentes.jsp?dataSel=" + lista);
	}

	String horaBtn = request.getParameter("OKhora");
	if (horaBtn != null) {
		String listaa = request.getParameter("horaS");
		response.sendRedirect("listarUtentes.jsp?dataSel=" + dataSel + "&horaSel=" + listaa);
	}
%>


<script type="text/javascript">
	window.onload = function(e) {
		let selector = document.getElementById('dataID');
		let value = selector[selector.selectedIndex].value;
		document.getElementById("dataaa").setAttribute("value", value);

		let selector1 = document.getElementById('horaID');
		let value1 = selector1[selector.selectedIndex].value;
		document.getElementById("hora").setAttribute("value", value1);
	}

	function updateData() {
		var selector2 = document.getElementById('dataID');
		var value2 = selector[selector.selectedIndex].value;

		document.getElementById("data").setAttribute("value", value2);

	}

	function updateHora() {
		var selector3 = document.getElementById('horaID');
		var value3 = selector[selector.selectedIndex].value;

		document.getElementById("hora").setAttribute("value", value3);

	}
</script>
</html>