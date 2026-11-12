package vulnlab.entete;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

/** CORS : l'origine de la requête est recopiée, avec les cookies autorisés. */
@RestController
public class EnteteCorsReflect {
    @GetMapping("/entete/cors")
    public String profile(@RequestHeader("Origin") String origin, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return "{\"email\":\"alice@example.com\"}";
    }
}
