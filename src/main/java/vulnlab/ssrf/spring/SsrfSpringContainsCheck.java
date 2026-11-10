package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Recherche de sous-chaîne : https://evil.com/?api.interne passe le contrôle. */
@RestController
public class SsrfSpringContainsCheck {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringcontainscheck")
    public String handle(@RequestParam String url) {
        if (!url.contains("api.interne")) {
            throw new IllegalArgumentException("URL refusée");
        }
        return restTemplate.getForObject(url, String.class);
    }
}
