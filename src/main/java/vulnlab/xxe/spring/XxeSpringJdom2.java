package vulnlab.xxe.spring;

import java.io.StringReader;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringJdom2 {

    @PostMapping("/xxe/xxespringjdom2")
    public Object handle(@RequestBody String xml) throws Exception {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new StringReader(xml)); // JDOM2 par défaut : vulnérable
        return doc.getRootElement().getName();
    }
}
