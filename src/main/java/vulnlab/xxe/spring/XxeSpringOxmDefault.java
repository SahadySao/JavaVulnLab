package vulnlab.xxe.spring;

import java.io.StringReader;
import javax.xml.transform.stream.StreamSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.Order;

/** Spring OXM par défaut : DTD et entités externes refusées. */
@RestController
public class XxeSpringOxmDefault {
    private final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    public XxeSpringOxmDefault() {
        marshaller.setClassesToBeBound(Order.class);
    }

    @PostMapping(value = "/xxe/oxmdefault", consumes = "application/xml")
    public Object handle(@RequestBody String xml) {
        return marshaller.unmarshal(new StreamSource(new StringReader(xml)));
    }
}
