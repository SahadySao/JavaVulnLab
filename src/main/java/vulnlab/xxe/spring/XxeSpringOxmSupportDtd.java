package vulnlab.xxe.spring;

import java.io.StringReader;
import javax.xml.transform.stream.StreamSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.Order;

/** Spring OXM avec les entités externes réactivées (pour accepter d'anciens documents). */
@RestController
public class XxeSpringOxmSupportDtd {
    private final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    public XxeSpringOxmSupportDtd() {
        marshaller.setClassesToBeBound(Order.class);
        marshaller.setSupportDtd(true);
        marshaller.setProcessExternalEntities(true);
    }

    @PostMapping(value = "/xxe/oxmsupportdtd", consumes = "application/xml")
    public Object handle(@RequestBody String xml) {
        return marshaller.unmarshal(new StreamSource(new StringReader(xml)));
    }
}
