package vulnlab.redirect;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** getScheme() porte sur la REQUÊTE (HSTS), pas sur l'URL de redirection, qui n'est pas vérifiée. */
public class RedirectSchemeLogged extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ("https".equals(request.getScheme())) {
            response.setHeader("Strict-Transport-Security", "max-age=31536000");
        }
        response.sendRedirect(request.getParameter("next"));
    }
}
