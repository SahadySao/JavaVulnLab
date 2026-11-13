package vulnlab.bfla;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** Servlet d'administration : rôle ADMIN exigé par web.xml (/legacy/admin/*). */
@WebServlet("/legacy/admin/stats")
public class BflaServletAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
