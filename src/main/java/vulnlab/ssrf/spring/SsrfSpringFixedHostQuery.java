package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Hôte fixé, suivi d'un "/" : l'utilisateur ne choisit que le paramètre. */
@RestController
public class SsrfSpringFixedHostQuery {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringfixedhostquery")
    public String handle(@RequestParam String id) {
        return restTemplate.getForObject("https://api.interne/users?id=" + id, String.class);
    }
}
