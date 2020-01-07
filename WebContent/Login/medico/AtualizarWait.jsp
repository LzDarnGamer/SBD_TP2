<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="admin.Admin"%>
<%@ page import="java.util.List" import="Main.Login"
	import="java.sql.Connection" import="java.sql.DriverManager"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.sql.*" import="utils.Javatosql"
	import="java.util.stream.Stream" import="medico.Medico"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Medico</title>

<script src="../dist/the-datepicker.js"></script>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="../images/icons/favicon.ico" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!--===============================================================================================-->
<link rel="icon" type="image/png" href="../images/icons/favicon.ico" />
<link rel="stylesheet" type="text/css" href="../css/util.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<link href="../dist/the-datepicker.css" rel="stylesheet" />
<!--===============================================================================================-->
<title>Aguardar...</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../dist/njtimepicker.css">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../css/lightpick.css">
</head>




<body style="background-image: url('../images/back.jpg');">

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-85 p-b-20">
				<form class="login100-form validate-form">
					<span class="login100-form-title p-b-70">A atualizar... aguarde
					</span>
				</form>
			</div>
		</div>
	</div>
	
	<%
	Medico m = (Medico)session.getAttribute("medico");
	String tipo = (String)session.getAttribute("type");
	if (tipo.equals("data")) {
		String data = (String)request.getParameter("data");

		String[] datee = data.split("-");

		String horaInicio = (String) request.getParameter("hora");
		String[] h1 = horaInicio.split(":");

		m.atualizarMancha(Javatosql.dateFormater(Integer.parseInt(datee[0]), Integer.parseInt(datee[1]),
				Integer.parseInt(datee[2])), Javatosql.timeFormater(h1[0], h1[1]));

	} else {
		
		String hora = (String) request.getParameter("hora");
		String[] h = hora.split(":");

		String data = (String) request.getParameter("data");
		String[] datee = data.split("-");
		Date d = Javatosql.dateFormater(Integer.parseInt(datee[0]), Integer.parseInt(datee[1]),
				Integer.parseInt(datee[2]));

		m.atualizarMancha(Javatosql.timeFormater(h[0], h[1]), d);
		
	}
		
	
	%>

</body>
<script>
	var timer = setTimeout(function() {
		window.location = "manchas.jsp"
	}, 3000);
</script>
</html>
