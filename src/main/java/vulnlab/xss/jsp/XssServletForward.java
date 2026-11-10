package vulnlab.xss.jsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Servlet + JSP : request.setAttribute puis forward ; la JSP écrit request.getAttribute("visitor"). */
public class XssServletForward extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String visitor = request.getParameter("visitor");
        request.setAttribute("visitor", visitor);
        request.getRequestDispatcher("/WEB-INF/views/xss/XssServletForward.jsp").forward(request, response);
    }
}
