<%@ page import="java.util.List" import="Main.Login"
	import="java.sql.Connection" import="java.sql.DriverManager"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.sql.*" import="utils.Javatosql" import="utente.Utente"%>
<%
Utente u = (Utente)session.getAttribute("utente");
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
<title>Utente</title>
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

	<div class="wrapper" id="menuP">
		<form id="formEsp" method="POST">
			<label for="format_24">Especialidade</label> <select
				class="container" onchange="updateEspecialidade()"
				id="especialidadeID">
				<%
					String espSel = (String)request.getParameter("espSel");
					String especialidades[] = Javatosql.listarEspecialidades(u.getCon());
					
					for (int i = 0; i < especialidades.length; ++i) {
						String ajuda = especialidades[i].trim();
						%>
				<option <%if (ajuda.equals(espSel)) {%> selected <%}%>><%=ajuda%></option>
				<%
					}
				
				%>
			</select>
			<button class="login100-form-btn" name="OKesp">OK</button>
			<input type='hidden' name='especialidadeSelecionada'value='especialidadeAqui' id="especialidade" />
		</form>
		
		<form id="formEsp" method="POST">
			<label for="format_24">Data</label>
			<select
				class="container" onchange="updateData()"
				id="dataID">
				<%
					if (espSel != null) {
						String cenas[] = Javatosql.listarDiaHora(u.getCon(), espSel);
						if (cenas != null) {
							for (int i = 0; i < cenas.length; ++i) {
								String help[] = cenas[i].split(" ");
								if (help.length == 4) {
									String coisa = help[0];
								%>
									<option><%=coisa%></option>
								<%
								}
							}
						
					
				%>
			</select>
			<input type='hidden' name='daataSelecionada' value='dataAqui' id="data" />
			
			<select class="container" onchange="updateHora()" id="horaID">
				<%
							for (int i = 0; i < cenas.length; ++i) {
								String help1[] = cenas[i].split(" ");
								if (help1.length == 4) {
									String coisa = help1[1];
								%>
									<option><%=coisa%></option>
								<%
								}
							}
						}
					}
				%>
			</select>
			<input type='hidden' name='horaSelecionada' value='horaAqui' id="hora" />
			
			<button class="login100-form-btn" name="OKdata">Marcar Consulta</button>
			<font color="White" size="15">${marcaSuc}</font>
		</form>

		<script>
		
		window.onload = function(e) {
			let selector = document.getElementById('especialidadeID');
		    let value = selector[selector.selectedIndex].value;
			document.getElementById("especialidade").setAttribute("value", value);
			
			let selectData = document.getElementById('dataID');
			let valueData = selectData[selectData.selectedIndex].value;
			document.getElementById("data").setAttribute("value", valueData);
			
			let selectHora = document.getElementById('horaID');
			let valueHora = selectHora[selectHora.selectedIndex].value;
			document.getElementById("hora").setAttribute("value", valueHora);		
		}
		
		
			function updateEspecialidade () {
				var selector = document.getElementById('especialidadeID');
			    var value = selector[selector.selectedIndex].value;
			    
				document.getElementById("especialidade").setAttribute("value", value);
				
			}
			
			function updateData () {
				var selectData = document.getElementById('dataID');
				var valueData = selectData[selectData.selectedIndex].value;
				document.getElementById("data").setAttribute("value", valueData);
			}
			
			function updateHora () {
				var selectHora = document.getElementById('horaID');
				var valueHora = selectHora[selectHora.selectedIndex].value;
				document.getElementById("hora").setAttribute("value", valueHora);
			}
			
				<%
				String espBtn = request.getParameter("OKesp");
				if (espBtn != null) {
					String esp = request.getParameter("especialidadeSelecionada");
					
					response.sendRedirect("MarcarConsulta.jsp?espSel=" + esp);
				}
				
				String dataBtn = request.getParameter("OKdata");
				if (dataBtn != null) {
					String dataS = request.getParameter("daataSelecionada");
					String horaS = request.getParameter("horaSelecionada");
					
					String haha[] = dataS.split("-");
					Date data = Javatosql.dateFormater(Integer.parseInt(haha[0]), Integer.parseInt(haha[1]), Integer.parseInt(haha[2]));
					
					String spaghetti[] = horaS.split(":");
					Time hora = Javatosql.timeFormater(spaghetti[0], spaghetti[1]);
					
					String cenas[] = Javatosql.listarDiaHora(u.getCon(), espSel);
					int nif_medico = Integer.parseInt(cenas[0].split(" ")[3]);
					if(u.marcarConsulta(data, hora, espSel, nif_medico)==1){
						%>
						var div = document.createElement("div");
						div.innerHTML = "Consulta marcada com sucesso";
						div.style.marginTop = "10px";
						document.getElementById('menuP').appendChild(div);
						<%
					}else{
						%>
						var div = document.createElement("div");
						div.innerHTML = "Consulta não marcada! Verifique os parâmetros";
						div.style.color = "red";
						div.style.marginTop = "10px";
						document.getElementById('menuP').appendChild(div);
						<%
					}
					//response.sendRedirect("MarcarConsulta.jsp?espSel=" + espSel + "&data=" + dataS + "&hora=" + horaS);
				}
				%>
			
			</script>
	</div>
	<!-- <div class="container-login100-form-btn">
		<button class="login100-form-btn" name="login">Entrar</button>
	</div> -->
</body>

<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>



</html>