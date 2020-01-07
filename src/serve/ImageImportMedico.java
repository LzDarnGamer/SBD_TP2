package serve;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mysql.cj.Session;

import admin.Admin;
import utils.Javatosql;

@WebServlet("/ImageImportMedico")
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class ImageImportMedico extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Admin admin = (Admin) request.getSession(false).getAttribute("admin");
		String redirected = (String)request.getSession(false).getAttribute("attbuteRedirected");

		String[] tempString = (String[]) request.getSession(false).getAttribute("redirectMedico" + redirected);
		
		int nif;
		if (redirected != null) {
			nif = Integer.parseInt(tempString[0]);
		} else {
			nif = Integer.parseInt(request.getParameter("nif"));
		}
		
		InputStream input = null; // input stream of the upload file
		String nome = request.getParameter("nome");
		String apelido = request.getParameter("apelido");
		String age = request.getParameter("idade");
		int idade = Integer.parseInt(age);
		String sexo = request.getParameter("gender");
		String morada = request.getParameter("morada");
		String venci = request.getParameter("vencimento");
		int vencimento = Integer.parseInt(venci);
		String especialidade = request.getParameter("especialidade");
		
		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("foto");
		if(filePart.getSize()<1) {
			
			admin.updateMedico(nif, sexo, idade, nome, apelido, morada, tempString[5], vencimento, especialidade);
			response.sendRedirect(request.getContextPath()+"/Login/Admin/criar_Utente/Admin_Medico.jsp");
		}
		else {
			input = filePart.getInputStream();
			if (redirected != null) {
				admin.updateMedico(nif, sexo, idade, nome, apelido, morada, input, vencimento, especialidade);
				response.sendRedirect(request.getContextPath()+"/Login/Admin/criar_Utente/Admin_Medico.jsp");
			} else {
				admin.createMedico(nif, sexo, idade, nome, apelido, morada, vencimento, especialidade, input);
				response.sendRedirect(request.getContextPath()+"/Login/Admin/criar_Utente/Admin_Medico.jsp");
			}
		}
		request.getSession(false).removeAttribute("attbuteRedirected");
	}
}