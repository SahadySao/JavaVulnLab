package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Gabarit d'URI : Spring encode {id}, la valeur ne sort pas de son emplacement. */
@RestController
public class SsrfSpringUriTemplate {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringuritemplate")
    public String handle(@RequestParam String id) {
        return restTemplate.getForObject("https://api.interne/users/{id}", String.class, id);
    }
}
