package vulnlab.xss.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** Pas un XSS : la donnée est écrite dans un fichier de log, pas dans la réponse. */
public class XssServletFileWriter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        PrintWriter log = new PrintWriter(new FileWriter("/var/log/app/visits.log", true));
        log.println("visite de " + name);
        log.close();
        response.getWriter().println("<p>Merci de votre visite</p>");
    }
}
