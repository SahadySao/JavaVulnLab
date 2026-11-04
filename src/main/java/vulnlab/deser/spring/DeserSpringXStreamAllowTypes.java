package vulnlab.deser.spring;

import com.thoughtworks.xstream.XStream;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.Order;

/** XStream configuré dans le constructeur : seule la classe Order est acceptée. */
@RestController
public class DeserSpringXStreamAllowTypes {
    private final XStream xstream;

    public DeserSpringXStreamAllowTypes() {
        xstream = new XStream();
        xstream.allowTypes(new Class[] {Order.class});
    }

    @PostMapping("/deser/deserspringxstreamallowtypes")
    public Object handle(@RequestBody String xml) {
        return xstream.fromXML(xml);
    }
}
