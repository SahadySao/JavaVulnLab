package vulnlab.bfla;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/** Purge des paramètres : rôle vérifié dans le code (request.isUserInRole). */
@RestController
public class BflaProgrammaticCheck {
    @PostMapping("/bfla/settings/purge")
    public void purge(HttpServletRequest request) {
        if (!request.isUserInRole("ADMIN")) {
            throw new SecurityException("refusé");
        }
    }
}
