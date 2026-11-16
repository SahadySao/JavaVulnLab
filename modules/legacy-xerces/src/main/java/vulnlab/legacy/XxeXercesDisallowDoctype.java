package vulnlab.legacy;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.bind.annotation.*;

/** disallow-doctype-decl est une option de Xerces lui-même : elle protège aussi avec Apache Xerces. */
@RestController
public class XxeXercesDisallowDoctype {

    @PostMapping("/legacy/xxe/disallowdoctype")
    public Object handle(@RequestBody String xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        return dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes())).getDocumentElement().getNodeName();
    }
}
