package vulnlab.entete;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Valeur d'un en-tête libre recopiée depuis la requête (risque selon le serveur et les proxys). */
@WebServlet("/entete/custom")
public class EnteteServletCustom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String origin = request.getParameter("source");
        response.setHeader("X-Request-Source", origin);
        response.getWriter().write("ok");
    }
}
