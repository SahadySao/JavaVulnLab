package vulnlab.ssrf.spring;

import java.net.URI;
import java.util.Set;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Liste NOIRE d'hôtes : 127.1, 2130706433, [::1] ou un nom DNS vers 127.0.0.1 passent. */
@RestController
public class SsrfSpringBlocklist {
    private static final Set<String> BLOCKED = Set.of("localhost", "127.0.0.1", "169.254.169.254");
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/blocklist")
    public String handle(@RequestParam String url) {
        URI uri = URI.create(url);
        if (BLOCKED.contains(uri.getHost())) {
            throw new IllegalArgumentException("Hôte interdit");
        }
        return restTemplate.getForObject(uri, String.class);
    }
}
