package vulnlab.xxe.spring;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringExpandEntitiesOnly {

    @PostMapping("/xxe/xxespringexpandentitiesonly")
    public Object handle(@RequestBody String xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setExpandEntityReferences(false); // ne bloque pas les ressources externes (OWASP) : vulnérable
        return dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes())).getDocumentElement().getNodeName();
    }
}
