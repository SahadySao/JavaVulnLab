package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Hôte fixé, identifiant validé par regex : ni / ni . ni ? ni & ni #. */
@RestController
public class SsrfSpringIdRegex {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/idregex")
    public String handle(@RequestParam String id) {
        if (!id.matches("[a-zA-Z0-9_-]{1,40}")) {
            throw new IllegalArgumentException("Identifiant invalide");
        }
        return restTemplate.getForObject("https://api.interne/users/" + id, String.class);
    }
}
