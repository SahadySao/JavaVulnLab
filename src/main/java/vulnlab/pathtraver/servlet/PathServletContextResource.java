package vulnlab.pathtraver.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;

/** Ressource de la webapp : name = "../web.xml" lit WEB-INF/web.xml. */
public class PathServletContextResource extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        String name = request.getParameter("report");
        try (InputStream in = getServletContext().getResourceAsStream("/WEB-INF/reports/" + name)) {
            in.transferTo(response.getOutputStream());
        }
    }
}
