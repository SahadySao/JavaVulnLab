package vulnlab.xss.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Le type dépend d'un paramètre : en text/html, le nom est interprété. */
public class XssServletJsonOrHtml extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        if (request.getParameter("format") != null) {
            response.setContentType("application/json");
        } else {
            response.setContentType("text/html");
        }
        response.getWriter().println("<h1>Bonjour " + name + "</h1>");
    }
}
