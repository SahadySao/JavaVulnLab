package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Paramètre typé Long : Spring refuse la requête si la valeur n'est pas un nombre. */
@RestController
public class SsrfPartialLongVariable {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/partial/longvariable/{id}")
    public String handle(@PathVariable Long id) {
        return restTemplate.getForObject("https://api.interne/users/{id}", String.class, id);
    }
}
