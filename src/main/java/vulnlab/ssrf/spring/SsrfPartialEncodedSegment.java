package vulnlab.ssrf.spring;

import java.nio.charset.StandardCharsets;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

/** encodePathSegment encode "/", mais pas ".." : /users/../profile remonte quand même d'un niveau. */
@RestController
public class SsrfPartialEncodedSegment {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/partial/encodedsegment")
    public String handle(@RequestParam String id) {
        return restTemplate.getForObject(
            "https://api.interne/users/" + UriUtils.encodePathSegment(id, StandardCharsets.UTF_8) + "/profile", String.class);
    }
}
