package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** String.format avec un gabarit qui fixe l'hôte. */
@RestController
public class SsrfSpringFormatFixedHost {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringformatfixedhost")
    public String handle(@RequestParam String id) {
        return restTemplate.getForObject(String.format("https://api.interne/users/%s", id), String.class);
    }
}
