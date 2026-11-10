package vulnlab.deser.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

/** L'ObjectMapper est injecté : sa configuration dangereuse est dans LabBeansConfig. */
@RestController
public class DeserSpringInjectedMapper {
    private final ObjectMapper mapper;

    public DeserSpringInjectedMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping("/deser/injectedmapper")
    public Object handle(@RequestBody String json) throws Exception {
        return mapper.readValue(json, Object.class);
    }
}
