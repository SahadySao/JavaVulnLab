package vulnlab.ssrf.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** URL de base dans la configuration, chemin complété par l'utilisateur : id = "../admin/reset?x=" */
@RestController
public class SsrfPartialValueBase {
    @Value("${api.users.url}")
    private String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ssrf/partial/valuebase")
    public String handle(@RequestParam String id) {
        return restTemplate.getForObject(baseUrl + "/users/" + id, String.class);
    }
}
