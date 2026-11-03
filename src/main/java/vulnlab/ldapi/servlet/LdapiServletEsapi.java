package vulnlab.ldapi.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import org.owasp.esapi.ESAPI;

/** JNDI : valeur échappée avec ESAPI encodeForLDAP. Sûr. */
public class LdapiServletEsapi extends HttpServlet {
    private DirContext ctx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            String uid = ESAPI.encoder().encodeForLDAP(request.getParameter("uid"));
            ctx.search("ou=users,dc=exemple,dc=com", "(uid=" + uid + ")", new SearchControls());
        } catch (Exception e) {
            throw new java.io.IOException(e);
        }
    }
}
