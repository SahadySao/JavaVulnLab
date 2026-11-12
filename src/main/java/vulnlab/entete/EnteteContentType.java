package vulnlab.entete;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** Type de contenu choisi par l'utilisateur : type = text/html rend le corps exécutable. */
@Controller
public class EnteteContentType {
    @GetMapping("/entete/preview")
    public void preview(@RequestParam String type, @RequestParam String body, HttpServletResponse response) throws IOException {
        response.setContentType(type);
        response.getOutputStream().write(body.getBytes());
    }
}
