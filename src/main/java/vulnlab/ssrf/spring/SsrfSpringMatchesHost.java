package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Regex qui impose schéma://hôte/ : l'hôte ne peut plus changer. */
@RestController
public class SsrfSpringMatchesHost {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringmatcheshost")
    public String handle(@RequestParam String url) {
        if (!url.matches("https://api\\.interne/.*")) {
            throw new IllegalArgumentException("URL refusée");
        }
        return restTemplate.getForObject(url, String.class);
    }
}
