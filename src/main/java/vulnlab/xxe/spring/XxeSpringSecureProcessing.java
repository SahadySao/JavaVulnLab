package vulnlab.xxe.spring;

import java.io.ByteArrayInputStream;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringSecureProcessing {

    @PostMapping("/xxe/xxespringsecureprocessing")
    public Object handle(@RequestBody String xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true); // protégé
        return dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes())).getDocumentElement().getNodeName();
    }
}
