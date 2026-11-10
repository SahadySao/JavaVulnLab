package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Regex sans "/" après l'hôte : https://api.interne.evil.com passe. */
@RestController
public class SsrfSpringMatchesNoSlash {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringmatchesnoslash")
    public String handle(@RequestParam String url) {
        if (!url.matches("https://api\\.interne.*")) {
            throw new IllegalArgumentException("URL refusée");
        }
        return restTemplate.getForObject(url, String.class);
    }
}
