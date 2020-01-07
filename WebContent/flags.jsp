<%@ page language="java"%>

<%@ page language="java" contentType="text/html; charset=windows-1252"%>
<%@ page errorPage="error.jsp"%>
<%@ page isThreadSafe="false"%>
<%@ page session="true"%>
<%@ page info="Exemplo das bandeiras"%>

<html>
<head>
<title>Bandeiras de Paises</title>

</head>
<body>

	<form method="post" action="setFlagServlet"
		enctype="multipart/form-data">
		<div>
			<label for="flag">Indique a bandeira:</label> <input type="file"
				name="flag" size="50" accept="image/*" required> <input
				type="submit" value="Atualizar">
		</div>
	</form>
</body>
</html>