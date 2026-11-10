package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/** UriComponentsBuilder : hôte fixé, la recherche n'entre que dans le paramètre q. */
@RestController
public class SsrfSpringUriBuilderQuery {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/ssrfspringuribuilderquery")
    public String handle(@RequestParam String q) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.interne/search").queryParam("q", q).toUriString();
        return restTemplate.getForObject(url, String.class);
    }
}
