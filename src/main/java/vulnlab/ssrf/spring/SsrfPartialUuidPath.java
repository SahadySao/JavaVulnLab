package vulnlab.ssrf.spring;

import java.util.UUID;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** UUID.fromString : la valeur ne peut contenir que des chiffres hexadécimaux et des tirets. */
@RestController
public class SsrfPartialUuidPath {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/partial/uuidpath")
    public String handle(@RequestParam String id) {
        UUID userId = UUID.fromString(id);
        return restTemplate.getForObject("https://api.interne/users/" + userId, String.class);
    }
}
