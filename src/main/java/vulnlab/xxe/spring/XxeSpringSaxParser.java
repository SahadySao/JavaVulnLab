package vulnlab.xxe.spring;

import java.io.StringReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringSaxParser {

    @PostMapping("/xxe/xxespringsaxparser")
    public Object handle(@RequestBody String xml) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();
        parser.parse(new InputSource(new StringReader(xml)), new DefaultHandler()); // SAX par défaut : vulnérable
        return "ok";
    }
}
