package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Gabarit RestTemplate : mode URI_COMPONENT, "/" et "." ne sont pas encodés dans {id}. */
@RestController
public class SsrfPartialRestTemplateVar {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/partial/resttemplatevar")
    public String handle(@RequestParam String id) {
        return restTemplate.getForObject("https://api.interne/users/{id}", String.class, id);
    }
}
