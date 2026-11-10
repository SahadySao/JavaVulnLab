package vulnlab.xxe.spring;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;

/** Fabrique injectée par champ : sa protection est dans LabBeansConfig. */
@RestController
public class XxeSpringInjectedFactory {
    @Autowired
    private DocumentBuilderFactory factory;

    @PostMapping(value = "/xxe/injectedfactory", consumes = "application/xml")
    public String handle(@RequestBody String xml) throws Exception {
        Document doc = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
        return doc.getDocumentElement().getNodeName();
    }
}
