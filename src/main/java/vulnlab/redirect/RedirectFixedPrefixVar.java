package vulnlab.redirect;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Préfixe fixe dans une variable : l'hôte ne change pas. Sûr. */
public class RedirectFixedPrefixVar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String target = "/compte/" + request.getParameter("page");
        response.sendRedirect(target);
    }
}
