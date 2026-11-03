package vulnlab.xxe.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

/** Chaîne écrite d'un seul bloc : aucune protection n'est possible, vulnérable. */
public class XxeServletInlineFactory extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(request.getInputStream());
        } catch (Exception e) {
            throw new java.io.IOException(e);
        }
    }
}
