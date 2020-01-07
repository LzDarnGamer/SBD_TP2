<%@page import="utils.utils"%>
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
	String type = request.getParameter("type");
	String id = request.getParameter("id");
	String[] tempString = (String[]) session.getAttribute("redirectStatusConsulta" + id);

	medico.marcarStatusConsulta(utils.dateFormater(tempString[2]), utils.timeFormater(tempString[3]), tempString[4],
			Integer.parseInt(tempString[0]), type);
	
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Medico</title>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!--===============================================================================================-->
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
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
					<span class="login100-form-title p-b-70">A marcar<%
						if (type.equals("falta")) {
					%> falta <%
						} else if (type.equals("presenca")) {
					%> presenca <%
						} %> 
					... aguarde
					</span>
				</form>
			</div>
		</div>
	</div>

</body>
<script>
	var timer = setTimeout(function() {
		window.location = document.referrer
	}, 1500);
</script>
</html>
