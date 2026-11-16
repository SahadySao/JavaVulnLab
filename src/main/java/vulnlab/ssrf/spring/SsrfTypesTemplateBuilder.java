package vulnlab.ssrf.spring;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;

/** Type renvoyé par un builder de bibliothèque : RestTemplateBuilder.build() renvoie un RestTemplate (dans le jar). */
@RestController
public class SsrfTypesTemplateBuilder {
    private final RestTemplateBuilder builder;

    public SsrfTypesTemplateBuilder(RestTemplateBuilder builder) {
        this.builder = builder;
    }

    @GetMapping("/ssrf/types/builder")
    public String handle(@RequestParam String url) {
        return builder.build().getForObject(url, String.class);
    }
}
