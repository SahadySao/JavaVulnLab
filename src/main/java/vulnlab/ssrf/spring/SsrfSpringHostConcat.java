package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Schéma et chemin fixés, mais l'HÔTE vient de l'utilisateur. */
@RestController
public class SsrfSpringHostConcat {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringhostconcat")
    public String handle(@RequestParam String host) {
        return restTemplate.getForObject("https://" + host + "/api/status", String.class);
    }
}
