package vulnlab.deser.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

/** enableDefaultTyping : le JSON choisit la classe à instancier (["com.exemple.Gadget", {...}]). */
@RestController
public class DeserSpringJacksonDefaultTyping {
    @PostMapping("/deser/deserspringjacksondefaulttyping")
    public Object handle(@RequestBody String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        return mapper.readValue(json, Object.class);
    }
}
