package vulnlab.xxe.spring;

import java.io.StringReader;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringExternalEntitiesOff {

    @PostMapping("/xxe/xxespringexternalentitiesoff")
    public Object handle(@RequestBody String xml) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false); // protégé : les deux
        XMLReader reader = spf.newSAXParser().getXMLReader();
        reader.parse(new InputSource(new StringReader(xml)));
        return "ok";
    }
}
