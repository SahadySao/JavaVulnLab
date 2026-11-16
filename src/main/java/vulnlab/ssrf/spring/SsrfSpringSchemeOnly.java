package vulnlab.ssrf.spring;

import java.net.URI;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Seul le SCHÉMA est vérifié : https://169.254.169.254/ ou https://intranet/ passent. */
@RestController
public class SsrfSpringSchemeOnly {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/schemeonly")
    public String handle(@RequestParam String url) {
        if (!"https".equals(URI.create(url).getScheme())) {
            throw new IllegalArgumentException("https uniquement");
        }
        return restTemplate.getForObject(url, String.class);
    }
}
