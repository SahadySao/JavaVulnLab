package vulnlab.entete;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

/** Valeur numérique : ne peut rien injecter. */
@RestController
public class EnteteNumeric {
    @GetMapping("/entete/page")
    public String page(@RequestParam int page, HttpServletResponse response) {
        response.setHeader("X-Page", String.valueOf(page));
        return "ok";
    }
}
