package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** startsWith avec le "/" après l'hôte : l'hôte ne peut plus changer. */
@RestController
public class SsrfSpringStartsWithSlash {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringstartswithslash")
    public String handle(@RequestParam String url) {
        if (!url.startsWith("https://api.interne/")) {
            throw new IllegalArgumentException("URL refusée");
        }
        return restTemplate.getForObject(url, String.class);
    }
}
