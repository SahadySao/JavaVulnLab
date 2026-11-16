package vulnlab.deser.spring;

import com.thoughtworks.xstream.XStream;
import org.springframework.web.bind.annotation.*;

/** allowTypesByWildcard("**") : la « liste blanche » accepte toutes les classes. */
@RestController
public class DeserSpringXStreamWildcardAll {
    private final XStream xstream;

    public DeserSpringXStreamWildcardAll() {
        xstream = new XStream();
        xstream.allowTypesByWildcard(new String[] {"**"});
    }

    @PostMapping("/deser/deserspringxstreamwildcardall")
    public Object handle(@RequestBody String xml) {
        return xstream.fromXML(xml);
    }
}
