package vulnlab.ssrf.spring;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** Proxy : le chemin reçu (getRequestURI) est recopié vers l'API interne. Hôte fixé par la configuration,
  * chemin choisi par l'utilisateur (../ vers d'autres points d'accès) : SSRF partielle, portée chemin.
  * Deux gestionnaires utilisent la même construction : un seul résultat, l'autre sink dans autresSinks. */
@RestController
@RequestMapping("/proxy")
public class SsrfSpringProxyPath {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${backend.url}")
    private String backendUrl;

    @GetMapping("/**")
    public String get(HttpServletRequest request) {
        String url = buildUrl(request);
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
    }

    @PostMapping("/**")
    public String post(HttpServletRequest request, @RequestBody String body) {
        String url = buildUrl(request);
        return restTemplate.postForObject(url, body, String.class);
    }

    private String buildUrl(HttpServletRequest request) {
        String prefix = request.getContextPath() + "/proxy";
        return backendUrl + request.getRequestURI().substring(prefix.length());
    }
}
