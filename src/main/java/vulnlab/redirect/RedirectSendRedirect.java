package vulnlab.redirect;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** sendRedirect(retour) : retour = https://site-pirate/connexion */
public class RedirectSendRedirect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String target = request.getParameter("returnUrl");
        response.sendRedirect(target);
    }
}
