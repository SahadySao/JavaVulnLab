package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Hôte fixé par une constante de la classe. */
@RestController
public class SsrfSpringConstantBase {
    private static final String BASE = "https://api.interne/users/";
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringconstantbase")
    public String handle(@RequestParam String id) {
        return restTemplate.getForObject(BASE + id, String.class);
    }
}
