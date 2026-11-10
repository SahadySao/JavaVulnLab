package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

/** Gabarit WebClient : "/" est encodé, mais ".." reste ".." : /users/../profile = /profile. */
@RestController
public class SsrfPartialWebClientVar {
    private final WebClient webClient = WebClient.create();

    @GetMapping("/ssrf/partial/webclientvar")
    public String handle(@RequestParam String id) {
        return webClient.get().uri("https://api.interne/users/{id}/profile", id).retrieve().bodyToMono(String.class).block();
    }
}
