package serve;


import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/getFlagServlet")
public class getFlagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getFlagServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String ShortName = request.getParameter("country");
			if (ShortName == null)
				ShortName = "Portugal";   // se não tiver sido indicado, fica bandeira portuguesa
	         /* format  = {mini,normal,big,ultra} */
			String format = request.getParameter("format");
			if (format == null)
				format = "normal";   // se não tiver sido apresenta tamanho normal
			System.out.println("pais =" + ShortName);
			OutputStream output = response.getOutputStream();
			FromToDb.readBlob(ShortName, format, output);			
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
