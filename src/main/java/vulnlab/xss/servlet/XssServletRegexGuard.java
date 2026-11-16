package vulnlab.xss.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Nom limité à [a-zA-Z0-9 ] avant l'écriture : sûr. */
public class XssServletRegexGuard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        if (!name.matches("[a-zA-Z0-9 ]+")) {
            response.sendError(400);
            return;
        }
        response.setContentType("text/html");
        response.getWriter().println("<h1>Bonjour " + name + "</h1>");
    }
}
