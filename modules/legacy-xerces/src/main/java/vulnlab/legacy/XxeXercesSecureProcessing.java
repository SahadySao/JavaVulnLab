package vulnlab.legacy;

import java.io.ByteArrayInputStream;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.bind.annotation.*;

/** xercesImpl dans le pom : newInstance() renvoie le Xerces d'Apache, qui ignore FEATURE_SECURE_PROCESSING. */
@RestController
public class XxeXercesSecureProcessing {

    @PostMapping("/legacy/xxe/secureprocessing")
    public Object handle(@RequestBody String xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true); // sans effet sur les entités avec Apache Xerces
        return dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes())).getDocumentElement().getNodeName();
    }
}
