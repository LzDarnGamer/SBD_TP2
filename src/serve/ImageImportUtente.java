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

@WebServlet("/setFlagServlet")
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class ImageImportUtente extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Admin admin = new Admin();
		String redirected = (String)request.getSession(false).getAttribute("attbuteRedirected");

		String[] tempString = (String[]) request.getSession(false).getAttribute("redirectUtente" + redirected);

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

		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("foto");
		if(filePart.getSize()<1) {
			
			admin.updateUtente(nif, sexo, idade, nome, apelido, morada, tempString[5]);
			response.sendRedirect(request.getContextPath()+"/Login/Admin/criar_Utente/Admin_Utente.jsp");
		}
		else {
			input = filePart.getInputStream();
			if (redirected != null) {
				admin.updateUtente(nif, sexo, idade, nome, apelido, morada, input);
				response.sendRedirect(request.getContextPath()+"/Login/Admin/criar_Utente/Admin_Utente.jsp");
			} else {
				admin.createUtente(nif, sexo, idade, nome, apelido, morada, input);
				response.sendRedirect(request.getContextPath()+"/Login/Admin/criar_Utente/Admin_Utente.jsp");
			}
		}
		request.getSession(false).removeAttribute("attbuteRedirected");
	}
}