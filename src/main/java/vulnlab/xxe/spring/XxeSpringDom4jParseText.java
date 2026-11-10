package vulnlab.xxe.spring;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.web.bind.annotation.*;

/** DocumentHelper.parseText crée un SAXReader non protégé en interne : aucune option possible. */
@RestController
public class XxeSpringDom4jParseText {
    @PostMapping(value = "/xxe/dom4jparsetext", consumes = "application/xml")
    public String handle(@RequestBody String xml) throws Exception {
        Document doc = DocumentHelper.parseText(xml);
        return doc.getRootElement().getName();
    }
}
