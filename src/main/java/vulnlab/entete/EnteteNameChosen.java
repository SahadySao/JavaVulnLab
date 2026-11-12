package vulnlab.entete;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

/** Nom de l'en-tête choisi par l'utilisateur (Set-Cookie, Refresh...). */
@RestController
public class EnteteNameChosen {
    @GetMapping("/entete/flag")
    public String flag(@RequestParam String header, HttpServletResponse response) {
        response.addHeader(header, "1");
        return "ok";
    }
}
