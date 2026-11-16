package vulnlab.redirect;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** La variable est réaffectée à une valeur libre : next=https://pirate.fr passe. */
public class RedirectVarReassigned extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String target = "/compte/" + request.getParameter("page");
        String next = request.getParameter("next");
        if (next != null) {
            target = next;
        }
        response.sendRedirect(target);
    }
}
