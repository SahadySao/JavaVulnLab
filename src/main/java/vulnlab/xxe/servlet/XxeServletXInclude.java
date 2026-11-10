package vulnlab.xxe.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/** DTD interdite, mais XInclude activé : <xi:include href="file:///etc/passwd" parse="text"/> passe. */
public class XxeServletXInclude extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            dbf.setNamespaceAware(true);
            dbf.setXIncludeAware(true);
            Document doc = dbf.newDocumentBuilder().parse(request.getInputStream());
            response.getWriter().print(doc.getDocumentElement().getTextContent());
        } catch (Exception e) {
            throw new java.io.IOException(e);
        }
    }
}
