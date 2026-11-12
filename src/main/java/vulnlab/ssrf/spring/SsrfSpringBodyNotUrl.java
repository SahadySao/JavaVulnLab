package vulnlab.ssrf.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Le corps reçu est ENVOYÉ (2e argument), l'URL est fixée par la configuration : pas de SSRF.
  * Joern fait passer la donnée de body à url (arguments voisins) : filtre ArgumentVoisin. */
@RestController
public class SsrfSpringBodyNotUrl {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${notes.url}")
    private String notesUrl;

    @PostMapping("/ssrf/notes")
    public String forward(@RequestBody String body) {
        String url = notesUrl + "/notes";
        return restTemplate.postForObject(url, body, String.class);
    }
}
