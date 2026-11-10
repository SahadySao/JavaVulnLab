package vulnlab.xxe.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

/** Le nom de l'option est dans une constante du projet : protégé. */
public class XxeServletProjectConstant extends HttpServlet {
    private static final String DISALLOW_DOCTYPE = "http://apache.org/xml/features/disallow-doctype-decl";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(DISALLOW_DOCTYPE, true);
            response.getWriter().print(dbf.newDocumentBuilder().parse(request.getInputStream()).getDocumentElement().getNodeName());
        } catch (Exception e) {
            throw new java.io.IOException(e);
        }
    }
}
