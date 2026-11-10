package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** startsWith sans "/" final : https://api.interne.evil.com passe le contrôle. */
@RestController
public class SsrfSpringStartsWithNoSlash {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringstartswithnoslash")
    public String handle(@RequestParam String url) {
        if (!url.startsWith("https://api.interne")) {
            throw new IllegalArgumentException("URL refusée");
        }
        return restTemplate.getForObject(url, String.class);
    }
}
