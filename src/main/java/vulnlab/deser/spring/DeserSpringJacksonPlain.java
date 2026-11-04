package vulnlab.deser.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.Order;

/** Jackson par défaut : la classe est fixée par le code (Order), pas par le JSON. */
@RestController
public class DeserSpringJacksonPlain {
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/deser/deserspringjacksonplain")
    public Object handle(@RequestBody String json) throws Exception {
        Order order = mapper.readValue(json, Order.class);
        return order.id;
    }
}
