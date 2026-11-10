package vulnlab.ssrf.spring;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Paramètre encodé : & = # deviennent %26 %3D %23, impossible d'ajouter ou de couper un paramètre. */
@RestController
public class SsrfPartialEncodedQuery {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/partial/encodedquery")
    public String handle(@RequestParam String q) {
        return restTemplate.getForObject(
            "https://api.interne/search?q=" + URLEncoder.encode(q, StandardCharsets.UTF_8) + "&limit=10", String.class);
    }
}
