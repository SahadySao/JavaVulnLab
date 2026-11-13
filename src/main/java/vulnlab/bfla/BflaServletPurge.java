package vulnlab.bfla;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** Purge des données hors de toute contrainte web.xml et de Spring Security. */
@WebServlet("/legacy/purge")
public class BflaServletPurge extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
