package vulnlab.xxe.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;

/** Parseur non protégé, mais le XML est une constante : pas de XXE exploitable. */
public class XxeServletConstantXml extends HttpServlet {
    private static final String CONFIG = "<config><mode>prod</mode></config>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(CONFIG.getBytes()));
        } catch (Exception e) {
            throw new java.io.IOException(e);
        }
    }
}
