package vulnlab.ssrf.spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vulnlab.common.ApiProperties;

/** URL de base lue dans une classe @ConfigurationProperties. */
@RestController
public class SsrfPartialConfigProps {
    private final ApiProperties properties;
    private final RestTemplate restTemplate = new RestTemplate();

    public SsrfPartialConfigProps(ApiProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/ssrf/partial/configprops")
    public String handle(@RequestParam String orderId) {
        return restTemplate.getForObject(properties.getBaseUrl() + "/orders/" + orderId, String.class);
    }
}
