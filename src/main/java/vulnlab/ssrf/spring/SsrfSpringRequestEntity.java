package vulnlab.ssrf.spring;

import java.net.URI;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** L'URL passe par RequestEntity.get(...) avant restTemplate.exchange(entity, ...). */
@RestController
public class SsrfSpringRequestEntity {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/requestentity")
    public String handle(@RequestParam String url) {
        RequestEntity<Void> entity = RequestEntity.get(URI.create(url)).build();
        return restTemplate.exchange(entity, String.class).getBody();
    }
}
