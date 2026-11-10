package vulnlab.xxe.spring;

import java.io.StringReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.springframework.util.xml.StaxUtils;
import org.springframework.web.bind.annotation.*;

/** Fabrique défensive de Spring : DTD déjà refusées. */
@RestController
public class XxeSpringStaxDefensive {
    @PostMapping(value = "/xxe/staxdefensive", consumes = "application/xml")
    public String handle(@RequestBody String xml) throws Exception {
        XMLInputFactory xif = StaxUtils.createDefensiveInputFactory();
        XMLStreamReader reader = xif.createXMLStreamReader(new StringReader(xml));
        return reader.hasNext() ? "ok" : "vide";
    }
}
