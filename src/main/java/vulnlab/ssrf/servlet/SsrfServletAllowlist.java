package vulnlab.ssrf.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URL;
import java.util.Set;

/** Liste blanche sur l'HÔTE analysé par java.net.URL, vérifiée avant l'appel. */
public class SsrfServletAllowlist extends HttpServlet {
    private static final Set<String> ALLOWED = Set.of("api.interne", "cdn.interne");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        URL url = new URL(request.getParameter("url"));
        if (!ALLOWED.contains(url.getHost())) {
            response.sendError(400);
            return;
        }
        try (InputStream in = url.openStream()) {
            in.transferTo(response.getOutputStream());
        }
    }
}
