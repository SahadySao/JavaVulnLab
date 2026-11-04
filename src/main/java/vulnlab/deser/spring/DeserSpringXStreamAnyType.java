package vulnlab.deser.spring;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.springframework.web.bind.annotation.*;

/** addPermission(AnyTypePermission.ANY) : la liste blanche est rouverte à toutes les classes. */
@RestController
public class DeserSpringXStreamAnyType {
    @PostMapping("/deser/deserspringxstreamanytype")
    public Object handle(@RequestBody String xml) {
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        return xstream.fromXML(xml);
    }
}
