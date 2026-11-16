package vulnlab.xss.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Réponse en application/json : le navigateur ne l'interprète pas comme du HTML (sûr pour le XSS). */
public class XssServletJsonContentType extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        response.setContentType("application/json");
        response.getWriter().println("{\"bonjour\": \"" + name + "\"}");
    }
}
