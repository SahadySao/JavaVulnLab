package vulnlab.entete;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Set;
import org.springframework.web.bind.annotation.*;

/** CORS : origine recopiée seulement si elle est dans une liste blanche. */
@RestController
public class EnteteCorsAllowlist {
    private static final Set<String> ALLOWED_ORIGINS = Set.of("https://app.example.com", "https://admin.example.com");

    @GetMapping("/entete/cors-allowlist")
    public String profile(@RequestHeader("Origin") String origin, HttpServletResponse response) {
        if (!ALLOWED_ORIGINS.contains(origin)) {
            response.setStatus(403);
            return "";
        }
        response.setHeader("Access-Control-Allow-Origin", origin);
        return "{\"email\":\"alice@example.com\"}";
    }
}
