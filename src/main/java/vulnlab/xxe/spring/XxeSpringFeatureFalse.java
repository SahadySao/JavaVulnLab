package vulnlab.xxe.spring;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringFeatureFalse {

    @PostMapping("/xxe/xxespringfeaturefalse")
    public Object handle(@RequestBody String xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false); // mauvaise valeur : vulnérable
        return dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes())).getDocumentElement().getNodeName();
    }
}
