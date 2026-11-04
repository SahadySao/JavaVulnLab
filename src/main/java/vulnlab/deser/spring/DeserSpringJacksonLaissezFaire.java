package vulnlab.deser.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.web.bind.annotation.*;

/** activateDefaultTyping avec un validateur qui accepte tout, via le builder, dans un champ. */
@RestController
public class DeserSpringJacksonLaissezFaire {
    private final ObjectMapper mapper = JsonMapper.builder()
        .activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL)
        .build();

    @PostMapping("/deser/deserspringjacksonlaissezfaire")
    public Object handle(@RequestBody String json) throws Exception {
        return mapper.readValue(json, Object.class);
    }
}
