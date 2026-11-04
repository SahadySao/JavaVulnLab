package vulnlab.deser.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.Envelope;

/** Mapper par défaut, mais Envelope.payload porte @JsonTypeInfo(use = Id.CLASS) sur un Object. */
@RestController
public class DeserSpringJacksonTypeInfo {
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/deser/deserspringjacksontypeinfo")
    public Object handle(@RequestBody String json) throws Exception {
        Envelope envelope = mapper.readValue(json, Envelope.class);
        return envelope.type;
    }
}
