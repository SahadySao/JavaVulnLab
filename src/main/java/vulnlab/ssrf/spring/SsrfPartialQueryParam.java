package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Injection de paramètres : q = "x&role=admin" ajoute un paramètre, "x#" coupe la fin. */
@RestController
public class SsrfPartialQueryParam {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/partial/queryparam")
    public String handle(@RequestParam String q) {
        return restTemplate.getForObject("https://api.interne/search?q=" + q + "&limit=10", String.class);
    }
}
