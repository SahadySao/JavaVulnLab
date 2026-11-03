package vulnlab.xxe.spring;

import java.io.StringReader;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringDom4jSafe {

    @PostMapping("/xxe/xxespringdom4jsafe")
    public Object handle(@RequestBody String xml) throws Exception {
        SAXReader reader = new SAXReader();
        reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); // protégé
        Document doc = reader.read(new StringReader(xml));
        return doc.getRootElement().getName();
    }
}
