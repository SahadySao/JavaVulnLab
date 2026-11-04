package vulnlab.deser.spring;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import org.springframework.web.bind.annotation.*;

/** XMLDecoder exécute les appels de méthode décrits dans le XML : aucune protection possible. */
@RestController
public class DeserSpringXmlDecoder {
    @PostMapping("/deser/deserspringxmldecoder")
    public Object handle(@RequestBody String xml) {
        try (XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(xml.getBytes()))) {
            return decoder.readObject();
        }
    }
}
