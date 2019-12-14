package serve;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/setFlagServlet")
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class setFlagServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // gets values of text fields
         String country = request.getParameter("country");
         if (country==null)
        	 return;  // vai falhar a atualização
         
         /* column  = {mini,normal,big,ultra} */
         String column = request.getParameter("format");
         if (column==null)
        	 column = "normal";

        InputStream input = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("flag");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            input = filePart.getInputStream();
        }

        // sends the statement to the database server
        FromToDb.writeBlob(country, column, input);
        // redirect previous page
        HttpSession ses=request.getSession();
        ses.setAttribute("country", country);
        System.out.println("Parametro > "+country);
        response.sendRedirect(request.getHeader("referer"));
    }
}