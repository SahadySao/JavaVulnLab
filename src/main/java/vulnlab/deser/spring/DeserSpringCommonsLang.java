package vulnlab.deser.spring;

import java.util.Base64;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.web.bind.annotation.*;

/** SerializationUtils.deserialize : un ObjectInputStream sans filtre, caché derrière un utilitaire. */
@RestController
public class DeserSpringCommonsLang {
    @PostMapping("/deser/deserspringcommonslang")
    public Object handle(@RequestParam String data) {
        return SerializationUtils.deserialize(Base64.getDecoder().decode(data));
    }
}
