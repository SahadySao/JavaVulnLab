package vulnlab.ssrf.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URL;

/** L'URL entière vient de la requête : ?url=http://169.254.169.254/latest/meta-data/ */
public class SsrfServletUrlOpenStream extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        URL url = new URL(request.getParameter("url"));
        try (InputStream in = url.openStream()) {
            in.transferTo(response.getOutputStream());
        }
    }
}
