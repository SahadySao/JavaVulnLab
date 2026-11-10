package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Hôte écrit dans le code, chemin complété par l'utilisateur. */
@RestController
public class SsrfPartialLiteralPath {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/partial/literalpath")
    public String handle(@RequestParam String id) {
        return restTemplate.getForObject("https://api.interne/users/" + id, String.class);
    }
}
