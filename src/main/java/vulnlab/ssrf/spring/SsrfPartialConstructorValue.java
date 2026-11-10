package vulnlab.ssrf.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** @Value injecté par le constructeur, puis gardé dans un champ. */
@RestController
public class SsrfPartialConstructorValue {
    private final String usersUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public SsrfPartialConstructorValue(@Value("${api.users.url}") String usersUrl) {
        this.usersUrl = usersUrl;
    }

    @GetMapping("/ssrf/partial/constructorvalue")
    public String handle(@RequestParam String id) {
        return restTemplate.getForObject(usersUrl + "/" + id + "/profile", String.class);
    }
}
