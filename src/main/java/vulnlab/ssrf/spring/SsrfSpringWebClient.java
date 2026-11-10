package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

/** WebClient : l'URL passe dans la chaîne get().uri(...). */
@RestController
public class SsrfSpringWebClient {
    private final WebClient webClient = WebClient.create();

    @GetMapping("/ssrf/ssrfspringwebclient")
    public String handle(@RequestParam String target) {
        return webClient.get().uri(target).retrieve().bodyToMono(String.class).block();
    }
}
