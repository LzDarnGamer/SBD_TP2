<!DOCTYPE html>
<%@page import="java.util.Arrays"%>
<%@page import="utils.utils"%>
<%@page import="admin.Admin"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Gerir Medico</title>
<%
	Admin admin = (Admin) session.getAttribute("admin");
%>
<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">
<link rel="stylesheet" href="vendor/jquery-ui/jquery-ui.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body style="background-image: url('../../images/back.jpg');">
	<div class="main">

		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<form method="POST" id="signup-form" class="signup-form"
						action="../../../ImageImportMedico" enctype="multipart/form-data">
						<div class="form-row">
							<div class="form-group">
								<label for="first_name">NIF</label> <input type="number"
									class="form-input" name="nif" id="nif" min="100000000"
									max="999999999" required="required" />
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="first_name">Nome</label> <input type="text"
									class="form-input" name="nome" id="nome" required="required" />
							</div>
							<div class="form-group">
								<label for="last_name">Apelido</label> <input type="text"
									class="form-input" name="apelido" id="apelido"
									required="required" />
							</div>
						</div>
						<div class="form-row">
							<div class="form-group form-icon">
								<label for="first_name">Idade</label> <input type="number"
									class="form-input" name="idade" id="idade" required="required" />
							</div>
							<div class="form-radio">
								<label for="gender">Género</label>
								<div class="form-flex">
									<input type="radio" name="gender" value="M" id="M"
										checked="checked" required="required" /> <label for="M">Masculino</label>

									<input type="radio" name="gender" value="F" id="F"
										required="required" /> <label for="F">Feminino</label>
								</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="first_name">Morada</label> <input type="text"
									class="form-input" name="morada" id="morada"
									required="required" />
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="first_name">Vencimento</label> <input type="text"
									class="form-input" name="vencimento" id="vencimento"
									required="required" />
							</div>
							<div class="form-group">
								<label for="last_name">Especialidade</label> <input type="text"
									class="form-input" name="especialidade" id="especialidade"
									required="required" />
							</div>
						</div>
						<div class="form-row">
							<div class="form-group">
								<label for="first_name">Escolha um foto de perfil</label> <input
									type="file" class="form-input" name="foto" id="foto" required
									accept='image/*' />
								<h5>Nota: Se estiver a editar pode deixar o campo
									fotografia vazio, o sistema vai perservar a fotografia antiga</h5>
							</div>
						</div>
						<div class="form-group">
							<input type="submit" name="submit" id="submit"
								class="form-submit" value="Submit" />

						</div>

					</form>

				</div>
			</div>
			<div class="form-group" id="myButton">
				<form id="signup-form" class="signup-form"
					action="../Admin_index.jsp">
					<button class="form-submit" name="button">Voltar</button>
				</form>
			</div>
		</section>

		<%
			String redirected = request.getParameter("attbuteRedirected");
			String[] tempString = (String[]) session.getAttribute("redirectMedico" + redirected);

			if (redirected != null) {
				session.setAttribute("attbuteRedirected", redirected);
				String[] nomeApelido = tempString[1].split(" ");
		%>
		<script>
			document.getElementById("<%=tempString[3]%>").checked = true;
		</script>
		<script>
			document.getElementById("nif").value = <%=tempString[0]%>;
			document.getElementById("nif").disabled = true;
			document.getElementById("nome").value = "<%=nomeApelido[0]%>";
			//asdasdasda
			document.getElementById("apelido").value = "<%=nomeApelido[1]%>";
			//asdasdasd
			document.getElementById("idade").value = <%=tempString[2]%>;
			//asdasd
			
			//asdasd
			document.getElementById("morada").value = "<%=tempString[4]%>";
			//asdasd
			document.getElementById("vencimento").value = <%=tempString[5]%>;
			//asdasd
			document.getElementById("especialidade").value = "<%=tempString[6]%>";
			//asdasd
			document.getElementById("foto").required = false;
		</script>


		<%
			}
		%>
	</div>
</body>
</html>