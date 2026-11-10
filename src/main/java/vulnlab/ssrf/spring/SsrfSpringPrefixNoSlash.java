package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Préfixe sans "/" après l'hôte : path = "@evil.com/" donne https://api.interne@evil.com/ */
@RestController
public class SsrfSpringPrefixNoSlash {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringprefixnoslash")
    public String handle(@RequestParam String path) {
        return restTemplate.getForObject("https://api.interne" + path, String.class);
    }
}
