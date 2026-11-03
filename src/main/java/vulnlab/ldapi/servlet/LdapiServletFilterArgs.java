package vulnlab.ldapi.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;

/** JNDI : filtre paramétré {0}, la valeur est échappée par l'API. Sûr. */
public class LdapiServletFilterArgs extends HttpServlet {
    private DirContext ctx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            String uid = request.getParameter("uid");
            ctx.search("ou=users,dc=exemple,dc=com", "(uid={0})", new Object[] {uid}, new SearchControls());
        } catch (Exception e) {
            throw new java.io.IOException(e);
        }
    }
}
