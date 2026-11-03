package vulnlab.xxe.spring;

import java.io.StringReader;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringXPathInputSource {

    @PostMapping("/xxe/xxespringxpathinputsource")
    public Object handle(@RequestBody String xml) throws Exception {
        XPath xpath = XPathFactory.newInstance().newXPath();
        return xpath.evaluate("/order/id", new InputSource(new StringReader(xml))); // parseur interne : vulnérable
    }
}
