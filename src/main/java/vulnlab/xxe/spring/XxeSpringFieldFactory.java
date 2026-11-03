package vulnlab.xxe.spring;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringFieldFactory {
    private final DocumentBuilderFactory factory;

    public XxeSpringFieldFactory() throws Exception {
        factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); // protégé dans le constructeur
    }

    @PostMapping("/xxe/xxespringfieldfactory")
    public Object handle(@RequestBody String xml) throws Exception {
        return factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes())).getDocumentElement().getNodeName();
    }
}
