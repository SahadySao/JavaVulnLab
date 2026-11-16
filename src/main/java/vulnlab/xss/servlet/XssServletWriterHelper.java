package vulnlab.xss.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Le flux de la réponse est passé à une méthode utilitaire, qui y écrit le paramètre. */
public class XssServletWriterHelper extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        render(response.getWriter(), request.getParameter("name"));
    }

    private void render(PrintWriter out, String name) {
        out.println("<p>Bonjour " + name + "</p>");
    }
}
