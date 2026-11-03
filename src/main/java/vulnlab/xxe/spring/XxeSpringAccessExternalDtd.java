package vulnlab.xxe.spring;

import java.io.ByteArrayInputStream;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringAccessExternalDtd {

    @PostMapping("/xxe/xxespringaccessexternaldtd")
    public Object handle(@RequestBody String xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // protégé
        return dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes())).getDocumentElement().getNodeName();
    }
}
