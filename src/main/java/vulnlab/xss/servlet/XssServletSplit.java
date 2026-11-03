package vulnlab.xss.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** split(" ")[0] ne protège pas du XSS : <script>alert(1)</script> ne contient pas d'espace.
  * Joern perd la donnée dans split(...) : Tiresias la retrouve grâce au pont de common/Flows.scala. */
public class XssServletSplit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tag = request.getParameter("tag");
        String first = tag.split(" ")[0];
        response.setContentType("text/html");
        response.getWriter().println("<p>Tag : " + first + "</p>");
    }
}
