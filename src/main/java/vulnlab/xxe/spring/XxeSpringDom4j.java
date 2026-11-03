package vulnlab.xxe.spring;

import java.io.StringReader;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringDom4j {

    @PostMapping("/xxe/xxespringdom4j")
    public Object handle(@RequestBody String xml) throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new StringReader(xml)); // dom4j par défaut : vulnérable
        return doc.getRootElement().getName();
    }
}
