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
	String tempNif = request.getParameter("reloadDel");
	int nif = 0;
	if (tempNif != null) {
		nif = Integer.parseInt(tempNif);
	}
	String type = request.getParameter("type");
	if (type.equals("medico")) {
		admin.deleteMedico(nif);
	} else if (type.equals("utente")) {
		admin.deleteUtente(nif);
	} else if (type.equals("exportar")) {
		admin.exportarUtente(nif);
	} else if (type.equals("importar")) {
		String path = (String) session.getAttribute("importarPath");
		admin.importarUtente(path);
	}
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
					<span class="login100-form-title p-b-70">A <%
						if (type.equals("exportar")) {
					%> exportar <%
						} else if (type.equals("importar")) {
					%> importar <%
						} else {
					%> eliminar <%
						}
					%>... aguarde
					</span>
				</form>
			</div>
		</div>
	</div>




</body>
<script>
	var timer = setTimeout(function() {
		<%if(type.equals("importar")){%>
		window.location = "Admin_index.jsp"
		<%}else{%>
		window.location = document.referrer
		<%}%>
	}, 1500);
</script>
</html>
