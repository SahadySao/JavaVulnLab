package vulnlab.journal;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/** java.util.logging, dans une servlet. */
@WebServlet("/journal/export")
public class JournalJul extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(JournalJul.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String user = request.getParameter("user");
        LOG.log(Level.INFO, "Export demandé par " + user);
    }
}
