package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** RestTemplate sur une URL choisie par l'utilisateur (aperçu de lien, import par URL...). */
@RestController
public class SsrfSpringRestTemplate {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringresttemplate")
    public String handle(@RequestParam String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
