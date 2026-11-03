package vulnlab.xxe.spring;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringHelperFactory {

    @PostMapping("/xxe/xxespringhelperfactory")
    public Object handle(@RequestBody String xml) throws Exception {
        DocumentBuilderFactory dbf = secureFactory();
        return dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes())).getDocumentElement().getNodeName();
    }

    private static DocumentBuilderFactory secureFactory() throws Exception {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); // protégé dans une méthode utilitaire
        return f;
    }
}
