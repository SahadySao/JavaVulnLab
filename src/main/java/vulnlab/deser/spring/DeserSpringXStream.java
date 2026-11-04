package vulnlab.deser.spring;

import com.thoughtworks.xstream.XStream;
import org.springframework.web.bind.annotation.*;

/** XStream sans liste blanche (vulnérable jusqu'à la 1.4.17). */
@RestController
public class DeserSpringXStream {
    @PostMapping("/deser/deserspringxstream")
    public Object handle(@RequestBody String xml) {
        XStream xstream = new XStream();
        return xstream.fromXML(xml);
    }
}
